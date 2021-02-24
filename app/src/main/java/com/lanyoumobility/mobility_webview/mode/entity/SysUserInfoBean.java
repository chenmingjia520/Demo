package com.lanyoumobility.mobility_webview.mode.entity;

import com.google.gson.annotations.SerializedName;
import com.lanyoumobility.mobility_webview.mode.base.BaseBean;

import java.io.Serializable;
import java.util.List;

public class SysUserInfoBean extends BaseBean {
    /**
     * data : {"userInfo":{"id":"1306710186453374288","realName":"陈明佳专营店","userName":"cmjzyd","avatar":"/avatar2.jpg","status":"0","telephone":"13098765678","email":"1230987@163.com","gender":"0","lastLoginIp":"112.96.194.199","lastLoginTime":"2021-01-07 20:38:03","createTime":null,"loginApp":null,"groupIds":["1"],"companyIds":[],"cityIds":[],"parkIds":["121"],"branchIds":["121"]},"CDZ":[{"permissionName":null,"permissionId":"CDZ","permissionPath":null,"plate":"CDZ","permissionParent":null,"permiss ionId":"CDZ-invoice-abolish","perm issionId":"CDZ-peccancy"},{"permissionName":null,"permissionId":"CDZ-addPush","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-advert","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-advert-A","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-advert-M","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-BillingStatements","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-branchChart","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-brandManagement","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-brandManagement-A","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-business","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-car","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-carList","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-carList-A","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-carList-Disable","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-carList-M","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-carList-refresh","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-companyManagement","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-companyManagement-A","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-companyManagement-D","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-companyManagement-M","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-dataManagement","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-depositManagement","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-depositManagement-Export","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-depositManagement-Surrender","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-discountList","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-discountList-A","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-discountList-D","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-discountList-M","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-GPStrack","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-invoice","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permiss ionId":"CDZ-invoice-abolish","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-invoice-drawABill","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-invoice-reject","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-invoiceDetails","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-marketing","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-member","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-memberList","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-memberList-D","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-memberList-Export","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-memberList-Identity","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-memberList-License","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-memberList-M","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-memberList-Order","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-nationalData","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-nationalData-export","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-netInformation","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-netInformation-A","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-netInformation-chart","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-netInformation-D","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-netInformation-M","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-netInformation-refresh","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-order","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-orderDetail","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-orderList","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-orderList-cancelOeder","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-orderList-changeCar","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-orderList-changePrice","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-orderList-detail","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-orderList-export","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-orderList-Reading","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-orderList-refund","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-orderList-reorganize","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-orderList-returnBack","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"perm issionId":"CDZ-peccancy","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-peccancy-ModifyAcceptance","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-peccancy-ModifyDeal","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-price","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-price-A","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-price-AdjustElectricityFees","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-price-AdjustLease","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-price-AdjustService","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-price-AdjustServicing","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-price-DeleteLease","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-price-M","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-pushDetails","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-pushManagement","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-storeData","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-storeData-export","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-vehicleManagement","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-vehicleManagement-A","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-yardManagement","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-yardManagement-A","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-yardManagement-D","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-yardManagement-M","permissionPath":null,"plate":"CDZ","permissionParent":null}]}
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
         * userInfo : {"id":"1306710186453374288","realName":"陈明佳专营店","userName":"cmjzyd","avatar":"/avatar2.jpg","status":"0","telephone":"13098765678","email":"1230987@163.com","gender":"0","lastLoginIp":"112.96.194.199","lastLoginTime":"2021-01-07 20:38:03","createTime":null,"loginApp":null,"groupIds":["1"],"companyIds":[],"cityIds":[],"parkIds":["121"],"branchIds":["121"]}
         * CDZ : [{"permissionName":null,"permissionId":"CDZ","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-addPush","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-advert","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-advert-A","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-advert-M","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-BillingStatements","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-branchChart","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-brandManagement","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-brandManagement-A","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-business","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-car","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-carList","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-carList-A","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-carList-Disable","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-carList-M","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-carList-refresh","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-companyManagement","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-companyManagement-A","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-companyManagement-D","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-companyManagement-M","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-dataManagement","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-depositManagement","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-depositManagement-Export","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-depositManagement-Surrender","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-discountList","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-discountList-A","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-discountList-D","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-discountList-M","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-GPStrack","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-invoice","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permiss ionId":"CDZ-invoice-abolish","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-invoice-drawABill","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-invoice-reject","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-invoiceDetails","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-marketing","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-member","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-memberList","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-memberList-D","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-memberList-Export","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-memberList-Identity","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-memberList-License","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-memberList-M","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-memberList-Order","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-nationalData","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-nationalData-export","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-netInformation","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-netInformation-A","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-netInformation-chart","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-netInformation-D","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-netInformation-M","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-netInformation-refresh","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-order","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-orderDetail","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-orderList","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-orderList-cancelOeder","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-orderList-changeCar","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-orderList-changePrice","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-orderList-detail","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-orderList-export","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-orderList-Reading","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-orderList-refund","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-orderList-reorganize","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-orderList-returnBack","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"perm issionId":"CDZ-peccancy","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-peccancy-ModifyAcceptance","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-peccancy-ModifyDeal","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-price","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-price-A","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-price-AdjustElectricityFees","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-price-AdjustLease","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-price-AdjustService","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-price-AdjustServicing","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-price-DeleteLease","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-price-M","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-pushDetails","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-pushManagement","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-storeData","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-storeData-export","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-vehicleManagement","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-vehicleManagement-A","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-yardManagement","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-yardManagement-A","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-yardManagement-D","permissionPath":null,"plate":"CDZ","permissionParent":null},{"permissionName":null,"permissionId":"CDZ-yardManagement-M","permissionPath":null,"plate":"CDZ","permissionParent":null}]
         */

