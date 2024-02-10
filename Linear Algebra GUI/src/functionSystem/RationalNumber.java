package functionSystem;


import java.math.BigInteger;
import functionSystem.ComparisonMode;

 public class RationalNumber {
	BigInteger numerator;
	BigInteger denominator;
	
	static long greatestCommonDivisor(long a, long b)
	{
		long r = a% b;
		while(r > 0)
		{
			a = b;
			b = r;
			r = a%b;
		}
		return b;
	}
	/*
	 * Converts decimal into fraction a/b (real number i swear). 
	 * Does not work well if you input a fraction in it
	 */
	public RationalNumber(Double input) {
		String owoString[] = String.valueOf(input).split("\\.");
		long whole = Long.valueOf(owoString[0]);
		input = Double.valueOf("0." + owoString[1]);
		if(input == 0)
		{
			numerator = BigInteger.valueOf(whole);
			denominator = BigInteger.valueOf(1);
			return;
		}
		
		long tempDenominator = (long)Math.pow(10, owoString[1].length());
		long tempNumerator = Long.valueOf(owoString[1]);
		
		long gcd = greatestCommonDivisor(tempDenominator, tempNumerator);
		tempNumerator /= gcd;
		tempDenominator /= gcd;
		BigInteger scale = BigInteger.valueOf(whole/Math.abs(whole));
		this.denominator = BigInteger.valueOf(tempDenominator);
		this.numerator = BigInteger.valueOf(Math.abs(whole)*tempDenominator + tempNumerator).multiply(scale);
		
	}
	/*
	 * Inserts a whole number (integer)
	 */
	public RationalNumber(Integer input) {
		this.numerator = BigInteger.valueOf(input);
		this.denominator =BigInteger.valueOf(1);
	}

	static void divideByZero(Long denominator) throws Exception
	{
		if(denominator == 0)
			throw new Exception("Can not divide by zero");
	}
	/*
	 * Insert rational number with fraction as it's input
	 */
	
	public RationalNumber(Long numerator, Long denominator) 
	{
		try {
			divideByZero(denominator);
			BigInteger scale = BigInteger.valueOf(denominator);
			scale = scale.abs().divide(scale);
			this.numerator = BigInteger.valueOf(numerator).multiply(scale);
			this.denominator = BigInteger.valueOf(denominator).abs();
			//System.out.println(denominator);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	public RationalNumber(int numerator, int denominator)
	{
		try {
			divideByZero(Long.valueOf(denominator));
			BigInteger scale = BigInteger.valueOf(denominator);
			scale = scale.abs().divide(scale);
			this.numerator = BigInteger.valueOf(numerator).multiply(scale);
			this.denominator = BigInteger.valueOf(denominator).abs();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	static boolean compareNumber(BigInteger inClassNum, BigInteger outClassNum, ComparisonMode comparisonMode)
	{
		boolean isEqual = inClassNum.compareTo(outClassNum) == 0;
		boolean isMore = inClassNum.compareTo(outClassNum) > 0;
		boolean isLess = inClassNum.compareTo(outClassNum) < 0;
		switch (comparisonMode) {
		case GREATER_THAN:
			return isMore;
		case LESS_THAN:
			return isLess;
		case EQUAL:
			return isEqual;
		case NOT_EQUAL:
			return !isEqual;
		case GREATER_THAN_OR_EQUAL:
			return isMore || isEqual;
		case LESS_THAN_OR_EQUAL:
			return isLess || isEqual;
		}
		
		return true;
	}
	public boolean compareTo(RationalNumber input, ComparisonMode comparisonMode)
	{
		BigInteger inClassNum = numerator.multiply(input.getDenominator());
		
		BigInteger outClassNum = input.getNumerator().multiply(denominator);
		return compareNumber(inClassNum, outClassNum, comparisonMode);
		
	}
	public boolean compareTo(int input, ComparisonMode comparisonMode)
	{
		RationalNumber temp = new RationalNumber(input);
		BigInteger inClassNum = numerator.multiply(temp.getDenominator());
		BigInteger outClassNum = temp.getDenominator().multiply(numerator);
		return compareNumber(inClassNum, outClassNum, comparisonMode);
	}
	public RationalNumber(BigInteger numerator, BigInteger denominator)
	{
		try {
			numerator.divide(denominator);
			this.numerator = numerator;
			this.denominator = denominator;
		} catch (ArithmeticException e) {
			e.printStackTrace();
		}
	}
	public String toString()
	{
		String outString = "";
		BigInteger whole = numerator.divide(denominator);
		BigInteger outNumerator = numerator.subtract(denominator.multiply(whole));
		BigInteger outDenominator = denominator;
		if(outNumerator.equals(BigInteger.valueOf(0)))
		{
			return whole.toString();
		}
		String sign ="";
		if(numerator.compareTo(BigInteger.valueOf(0)) < 0)
		{
			outNumerator = outNumerator.abs();
			whole = whole.abs();
			sign = "-";
		}
		outString += outNumerator + "/" + outDenominator;
		if(!whole.equals(BigInteger.valueOf(0)))
		{
			outString = sign + "(" + whole + " " + outString + ")";
			return outString;
		}
		return  sign+ outString;
	}
	public void printlnNumber()
	{
		System.out.println(numerator + "/" + denominator);
	}
	public void printNumber()
	{
		System.out.print(numerator + "/" + denominator);
	}

	/*
	 * Multiply the number that takes rational number as it's input
	 */
	public RationalNumber add(RationalNumber input)
	{
		BigInteger resultDenominator = input.getDenominator().multiply(denominator);
		BigInteger resultNumerator = input.getNumerator().multiply(denominator).
				add(numerator.multiply(input.getDenominator()));
		//System.out.print(input.getNumerator().multiply(denominator) + " " + numerator.multiply(input.getDenominator())+ " = " + resultNumerator + " | ");
		return simplify(resultNumerator, resultDenominator);
	}
	public RationalNumber add(Long input)
	{
		BigInteger resultNumerator = numerator.add(denominator.multiply(BigInteger.valueOf(input)));
		return new RationalNumber(resultNumerator, denominator);
	}
	public RationalNumber subtract(RationalNumber input)
	{
		BigInteger resultNumerator = numerator.multiply(input.getDenominator()).subtract(input.getNumerator().multiply(denominator));
		
		BigInteger resultDenominator = denominator.multiply(input.getDenominator());
	
		//System.out.println(input.getNumerator().multiply(denominator) + " ");
		
		return simplify(resultNumerator, resultDenominator);
	}
	public RationalNumber multiply(RationalNumber input)
	{
		BigInteger resultNumerator = numerator.multiply(input.getNumerator());
		BigInteger resultDenominator = denominator.multiply(input.getDenominator());
		return simplify(resultNumerator, resultDenominator);
	}
	public RationalNumber multiply(Double input)
	{
		RationalNumber toMultiply = new RationalNumber(input);
		BigInteger resultNumerator = numerator.multiply(toMultiply.getNumerator());
		BigInteger resultDenominator = denominator.multiply(toMultiply.getDenominator());
		return simplify(resultNumerator, resultDenominator);
	}
	public RationalNumber divide(RationalNumber input)
	{
		BigInteger resultNumerator = numerator.multiply(input.getDenominator());
		BigInteger resultDenominator = denominator.multiply(input.getNumerator());
		return simplify(resultNumerator, resultDenominator);
	}
	public RationalNumber divide(Double input)
	{
		RationalNumber toMultiply = new RationalNumber(input);
		BigInteger resultNumerator = numerator.multiply(toMultiply.getDenominator());
		BigInteger resultDenominator = denominator.multiply(toMultiply.getNumerator());
		return simplify(resultNumerator, resultDenominator);
	}
	void modify(RationalNumber input)
	{
		this.numerator = input.getNumerator();
		this.denominator = input.getDenominator();
	}
	static RationalNumber simplify(BigInteger numerator, BigInteger denominator)
	{
		BigInteger  gcd = denominator.gcd(numerator);
		
		BigInteger scale = denominator.divide(denominator.abs());
		return new RationalNumber(numerator.divide(gcd).multiply(scale), denominator.divide(gcd).abs());
		
	}
	public RationalNumber absolute()
	{
		return new RationalNumber(numerator.abs(), denominator);
	}
	/*
	 * I'm sure you can convert it to anything you want to
	 */
	public BigInteger getNumerator()
	{
		return numerator;
	}
	/*
	 * I'm sure you can convert it to anything you want to
	 */
	public BigInteger getDenominator()
	{
		return denominator;
	}
	public BigInteger getWholeNumber()
	{
		return numerator.divide(denominator);
	}
}
