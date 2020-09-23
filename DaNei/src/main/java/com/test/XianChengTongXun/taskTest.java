package com.test.XianChengTongXun;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 线程池简单测试
 */
public class taskTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = TaskExecutorUtil.doCreateThreadPoolExecutor();
        Future future = threadPool.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                TimeUnit.SECONDS.sleep(2);
                return new Random().nextInt();
            }
        });
        Future future2 = threadPool.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                TimeUnit.SECONDS.sleep(2);
                return new Random().nextInt();
            }
        });
        Future future3 = threadPool.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                TimeUnit.SECONDS.sleep(2);
                return new Random().nextInt();
            }
        });
        System.out.println("future->" + future.isDone());
        Integer result = (int) future.get();
        Integer result2 = (int) future2.get();
        Integer result3 = (int) future3.get();
        System.out.println("result->" + result);
        System.out.println("result2->" + result2);
        System.out.println("result3->" + result3);
        System.out.println("future->" + future.isDone());
        TaskExecutorUtil.close(threadPool);
    }
}
