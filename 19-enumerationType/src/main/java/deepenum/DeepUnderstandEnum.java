package deepenum;

/**
 * @program: JavaDemos
 * @description:enum的深入理解
 * 资料：https://www.bilibili.com/video/BV1A34y1v7aL
 * @author: Prvyx
 * @created: 2022/04/19 13:32
 */
enum Color {
    RED("我是红色"),
    BLUE("我是蓝色"),
    YELLOW("我是黄色"),
    GREEN("我是绿色")
    ;
    private String description;
    Color(String description) {
        this.description=description;
    }
    public String getDescription(){
        return description;
    }
}
public class DeepUnderstandEnum {
    public static void main(String[] args) {
        for(Color color:Color.values()){
            System.out.println(color+" description:" +color.getDescription());
        }
    }
}
/*Output:
RED description:我是红色
BLUE description:我是蓝色
YELLOW description:我是黄色
GREEN description:我是绿色
 */