        private UserInfoBean userInfo;
        private List<CDZBean> CDZ;

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public List<CDZBean> getCDZ() {
            return CDZ;
        }

        public void setCDZ(List<CDZBean> CDZ) {
            this.CDZ = CDZ;
        }

        public static class UserInfoBean  implements Serializable{
            /**
             * id : 1306710186453374288
             * realName : 陈明佳专营店
             * userName : cmjzyd
             * avatar : /avatar2.jpg
             * status : 0
             * telephone : 13098765678
             * email : 1230987@163.com
             * gender : 0
             * lastLoginIp : 112.96.194.199
             * lastLoginTime : 2021-01-07 20:38:03
             * createTime : null
             * loginApp : null
             * groupIds : ["1"]
             * companyIds : []
             * cityIds : []
             * parkIds : ["121"]
             * branchIds : ["121"]
             */

            private String id;
            private String realName;
            private String userName;
            private String avatar;
            private String status;
            private String telephone;
            private String email;
            private String gender;
            private String lastLoginIp;
            private String lastLoginTime;
            private Object createTime;
            private Object loginApp;
            private List<String> groupIds;
            private List<?> companyIds;
            private List<?> cityIds;
            private List<String> parkIds;
            private List<String> branchIds;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getRealName() {
                return realName;
            }

            public void setRealName(String realName) {
                this.realName = realName;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getLastLoginIp() {
                return lastLoginIp;
            }

            public void setLastLoginIp(String lastLoginIp) {
                this.lastLoginIp = lastLoginIp;
            }

            public String getLastLoginTime() {
                return lastLoginTime;
            }

            public void setLastLoginTime(String lastLoginTime) {
                this.lastLoginTime = lastLoginTime;
            }

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }

            public Object getLoginApp() {
                return loginApp;
            }

            public void setLoginApp(Object loginApp) {
                this.loginApp = loginApp;
            }

            public List<String> getGroupIds() {
                return groupIds;
            }

            public void setGroupIds(List<String> groupIds) {
                this.groupIds = groupIds;
            }

            public List<?> getCompanyIds() {
                return companyIds;
            }

            public void setCompanyIds(List<?> companyIds) {
                this.companyIds = companyIds;
            }

            public List<?> getCityIds() {
                return cityIds;
            }

            public void setCityIds(List<?> cityIds) {
                this.cityIds = cityIds;
            }

            public List<String> getParkIds() {
                return parkIds;
            }

            public void setParkIds(List<String> parkIds) {
                this.parkIds = parkIds;
            }

            public List<String> getBranchIds() {
                return branchIds;
            }

