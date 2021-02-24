package com.lanyoumobility.mobility_webview.update.bean;


import com.lanyoumobility.mobility_webview.mode.base.BaseBean;

/**
 * Created by EZ on 2017/8/5.
 */

public class UpdateInfoBean extends BaseBean {

    /**
     * data : {"id":"2","type":"2","editionType":1,"versionNumber":"123","versionCode":"122","forceUpdateNumber":"12","forceUpdateCode":"12","isForceUpdate":"1","updateTime":"2021-01-06 19:07:33","updates":"1302933558852534179","creates":"1302933558852534179","createTime":"2021-01-05 15:56:31","installName":"测测测测测测测测测测5","updateContent":"<p>测测测测测测测123321<\/p>","downloadPath":"测测测测测测测测测测测测6"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 2
         * type : 2
         * editionType : 1
         * versionNumber : 123
         * versionCode : 122
         * forceUpdateNumber : 12
         * forceUpdateCode : 12
         * isForceUpdate : 1
         * updateTime : 2021-01-06 19:07:33
         * updates : 1302933558852534179
         * creates : 1302933558852534179
         * createTime : 2021-01-05 15:56:31
         * installName : 测测测测测测测测测测5
         * updateContent : <p>测测测测测测测123321</p>
         * downloadPath : 测测测测测测测测测测测测6
         */

        private String id;
        private String type;
        private int editionType;
        private String versionNumber;
        private String versionCode;
        private String forceUpdateNumber;
        private String forceUpdateCode;
        private String isForceUpdate;
        private String updateTime;
        private String updates;
        private String creates;
        private String createTime;
        private String installName;
        private String updateContent;
        private String downloadPath;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getEditionType() {
            return editionType;
        }

        public void setEditionType(int editionType) {
            this.editionType = editionType;
        }

        public String getVersionNumber() {
            return versionNumber;
        }

        public void setVersionNumber(String versionNumber) {
            this.versionNumber = versionNumber;
        }

        public String getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(String versionCode) {
            this.versionCode = versionCode;
        }

        public String getForceUpdateNumber() {
            return forceUpdateNumber;
        }

        public void setForceUpdateNumber(String forceUpdateNumber) {
            this.forceUpdateNumber = forceUpdateNumber;
        }

        public String getForceUpdateCode() {
            return forceUpdateCode;
        }

        public void setForceUpdateCode(String forceUpdateCode) {
            this.forceUpdateCode = forceUpdateCode;
        }

        public String getIsForceUpdate() {
            return isForceUpdate;
        }

        public void setIsForceUpdate(String isForceUpdate) {
            this.isForceUpdate = isForceUpdate;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getUpdates() {
            return updates;
        }

        public void setUpdates(String updates) {
            this.updates = updates;
        }

        public String getCreates() {
            return creates;
        }

        public void setCreates(String creates) {
            this.creates = creates;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getInstallName() {
            return installName;
        }

        public void setInstallName(String installName) {
            this.installName = installName;
        }

        public String getUpdateContent() {
            return updateContent;
        }

        public void setUpdateContent(String updateContent) {
            this.updateContent = updateContent;
        }

        public String getDownloadPath() {
            return downloadPath;
        }

        public void setDownloadPath(String downloadPath) {
            this.downloadPath = downloadPath;
        }
    }
}
