import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author danghf
 * @version 1.0
 * @data 2022/12/1 15:55
 * @since 1.8
 */
public class Test {
    //
    public static int[] mergeSort(int[] arr){
        // 分数组
        if (arr.length < 2){
            return arr;
        }
        int half = arr.length / 2;
        // [0, half - 1]
        int[] left = Arrays.copyOfRange(arr, 0, half);
        int[] right = Arrays.copyOfRange(arr, half, arr.length);
        // 左边进行排序
        int[] ints = mergeSort(left);
        int[] ints1 = mergeSort(right);
        return merge(ints,ints1);
    }
    public static int[] merge(int[] arr1,int[] arr2){
        int[] res = new int[arr1.length + arr2.length];
        int left = arr1.length;
        int right = arr2.length;
        int l = 0, r = 0,index = 0;
        // 都没有结束
        while (l < left && r < right){
            if (arr1[l] <= arr2[r]){
                res[index++] = arr1[l++];
            }else {
                res[index++] = arr2[r++];
            }
        }
        // 有一个结束，不可能同时结束
        if (l >= left){
            while (r < right){
                res[index++] = arr2[r++];
            }
        }else {
            // r >= right
            while (l < left){
                res[index++] = arr1[l++];
            }
        }
        return res;
    }
    public static void main(String[] args) {
        int[] arr = {9,8,7,6,5,4,3,2,1};
        int[] ints = mergeSort(arr);
        System.out.println(Arrays.toString(ints));
    }
}
