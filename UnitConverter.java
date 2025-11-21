import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UnitConverter {
	
	//create regular expression for the two different types of inputs
	//fyi this is awful... I'm sure you know.
	static String REGEX = "^convert\\s+(\\w+)\\s+(\\w+)\\s+([+-]?\\d*\\.?\\d+)$";
	static String REGEX2 = "^convert\\s+(\\w+)\\s+(\\w+)\\s+([+-]?\\d*\\.?\\d+)\\s+(\\w+)\\s+(\\w+)\\s+(\\w+)\\s+(\\w+)+$";
	private static final Pattern UNIT_CONVERTER_PATTERN = Pattern.compile(REGEX);
	private static final Pattern TWO_UNIT_CONVERTER_PATTERN = Pattern.compile(REGEX2);
	
	//variables for the different units
	public static final String[] length = {"meters", "meter", "m", "foot", "feet","ft", "km", "kilometers", "kilometer", "miles","mile"};
	public static final String[] weight = {"kilograms","kilogram","kg", "pounds","pound","lbs", "gram", "grams", "ounces","ounce"};
	public static final String[] temp = {"celsius", "c", "fahrenheit","f", "kelvin","k"};
	public static final String[] currency = {"usd", "eur", "bitcoin"};
	public static final String[] speed = {"mph","mp/h","km/h", "kmh","mach"};
	
	
	public static void main(String[] args) {
		System.out.println("This is a unit converter");//tell what program is
		showOptions();//tells user what we can convert
		Scanner scn = new Scanner(System.in);//create scanner obj
		String input = "";//create a variable for input
		while(true)
		{
			instructions();//method to give directions
			input = scn.nextLine().toLowerCase();//get input from user
			if(input.equals("exit"))//checks to see if input is exit
				break;
			
			try
			{		
				boolean unitConvert = UNIT_CONVERTER_PATTERN.matcher(input).matches();//using pattern check the inputs
				boolean TwoUnitConvert = TWO_UNIT_CONVERTER_PATTERN.matcher(input).matches();
				
				
				if(unitConvert == false && TwoUnitConvert == false)//check if both are false
					{
						System.err.println("Invalid input, please follow template.");//if false tell user they are bad
						continue;//restart loop
					}
				
				String[] inputSplitted = input.split(" ");//split input into an array
				checkUnit(inputSplitted);
				
				double value = Double.parseDouble(inputSplitted[3]);//create a double instead of string for input
				checkNumber(value);
				
				double newValue = convertInput(inputSplitted[1],inputSplitted[2],value);
				System.out.printf("%n%.2f %s is %.2f %s%n",value,inputSplitted[1],newValue,inputSplitted[2]);
				if(inputSplitted.length == 8)
					{
						double finalValue = convertInput(inputSplitted[6],inputSplitted[7], newValue);
						System.out.printf("%.2f %s is %.2f %s%n",newValue,inputSplitted[6],finalValue,inputSplitted[7]);
					}
	
				}
				
			catch(Exception e)//check for everything!
			{
				System.err.println(e.toString());
				continue;
			}
			
			scn.close();
		}
		System.out.println("Thank you for using my unit convert... Updates coming soon!");
	}

	//this is where the math happens. This method will return the a new double as the new value
	private static double convertInput(String unitFrom, String unitTo, double value) {
		double newValue = 0;//gives a value back
		if(Arrays.asList(length).contains(unitFrom)) //checks for lengths
		{
			switch(unitFrom)
			{
				case "meters","meter","m":
					switch(unitTo)
					{
						case"meters","meter", "m":
							newValue = value;
							break;
						case "foot", "feet","ft":
							newValue = value * 3.28084;
							break;
						case "kilometer","km","kilometers":
							newValue = value * .001;
							break;
						case"mile","miles":
							newValue = value * .000621;
							break;
					}
					break;
				case "foot","feet","ft":
					switch(unitTo)
					{
						case"meters","meter", "m":
							newValue = value * .3048;
							break;
						case "foot", "feet","ft":
							newValue = value;
							break;
						case "kilometer","km","kilometers":
							newValue = value * .0003048;
							break;
						case"mile","miles":
							newValue = value * 0.000189394;
							break;
					}
					break;
				case "kilometer","kilometers","km":
					switch(unitTo)
					{
						case"meters","meter", "m":
							newValue = value * 1000;
							break;
						case "foot", "feet","ft":
							newValue = value * 3280.84;
							break;
						case "kilometer","km","kilometers":
							newValue = value;
							break;
						case"mile","miles":
							newValue = value * 0.621371;
							break;
					}
					break;
				case "mile","miles":
					switch(unitTo)
					{
						case"meters","meter", "m":
							newValue = value * 1609.34;
							break;
						case "foot", "feet","ft":
							newValue = value * 5280;
							break;
						case "kilometer","km","kilometers":
							newValue = value * 1.60934;
							break;
						case"mile","miles":
							newValue = value;
							break;
					}
					break;
			}
	
		}
		else if(Arrays.asList(weight).contains(unitFrom)) //checks for weights
		{
			switch(unitFrom)
			{
				case "kilograms","kilogram","kg":
					switch(unitTo)
					{
						case"pounds","pound","lbs":
							newValue = value * 2.20462;
							break;
						case "gram", "grams":
							newValue = value * 1000;
							break;
						case "ounces","ounce":
							newValue = value * 35.274;
							break;
						case"kilograms","kilogram","kg":
							newValue = value;
							break;
					}
					break;
				case "pounds","pound","lbs":
					switch(unitTo)
					{
						case"pounds","pound","lbs":
							newValue = value;
							break;
						case "gram", "grams":
							newValue = value * 453.592;
							break;
						case "ounces","ounce":
							newValue = value * 16;
							break;
						case"kilograms","kilogram","kg":
							newValue = value * 0.453592;
							break;
					}
					break;
				case "gram", "grams":
					switch(unitTo)
					{
						case"pounds","pound","lbs":
							newValue = value * 0.00220462;
							break;
						case "gram", "grams":
							newValue = value;
							break;
						case "ounces","ounce":
							newValue = value * 0.035274;
							break;
						case"kilograms","kilogram","kg":
							newValue = value * 0.001;
							break;
					}
					break;
				case "ounces","ounce":
					switch(unitTo)
					{
						case"pounds","pound","lbs":
							newValue = value * 0.0625;
							break;
						case "gram", "grams":
							newValue = value * 28.3495;
							break;
						case "ounces","ounce":
							newValue = value;
							break;
						case"kilograms","kilogram","kg":
							newValue = value * 0.0283495;
							break;
					}
				break;
			}
		}
		else if(Arrays.asList(temp).contains(unitFrom)) //checks for temps 
		{
			switch(unitFrom)
			{
				case "celsius", "c":
					switch(unitTo)
					{
						case"celsius", "c":
							newValue = value;
							break;
						case "fahrenheit","f":
							newValue = ((value * 1.8) + 32);
							break;
						case "kelvin","k":
							newValue = value + 273.15;
							break;
					}
					break;
				case "fahrenheit","f":
					switch(unitTo)
					{
						case"celsius", "c":
							newValue = (value-32) * 1.8;
							break;
						case "fahrenheit","f":
							newValue = value;
							break;
						case "kelvin","k":
							newValue = (value-32) * .5555 + 273.15;
							break;
					}
					break;
				case "kelvin","k":
					switch(unitTo)
					{
						case"celsius", "c":
							newValue = value - 273.15;
							break;
						case "fahrenheit","f":
							newValue = (value - 273.15) * 1.8 + 32;
							break;
						case "kelvin","k":
							newValue = value;
							break;
					}
					break;
			}
		}
		else if(Arrays.asList(currency).contains(unitFrom)) //checks for currency 
		{
			switch(unitFrom)
			{
				case "usd":
					switch(unitTo)
					{
						case"usd":
							newValue = value;
							break;
						case "eur":
							newValue = value * .87;
							break;
						case "bitcoin":
							newValue = value * .000012;
							break;
					}
					break;
				case "eur":
					switch(unitTo)
					{
						case"usd":
							newValue = value * 1.15;
							break;
						case "eur":
							newValue = value;
							break;
						case "bitcoin":
							newValue = value * 0.000014;
							break;
					}
					break;
				case "bitcoin":
					switch(unitTo)
					{
						case"usd":
							newValue = value * 84126.04;
							break;
						case "eur":
							newValue = value * 73068.51;
							break;
						case "bitcoin":
							newValue = value;
							break;
					}
			}
		}
		else if(Arrays.asList(currency).contains(unitFrom)) //checks for currency 
		{
			switch(unitFrom)
			{
				case "mph","mp/h":
					switch(unitTo)
					{
						case"mph","mp/h":
							newValue = value;
							break;
						case "km/h", "kmh":
							newValue = value * 1.60934;
							break;
						case "mach":
							newValue = value * 0.00130332;
							break;
					}
					break;
				case "km/h", "kmh":
					switch(unitTo)
					{
						case"mph","mp/h":
							newValue = value * 0.621371;
							break;
						case "km/h", "kmh":
							newValue = value;
							break;
						case "mach":
							newValue = value * 0.000809848;
							break;
					}
					break;
				case "mach":
					switch(unitTo)
					{
						case"mph","mp/h":
							newValue = value * 767.269;
							break;
						case "km/h", "kmh":
							newValue = value * 1234.8;
							break;
						case "mach":
							newValue = value;
							break;
					}
					break;
			}
		}
		return newValue;
	}

	//this method checks if value entered is a positive number
	private static void checkNumber(double value) {
		if(value <= 0)
		{
			throw new IllegalArgumentException("Number to convert must be greater than 0");
		}
	}
	//this method checks if units are in the list we have to offer. Since the list are broken up into 
	//their own category we know that the unit converges will only happen within the correct ones.
	private static void checkUnit(String[] inputSplitted) {
		boolean isGood = false;
		if(inputSplitted.length == 4) //this checks if the input values for one converges
		{
			if(Arrays.asList(length).contains(inputSplitted[1]) 
					&& Arrays.asList(length).contains(inputSplitted[2])) //checks lengths
						isGood = true;
			else if(Arrays.asList(weight).contains(inputSplitted[1]) 
					&& Arrays.asList(weight).contains(inputSplitted[2])) //checks weights
						isGood = true;
			else if(Arrays.asList(temp).contains(inputSplitted[1]) 
					&& Arrays.asList(temp).contains(inputSplitted[2])) //checks temps
						isGood = true;
			else if(Arrays.asList(currency).contains(inputSplitted[1]) 
					&& Arrays.asList(currency).contains(inputSplitted[2])) //checks currency
						isGood = true;
			else if(Arrays.asList(speed).contains(inputSplitted[1]) 
					&& Arrays.asList(speed).contains(inputSplitted[2])) //checks currency
						isGood = true;
		}
		else if(inputSplitted.length == 8) //this checks if the input values for one converges
		{
			if(Arrays.asList(length).contains(inputSplitted[1]) 
					&& Arrays.asList(length).contains(inputSplitted[2])
					&& Arrays.asList(length).contains(inputSplitted[6])
					&& Arrays.asList(length).contains(inputSplitted[7])) //checks lengths
						isGood = true;
			else if(Arrays.asList(weight).contains(inputSplitted[1]) 
					&& Arrays.asList(weight).contains(inputSplitted[2])
					&& Arrays.asList(weight).contains(inputSplitted[6])
					&& Arrays.asList(weight).contains(inputSplitted[7])) //checks weights
						isGood = true;
			else if(Arrays.asList(temp).contains(inputSplitted[1]) 
					&& Arrays.asList(temp).contains(inputSplitted[2])
					&& Arrays.asList(temp).contains(inputSplitted[6])
					&& Arrays.asList(temp).contains(inputSplitted[7])) //checks temps
						isGood = true;	
			else if(Arrays.asList(currency).contains(inputSplitted[1]) 
					&& Arrays.asList(currency).contains(inputSplitted[2])
					&& Arrays.asList(currency).contains(inputSplitted[6])
					&& Arrays.asList(currency).contains(inputSplitted[7])) //checks currency
						isGood = true;
			else if(Arrays.asList(speed).contains(inputSplitted[1]) 
					&& Arrays.asList(speed).contains(inputSplitted[2])
					&& Arrays.asList(speed).contains(inputSplitted[6])
					&& Arrays.asList(speed).contains(inputSplitted[7])) //checks currency
						isGood = true;
		}
		if(isGood == false)
			throw new IllegalArgumentException("One of your units is not in our list or units do not match.");
	}
	
	//this prints out all the units you can use
	private static void showOptions() {
		System.out.println("Units we can convert");
		System.out.print("\nLength: ");
		for(String s:length)
		{
			System.out.print(s + ", ");
		}
		System.out.print("\nWeight: ");
		for(String s:weight)
		{
			System.out.print(s + ", ");
		}
		System.out.print("\nTemp: ");
		for(String s:temp)
		{
			System.out.print(s + ", ");
		}
		System.out.print("\nCurrency: ");
		for(String s:currency)
		{
			System.out.print(s + ", ");
		}
		System.out.print("\nSpeed: ");
		for(String s:speed)
		{
			System.out.print(s + ", ");
		}
		
	}
	//a simple method to give user instructions
	private static void instructions() {
		System.out.println("\n\nFollow one of the two layouts to convert units or type exit to quit");
		System.out.println("Convert <unit_from> <unit_to> <value>");
		System.out.println("Convert <unit_from> <unit_to> <value> then convert <unit_from> <unit_to>");
			
	}

}