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
        basePackages = "com.pemda.ekinerjademo.repository.bismarepository",
        entityManagerFactoryRef = "bismaEntityManager",
        transactionManagerRef = "bismaTransactionManager")
public class BismaRepositoryConfig {
    @Autowired
    private Environment env;

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean bismaEntityManager() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();

        em.setDataSource(bismaDataSource());
        em.setPackagesToScan(
                new String[] { "com.pemda.ekinerjademo.model.bismamodel" });

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
                env.getProperty("spring.bismaDatasource.testWhileIdle"));
        properties.put(
                "spring.datasource.validationQuery",
                env.getProperty("spring.bismaDatasource.validationQuery"));
        properties.put(
                "hibernate.connection.zeroDateTimeBehavior",
                env.getProperty("hibernate.connection.zeroDateTimeBehavior"));
        //for sql query debugging
        properties.put("hibernate.show_sql",
                env.getRequiredProperty("spring.jpa.properties.hibernate.show_sql")
        );
        properties.put("hibernate.format_sql",
                env.getRequiredProperty("spring.jpa.properties.hibernate.format_sql")
        );
        properties.put("hibernate.generate_statistics",
                env.getRequiredProperty("spring.jpa.properties.hibernate.generate_statistics")
        );

        em.setJpaPropertyMap(properties);

        return em;
    }

    @Primary
    @Bean
    public DataSource bismaDataSource() {
        DriverManagerDataSource dataSource
                = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getProperty("spring.bismaDataSource.driverClassName"));
        dataSource.setUrl(env.getProperty("spring.bismaDataSource.url"));
        dataSource.setUsername(env.getProperty("spring.bismaDataSource.username"));
        dataSource.setPassword(env.getProperty("spring.bismaDataSource.password"));

        return dataSource;
    }

    @Primary
    @Bean
    public PlatformTransactionManager bismaTransactionManager() {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(bismaEntityManager().getObject());

        return transactionManager;
    }

}
