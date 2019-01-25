import java.util.Random;

public class TRD1123 extends Player {
    private char letter;
    private String name;
    private Move bestMove=null;

    public TRD1123(char letter)
    {
        super("TRD1123",letter);
    }

    public Move getMove(Board board) {
        // HOW ARE WE GONNA GRADE THIS FRICKEN BOOOOARD??????
        int lastScore=0;
        for(int z=0;z<board.getBoard().length;z++) {
            for (int x=0; x< board.getBoard()[0][0].length;x++) {
                if(z==0&&x==0) {//checks to see if its the first iteration, then sets that to bestMove for comparison
                    bestMove=new Move(x,z);
                }
                board.makeMove(new Move(x,z), letter);//drops piece into board for grading later
                if(boardGrader(board)>=lastScore) {//checks to see if this next move is better than our last. If so, it becomes bestMove, and lastScore equals boardGrader(board)
                    bestMove=new Move(x,z);
                    lastScore=boardGrader(board);
                }
            }
        }
        return bestMove;
    }
/*
HOW TO MAKE GRADER:

Double for loop, x and z
board.droppiece( that move);
grade the board
look through 4 possibilities for each board, then return whichever has the best probability of winning.
Look for our worst for the opponent's move, then get a counter move.

IM STILL NOT CLEAR ON THE GRADING PROCESS

board .getBoard @ location , then change it to a hyphen
whichever move has the highest score, show that as the Move bestMove
 */
    public int boardGrader(Board board){
        for(int x=0;x<board.getBoard().length;x++) {
            for (int z=0;z<board.getBoard().length;z++) {

            }
        }
        return 0;
    }

    public Player freshCopy()
    {
        return new RandomComputer(letter);
    }
}
