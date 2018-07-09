package com.pemda.ekinerjademo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
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

    @Primary
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
                "hibernate.dialect",
                env.getProperty("spring.jpa.properties.hibernate.dialect"));
        properties.put(
                "spring.datasource.testWhileIdle",
                env.getProperty("spring.ekinerjaDatasource.testWhileIdle"));
        properties.put(
                "spring.datasource.validationQuery",
                env.getProperty("spring.ekinerjaDatasource.validationQuery"));
        properties.put(
                "hibernate.connection.zeroDateTimeBehavior",
                env.getProperty("hibernate.connection.zeroDateTimeBehavior"));
        properties.put(
                "spring.jpa.hibernate.ddl-auto",
                env.getProperty("spring.jpa.hibernate.ddl-auto"));
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
    public DataSource ekinerjaDataSource() {
        DriverManagerDataSource dataSource
                = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getProperty("spring.ekinerjaDataSource.driverClassName"));
        dataSource.setUrl(env.getProperty("spring.ekinerjaDataSource.url"));
        dataSource.setUsername(env.getProperty("spring.ekinerjaDataSource.username"));
        dataSource.setPassword(env.getProperty("spring.ekinerjaDataSource.password"));

        return dataSource;
    }

    @Primary
    @Bean
    public PlatformTransactionManager ekinerjaTransactionManager() {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(ekinerjaEntityManager().getObject());

        return transactionManager;
    }

    @Bean
    EmbeddedServletContainerCustomizer containerCustomizer() throws Exception {
        return (ConfigurableEmbeddedServletContainer container) -> {
            if (container instanceof TomcatEmbeddedServletContainerFactory) {
                TomcatEmbeddedServletContainerFactory tomcat = (TomcatEmbeddedServletContainerFactory) container;
                tomcat.addConnectorCustomizers(
                        (connector) -> {
                            connector.setMaxPostSize(1000000000); // 1000 MB
                        }
                );
            }
        };
    }

}
