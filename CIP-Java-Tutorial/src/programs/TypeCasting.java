package programs;

public class TypeCasting {
	public static void main(String args[]) {
		// IMPLICIT CASTING (WIDENING)
		byte from_byte = 12;
		int to_int = from_byte;
		System.out.println("to_int : " + to_int);
		
		int from_int_to_double = 3;
		double to_double_from_int = from_int_to_double;
		System.out.println("to_double_from_int : " + to_double_from_int);
		
		char a = 'A';
		int a_val = a;
		System.out.println("a_val : " + a_val);
		
		// EXPLICIT CASTING (NARROWING)
		int from_int = 128;
		byte to_byte = (byte)from_int;
		System.out.println("to_byte : " + to_byte);
		
		double from_double_to_int = 3.1123123123123123134;
		int to_int_from_double = (int)from_double_to_int;
		System.out.println("to_int_from_double : " + to_int_from_double);
		
		int a_num = 90;
		char a_char = (char)a_num;
		System.out.println("a_char : " + a_char);
		
		// OVERFLOWING
		System.out.println("Max BYTE : " + Byte.MAX_VALUE);
		System.out.println("Overflowed Byte : " + (byte)(Byte.MAX_VALUE + 1));
		
		// UNDERFLOWING
		System.out.println("Min BYTE : " + Byte.MIN_VALUE);
		System.out.println("Underflowed Byte : " + (byte)(Byte.MIN_VALUE - 1));
		
		// AREA OF CIRCLE
		int radius = 10;
		double pi = 3.14;
		double area = radius * radius * pi;
		System.out.println("Area : " + area);
	}
}
