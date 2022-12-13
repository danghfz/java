import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;

/**
 * @author danghf
 * @version 1.0
 * @data 2022/12/6 16:40
 * @since 1.8
 */
public class Demo {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("E:\\MP4");
        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                String filename = path.getFileName().toString();
                System.out.println("pre: " + filename);
                String regex = "_";
                String[] split = filename.split(regex);
                if (split.length > 2) {
                    String newName = split[1] + regex + split[2];
                    File file = new File(String.valueOf(path));
                    System.out.println("new: " + newName);
                    boolean b = file.renameTo(new File("E:\\MP4\\" + newName));
                    System.out.println(split[1] + ": " + b);
                }

                return super.visitFile(path, attrs);
            }

        });
    }
}

class Demo2 {
    public static void main(String[] args) {
        int[] arr = {
                10, 9, 8, 7, 6, 5, 4, 3, 2, 1
        };
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void heapSort(int[] arr) {
        // 树化
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            heapInfo(arr, i);
        }
        // 排序
        int last = length - 1;
        for (int i = 0; i < length; i++) {
            swap(arr, 0, last);
            // 重新树化
            heapInsert(arr, 0,--last);
        }
    }

    /**
     * @param arr   数组
     * @param index 当前索引
     * @param last  数组末尾索引
     */
    protected static void heapInsert(int[] arr, int index, int last) {
        // 寻找最大子节点
        int left = index * 2 + 1;
        int right = left + 1;
        int max = -1;
        if (left > last) {
            // 没有子节点，结束
            return;
        }
        if (right > last) {
            max = left;
        }
        if (right <= last) {
            max = arr[left] > arr[right] ? left : right;
        }
        // 比较交换
        if (arr[index] < arr[max]) {
            swap(arr, index, max);
            heapInsert(arr, max, last);
        }
    }

    // 大顶堆
    protected static void heapInfo(int[] arr, int index) {
        // 第一个结点
        if (index == 0) {
            return;
        }
        // 父节点的索引为
        int parent = (index - 1) / 2;
        if (arr[parent] < arr[index]) {
            swap(arr, parent, index);
            heapInfo(arr, parent);
        }
    }

    protected static void swap(int[] arr, int index1, int index2) {
        int swap = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = swap;
    }
}
