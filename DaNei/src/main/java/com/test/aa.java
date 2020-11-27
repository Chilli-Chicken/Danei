package com.test;

public class aa {
    public static void main(String[] args) {
        Integer[][] a ={{0,0},{1,1}};

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j <a[0].length; j++) {
                System.out.println("("+i+":"+j+")=》"+a[i][j]);
            }
        }
    }
}
