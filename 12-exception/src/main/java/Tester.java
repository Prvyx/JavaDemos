import java.util.Random;

/**
 * @program: JavaDemos
 * @description:自定义异常demo
 * @author: Prvyx
 * @created: 2022/04/07 16:55
 */
class ScoreException extends Exception{
    public ScoreException(String message) {
        super(message);
    }
}
public class Tester {
    public static void main(String[] args) {
        int score=new Random().nextInt(200);
        try {
            if(score>100){
                throw new ScoreException("成绩超过100分，错误");
            }
        }catch (ScoreException e){
            e.printStackTrace();
        }
    }
}
