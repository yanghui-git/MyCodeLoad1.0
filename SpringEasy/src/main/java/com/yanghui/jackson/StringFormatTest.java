package com.yanghui.jackson;

import org.junit.Test;

import java.util.Date;

public class StringFormatTest {


    /**
     * String.format()
     */
    @Test
    public void testTwo(){
        String str=null;
        str=String.format("hi,这是测试人 %s %s%s","yh","yh","yyhhhh");
        System.out.println(str);
        str=String.format("boolean测试%b",1==2);
        System.out.println(str);
        str=String.format("整数 %d",12);
        System.out.println(str);
        //四舍五入
        str=String.format("浮点型 %.4f",12.99612);
        System.out.println(str);
        Date date=new Date();
        //c的使用
        System.out.printf("全部日期和时间信息：%tc%n",date);
        //f的使用
        System.out.printf("年-月-日格式：%tF%n",date);
        //d的使用
        System.out.printf("月/日/年格式：%tD%n",date);
        //r的使用
        System.out.printf("HH:MM:SS PM格式（12时制）：%tr%n",date);
        //t的使用
        System.out.printf("HH:MM:SS格式（24时制）：%tT%n",date);
        //R的使用
        System.out.printf("HH:MM格式（24时制）：%tR",date);

    }


}
