package com.cocoblue.jdbcexam.dto;

public class Person {
    // 이름
    private String name;
    // 나이
    private int age;
    // 직업
    private String job;
    // 전화번호
    private String phone;

    public Person(String name, int age, String job, String phone) {
        this.name = name;
        this.age = age;
        this.job = job;
        this.phone = phone;
    }

    // Auto Generated (Getter, Setter, toString)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", job='" + job + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
