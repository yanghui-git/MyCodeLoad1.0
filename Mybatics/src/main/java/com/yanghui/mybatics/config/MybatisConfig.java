package com.yanghui.mybatics.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Configuration
@Component
@MapperScan(basePackages = "com.yanghui.mybatics,mapper", sqlSessionFactoryRef = "sqlSessionFactoryOfMaster")
public class MybatisConfig {

    @Bean("sqlSessionFactoryOfMaster")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("mysqlConfig") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sqlSessionFactoryBean =new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        System.out.println("1111111");
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml"));;
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "transactionManagerOfMaster")
    @Primary
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSourceConfig());
    }


    @Bean("mysqlConfig")
    @Primary
    public DataSource dataSourceConfig(){
        DruidDataSource druidDataSource =new DruidDataSource();
        druidDataSource.setUrl("jdbc:mysql://10.1.200.204:3306/showdb?useUnicode=true&characterEncoding=utf-8&useSSL=false");
        druidDataSource.setUsername("yhtestshow");
        druidDataSource.setPassword("DBuser123!");
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        return druidDataSource;
    }
}

