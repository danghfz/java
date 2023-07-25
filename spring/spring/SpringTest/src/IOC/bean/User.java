package IOC.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author µ≥
 * @version 1.0
 * 2022/5/15   13:53
 */
@Component
public class User {
    @Value("’‘µœ")
    private String name;
    @Value("123465")
    private String id;
    @Qualifier
    private Date date;

    public User() {
    }

    public User(String name, String id, Date date) {
        this.name = name;
        this.id = id;
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", date=" + date +
                '}';
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
