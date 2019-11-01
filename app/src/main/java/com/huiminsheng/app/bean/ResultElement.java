package com.huiminsheng.app.bean;

public class ResultElement {

    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ResultElement{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}