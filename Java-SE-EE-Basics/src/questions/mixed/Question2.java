package questions.mixed;

public class Question2 {
	
	final int x = 2;
	
	// What will be result of this program?
	public static void main(String[] args) {
		int x = 0;               // 1
		for (x = 5; x < 7; x++); // 2
		System.out.println(x);
		
		// 1. 7
		// 2. 2
		// 3. compilation error at line 1
		// 4. compilation error at line 2
		
		
		// Answer: 1. 7
	}
}
