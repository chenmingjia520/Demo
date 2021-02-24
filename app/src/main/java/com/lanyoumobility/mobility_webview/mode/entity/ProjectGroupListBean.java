package com.lanyoumobility.mobility_webview.mode.entity;

import com.google.gson.annotations.SerializedName;
import com.lanyoumobility.mobility_webview.mode.base.BaseBean;

import java.util.List;

public class ProjectGroupListBean extends BaseBean {
    /**
     * result : [{"id":17611,"createUserId":"1357598592361095168","projectGroupId":"1357598928236765184","projectGroupName":"测试项目组","createDate":"2021-02-05 15:56:22","modifyDate":"2021-02-05 15:56:22","projectGroupIcons":[{"id":17631,"projectGroupIconId":"1357598929625079808","projectGroupId":"1357598928236765184","createUserId":"1357598592361095168","createDate":"2021-02-05 10:54:18","iconName":"icon_001","modifyDate":"2021-02-05 10:54:18","iconPath":"http://127.0.0.1:8080/1357255280802402304/1612493657840.png","iconRemark":null,"iconType":0},{"id":17632,"projectGroupIconId":"1357598930895953920","projectGroupId":"1357598928236765184","createUserId":"1357598592361095168","createDate":"2021-02-05 10:54:33","iconName":"icon_002","modifyDate":"2021-02-05 10:54:33","iconPath":"http://127.0.0.1:8080/1357255280802402304/1612493673142.png","iconRemark":null,"iconType":0},{"id":17633,"projectGroupIconId":"1357598932158439424","projectGroupId":"1357598928236765184","createUserId":"1357598592361095168","createDate":"2021-02-05 10:54:41","iconName":"icon_003","modifyDate":"2021-02-05 10:54:41","iconPath":"http://127.0.0.1:8080/1357255280802402304/1612493681419.png","iconRemark":null,"iconType":0},{"id":17634,"projectGroupIconId":"1357598933441896448","projectGroupId":"1357598928236765184","createUserId":"1357598592361095168","createDate":"2021-02-05 10:54:51","iconName":"icon_004","modifyDate":"2021-02-05 10:54:51","iconPath":"http://127.0.0.1:8080/1357255280802402304/1612493690827.png","iconRemark":null,"iconType":0},{"id":17635,"projectGroupIconId":"1357598934721159168","projectGroupId":"1357598928236765184","createUserId":"1357598592361095168","createDate":"2021-02-05 10:54:58","iconName":"icon_005","modifyDate":"2021-02-05 10:54:58","iconPath":"http://127.0.0.1:8080/1357255280802402304/1612493697901.png","iconRemark":null,"iconType":0},{"id":17636,"projectGroupIconId":"1357598935971061760","projectGroupId":"1357598928236765184","createUserId":"1357598592361095168","createDate":"2021-02-05 10:55:09","iconName":"icon_006","modifyDate":"2021-02-05 10:55:09","iconPath":"http://127.0.0.1:8080/1357255280802402304/1612493709058.png","iconRemark":null,"iconType":0},{"id":17637,"projectGroupIconId":"1357598937229352960","projectGroupId":"1357598928236765184","createUserId":"1357598592361095168","createDate":"2021-02-05 12:42:21","iconName":"测试数据","modifyDate":"2021-02-05 12:42:21","iconPath":"http://127.0.0.1:8080/1357255280802402304/1612500140960.png","iconRemark":null,"iconType":0}]},{"id":17612,"createUserId":"1357598592361095168","projectGroupId":"1357602576853450752","projectGroupName":"测试123","createDate":"2021-02-05 16:10:52","modifyDate":"2021-02-05 16:10:52","projectGroupIcons":[{"id":17638,"projectGroupIconId":"1357602578237571072","projectGroupId":"1357602576853450752","createUserId":"1357598592361095168","createDate":"2021-02-05 10:54:18","iconName":"icon_001","modifyDate":"2021-02-05 10:54:18","iconPath":"http://127.0.0.1:8080/1357255280802402304/1612493657840.png","iconRemark":null,"iconType":0},{"id":17639,"projectGroupIconId":"1357602579474890752","projectGroupId":"1357602576853450752","createUserId":"1357598592361095168","createDate":"2021-02-05 10:54:33","iconName":"icon_002","modifyDate":"2021-02-05 10:54:33","iconPath":"http://127.0.0.1:8080/1357255280802402304/1612493673142.png","iconRemark":null,"iconType":0},{"id":17640,"projectGroupIconId":"1357602580699627520","projectGroupId":"1357602576853450752","createUserId":"1357598592361095168","createDate":"2021-02-05 10:54:41","iconName":"icon_003","modifyDate":"2021-02-05 10:54:41","iconPath":"http://127.0.0.1:8080/1357255280802402304/1612493681419.png","iconRemark":null,"iconType":0},{"id":17641,"projectGroupIconId":"1357602581932752896","projectGroupId":"1357602576853450752","createUserId":"1357598592361095168","createDate":"2021-02-05 10:54:51","iconName":"icon_004","modifyDate":"2021-02-05 10:54:51","iconPath":"http://127.0.0.1:8080/1357255280802402304/1612493690827.png","iconRemark":null,"iconType":0},{"id":17642,"projectGroupIconId":"1357602583140712448","projectGroupId":"1357602576853450752","createUserId":"1357598592361095168","createDate":"2021-02-05 10:54:58","iconName":"icon_005","modifyDate":"2021-02-05 10:54:58","iconPath":"http://127.0.0.1:8080/1357255280802402304/1612493697901.png","iconRemark":null,"iconType":0},{"id":17643,"projectGroupIconId":"1357602584344477696","projectGroupId":"1357602576853450752","createUserId":"1357598592361095168","createDate":"2021-02-05 10:55:09","iconName":"icon_006","modifyDate":"2021-02-05 10:55:09","iconPath":"http://127.0.0.1:8080/1357255280802402304/1612493709058.png","iconRemark":null,"iconType":0},{"id":17644,"projectGroupIconId":"1357602585548242944","projectGroupId":"1357602576853450752","createUserId":"1357598592361095168","createDate":"2021-02-05 12:42:21","iconName":"测试数据","modifyDate":"2021-02-05 12:42:21","iconPath":"http://127.0.0.1:8080/1357255280802402304/1612500140960.png","iconRemark":null,"iconType":0}]}]
     * pagination : null
     */
    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 17611
         * createUserId : 1357598592361095168
         * projectGroupId : 1357598928236765184
         * projectGroupName : 测试项目组
         * createDate : 2021-02-05 15:56:22
         * modifyDate : 2021-02-05 15:56:22
         * projectGroupIcons : [{"id":17631,"projectGroupIconId":"1357598929625079808","projectGroupId":"1357598928236765184","createUserId":"1357598592361095168","createDate":"2021-02-05 10:54:18","iconName":"icon_001","modifyDate":"2021-02-05 10:54:18","iconPath":"http://127.0.0.1:8080/1357255280802402304/1612493657840.png","iconRemark":null,"iconType":0},{"id":17632,"projectGroupIconId":"1357598930895953920","projectGroupId":"1357598928236765184","createUserId":"1357598592361095168","createDate":"2021-02-05 10:54:33","iconName":"icon_002","modifyDate":"2021-02-05 10:54:33","iconPath":"http://127.0.0.1:8080/1357255280802402304/1612493673142.png","iconRemark":null,"iconType":0},{"id":17633,"projectGroupIconId":"1357598932158439424","projectGroupId":"1357598928236765184","createUserId":"1357598592361095168","createDate":"2021-02-05 10:54:41","iconName":"icon_003","modifyDate":"2021-02-05 10:54:41","iconPath":"http://127.0.0.1:8080/1357255280802402304/1612493681419.png","iconRemark":null,"iconType":0},{"id":17634,"projectGroupIconId":"1357598933441896448","projectGroupId":"1357598928236765184","createUserId":"1357598592361095168","createDate":"2021-02-05 10:54:51","iconName":"icon_004","modifyDate":"2021-02-05 10:54:51","iconPath":"http://127.0.0.1:8080/1357255280802402304/1612493690827.png","iconRemark":null,"iconType":0},{"id":17635,"projectGroupIconId":"1357598934721159168","projectGroupId":"1357598928236765184","createUserId":"1357598592361095168","createDate":"2021-02-05 10:54:58","iconName":"icon_005","modifyDate":"2021-02-05 10:54:58","iconPath":"http://127.0.0.1:8080/1357255280802402304/1612493697901.png","iconRemark":null,"iconType":0},{"id":17636,"projectGroupIconId":"1357598935971061760","projectGroupId":"1357598928236765184","createUserId":"1357598592361095168","createDate":"2021-02-05 10:55:09","iconName":"icon_006","modifyDate":"2021-02-05 10:55:09","iconPath":"http://127.0.0.1:8080/1357255280802402304/1612493709058.png","iconRemark":null,"iconType":0},{"id":17637,"projectGroupIconId":"1357598937229352960","projectGroupId":"1357598928236765184","createUserId":"1357598592361095168","createDate":"2021-02-05 12:42:21","iconName":"测试数据","modifyDate":"2021-02-05 12:42:21","iconPath":"http://127.0.0.1:8080/1357255280802402304/1612500140960.png","iconRemark":null,"iconType":0}]
         */

