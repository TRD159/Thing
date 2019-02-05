import java.util.Random;

public class TRD1123 extends Player {
    private char letter;
    private String name;
    private Move bestMove=null;
    public static final char EMPTY = '-', RED = 'R', BLUE = 'B', PLAYING = '-', TIE = 'T';
    public static final int X_SIZE=8,Y_SIZE=7, Z_SIZE = 8;
    char[][][]board;
    String score="";
    public TRD1123(char letter)
    {
        super("Craig",letter);
    }

    /*
    TODO: MAKE OUR MOVES BE NOT JUST UPWARD VERTICAL

    TODO: MAKE SURE THAT OUR MOVES DONT GO OUT OF BOUNDS

     */

    public Move getMove(Board board) {
        this.board=board.getBoard();
        int lastScore=0;
        Location location=null;
        bestMove=new Move((int)(Math.random()*7),(int)(Math.random()*7));
        //Move lastMove = null;
        for(int z=0;z<board.getBoard().length;z++) {
            for (int x=0; x< board.getBoard()[0][0].length;x++) {
                if(!(board.isFull(new Move(x,z)))) {
                    location=board.makeMove(new Move(x, z), letter);//drops piece into board for grading later-VK
                }
                BoardGrader boardGrader=new BoardGrader(board,location,letter,0,0);
                System.out.println(x+", "+z+":"+boardGrader.boardScorer(board, letter));
                //System.out.println(x + ", " + z + ": " + boardGrader.otherScorer(board));
                if(boardGrader.boardScorer(board, letter)>lastScore) {//checks to see if this next move is better than our last. If so, it becomes bestMove, and lastScore equals boardScorer(board)-VK
                    //lastMove = new Move(x, z)
                    if (!board.isFull(new Move(x, z))) {
                        bestMove = new Move(location.x, location.z);
                    }
                }
                lastScore = boardGrader.boardScorer(board, letter);
                //lastScore = boardGrader.otherScorer(board);
                if(location!=null) {
                    board.setLocation(location, Board.EMPTY);
                }
            }
        }
        if(!board.isFull(bestMove)){
            return bestMove;//returns what boardGrader's best score is-VK
        }
        return null;
    }

    //Todo 2/4/19
    //TODO: Craig scored a 95 on his test today, punish him for me
    //TODO: Why are our scores this low? All you need to do now is fix the boardScorer or otherScorer
    //Its getting better

    //TODO: Code the thing to look into the future. I have no idea what you did to set all this up, so it's up to you.`


    public Player freshCopy() {
        return new TRD1123(letter);
    }

    public String getScore() {
        return score;
    }


}