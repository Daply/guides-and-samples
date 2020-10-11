package abstract_classes.and.interfaces;

public class AbstractClassChild extends AbstractClass{

	@Override
	void abstractMethod() {
		System.out.println("This is implementation of abstract method"
				+ " in a child of abstract class ");
	}

	void simpleMethod() {
		System.out.println("This is simple method from child of abstract class");
	}

}
