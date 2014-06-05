package decryption;

import java.util.ArrayList;
import java.util.List;

//BRING YOUR TEXTBOOKS
public class DecoderRing {
	
	private static final int CHAR_TO_INT = 97;
	private List<Character> knownFreq = new ArrayList<Character>(),
				foundFreq = new ArrayList<Character>();
	
	public DecoderRing()
	{
		char[] charsByFrequency = 
			{'e', 't', 'a', 'o', 'i', 'n', 's', 'h', 'r', 'd', 'l', 'c', 'u',
				'm', 'w', 'f', 'g', 'y', 'p', 'b', 'v', 'k', 'j', 'x', 'q', 'z'};
		
		for(char ch : charsByFrequency)
			knownFreq.add(ch);
	}
	
	
	//i = character, derp[i] = frequency
	public int[] countFrequency(String code)
	{
		int[] derp = new int[26];
		for(int i = 0; i < code.length(); i++)
		{
			derp[(int) code.charAt(i) - CHAR_TO_INT]++;
		}
		return derp;
	}
	
	
	
	public String decode(String code)
	{
		String decoded = "";
		int[] frequency = countFrequency(code.toLowerCase());
		while(foundFreq.size() < knownFreq.size()) {
			int maxIndex = 0;
			for(int i=0; i<frequency.length; i++)
				if(frequency[i] > frequency[maxIndex])
					maxIndex = i;
			foundFreq.add((char) (maxIndex + CHAR_TO_INT));
			frequency[maxIndex] = 0;
		}
		
		for(int i=0; i < code.length(); i++) {
			decoded += knownFreq.get(foundFreq.indexOf(code.toLowerCase().charAt(i)));
		}
		return decoded;
	}
	
	public static void main(String[] args)
	{
		DecoderRing dr = new DecoderRing();
		System.out.println(dr.decode(""));
	}

}
