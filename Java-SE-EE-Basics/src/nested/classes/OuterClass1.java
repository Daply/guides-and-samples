package nested.classes;



/**
 * 
 * @author Pleshchankova Daria
 *
 */

// Nested class is a class that is defined inside of other class
//
//                                         Nested classes
//                       |                                               |
//                    static                                         non-static
//             (static nested classes)                           (inner    classes)
//                                                 |                      |                    |
//                                        member inner classes       local classes     anonymous classes

// !!! A nested class can be declared private, public, protected, or package private
// !!! If you define NON static INNER INTERFACE CLASS, it will be interpreted as PUBLIC STATIC INTERFACE CLASS
// !!! LOCAL INNER CLASSES has limits: 
//     - visibility only in block where defined
//     - cannot be private, public, protected or static
//     - cannot have inside static fields, methods and classes, exception: static final fields

// !!! The use of anonymous classes is justified in many cases, in particular when:
//     - class body is very short;
//     - only one instance of the class is needed;
//     - the class is used at the place of its creation or immediately after it;
//     - the class name is not important and does not facilitate the understanding of the code.

//Why Use Nested Classes?
//
//Compelling reasons for using nested classes include the following:
//
//    It is a way of logically grouping classes that are only used in one place: 
//       If a class is useful to only one other class, then it is logical to embed it 
//       in that class and keep the two together. Nesting such "helper classes" makes 
//       their package more streamlined.
//
//    It increases encapsulation: 
//       Consider two top-level classes, A and B, where B needs access to 
//       members of A that would otherwise be declared private. 
//       By hiding class B within class A, A's members can be declared private and 
//       B can access them. In addition, B itself can be hidden from the outside world.
//
//    It can lead to more readable and maintainable code: 
//       Nesting small classes within top-level classes places the code closer to where it is used.

/**
 * 
 * STATIC NESTED CLASS EXAMPLE
 *
 */


public class OuterClass1 {

    public static int staticField = 0;
    private static int privateStaticField = 0;
    
    public int nonStaticField = 0;
    
    /**
     * Static Nested Class
     *
     */
    public static class StaticNestedClass {
        
        public int someNestedClassField = 0;
        
        public StaticNestedClass() {
            
            // static nested classes (StaticNestedClass) HAS NO access to 
            // NON static fields (nonStaticField) of outer class (OuterClass), 
            // analogically like static methods
            //nonStaticField = 1;               // error
            //OuterClass.nonStaticField = 1;    // error
            // to access NON static fields (nonStaticField) in static 
            // nested class (StaticNestedClass) only possible
            // through instance of outer class (OuterClass)
            OuterClass1 oc = new OuterClass1();
            oc.nonStaticField = 1;
            
            // static nested classes (StaticNestedClass) HAS access to 
            // static fields (nonStaticField) of outer class (OuterClass), 
            // even to private static fields
            staticField = 1;
            privateStaticField = 1;
            
            System.out.println("Check the methods of outer class:");
            
            // static nested classes (StaticNestedClass) HAS access to
            // public static method from outer class
            publicStaticMethod();
            
            // static nested classes (StaticNestedClass) HAS access to
            // private static method from outer class
            privateStaticMethod();
            
            // static nested classes (StaticNestedClass) HAS NO access to
            // NON static methods from outer class
            //publicNonStaticMethod();  // error
            //privateNonStaticMethod();  // error
        }
    }

    public static void publicStaticMethod() {
        System.out.println("public static method of outer class");
    }
    
    public void publicNonStaticMethod() {
        System.out.println("public non-static method of outer class");
    }
    
    private static void privateStaticMethod() {
        System.out.println("private static method of outer class");
    }
    
    private void privateNonStaticMethod() {
        System.out.println("private non-static method of outer class");
    }
    
    public static void main(String [] args) {
        OuterClass1.StaticNestedClass instance = new OuterClass1.StaticNestedClass(); 
        instance.someNestedClassField = 1;
    }
}
