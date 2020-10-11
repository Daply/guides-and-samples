package enums;

import java.util.Arrays;

/**
 * 
 * @author Pleshchankova Daria
 *
 */

public class EnumsExample {

    public static void main(String[] args) {
        // enum is actually class inherited from Enum class
        System.out.println(Color.class.getSuperclass());
        
        // enum Color elements are static instances of the Color enum class.
        Color color = Color.BLUE;
        if (color == Color.BLUE) 
            color = Color.RED; 
        
        
        color = Color.INDIGO; 
        System.out.println("color.name() = " + color.name() +
                " color.toString() = " + color.toString() +
                " color.ordinal() = " + color.ordinal());
        
        color = Color.valueOf("RED");
        System.out.println(color);
        
        //color = Color.valueOf("RED1"); // java.lang.IllegalArgumentException
        //System.out.println(color); 
        
        //color = Color.valueOf(null);  // java.lang.NullPointerException
        //System.out.println(color);
        
        // get all elements of enum
        System.out.println(Arrays.toString(Color.values()));
    }
}
