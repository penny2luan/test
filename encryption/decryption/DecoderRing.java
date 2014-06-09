package decryption;

import java.util.Scanner;

public class DecoderRing {
	
	private static Scanner in;
	private static final int CHAR_TO_INT = 65;
	private static int[] foundFreq = new int[26];
	private static char changeTo, changed;
	private static String code, decoded, lastState;
	
	private static void countFrequency(String code)
	{
		for(int i = 0; i < code.length(); i++)
		{
			if(code.charAt(i) != ' ' && code.charAt(i) != '\'' && code.charAt(i) != '.')
				foundFreq[(int) code.toUpperCase().charAt(i) - CHAR_TO_INT]++;
		}
	}
	
	private static void printFrequency(String code)
	{
		countFrequency(code);
		int c = CHAR_TO_INT;
		for(int i=0; i<foundFreq.length; i++) 
		{
			if(foundFreq[i] != 0)
				System.out.println(((char) c) + " - " + foundFreq[i]);
			c++;
		}
	}
	
	public static void replace(char letterToChange, char letterToChangeTo)
	{
//		decoded.replace(letterToChange, newChar)
	}
	
	public static void undo()
	{
//		replace(changeTo, changed);
		decoded = decoded.replace(changeTo, changed);
	}
	
		
	public static void main(String[] args)
	{
		code = "HI MY NAME IS JASON I LIKE PIE AND ALL SORTS OF GOOD'. FOOD AND IM BORED SO IM GOING TO MAKE THIS SUPER LONG WHEEEeeeeeeeeejubguheeeeE";
		decoded = code;
		in = new Scanner(System.in);
		printFrequency(code);
		
		
		boolean done = false;
		while(!done) {
			System.out.println("Original | " + code);
			System.out.println("Working  | " + decoded);
			System.out.println("Please select action: ");
			System.out.println("    replace    undo    finish");
			String input = in.next();
			switch(input.toLowerCase()) {
			case "replace":
				System.out.println("Input character to change: ");
				changed = in.next().toUpperCase().charAt(0);
				System.out.println("Input character to change to: ");
				changeTo = in.next().toUpperCase().charAt(0);
//				replace(c1, c2);
				lastState = decoded;
				decoded = decoded.replace(changed, changeTo);
				break;
			case "undo":
//				undo();
				if(lastState != null && lastState != decoded)
					decoded = lastState;
				else
					System.out.println("Cannot undo.");
				break;
			case "finish":
				System.out.println("Final result | " + decoded);
				done = true;
				break;
			}
		}
	}

}
