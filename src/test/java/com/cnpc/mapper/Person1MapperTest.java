package com.cnpc.mapper;

import com.cnpc.enums.PersonSexEnum;
import com.cnpc.mapper.one.Person1Mapper;
import com.cnpc.model.Person;
import com.cnpc.param.PersonParam;
import com.cnpc.result.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Person1MapperTest {

    @Autowired
    private Person1Mapper personMapper;

    @Test
    public void testInsert() throws Exception {
        for (int i = 0; i <10 ; i++) {
            personMapper.insert(new Person("magic"+i, "password"+i, PersonSexEnum.MAN,15+i));
        }
        for (int i = 10; i <20 ; i++) {
            personMapper.insert(new Person("magic"+i, "password"+i, PersonSexEnum.WOMAN,5+i));

        }


//		Assert.assertEquals(3, userMapper.getAll().size());
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

    /**personParam.
     * beginLine当前页第一行
     * pageSize 给定每页3行，也可以传
     * currentPage 前台传入当前第几页（0）
     * beginline = currentPage * pageSize
     *
     * 传进mysql limit beginLine ,pageSize 就取到了需要的值
     *
     * Page中参数
     * currentPage 当前页 = personParam中currentPage
     * totlNumber 总条数 = count
     * totlePage 总页数 = totleNumber/pageParam.pageSize == 0 ?
     * totleNumber/pageParam.pageSize : totleNumber/pageParam.pageSize+1
     * 数据集合 = personlist
     *
     */

    @Test
    public void pageTest(){
        PersonParam personParam = new PersonParam();
        personParam.setCurrentPage( 0 );
        List<Person> personList = personMapper.getList( personParam );
        long count = personMapper.getCount( personParam );
        Page page = new Page( personParam,count,personList );
        System.out.println(page);

    }

    @Test
    public void selectByAgeAndSex(){
        Map<String,Object> map = new HashMap<>(  );
        map.put( "sex",PersonSexEnum.MAN );
        map.put( "age",20 );
        List<Person> listByAgeAndSex = personMapper.getListByAgeAndSex( map );
        System.out.println(listByAgeAndSex.toString());
    }
}