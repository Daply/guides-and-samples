package blocks;

public class A {

	{
		System.out.println("This is block");
	}
	
	public static void method() {
		System.out.println("This is method");
	}
	
	public static void main(String [] args) {
		method();
		A a = new A();
		a.method();
	}
}
