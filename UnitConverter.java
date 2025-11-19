import java.util.Scanner;
import java.util.regex.Pattern;

public class UnitConverter {
	
	private static final Pattern UNIT_CONVERTER_PATTERN = Pattern.compile("^convert\\s+(\\S+)\\s+(\\S+)\\s+([+-]?\\d*\\.?\\d+)$");
	private static final Pattern TWO_UNIT_CONVERTER_PATTERN = Pattern.compile("^convert\\s+(\\S+)\\s+(\\S+)\\s+([+-]?\\d*\\.?\\d+),\\s*then\\s+convert\\s+(\\S+)\\s+(\\S+)$");
	
	public static void main(String[] args) {
		System.out.println("This is a unit converter");
		
		String input = "";
		
		try(Scanner scn = new Scanner(System.in);)
		{	
			
			while(!input.equals("exit"))
			{
			instructions();
			input = scn.nextLine().toLowerCase();
			boolean unitConvert = UNIT_CONVERTER_PATTERN.matcher(input).matches();
			boolean TwoUnitConvert = TWO_UNIT_CONVERTER_PATTERN.matcher(input).matches();
			
			if(unitConvert == false && TwoUnitConvert == false)
			{
				System.out.println("Invalid input");
				continue;
			}
			System.out.println(input);
			}
		}
		catch(Exception e)
		{
			System.err.println("You're input did not match the correct format");
		}
	}


	private static void instructions() {
		System.out.println("Convert <unit_from> <unit_to> <value>");
		System.out.println("Or convert <unit_from> <unit_to> <value>, then convert <unit_from> <unit_from>");
		
	}

}
