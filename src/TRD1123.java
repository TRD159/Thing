import java.util.Random;

public class TRD1123 extends Player {
    private char letter;
    private String name;
    private Move bestMove=null;

    public TRD1123(char letter)
    {
        super("TRD1123",letter);
    }

    /*
    TODO 1/28/19-WE need to finish boardGrader, check comments within the method for directions -VK
     */

    public Move getMove(Board board) {
        int lastScore=0;
        for(int z=0;z<board.getBoard().length;z++) {
            for (int x=0; x< board.getBoard()[0][0].length;x++) {
                if(z==0&&x==0) {//checks to see if its the first iteration, then sets that to bestMove for comparison-VK
                    bestMove=new Move(x,z);
                }
                board.makeMove(new Move(x,z), letter);//drops piece into board for grading later-VK
                if(boardGrader(board)>=lastScore) {//checks to see if this next move is better than our last. If so, it becomes bestMove, and lastScore equals boardGrader(board)-VK
                    bestMove=new Move(x,z);
                    lastScore=boardGrader(board);
                }
                else {
                    board.makeMove(new Move(x,z),'-');
                }
            }
        }
        return bestMove;//returns what boardGrader's best score is-VK
    }
    /*
    HOW TO MAKE GRADER:(hyphen indicates completion)
    Double for loop, x and z-
    board.droppiece( that move);-
    grade the board
    look through 4 possibilities for each board, then return whichever has the best probability of winning.
    Look for our worst for the opponent's move, then get a counter move.
    ******I'M STILL NOT CLEAR ON THE GRADING PROCESS******
    board .getBoard @ location , then change it to a hyphen-
    whichever move has the highest score, show that as the Move bestMove-
    return bestMove-
    -VK
     */



    public int boardGrader(Board board){
        for(int x=0;x<board.getBoard().length;x++) {
            for (int z=0;z<board.getBoard().length;z++) {
                /*Will go through the current instance (called in getMove) of the board. Checks through the board, and finds the grade. I'm thinking that we should
                check the board based on our occurrences to win. (We would only go four deep) Whichever has the most occurrences to win, we count as the best, return that score, then put that move as the getMove (already done in getMove)
                 -VK
                 */

                

            }
        }
        return 0;
    }

    public Player freshCopy()
    {
        return new RandomComputer(letter);
    }
}