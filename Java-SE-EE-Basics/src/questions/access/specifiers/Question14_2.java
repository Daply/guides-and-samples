package questions.access.specifiers;

import questions.access.specifiers.Question14;

public class Question14_2 {

	protected void test1() {
        System.out.println("Hello from SubQuestion14");
        Question14 q = new Question14();
         q.protectedField = 5;
         q.test();
	}

	// Will privateField and test method be accessed in SubQuestion14?
	public static void main(String[] args) {

	}

}
