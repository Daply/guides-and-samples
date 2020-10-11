package abstract_classes.and.interfaces;

public class InterfaceClassChild implements InterfaceClass{

	@Override
	public void simpleMethod() {
		System.out.println("This is simple method from child of interface");
	}

	@Override
	public void abstractMethod() {
		System.out.println("This is implementation of abstract method"
				+ " in a child of interface ");
	}

}
