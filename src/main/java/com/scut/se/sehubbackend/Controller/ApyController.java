package com.scut.se.sehubbackend.Controller;

import com.scut.se.sehubbackend.Domain.user.UserAuthentication;
import com.scut.se.sehubbackend.Enumeration.SeStatus;
import com.scut.se.sehubbackend.Others.ReqApplicationForm;
import com.scut.se.sehubbackend.Others.Response;
import com.scut.se.sehubbackend.Service.ApyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/apply")
public class ApyController {

    @Autowired private ApyService apyService;

    @RequestMapping("/submit")
    public Response SubmitApplication(@RequestBody ReqApplicationForm form){
        try {
            // 获取本人
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserAuthentication user = (UserAuthentication) authentication.getPrincipal();
            return new Response(apyService.SubmitApplication(user, form));
        }catch (NullPointerException e){
            return new Response(SeStatus.NotLogin);
        }
    }
}
