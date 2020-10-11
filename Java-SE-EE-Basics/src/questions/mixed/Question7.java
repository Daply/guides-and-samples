package questions.mixed;


public class Question7 {

	// What will be result of this program?
	public static void main(String[] args) {
		int i = 0, j = 5;
		step: for (;;) {
			i++;
			for (;;) {
				if (i > --j) {
					break step;
				}
			}
		}
		System.out.println(i + "-" + j);
		
		// Answer: 1-0
	}

}
