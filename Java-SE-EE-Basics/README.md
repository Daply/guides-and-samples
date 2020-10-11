# Java SE Basics
Java SE questions and examples with short theory.

##### Table of Contents  
[List of Java reserved words](#list-of-java-reserved-words)<br>
[Java identifiers and used symbols](#java-identifiers-and-used-symbols)<br>
[Primitive types in java](#primitive-types-in-java)<br>
[Classes](#classes)<br>
[Access specifiers](#access-specifiers)<br>
[Constructor](#constructor)<br>
[Methods](#methods)<br>
[Static](#static)<br>
[Final](#final)<br>
[Abstract](#abstract)<br>
[Native](#native)<br>
[Synchronized](#synchronized)<br>
[Logial blocks or instance initialization blocks](#logial-blocks-or-instance-initialization-blocks)<br>
[Methods overloading](#methods-overloading)<br>
[Parameterized classes](#parameterized-classes)<br>
[Parameterized methods](#parameterized-methods)<br>
[Methods with various number of parameters](#methods-with-various-number-of-parameters)<br>
[Enums](#enums)<br>
[Exceptions](#exceptions)<br>
[Collections](#collections)<br>
[Strings](#strings)<br>
[Input Output Streams](#input-output-streams)<br>
[Lambdas](#lambdas)<br>
[Streams](#streams)<br>


* # List of Java reserved words
--------------------------------------------------------------------------------------------------------------------------

 |          |         |            |              |            |
 |:--------:|:-------:|:----------:|:------------:|-----------:|
 | abstract | do      | if         | private      | this       |
 | assert   | double  | implements | protected	  | throw      |
 | boolean  | else    | import     | public       | throws     |
 | break    | enum    | instanceof | return       | transient  |
 | byte     | extends | int        | short        | true       |
 | case     | false   | interface  | static       | try        |
 | catch    | final   | long       | strictfp     | void       |
 | char     | finally | native     | super        | volatile   |
 | class    | float   | new	   | switch	  | while      |
 | class    | float   | new	   | synchronized | continue   |
 | const    | for     | null	   | 		  |            |
 | default  | goto    | package    | 	          |            |

* # Java identifiers and used symbols
--------------------------------------------------------------------------------------------------------------------------

The only allowed characters for identifiers are all alphanumeric characters([A-Z],[a-z],[0-9]), ‘$‘(dollar sign) and ‘_‘ (underscore). For example “geek@” is not a valid java identifier as it contain ‘@’ special character.
Identifiers should not start with digits([0-9]). For example “123geeks” is a not a valid java identifier.
Java identifiers are case-sensitive.
There is no limit on the length of the identifier but it is advisable to use an optimum length of 4 – 15 letters only.
Reserved Words can’t be used as an identifier. For example “int while = 20;” is an invalid statement as while is a reserved word.


In Java there are used integer literals (all int type): <br>
15 - integer decimal number,<br>
062 - octal number,<br>
0x62 - hexadecimal number,<br>
0b1001 - binary number<br>

for long type it has to be defined in the end symbol L like 0xffffL or 124L.

For intepreting big numbers it is allowed to use symbol \"_\" like: <br>
int num = 12_000_000; <br/>
but it is incorrect to write like \_45 or 45\_

* # Primitive types in java
--------------------------------------------------------------------------------------------------------------------------
 
 byte -> short -> int -> long -> float -> double
 
 | Types   | Size (bits) |  Minimum Value    | Maximum Value                       |
 |:-------:|:-----------:|:-----------------:|:-----------------------------------:|
 | byte    | 8           | -128				          | 127		                               |
 | char    | 16          | 0  				           | 2<sup>16</sup>-1                    |
 | short   | 16          | -2<sup>15</sup>   | 2<sup>15</sup>-1                    |
 | int     | 32          | -2<sup>31</sup>   | 2<sup>31</sup>-1                    |
 | long    | 64          | -2<sup>63</sup>   | 2<sup>63</sup>-1                    |
 | float   | 32          | 2<sup>-149</sup>  | (2-2<sup>-23</sup>)2<sup>127</sup>  |
 | double  | 64          | 2<sup>-1074</sup> | (2-2<sup>-52</sup>)2<sup>1023</sup> |
 | boolean | -           | -  				           | -		                                 |
 | void    | -           | -  				           | - 		                                |
 
 <b>boolean</b> takes values <b>true</b> or <b>false</b> and it's size is virtual machine dependent.
 
Java automatically extends type with less range to the type with bigger (for example from byte to int). For more narrow range of values it has to be explicit converting like (type)value. With all operators must be used explicit converting, except ++, -- and abbreviated operators (+=, /=, etc.)

Examples: <br>
<a href="https://github.com/Daply/Java-Basics/blob/master/src/primitive_types/PrimitiveTypesExample.java">PrimitiveTypesExample.java</a>

* # Classes
--------------------------------------------------------------------------------------------------------------------------
General definition of class without inheritance and implementing interfaces:
```java
[public] [final] [abstract] class ClassName {
  {} // logical blocks
  // inner classes
  
  // fields
  private
  public
  protected

  // constructors
  private
  public
  protected
  
  // methods
  private
  public
  protected
}
```
if class is public it can be accessed from other packages

* # Access specifiers
--------------------------------------------------------------------------------------------------------------------------
Java Access Specifiers regulate access to classes, fields and methods.

1. public
2. private
3. protected
4. default (no specifier)

-- **public** <br>
Classes, methods, and fields declared as public can be accessed from any class in the Java program, whether these classes are in the same package or in another package. <br>
-- **private** <br>
Private methods and fields can only be accessed within the same class to which the methods and fields belong. Private methods and fields are not visible within subclasses and are not inherited by subclasses. A private method cannot be overridden.<br>
-- **protected** <br>
Methods and fields declared as protected can only be accessed by the subclasses in other package or any class within the package of the protected members' class. The protected access specifier cannot be applied to class and interfaces. <br>
-- **default** (no specifier) <br>
Classes, variables, and methods can be default accessed. Using default specifier we can access class, method, or field which belongs to same package, but not from outside this package. 


| Access specifiers                                       | default | private | protected | public |
| :------------------------------------------------------ | :-----: | :-----: | :-------: | :----: |
| Accessible inside the class                             |   yes   |   yes   |    yes    |   yes  |
| Accessible within the subclass inside the same package  |   yes   |    no   |    yes    |   yes  |
| Accessible outside the class                            |    no   |    no   |     no    |   yes  |
| Accessible within the subclass outside the same package |    no   |    no   |    yes    |   yes  |

Examples: <br>
<a href="https://github.com/Daply/Java-Basics/tree/master/src/access">Access specifiers</a>

* # Constructor
--------------------------------------------------------------------------------------------------------------------------
A <b>constructor</b> in Java is a special method that is used to initialize objects. The <b>constructor</b> is called when an object of a class is created. <br>
If a class is defined without <b>constructor</b> compiler automatically will define <b>constructor</b> like this:
```java
class Test {
   public Test() {
      super();
   }
}
```
Compiler automatically calls superclass <b>constructor</b> (super(list_of_parameters)) if it didn't called explicitly.

If a class has a <b>constructor</b> with parameters
```java
class Test {
   public int field = 0;
   public Test(int field) {
      this.field = field;
   }
}
```
the code 
```java
Test t = new Test ();
```
will give a error, because a constructor without parameters is not defined

<b>super</b> can be used to access superclass constructor, methods and fields
```java
super(list_of_parameters); // calling superclass constructor
super.id = 4; // calling superclass attribute
super.getId(); // calling superclass method
```

* # Methods
--------------------------------------------------------------------------------------------------------------------------
Methods can be defined like this:
```java
[access] [static] [abstract] [final] [synchronized] [native] [<parameterization>] _ReturnType_ methodName(_list of parameters_) [throws _list of exceptions_]
```

* # Static
--------------------------------------------------------------------------------------------------------------------------
The <b>static</b> keyword in Java is used for memory management mainly. It can be applied java <b>static</b> keyword with variables, methods, blocks and nested class. The <b>static</b> keyword belongs to the class.<br>
The static can be:
1. Variable (also known as a class variable)
2. Method (also known as a class method)
3. Block
4. Nested class

```java
class Test {
    public static String TEST_NAME = "Test#1";
}

// variable can be called with class name like this
Test.TEST_NAME
```
If <b>static</b> variable is changed, this change will be seemed to every class object

```java
class Test {
    public static String TEST_NAME = "Test#1";
}

Test t1 = new Test();
Test t2 = new Test();
Test.TEST_NAME = "TEST";
System.out.println(t1.TEST_NAME); // result will be "TEST"
System.out.println(t2.TEST_NAME); // result will be "TEST"
```
<b>Non-static</b> methods can call <b>static</b> methods and variables, but <b>static</b> methods cannot call <b>non-static</b> methods and variables (because they "don't know" to which object they belong and even class object can be not created).<br>
Static methods cannot be overridden. If you define a static method in the subclass with the same declaration, it will HIDE the parent class inherited method, and NOT override it.<br>
Extending static classes is allowed, since its members are not necessarily static.<br>
Static fields and methods are not thread-safe (so it is better to use <b>synchronized</b> with <b>static</b>).

* # Final
--------------------------------------------------------------------------------------------------------------------------
Final is used to define constant variables as class members, local variables or method parameter. Methods with <b>final</b> modificator cannot be overwritten in subclasses. For classes with <b>final</b> modificator cannot be inherited.

Constant variable must be initialized only once and it's value cannot be changed. If it is not initialized while defining, it can be initialized in logical class block or in class constructor, but only in one of these places.

* # Abstract
--------------------------------------------------------------------------------------------------------------------------
<b>Abstract</b> methods are situated in <b>abstract</b> classes or interfaces and such methods can't have a body and must be implemented in subclasses.

It is impossible to create an instance of <b>abstract</b> class (and interface too). <b>Abstract</b> classes can have <b>abstract</b> and non <b>abstract</b> methods.

```java
  public abstract class Test {
       private String testName;
       public abstract void test(int testNumber);
       public String getTestName() {
           return testName;
       }
  }
  
  Test t = new Test(); // compilation error
```

* # Native
--------------------------------------------------------------------------------------------------------------------------
Methods with keyword <b>native</b> are methods which were implemented on different programming language.

Native methods can be overwritten with simple methods in subclasses.

* # Synchronized
--------------------------------------------------------------------------------------------------------------------------
For using several threads it is needed to synchronize methods, which use common data. When interpretator sees <b>synchronized</b> it blocks access to data when thread is launching and unblock when thread is finished. 

notifyAll(), notify() and wait() of class Object (superclass for all classes in Java) use modificator synchronized, because these methods work with threads.

* # Logial blocks or instance initialization blocks
--------------------------------------------------------------------------------------------------------------------------
Blocks of code in parentheses:
```java
{ /* code */  }
static { /* code */  }
```
Often used for fields initializing, but also can contain methods calling.<br>
Operations with class fields inside of logical block are possible only using <b>this</b> reference, for example like this:
```java
class A {
  public int variable1;
   {
		   System.out.println("This is block from class A");
	   	variable1 = 5 + variable2; // error, cannot reference a field before it is defined
	   	printHelloFromBlock(1);
	  }
   public int variable2 = 5;
}
```
The solution will be to use this.variable2.

Logical block can be defined with static specificator. In ihis case it is called only once in application lyfecycle or when *static* method (field) was called.

Important points: <br>
- instance initialization blocks run every time a new instance is created.
- instance initialization blocks run in the order they appear in the program
- The instance initialization block is invoked after the parent class constructor is invoked (i.e. after super() constructor call)

* # Methods overloading
--------------------------------------------------------------------------------------------------------------------------
<b>Method overloading</b> is a feature that allows a class to have more than one method having the same name, if their argument lists are different. It is similar to constructor overloading in Java, that allows a class to have more than one constructor having different argument lists.

There are two ways to overload the method in java:
- By changing number of arguments
- By changing the data type

Methods with the same names but with different lists of parameters can be in different classes and will be still considered overloaded. If parameter lists are identical - methods are overrided.

*Static* methods can be overloaded with *non-static* and vice versa.

* # Parameterized classes
--------------------------------------------------------------------------------------------------------------------------
```java
public class Test <T1, T2 extends Number> {
    private T1 field1;
    private T2 field2;
}
```
T1 and T2 - fictitious object types, used for defining class fields and processed data. T2 can be only subclass of Number class.<br>
Examples of creating objects of class Test:
```java
Test<String, Short> test1 = new Test<String, Short>();
Test<StringBuilder, Long> test2 = new Test<StringBuilder, Long>();
// or
Test<StringBuilder, Long> test2 = new Test<>(); // oprator <> diamond in java
test1=test2; // compilation error

// it is possible use type check like this
test1 instanceof Test

// but this is erronous
test1 instanceof Test<String, Short>
```
Example of parameterized class:
```java
public class Message<T> {
    private T value;
    public Message() {}
    
    public T getValue() {
         return this.value;
    }
    
    public void setValue(T value) {
         this.value = value;
    }
}

// if two objects created
Message<String> mes1 = new Message<String>();
mes1.setValue("Java");
Message<Integer> mes2 = new Message<Integer>();
mes2.setValue(2);
mes1 = mes2; // compilation error, parameterization is noncovariant

// default parameterization
Message mes3 = new Message();
System.out.println(mes3.getValue()); // output: null
mes3 = mes1; // no error
mes3.setValue(new Float(3.4f));
mes3.setValue("Java");
System.out.println(mes3); // output will be object type, not the parameterization type: java.lang.String: Java
```
If it is needed to pass an object of the same class but the other parameterized type, it is possible to do so with <b>"?"</b> metasymbol.
```java
public class Test<T extends Number> {
     private T test;
     public Test(T test){
         this.test = test;
     }
     public boolean equalsTo(Test<T> t){
        return test == t.test;
     }
}

Test<Double> t1 = new Test<Double>(5.4D);
Test<Double> t2 = new Test<Double>(5.4D);
t1.equalsTo(t2); // true
Test<Integer> t3 = new Test<Integer>(5);
t1.equalsTo(t3); // error, incompatible types

// for using method equalsTo for Test class instances of any allowed type it is possible to use "?":
public boolean equalsTo(Test<?> t){
        return test == t.test;
}
t1.equalsTo(t3); // no compilation error
```
It is impossible to do explicit calling of a *constructor* <b>generic</b> type, because compiler doesn't know which constructor can be called and what memory volume is needed, also on the same reason <b>generic</b> fields can't be *static*, *static* methods can't have <b>generic</b> parameters and call <b>generic</b> fields:
```java
public class Test<T> {
   private T value = new T(); // error
   static T field; // error
   T id;
   static T getField() { // error
        return field;
   }
   
   static void printId() { // error
        System.out.println(id);
   }
}
```
* # Parameterized methods
--------------------------------------------------------------------------------------------------------------------------
```java
<T extends Type> returnType methodName(T arg) { }
<T> T[ ] methodName(int count, T arg) { }
```
Generic methods can be in parameterized classes or not.<br>
Parameterized methods can be *static*.

* # Methods with various number of parameters
--------------------------------------------------------------------------------------------------------------------------
List of parameters in common way:
```java
(Type... args)
```
If it is needed to pass parameters of other types:
```java
(Type1 t1, Type2 t2, Typen tn, Type... args)
```
Example:
```java
public class Test {
      public static void printArgs(Integer... args) {
          if (args.length == 0) {
	      System.out.println("No args");
	  }
	  else {
	      for (int element: args) {
	           System.out.printf("arg:%d ", element);
	      }
	  }
      }
      
      public static void main(String... args) {
           printArgs(7, 71, 43);
	   Integer[] arr = {2, 5, 3, 75, 11};
	   printArgs(arr);
	   printArgs();
	   printArgs(arr, arr); // Compilation error
	   printArgs(71, arr); // Compilation error
      }
}

//output:
// arg:7 arg:71 arg:43
// arg:2 arg:5 arg:3 arg:75 arg:11
// No args
```
For passing several arrays it is needed to use:
```java
void methodName(Type []... args) {}
```
Methods with various number of parameters can be overloaded:
```java
void test(Integer... args) {}
void test(int x1, int x2) {}
```
But this is WRONG:
```java
void test(Integer... args) {}
void test(Integer[] args) {}
```
Example:
```java
public class Test {
      public static void printArgs(Object... args) {  // 1
          System.out.println("Object args: " + args.length);
      }
      
      public static void printArgs(Integer[]... args) {  // 2
          System.out.println("Integer[] args: " + args.length);
      }
      
      public static void printArgs(int... args) {   // 3
          System.out.println("int args: " + args.length);
      }
      
      public static void main(String... args) {
	   Integer[] arr = {1, 2, 3, 4};
	   printArgs(7, "Hello", 5.2d, true, null);
	   printArgs(arr, arr, arr);
	   printArgs(arr, 7, 41);
	   printArgs(arr); // method 1 called
	   printArgs(4, 5);
	   printArgs(); // non determination while overloading
      }
}

//output:
// Object args: 5
// Integer[] args: 3
// Object args: 3
// Object args: 4  // method 1 called because name of array is an object reference, and this parameter will be the nearest
                   // method 2 will be called only if method 1 will be absent
// int args: 2
```
<b>Overloading and overriding</b> is allowed.
The only restriction, the parameter <b>Type... args</b> must be in the end of parameter list of method:
```java
// THIS IS NOT ALLOWED
(Type1... args, Type2 obj) // Compilation error
(Type1... args1, Type2... args2) // Compilation error
```
* # Enums
--------------------------------------------------------------------------------------------------------------------------
<b>Enum</b> is a subclass of an abstract class <b>java.lang.Enum</b>.
```java
public enum DayOfWeek {
   SUNDAY,
   MONDAY,
   TUESDAY,
   WEDNESDAY,
   THURSDAY,
   FRIDAY,
   SATURDAY
}

public void checkHoliday(DayOfWeek dayOfWeek) {

       switch (dayOfWeek) {

           case SUNDAY:   // in case it is possible to use constant without type, because it is already defined in switch(dayOfWeek)
               System.out.println("It's a holiday!");
           case MONDAY:
               System.out.println("It's not a holiday :(");
           case TUESDAY:
               System.out.println("It's not a holiday :(");
               // and so on
       }
   }
```
<b>Enums</b> can have fields, methods, constructors and implementing interfaces. Every enum can use methods:
```java
static enumType[] values() - returns array of enum elements
static <T extends Enum<T>> T valueOf(Class <T> enumType, String arg) - creates enum element, with given type and value
static enumType valueOf(String arg) - creates enum element, appropriate to given string value
int ordinal() - returns enum element position
String name() - returns enum element name
int compareTo(T obj) - compares elements on more, less or equal
```
Restrictions for <b>enums</b>:
- It it impossible to inherit <b>enum</b>.
- <b>Enum</b> cant be a subclass.
- <b>Enum</b> cant be abstract.
- It it impossible to create <b>enum's</b> instances using <b>new</b> keyword.
- Constructor of enum cannot be public or protected, because it is not called explicitly and cannot be a superclass.

* # Exceptions
--------------------------------------------------------------------------------------------------------------------------

                                           Throwable
                                               |
		           ---------------------------------------------------
                       |                                                 |
                     Error                                            Exception
                       |                                                 |
              -------------------                              ------------------------------           
              |                 |                              |                            |
    OutOfMemoryError       StackOverflowError            RuntimeException               IOException
                      ...                                   |                               |
                                                            -- ArithmeticException          -- FileNotFoundException
                                                            |                              ...
							    -- NullPointerException
						            |
							    -- ClassCastException
						            |
						            -- IndexOutOfBoundsException
                                                                ...
								
								
Error and RuntimeException - un-checked exceptions (exceptions, which cannot be handled during compilation).
All other - checked exceptions.

<b>Throwable methods:</b><br>
fillInStackTrace ()<br>
------- Fills in the execution stack trace.<br>
getCause ()<br>
------- Returns the cause of this throwable or null if the cause is nonexistent or unknown.<br>
String	getLocalizedMessage ()<br>
------- Creates a localized description of this throwable.<br>
String	getMessage ()<br>
------- Returns the detail message string of this throwable.<br>
StackTraceElement [] getStackTrace ()<br>
------- Provides programmatic access to the stack trace information printed by printStackTrace().<br>
initCause (Throwable  cause)<br>
------- Initializes the cause of this throwable to the specified value.<br>
void printStackTrace ()<br>
------- Prints this throwable and its backtrace to the standard error stream.<br>
void printStackTrace (PrintStream s)</br>
------- Prints this throwable and its backtrace to the specified print stream.<br>
void printStackTrace (PrintWriter s)<br>
------- Prints this throwable and its backtrace to, the specified print writer.<br>
void setStackTrace (StackTraceElement [] stackTrace)<br>
------- Sets the stack trace elements that will be returned by getStackTrace() and printed by printStackTrace() and related methods.<br>
String toString ()<br>
------- Returns a short description of this throwable.<br>


* # Collections
--------------------------------------------------------------------------------------------------------------------------

                                                 interface 
					             Collection
						         |
                    ------------------------------------------------------------------
			|                                |                               |
	            interface                        interface                       interface
		       Set                             List                            Queue
		        |                                |                               |
	         ----------------------           ---------------------------------   ------------------------
             |                    |           |                     |         |   |                      |
	      HashSet             interface    ArrayList             Vector    LinkedList            PriorityQueue
	         |                SortedSet
	         |                    |
       LinkedHashSet          interface
                             NavigableSet
                                  |
			           TreeSet


* # Strings
--------------------------------------------------------------------------------------------------------------------------

All methods description official documentation:<br>
https://docs.oracle.com/javase/7/docs/api/java/lang/String.html

Strings are immutable, it means that after creation of String object it is impossible to change it.
Classes for changing strings are StringBuider and StringBuffer, StringBuffer is synchronized, StringBuilder is not.

String is final class, so it is impossible to inherit from it.

```java
// 1
String s = "abc";
String s1 = "abc"; // s1 will point to the same string "abc" in memory
    	
if (s == s1) {  // true
    System.out.println("YES"); // this will be printed
}
else {
    System.out.println("NO");
}
// 2
String s = "abc";
String s1 = new String("abc"); // creation of new object
    	
if (s == s1) { // false
    System.out.println("YES");
}
else {
    System.out.println("NO"); // this will be printed
}
```

* # Input Output Streams
--------------------------------------------------------------------------------------------------------------------------

InputStream OutputStream               Reader  Writer


* # Lambdas
--------------------------------------------------------------------------------------------------------------------------

<b>Functional interfaces:</b>
https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html

```java
public interface Countable {
    int count(int a, int b);
}


Countable countable = (a, b) -> a + b;
int result = countable.count(3, 5); // 8
```

* # Streams
--------------------------------------------------------------------------------------------------------------------------
```java
// 1
List<String> myList =
    Arrays.asList("a1", "a2", "b1", "c2", "c1");

myList
    .stream()
    .filter(s -> s.startsWith("c"))
    .map(String::toUpperCase)
    .sorted()
    .forEach(System.out::println);
    
// 2
Stream.of("d2", "a2", "b1", "b3", "c")
    .filter(s -> {
        System.out.println("filter: " + s);
        return true;
    })
    .forEach(s -> System.out.println("forEach: " + s));
// 3
Integer sumOdd = collection.stream().filter(o -> o % 2 != 0).reduce((s1, s2) -> s1 + s2).orElse(0);
// 4

```


# Questions:
<a href="https://github.com/Daply/Java-Basics/tree/master/src/questions">questions</a>
