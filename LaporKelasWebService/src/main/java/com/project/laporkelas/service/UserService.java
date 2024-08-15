package com.project.laporkelas.service;

import com.project.laporkelas.dto.UserDto;
import com.project.laporkelas.entity.User;
import java.util.List;

public interface UserService{
    public UserDto createUser(UserDto user);
    public UserDto myProfile();
    public void updateUser(Long id, UserDto userDto, String emailValidation, String passwordValidation) ;
    public void deleteUser(Long userId);
    List<User> findAllUsers();
    public List<UserDto> searchUsers(String keyword);
}
