package com.test.HanShuJieKou;

import sun.awt.geom.AreaOp;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class Stream {

    public static void main(String[] args) {
        List<Integer> li= Arrays.asList(1,2,3,4,5,6);
//        li.stream().forEach(System.out::println);
       ;
        System.out.println(  li.stream().reduce(10,(a, b)->a*b));
    }
}
