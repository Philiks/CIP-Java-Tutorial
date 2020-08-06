package programs;

public class Test {
	public static void main(String args[]) {
		int a = 1, b, c;
		b = a++;
		c = ++a;
		System.out.println("b: " + b + " " + "c: " + c);
		
		int d;
		d = c % b;
		System.out.println(d);
		Integer num = Integer.valueOf(10);
		boolean isTrue = num instanceof Number;
		System.out.println(isTrue);
		
		int shift = a << 2;
		// 00000011 = 3
		// 00001100 = 12
		System.out.println(!(!isTrue));
		
		int height = 4;
		boolean hasHeartCondition = false;
		boolean canRide = height < 5 && hasHeartCondition;
		
		int x = 1;
		x += 1; // x = x + 1
		d = c =b = a =100;
		System.out.println(x);
		System.out.println(a + " " + b +  c + " " + d);
		
		num = 69 > 420 ? 420 : 69;
		System.out.println(num);
	}
}

