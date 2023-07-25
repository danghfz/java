package IOC.bean;

/**
 * @author ��
 * @version 1.0
 * 2022/5/15   14:09
 */
public class Book {
    private String name;
    private Integer money;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", money=" + money +
                '}';
    }

    public Book() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }
}
