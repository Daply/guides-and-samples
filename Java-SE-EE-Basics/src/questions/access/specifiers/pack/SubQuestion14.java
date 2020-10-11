package questions.access.specifiers.pack;

import questions.access.specifiers.Question14;

public class SubQuestion14 extends Question14{

	protected void test1() {
        System.out.println("Hello from SubQuestion14");
        protectedField = 5;
        test();
	}

	// Will protectedField and test method be accessed in SubQuestion14, in Question14_1?
	public static void main(String[] args) {

	}

}
