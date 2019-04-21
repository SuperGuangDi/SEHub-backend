package com.scut.se.sehubbackend.Controller;

import com.scut.se.sehubbackend.Enumeration.SeStatus;
import com.scut.se.sehubbackend.Others.ReqApplicationForm;
import com.scut.se.sehubbackend.Others.Response;
import com.scut.se.sehubbackend.Service.ApyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/apply")
public class ApyController {

    @Autowired private ApyService apyService;

    @RequestMapping("/submit")
    public Response SubmitApplication(@RequestBody ReqApplicationForm form){
        return new Response(SeStatus.Success);
    }
}
