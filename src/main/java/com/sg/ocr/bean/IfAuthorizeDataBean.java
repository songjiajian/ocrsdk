package com.sg.ocr.bean;

import java.io.Serializable;

public class IfAuthorizeDataBean {

    /**
     * projectInfo : {"id":"7efdd75c-0498-4a74-ba7a-4b12da133b18","companyId":"07ba3508-e2d5-4659-be4a-758408f2250e","projectName":"测试项目01","projectCode":"test01","inChargePerson":"郝峻","linkPhone":"18665808923","projectInfo":"用来测试项目","address":"龙华区大浪默根","remark":"暂无","lat":"22.545652","lng":"114.068289","lastUserInfoModiDate":1677052396403,"lastUserInfoModiFlag":"20230222035316403","deleted":0,"onDutyTime":"","offDutyTime":"","signInStartTime":"00:00","signInEndTime":"11:59","signOutStartTime":"12:00","signOutEndTime":"23:59","signMode":1}
     * ifFindTerminal : true
     * reqDomain : pa.unioncore.vip
     * deviceInfo : {"id":"621daa7d-3c7f-4758-917e-3a475fa21da7","companyId":"07ba3508-e2d5-4659-be4a-758408f2250e","projectId":"7efdd75c-0498-4a74-ba7a-4b12da133b18","projectName":"测试项目01","projectCode":"test01","ckpId":"48beac71-1b92-41c2-9c0d-46e3510aa4c1","ckpName":"南门打卡点01","ckpCode":"S001","createDate":1681803983936,"remark":"","deleted":0,"deviceId":"9a36aaa5-f070-4442-92d5-9c5d0079ba92","deviceNo":"0010202303016364","ifEnable":1}
     * terminalDomain : pa.unioncore.vip
     */

    private Object projectInfo;
    private boolean ifFindTerminal;
    private String reqDomain;
    private DeviceInfoBean deviceInfo;
    private NfcCfgBean nfcCfg;
    private String terminalDomain;

    public NfcCfgBean getNfcCfg() {
        return nfcCfg;
    }

    public void setNfcCfg(NfcCfgBean nfcCfg) {
        this.nfcCfg = nfcCfg;
    }

    public Object getProjectInfo() {
        return projectInfo;
    }

    public void setProjectInfo(Object projectInfo) {
        this.projectInfo = projectInfo;
    }

    public boolean isIfFindTerminal() {
        return ifFindTerminal;
    }

    public void setIfFindTerminal(boolean ifFindTerminal) {
        this.ifFindTerminal = ifFindTerminal;
    }

    public String getReqDomain() {
        return reqDomain;
    }

    public void setReqDomain(String reqDomain) {
        this.reqDomain = reqDomain;
    }

    public DeviceInfoBean getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(DeviceInfoBean deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getTerminalDomain() {
        return terminalDomain;
    }

    public void setTerminalDomain(String terminalDomain) {
        this.terminalDomain = terminalDomain;
    }

    public static class NfcCfgBean implements Serializable {

        /**
         * nfcAppId : 1OP20230608094046698
         * nfcEnvCode : 52302
         * nfcPort : 9989
         * nfcServerIP : es.eidlink.com
         * nfcTravelPort : 1444
         */

        private String nfcAppId;
        private int nfcEnvCode;
        private int nfcPort;
        private String nfcServerIP;
        private int nfcTravelPort;

        public String getNfcAppId() {
            return nfcAppId;
        }

        public void setNfcAppId(String nfcAppId) {
            this.nfcAppId = nfcAppId;
        }

        public int getNfcEnvCode() {
            return nfcEnvCode;
        }

        public void setNfcEnvCode(int nfcEnvCode) {
            this.nfcEnvCode = nfcEnvCode;
        }

        public int getNfcPort() {
            return nfcPort;
        }

        public void setNfcPort(int nfcPort) {
            this.nfcPort = nfcPort;
        }

        public String getNfcServerIP() {
            return nfcServerIP;
        }

        public void setNfcServerIP(String nfcServerIP) {
            this.nfcServerIP = nfcServerIP;
        }

        public int getNfcTravelPort() {
            return nfcTravelPort;
        }

        public void setNfcTravelPort(int nfcTravelPort) {
            this.nfcTravelPort = nfcTravelPort;
        }
    }


    public static class DeviceInfoBean implements Serializable {
        /**
         * id : 621daa7d-3c7f-4758-917e-3a475fa21da7
         * companyId : 07ba3508-e2d5-4659-be4a-758408f2250e
         * projectId : 7efdd75c-0498-4a74-ba7a-4b12da133b18
         * projectName : 测试项目01
         * projectCode : test01
         * ckpId : 48beac71-1b92-41c2-9c0d-46e3510aa4c1
         * ckpName : 南门打卡点01
         * ckpCode : S001
         * createDate : 1681803983936
         * remark :
         * deleted : 0
         * deviceId : 9a36aaa5-f070-4442-92d5-9c5d0079ba92
         * deviceNo : 0010202303016364
         * ifEnable : 1
         */

        private String id;
        private String companyId;
        private String projectId;
        private String projectName;
        private String projectCode;
        private String ckpId;   //打卡点Id
        private String ckpName; //打卡点名称
        private String ckpCode; //打卡点编号
        private long createDate;
        private String remark;
        private int deleted;
        private String deviceId;
        private String deviceNo;
        private int ifEnable;
        private String ckpEmpName; //检查人姓名
        private String ckpEmpId; //检查人姓名

        public String getCkpEmpName() {
            return ckpEmpName;
        }

        public void setCkpEmpName(String ckpEmpName) {
            this.ckpEmpName = ckpEmpName;
        }

        public String getCkpEmpId() {
            return ckpEmpId;
        }

        public void setCkpEmpId(String ckpEmpId) {
            this.ckpEmpId = ckpEmpId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCompanyId() {
            return companyId;
        }

        public void setCompanyId(String companyId) {
            this.companyId = companyId;
        }

        public String getProjectId() {
            return projectId;
        }

        public void setProjectId(String projectId) {
            this.projectId = projectId;
        }

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

        public String getProjectCode() {
            return projectCode;
        }

        public void setProjectCode(String projectCode) {
            this.projectCode = projectCode;
        }

        public String getCkpId() {
            return ckpId;
        }

        public void setCkpId(String ckpId) {
            this.ckpId = ckpId;
        }

        public String getCkpName() {
            return ckpName;
        }

        public void setCkpName(String ckpName) {
            this.ckpName = ckpName;
        }

        public String getCkpCode() {
            return ckpCode;
        }

        public void setCkpCode(String ckpCode) {
            this.ckpCode = ckpCode;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getDeleted() {
            return deleted;
        }

        public void setDeleted(int deleted) {
            this.deleted = deleted;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getDeviceNo() {
            return deviceNo;
        }

        public void setDeviceNo(String deviceNo) {
            this.deviceNo = deviceNo;
        }

        public int getIfEnable() {
            return ifEnable;
        }

        public void setIfEnable(int ifEnable) {
            this.ifEnable = ifEnable;
        }
    }

}
