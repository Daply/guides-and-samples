package questions.mixed;


public class Question12 {

	// What will be result of this program?
	public static void main(String[] args) {
		int a = 6;
		Integer b = a;
		int[] c = new int[a];
		test(a, b, c);
		System.out.println(a + "  " + b + "  " + c[0]);
		// Answer: 6  6  2
	}

	
	static void test(int a, Integer b, int[] c) {
		a+=2;
		b+=2;
		c[0]+=2;
	}
}
