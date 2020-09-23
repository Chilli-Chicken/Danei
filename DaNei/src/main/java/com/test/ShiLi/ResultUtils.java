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
    }
}
