import java.util.Scanner;

public class Vigenere {

    public char temp [][] = new char[26][26];
    Vigenere(){
        char c = 'A';
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                 char z = (char) (c+j);
                 if(z>'Z') z=(char) (z-26);
                 temp[i][j] = z;
            }
            c = (char) (c + 1);
        }

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                System.out.print(temp[i][j]);
            }

            System.out.println();
        }
        System.out.println("Vigenere密码表初始化完成");
        System.out.println();

    }

    public void encode() {
        Scanner scc = new Scanner(System.in);
        System.out.println("请输入明文");
        String s = scc.next();
        System.out.println("请输入密钥");
        String v = scc.next();

        s = s.toUpperCase();
        v = v.toUpperCase();
        int length = s.length();
        int l = v.length();
        char []sc = s.toCharArray();
        char [] vc = v.toCharArray();
        char [] key= new char[length];
        char [] code = new char[length];
        for(int i =0;i<length;i++){
             key[i]= vc[i%l];
        }
        System.out.println("密钥为"+String.valueOf(key));
         for(int i =0;i<length;i++){
            code[i] = temp[sc[i]-'A'][key[i]-'A'];
         }
         
        System.out.println("密文为"+String.valueOf(code));

     }

     public void decode(){
        Scanner scc = new Scanner(System.in);
        System.out.println("请输入秘文");
        String s = scc.next();
        System.out.println("请输入密钥");
        String v = scc.next();
        s = s.toUpperCase();
        v = v.toUpperCase();
        int length = s.length();
        int l = v.length();
        char [] sc = s.toCharArray();
        char [] vc = v.toCharArray();
        char [] key= new char[length];
        char [] code = new char[length];
        for(int i =0;i<length;i++){
            key[i]= vc[i%l];
       }
       for(int i =0;i<length;i++){
           int tem = sc[i]-key[i];
           if(tem<0) tem = tem+26;

           code[i] = temp[tem][0];
       }
       System.out.println("明文为"+String.valueOf(code));

     }
    }
    

    

