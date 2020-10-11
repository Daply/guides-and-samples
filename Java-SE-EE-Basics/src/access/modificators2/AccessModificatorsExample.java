package access.modificators2;

import access.modificators1.AccessModificator;

/**
 * 
 * @author Pleshchankova Daria
 *
 */

public class AccessModificatorsExample {
	
    public static void main(String[] args) {
    	AccessModificator am = new AccessModificator();
    	
    	// because of another package there will
    	// be error accessing to package visible varieties
    	// and methods
    	// am.packageElem = 100; // error
    	
    	am.publicElem = 10;
    	
    	// cant access to private, because private
    	// are visible only in the same class, where
    	// declared
    	// am.privateElem = 10; // error
    	
    	// am.packageMethod(); // error
    	am.publicMethod();
    	// am.privateMethod(); // error
    }
	
}
