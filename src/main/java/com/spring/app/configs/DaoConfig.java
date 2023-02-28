package com.spring.app.configs;


import com.spring.app.dao.StudentDao;
import com.spring.app.dao.StudentDaoMySqlImpl;
import com.spring.app.entity.Student;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

import static com.spring.app.constants.Constants.*;


@Configuration
@EnableTransactionManagement
public class DaoConfig {

    @Bean("DaoProperties")
    public MyProperties getHibernateProperties() {
        MyProperties properties = new MyProperties();
        properties.setProperty(HIBERNATE_DIALECT, org.hibernate.dialect.MySQL5Dialect.class.getName());
        properties.setProperty(HIBERNATE_SHOW_SQL, "true");
        properties.setProperty(HIBERNATE_HBM2DDL_AUTO, "update");
        return properties;
    }


    @Bean
    public DriverManagerDataSource getDataSource() {
        System.out.println(DATABASE_USERNAME);
        DriverManagerDataSource dataSource = new DriverManagerDataSource(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        dataSource.setDriverClassName(com.mysql.cj.jdbc.Driver.class.getName());
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean getLocalSessionFactory(DriverManagerDataSource dataSource, MyProperties properties) {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setHibernateProperties(properties);
        localSessionFactoryBean.setDataSource(dataSource);
        localSessionFactoryBean.setAnnotatedClasses(Student.class);
        return localSessionFactoryBean;
    }

    @Bean
    public HibernateTemplate getHibernateTemplate(SessionFactory sessionFactory) {
        return new HibernateTemplate(sessionFactory);
    }

    @Bean
    public HibernateTransactionManager getHibernateTransactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

    @Bean("studentDao")
    public StudentDao getStudentDao(HibernateTemplate hibernateTemplate) {
        return new StudentDaoMySqlImpl(hibernateTemplate);
    }

    static class MyProperties extends Properties {
    }
}
