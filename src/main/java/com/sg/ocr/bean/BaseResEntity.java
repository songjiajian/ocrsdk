package com.sg.ocr.bean;




public class BaseResEntity {

    private IfAuthorizeDataBean data;
    private int code;
    private String msg;
    private String message;
    private boolean success;
    private String methodName;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public BaseResEntity() {
    }

    public BaseResEntity(IfAuthorizeDataBean data) {
        this.code = 0;
        this.data = data;
    }

    public BaseResEntity(IfAuthorizeDataBean data, int code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }


    public BaseResEntity(IfAuthorizeDataBean data, int code, String msg, boolean success) {
        this.data = data;
        this.code = code;
        this.msg = msg;
        this.success = success;
    }

    public BaseResEntity(IfAuthorizeDataBean data, int code, String msg, String methodName) {
        this.data = data;
        this.code = code;
        this.msg = msg;
        this.methodName = methodName;
    }

    public boolean isSucceed() {
        return code == 0;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public IfAuthorizeDataBean getData() {
        return data;
    }


    @Override
    public String toString() {
        return "BasicResponseEntity{" +
                "data=" + data +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                ", methodName='" + methodName + '\'' +
                '}';
    }
}
