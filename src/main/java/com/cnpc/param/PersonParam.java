package com.cnpc.param;


import com.cnpc.enums.PersonSexEnum;

public class PersonParam extends PageParam {
    private String age;
    private String sex;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
