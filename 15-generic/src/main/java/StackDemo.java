/**
 * @program: JavaDemos
 * @description:堆栈类的demo
 * @author: Prvyx
 * @created: 2022/04/13 14:19
 */

public class StackDemo <T>{
    private static class Node<U>{
        private U item;
        private Node<U> next;

        public Node() {
        }

        public Node(U item, Node<U> next) {
            this.item = item;
            this.next = next;
        }

        Boolean isEnd(){
            return item==null&&next==null;
        }
    }

    private Node<T> top=new Node<>();
    public void push(T item){
        top=new Node<>(item,top);

    }
    public T pop(){
        T rs=top.item;
        if(!top.isEnd()){
            top=top.next;
        }
        return rs;
    }

    public static void main(String[] args) {
        StackDemo<String> ss=new StackDemo<>();
        for(String s:"dong prvyx bo".split(" ")){
            ss.push(s);
        }
        String s;
        while ((s=ss.pop())!=null){
            System.out.println(s);
        }

    }
}
