package com.pemda.ekinerjademo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * Created by bagus on 08/09/17.
 */
@Configuration
@ComponentScan(basePackages = {"com.pemda.ekinerjademo"})
@PropertySource({"classpath:application.properties"})
@EnableJpaRepositories(
        basePackages = "com.pemda.ekinerjademo.repository.ekinerjarepository",
        entityManagerFactoryRef = "ekinerjaEntityManager",
        transactionManagerRef = "ekinerjaTransactionManager")
public class EkinerjaRepositoryConfig {
    @Autowired
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean ekinerjaEntityManager() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();

        em.setDataSource(ekinerjaDataSource());
        em.setPackagesToScan(
                new String[] { "com.pemda.ekinerjademo.model.ekinerjamodel" });

        HibernateJpaVendorAdapter vendorAdapter
                = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();
        properties.put(
                "hibernate.show_sql",
                env.getProperty("spring.jpa.show-sql"));
        properties.put(
                "spring.jpa.hibernate.naming.physical-strategy",
                env.getProperty("spring.jpa.hibernate.naming.physical-strategy"));
        properties.put(
                "spring.jpa.properties.hibernate.dialect",
                env.getProperty("spring.jpa.properties.hibernate.dialect"));
        properties.put(
                "spring.datasource.testWhileIdle",
                env.getProperty("spring.ekinerjaDatasource.testWhileIdle"));
        properties.put(
                "spring.datasource.validationQuery",
                env.getProperty("spring.ekinerjaDatasource.validationQuery"));

        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    public DataSource ekinerjaDataSource() {
        DriverManagerDataSource dataSource
                = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getProperty("spring.ekinerjaDataSource.driverClassName"));
        dataSource.setUrl(env.getProperty("spring.ekinerjaDataSource.url"));
        dataSource.setUsername(env.getProperty("spring.ekinerjaDataSource.username"));
        dataSource.setPassword(env.getProperty("spring.ekinerjaDataSource.password"));

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager ekinerjaTransactionManager() {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(ekinerjaEntityManager().getObject());

        return transactionManager;
    }
}
