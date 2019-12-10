import java.util.*;
public class FractionLibrary
{
/*
	Antonio Palmeros
	November 24, 2019

	this singleton class will make sure there is only one type of DecimalRationalNumber
	object.

	Instance Variables:
		instance
			stores and initiates a new instance of the FractionLibrary().
		map
			stores and initiates a new HashMap.

	Constructors:
		FractionLibrary()
			default FractionLibrary contructor.

	Methods:
		public static FractionLibrary getInstance()
			returns the newInstance method.

		public static FractionLibrary newInstance()
			returns the instance variable of "instance".

		public HashMap<Long, DecimalRationalNumber> getMap()
			returns the map instance variable.

		public DecimalRationalNumber get(long denominator)
			returns a DecimalRationalNumber object.

		public DecimalRationalNumber put(long denominator, DecimalRationalNumber fraction)
			returns a DecimalRationalNumber object that gets places in the map.

		public int size()
			returns the size of the map.
*/
	private static FractionLibrary instance = new FractionLibrary();
	private HashMap<Long, DecimalRationalNumber> map = new HashMap<Long, DecimalRationalNumber>();
	private HashMap<DecimalRationalNumber, Statistics> statMap = new HashMap<DecimalRationalNumber, Statistics>();

	private FractionLibrary(){}

	public static FractionLibrary getInstance()
	{
		return newInstance();
	}

	public static FractionLibrary newInstance()
	{
		return instance;
	}

	public HashMap<Long, DecimalRationalNumber> getMap()
	{
		return map;
	}

	public DecimalRationalNumber get(long denominator)
	{
		DecimalRationalNumber result;
		Statistics x;

		result = map.get(denominator);
		if (result != null)
		{
			x = statMap.get(result);
			x.incrementAccesses();
		}
		return result;
		/*
		DecimalRationalNumber x;
		x = new DecimalRationalNumber(1, denominator);

		if (x == null)
		{
			return null;
		}
		else
		{
			return x;
		}
		*/
	}

	public void put(long denominator, DecimalRationalNumber fraction)
	{
		DecimalRationalNumber result;
		Statistics stat;

		result = get(denominator);

		if (result == null)
		{
			map.put(denominator, fraction);
			stat = statMap.get(fraction);
			if (stat == null)
			{
				stat = new Statistics();
				statMap.put(fraction, stat);
			}
		stat.incrementStores();
		}
		map.put(denominator, fraction);
		//return map.put(denominator, fraction);
	}

	public int size()
	{
		return map.size();
	}

	public int getAccesses(DecimalRationalNumber get)
	{
		return 0;
	}

	public int getStores(DecimalRationalNumber get)
	{
		return 0;
	}

	private class Statistics
	{
		private int accesses;
		private int stores;

		public Statistics()
		{
		}

		public int incrementAccesses()
		{
			return 0;
		}

		public int incrementStores()
		{
			return 0;
		}

		public int getAccesses()
		{
			return 0;
		}

		public int getStores()
		{
			return 0;
		}
	}

}