public class DecimalRationalNumber
{
/*
	Antonio Palmeros
	November 11, 2019

	this class will divide 1 by any denominator and return a decimal up to 10 digits.

	Instance Variables:
		MAXIMUM_SCALE
			stores the max digits of the decimal numerator.
		numerator
			stores a long of the numerator.
		denominator
			stores a long of the denominator
		scale
			the byte that stores where the decimal point will go in the decimal number.

	Constructors:
		DecimalRationalNumber()
			default constructor

		DecimalRationalNumber(String value)
			takes a String value and converts it to a long
			without leading or trailing zeroes.

		DecimalRationalNumber(long numerator, long denominator)
			takes a numerator and a denominator. calls an object of DecimalRationalNumber(long denominator)
			and multiples it by the long numerator parameter.
			the new numerator is the result of this with the scale adjusted.

	Methods:
		private DecimalRationalNumber convertToDecimalFraction(long denominator)
			this method takes a denominator and divides it by one to get the decimal
			number to a max of ten decimal places

		public byte getMaximumScale()
			returns the MAXIMUM_SCALE variable

		public long getNumerator()
			returns the numerator variable.

		public long getScale()
			returns the scale variable.

		public String getAsString()
			retuns the numerator as a string
			and adds a decimal point and/or ads a negative sign

		private void setNumerator(long numerator)
			sets the numerator instance variable

		private void setScale(byte scale)
			sets the scale instance variable

		public String toString()
			returns GetAsString() method.

	Modification History:
		November 10, 2019
			Original version.

		November 11, 2019
			Added two new constructors, DecimalRationalNumber(String)
			and DecimalRationalNumber(long, long)

		November 17 2019
			Added DecimalRationalNumber()
			Added private DecimalRationalNumber convertToDecimalFraction(long denominator)
			Added private void setNumerator(long numerator)
			Added private void setScale(byte scale)
			Removed the DecimalRationalNumber(long numerator) constructor

			The method private DecimalRationalNumber convertToDecimalFraction(long denominator),
			now does what the DecimalRationalNumber(long) used to do.

			DecimalRational Number(long, long) now calls
			the method, private DecimalRationalNumber convertToDecimalFraction(long denominator)

*/
	private static final byte MAXIMUM_SCALE = 10;
	private long numerator;
	private long denominator;
	private byte scale;

	private DecimalRationalNumber(){}

	public DecimalRationalNumber(String value)
	{
		this(value, getMaximumScale() , false);
	}

	public DecimalRationalNumber(String value, byte maximumScale, boolean round)
	{
		try
		{
			int indexAtDecimal;
			String str1;
			StringBuilder str2;
			String str2ToString;
			String str3;
			StringBuilder str4;
			String str4ToString;
			String addStrings;
			StringBuilder str;
			String toStringStr;

			indexAtDecimal = value.indexOf(".");
			str1 = value.substring(indexAtDecimal);
			str2 = new StringBuilder(str1);

			while (str2.length() > 1 && str2.charAt(str2.length()-1) == '0')
			{
				str2.setLength(str2.length()-1);
			}

			str2ToString = str2.toString();
			str3 = value.substring(0, indexAtDecimal);
			str4 = new StringBuilder(str3);

			while (str4.length() > 0 && str4.charAt(0) == '0')
			{
				str4.deleteCharAt(0);
			}

			str4ToString= str4.toString();
			addStrings = str4ToString + str2ToString;
			str = new StringBuilder(addStrings);

			for(int i=0; i<str.length(); i++)
			{
				if (str.charAt(i) == '.')
				{
					str.deleteCharAt(i);
				}
			}

			toStringStr = str.toString();
			numerator = Long.parseLong(toStringStr);
			scale = (byte)(str2ToString.length()-1);

			while (round == true && scale >= maximumScale + 1)
			{
				numerator = numerator + 5;
				numerator = numerator / 10;
				scale = (byte)(scale - 1);
			}

		}
		catch(NullPointerException npe)
		{
			throw new IllegalArgumentException("the parameter has a null value" + npe);
		}
		catch(NumberFormatException nfe)
		{
			throw new IllegalArgumentException("Number format of the string is invalid" + nfe);
		}
		catch(IllegalArgumentException iae)
		{
			throw new IllegalArgumentException(iae.getMessage());
		}
	}

	public DecimalRationalNumber(long numerator, long denominator)
	{
		DecimalRationalNumber obj;
		obj = convertToDecimalFraction(Math.max(1,Math.abs(denominator)));

		if (denominator == 0)
		{
			throw new IllegalArgumentException("Denominator cannot be 0");
		}
		if (denominator < 0)
		{
			numerator = -numerator;
			denominator = -denominator;
		}

		this.numerator = this.numerator * numerator;
		numerator = numerator * (obj.getNumerator());

		while (obj.getScale() > 0 &&(this.numerator % 10) == 0)
		{
			scale = (byte)(scale + 1);
		}
	}

