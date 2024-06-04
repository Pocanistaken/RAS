package com.ras.entity.account;

public class Employee {

    
    private String username;
    private String role;
    private String name;
    private String surname;
    private String birthDate;
    private String phoneNumber;
    
    private static Employee instance;
    
    private Employee() {
       
    }

    public Employee(String username, String role, String name, String surname, String birthDate, String phoneNumber) {
        this.username = username;
        this.role = role;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
    }
    
    
    public static synchronized Employee getInstance() {
        if (instance == null) {
            instance = new Employee();
        }
        return instance;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
