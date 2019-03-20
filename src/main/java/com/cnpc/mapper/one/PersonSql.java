package com.cnpc.mapper.one;

import org.apache.ibatis.jdbc.SQL;
import com.cnpc.param.PersonParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class PersonSql {
    private static final Logger LOGGER = LoggerFactory.getLogger( PersonSql.class );


    public String getList(PersonParam personParam){
        StringBuffer sql =new StringBuffer( "select id,username,password,sex,age " );
        sql.append( "from person where 1=1 " );
        if(personParam !=null){
            if(!StringUtils.isEmpty( personParam.getSex() )){
                sql.append( " and sex = #{sex} " );
            }
            if(!StringUtils.isEmpty( personParam.getAge() )){
                sql.append( " and age = #{age}" );
            }
        }
        sql.append( " order by id asc" );
        sql.append( " limit "+personParam.getBeginLine()+","+personParam.getPageSize());
        LOGGER.info("getList SQL ===="+sql.toString());
        return sql.toString();
    }

    public String getCount(PersonParam personParam){
        String sql = new SQL(){{
            SELECT("count(1)");
            FROM( "person" );
            if(!StringUtils.isEmpty( personParam.getSex() )){
                WHERE( "sex = #{sex}" );
            }
            if(!StringUtils.isEmpty( personParam.getAge() )){
                WHERE( "age = #{age}" );
            }
        //从这个toString可以看出，其内部使用StringBuilder实现SQL拼接
        }}.toString();
        LOGGER.info("getCount sql is :" +sql);
        return sql;
    }
}
