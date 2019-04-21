package com.scut.se.sehubbackend.Domain.application;

import com.scut.se.sehubbackend.Domain.user.UserAuthentication;
import com.scut.se.sehubbackend.Enumeration.ApplicationType;
import com.scut.se.sehubbackend.Enumeration.ApprovalStatus;
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
public class ApplicationInternalInformation implements Serializable {

    private static final Long serialVersionUID=3L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Enumerated(value = EnumType.STRING)
    @NotNull
    ApprovalStatus status;

    @NotNull
    Date submission;

    @Column(name = "`check`")
    Date check;

    @NotNull
    Date lastModifiedTime;

    @NotNull
    @OneToOne
    UserAuthentication lastModifier;

    @ManyToOne
    @NotNull
    UserAuthentication sponsor;

}
