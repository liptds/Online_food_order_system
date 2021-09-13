package com.laioffer.onlineOrder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class ApplicationConfig {

    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.laioffer.onlineOrder.entity");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        Properties prop = new Properties();
        InputStream input = null;
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        try {
            input = new FileInputStream("config.properties");

            // load a properties file
            prop.load(input);

            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            // 只需要修改红色部分, 保留其他内容,  YOUR_RDS_INSTANCE_ADDRESS,USERNAME,  PASSWORD可以回到第一个项目的MysqlDBUtil.java去找

            dataSource.setUrl("jdbc:mysql://laiproject-instance.ckoazwbgnpfh.us-east-2.rds.amazonaws.com:3306/onlineOrder?createDatabaseIfNotExist=true&serverTimezone=UTC");
            dataSource.setUsername(prop.getProperty("user"));
            dataSource.setPassword(prop.getProperty("password"));

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return dataSource;

    }


    private final Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        return hibernateProperties;
    }
}


