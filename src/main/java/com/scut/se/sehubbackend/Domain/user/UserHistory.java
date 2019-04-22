package com.scut.se.sehubbackend.Domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.scut.se.sehubbackend.Enumeration.Department;
import com.scut.se.sehubbackend.Enumeration.Position;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserHistory implements Serializable,Comparable {

    private static final Long serialVersionUID=5L;

    @Id
    @JsonIgnore
    @ToString.Exclude
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    Long year;

    @Enumerated(EnumType.STRING)
    Department department;

    @NotNull
    @Enumerated(EnumType.STRING)
    Position position;

    @ManyToOne(optional = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    UserAuthentication userAuthentication;

    @Override
    public int compareTo(Object o) {
        return year.compareTo(((UserHistory)o).getYear());
    }
}
