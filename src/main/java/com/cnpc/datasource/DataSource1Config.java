package com.cnpc.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


@Configuration
//最后将上面创建的 SqlSessionTemplate 注入到对应的 Mapper 包路路径下，
// 这样这个包下面的 Mapper 都会使用第一个数据源来进行数据库操作。
@MapperScan(basePackages = "com.cnpc.mapper.one", sqlSessionTemplateRef  = "oneSqlSessionTemplate")
public class DataSource1Config {

    //首先加载配置的数据源：
    @Bean(name = "oneDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.one")
    @Primary//默认数据源 只能指定一个
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }

    //根据创建的数据源，构建对应的 SqlSessionFactory。代码中需要指明需要加载的 Mapper xml ⽂文件。
    @Bean(name = "oneSqlSessionFactory")
    @Primary
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("oneDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mybatis/mapper/one/*.xml"));
        return bean.getObject();
    }

    //同时将数据源添加到事务中。
    @Bean(name = "oneTransactionManager")
    @Primary
    public DataSourceTransactionManager testTransactionManager(@Qualifier("oneDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    //接下来将上面创建的 SqlSessionFactory 注入，
    // 创建我们在 Mapper 中需要使用的 SqlSessionTemplate。

    @Bean(name = "oneSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(
            @Qualifier("oneSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
