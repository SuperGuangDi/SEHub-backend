package com.scut.se.sehubbackend.Domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserAuthorityRecord implements Serializable {

    private static final Long serialVersionUID=4L;

    public static Set<GrantedAuthority> toGrantedAuthorities(Set<UserAuthorityRecord> userAuthorityRecords){
        Set<GrantedAuthority> grantedAuthorities=new HashSet<>();
        for (UserAuthorityRecord userAuthorityRecord:userAuthorityRecords){
            grantedAuthorities.add(new SimpleGrantedAuthority(userAuthorityRecord.getAuthority()));
        }
        return grantedAuthorities;
    }
    public static GrantedAuthority toGrantedAuthority(UserAuthorityRecord userAuthorityRecord){
        return new SimpleGrantedAuthority(userAuthorityRecord.getAuthority());
    }
    public static Set<UserAuthorityRecord> toUserAuthorityRecords(UserAuthentication owner,Set<GrantedAuthority> grantedAuthorities){
        Set<UserAuthorityRecord> userAuthorityRecords=new HashSet<>();
        for (GrantedAuthority grantedAuthority:grantedAuthorities){
            userAuthorityRecords.add(UserAuthorityRecord.builder().owner(owner).authority(grantedAuthority.getAuthority()).build());
        }
        return userAuthorityRecords;
    }
    public static UserAuthorityRecord toUserAuthorityRecord(UserAuthentication owner,GrantedAuthority grantedAuthority){
        return UserAuthorityRecord.builder().owner(owner).authority(grantedAuthority.getAuthority()).build();
    }

    @Id
    @JsonIgnore
    @ToString.Exclude
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(optional = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    UserAuthentication owner;

    @NotNull
    String authority;

}
