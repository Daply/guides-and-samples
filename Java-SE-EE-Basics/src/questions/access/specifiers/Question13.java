package questions.access.specifiers;


public class Question13 {
	
	public int a = 2;
	private int b = 3;
	int c = 4;
	
	private void test() {
        System.out.println("Hello from Question13");
	}

	// Will there be an error in lines from 1 to 3, lines 4 to 6 and line 7?
	public static void main(String[] args) {

		
		// Answer: In line 5 : Yes. b can not be accessed in subclasses
		//         in line 7 : Yes. Private methods can not be inherited by subclasses
	}

	
	class Quest {
		
		public Quest() {
			a = 5; // 1
			b = 6; // 2
			c = 7; // 3
		}
	}
}

class SubQuestion13 extends Question13{
	
	public SubQuestion13() {
		a = 5; // 4
		//b = 6; // 5
		c = 7; // 6
	}
	
	//@Override      
	private void test() {   // 7
        System.out.println("Hello from SubQuestion13");
	}
}

