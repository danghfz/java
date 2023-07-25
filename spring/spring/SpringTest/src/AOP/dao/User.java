package AOP.dao;

import org.springframework.stereotype.Component;

/**
 * @author µ³
 * @version 1.0
 * 2022/5/15   14:21
 */
@Component
public class User {
    public void add(){
        System.out.println("User : add");
    }
}
