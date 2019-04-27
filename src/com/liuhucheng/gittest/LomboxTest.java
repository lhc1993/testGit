package com.liuhucheng.gittest;

import java.util.ArrayList;
import java.util.List;

public class LomboxTest {
    public static void main(String[] args) {
        Bean bean = new Bean();
        Bean bean1 = new Bean("zhangsan",20);
        bean.setName("lisi");
        List<String> arra = new ArrayList<String>();
        arra.add("nihao");
        System.out.println(arra.size());

        String [] s = null;
    }
}
