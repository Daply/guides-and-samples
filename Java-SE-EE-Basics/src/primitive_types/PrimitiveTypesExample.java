package primitive_types;

/**
 * 
 * @author Pleshchankova Daria
 *
 */

//
// Primitive types in java:
//
// byte -> char -> short -> int -> long -> float -> double
//

public class PrimitiveTypesExample {
	
	static byte maxByte = 127;
	static byte minByte = -128;
	
	static char maxChar = 65535;
	static char minChar = 0;
	
	static short maxShort = 32767;
	static short minShort = -32768;
	
	static int maxInt = 2147483647;
	static int minInt = -2147483648;
	
	static long maxLong = 9223372036854775807L;
	static long minLong = 9223372036854775807L;
	
	static float maxFloat = 1.4E-45f;
	static float minFloat = 3.4028235E+38f;
	
	static double maxDouble = 4.9E-324;
	static double minDouble = 1.7976931348623157E+308;
	
	public static void main(String[] args) {
		// Symbol _
		int num = 12_000_000;
    	int num1 = 1_2_0_0_0_0_0_0;
		
    	// Literals
    	int v = 0xffff;      // hexadecimal
    	long v1 = 0xffffL;   // hexadecimal long
    	long v2 = 124L;     // decimal long
    	int v3 = 0b01010101010; // binary
    	int v4 = 0325;  // octal
		
    	// Type conversions
    	byte b = 1;
    	byte b1 = 1 + 1; 
    	//byte b2 = 127 + 2; // type conversion error 
    	/* error because the size of byte is exceeded, 
    	 * has to be explicit converting to byte */
    	byte b2 = (byte) (127 + 1);
    	//b = b1 + 2;        // type conversion error
    	/* error because on the moment of this code execution
    	 * variable b1 can be changed and as a result expression
    	 * b1 + 1 can exceed allowed range */
    	b = (byte) (b1 + 1);
    	final byte B = 1;
    	b = B + 1;  // works
    	/* B - is a constant value, so if B + 1 is not 
    	 * exceed allowed range, there is no error */
        
    	// b = -b; // type conversion error
    	/* must be used explicit converting */
    	b = (byte) -b;
    	// b = +b; // type conversion error
    	/* the same */
    	
    	// all these works fine, because abbreviated operators
    	// are converting implicitly
    	int i = 7; 
    	b += i++; 
    	b *= 129;
    	
    	//b = i; // type conversion error
    	/* error because the size of byte is exceeded, 
    	 * has to be explicit converting to byte */
    	b = (byte) i;
    	
    	final int I = 7;
    	b = I; // works
    	/* I - is a constant value, so if I is not 
    	 * exceed allowed range, there is no error */
    }
}
