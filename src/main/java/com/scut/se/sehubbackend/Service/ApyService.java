package com.scut.se.sehubbackend.Service;

import com.scut.se.sehubbackend.Domain.user.UserAuthentication;
import com.scut.se.sehubbackend.Enumeration.ApplicationType;
import com.scut.se.sehubbackend.Enumeration.Department;
import com.scut.se.sehubbackend.Enumeration.DynamicDetail;
import com.scut.se.sehubbackend.Repository.user.OwnerOnly;
import com.scut.se.sehubbackend.Repository.user.UserAuthorityRecordRepository;
import com.scut.se.sehubbackend.Security.Authorization.interfaces.AuthorityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ApyService {

    @Autowired static AuthorityMapper authorityMapper;
    @Autowired UserAuthorityRecordRepository authorityRecordRepository;
    private static Map<ApplicationType, GrantedAuthority> applicationType2GrantedAuthority;


    static {
        applicationType2GrantedAuthority=new HashMap<>();
        applicationType2GrantedAuthority.put(ApplicationType.Etiquette,authorityMapper.mapDynamic(Department.Relation,null));
        applicationType2GrantedAuthority.put(ApplicationType.Event,authorityMapper.mapDynamic(Department.StandingCommittee,null));
        applicationType2GrantedAuthority.put(ApplicationType.Host,authorityMapper.mapDynamic(Department.Literary,null));
        applicationType2GrantedAuthority.put(ApplicationType.Material,authorityMapper.mapDynamic(Department.Secretary,null));
        applicationType2GrantedAuthority.put(ApplicationType.NewMedia,authorityMapper.mapDynamic(Department.Media, DynamicDetail.NewMediaApplication));
        applicationType2GrantedAuthority.put(ApplicationType.Publicity,authorityMapper.mapDynamic(Department.Propaganda,null));
        applicationType2GrantedAuthority.put(ApplicationType.Reporter,authorityMapper.mapDynamic(Department.Media,DynamicDetail.ReporterApplication));
    }

    public List<UserAuthentication> getAcceptors(ApplicationType applicationType){
        List<OwnerOnly> owners=authorityRecordRepository
                .findAllByAuthority(
                        applicationType2GrantedAuthority.get(applicationType).getAuthority()
                );
        List<UserAuthentication> result=new ArrayList<>();
        for (OwnerOnly ownerOnly:owners)
            result.add(ownerOnly.getOwner());
        return result;
    }
}
