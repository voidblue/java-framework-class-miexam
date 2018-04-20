package kr.ac.jejunu;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {
    @Bean
    public ProductDao getDao() {
        return new ProductDao(connectionMaker());
    }
    @Bean
    ConnectionMaker connectionMaker(){
        return new JejuConnectionMaker();
    }
}
