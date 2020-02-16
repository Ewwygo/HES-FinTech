package com.hes.fintech.usercontrol.service;

import com.hes.fintech.usercontrol.dto.UserAccountDTO;
import com.hes.fintech.usercontrol.mapper.UserAccountMapper;
import com.hes.fintech.usercontrol.repository.UserAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserAccountService {

    private final UserAccountMapper userAccountMapper;
    private final UserAccountRepository userAccountRepository;

    public List<UserAccountDTO> getList(){
        return userAccountRepository.findAll().stream().map(userAccountMapper::destinationToSource).collect(Collectors.toList());
    }

    public List<UserAccountDTO> getListByUsername(String filter){
        return userAccountRepository.findAllByUsername(filter).stream().map(userAccountMapper::destinationToSource).collect(Collectors.toList());
    }

    public UserAccountDTO findById(Long id){
        return userAccountMapper.destinationToSource(userAccountRepository.findById(id).get());
    }
}
