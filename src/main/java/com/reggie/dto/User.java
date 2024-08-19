package com.reggie.dto;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("user_table")
public class User {
    public User() {
    }

    public User(Integer id, Integer age, String sex) {
        this.id = id;
        this.age = age;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + sex + '\'' +
                '}';
    }

    private Integer id;
    private Integer age;
    private String sex;
}
