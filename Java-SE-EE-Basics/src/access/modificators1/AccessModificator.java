package access.modificators1;

/**
 * 
 * @author Pleshchankova Daria
 *
 */

public class AccessModificator {
	
	int packageElem = 123;
	private int privateElem = 234;
	public int publicElem = 345;
	
	void packageMethod() {
		System.out.println("Package visible method");
	}
	
	private void privateMethod() {
		System.out.println("Private visible method");
	}
	
	public void publicMethod() {
		System.out.println("Public visible method");
	}
}
