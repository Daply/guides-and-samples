package questions.strings;


public class Question8 {

	// What will be result of this program?
	public static void main(String[] args) {
		String s1 = "test1";
		String s2 = "test1";
		System.out.println("Result: " + s1 == s1);
		System.out.println("Result: " + s1 == s2);
		System.out.println(s1 == s2);
		
		// Answer:  false
		//          false
		//          true
	}

}
