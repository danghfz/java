package IOC.config;

import IOC.bean.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author ��
 * @version 1.0
 * 2022/5/15   14:07
 */
@Configuration
@ComponentScan(basePackages = "IOC.*")
public class Spring {//������
    @Bean
    public Book book(){
        Book book = new Book();
        book.setName("java");
        book.setMoney(100);
        return book;
    }
}
