package com.scut.se.sehubbackend.Domain;

import com.scut.se.sehubbackend.Domain.application.ApplicationForm;
import com.scut.se.sehubbackend.Domain.user.UserAuthentication;
import com.scut.se.sehubbackend.Enumeration.NoticeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notice implements Serializable {

    private static final Long serialVersionUID=8L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    NoticeType type;

    @ManyToOne
    UserAuthentication sponsor;

    @ManyToOne
    @NotNull
    UserAuthentication acceptor;

    ApplicationForm form;

    @NotNull
    Date initiateTime;

    @Lob
    String remarks;
}