            public void setBranchIds(List<String> branchIds) {
                this.branchIds = branchIds;
            }
        }

        public static class CDZBean  implements Serializable{
            /**
             * permissionName : null
             * permissionId : CDZ
             * permissionPath : null
             * plate : CDZ
             * permissionParent : null
             * permiss ionId : CDZ-invoice-abolish
             * perm issionId : CDZ-peccancy
             */

            private Object permissionName;
            private String permissionId;
            private Object permissionPath;
            private String plate;
            private Object permissionParent;
            @SerializedName("permiss ionId")
            private String _$PermissIonId315; // FIXME check this code
            @SerializedName("perm issionId")
            private String _$PermIssionId310; // FIXME check this code

            public Object getPermissionName() {
                return permissionName;
            }

            public void setPermissionName(Object permissionName) {
                this.permissionName = permissionName;
            }

            public String getPermissionId() {
                return permissionId;
            }

            public void setPermissionId(String permissionId) {
                this.permissionId = permissionId;
            }

            public Object getPermissionPath() {
                return permissionPath;
            }

            public void setPermissionPath(Object permissionPath) {
                this.permissionPath = permissionPath;
            }

            public String getPlate() {
                return plate;
            }

            public void setPlate(String plate) {
                this.plate = plate;
            }

            public Object getPermissionParent() {
                return permissionParent;
            }

            public void setPermissionParent(Object permissionParent) {
                this.permissionParent = permissionParent;
            }

            public String get_$PermissIonId315() {
                return _$PermissIonId315;
            }

            public void set_$PermissIonId315(String _$PermissIonId315) {
                this._$PermissIonId315 = _$PermissIonId315;
            }

            public String get_$PermIssionId310() {
                return _$PermIssionId310;
            }

            public void set_$PermIssionId310(String _$PermIssionId310) {
                this._$PermIssionId310 = _$PermIssionId310;
            }
        }
    }


    /**
     * data : {"userInfo":{"id":"1322077033655742464","account":"17573052878","mobile":"17573052878","spareMobile":"","email":"","imgPath":"","userName":"用户2878","realName":"徐超","hometown":"","industry":"","signature":"","hobby":"","age":"0","status":"0","gender":"0","memberType":"0","userType":"0","identityStatus":"1","driverStatus":"1","isCustomerProtocol":"0","isSecretProtocol":"0","isAllowOrder":"0","balance":"0.00","depositType":"1","deposit":"0.01","couponsNum":null,"isProtection":"0","isStaff":"0","lastLoginTime":"2020-12-24 16:18:41","createTime":"2020-10-30 15:25:02","driver":{"customerId":"1322077033655742464","driverPageUrl":"https://dbc-kf.lanyou-mobility.com/lycxbms/pic/driver/1322077033655742464-driverPic-1604492386847.jpg","driverSubPageUrl":"https://dbc-kf.lanyou-mobility.com/lycxbms/pic/driver/1322077033655742464-backDriverPic-1604492386847.jpg","realName":"徐超","driverNo":"519464349649464","status":null,"rejectCause":"","drivingModel":null,"receiveDate":null,"termOfValidity":null,"fileNumber":null},"identity":{"customerId":"1322077033655742464","identityNo":"43062119990928841x","identityUrl":"https://dbc-kf.lanyou-mobility.com/lycxbms/pic/identity/1322077033655742464-identityPic-1604492366440.jpg","identityBackUrl":"https://dbc-kf.lanyou-mobility.com/lycxbms/pic/notFound/404.jpeg","realName":"徐超","status":null,"rejectCause":""},"staff":null,"customerLoginInfo":{"customerId":"1322077033655742464","platForm":"android","deviceId":"2a92e1b9fb3d4febb2a156c038fba076","pushId":"1104a89792118bcab80","appVersion":"6.4.0","systemNumber":"android9","phoneName":"Xiaomi MI 9","createTime":"2020-12-24 16:18:41","cityName":"广州市","source":0,"wxToken":"","qqOpenid":"","cityCode":"020","adcode":"440114","lon":"113.171733","lat":"23.36786","waSignupData":"","waLoginData":""},"customerVehicleStatus":null}}
     */


}
