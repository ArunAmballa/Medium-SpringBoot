package com.arun.ProdReadyFeature.app.services.Impl;

import com.arun.ProdReadyFeature.app.dto.UserDto;
import com.arun.ProdReadyFeature.app.entities.User;
import com.arun.ProdReadyFeature.app.exceptions.ResourceNotFoundException;
import com.arun.ProdReadyFeature.app.repositories.UserRepository;
import com.arun.ProdReadyFeature.app.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;


    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository){
        this.modelMapper = modelMapper;
        this.userRepository=userRepository;
    }


    @Override
    public UserDto createUser(UserDto userDto) {
        User user=modelMapper.map(userDto,User.class);
        User savedUser=userRepository.save(user);
        return modelMapper.map(savedUser,UserDto.class);
    }

    @Override
    public UserDto getUserById(Long id) {
        User user=userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User With Given Id Not Found"));
        return modelMapper.map(user,UserDto.class);

    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users=userRepository.findAll();
        List<UserDto> actualUsers=users.stream().map((user)->modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
        return actualUsers;
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        User user=userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User With Given Id Not Found"));
        User inputUser=modelMapper.map(userDto,User.class);
        User updatedUser=userRepository.save(inputUser);
        return modelMapper.map(updatedUser,UserDto.class);
    }

    public Boolean isUserExist(Long id){
        return userRepository.existsById(id);
    }

    @Override
    public Boolean deleteUser(Long id) {
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return true;
        }return false;
    }

}
