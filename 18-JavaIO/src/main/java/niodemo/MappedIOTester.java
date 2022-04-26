package niodemo;

import java.io.*;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @program: JavaDemos
 * @description:测试旧IO与nio的mappedByteBuffer的速度差异
 * 当你想写入时，推荐RandomAccessFile；当你只想读取时，并不推荐RandomAccessFile，推荐加上BufferedInputStream的FileInputStream
 * @author: Prvyx
 * @created: 2022/04/17 09:38
 */

public class MappedIOTester {
    private static int numOfInts=4000000;
    private static int numOfUbuffInts=200000;

    private abstract static class Tester{
        private String name;

        public Tester(String name) {
            this.name = name;
        }
        public void runTest()throws IOException{
            long start=System.nanoTime();
            test();
            double duration=System.nanoTime()-start;
            System.out.println(name+"的运行时间:"+duration/1e9+"s");
        }
        public abstract void test()throws IOException;
    }

    private static Tester[] testers={
            new Tester("Stream Write"){
                @Override
                public void test() throws IOException {
                    DataOutputStream outputStream=new DataOutputStream(
                            new BufferedOutputStream(
                                    new FileOutputStream("C:\\Users\\呵\\Desktop\\linksTemp.txt")));
                    for(int i=0;i<numOfInts;i++){
                        outputStream.writeInt(i);
                    }
                    outputStream.close();
                }
            },
            new Tester("Mapped Write"){
                @Override
                public void test() throws IOException{
                    FileChannel fc = new RandomAccessFile("C:\\Users\\呵\\Desktop\\linksTemp.txt", "rw")
                            .getChannel();
                    MappedByteBuffer buffer=fc.map(FileChannel.MapMode.READ_WRITE,0,numOfInts);
                    for(int i=0;i<numOfInts;i++){
                        buffer.put((byte) i);
                    }
                    fc.close();
                }
            },
            new Tester("Stream Read") {
                @Override
                public void test() throws IOException {
                    // 加上new BufferedInputStream加快速度，否则，跑的太慢
                    DataInputStream inputStream=new DataInputStream(
                            new BufferedInputStream(new FileInputStream("C:\\Users\\呵\\Desktop\\linksTemp.txt")));
                    for(int i=0;i<numOfInts;i++){
                        inputStream.readInt();
                    }
                    inputStream.close();
                }
            },
            new Tester("Mapped Read") {
                @Override
                public void test() throws IOException {
                    FileChannel fc=new FileInputStream("C:\\Users\\呵\\Desktop\\linksTemp.txt")
                            .getChannel();
                    // 不能把READ_ONLY改为READ_WRITE
                    // 原因：FileInputStream是只读的，FileChannel是通过FileInputStream得到的，FileChannel也是只读的
                    // 资料：https://www.cnblogs.com/zhengqun/p/4941928.html
                    IntBuffer intBuffer= fc.map(FileChannel.MapMode.READ_ONLY,0,numOfInts).asIntBuffer();
                    while (intBuffer.hasRemaining()){
                        intBuffer.get();
                    }
                    fc.close();
                }
            },
            new Tester("Stream Read/Write") {
                @Override
                public void test() throws IOException {
                    RandomAccessFile randomAccessFile=new RandomAccessFile("C:\\Users\\呵\\Desktop\\linksTemp.txt","rw");
                    randomAccessFile.writeInt(1);
                    System.out.println(randomAccessFile.length());
                    for(int i=0;i<numOfUbuffInts;i++){
                        randomAccessFile.seek(randomAccessFile.length()-4);
                        randomAccessFile.writeInt(randomAccessFile.readInt());
                        if(i<=10){
                            System.out.println("randomAccessFile.getFilePointer()="+randomAccessFile.getFilePointer());
                        }
                    }
                    System.out.println("randomAccessFile.length()="+randomAccessFile.length());
                    randomAccessFile.close();
                }
            },
            new Tester("Mapped Read/Write") {
                @Override
                public void test() throws IOException {
                    FileChannel fc=new RandomAccessFile("C:\\Users\\呵\\Desktop\\linksTemp.txt","rw")
                            .getChannel();
                    System.out.println("numOfUbuffInts="+numOfUbuffInts);
                    System.out.println("fc.size()="+fc.size());
                    IntBuffer intBuffer=fc.map(FileChannel.MapMode.READ_WRITE,0,fc.size()).asIntBuffer();
                    intBuffer.put(0);
                    for(int i=1;i<numOfUbuffInts;i++){
                        intBuffer.put(intBuffer.get(i-1));
                        if(i<=10){
                            System.out.println(intBuffer.position()+"-"+intBuffer.limit()+"-"+intBuffer.capacity());
                        }
                    }

                }
            }
    };

    public static void main(String[] args) {
        try {
            for(Tester tester:testers){
                tester.runTest();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
