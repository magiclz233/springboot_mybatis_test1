package com.cnpc.mapper.two;

import com.cnpc.enums.PersonSexEnum;
import com.cnpc.model.Person;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface Person2Mapper {

    @Select( "select id,username,password,sex,age from person" )
    /**
     * 当modul中字段名和数据库中不同时
     * column--:数据库字段名字
     * property：model字段名字
     * javaType:字段类型
     */
    @Results({
            @Result(column = "username",property = "username",javaType = String.class),
            @Result(column = "sex",property = "sex",javaType = PersonSexEnum.class)
    })
    List<Person> getAll();

    @Select( "select id,username,password,sex,age from person where id = #{id}" )
    @Results({
            @Result(column = "username",property = "username",javaType = String.class),
            @Result(column = "sex",property = "sex",javaType = PersonSexEnum.class)
    })
    Person getOne(int id);

    @Insert( "insert into person(username,password,sex,age) " +
            "values(#{username},#{password},#{sex},#{age})" )
    void insert(Person person);

    @Update( "update person set username = #{username},password = #{password}," +
            "sex = #{sex},age = #{age} where id = #{id}" )
    void update(Person person);

    @Delete( "delete from person where id = #{id}" )
    void delete(int id);

    @Update({"<script> ",
            "update person " ,
            "<set>" ,
            " <if test='username != null'>username=#{username},</if>" ,
            " <if test='age != null'>age=#{age},</if>" ,
            " </set> ",
            "where id=#{id} " ,
            "</script>"})
    void updateUser(Person person);

    @Select("SELECT * FROM person WHERE sex = #{sex}")
    List<Person> getListByPsersonSex(@Param("sex") String sex);

    @Select("SELECT * FROM person WHERE age=#{age} AND sex = #{sex}")
    List<Person> getListByAgeAndSex(Map<String, Object> map);


}
