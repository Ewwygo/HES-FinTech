package com.hes.fintech.usercontrol.dto;

import com.hes.fintech.usercontrol.entity.Role;
import com.hes.fintech.usercontrol.entity.Status;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UserAccountDTO {

    public UserAccountDTO(){

    }

    public UserAccountDTO(Long id, String username, String password, String firstName, String lastName, Role role, Status status, Date createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.status = status;
        this.createdAt = createdAt;
    }

    private Long id;

    private String username;
    private String password;
    private String firstName;
    private String lastName;

    private Role role;
    private Status status;

    private Date createdAt;
}
