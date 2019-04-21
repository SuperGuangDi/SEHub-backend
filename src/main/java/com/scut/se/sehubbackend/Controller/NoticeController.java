package com.scut.se.sehubbackend.Controller;

import com.scut.se.sehubbackend.Service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/api/notice")
public class NoticeController {

    @Autowired NoticeService noticeService;

    @RequestMapping("/acquire")
    public ResponseEntity<ArrayList<Map<String,Object>>> acquire(){
        return noticeService.getAllFrontNotices();
    }
}
