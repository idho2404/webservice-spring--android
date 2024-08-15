package com.project.laporkelas.service;

import com.project.laporkelas.dto.UserDto;
import com.project.laporkelas.entity.User;
import com.project.laporkelas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDto loadUserByUsername(String email) throws
            UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " +
                    email);
        }
        return UserDto.build(user);
    }
}
