public enum Constants
{
/*
	Antonio Palmeros
	November 17, 2019

	this enum will provide constants of common DecimalRationalNumber objects.

	Instance Variables:
		private DecimalRational value;
			stores a DecimalRational object.

	Constructors:
		Constants(int numerator)
			calls the Constants(int, int) constructor.

		Constants(int numerator, int denominator)
			initiates a new DecimalRationalNumber object to the instance variable.

	Methods:
		public DecimalRationalNumber getValue()
			this method returns a DecimalRationalNumber object from the
			instance variable;

		public static String Constants(long numerator, byte scale)
			this method checks if the DecimalRationalNumber object has the same numerator
			and scale as one of the enum values and returns the name of that enum value,
			the method returns null if it doesn't.

	Modification History:
		November 14, 2019
			Original version.

		November 17, 2019
			Added the public static String Constants(long, byte) method.

*/

	Zero(0),
	One(1),
	Ten(10),
	Hundred(100),
	Thousand(1000),
	Tenth(1,10),
	Hundredth(1,100),
	Thousandth(1,1000);

	private DecimalRationalNumber value;

	private Constants(int numerator)
	{
		this(numerator, 1);
	}

	private Constants(int numerator, int denominator)
	{
		this.value = new DecimalRationalNumber(numerator, denominator);
	}

	public DecimalRationalNumber get()
	{
		return this.value;
	}

	public static Constants get(long numerator, byte scale)
	{
		Constants[] array;
		array = Constants.values();
		for (int i=0; i<array.length; i++)
		{
			if (numerator == array[i].get().getNumerator() && scale == array[i].get().getScale())
			{
				return array[i];
			}
		}

		return null;
	}
}