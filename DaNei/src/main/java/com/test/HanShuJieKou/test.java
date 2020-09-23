package com.test.HanShuJieKou;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 函数接口用 @FunctionalInterface 修饰
 * 有且只有一个抽象方法，但可以有多个default修饰的接口，也可以有多个静态接口
 * 允许java.lang.Object中的public方法
 */
@FunctionalInterface
interface defu{
    default void a(){}
    static void b(){}
    void c(String a);
    //java.lang.Object中的public方法
    boolean equals(Object var1);
}

interface shuruFanhui<T,R>{
    R fanhui(T t);
}


public class test {
    /**
     * java8中提供了4种日常常用的函数接口
     */
//消费型接口
    Comparable<String> comparable=new Comparable<String>() {
        @Override
        public int compareTo(String o) {
            return 0;
        }
    };

//函数式 有输入又返回
    Function<String,Integer> function=new Function<String, Integer>() {
        @Override
        public Integer apply(String s) {
            return 1;
        }
    };
//判定试
    Predicate<String> predicate=new Predicate<String>() {
        @Override
        public boolean test(String s) {
            return false;
        }
    };
    //供应是
   Supplier supplier=new Supplier() {
        @Override
        public Object get() {
            return new Object();
        }
    };

    public static void main(String[] args) {
        Comparable<String> comparable=new Comparable<String>() {
            @Override
            public int compareTo(String o) {
                return 0;
            }
        };
        new defu() {
            @Override
            public void c(String a) {
                System.out.println("=====");
                System.out.println(a);
            }
        }.c("ppp");

        List<String> a =new ArrayList();
        a.add("a");
        a.add("b");
        a.forEach(e -> new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
        a.forEach(e ->{
            System.out.println(e);
        });
        a.forEach(e -> System.out.println(e));
        a.forEach(System.out::println);

        Comparator comparable1=new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        };
    }

    shuruFanhui<String,Integer> sss =new shuruFanhui<String, Integer>() {
        @Override
        public Integer fanhui(String s) {
            return null;
        }
    };
}
