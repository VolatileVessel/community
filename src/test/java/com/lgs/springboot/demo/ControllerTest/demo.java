package com.lgs.springboot.demo.ControllerTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class demo {
    public static void main(String[] args) {
        long totalMilliSeconds = System.currentTimeMillis();

        DateFormat dateFormatterChina = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM);//格式化输出
        TimeZone timeZoneChina = TimeZone.getTimeZone("Asia/Shanghai");//获取时区 这句加上，很关键。
        dateFormatterChina.setTimeZone(timeZoneChina);//设置系统时区
        long totalSeconds = totalMilliSeconds / 1000;

        //求出现在的秒
        long currentSecond = totalSeconds % 60;

        //求出现在的分
        long totalMinutes = totalSeconds / 60;
        long currentMinute = totalMinutes % 60;

        //求出现在的小时
        long totalHour = totalMinutes / 60;
        long currentHour = totalHour % 24;

        //显示时间
        System.out.println("总毫秒为： " + totalMilliSeconds);
        System.out.println(currentHour + ":" + currentMinute + ":" + currentSecond + " GMT");


        Date nowTime = new Date(System.currentTimeMillis());
        System.out.println(nowTime);
        System.out.println(System.currentTimeMillis());
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String retStrFormatNowDate = sdFormatter.format(nowTime);
        try {
            Date parse = sdFormatter.parse(retStrFormatNowDate);
            System.out.println("Date "+parse);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(retStrFormatNowDate);


        System.out.println("-----------------------------");



      /*  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//一般网上的转换是没有中间new Long（timeStamp）,因为他们都是精确到毫秒的时间戳，不用再乘以1000进行转换

        Date date = new Date();
        String dareString = simpleDateFormat.format(date);
        System.out.println(dareString);*/
        //System.out.println(System.currentTimeMillis());
        /*int i=1 ; int j=0; int  m; int x;
        for (m = 1; m < 24; m++) {
            x=i;
            System.out.println(""+i );
            i=i+j;
            j=x;

        }*/
/*
int [] m=new int[24];
       int i;
       m[0]=m[1]=1;
        for ( i=0 ;i<24;i++) {
            if(i==0||i==1){
                System.out.println(""+i+1+"个");
            }else{
                m[i]=m[i-1]+m[i-2];
                System.out.println(""+m[i]+"个");

            }
        }
        System.out.println(m[0]);
        System.out.println(m[1]);
        System.out.println(m[23]);

*/
 /*for (int i1 : m) {
            System.out.println(i1);
        }

        return ;*/
    }
}
