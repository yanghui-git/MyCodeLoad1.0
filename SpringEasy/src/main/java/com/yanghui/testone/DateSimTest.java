package com.yanghui.testone;

import org.junit.Test;

import java.net.InetAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期与时间戳
 */
public class DateSimTest {


    @Test
    public void tranTime() {
        long test = System.currentTimeMillis();
        Date date = new Date(test);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("时间戳转具体日期");
        System.out.println(simpleDateFormat.format(date));
        System.out.println("4b43098369aa11eaac8e005056b93035".length());
    }


    @Test
    public void transTime() throws ParseException {
        System.out.println(new Date().getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ss = "2020-03-19 15:57:01";
        System.out.println(simpleDateFormat.parse(ss));
        Date date = simpleDateFormat.parse(ss);
        System.out.println(date.getTime());
    }


    @Test
    public void getIp() {
        InetAddress ia = null;
        try {
            ia = ia.getLocalHost();
            String localname = ia.getHostName();
            String localip = ia.getHostAddress();
            System.out.println("本机名称是：" + localname);
            System.out.println("本机的ip是 ：" + localip);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
