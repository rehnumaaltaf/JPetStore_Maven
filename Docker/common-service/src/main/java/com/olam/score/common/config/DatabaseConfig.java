package com.olam.score.common.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

  /**
   * DataSource definition for database connection. Settings are read from
   * the application.properties file (using the env object).
   */
  @Bean
  public DataSource dataSource() {
      
   PoolProperties p = new PoolProperties();
   p.setUrl(env.getProperty("db.url"));
   p.setDriverClassName(env.getProperty("db.driver"));
   p.setUsername(env.getProperty("db.username"));
   p.setPassword(env.getProperty("db.password"));
   p.setJmxEnabled(true);
   p.setTestWhileIdle(false);
   p.setTestOnBorrow(true);
   p.setValidationQuery("SELECT 1");
   p.setTestOnReturn(false);
   p.setValidationInterval(30000);
   p.setTimeBetweenEvictionRunsMillis(30000);
   p.setMaxActive(100);
   p.setInitialSize(10);
   p.setMaxWait(10000);
   p.setRemoveAbandonedTimeout(60);
   p.setMinEvictableIdleTimeMillis(30000);
   p.setMinIdle(10);
   p.setLogAbandoned(true);
   p.setRemoveAbandoned(true);
   p.setJdbcInterceptors(
     "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"+
     "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer;"+
     "org.apache.tomcat.jdbc.pool.interceptor.StatementCache");
   org.apache.tomcat.jdbc.pool.DataSource datasource = new org.apache.tomcat.jdbc.pool.DataSource();
   datasource.setPoolProperties(p);
   
   return datasource;
  
  }

  /**
   * Declare the JPA entity manager factory.
   */
  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean entityManagerFactory =
        new LocalContainerEntityManagerFactoryBean();
    
    entityManagerFactory.setDataSource(dataSource);
    
    // Classpath scanning of @Component, @Service, etc annotated class
    entityManagerFactory.setPackagesToScan(
        env.getProperty("entitymanager.packagesToScan"));
    
    // Vendor adapter
    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
    
    // Hibernate properties
    Properties additionalProperties = new Properties();
    additionalProperties.put(
        "hibernate.dialect", 
        env.getProperty("hibernate.dialect"));
    additionalProperties.put(
        "hibernate.show_sql", 
        env.getProperty("hibernate.show_sql"));
//    additionalProperties.put(
//        "hibernate.hbm2ddl.auto", 
//        env.getProperty("hibernate.hbm2ddl.auto"));
    entityManagerFactory.setJpaProperties(additionalProperties);
    
    return entityManagerFactory;
  }

  /**
   * Declare the transaction manager.
   */
  @Bean
  public JpaTransactionManager transactionManager() {
    JpaTransactionManager transactionManager = 
        new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(
        entityManagerFactory.getObject());
    return transactionManager;
  }
  
  /**
   * PersistenceExceptionTranslationPostProcessor is a bean post processor
   * which adds an advisor to any bean annotated with Repository so that any
   * platform-specific exceptions are caught and then rethrown as one
   * Spring's unchecked data access exceptions (i.e. a subclass of 
   * DataAccessException).
   */
  @Bean
  public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
    return new PersistenceExceptionTranslationPostProcessor();
  }

  // Private fields
  
  @Autowired
  private Environment env;

  @Autowired
  private DataSource dataSource;

  @Autowired
  private LocalContainerEntityManagerFactoryBean entityManagerFactory;

}