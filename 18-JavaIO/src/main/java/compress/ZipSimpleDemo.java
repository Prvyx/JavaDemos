package compress;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @program: JavaDemos
 * @description:使用zip压缩单个图片
 * @author: Prvyx
 * @created: 2022/04/17 17:35
 */

public class ZipSimpleDemo {
    private static String path1="C:\\Users\\呵\\Desktop\\派大星.png";
    private static String path2="C:\\Users\\呵\\Desktop\\test.zip";
    public static void main(String[] args)throws Exception {
        BufferedInputStream bis=new BufferedInputStream(new FileInputStream(path1));
        ZipOutputStream zos=new ZipOutputStream(new FileOutputStream(path2));
        zos.putNextEntry(new ZipEntry("派大星.png"));
        BufferedOutputStream bos=new BufferedOutputStream(zos);

        int len;
        byte[] bytes=new byte[2048];
        while ((len=bis.read(bytes))!=-1){
            bos.write(bytes,0,len);
            bos.flush();
        }
        bis.close();
        bos.close();
    }
}
