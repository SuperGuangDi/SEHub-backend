package com.scut.se.sehubbackend.Enumeration;


public enum SeStatus {

    NotLogin(301, "not login"),
    Success(0, "operate successfully"),
    InvalidApyType(400, "invalid application type");

    private int code;
    private String msg;

    private SeStatus(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode(){ return this.code; }
    public String getMsg() { return this.msg; }
}

