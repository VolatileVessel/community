package com.lgs.springboot.demo.controller;

public class demo {
    public static void main(String[] args) {
        int i=1 ; int j=0; int  m; int x;
        for (m = 1; m < 24; m++) {
            x=i;
            System.out.println(""+i );
            i=i+j;
            j=x;

        }
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
