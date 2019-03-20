package com.cnpc.mapper;

import com.cnpc.enums.PersonSexEnum;

import com.cnpc.mapper.two.Person2Mapper;
import com.cnpc.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Person2MapperTest {

    @Autowired
    private Person2Mapper personMapper;

    @Test
    public void testInsert() throws Exception {
        for (int i = 0; i <10 ; i++) {
            personMapper.insert(new Person("magic"+i, "password"+i, PersonSexEnum.MAN,15+i));
        }
        for (int i = 10; i <20 ; i++) {
            personMapper.insert(new Person("magic"+i, "password"+i, PersonSexEnum.WOMAN,5+i));

        }
    }

    @Test
    public void testQuery() throws Exception {
        List<Person> personList = personMapper.getAll();
        if(personList==null || personList.size()==0){
            System.out.println("is null");
        }else{
            System.out.println(personList.size());
        }
    }


    @Test
    public void testUpdate() throws Exception {
        Person person = personMapper.getOne(1);
        System.out.println(person.toString());
        person.setAge(200);
        personMapper.update(person);

    }

    @Test
    public void delete(){
        personMapper.delete( 18 );
    }


}