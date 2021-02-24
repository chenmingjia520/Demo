package com.lanyoumobility.mobility_webview.mode.entity;

import com.google.gson.annotations.SerializedName;
import com.lanyoumobility.mobility_webview.mode.base.BaseBean;

import java.io.Serializable;

public class WxPayDepositBean extends BaseBean {


    /**
     * data : {"appid":"wx5789663213f577e7","noncestr":"b0412462f23f496d9fdaf08815cc279c","package":"Sign=WXPay","partnerid":"1569825871","prepayid":"wx25084353869845a58adcf03ec9fd5c0000","sign":"4DFC5B57A095D2923F109D4620545122","timestamp":"1598316233"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * appid : wx5789663213f577e7
         * noncestr : b0412462f23f496d9fdaf08815cc279c
         * package : Sign=WXPay
         * partnerid : 1569825871
         * prepayid : wx25084353869845a58adcf03ec9fd5c0000
         * sign : 4DFC5B57A095D2923F109D4620545122
         * timestamp : 1598316233
         */

        private String appid;
        private String noncestr;
        @SerializedName("package")
        private String packageX;
        private String partnerid;
        private String prepayid;
        private String sign;
        private String timestamp;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }
    }
}
