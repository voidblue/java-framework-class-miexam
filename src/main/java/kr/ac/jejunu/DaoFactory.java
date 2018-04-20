package kr.ac.jejunu;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

@Configuration
public class DaoFactory {
    @Value("${db.classname}")
    private String classname;
    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;




    @Bean
    public ProductDao getDao() {
        return new ProductDao(jdbcTemplate());
    }
    @Bean
    ConnectionMaker connectionMaker(){
        return new JejuConnectionMaker();
    }

    @Bean
    DataSource dataSource(){
        DataSource dataSource = new SimpleDriverDataSource();
        try {
            ((SimpleDriverDataSource) dataSource).setDriverClass((Class<? extends Driver>) Class.forName(classname));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ((SimpleDriverDataSource) dataSource).setUrl(url);
        ((SimpleDriverDataSource) dataSource).setUsername(username);
        ((SimpleDriverDataSource) dataSource).setPassword(password);
        return dataSource;
    }

    @Bean
    JdbcContext jdbcContext(){
        return new JdbcContext(dataSource());
    }

    @Bean
    JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }
}
