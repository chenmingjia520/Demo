package com.lanyoumobility.mobility_webview.mode.entity;


import com.lanyoumobility.mobility_webview.mode.base.BaseBean;

public class TaskNumBean extends BaseBean {

    /**
     * data : {"giveCarTask":"2","returnCarTask":"2","totalTaskNum":"2643","electricTask":"5","recoveryCarTask":"2","prepareCarTask":"6","identityTask":"2626"}
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
         * giveCarTask : 2
         * returnCarTask : 2
         * totalTaskNum : 2643
         * electricTask : 5
         * recoveryCarTask : 2
         * prepareCarTask : 6
         * identityTask : 2626
         */

        private String giveCarTask;// 送车上门任务数
        private String returnCarTask;// 还车任务数
        private String totalTaskNum;// 总任务数
        private String electricTask;// 充好电任务数
        private String recoveryCarTask;// 上门收车任务数
        private String prepareCarTask;// 整备任务数
        private String identityTask;// 认证任务数


        public String getGiveCarTask() {
            return giveCarTask;
        }

        public void setGiveCarTask(String giveCarTask) {
            this.giveCarTask = giveCarTask;
        }

        public String getReturnCarTask() {
            return returnCarTask;
        }

        public void setReturnCarTask(String returnCarTask) {
            this.returnCarTask = returnCarTask;
        }

        public String getTotalTaskNum() {
            return totalTaskNum;
        }

        public void setTotalTaskNum(String totalTaskNum) {
            this.totalTaskNum = totalTaskNum;
        }

        public String getElectricTask() {
            return electricTask;
        }

        public void setElectricTask(String electricTask) {
            this.electricTask = electricTask;
        }

        public String getRecoveryCarTask() {
            return recoveryCarTask;
        }

        public void setRecoveryCarTask(String recoveryCarTask) {
            this.recoveryCarTask = recoveryCarTask;
        }

        public String getPrepareCarTask() {
            return prepareCarTask;
        }

        public void setPrepareCarTask(String prepareCarTask) {
            this.prepareCarTask = prepareCarTask;
        }

        public String getIdentityTask() {
            return identityTask;
        }

        public void setIdentityTask(String identityTask) {
            this.identityTask = identityTask;
        }
    }
}
