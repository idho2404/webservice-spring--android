package com.project.laporkelas.mapper;

import com.project.laporkelas.dto.UserDto;
import com.project.laporkelas.entity.User;

public class UserMapper {
    public static User mapToUser(UserDto userDto){
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .nim(userDto.getNim())
                .kelas(userDto.getKelas())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }
    public static UserDto mapToUserDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .nim(user.getNim())
                .kelas(user.getKelas())
                .password(user.getPassword())
                .email(user.getEmail())
                .build();
    }
}
