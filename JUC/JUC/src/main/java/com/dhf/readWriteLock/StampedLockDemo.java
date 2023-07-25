package com.dhf.readWriteLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * @author 党
 * @version 1.0
 * 2022/9/12   14:15
 * 邮戳锁
 */
public class StampedLockDemo {
    private static class Resource{
        private static int num = 20;
        StampedLock stampedLock = new StampedLock();

        public void write() { // ReentrantReadWriteLock的写
            long stamp = stampedLock.writeLock();
            System.out.println(Thread.currentThread().getName() + "准备修改");
            try {
                num = num + (int) (Math.random() * 10);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                stampedLock.unlockWrite(stamp);
            }
            System.out.println(Thread.currentThread().getName() + "结束修改");
        }
        // 悲观读，都没有完成的时候，无法获取写锁
        public void read() { // ReentrantReadWriteLock 的读
            long stamp = stampedLock.readLock();
            try {
                System.out.println(Thread.currentThread().getName() + "正在读取");
                TimeUnit.MILLISECONDS.sleep(500);
                for (int i = 0; i < 3; i++) {
                    System.out.println(Thread.currentThread().getName() + ": " + num);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                stampedLock.unlockRead(stamp);
            }
        }

        // 乐观读，读的过程中可以写锁介入
        public void tryOptimisticRead(){
            // 乐观锁
            long stamp = stampedLock.tryOptimisticRead();
            int res = num;
            // 乐观认为，读取过程中，没有线程修改num
            boolean validate = stampedLock.validate(stamp);
            System.out.println("after 4s stampedLock.validate(stamp)【true无修改,false有修改】\t" + validate);
            for (int i = 0; i < 4; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"\t正在读取 " +i+"次"+
                        " stampedLock.validate: "+stampedLock.validate(stamp));
            }
            if (!stampedLock.validate(stamp)){ // 有修改
                System.out.println("有人修改，升级悲观锁");
                this.read();
            }
            System.out.println(Thread.currentThread().getName()+" finish " + num);
        }

    }


    public static void main(String[] args) throws Exception{
        Resource resource = new Resource();
        new Thread(() -> {
            resource.tryOptimisticRead();
        },"read").start();
        TimeUnit.SECONDS.sleep(6);
        new Thread(resource::write,"write").start();
    }

    private static void ReentrantLockDemo() throws InterruptedException {
        Resource resource = new Resource();
        new Thread(resource::read,"readThread").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(resource::write,"writeThread").start();
        TimeUnit.SECONDS.sleep(1);
        resource.read();
    }
}
