package com.test.ShiLi;

import org.json.JSONObject;

import java.util.function.Supplier;

public class ResultUtils {
  private static   Supplier<JSONObject> supplier= JSONObject :: new;

  public static JSONObject SUCCESS(){
      JSONObject jsonObject =supplier.get();
      jsonObject.put("msg","成功");
      jsonObject.put("code","000000");

      return jsonObject;
  }
    public static JSONObject Fall(){
        JSONObject jsonObject =supplier.get();
        jsonObject.put("msg1","成功");
        jsonObject.put("code1","000000");

        return jsonObject;
    }
    public static void main(String[] args) {
        System.out.println(SUCCESS().toString());
        System.out.println(Fall());

        JSONObject a =new JSONObject();
        a.put("mchId","JY00010002");
        a.put("mchTransNo","pf18234233d21131172a40");
        a.put("amount","0.01");
        a.put("payType","04");
        a.put("desc","标题");
        a.put("notifyUrl","http://112.74.115.152:39950/test");
        a.put("returnUrl","http://www.pfokpay.com");
        a.put("sign","D81ACED3E362EB9B247E9C79DC947D8F");
        a.put("openid","opQ0l5jMjYLo7u-6zlYcKJtzxRkw");
        a.put("is_web","1");
        System.out.println(a.toString());
    }
}
