package com.xuzimian.java.learning.jvm.oom.demo;

import java.util.ArrayList;
import java.util.List;

public class OOMTest {
    Byte[] array = new Byte[1024*1024];//1MB

    public static void main(String[] args) {
        List<OOMTest> list = new ArrayList<>();
        int count = 0;
        try {
            while (true) {
                list.add(new OOMTest());
                count = count +1;
            }
        }catch (Error e){
            System.out.println(String.format("- The program run by oom in %dM memory -",count));
            e.printStackTrace();
        }
    }
}
