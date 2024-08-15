package com.project.laporkelas.service;

import com.project.laporkelas.dto.UserDto;
import com.project.laporkelas.entity.ERole;
import com.project.laporkelas.entity.Role;
import com.project.laporkelas.entity.User;
import com.project.laporkelas.mapper.UserMapper;
import com.project.laporkelas.repository.RoleRepository;
import com.project.laporkelas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public UserDto createUser(UserDto userDto) {
        if (!userDto.getEmail().toLowerCase().endsWith("@stis.ac.id")) {
            throw new RuntimeException("Error: Harus Menggunakan Email STIS");
        }
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new RuntimeException("Error: Email Sudah Terdaftar. Tidak Bisa Mendaftar Dengan Email Sama!");
        }
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
        User user = UserMapper.mapToUser(userDto);
        user.setRoles(roles);
        return UserMapper.mapToUserDto(userRepository.save(user));
    }
    
    @Override
    public UserDto myProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName(); // Mendapatkan email pengguna yang saat ini diautentikasi
        User user = userRepository.findByEmail(userEmail);

        if (user != null) {
            return UserMapper.mapToUserDto(user);
        } else {
            // Handle jika pengguna tidak ditemukan (opsional)
            return null;
        }
    }

  
    @Override
    public void updateUser(Long id, UserDto userDto, String emailValidation, String passwordValidation) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with ID " + id + " not found."));

        // Hash kata sandi yang dimasukkan oleh pengguna
        if (!passwordEncoder.matches(passwordValidation, existingUser.getPassword()) || !existingUser.getEmail().equalsIgnoreCase(emailValidation)) {
            throw new RuntimeException("Email atau password tidak valid. Silakan masukkan data yang benar.");
        }

        
        if (userDto.getEmail() != null) {
            existingUser.setEmail(userDto.getEmail());
        }
        if (userDto.getPassword() != null) {
            existingUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }
        if (userDto.getName() != null) {
            existingUser.setName(userDto.getName());
        }
        if (userDto.getNim() != null) {
            existingUser.setNim(userDto.getNim());
        }
        if (userDto.getKelas() != null) {
            existingUser.setKelas(userDto.getKelas());
        }

        userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<User> findAllUsers() {
        List<User> users = userRepository.findAll();
         return users.stream()
        .map(user -> {
            User userDto = new User();
            userDto.setId(user.getId());
            userDto.setName(user.getName());
            userDto.setEmail(user.getEmail());
            userDto.setNim(user.getNim());
            userDto.setKelas(user.getKelas());
            return userDto;
        })
        .collect(Collectors.toList());
    }
    
    @Override
    public List<UserDto> searchUsers(String keyword) {
        List<User> users = this.userRepository
        .findByNameContainingOrNimContainingOrKelasContainingOrEmailContainingIgnoreCase(keyword, keyword, keyword, keyword);
        List<UserDto> userDto = users.stream()
            .map((student) -> (UserMapper.mapToUserDto(student)))
            .collect(Collectors.toList());
        return userDto;
    }
}
