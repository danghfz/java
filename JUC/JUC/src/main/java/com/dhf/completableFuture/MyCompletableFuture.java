package com.dhf.completableFuture;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.val;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 党
 * @version 1.0
 * 2022/8/5   9:31
 */
public class MyCompletableFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 不推荐 new
        // Creates a new incomplete[不完美的] CompletableFuture.
        CompletableFuture<Object> objectCompletableFuture = new CompletableFuture<>();

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println("runAsync  runnable");
        }); // Runnable 接口
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CompletableFuture<Void> voidCompletableFuture1 = CompletableFuture.runAsync(() -> {// Runnable 接口
            System.out.println("runAsync  runnable");

        }, executorService); // Runnable 接口, Executors线程池

        CompletableFuture<Object> objectCompletableFuture1 = CompletableFuture.supplyAsync(() -> {
            return "hello CompletableFuture";
        });
        System.out.println(objectCompletableFuture1.get()); // 获取返回值
        CompletableFuture<Object> objectCompletableFuture2 = CompletableFuture.supplyAsync(() -> {
            return "hello CompletableFuture";
        }, executorService);
        System.out.println(objectCompletableFuture2.get()); // 获取返回值
        executorService.shutdown();

    }
}

class MyCompletableFuture2 { // whenComplete 回调
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CompletableFuture<String> objectCompletableFuture;
        try {
            objectCompletableFuture = CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String result = "hello";
                return result;
            }, executorService).whenComplete((result, exception) -> {
                System.out.println(executorService);
                System.out.println(executorService);
                if (exception == null) { // 没有异常
                    System.out.println("没有异常，得到结果");
                    System.out.println(Thread.currentThread().getName() + ":whenComplete:" + result);
                }
            }).exceptionally(exception -> { // 异常处理
                System.out.println("异常");
                return null;
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }


        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "i:" + i);
        }
        // 默认线程池 ForkJoinPool 在主线程结束后，会自动关闭线程池，类型守护线程
    }
}

class MyCompletableFuture3{
    public static void main(String[] args) {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        });
        System.out.println(stringCompletableFuture.join()); // 获取返回值
    }
}

class MyCompletableFutureAPI{
    public static void main(String[] args) throws Exception{
        // 1. 获得结果和触发计算
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            return "abc";
        });
        // 获取结果
//        completableFuture.get(); // 抛出异常
//        completableFuture.get(2L, TimeUnit.SECONDS); // 超时时间
//        completableFuture.join(); // 等待结果 没有异常
//        completableFuture.getNow("dhf");  // 获取结果，没有结果时，返回默认值
        // 触发计算
        boolean complete = completableFuture.complete("hello"); // 触发计算，打断get或join方法
        System.out.println(completableFuture.get());

        // 2. 对计算结果进行处理
        String completableFuture2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).thenApply((result) -> {
            return result + 2;
        }).get();
        System.out.println(completableFuture2);

        val handle = CompletableFuture.supplyAsync(() -> {
            return "hello";
        }).handle((result, exception) -> { // handle可以带这异常
            if (exception == null) {
                System.out.println(result);
            } else {
                exception.printStackTrace();
            }
            return "hello";
        });
        System.out.println(handle.get());

        // 3 消费处理结果
        // null
        System.out.println(CompletableFuture.supplyAsync(() -> "result").thenRun(()->{}).join());
        // null
        System.out.println(CompletableFuture.supplyAsync(() -> "result").thenAccept(r->{}).join());
        // result
        System.out.println(CompletableFuture.supplyAsync(() -> "result").thenApply(r->r).join());

        // 4 对计算速度进行选择

        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            return "playA";
        });
        CompletableFuture<String> completableFuture3 = CompletableFuture.supplyAsync(() -> "playB");
        String winner = completableFuture1.applyToEither(completableFuture3, (result) -> {
            return result + " is win";
        }).join();

        // 5 对计算结果进行合并
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> 1);
        CompletableFuture<Integer> integerCompletableFuture2 = CompletableFuture.supplyAsync(() -> 2);
        // 先完成的要等待后完成的结果
        Integer join = integerCompletableFuture.thenCombine(integerCompletableFuture2, (result, result2) -> {
            return result + result2;
        }).join();
        System.out.println(join);
    }
}
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
class Student {
    private String name;
    private Integer age;

    public static void main(String[] args) {
        Student student = new Student();
        student.setName("张三").setAge(18); // 链式编程
    }
}