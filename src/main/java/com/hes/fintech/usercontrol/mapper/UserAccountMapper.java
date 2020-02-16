package com.hes.fintech.usercontrol.mapper;

import com.hes.fintech.usercontrol.dto.UserAccountDTO;
import com.hes.fintech.usercontrol.entity.UserAccount;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserAccountMapper {

    UserAccount sourceToDestination(UserAccountDTO source);

    UserAccountDTO destinationToSource(UserAccount destination);
}
