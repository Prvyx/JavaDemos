package compress;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @program: JavaDemos
 * @description:使用GZIP压缩文本文件的demo（我现在使用GZIP压缩不了图片。。。）
 * 原因：https://cloud.tencent.com/developer/article/1703421
 * @author: Prvyx
 * @created: 2022/04/17 16:33
 */

public class GzipDemo {
    private static String path1="C:\\Users\\呵\\Desktop\\test3.txt";
    private static String path2="C:\\Users\\呵\\Desktop\\test3.gz";
    private static void compressByGZIP(File source,File target)throws IOException {
        BufferedInputStream bis=new BufferedInputStream(new FileInputStream(source));
        BufferedOutputStream bos=new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream(target)));

        int len;
        byte[] bytes=new byte[2048];
        while ((len=bis.read(bytes))!=-1){
            bos.write(bytes,0,len);
        }
        bis.close();
        bos.close();

        // 读取GZIP压缩后的txt文件
        BufferedReader bis2=new BufferedReader(
                new InputStreamReader(new GZIPInputStream(new FileInputStream(target)),"GBK"));
        String s;
        while ((s=(bis2.readLine()))!=null){
            System.out.println(s);
        }
    }
    private static void compressByGZIP(String source,String target)throws IOException{
        compressByGZIP(new File(source),new File(target));
    }

    public static void main(String[] args) {
        try {
            compressByGZIP(path1,path2);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
