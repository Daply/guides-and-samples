package abstract_classes.and.interfaces;

/**
 * 
 * @author Pleshchankova Daria
 *
 */

// abstract classes method can have body, interfaces not

// all methods in interfaces by default are abstract public, 
// interface varieties by default are public static final

// all abstract methods must be implemented in child classes

public class AbstractAndInterfaceExample {
	
    public static void main(String[] args) {
    	AbstractClass acobj = new AbstractClassChild();
    	AbstractClassChild accobj = new AbstractClassChild();
    	
    	acobj.packageValue = 3;
    	acobj.publicValue = 3;
    	acobj.abstractMethod();
    	acobj.simpleMethod();
    	
    	accobj.packageValue = 4;
    	accobj.publicValue = 4;
    	accobj.abstractMethod();
    	accobj.simpleMethod();
    	
    	
    	System.out.println(InterfaceClass.value);
    	InterfaceClass ic = new InterfaceClassChild();
    	ic.abstractMethod();
    	ic.simpleMethod();
    }
    
}
