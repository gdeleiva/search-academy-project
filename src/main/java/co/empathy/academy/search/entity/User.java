package co.empathy.academy.search.entity;

import jakarta.persistence.*;



public class User {

    public static Long id;

    private String dni;
    private String name;
    private String email;

    public User(Long id, String dni, String name, String email) {
        super();
        this.id =id;
        this.dni = dni;
        this.name = name;
        this.email = email;
    }

    public User() {
    }

    public User(String name) {
        this.dni = "12345678";
        this.name = name;
        this.email = "prueba@gmail.com";
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}