	private DecimalRationalNumber convertToDecimalFraction(long denominator)
	{
		long dividend;
		long remainder;
		long quotient;
		scale = 0;
		dividend = 1;
		numerator = 0;
		if (denominator == 1)
		{
			numerator = 1;
		}
		remainder = dividend % denominator;
		while (remainder != 0 && scale <= getMaximumScale())
		{
			dividend = remainder * 10;
			quotient = dividend / denominator;
			remainder = dividend % denominator;
			numerator = (numerator * 10) + quotient;
			scale = (byte) (scale + 1);
		}
		if (scale > getMaximumScale())
		{
			numerator = numerator + 5;
			numerator = numerator / 10;
			scale = (byte)(scale + 1);
		}

		FractionLibrary y;
		y = FractionLibrary.getInstance();

		if (y.getMap().containsKey(denominator) != true)
		{
			DecimalRationalNumber x;
			x = new DecimalRationalNumber();
			y.put(denominator, x);
			return x;
		}
		else
		{
			return null;
		}
	}

	public boolean equals(DecimalRationalNumber other)
	{
		long thisNumerator;
		long otherNumerator;
		thisNumerator = this.getNumerator();
		otherNumerator = other.getNumerator();
		return thisNumerator == otherNumerator;
	}

	public int hashCode()
	{
		return getAsString().hashCode();
	}

	public int compareTo(DecimalRationalNumber other)
	{
		if (other == null)
		{
			throw new IllegalArgumentException("DecimalRationalNumber object has null type");
		}

		return (int)minus(other);
	}

	public long plus(DecimalRationalNumber other)
	{
		long thisNumerator;
		long otherNumerator;
		thisNumerator = this.getNumerator();
		otherNumerator = other.getNumerator();

		return thisNumerator + otherNumerator;
	}

	public long changeSign(DecimalRationalNumber cs)
	{
		return (long)(-1 * cs.getNumerator());
	}

	public long minus(DecimalRationalNumber other)
	{
		long thisNumerator;
		long otherNumerator;
		thisNumerator = this.getNumerator();
		otherNumerator = other.getNumerator();
		if (thisNumerator > otherNumerator)
		{
			return thisNumerator - otherNumerator;
		}
		else
		{
			return otherNumerator - thisNumerator;
		}
	}

	public long times(DecimalRationalNumber other)
	{
		long thisNumerator;
		long otherNumerator;
		thisNumerator = this.getNumerator();
		otherNumerator = other.getNumerator();

		return thisNumerator * otherNumerator;
	}

	public long divideBy(DecimalRationalNumber other)
	{
		long thisNumerator;
		long otherNumerator;
		thisNumerator = this.getNumerator();
		otherNumerator = other.getNumerator();
		if (thisNumerator > otherNumerator)
		{
			return thisNumerator / otherNumerator;
		}
		else
		{
			return otherNumerator / thisNumerator;
		}
	}

	public boolean isZero()
	{
		DecimalRationalNumber other;
		other = new DecimalRationalNumber();

		return other.getNumerator() == (long)0;
	}

	public boolean isLessThanZero()
	{
		DecimalRationalNumber other;
		other = new DecimalRationalNumber();
		if (other.getNumerator() < 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean isGreaterThanZero()
	{
		DecimalRationalNumber other;
		other = new DecimalRationalNumber();
		if (other.getNumerator() > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/*
		DRN y;
		y = Constants.Tenth.get()

		while (!y.equals(Constants.One.get())
		{
			y = y.plus(Constants.Tenth.get());
		}
	*/


	public static byte getMaximumScale()
	{
		return MAXIMUM_SCALE;
	}

	public long getNumerator()
	{
		return this.numerator;
	}

	public byte getScale()
	{
		return this.scale;
	}

	public String getAsString()
	{
		int sign;
		StringBuilder result;
		sign = Long.signum(numerator);

		numerator = Math.abs(numerator);
		result = new StringBuilder("" + numerator);

		while (result.length() < scale)
		{
			result.insert(0, "0");
		}
		if (scale > 0)
		{
			result.insert(result.length() - scale, ".");
		}
		if (sign < 0)
		{
			result.insert(0, "-");
		}
		return result.toString();
	}

	private void setNumerator(long numerator)
	{
		this.numerator = numerator;
	}

	private void setScale(byte scale)
	{
		this.scale = scale;
	}

	public String toString()
	{
		return getAsString();
	}
	public static void main(String[] args)
	{
		DecimalRationalNumber DRN;
		DRN = new DecimalRationalNumber("0.12222222", (byte) 6, true);

		System.out.println(DRN.changeSign(DRN));

	}
}
