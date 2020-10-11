package hashcode.and.equals;

import java.util.HashSet;
import java.util.Set;

import multithreading.ThreadsDrawingExample;

/**
 * 
 * @author Pleshchankova Daria
 *
 */

public class HashcodeAndEqualsExample {

    public static int field1 = 1;
    public int field2 = 2;
    private static int field3 = 3;
    private int field4 = 4;
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + field2;
        result = prime * result + field4;
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        HashcodeAndEqualsExample other = (HashcodeAndEqualsExample) obj;
        if (field2 != other.field2)
            return false;
        if (field4 != other.field4)
            return false;
        return true;
    }


	@Override
	public String toString() {
		return "HashcodeAndEqualsExample [field2=" + field2 + ", field4=" + field4 + ", hashCode()=" + hashCode() + "]";
	}

	public static void checkEquals(Object obj1, Object obj2) {
    	if (obj1.equals(obj2))
    		System.out.println("Object 1 equals object 2");
    	else 
    		System.out.println("Object 1 NOT equals object 2");
    }
    
    
    public static void main(String[] args) {  
    	HashcodeAndEqualsExample obj1 = new HashcodeAndEqualsExample();
    	HashcodeAndEqualsExample obj2 = new HashcodeAndEqualsExample();
    	HashcodeAndEqualsExample obj3 = new HashcodeAndEqualsExample();
    	obj1.field1 = 5;
    	checkEquals(obj1, obj2);
    	obj1.field4 = 120;
    	checkEquals(obj1, obj3);
    	checkEquals(obj1, obj1);
    	checkEquals(obj1, null);
    	
    	Set set = new HashSet();
    	set.add(obj1);
    	set.add(obj2);
    	System.out.println(set);
    	obj1.field4 = 4;
    	System.out.println(set);
    }
}
