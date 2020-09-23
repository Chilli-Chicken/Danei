package com.test.XianChengTongXun;

import org.omg.CORBA.OBJ_ADAPTER;

import java.util.Arrays;
import java.util.Timer;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @param <T>
 *  Condition 实现了线程间通讯的更方便的操作 比如可以有多把锁
 */
class BlockContainerCondition<T> {
    private Object[] array;
    private int size;

    public BlockContainerCondition() {
        this(16);
    }

    public BlockContainerCondition(int cap) {
        array = new Object[cap];
    }

    private ReentrantLock lock = new ReentrantLock(false);
    private Condition producerCondition = lock.newCondition();
    private Condition consumerCondition = lock.newCondition();

    public void put(T t) {
        lock.lock();
        try {
            while (size == array.length)
                try {
                    producerCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            array[size] = t;
            size++;
            consumerCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }


    @Override
    public String toString() {
        return "BlockContainer{" +
                "array=" + Arrays.toString(array) +
                '}';
    }

    public  T take() {
        lock.lock();
        try {
            while (size == 0)
                try {
                    consumerCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            Object o = array[0];
            System.arraycopy(array, 1, array, 0, size - 1);
            size--;
            array[size] = null;
            producerCondition.signalAll();
            return (T) o;
        } finally {
            lock.unlock();
        }
    }
}

class Producer extends Thread {
    BlockContainerCondition<Object> blockContainerCondition;

    public Producer(BlockContainerCondition<Object> blockContainer) {
        this.blockContainerCondition = blockContainer;
    }

    @Override
    public void run() {
        int i = 1;
        while (true) {
            blockContainerCondition.put(i);
            i++;
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

class Consumer extends Thread {
    BlockContainerCondition<Object> blockContainerCondition;

    public Consumer(BlockContainerCondition<Object> blockContainer) {
        this.blockContainerCondition = blockContainer;
    }

    @Override
    public void run() {
        while (true) {
           Object o= blockContainerCondition.take();
            System.out.println(o.toString());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

public class ConditionTest {
    public static void main(String[] args) {
        BlockContainerCondition<Object> blockContainerCondition =new BlockContainerCondition<>();
        Producer producer=new Producer(blockContainerCondition);
        Consumer consumer=new Consumer(blockContainerCondition);
        producer.start();
        consumer.start();
    }
}
