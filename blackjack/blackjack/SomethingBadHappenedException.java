package blackjack;

public class SomethingBadHappenedException extends RuntimeException
{
    private static final long serialVersionUID = 6201663068056413511L;

    public SomethingBadHappenedException(String s)
    {
        super(s);
    }
}
