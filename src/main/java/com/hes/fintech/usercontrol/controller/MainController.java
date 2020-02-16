package com.hes.fintech.usercontrol.controller;

import com.hes.fintech.usercontrol.entity.UserPrincipal;
import com.hes.fintech.usercontrol.service.UserAccountService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@AllArgsConstructor
public class MainController {

    private final UserAccountService userAccountService;

    @GetMapping("/user")
    public String user(@AuthenticationPrincipal UserPrincipal userPrincipal, Map<String,Object> model){
        model.put("users",userAccountService.getList());
        model.put("auth",userPrincipal.getUserAccount().getRole().name());
        return "user";
    }

    @GetMapping("/user/{userId}")
    public String userId(@PathVariable Long userId,
                         @AuthenticationPrincipal UserPrincipal userPrincipal,
                         Map<String,Object> model){
        model.put("auth",userPrincipal.getUserAccount().getRole().name());
        model.put("user",userAccountService.findById(userId));
        return "userDetail";
    }

    @PostMapping("/user/username-filter")
    public String filterUser(@AuthenticationPrincipal UserPrincipal userPrincipal,
                             @RequestParam String usernameFilter, Map<String,Object> model){
        if (usernameFilter == ""){
            model.put("users",userAccountService.getList());
        } else {
            model.put("users", userAccountService.getListByUsername(usernameFilter));
        }
        model.put("auth",userPrincipal.getUserAccount().getRole().name());
        return "user";
    }
}
