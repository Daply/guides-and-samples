package nested.classes;

/**
 * 
 * @author Pleshchankova Daria
 *
 */

public class NestedClassesExample {
    public static void main(String [] args) {
        OuterClass1.StaticNestedClass instance = new OuterClass1.StaticNestedClass(); 
        instance.someNestedClassField = 1;
        
        OuterClass2 outerClass = new OuterClass2();
        OuterClass2.MemeberInnerClass memberInnerClass = outerClass.new MemeberInnerClass(2);
        
        // ERROR as constructor without parameters of inner class is PRIVATE
        //OuterClass.MemeberInnerClass memberInnerClass1 = outerClass.new MemeberInnerClass(); // error
        
        memberInnerClass.publicInnerClassField = 1;
        // ERROR because this PRIVATE is field 
        //memberInnerClass.privateInnerClassField = 2; // error
        
        System.out.println("Check which method will be called:");
        
        // when calling METHOD of INNER class with the same 
        // name as METHOD of OUTER class, it calls
        // METHOD of INNER class
        memberInnerClass.publicNonStaticMethod();
        
        
        // inner interface interpreted as static
        System.out.println(OuterClass3.NonStaticInterface.interfaceField);
        
        
        OuterClass4.methodForCreatingALocalClass();
        
        
        // CREATION OF ANONYMOUS CLASS
        System.out.println(
        new OuterClass5() {
            public void setNonStaticField() {
                nonStaticField = 5;
            }
        }.nonStaticField);
    }
}
