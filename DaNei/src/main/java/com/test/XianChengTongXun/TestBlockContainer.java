package com.test.XianChengTongXun;

import java.util.Arrays;
import java.util.Random;

/**
 *
 *
 * 线程间通讯 wait notifyAll notify
 * @param <T>
 */
class BlockContainer<T> {
    private Object[] array;
    private int size;

    public BlockContainer() {
        this(16);
    }

    public BlockContainer(int cap) {
        array = new Object[cap];
    }

    public synchronized void put(T t) {
        while (size == array.length)
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        array[size] = t;
        size++;
        this.notifyAll();
    }


    @Override
    public String toString() {
        return "BlockContainer{" +
                "array=" + Arrays.toString(array) +
                '}';
    }

    public synchronized T take() {
        while (size == 0)
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        Object o = array[0];
        System.arraycopy(array, 1, array, 0, size - 1);
        size--;
        array[size] = null;
        this.notifyAll();
        return (T) o;


    }
}

public class TestBlockContainer {
    public static void main(String[] args) {
        TestBlockContainer testBlockContainer = new TestBlockContainer();
        testBlockContainer.test();

    }

    public void test() {
        BlockContainer<Integer> blockContainer = new BlockContainer<>(3);

        Runnable runnable = () -> {
            for (int i = 0; i < 4; i++) {
                blockContainer.put((int) (Math.random() * 1000));
            }
        };



        Thread thread = new Thread(runnable);
        thread.start();
        try {
            thread.join(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        while (thread.isAlive()) {
//            try {
//                Thread.yield();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        System.out.println(blockContainer.toString());

        try {
            Thread.sleep(5000);
            blockContainer.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(blockContainer.toString());

    }


}
