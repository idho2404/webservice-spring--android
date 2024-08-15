package com.example.laporkelas.model;

public class UserDto {
    private Long id;
    private String name;
    private String nim;
    private String kelas;
    private String email;
    private String password;

    public UserDto(String name, String email, String nim, String kelas, String password) {
        this.name = name;
        this.email = email;
        this.nim = nim;
        this.kelas = kelas;
        this.password = password;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
