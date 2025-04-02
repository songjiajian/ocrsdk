package com.sg.ocr;

import androidx.annotation.NonNull;

import java.util.List;

public interface InitOcrSdkListener {
    void onInitResult(boolean result,String msg);
}
