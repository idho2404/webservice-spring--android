package com.example.laporkelas.model;

public class Role {
    private Integer id;
    private ERole name;

    public Role() {
        // Constructor kosong diperlukan untuk penggunaan ORM (misalnya, Hibernate)
    }

    public Role(Integer id, ERole name) {
        this.id = id;
        this.name = name;
    }

    // Getter dan Setter

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }
}
