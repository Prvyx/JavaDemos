import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Stack<T> {
	private List<T> stack = new ArrayList<T>();

	public void showStack() {
		if (!this.stack.isEmpty()) {
			Iterator<T> output = this.stack.iterator();
			while (output.hasNext()) {
				System.out.print(output.next() + ", ");
			}
			System.out.println();
		} else {
			System.out.println("empty stack");
		}
	}

	public void push(T t) {
		stack.add(0, t);// add on the top
	}

	public T pop() {
		if (!stack.isEmpty()) {
			return stack.remove(0);// delete the first item
		} else {
			return null;
		}
	}
	public T peek(){
		if(stack.isEmpty())return null;
		return stack.get(0);//get the top item
	}
	
	public boolean isEmpty(){
		return stack.isEmpty();
	}
}
/*
public class StackDemo {
	public static void main(String[] args) {
		Stack<String> stack = new Stack<String>();
		stack.push("1");
		stack.push("2");
		stack.push("3");
		stack.showStack();
		stack.pop();
		stack.showStack();
	}
}*/
