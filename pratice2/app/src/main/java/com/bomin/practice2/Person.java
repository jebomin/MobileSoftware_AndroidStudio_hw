package com.bomin.practice2;

public class Person {

    private String id; //아이디
    private String name; //이름
    private String age; // 나이
    private String sex; // 성별

    public Person(String id, String name, String age, String sex) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }
}