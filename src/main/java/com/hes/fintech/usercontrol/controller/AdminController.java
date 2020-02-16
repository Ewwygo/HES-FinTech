package com.hes.fintech.usercontrol.controller;

import com.hes.fintech.usercontrol.dto.UserAccountDTO;
import com.hes.fintech.usercontrol.entity.Role;
import com.hes.fintech.usercontrol.entity.Status;
import com.hes.fintech.usercontrol.entity.UserPrincipal;
import com.hes.fintech.usercontrol.exception.SuchUserAlreadyExistException;
import com.hes.fintech.usercontrol.service.AdminService;
import com.hes.fintech.usercontrol.service.UserAccountService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Map;

@Controller
@AllArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final UserAccountService userAccountService;

    @GetMapping("/user/new")
    public String newUser(){
        return "newUser";
    }

    @PostMapping("/user/new")
    public String newUserAdd(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String firstName,
                             @RequestParam String lastName,
                             @RequestParam Role role,
                             @RequestParam Status status,
                             Map<String,Object> model) {
        UserAccountDTO user = UserAccountDTO.builder()
                                        .username(username)
                                        .password(password)
                                        .firstName(firstName)
                                        .lastName(lastName)
                                        .status(status)
                                        .role(role)
                                        .createdAt(new Date())
                                        .build();
        try {
            adminService.saveUser(user);
        } catch (SuchUserAlreadyExistException e) {
            model.put("users",userAccountService.getList());
            model.put("e",e.getMessage());
            return "user";
        }
        return "redirect:/user";
    }

    @GetMapping("/user/{userId}/edit")
    public String getUserEdit(@PathVariable Long userId, Map<String,Object> model){
        model.put("user",userAccountService.findById(userId));
        return "userDetailEdit";
    }

    @PostMapping("/user/edit")
    public String userEdit(@AuthenticationPrincipal UserPrincipal userPrincipal,
                            @RequestParam Long id,
                           @RequestParam String firstName,
                           @RequestParam String lastName,
                           @RequestParam Role role,
                           Map<String,Object> model){
        model.put("users",userAccountService.getList());
        model.put("auth",userPrincipal.getUserAccount().getRole().name());
        if (adminService.editUser(id,firstName,lastName,role)){
            model.put("e","Successful");
        } else model.put("e","Something went wrong");
        return "user";
    }

    @PostMapping("/user/{id}/lock")
    public String userLock(@PathVariable Long id){
        adminService.userLock(id);
        return "redirect:/user";
    }

    @PostMapping("/user/{id}/unlock")
    public String userUnLock(@PathVariable Long id){
        adminService.userUnLock(id);
        return "redirect:/user";
    }
}
