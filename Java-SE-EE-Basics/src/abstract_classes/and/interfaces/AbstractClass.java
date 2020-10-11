package abstract_classes.and.interfaces;

/**
 * 
 * @author Pleshchankova Daria
 *
 */

public abstract class AbstractClass {

	int packageValue = 0;
	private int privateValue = 0;
	public int publicValue = 0;
	
	void simpleMethod() {
		System.out.println("This is simple method from abstract class");
	}
	
	abstract void abstractMethod();
	
}
