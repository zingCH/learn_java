/**
 * <b>工程名：</b>week04<br/>
 * <b>包  名：</b>org.study<br/>
 * <b>文件名：</b>Homework03.java<br/>
 * <b>日  期：</b>2021/05/30<br/>
 */
package org.study;

/**
 * <b>类  名：</b>Homework03<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>qchang<br/>
 * <b>创建时间：</b>2021/05/30<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b><br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0.0 <br/>
 */

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * 一个简单的代码参考：
 */
public class Homework03 {


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        long start=System.currentTimeMillis();
        int result = 0;
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        //1.线程池
        //ThreadPoolExecutor threadPool = getThreadPool();
        //Future<Integer> integerFuture = threadPool.submit(()->sum());
        //result = integerFuture.get(); //这是得到的返回值

        //2.join
        //int[] resultArr = new int[1];
        //Thread thread = new Thread(()->resultArr[0]=sum());
        //thread.start();
        //thread.join();
        //result = resultArr[0];

        //3.wait/notify(synchronized)
        //int[] resultArr = new int[1];
        //Thread thread = new Thread(()->{
        //    synchronized (resultArr){
        //        resultArr[0]=sum();
        //        resultArr.notify();
        //    }
        //});
        //synchronized (resultArr){
        //    thread.start();
        //    //等待
        //    resultArr.wait();
        //}
        //result = resultArr[0];

        //4.标记
        //AtomicBoolean isOk = new AtomicBoolean(false);
        //int[] resultArr = new int[1];
        //Thread thread = new Thread(()->{
        //    resultArr[0]=sum();
        //    isOk.compareAndSet(false,true);
        //});
        //thread.start();
        //while (!isOk.get()){ }
        //result = resultArr[0];

        //5.Lock
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
        int[] resultArr = new int[1];
        Thread thread = new Thread(()->{
            reentrantLock.lock();
            try{
                resultArr[0]=sum();
                condition.signal();
            }finally {
                reentrantLock.unlock();
            }
        });
        thread.start();
        reentrantLock.lock();
        try{
            condition.await();
            result = resultArr[0];
        }finally {
            reentrantLock.unlock();
        }


        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 然后退出main线程
        //threadPool.shutdownNow();

    }


    private static ThreadPoolExecutor getThreadPool(){
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1,
                Runtime.getRuntime().availableProcessors(),
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(100),
                new ThreadFactory() {
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setName("Custom-Thread-");
                        return thread;
                    }
                });
        return threadPool;
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
