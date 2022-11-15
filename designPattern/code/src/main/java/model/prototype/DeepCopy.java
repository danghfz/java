package model.prototype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.*;

/**
 * @author danghf
 * @version 1.0
 * 2022/11/15   17:32
 * 深拷贝实现
 */
public class DeepCopy {
    public static void main(String[] args) throws CloneNotSupportedException {
        DeepTarget deepTarget = new DeepTarget("name", new Target("target"));
        DeepTarget clone = (DeepTarget) deepTarget.clone();
        DeepTarget clone2 = (DeepTarget) deepTarget.clone();
        System.out.println(clone == clone2); // false
    }
}

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
class DeepTarget implements Serializable, Cloneable {
    private String name;
    private Target target; // 引用类型

    // 深拷贝方式 1： clone
    @Override
    protected Object clone() throws CloneNotSupportedException {
        // 完成基本数据类型 和 String 的克隆
        Object clone = super.clone();
        //对引用类型的属性，进行单独处理
        DeepTarget deepTarget = (DeepTarget) clone;
        deepTarget.target = (Target) deepTarget.target.clone();
        return deepTarget;
    }

    // 深拷贝方式 2：序列化
    public Object deepCopy() {
        // try-with-resources 自动关闭流
        try (
                // 创建流对象
                // 输出流
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos);
                // 输入流
                ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
                ObjectInputStream ois = new ObjectInputStream(bis);
        ) {
            // 序列化
            oos.writeObject(this);// 当前这个对象以对象流的方式输出
            // 反序列化
            Object object = ois.readObject();
            return object;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
class Target implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;
    private String name;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}