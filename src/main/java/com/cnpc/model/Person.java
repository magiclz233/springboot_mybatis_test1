package com.cnpc.model;

import com.cnpc.enums.PersonSexEnum;
import lombok.Data;

@Data
public class Person {
    private Integer id;
    private String username;
    private String password;
    private PersonSexEnum sex;
    private Integer age;



    public Person(String username,String password,PersonSexEnum sex,Integer age){
        super();
        this.age = age;
        this.password = password;
        this.username = username;
        this.sex = sex;
    }
}
