package com.bomin.practice3;

public class PhoneBook {

    private String phone_id; //아이디
    private String phone_name; //이름
    private String phone_number; //전화번호
    private byte[] user_image; //사진

    public PhoneBook(String phone_id, String phone_name, String phone_number, byte[] user_image) {
        this.phone_id = phone_id;
        this.phone_name = phone_name;
        this.phone_number = phone_number;
        this.user_image = user_image;
    }

    public String getPhone_id() {
        return phone_id;
    }

    public void setPhone_id(String phone_id) {
        this.phone_id = phone_id;
    }

    public String getPhone_name() {
        return phone_name;
    }

    public void setPhone_name(String phone_name) {
        this.phone_name = phone_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public byte[] getUser_image() {
        return user_image;
    }

    public void setUser_image(byte[] user_image) {
        this.user_image = user_image;
    }
}