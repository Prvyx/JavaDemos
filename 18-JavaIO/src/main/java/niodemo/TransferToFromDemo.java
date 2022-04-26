package niodemo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @program: JavaDemos
 * @description:将两个通道直接相连，非常方便两个通道的复制
 * @author: Prvyx
 * @created: 2022/04/16 15:18
 */

public class TransferToFromDemo {
    public static void main(String[] args) {
        try {
            FileChannel fc1=new FileInputStream("C:\\Users\\呵\\Desktop\\linksCopy.txt").getChannel();
            FileChannel fc2=new FileOutputStream("C:\\Users\\呵\\Desktop\\links.txt").getChannel();
//            fc1.transferTo(0,fc1.size(),fc2);
            fc2.transferFrom(fc1,0,fc1.size());
            fc1.close();
            fc2.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
