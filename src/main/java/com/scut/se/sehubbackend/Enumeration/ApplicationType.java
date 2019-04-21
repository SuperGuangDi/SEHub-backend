package com.scut.se.sehubbackend.Enumeration;

public enum ApplicationType {
    Event("activity"),//活动申请
    Host("presenter"),//主持人申请
    Etiquette("etiquette"),//礼仪申请
    NewMedia("newmedia"),//新媒体申请
    Reporter("reporter"),//记者团申请
    Publicity("publicity"),//海报申请
    Material("material"),//物资申请
    Ticket("ticket"); //讲座票申请

    private String type;
    private ApplicationType(String type){ this.type = type; }

    public String getType() {
        return this.type;
    }
}
