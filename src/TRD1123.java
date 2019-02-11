import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
public class TRD1123 extends Player {
    private char letter;
    private String name;
    private Move bestMove = null, worstMove = null;
    public static final char EMPTY = '-', RED = 'R', BLUE = 'B', PLAYING = '-', TIE = 'T';
    public static final int X_SIZE = 8, Y_SIZE = 7, Z_SIZE = 8;
    char[][][] board;
    String score = "";


    public TRD1123(char letter) {
        super("Craig", letter);
    }

    public Move makeMove(Board board, Move m) {
       return null;
    }

    public Move getMove(Board board) {
        this.board = new Board(board).getBoard();
        char[][][] baord = new char[8][7][8];
        int lastScore = 0;
        char opponentLetter = (letter == 'R') ? 'B' : 'R';
        Location location = null;
        bestMove = new Move((int) Math.random() * 7, (int) Math.random() * 7);
        Move lastMove = null;
        for (int z = 0; z < board.getBoard().length; z++) {
            for (int x = 0; x < board.getBoard()[0][0].length; x++) {
                if (!(board.isFull(new Move(x, z)))) {
                    location = board.makeMove(new Move(x, z), letter);//drops piece into board for grading later-VK
                }
                if (location!=null&&board.getBoard()[location.z][location.y][location.x] != '-' && board.getBoard()[location.z][location.y][location.x] != letter) {
                    //opponentLetter = board.getBoard()[location.z][location.y][location.x];
                }
                BoardGrader boardGrader = new BoardGrader(board, location, letter, 0, 0);
                //System.out.println(x + ", " + z + ":" + boardGrader.boardScorer(board, letter));
                int myScore = boardGrader.boardScorer(board, letter);
                int theirScore = boardGrader.boardScorer(board, opponentLetter);
                //System.out.println(x + ", " + z + ": " + (myScore - theirScore));
                if (myScore - theirScore > lastScore) {//checks to see if this next move is better than our last. If so, it becomes bestMove, and lastScore equals boardScorer(board)-VK
                    lastMove = new Move(x, z);
                    if (!board.isFull(new Move(x, z))) {
                        bestMove = new Move(location.x, location.z);
                    }
                }
                lastScore = myScore - theirScore;
                if (location != null) {
                    board.setLocation(location, Board.EMPTY);
                }
            }
        }
        if (!board.isFull(bestMove)) {
            return bestMove;//returns what boardGrader's best score is-VK
        }
        return null;
    }

    public Player freshCopy () {
        return new TRD1123(letter);
    }

    public String getScore () {
        return score;
    }



    public Move lookAhead() {
        //TODO: JUST DO THIS
        List<scoredMove> bestMoves = new ArrayList<>();
        int[] bestScores = new int[4];
        return null;
    }

    /*This method gets the best four moves available to the player at the time.
    In case you're worried about scoredMove, it's just move, except that it contains a score value and a copy of the move that was processed before it.
    Basically, it's a binary search tree.
    * */
    public ArrayList<Move> makeMove(List<scoredMove> bestMoves, int[] bestScores, char c, scoredMove bestMove,Board board) {
        this.board = new Board(board).getBoard();
        int lastScore = 0;
        char opponentLetter = (letter == 'R') ? 'B' : 'R';
        Location location = null;
        //bestMove = new Move((int) Math.random() * 7, (int) Math.random() * 7);
        Move lastMove = null;
        for (int z = 0; z < board.getBoard().length; z++) {
            for (int x = 0; x < board.getBoard()[0][0].length; x++) {
                if (!(board.isFull(new Move(x, z)))) {
                    location = board.makeMove(new Move(x, z), letter);//drops piece into board for grading later-VK
                }
                if (location!=null&&board.getBoard()[location.z][location.y][location.x] != '-' && board.getBoard()[location.z][location.y][location.x] != letter) {
                    //opponentLetter = board.getBoard()[location.z][location.y][location.x];
                }
                BoardGrader boardGrader = new BoardGrader(board, location, letter, 0, 0);
                //System.out.println(x + ", " + z + ":" + boardGrader.boardScorer(board, letter));
                int myScore = boardGrader.boardScorer(board, letter);
                int theirScore = boardGrader.boardScorer(board, opponentLetter);
                //System.out.println(x + ", " + z + ": " + (myScore - theirScore));
                if (myScore - theirScore > lastScore) {//checks to see if this next move is better than our last. If so, it becomes bestMove, and lastScore equals boardScorer(board)-VK
                    lastMove = new Move(x, z);
                    if (!board.isFull(new Move(x, z))) {
                        bestMoves.add(new scoredMove(x, z, (myScore-theirScore), bestMove));
                        while(bestMoves.size() > 3) {
                            int a = 0;
                            int worstScore = 2147483647; //The largest possible integer value there is
                            int worstMove = 0;
                            for(; a < bestMoves.size(); a++) {
                                if(bestMoves.get(a).getScore() < worstScore) {
                                    worstScore = bestMoves.get(a).getScore();
                                    worstMove = a;
                                }
                            }
                            bestMoves.remove(worstMove);
                        }
                    }
                }
                lastScore = myScore - theirScore;
                if (location != null) {
                    board.setLocation(location, Board.EMPTY);
                }
            }
        }
        if (!board.isFull(bestMove)) {
            //return bestMove;//returns what boardGrader's best score is-VK
        }
        return (ArrayList)bestMoves;
    }
}
