package com.sg.ocr;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.chinese.ChineseTextRecognizerOptions;
import com.sg.ocr.bean.BaseResEntity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class OcrHelper {

    private OcrHelper() {
    }

    private static class Holder {
        private static final OcrHelper INSTANCE = new OcrHelper();
    }

    public static OcrHelper getInstance() {
        return OcrHelper.Holder.INSTANCE;
    }


    private InitOcrSdkListener mInitOcrSdkListener;
    private OcrTextListener mListener;
    private Context mContext;

    public void init( InitOcrSdkListener listener) {
        this.mInitOcrSdkListener = listener;
        this.mContext = getAppContext();

        if (mContext != null && checkPermission()) {
            SPUtilNoDelete.getInstance().init(mContext);
            checkIsAuthorized(CommonUtils.getSN(mContext));
        }
    }


    public void ocrTextFromBitmap(Bitmap bitmap, OcrTextListener listener) {
        this.mListener = listener;
        InputImage image = InputImage.fromBitmap(bitmap, 0);
        ChineseTextRecognizerOptions build = new ChineseTextRecognizerOptions.Builder().build();
        TextRecognizer recognizer = TextRecognition.getClient(build);
        recognizer.process(image).addOnSuccessListener(new OnSuccessListener<Text>() {
            @Override
            public void onSuccess(Text text) {
                recognizer.close();
                handlePhotoText(text);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // 处理错误
                e.printStackTrace();
                recognizer.close();

                if (mListener != null) {
                    mListener.OnFailure(e);
                }
            }
        });
    }

    private List<String> reduceList = new ArrayList<>();//适配器所需数据源

    private void handlePhotoText(Text result) {
        Log.d("TAG", "===result===" + result.getText());
        reduceList.clear();
        for (Text.TextBlock block : result.getTextBlocks()) {
            for (Text.Line line : block.getLines()) {
                String lineText = line.getText();
                Log.d("", "===Line文本===： " + lineText);
                reduceList.add(lineText);
            }
        }

        if (mListener != null) {
            mListener.onSuccess(reduceList);
        }
    }


    private void checkIsAuthorized(String deviceNo) {
        if (TextUtils.isEmpty(deviceNo)) {
            Log.d("TAG", "初始化失败，设备编号为空");
            if (mInitOcrSdkListener != null) {
                mInitOcrSdkListener.onInitResult(false, "初始化失败，设备编号为空");
            }
        } else {
            Log.d("TAG","===checkIsAuthorized deviceNo==="+deviceNo);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String url = "http://pa.unioncore.vip/paApi/v1/terminalVerifyV3";
                    String domain = "http://ocrsdk.unioncore.vip";


                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("terminalNo", deviceNo);
                        jsonObject.put("appVersion", "1.0.0");
                        jsonObject.put("domain", domain);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    String json = jsonObject.toString();
                    MediaType mediaType = MediaType.get("application/json");
                    RequestBody requestBody = RequestBody.create(json, mediaType);

                    // 创建OkHttpClient实例
                    OkHttpClient okHttpClient = new OkHttpClient.Builder()
                            .connectTimeout(15, TimeUnit.SECONDS) // 设置连接超时时间为10秒
                            .readTimeout(15, TimeUnit.SECONDS) // 设置读取超时时间为10秒
                            .build();


                    final Request request = new Request.Builder()
                            .url(url)
                            .post(requestBody)
                            .build();

                    Call call = okHttpClient.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.d("TAG", "初始化失败 request onFailure: " + e.getMessage());
                            if (mInitOcrSdkListener != null) {
                                mInitOcrSdkListener.onInitResult(false, "初始化失败 request onFailure:" + e.getMessage());
                            }
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String json = response.body().string();

                            Log.d("TAG", "request onResponse: " + json);

                            BaseResEntity bean = new Gson().fromJson(json, BaseResEntity.class);
                            if (bean != null && 200 == bean.getCode() && bean.getData() != null && bean.getData().isIfFindTerminal()) {
                                Log.d("TAG", "初始化成功");
                                SPUtilNoDelete.getInstance().setIsAuthorized(true);
                                if (mInitOcrSdkListener != null) {
                                    mInitOcrSdkListener.onInitResult(true, "初始化成功");
                                }
                            } else if (bean != null && !TextUtils.isEmpty(bean.getMsg())) {
                                Log.d("TAG", "初始化失败，设备授权失败：" + bean.getMsg());
                                SPUtilNoDelete.getInstance().setIsAuthorized(false);
                                if (mInitOcrSdkListener != null) {
                                    mInitOcrSdkListener.onInitResult(false, bean.getMsg());
                                }
                            } else {
                                Log.d("TAG", "初始化失败，设备授权失败");
                                SPUtilNoDelete.getInstance().setIsAuthorized(false);
                                if (mInitOcrSdkListener != null) {
                                    mInitOcrSdkListener.onInitResult(false, "初始化失败，设备授权失败");
                                }
                            }
                        }
                    });
                }

            }).start();
        }

    }

    public Context getAppContext() {
        try {
            Context appContext = (Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication").invoke((Object) null, (Object[]) null);
            return appContext;
        } catch (Exception e) {
            e.printStackTrace();
            if (mInitOcrSdkListener != null) {
                mInitOcrSdkListener.onInitResult(false, "初始化失败，获取上下文失败");
            }
            return null;
        }
    }

    private boolean checkPermission() {
        final String permission = Manifest.permission.INTERNET;
//        final String permission1 = Manifest.permission.WRITE_EXTERNAL_STORAGE;
//        final String permission2 = Manifest.permission.READ_EXTERNAL_STORAGE;
        if (ContextCompat.checkSelfPermission(mContext, permission) != PackageManager.PERMISSION_GRANTED
                /*|| ContextCompat.checkSelfPermission(mContext, permission1) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(mContext, permission2) != PackageManager.PERMISSION_GRANTED*/) {
            //没有权限
            Log.d("TAG", "初始化失败，获取网络权限失败");
            if (mInitOcrSdkListener != null) {
                mInitOcrSdkListener.onInitResult(false, "初始化失败，获取网络权限失败");
            }
            return false;
        } else {
            //有权限
            return true;
        }
    }

}
