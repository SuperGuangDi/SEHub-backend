package com.scut.se.sehubbackend.Controller;

import com.scut.se.sehubbackend.Domain.Notice;
import com.scut.se.sehubbackend.Domain.user.UserAuthentication;
import com.scut.se.sehubbackend.Domain.user.UserHistory;
import com.scut.se.sehubbackend.Domain.user.UserInformation;
import com.scut.se.sehubbackend.Enumeration.Department;
import com.scut.se.sehubbackend.Enumeration.NoticeType;
import com.scut.se.sehubbackend.Enumeration.Position;
import com.scut.se.sehubbackend.Repository.NoticeRepository;
import com.scut.se.sehubbackend.Repository.user.UserAuthenticationRepository;
import com.scut.se.sehubbackend.Repository.user.UserHistoryRepository;
import com.scut.se.sehubbackend.Repository.user.UserInformationRepository;
import com.scut.se.sehubbackend.Service.AuthorizeService;
import com.scut.se.sehubbackend.Service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@RequestMapping("/api")
@RestController
public class TestController {

    @Autowired UserAuthenticationRepository userRepository;
    @Autowired UserInformationRepository userInformationRepository;
    @Autowired UserHistoryRepository userHistoryRepository;
    @Autowired NoticeRepository noticeRepository;
    @Autowired NoticeService noticeService;
    @Autowired AuthorizeService authorizeService;

    @RequestMapping("/adduser")
    public void addUser(){
        UserHistory userHistory=UserHistory.builder()
                .year(Long.valueOf(2017)).position(Position.Minister).department(Department.Research)
                .build();
        UserInformation userInformation=UserInformation.builder()
                .name("ptx")
                .build();
        UserAuthentication userAuthentication=UserAuthentication.builder()
                .userInformation(userInformation).userHistory(userHistory).studentNO("201730683314").password("123456")
                .build();
        userHistory.setUserAuthentication(userAuthentication);
        userInformation.setUserAuthentication(userAuthentication);

        userRepository.save(userAuthentication);
    }

    @RequestMapping("/addnotice")
    public void addNotice() {
        Optional<UserAuthentication> userAuthenticationOptional=userRepository.findById("201730683314");
        if(userAuthenticationOptional.isPresent()){
            UserAuthentication userAuthentication=userAuthenticationOptional.get();
            Notice notice=Notice.builder()
                    .type(NoticeType.Application)
                    .initiateTime(Date.valueOf(LocalDate.now()))
                    .principalId(Long.valueOf(1999))
                    .sponsor(userAuthentication)
                    .acceptor(userAuthentication)
                    .remarks("测试用备注")
                    .build();
            noticeRepository.save(notice);
        }
    }

    @RequestMapping("/getnotice")
    public ResponseEntity<ArrayList<Map<String,Object>>> getNotice(){
        return noticeService.getAllFrontNotices();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(){
        return authorizeService.login();
    }
}
