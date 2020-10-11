package questions.mixed;


class Test {
	public int test = 1;
	
	public void testing(Test t) {
		t.test+=10;
	}
}

public class Question10 {

	// What will be result of this program?
	public static void main(String[] args) {
		Test test = new Test();
		test.test = 15;
		test.testing(test);
		System.out.println(test.test);
		
		// Answer: 25
	}

}
