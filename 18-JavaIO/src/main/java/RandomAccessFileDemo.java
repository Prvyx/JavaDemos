import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 * @program: JavaDemos
 * @description:关于RandomAccessFile类的demo
 * @author: Prvyx
 * @created: 2022/04/15 22:06
 */

public class RandomAccessFileDemo {
    public static void main(String[] args) {
        try {
            RandomAccessFile file=new RandomAccessFile("C:\\Users\\呵\\Desktop\\test.txt","rw");
            Scanner scanner=new Scanner(System.in);
            String str;
            System.out.println("文件指针位置："+file.getFilePointer());
            // 将文件指针置于文件末尾
            file.seek(file.length());
            System.out.println("将文件指针移动至末尾，位置："+file.getFilePointer());
            while (!(str=scanner.nextLine()).equals("end")){
                file.writeBytes(str+"\n");
            }

            System.out.println("写入后文件指针的位置："+file.getFilePointer());

            System.out.println("从头读取");
            // 将文件指针置于文件头（即0）
            file.seek(0);
            int len;
            byte[] bytes=new byte[1024];
            StringBuilder stringBuilder=new StringBuilder();
            while ((len=file.read(bytes))!=-1){
                stringBuilder.append(new String(bytes,0,len));
            }

            System.out.println(stringBuilder);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
