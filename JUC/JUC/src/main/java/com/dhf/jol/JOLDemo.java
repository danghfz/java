package com.dhf.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

/**
 * @author 党
 * @version 1.0
 * 2022/9/9   15:17
 */
class Customer{
    private String id;
    private boolean flag;
}
public class JOLDemo {
    public static void main(String[] args) {
        // vm 虚拟机详情细节
//        System.out.println(VM.current().details());
//        Object o = new Object();
        Object o = new Customer();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }
}
