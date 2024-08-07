package com.arun.ProdReadyFeature.app.services;

import com.arun.ProdReadyFeature.app.dto.UserDto;
import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUserById(Long id);

    List<UserDto> getAllUsers();

    UserDto updateUser(Long id,UserDto userDto);

    Boolean deleteUser(Long id);
}
