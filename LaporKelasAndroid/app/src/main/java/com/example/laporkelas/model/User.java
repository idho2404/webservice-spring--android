package com.example.laporkelas.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class User implements Serializable {
    private Long id;
    private String name;
    private String nim;
    private String kelas;
    private String email;
    private String password;
    private Set<Role> roles = new HashSet<>();

    // Tambahkan konstruktor, getter, dan setter sesuai kebutuhan

    public User() {
        // Constructor kosong diperlukan untuk penggunaan ORM (misalnya, Room)
    }

    public User(Long id, String name, String nim, String kelas, String email, String password) {
        this.id = id;
        this.name = name;
        this.nim = nim;
        this.kelas = kelas;
        this.email = email;
        this.password = password;
    }

    // Getter dan Setter untuk properti lainnya

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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}

