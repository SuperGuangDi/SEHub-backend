package com.scut.se.sehubbackend.Domain.application;

import com.scut.se.sehubbackend.Enumeration.ApplicationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationJoinInformation implements Serializable {

    private static final Long serialVersionUID=11L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    ApplicationType type;

    String eventName;//活动名称

    String eventSite;//活动地点

    Date eventTime;//活动时间

    @Lob
    String eventBackground;//活动背景

    @Lob
    String eventAim;//活动目标

    @Lob
    String eventIntroduction;//活动简介

    String eventHost;//主办方

    String eventOrganization;//主办单位

    String eventTarget;//活动受众

    int amount;//申请人员数量

    Date rehearsalTime;//彩排时间

    @Lob
    String task;//工作

    Date lendTime;//借出时间

    Date returnTime;//返还时间

    @ElementCollection
    List<String> materialList;//物资列表

    Date deadline;//限期

    @Lob
    String content;//宣传文字要求

    @Lob
    String remarks;//备注
}
