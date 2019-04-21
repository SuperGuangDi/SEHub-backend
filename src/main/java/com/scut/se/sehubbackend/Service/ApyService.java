package com.scut.se.sehubbackend.Service;

import com.scut.se.sehubbackend.Domain.Notice;
import com.scut.se.sehubbackend.Domain.application.ApplicationForm;
import com.scut.se.sehubbackend.Domain.application.ApplicationInternalInformation;
import com.scut.se.sehubbackend.Domain.application.ApplicationJoinInformation;
import com.scut.se.sehubbackend.Domain.user.UserAuthentication;
import com.scut.se.sehubbackend.Enumeration.*;
import com.scut.se.sehubbackend.Others.ReqApplicationForm;
import com.scut.se.sehubbackend.Repository.NoticeRepository;
import com.scut.se.sehubbackend.Repository.application.ApplicationFormRepository;
import com.scut.se.sehubbackend.Repository.application.ApplicationInternalInformationRepository;
import com.scut.se.sehubbackend.Repository.application.ApplicationJoinInformationRepository;
import com.scut.se.sehubbackend.Repository.user.OwnerOnly;
import com.scut.se.sehubbackend.Repository.user.UserAuthorityRecordRepository;
import com.scut.se.sehubbackend.Security.Authorization.interfaces.AuthorityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ApyService {

    @Autowired private static AuthorityMapper authorityMapper;
    @Autowired private UserAuthorityRecordRepository authorityRecordRepository;
    @Autowired private ApplicationInternalInformationRepository internalInformationRepository;
    @Autowired private ApplicationJoinInformationRepository joinInformationRepository;
    @Autowired private ApplicationFormRepository formRepository;
    @Autowired private NoticeRepository noticeRepository;
    private static Map<ApplicationType, GrantedAuthority> applicationType2GrantedAuthority;

    // getAcceptors使用的
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

    // 获取所有有权限的接收者
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

    // 提交申请表
    public SeStatus SubmitApplication(UserAuthentication applicant, ReqApplicationForm form){
        // 构造共有信息
        ApplicationInternalInformation internalInformation = ApplicationInternalInformation.builder()
                .status(ApprovalStatus.Submit)
                .lastModifiedTime(new Date())
                .sponsor(applicant)
                .submission(new Date())
                .lastModifier(applicant)
                .build();
        internalInformationRepository.save(internalInformation);

        ApplicationJoinInformation joinInformation;
        switch (form.getType()){
            case Etiquette: {
                joinInformation = ApplicationJoinInformation.builder()
                        .eventName(form.getActname())
                        .eventSite(form.getActaddr())
                        .eventTime(form.getActtime())
                        .amount(form.getNumber())
                        .task(form.getWork())
                        .remarks(form.getOthers())
                        .build();
                break;
            }
            // To do
//            case Event: {
//                break;
//            }
            default:
                return SeStatus.InvalidApyType;
        }

        // 保存表单
        joinInformation.setType(form.getType());
        joinInformationRepository.save(joinInformation);

        ApplicationForm applicationForm = ApplicationForm.builder()
                .internalInformation(internalInformation).joinInformation(joinInformation).build();
        formRepository.save(applicationForm);


        // 添加notices
        ArrayList<Notice> notices = new ArrayList<>();
        for(UserAuthentication accptor: getAcceptors(form.getType())){
            Notice notice = Notice.builder()
                    .type(NoticeType.ApplicationSubmit)
                    .sponsor(applicant)
                    .acceptor(accptor)
                    .form(applicationForm)
                    .initiateTime(new Date())
                    .build();
            notices.add(notice);
        }
        noticeRepository.saveAll(notices);


        return SeStatus.Success;
    }
}
