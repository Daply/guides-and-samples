package access.modificators1;

/**
 * 
 * @author Pleshchankova Daria
 *
 */

public class AccessModificatorsExample {
	
    public static void main(String[] args) {
    	AccessModificator am = new AccessModificator();
    	
    	am.packageElem = 100;
    	
    	am.publicElem = 10;
    	
    	// cant access to private, because private
    	// are visible only in the same class, where
    	// declared
    	// am.privateElem = 10; // error
    	
    	am.packageMethod();
    	am.publicMethod();
    	// am.privateMethod(); // error
    }
	
}
