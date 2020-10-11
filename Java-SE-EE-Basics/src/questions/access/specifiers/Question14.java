package questions.access.specifiers;


public class Question14 {

	protected int protectedField = 3;
	
	protected void test() {
        System.out.println("Hello from Question14");
	}

	// Will protectedField and test method be accessed in SubQuestion14, in Question14_1, in Question14_2?
	public static void main(String[] args) {

		
		// Answer: Access will be in SubQuestion14 because it is a subclass, 
		//         access will be in Question14_2 because it is in the same package with Question14,
		//         but no access in Question14_1, because it is not in the same package with Question14
	}

}
