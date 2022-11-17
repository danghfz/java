package model.flyweight;

/**
 * @author danghf
 * @version 1.0
 * 2022/11/17   17:05
 * Integer 中的 享元模式
 */
public class Jdk {
    public static void main(String[] args) {
        Integer x = Integer.valueOf(127);
        Integer y = new Integer(127);
        Integer z = Integer.valueOf(127);
        Integer w = new Integer(127);
        // true
        System.out.println(x.equals(y));
        // false
        System.out.println(x == y);
        // true
        System.out.println(x == z);
        // false
        System.out.println(w == x);
        // false
        System.out.println(w == y);
        /**
         * public static Integer valueOf(int i) {
         *         if (i >= IntegerCache.low && i <= IntegerCache.high)
         *             return IntegerCache.cache[i + (-IntegerCache.low)];
         *         return new Integer(i);
         *     }
         * static {
         *             // high value may be configured by property
         *             int h = 127;
         *             String integerCacheHighPropValue =
         *                 sun.misc.VM.getSavedProperty("java.lang.Integer.IntegerCache.high");
         *             if (integerCacheHighPropValue != null) {
         *                 try {
         *                     int i = parseInt(integerCacheHighPropValue);
         *                     i = Math.max(i, 127);
         *                     // Maximum array size is Integer.MAX_VALUE
         *                     h = Math.min(i, Integer.MAX_VALUE - (-low) -1);
         *                 } catch( NumberFormatException nfe) {
         *                     // If the property cannot be parsed into an int, ignore it.
         *                 }
         *             }
         *             high = h;
         *        //  重点============================================================
         *             cache = new Integer[(high - low) + 1];
         *             int j = low;
         *             for(int k = 0; k < cache.length; k++)
         *                 cache[k] = new Integer(j++);
         *
         *             // range [-128, 127] must be interned (JLS7 5.1.7)
         *             assert IntegerCache.high >= 127;
         *         }
         */
    }
}
