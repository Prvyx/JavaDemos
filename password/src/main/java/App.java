import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("201916010709\t董帅博\t");
        long start=System.nanoTime();
        Vigenere vigenere = new Vigenere();
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入下列选择");
        System.out.println("1.加密");
        System.out.println("2.解密");
        int i = sc.nextInt();
        switch (i){
            case 1: vigenere.encode(); break;
            case 2: vigenere.decode(); break;
               
        }

        long end=System.nanoTime();
        System.out.println("程序运行时间："+(end-start)+"ns");

        // String s = "ZSIIOROY";
        // String v = "ok";

    }



}
