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
        //Move lastMove = null;
        for(int z=0;z<board.getBoard().length;z++) {
            for (int x=0; x< board.getBoard()[0][0].length;x++) {
                if(z==0&&x==0) {//checks to see if its the first iteration, then sets that to bestMove for comparison-VK
                    bestMove=new Move(x,z);
                }
                if(!(board.isFull(new Move(x,z)))) {
                    location=board.makeMove(new Move(x, z), letter);//drops piece into board for grading later-VK
                }
                BoardGrader boardGrader=new BoardGrader(board,location,letter,0,0);
                score=(x+", "+z+":"+boardGrader.boardScorer(board));
                if(boardGrader.boardScorer(board)>lastScore) {//checks to see if this next move is better than our last. If so, it becomes bestMove, and lastScore equals boardGrader(board)-VK
                    //lastMove = new Move(x, z)
                    if(location.x<X_SIZE&&location.y<Y_SIZE&&location.z<Z_SIZE) {
                        board.setLocation(location, board.EMPTY);
                    }

                    /*if(x < board.getBoard().length) {
                        if(z < board.getBoard().length) {
                            if(board.getBoard()[x][location.y][z] != '-') {
                                bestMove=new Move(x,z);
                                lastScore=boardGrader(board);
                            }
                        }
                    }*/
                }

            }
        }
        return bestMove;//returns what boardGrader's best score is-VK
    }



    public Player freshCopy() {
        return new TRD1123(letter);
    }

    public String getScore() {
        return score;
    }


}