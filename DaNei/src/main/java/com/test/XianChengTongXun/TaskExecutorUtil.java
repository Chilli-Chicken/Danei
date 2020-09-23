package com.test.XianChengTongXun;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Executor 线程池简单实现
 */

public class TaskExecutorUtil {
    static ThreadFactory threadFactory = new ThreadFactory() {
        AtomicInteger at = new AtomicInteger();

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "thread-pool-" + at.getAndIncrement());
        }
    };
    static BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(1);

    static ExecutorService doCreateThreadPoolExecutor() {
        return new ThreadPoolExecutor(
                1,//核心线程数
                1,//最大线程数
                60,//这个线程多长时间释放
                TimeUnit.SECONDS,//时间单位 秒
                workQueue,//工作队列
                threadFactory,//线程工厂
                new ThreadPoolExecutor.CallerRunsPolicy()//任务执行时需要的一个策略  谁提交的任务谁去执行
        );
    }

    static void close(ExecutorService executorService) {
        try {
            //死锁问题
            executorService.shutdown();//设定一个shutdown标记 并不是直接关闭 而是不在接受新的任务 但是正在执行的任务可以继续执行
            executorService.awaitTermination(5, TimeUnit.SECONDS);//等会那些未执行的任务
        } catch (InterruptedException e) {
            System.err.println("task Interrupted");
        } finally {
            if (!executorService.isTerminated()) {
                System.err.println("task not finish");//还是有些任务没有结束
            }
            executorService.shutdownNow();//关闭  不在接受新任务 并且尝试关闭正在执行的任务 返回任务队列
        }
    }

}
