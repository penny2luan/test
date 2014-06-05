package caesar;

public class CaesarBox
{
    private static final int LETTERS_IN_ALPHABET = 'Z' - 'A' + 1;
    
    private int shift;
    
    // precondition: 'A' <= shift < 'Z'
    public CaesarBox(int shift)
    {
        this.shift = shift;
    }
    
    public String encrypt(String plaintext)
    {
        String cipherText = "";
        for(int i = 0; i < plaintext.length(); i++)
        {
            char c = plaintext.charAt(i);
            boolean isUpperCaseLetter = 'A' <= c && c <= 'Z';
            if(isUpperCaseLetter)
            {
                int shiftedC = c - 'A';
                shiftedC += shift;
                shiftedC %= LETTERS_IN_ALPHABET;
                shiftedC += 'A';
                cipherText += (char) shiftedC;
            }
            else
                cipherText += c;
        }
        return cipherText;
    }
}
