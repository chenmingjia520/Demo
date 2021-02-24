package com.lanyoumobility.mobility_webview.mode.entity;

import com.lanyoumobility.mobility_webview.mode.base.BaseBean;

public class UserInfoBean extends BaseBean {


    /**
     * result : {"id":"1357598592361095168","userName":"15801653480","passWord":"123456","mobile":"15801653480","spareMobile":null,"email":null,"realName":null,"hometown":null,"industry":null,"signature":null,"hobby":null,"status":false,"age":0,"gender":0,"userType":0,"lastLoginTime":"2021-02-05 15:55:02","isAllowOrder":0,"isCustomerProtocol":0,"isSecretProtocol":0,"isStaff":null,"createTime":"2021-02-05 15:55:02","updateTime":"2021-02-05 15:55:02","provinceName":null,"cityName":null,"carrier":null,"isDelete":0,"inviterid":null}
     * pagination : null
     */

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }


    public static class ResultBean {
        /**
         * id : 1357598592361095168
         * userName : 15801653480
         * passWord : 123456
         * mobile : 15801653480
         * spareMobile : null
         * email : null
         * realName : null
         * hometown : null
         * industry : null
         * signature : null
         * hobby : null
         * status : false
         * age : 0
         * gender : 0
         * userType : 0
         * lastLoginTime : 2021-02-05 15:55:02
         * isAllowOrder : 0
         * isCustomerProtocol : 0
         * isSecretProtocol : 0
         * isStaff : null
         * createTime : 2021-02-05 15:55:02
         * updateTime : 2021-02-05 15:55:02
         * provinceName : null
         * cityName : null
         * carrier : null
         * isDelete : 0
         * inviterid : null
         */

        private String id;
        private String userName;
        private String passWord;
        private String mobile;
        private String spareMobile;
        private String email;
        private String realName;
        private String hometown;
        private String industry;
        private String signature;
        private String hobby;
        private boolean status;
        private int age;
        private int gender;
        private int userType;
        private String lastLoginTime;
        private int isAllowOrder;
        private int isCustomerProtocol;
        private int isSecretProtocol;
        private String isStaff;
        private String createTime;
        private String updateTime;
        private String provinceName;
        private String cityName;
        private String carrier;
        private int isDelete;
        private String inviterid;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassWord() {
            return passWord;
        }

        public void setPassWord(String passWord) {
            this.passWord = passWord;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getSpareMobile() {
            return spareMobile;
        }

        public void setSpareMobile(String spareMobile) {
            this.spareMobile = spareMobile;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getHometown() {
            return hometown;
        }

        public void setHometown(String hometown) {
            this.hometown = hometown;
        }

        public String getIndustry() {
            return industry;
        }

        public void setIndustry(String industry) {
            this.industry = industry;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getHobby() {
            return hobby;
        }

        public void setHobby(String hobby) {
            this.hobby = hobby;
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public String getLastLoginTime() {
            return lastLoginTime;
        }

        public void setLastLoginTime(String lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
        }

        public int getIsAllowOrder() {
            return isAllowOrder;
        }

        public void setIsAllowOrder(int isAllowOrder) {
            this.isAllowOrder = isAllowOrder;
        }

        public int getIsCustomerProtocol() {
            return isCustomerProtocol;
        }

        public void setIsCustomerProtocol(int isCustomerProtocol) {
            this.isCustomerProtocol = isCustomerProtocol;
        }

        public int getIsSecretProtocol() {
            return isSecretProtocol;
        }

        public void setIsSecretProtocol(int isSecretProtocol) {
            this.isSecretProtocol = isSecretProtocol;
        }

        public String getIsStaff() {
            return isStaff;
        }

        public void setIsStaff(String isStaff) {
            this.isStaff = isStaff;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getCarrier() {
            return carrier;
        }

        public void setCarrier(String carrier) {
            this.carrier = carrier;
        }

        public int getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(int isDelete) {
            this.isDelete = isDelete;
        }

        public String getInviterid() {
            return inviterid;
        }

        public void setInviterid(String inviterid) {
            this.inviterid = inviterid;
        }
    }
}
