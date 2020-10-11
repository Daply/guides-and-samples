package enums;

/**
 * 
 * @author Pleshchankova Daria
 *
 */

// By declaring enum, we implicitly create a class 
// derived from java.lang.Enum. 
// Conventionally, the enum Season {...} construction is 
// equivalent to the class Season extends java.lang.Enum {...}.

// !!! The enum Color elements are static instances of the Color enum class.

public enum Color {
    RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET;
    
    public Color getFirstColor() {
        return RED;
    }
}
