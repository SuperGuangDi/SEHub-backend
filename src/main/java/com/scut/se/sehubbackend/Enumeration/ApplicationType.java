package com.scut.se.sehubbackend.Enumeration;

public enum ApplicationType {
    Activity("activity"),
    Etiquette("etiquette"),
    Presenter("presenter"), // 主持人
    NewMedia("newmedia"), // 新媒体
    Reporter("reporter"), // 记者团
    Publicity("publicity"), // 宣传
    Material("material"),// 秘书
    Ticket("ticket"); //讲座票

    private String type;
    private ApplicationType(String type){ this.type = type; }

    public String getType() {
        return this.type;
    }
}
