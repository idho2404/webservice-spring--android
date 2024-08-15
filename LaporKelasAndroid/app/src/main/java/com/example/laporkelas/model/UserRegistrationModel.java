package com.example.laporkelas.model;

public class UserRegistrationModel {
    private String name;
    private String nim;
    private String kelas;
    private String email;
    private String password;
    public UserRegistrationModel(String name, String nim, String kelas, String email, String password) {
        this.name = name;
        this.nim = nim;
        this.kelas = kelas;
        this.email = email;
        this.password = password;
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
