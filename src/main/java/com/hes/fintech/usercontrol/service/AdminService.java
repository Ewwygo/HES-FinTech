package com.hes.fintech.usercontrol.service;

import com.hes.fintech.usercontrol.dto.UserAccountDTO;
import com.hes.fintech.usercontrol.entity.Role;
import com.hes.fintech.usercontrol.entity.Status;
import com.hes.fintech.usercontrol.entity.UserAccount;
import com.hes.fintech.usercontrol.exception.SuchUserAlreadyExistException;
import com.hes.fintech.usercontrol.mapper.UserAccountMapper;
import com.hes.fintech.usercontrol.repository.UserAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminService {

    private final UserAccountRepository userAccountRepository;
    private final UserAccountMapper userAccountMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void saveUser(UserAccountDTO userAccountDTO) throws SuchUserAlreadyExistException {
        if (userAccountRepository.findByUsername(userAccountDTO.getUsername()).isPresent()){
            throw new SuchUserAlreadyExistException("User with username=" + userAccountDTO.getUsername() + " already exists");
        }
        UserAccount userAccount = userAccountMapper.sourceToDestination(userAccountDTO);
        userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
        userAccountRepository.save(userAccount);
    }

    @Transactional
    public boolean editUser(Long id, String firstName, String lastName, Role role){
        Optional<UserAccount> userAccount = userAccountRepository.findById(id);
        if (userAccount.isPresent()){
            userAccount.get().setFirstName(firstName);
            userAccount.get().setLastName(lastName);
            userAccount.get().setRole(role);
            return true;
        } else return false;
    }

    @Transactional
    public boolean userLock(Long id){
        Optional<UserAccount> userAccount = userAccountRepository.findById(id);
        if (userAccount.isPresent()){
            userAccount.get().setStatus(Status.INACTIVE);
            return true;
        }
        else return false;
    }

    @Transactional
    public boolean userUnLock(Long id){
        Optional<UserAccount> userAccount = userAccountRepository.findById(id);
        if (userAccount.isPresent()){
            userAccount.get().setStatus(Status.ACTIVE);
            return true;
        }
        else return false;
    }

    // Admin for debug app
    @PostConstruct
    public void init() {
        if (userAccountRepository.findByUsername("user").isEmpty()) {
            UserAccount userAccount = new UserAccount();
            userAccount.setUsername("user");
            userAccount.setPassword(passwordEncoder.encode("password"));
            userAccount.setRole(Role.ADMIN);
            userAccount.setStatus(Status.ACTIVE);
            userAccount.setFirstName("admin");
            userAccount.setLastName("admin");
            userAccountRepository.save(userAccount);
        }
    }
}
