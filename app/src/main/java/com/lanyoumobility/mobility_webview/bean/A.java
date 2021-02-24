package com.lanyoumobility.mobility_webview.bean;

import java.util.List;

public class A {

    /**
     * images : [{"id":"6879e28d-e71a-68ac-ee28-6feb66a227e6","projectName":"ic_launcher","imageName":"ic_launcher","imageNumber":null,"designer":null,"progress":null,"manage":null,"date":null,"partyA":null,"description":null,"markerData":[{"id":"0e06dd2e-613a-81c4-aed6-eaf820085a90","name":"10001","imageId":1,"imageWidth":80,"imageHeight":80,"coordinate":{"x":14.4725914605405,"y":13.379797702229986},"path":null,"scale":{"x":0.1474810273727235,"y":0.14748102737272328},"rotation":0,"visible":true}],"base64":"data:image/png;base64,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"}]
     * defaultImage : 6879e28d-e71a-68ac-ee28-6feb66a227e6
     * icons : [{"id":1,"name":"10001"},{"id":2,"name":"10002"},{"id":3,"name":"10003"},{"id":4,"name":"10004"},{"id":5,"name":"10005"},{"id":6,"name":"10006"},{"id":7,"name":"10007"},{"id":8,"name":"10008"},{"id":9,"name":"10009"},{"id":10,"name":"10010"},{"id":11,"name":"10011"},{"id":12,"name":"10012"},{"id":13,"name":"10013"},{"id":14,"name":"10014"},{"id":15,"name":"10015"},{"id":16,"name":"10016"},{"id":17,"name":"10017"},{"id":18,"name":"10018"}]
     */

    private String defaultImage;
    private List<ImagesBean> images;
    private List<IconsBean> icons;

    public String getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(String defaultImage) {
        this.defaultImage = defaultImage;
    }

    public List<ImagesBean> getImages() {
        return images;
    }

    public void setImages(List<ImagesBean> images) {
        this.images = images;
    }

    public List<IconsBean> getIcons() {
        return icons;
    }

    public void setIcons(List<IconsBean> icons) {
        this.icons = icons;
    }

    public static class ImagesBean {
        /**
         * id : 6879e28d-e71a-68ac-ee28-6feb66a227e6
         * projectName : ic_launcher
         * imageName : ic_launcher
         * imageNumber : null
         * designer : null
         * progress : null
         * manage : null
         * date : null
         * partyA : null
         * description : null
         * markerData : [{"id":"0e06dd2e-613a-81c4-aed6-eaf820085a90","name":"10001","imageId":1,"imageWidth":80,"imageHeight":80,"coordinate":{"x":14.4725914605405,"y":13.379797702229986},"path":null,"scale":{"x":0.1474810273727235,"y":0.14748102737272328},"rotation":0,"visible":true}]
         * base64 : data:image/png;base64,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
         */

        private String id;
        private String projectName;
        private String imageName;
        private Object imageNumber;
        private Object designer;
        private Object progress;
        private Object manage;
        private Object date;
        private Object partyA;
        private Object description;
        private String base64;
        private List<MarkerDataBean> markerData;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

        public String getImageName() {
            return imageName;
        }

        public void setImageName(String imageName) {
            this.imageName = imageName;
        }

        public Object getImageNumber() {
            return imageNumber;
        }

        public void setImageNumber(Object imageNumber) {
            this.imageNumber = imageNumber;
        }

        public Object getDesigner() {
            return designer;
        }

        public void setDesigner(Object designer) {
            this.designer = designer;
        }

        public Object getProgress() {
            return progress;
        }

        public void setProgress(Object progress) {
            this.progress = progress;
        }

        public Object getManage() {
            return manage;
        }

        public void setManage(Object manage) {
            this.manage = manage;
        }

        public Object getDate() {
            return date;
        }

        public void setDate(Object date) {
            this.date = date;
        }

        public Object getPartyA() {
            return partyA;
        }

        public void setPartyA(Object partyA) {
            this.partyA = partyA;
        }

        public Object getDescription() {
            return description;
        }

        public void setDescription(Object description) {
            this.description = description;
        }

        public String getBase64() {
            return base64;
        }

        public void setBase64(String base64) {
            this.base64 = base64;
        }

        public List<MarkerDataBean> getMarkerData() {
            return markerData;
        }

        public void setMarkerData(List<MarkerDataBean> markerData) {
            this.markerData = markerData;
        }

        public static class MarkerDataBean {
            /**
             * id : 0e06dd2e-613a-81c4-aed6-eaf820085a90
             * name : 10001
             * imageId : 1
             * imageWidth : 80
             * imageHeight : 80
             * coordinate : {"x":14.4725914605405,"y":13.379797702229986}
             * path : null
             * scale : {"x":0.1474810273727235,"y":0.14748102737272328}
             * rotation : 0
             * visible : true
             */

            private String id;
            private String name;
            private int imageId;
            private int imageWidth;
            private int imageHeight;
            private CoordinateBean coordinate;
            private Object path;
            private ScaleBean scale;
            private int rotation;
            private boolean visible;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getImageId() {
                return imageId;
            }

            public void setImageId(int imageId) {
                this.imageId = imageId;
            }

            public int getImageWidth() {
                return imageWidth;
            }

            public void setImageWidth(int imageWidth) {
                this.imageWidth = imageWidth;
            }

            public int getImageHeight() {
                return imageHeight;
            }

            public void setImageHeight(int imageHeight) {
                this.imageHeight = imageHeight;
            }

            public CoordinateBean getCoordinate() {
                return coordinate;
            }

            public void setCoordinate(CoordinateBean coordinate) {
                this.coordinate = coordinate;
            }

            public Object getPath() {
                return path;
            }

            public void setPath(Object path) {
                this.path = path;
            }

            public ScaleBean getScale() {
                return scale;
            }

            public void setScale(ScaleBean scale) {
                this.scale = scale;
            }

            public int getRotation() {
                return rotation;
            }

            public void setRotation(int rotation) {
                this.rotation = rotation;
            }

            public boolean isVisible() {
                return visible;
            }

            public void setVisible(boolean visible) {
                this.visible = visible;
            }

            public static class CoordinateBean {
                /**
                 * x : 14.4725914605405
                 * y : 13.379797702229986
                 */

                private double x;
                private double y;

                public double getX() {
                    return x;
                }

                public void setX(double x) {
                    this.x = x;
                }

                public double getY() {
                    return y;
                }

                public void setY(double y) {
                    this.y = y;
                }
            }

            public static class ScaleBean {
                /**
                 * x : 0.1474810273727235
                 * y : 0.14748102737272328
                 */

                private double x;
                private double y;

                public double getX() {
                    return x;
                }

                public void setX(double x) {
                    this.x = x;
                }

                public double getY() {
                    return y;
                }

                public void setY(double y) {
                    this.y = y;
                }
            }
        }
    }

    public static class IconsBean {
        /**
         * id : 1
         * name : 10001
         */

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
