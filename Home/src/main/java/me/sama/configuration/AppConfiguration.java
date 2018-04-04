package me.sama.configuration;

import com.mysql.jdbc.Driver;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import java.util.Properties;

/**
 * @author SAMA
 * @since 04/03/2018.
 */
@Configuration
@ComponentScan(basePackages = "me.sama")
@PropertySource("config.properties")
public class AppConfiguration {


    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public static BasicDataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(Driver.class.getName());
        dataSource.setUrl("${hibernate.mysql.connection.url}");
        dataSource.setUsername("${hibernate.mysql.connection.username}");
        dataSource.setPassword("${hibernate.mysql.connection.password}");
        return dataSource;
    }

    public static LocalSessionFactoryBean sessionFactoryBean(BasicDataSource dataSource){
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
        properties.setProperty("hibernate.show_sql", "${hibernate.connection.show_sql}");
        properties.setProperty("hibernate.c3p0.min_size","${hibernate.c3p0.min_size}");
        properties.setProperty("hibernate.c3p0.max_size","${hibernate.c3p0.max_size}");
        properties.setProperty("hibernate.c3p0.acquire_increment","${hibernate.c3p0.acquire_increment}");
        properties.setProperty("hibernate.c3p0.idle_test_period","${hibernate.c3p0.idle_test_period}");
        sessionFactoryBean.setHibernateProperties(properties);
        return sessionFactoryBean;
    }

}
