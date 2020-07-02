package programs;

public class IdentifierAndPrimitiveDataTypes {
	public static void main(String args[]) {
		// ===== DECLARATION =======
		// dataype identifier ;
		int myNumber;
		// ====== ASSIGNMENT ======
		// identifier = literals ;
		myNumber = 123;
		// ==== DECLARATION & ASSIGNMENT =====
		// datatype identifier = literals ;
		int myOtherNumber = 456;
		
		// ======= IDENTIFIERS ======
		// 1. id should not be a keyword
		//int class; WRONG!
		// 2. id is case-sentitive
		//MyNumber = 69; WRONG!
		// 3. id is allowed to use alphanumerics and 
		// two symbols _ and $
		// 3.1 id should not start with a digit
		//int 3$_N3umbe$_r3$_; // WRONG!
		// 4. id should not contain a whitespace
		//int fourth number; //WRONG!
		
		// ====== NAMING CONVENTION ======
		// class name & file name
		// method and variable name
		//	camel casing
		//	snake casing
		int my_fourth_number;
		// constant variable name
		final int MY_CONSTANT_NUMBER = 1;
		
		// ====== JAVA PRIMITIVE DATA TYPES ======
		byte	thisIsByte;		//8-bits  signed-bits
		short 	thisIsShort;	//16-bits signed-bits
		int 	thisIsInt;		//32-bits signed-bits
		long 	thisIsLong;		//64-bits signed-bits
		float 	thisIsFloat;	//32-bits signed-bits single-precision
		double 	thisIsDouble;	//64-bits signed-bits double-precision
		boolean thisIsBoolean;	//8- bits signed-bits
		char 	thisIsChar;		//16-bits signed-bits unicode
		
		// ====== LITERALS =======
		thisIsByte = 127;
		thisIsShort = 12312;
		thisIsInt = 127;
		thisIsInt = 0b101010;
		thisIsInt = 0127;
		thisIsInt = 0x127afbc;
		thisIsLong = 123123;
		
		thisIsFloat = 127.2e12f;
		thisIsDouble = 1234.2e121d;
		
		thisIsBoolean = false;
		thisIsBoolean = true;
		
		thisIsChar = 'f';
		
		String thisIsString = "subscribe to me";
		
		// ====== TYPE CASTING =======
		thisIsInt 	= thisIsByte;
		thisIsByte 	= (byte)thisIsInt;
		thisIsInt 	= (int)thisIsLong;
		thisIsInt	= thisIsChar;
		thisIsChar	= (char)thisIsInt;
		thisIsString = String.valueOf(thisIsBoolean);
		thisIsString = Double.toString(thisIsDouble);
		thisIsInt = Integer.valueOf(thisIsString);
	}
}
