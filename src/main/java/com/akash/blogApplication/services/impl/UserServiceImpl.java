package com.akash.blogApplication.services.impl;

import com.akash.blogApplication.exceptions.ResourceNotFoundException;
import com.akash.blogApplication.payloads.UserDto;
import com.akash.blogApplication.entities.User;

import com.akash.blogApplication.repositories.UserRepo;
import com.akash.blogApplication.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        User user = userDtoToUser(userDto);
        User savedUser = this.userRepo.save(user);
        return this.userToUserDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {

        User user = this.userDtoToUser(getUserById(userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updatedUser = this.userRepo.save(user);
        return this.userToUserDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {

        User user = userRepo.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User","Id",userId)
        );
        return this.userToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> users = this.userRepo.findAll();

        return users.stream().map(user ->
                this.userToUserDto(user)).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userDtoToUser(getUserById(userId));
        this.userRepo.delete(user);
    }

    public User userDtoToUser(UserDto userDto) {
        return this.modelMapper.map(userDto,User.class);
    }

    public UserDto userToUserDto(User user) {
        return this.modelMapper.map(user,UserDto.class);
    }
}
