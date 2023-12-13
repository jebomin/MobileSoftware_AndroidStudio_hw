package com.example.final_2021;

public class Information {
    String name;
    String phone;
    String email;

    public Information(String name, String phone, String email){
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getName(){
        return name;
    }
    public String getPhone(){
        return phone;
    }
    public String getEmail(){
        return email;
    }
}