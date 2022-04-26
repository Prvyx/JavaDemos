package com.prvyx.utils;

import java.io.IOException;

/**
 * @program: JavaDemos
 * @description:打开windows的app，懒人模式
 * java打包：https://www.php.cn/java/base/435429.html
 * java 程序设置自启动方法：https://jingyan.baidu.com/article/ff42efa957a062819f220257.html
 * java 程序关闭windows app ：https://zhidao.baidu.com/question/324679811.html
 * 命令行开无线网：https://jingyan.baidu.com/article/19020a0a107b75529d2842db.html
 * @author: Prvyx
 * @created: 2022/04/19 23:08
 */

public class StartUp {

    public static void openWindowsApp(String appPath)throws IOException {
        Runtime.getRuntime().exec(appPath);
    }
    public static void closeWindowsApp(String cmd)throws IOException{
        Runtime.getRuntime().exec(cmd);
    }
    public static void runCmd(String cmd)throws IOException{
        Runtime.getRuntime().exec(cmd);
    }
    public static void main(String[] args) {
        try {
//            runCmd("netsh wlan set hostednetwork mode=allow STU_HAUT");
//            openWindowsApp("C:\\Users\\呵\\AppData\\Local\\17a23497f96eeda002a216197156195c\\17a23497f96eeda002a216197156195c.exe");
            openWindowsApp("C:\\Program Files\\JetBrains\\IntelliJ IDEA 2021.2\\bin\\idea64.exe");
            openWindowsApp("C:\\Program Files (x86)\\Tencent\\QQMusic\\QQMusic.exe");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
