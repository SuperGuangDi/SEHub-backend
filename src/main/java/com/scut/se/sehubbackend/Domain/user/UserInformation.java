package com.scut.se.sehubbackend.Domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInformation implements Serializable {

    private static final long serialVersionUID=2L;

    @Id
    @JsonIgnore
    @ToString.Exclude
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "`name`")
    @NotNull
    String name;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToOne(optional = false)
    @JsonIgnore
    UserAuthentication userAuthentication;

}
