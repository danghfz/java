package model.prototype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author danghf
 * @version 1.0
 * 2022/11/15   16:33
 * 小羊肖恩
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Sheep implements Cloneable{
    private String name;
    private String age;
    private String color;
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object clone = super.clone();// 默认浅拷贝
        Sheep sheep = (Sheep) clone;
        return sheep;
    }
}
