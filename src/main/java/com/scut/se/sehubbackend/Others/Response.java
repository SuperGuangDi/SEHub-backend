package com.scut.se.sehubbackend.Others;


import com.scut.se.sehubbackend.Enumeration.SeStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Response {
    private int code;
    private String msg;
    private Object body;

    public Response(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public Response(SeStatus status){
        this.code = status.getCode();
        this.msg = status.getMsg();
    }

    public Response(SeStatus status, Object object){
        this.code = status.getCode();
        this.msg = status.getMsg();
        this.body = object;
    }

}
