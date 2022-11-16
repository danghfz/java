package model.combination;

import java.util.HashMap;

/**
 * @author danghf
 * @version 1.0
 * 2022/11/16   17:29
 * 组合模式 在 java jdk中的应用
 */
public class Jdk {
    public static void main(String[] args) {
        /**
         * 1、Map是一个抽象的构建
         * 2、HashMap 是一个中间构建，实现/继承了相关方法
         *    put  putAll
         * 3、Node 是HashMap的静态内部类，类似 leaf结点
         */
        HashMap<Integer, String> hashMap = new HashMap<>(16);
        // 直接放在叶子结点
        hashMap.put(0,"0");
        HashMap<Integer, String> map = new HashMap<>(16);
        map.put(1, "1");
        map.put(2, "2");
        hashMap.putAll(map);
        System.out.println(hashMap);
    }
}
