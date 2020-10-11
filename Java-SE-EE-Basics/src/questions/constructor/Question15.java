package questions.constructor;


public class Question15 {
	
	int field = 0;

	public Question15 (int field) {
		this.field = field;
	}
	
	// What will be result of this program?
	public static void main(String[] args) {
		
		//Question15 q = new Question15 (); // line 1
		Question15 q1 = new Question15 (5); // line 2
		
		// Answer: There will be error in line 1, because a constructor without parameters is not defined
	}

}
