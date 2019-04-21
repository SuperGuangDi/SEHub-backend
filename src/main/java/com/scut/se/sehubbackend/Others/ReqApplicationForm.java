package com.scut.se.sehubbackend.Others;

import com.scut.se.sehubbackend.Enumeration.ApplicationType;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ReqApplicationForm {
    private ApplicationType type;

    private Date acttime;
    private String actname;
    private String actaddr;
    private String actaim;
    private String actback;
    private String actintro;
    private String hostunit;
    private String organizer;
    private String target;

    private Date rehtime;   // 彩排时间
    private Date deadline;  // 截止时间, 新媒体、记者团、宣传物资申请
    private Date lendtime;  // 物资借用时间
    private Date backtime;  // 物资归还时间

    private int number;     // 主持人或者礼仪队的人数

    private String others;
    private String work;    // 礼仪工作、新媒体项目、记者团项目

    private List<String> needs; // 宣传、秘书物资列表

    private String content; // 宣传 文字的要求

}
