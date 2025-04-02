package com.sg.ocr.bean;

import java.io.Serializable;

public class CheckVersionResponseBean implements Serializable {



    private int code;
    private String msg;
    private EntityCheckVersion data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public EntityCheckVersion getData() {
        return data;
    }

    public void setData(EntityCheckVersion data) {
        this.data = data;
    }

    public static class EntityCheckVersion implements Serializable {

        private String title;
        private int ifForce;
        private String content;
        private int versionCode;
        private String updateDate;
        private String apkUrl;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getIfForce() {
            return ifForce;
        }

        public void setIfForce(int ifForce) {
            this.ifForce = ifForce;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(int versionCode) {
            this.versionCode = versionCode;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getApkUrl() {
            return apkUrl;
        }

        public void setApkUrl(String apkUrl) {
            this.apkUrl = apkUrl;
        }
    }
}
