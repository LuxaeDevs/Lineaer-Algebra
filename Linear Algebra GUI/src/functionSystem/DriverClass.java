package functionSystem;
//import javax.lang.model.element.NestingKind;
import functionSystem.ComparisonMode;
public class DriverClass {
	public static void main(String args[] ) {
		for(int i = 1; i<=10; i++)
		{
			RationalNumber num1 = new RationalNumber(1, i);
			for(int j = 2 ; j<=10; j++)
			{
				RationalNumber num2 = new RationalNumber(j,3);
				RationalNumber res = num1.add(num2);
				boolean meow = num1.compareTo(num2, ComparisonMode.GREATER_THAN);
				System.out.println(num1.toString() + " + " +num2.toString() + " = " + res.toString() + " " + meow);
			}
		}
		System.out.println(new RationalNumber(-2.10).toString());
		
	}
}
