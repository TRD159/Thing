import java.util.Random;

public class TRD1123 extends Player {
    private char letter;
    private String name;

    public TRD1123(char letter)
    {
        super("TRD1123",letter);
    }

    public Move getMove(Board board)
    {
        return null;
    }



    public Player freshCopy()
    {
        return new RandomComputer(letter);
    }
}
