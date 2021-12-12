package com.bell.BellApi.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {

    @Bean(name = "hibernateSessionFactory")
    public LocalSessionFactoryBean sessionFactoryBean(DataSource dataSource){
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource);
        localSessionFactoryBean.setPackagesToScan("com.bell.BellApi.model");
        return localSessionFactoryBean;
    }

    @Bean(name = "hibernate")
    public HibernateTransactionManager hibernateTransactionManager(LocalSessionFactoryBean sessionFactoryBean){
        return new HibernateTransactionManager(sessionFactoryBean.getObject());
    }

    @Bean(name = "jpa")
    public JpaTransactionManager jpaTransactionManager(EntityManagerFactory entityManagerFactory, DataSource dataSource){
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        jpaTransactionManager.setDataSource(dataSource);
        return jpaTransactionManager;
    }
}
