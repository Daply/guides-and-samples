package questions.inheritance;


class Parent {
	
    public String name = "Parent";
    
	public String getName() {
		return name;
	}

}

class Child extends Parent {
	
    public String name = "Child";
    
	public String getName() {
		return name;
	}
}

public class Question6 {

	// What will be result of this program?
	public static void main(String[] args) {
		Child s = new Child();
		Parent q = s;
		System.out.println(q.getName());
		
		// Answer: Child. Because reference was Parent, but the object was defined as Child.
	}

}
