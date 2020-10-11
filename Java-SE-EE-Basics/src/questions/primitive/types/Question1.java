package questions.primitive.types;

public class Question1 {
	
	// What will be result of this program?
	public static void main(String[] args) {
		byte b = 0;
		while(++b > 0);
		System.out.println(b);
		
		// 1. 127
		// 2. -128
		// 3. compilation error
		// 4. 0
		
		
		// Answer: 2. -128
	}
}