        private int id;
        private String createUserId;
        private String projectGroupId;
        private String projectGroupName;
        private String createDate;
        private String modifyDate;
        private List<ProjectGroupIconsBean> projectGroupIcons;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(String createUserId) {
            this.createUserId = createUserId;
        }

        public String getProjectGroupId() {
            return projectGroupId;
        }

        public void setProjectGroupId(String projectGroupId) {
            this.projectGroupId = projectGroupId;
        }

        public String getProjectGroupName() {
            return projectGroupName;
        }

        public void setProjectGroupName(String projectGroupName) {
            this.projectGroupName = projectGroupName;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getModifyDate() {
            return modifyDate;
        }

        public void setModifyDate(String modifyDate) {
            this.modifyDate = modifyDate;
        }

        public List<ProjectGroupIconsBean> getProjectGroupIcons() {
            return projectGroupIcons;
        }

        public void setProjectGroupIcons(List<ProjectGroupIconsBean> projectGroupIcons) {
            this.projectGroupIcons = projectGroupIcons;
        }

        public static class ProjectGroupIconsBean {
            /**
             * id : 17631
             * projectGroupIconId : 1357598929625079808
             * projectGroupId : 1357598928236765184
             * createUserId : 1357598592361095168
             * createDate : 2021-02-05 10:54:18
             * iconName : icon_001
             * modifyDate : 2021-02-05 10:54:18
             * iconPath : http://127.0.0.1:8080/1357255280802402304/1612493657840.png
             * iconRemark : null
             * iconType : 0
             */

            private int id;
            private String projectGroupIconId;
            private String projectGroupId;
            private String createUserId;
            private String createDate;
            private String iconName;
            private String modifyDate;
            private String iconPath;
            private String iconRemark;
            private int iconType;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getProjectGroupIconId() {
                return projectGroupIconId;
            }

            public void setProjectGroupIconId(String projectGroupIconId) {
                this.projectGroupIconId = projectGroupIconId;
            }

            public String getProjectGroupId() {
                return projectGroupId;
            }

            public void setProjectGroupId(String projectGroupId) {
                this.projectGroupId = projectGroupId;
            }

            public String getCreateUserId() {
                return createUserId;
            }

            public void setCreateUserId(String createUserId) {
                this.createUserId = createUserId;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getIconName() {
                return iconName;
            }

            public void setIconName(String iconName) {
                this.iconName = iconName;
            }

            public String getModifyDate() {
                return modifyDate;
            }

            public void setModifyDate(String modifyDate) {
                this.modifyDate = modifyDate;
            }

            public String getIconPath() {
                return iconPath;
            }

            public void setIconPath(String iconPath) {
                this.iconPath = iconPath;
            }

            public String getIconRemark() {
                return iconRemark;
            }

            public void setIconRemark(String iconRemark) {
                this.iconRemark = iconRemark;
            }

            public int getIconType() {
                return iconType;
            }

            public void setIconType(int iconType) {
                this.iconType = iconType;
            }
        }
    }
}
