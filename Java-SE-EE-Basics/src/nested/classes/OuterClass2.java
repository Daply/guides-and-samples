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
 * MEMBER INNER CLASS EXAMPLE
 *
 */

public class OuterClass2 {

    public static int staticField = 0;
    private static int privateStaticField = 0;
    
    public int nonStaticField = 0;
    
    /**
     * Inner Member Class
     *
     */
    public class MemeberInnerClass {
        public int publicInnerClassField = 0;
        private int privateInnerClassField = 0;
        
        private MemeberInnerClass() {
            
        }
        
        public MemeberInnerClass(int innerClassField) {
            // inner classes (InnerClass) HAS access to 
            // non-static fields (nonStaticField) of outer class (OuterClass), 
            // to static fields (even private) too 
            nonStaticField = 1; 
            staticField = 1;
            OuterClass2.staticField = 1;
            privateStaticField = 1;
            
            System.out.println("Check the methods of outer class:");
            
            // inner classes (InnerClass) HAS access to
            // public static method from outer class
            publicStaticMethod();
            
            // inner classes (InnerClass) HAS access to
            // private static method from outer class
            privateStaticMethod();
            
            // inner classes (InnerClass) HAS access to
            // private NON static methods (even private) from outer class
            publicNonStaticMethod();
            privateNonStaticMethod();
        }
        
        // ERROR declaring STATIC method!!!
        //public static void publicStaticMethod() {
        //    System.out.println("public static method of inner class");
        //}
        
        public void publicNonStaticMethod() {
            System.out.println("public non-static method of inner class");
        }
    }
    
    /**
     * Defining inner interface, 
     * non explicitly will be interpreted as PUBLIC STATIC INTERFACE CLASS
     *
     */
    public interface NonStaticInterface { 
        int interfaceField = 345;
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
    
    private void checkMemberClassVisibility() {
        MemeberInnerClass memberInnerClass = new MemeberInnerClass();
        memberInnerClass.privateInnerClassField = 1;
        memberInnerClass.publicInnerClassField = 1;
    }
    
    public static void main(String [] args) {
        // NO ACCESS 
        //InnerClass innerClass = new InnerClass();  // error
        //     it is ONLY possible to create instance of inner class
        //     with private constructor by using instance of
        //     outer class
        //                          |
        //                         \ /
        OuterClass2 outerClass = new OuterClass2();
        OuterClass2.MemeberInnerClass memberInnerClass = outerClass.new MemeberInnerClass(2);
        // outer class has access from any its point to private methods 
        // and fields of its inner class
        memberInnerClass.publicInnerClassField = 1;
        memberInnerClass.privateInnerClassField = 2;
        
        System.out.println("Check which method will be called:");
        
        // when calling METHOD of INNER class with the same 
        // name as METHOD of OUTER class, it calls
        // METHOD of INNER class
        memberInnerClass.publicNonStaticMethod();
        
        
        // inner interface interpreted as static
        System.out.println(OuterClass2.NonStaticInterface.interfaceField);
    }
}
