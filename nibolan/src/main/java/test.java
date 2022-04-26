public class test {
	public static void main(String[] args) {
		String s = "9+(3-1)*3+10/2";
		RPNCalculator aa = new RPNCalculator();
		System.out.println(aa.toRPN(s));
		System.out.println(aa.calculate(s));
	}
}
