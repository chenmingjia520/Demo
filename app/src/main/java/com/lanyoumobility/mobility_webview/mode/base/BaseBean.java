package com.lanyoumobility.mobility_webview.mode.base;

import java.io.Serializable;

public class BaseBean implements Serializable {
    /**
     * code : ok
     * message : 请求成功
     * result : 1357846660755755008
     * pagination : null
     * timestamp : 1612570846277
     */

    private String code;
    private String message;
    private Object pagination;
    private long timestamp;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    public Object getPagination() {
        return pagination;
    }

    public void setPagination(Object pagination) {
        this.pagination = pagination;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
