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
 * Created by bagus on 29/09/17.
 */
@Configuration
@ComponentScan(basePackages = {"com.pemda.ekinerjademo"})
@PropertySource({"classpath:application.properties"})
@EnableJpaRepositories(
        basePackages = "com.pemda.ekinerjademo.repository.simdarepository",
        entityManagerFactoryRef = "simdaEntityManager",
        transactionManagerRef = "simdaTransactionManager")
public class SimdaRepositoryConfig {
    @Autowired
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean simdaEntityManager() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();

        em.setDataSource(simdaDataSource());
        em.setPackagesToScan(
                new String[] { "com.pemda.ekinerjademo.model.simdamodel" });

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
                "hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
        properties.put(
                "spring.datasource.testWhileIdle",
                env.getProperty("spring.simdaDatasource.testWhileIdle"));
        properties.put(
                "spring.datasource.validationQuery",
                env.getProperty("spring.simdaDatasource.validationQuery"));
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

        properties.put("spring.datasource.continue-on-error",
                env.getRequiredProperty("spring.simdaDataSource.continue-on-error"));

        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    public DataSource simdaDataSource() {
        DriverManagerDataSource dataSource
                = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getProperty("spring.simdaDataSource.driverClassName"));
        dataSource.setUrl(env.getProperty("spring.simdaDataSource.url"));
        dataSource.setUsername(env.getProperty("spring.simdaDataSource.username"));
        dataSource.setPassword(env.getProperty("spring.simdaDataSource.password"));

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager simdaTransactionManager() {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(simdaEntityManager().getObject());

        return transactionManager;
    }

}
