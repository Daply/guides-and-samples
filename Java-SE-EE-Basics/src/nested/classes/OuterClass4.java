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
 * LOCAL INNER CLASS EXAMPLE
 *
 */


public class OuterClass4 {

    public static int staticField = 0;
    private static int privateStaticField = 0;
    
    public int nonStaticField = 0;
    
    public static void methodForCreatingALocalClass() {
        class LocalInnerClass {
            public static final int localInnerClassField = 0;
            //static int localInnerClassField1 = 0;  // error
            public int localInnerClassField2 = 0;
            
            public void publicIncreasePrivateStaticField() {
                privateStaticField++;
            }
            
            private void privateIncreasePrivateStaticField() {
                privateStaticField++;
            }
        }
        LocalInnerClass localInnerClass = new LocalInnerClass();
        // HAS ACCESS
        localInnerClass.publicIncreasePrivateStaticField();
        // HAS ACCESS
        localInnerClass.privateIncreasePrivateStaticField(); 
        System.out.println(privateStaticField);
        System.out.println("methodForCreatingALocalClass");
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
        OuterClass4.methodForCreatingALocalClass();
    }
}
