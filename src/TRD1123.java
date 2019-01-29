import java.util.Random;

public class TRD1123 extends Player {
    private char letter;
    private String name;
    private Move bestMove=null;
    public static final char EMPTY = '-', RED = 'R', BLUE = 'B', PLAYING = '-', TIE = 'T';
    public static final int X_SIZE=8,Y_SIZE=7, Z_SIZE = 8;
    char[][][]board;
    public TRD1123(char letter)
    {
        super("TRD1123",letter);
    }

    /*
    TODO 1/28/19-WE need to finish boardGrader, check comments within the method for directions -VK
     */

    public Move getMove(Board board) {
        this.board=board.getBoard();
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
        int score = 0;
        int count = 0;

        for(int z=0;z<board.getBoard().length;z++) {
            for (int y=0;y<board.getBoard()[0].length;y++) {
                for(int x=0;x<board.getBoard().length;x++) {
                    int lz = board.getBoard().length;
                    int lx = lz;
                    int ly = board.getBoard()[0].length;
                    /*Will go through the current instance (called in getMove) of the board. Checks through the board, and finds the grade. I'm thinking that we should
                    check the board based on our occurrences to win. (We would only go four scenarios deep) Whichever has the most occurrences to win, we count as the best,
                    return that score, then put that move as the getMove (already done in getMove)
                    -VK*/

                    //You remember how we coded win checks? We're gonna do that, except with scoring.
                    //I made a few examples for you. We're gonna be coding these together, in this format. -RK

                    if(board.getBoard()[z][y][x] != EMPTY) {
                        for(int xx = x; xx < board.getBoard()[0][0].length; xx++) {
                            if(board.getBoard()[z][y][xx] == letter) {
                                count++;
                            } else {
                                break;
                            }
                        }
                        score += (int)Math.pow(10, count - 1);
                        count = 0;

                        for(int yy = y; yy < board.getBoard()[0].length; yy++) {
                            if(board.getBoard()[z][yy][x] == letter) {
                                count++;
                            } else {
                                break;
                            }
                        }
                        score += (int)Math.pow(10, count - 1);
                        count = 0;

                        for(int zz = z; zz < board.getBoard().length; zz++) {
                            if(board.getBoard()[zz][y][x] == letter) {
                                count++;
                            } else {
                                break;
                            }
                        }
                        score += (int)Math.pow(10, count - 1);
                        count = 0;

                        for(int zx = 0; zx + z < lz && zx + x < lx; zx++) {
                            if(board.getBoard()[z + zx][y][x + zx] == letter) {
                                count++;
                            } else {
                                break;
                            }
                        }
                        score += (int)Math.pow(10, count - 1);
                        count = 0;
                    }
                }
            }
        }
        return 0;
    }


    public Player freshCopy() {
        return new RandomComputer(letter);
    }
}