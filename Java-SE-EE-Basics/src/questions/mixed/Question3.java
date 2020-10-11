package questions.mixed;


interface In {
	public final static int VAR = 1;
}

public class Question3 implements In {

	// What will be result of this program?
	public static void main(String[] args) {
		int var = 4;
		test(++var, var);
		
		// 1. 12-11
		// 2. 9-9
		// 3. runtime error
		// 4. 11-10
		
		
		// Answer: 4. 11-10
	}
	
	static void test(int var, int var1) {
		var += VAR + var++;
		var1 += var1++;
		System.out.println(var + "-" + var1);
	}
}
