package com.lgs.springboot.demo.ControllerTest;

import java.io.File;

/***
 * 递归   当前方法内调用自己的这种方式----> return 5+4+3+2+1;
 */
public class recursionTest {
    /*****
     *1. 指定要打印的目录File对象
     * 	2. 调用getFileAll()方法
     * 		2.1 获取指定目录中的所有File对象
     * 		2.2 遍历得到每一个File对象
     * 		2.3 判断当前File 对象是否是目录
     * 			判断结果为true，说明为目录，通过递归，再次调用步骤2的getFileAll()方法
     * 			判断结果为false，说明是文件，打印文件的路径
     */
    public static void main(String[] args) {
        File file = new File("E:\\111");
        getFile(file);


    }

    private static void getFile(File file) {
       // 获取目录下当前文件以及文件对象，只要拿到了文件对象，那么就可以获取其中想要的信息
        File[] files = file.listFiles();
        //files.for -->
        for (File file1 : files) {
            //
            if (file1.isDirectory()) {
                getFile(file1);
            }else{
                System.out.println(file1);
            }
        }
    }

   /* //递归计算10以内的和
    public static void main(String[] args) {
        int num = 10;
        int sum = getsum(num);
        System.out.println(sum);

    }

    private static int getsum(int num) {
        if (num == 1) {
            return 1;
        }
        int i = num + getsum(num - 1);
        return i;
    }
*/

}
