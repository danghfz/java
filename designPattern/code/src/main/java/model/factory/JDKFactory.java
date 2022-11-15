package model.factory;

import java.util.Calendar;

/**
 * @author danghf
 * @version 1.0
 * 2022/11/15   16:12
 * jdk源码中 使用 工厂模式
 *  Calendar
 */
public class JDKFactory {
    public static void main(String[] args) {
        Calendar instance = Calendar.getInstance();
        // createCalendar(TimeZone.getDefault(), Locale.getDefault(Locale.Category.FORMAT));

    }
}
