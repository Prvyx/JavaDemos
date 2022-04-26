import java.io.*;

/**
 * @program: JavaDemos
 * @description:跨平台的存储和恢复数据（从windows资源管理器看DataOutputStream输出的文件是乱码的，需要使用DataInputStream读取）
 * @author: Prvyx
 * @created: 2022/04/16 08:34
 */

public class StoreAndRecoverData {
    private static String path="C:\\Users\\呵\\Desktop\\linksCopy.txt";
    public static void main(String[] args) {
        try (DataOutputStream dataOutputStream=new DataOutputStream(new BufferedOutputStream(new FileOutputStream(path)));
             DataInputStream dataInputStream=new DataInputStream(new BufferedInputStream(new FileInputStream(path)))
        ){
            // DataOutputStream的输出是乱码的，因为DataOutputStream是面向字节的。需要用DataInputStream读取
            dataOutputStream.writeInt(123123);
            dataOutputStream.writeUTF("董帅博\n");
            dataOutputStream.writeDouble(123.1);
            // 在读取之前需把写入的流给关掉，否则抛异常
            dataOutputStream.close();

            System.out.println(dataInputStream.readInt());
            System.out.println(dataInputStream.readUTF());
            System.out.println(dataInputStream.readDouble());
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
