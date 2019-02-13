import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.lang.reflect.Method;
import java.util.ArrayList;
public class TRD1123 extends Player {
    private char letter;
    private String name;
    private Move bestMove = null, worstMove = null;
    public static final char EMPTY = '-', RED = 'R', BLUE = 'B', PLAYING = '-', TIE = 'T';
    public static final int X_SIZE = 8, Y_SIZE = 7, Z_SIZE = 8;
    char[][][] board;
    Board boardy=null;
    String score = "";
    BoardScorer b=new BoardScorer();


    public TRD1123(char letter) {
        super("Craig", letter);
        this.letter=letter;
    }

    public Move makeMove(Board board, Move m) {
       return null;
    }

    public Move getMove(Board board) {
        this.boardy = new Board(board);
        //this.board = new Board(board).getBoard();
        //char[][][] baord = new char[8][7][8];
        int lastScore = 0;
        char opponentLetter = (letter == 'R') ? 'B' : 'R';
        if(letter=='R') {
            opponentLetter='B';
        }
        else{
            opponentLetter='R';
        }

        Location location = null;
        //bestMove = new Move((int) Math.random() * 7, (int) Math.random() * 7);
        Move lastMove = null;
        /*checkr: for (int z = 0; z < board.getBoard().length; z++) {
            for (int x = 0; x < board.getBoard()[0][0].length; x++) {
                //I'm dying in here


                if(!boardy.isFull(new Move(x, z)))
                    location = boardy.makeMove(new Move(x, z), opponentLetter);

                if(boardyGrader.boardScorer(boardy, opponentLetter) > 1000)
                    return new Move(location.x, location.z);


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
        //return lookAhead();*/
        Move tempMove=null;
        for (int x = 0; x < X_SIZE; x++) {
            for (int y = 0; y < Y_SIZE; y++) {
                for (int z = 0; z<Z_SIZE; z++) {
                    /*tempMove = b.score(board, letter, x, y, z);
                    if (b.isOpportunity()) {
                        if (!board.isFull(tempMove)) {
                            bestMove = tempMove;
                        }
                    } else {
                        tempMove = b.score(board, opponentLetter, x, y, z);
                        if(b.isOpportunity()) {
                            if (!board.isFull(tempMove)) {
                                bestMove = tempMove;
                            }
                        }
                    }*/

                    //tempMove=b.score(boardy, letter, x,y,z);
                    /*if(tempMove!=null&&!board.isFull(tempMove)) {
                        bestMove=tempMove;
                        System.out.println("WE JUST SCOORREED");
                    }*/
                    tempMove=b.score(boardy, opponentLetter, z,y,x);
                    if(tempMove!=null&&!board.isFull(tempMove)) {
                        bestMove=tempMove;
                        System.out.println("\t\t\t\t\t\tX: "+bestMove.getX()+" Z:"+bestMove.getZ());
                    }

                    if(bestMove==null) {
                        tempMove=new Move(x,z);
                        if(!board.isFull(tempMove)) {
                            location = boardy.makeMove(tempMove, letter);
                        }
                        BoardGrader boardyGrader = new BoardGrader(boardy, location, letter, 0, 0);
                        if(boardyGrader.boardScorer(boardy,letter)>lastScore) {
                            lastScore=boardyGrader.boardScorer(boardy,letter);
                            bestMove=tempMove;
                        }
                    }

                }
            }

        }
        if(bestMove!=null) {
            return bestMove;
        }
        else {
            System.out.println("NOOOOO I'm NULLLLLL");
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
        Move bestMove=null;
        //TODO: JUST DO THIS
        List<scoredMove> bestMoves = new ArrayList<>();
        int[] bestScores = new int[4];
        ArrayList<scoredMove> moves=makeMove(bestMoves,bestScores,' ',null,boardy);
        System.out.println(moves.size());
        int bestScore=0;
        for(scoredMove m:moves) {
            if(m.getScore()>bestScore) {
                bestMove=m;
            }
        }
        return (Move)bestMove;
    }

    /*This method gets the best four moves available to the player at the time.
    In case you're worried about scoredMove, it's just move, except that it contains a score value and a copy of the move that was processed before it.
    Basically, it's a binary search tree.*/

    public ArrayList<scoredMove> makeMove(List<scoredMove> bestMoves, int[] bestScores, char c, scoredMove bestMove,Board board) {
        //this.board = new Board(board).getBoard();
        int lastScore = 0;
        char opponentLetter = (letter == 'R') ? 'B' : 'R';
        Location location = null;
        //bestMove = (scoredMove)new scoredMove((int) Math.random() * 7, (int) Math.random() * 7);
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
        return (ArrayList)bestMoves;
    }
}



/*TODO:
 * 1. Create 11 Arraylists: 2 sets of five, one for each player, counting doubles, triples, quadruples, and quintuples, and one for useless garbage
 * 2. Implement a //System to scan the board and categorize each and every piece into one of these Arraylists
 * 3. Score the contents of each Arraylist according to their priority, and make sure that Craig knows the difference between them.
 * -RK
 * */


//TODO: HOW TO BEAT BLOCKER. Look at all the spots that give you 6. Look at all the spots for them, block them.

class BoardScorer {

    public static final char EMPTY = '-', letter = 'R', BLUE = 'B', PLAYING = '-', TIE = 'T';
    public static final int X_SIZE = 8, Y_SIZE = 7, Z_SIZE = 8;
    char[][][] board=null;
    boolean opportunity=false;
    Method m= null;
    Class<?>className;
    int maxCount=3;

    public boolean isOpportunity() {
        return opportunity;
    }

    public Move score(Board board, char letter, int x, int y, int z) {
        //System.out.println("IM SCORING");
        char opponentLetter = (letter == 'R') ? 'B' : 'R';
        this.board = board.getBoard();

        /*ArrayList<Method> methods = new ArrayList<Method>();
        for (int c = 0; c < BoardScorer.class.getDeclaletterMethods().length; c++) {
            if (BoardScorer.class.getDeclaletterMethods()[c].getName().contains("check")) {
                methods.add(BoardScorer.class.getDeclaletterMethods()[c]);
            }
        }
        for (Method m:
             methods) {
            ////System.out.println(m.toString());

        }
        Object[]params={new Location(x, y, z), letter, 1};
        try {
            className=Class.forName("BoardScorer");
            Object boardScore = className.newInstance();
        }catch (Exception e) {
            e.printStackTrace();
        }

        int tempx=0, tempz=0;
        for (int c = 0; c < methods.size(); c++) {
            try {
                if((methods.get(c).getReturnType().getName().contains("int"))) {
                    //System.out.println("AYYO");
                    if ((Integer) (methods.get(c).invoke(/*new Location(x, y, z), letter, 1)className,params)) >= 0) {
                        //System.out.println("HEY ");
                        opportunity = true;
                        m = methods.get(c);
                        if (m.getName().contains("XP")) {
                            x += 2;
                        }
                        if (m.getName().contains("YP")) {
                            x += 2;
                        }
                        if (m.getName().contains("ZP")) {
                            z += 2;
                        }
                        if (m.getName().contains("XM")) {
                            x -= 2;
                        }
                        if (m.getName().contains("YM")) {
                            y -= 2;
                        }
                        if (m.getName().contains("ZM")) {
                            z -= 2;
                        }
                    }
                }
            }catch(Exception e ){e.printStackTrace();}
        }
        if(x<X_SIZE&&z<Z_SIZE) {
            return new Move(x, z);
        }*//*checkYM(new Location(x,y,z),letter,0)||checkZM(new Location(x,y,z),letter,0)||
        checkYPZP(new Location(x,y,z),letter,0)||
                                checkYPZM(new Location(x,y,z),letter,0)||checkZPXP(new Location(x,y,z),letter,0)||
                                checkZPXM(new Location(x,y,z),letter,0)||checkYPXP(new Location(x,y,z),letter,0)||checkYMXP(new Location(x,y,z),letter,0)||checkYMZP(new Location(x,y,z),letter,0)||checkYMZM(new Location(x,y,z),letter,0)
                        ||checkYMZPXP(new Location(x,y,z),letter,0)||checkYPZMXP(new Location(x,y,z),letter,0)
                                ||checkYPZPXM(new Location(x,y,z),letter,0)||checkYPZPXP(new Location(x,y,z),letter,0)) {
                        */

        maxCount = 4;
        //System.out.println("\t\t\t\t\t"+checkXP(new Location(x, y, z), letter, 1));
        if (checkXP(new Location(x, y, z), letter, 1) >= maxCount) {
            //System.out.println("\t\t\t PLEASE SEE MNEEEE");
            if (x < X_SIZE - (checkXP(new Location(x, y, z), letter, 1))) {
                //System.out.println("\t\t\t\t\tEY I MAKE MOVE");
                if (board.getBoard()[z][y][x+(checkXP(new Location(x, y, z), letter, 1))] != opponentLetter)
                    return new Move(x + (checkXP(new Location(x, y, z), letter, 1)), z);
            }
        }
        /*if (checkYP(new Location(x, y, z), letter, 1) >= maxCount) {
            //System.out.println("\t\t\t PLEASE SEE MNEEEE");
            if (y < Y_SIZE - (checkYP(new Location(x, y, z), letter, 1))) {
                //System.out.println("\t\t\t\t\tEY I MAKE MOVE");
                if (board.getBoard()[z][y+(checkYP(new Location(x, y, z), letter, 1))][x] != opponentLetter)
                    return new Move(x, z);
            }
        }*/
        if (checkZP(new Location(x, y, z), letter, 1) >= maxCount) {
            //System.out.println("\t\t\t PLEASE SEE MNEEEE");
            if (z < Z_SIZE - (checkZP(new Location(x, y, z), letter, 1)))
                if (board.getBoard()[z+checkZP(new Location(x, y, z), letter, 1)][y][x] != opponentLetter)
                    return new Move(x, z + (checkZP(new Location(x, y, z), letter, 1)));
        }
        if (checkXM(new Location(x, y, z), letter, 1) >= maxCount) {
            //System.out.println("\t\t\t PLEASE SEE MNEEEE");
            if (x - (checkXM(new Location(x, y, z), letter, 1)) >= 0)
                if (board.getBoard()[z][y][x-(checkXM(new Location(x, y, z), letter, 1))] != opponentLetter)
                    return new Move(x - (checkXM(new Location(x, y, z), letter, 1)), z);
        }
        if (checkYM(new Location(x, y, z), letter, 1) >= maxCount) {
            //System.out.println("\t\t\t PLEASE SEE MNEEEE");
            if (y - (checkYM(new Location(x, y, z), letter, 1)) >= 0) {
                if (board.getBoard()[z][y-(checkYM(new Location(x, y, z), letter, 1))][x] != opponentLetter) return new Move(x, z);
            }
        }
        if (checkZM(new Location(x, y, z), letter, 1) >= maxCount) {
            //System.out.println("\t\t\t PLEASE SEE MNEEEE");
            if (z - (checkZM(new Location(x, y, z), letter, 1)) >= 0) {
                if (board.getBoard()[z-(checkZM(new Location(x, y, z), letter, 1))][y][x] != opponentLetter) return new Move(x, z - (checkZM(new Location(x, y, z), letter, 1)));
            }
        }
        if (checkYPZP(new Location(x, y, z), letter, 1) >= maxCount) {
            //System.out.println("\t\t\t PLEASE SEE MNEEEE");
            if (y < Y_SIZE - (checkYPZP(new Location(x, y, z), letter, 1)) && z < Z_SIZE - (checkYPZP(new Location(x, y, z), letter, 1))) {
                if (board.getBoard()[z+(checkYPZP(new Location(x, y, z), letter, 1))][y+(checkYPZP(new Location(x, y, z), letter, 1))][x] != opponentLetter) return new Move(x, z + (checkYPZP(new Location(x, y, z), letter, 1)));
            }
        }
        if (checkYPZM(new Location(x, y, z), letter, 1) >= maxCount) {
            //System.out.println("\t\t\t PLEASE SEE MNEEEE");
            if (y < Y_SIZE - (checkYPZM(new Location(x, y, z), letter, 1)) && z - (checkYPZM(new Location(x, y, z), letter, 1)) >= 0)
                if (board.getBoard()[z-(checkYPZM(new Location(x, y, z), letter, 1))][y+checkYPZM(new Location(x, y, z), letter, 1)][x] != opponentLetter) return new Move(x, z - (checkYPZM(new Location(x, y, z), letter, 1)));
        }
        if (checkZPXP(new Location(x, y, z), letter, 1) >= maxCount) {
            //System.out.println("\t\t\t PLEASE SEE MNEEEE");
            if (z < Z_SIZE - (checkZPXP(new Location(x, y, z), letter, 1) ) && x < X_SIZE-(checkZPXP(new Location(x, y, z), letter, 1) ))
                if (board.getBoard()[z+(checkZPXP(new Location(x, y, z), letter, 1) )][y][x+(checkZPXP(new Location(x, y, z), letter, 1) )] != opponentLetter) return new Move(x + (checkZPXP(new Location(x, y, z), letter, 1) ), z + (checkZPXP(new Location(x, y, z), letter, 1) ));
        }
        if (checkZPXM(new Location(x, y, z), letter, 1) >= maxCount) {
            //System.out.println("\t\t\t PLEASE SEE MNEEEE");
            if (z < Z_SIZE - (checkZPXM(new Location(x, y, z), letter, 1)) && x - (checkZPXM(new Location(x, y, z), letter, 1))>= 0)
                if (board.getBoard()[z+(checkZPXM(new Location(x, y, z), letter, 1))][y][x-(checkZPXM(new Location(x, y, z), letter, 1))] != opponentLetter) return new Move(x - (checkZPXM(new Location(x, y, z), letter, 1)), z + (checkZPXM(new Location(x, y, z), letter, 1)));
        }
        if (checkYPXP(new Location(x, y, z), letter, 1) >= maxCount) {
            //System.out.println("\t\t\t PLEASE SEE MNEEEE");
            if (y + (checkYPXP(new Location(x, y, z), letter, 1) ) < Y_SIZE && x + (checkYPXP(new Location(x, y, z), letter, 1) ) < X_SIZE)
                if (board.getBoard()[z][y+(checkYPXP(new Location(x, y, z), letter, 1) )][x+(checkYPXP(new Location(x, y, z), letter, 1) )] != opponentLetter) return new Move(x + (checkYPXP(new Location(x, y, z), letter, 1) ), z);
        }
        if (checkYMXP(new Location(x, y, z), letter, 1) >= maxCount) {
            //System.out.println("\t\t\t PLEASE SEE MNEEEE");
            if (y - (checkYMXP(new Location(x, y, z), letter, 1)) >= 0 && x + (checkYMXP(new Location(x, y, z), letter, 1)) < X_SIZE)
                if (board.getBoard()[z][y-(checkYMXP(new Location(x, y, z), letter, 1))][x+(checkYMXP(new Location(x, y, z), letter, 1))] != opponentLetter) return new Move(x + (checkYMXP(new Location(x, y, z), letter, 1)), z);
        }
        if (checkYMZP(new Location(x, y, z), letter, 1) >= maxCount) {
            //System.out.println("\t\t\t PLEASE SEE MNEEEE");
            if (y - (checkYMZP(new Location(x, y, z), letter, 1)) >= 0 && z + (checkYMZP(new Location(x, y, z), letter, 1)) < Z_SIZE)
                if (board.getBoard()[z+(checkYMZP(new Location(x, y, z), letter, 1))][y-(checkYMZP(new Location(x, y, z), letter, 1))][x] != opponentLetter) return new Move(x, z + (checkYMZP(new Location(x, y, z), letter, 1)));
        }
        if (checkYMZM(new Location(x, y, z), letter, 1) >= maxCount) {
            //System.out.println("\t\t\t PLEASE SEE MNEEEE");
            if (y - (checkYMZM(new Location(x, y, z), letter, 1)) >= 0 && z - (checkYMZM(new Location(x, y, z), letter, 1)) >= 0)
                if (board.getBoard()[z-(checkYMZM(new Location(x, y, z), letter, 1))][y-(checkYMZM(new Location(x, y, z), letter, 1))][x] != opponentLetter) return new Move(x, z - (checkYMZM(new Location(x, y, z), letter, 1)));
        }
        if (checkYMZPXP(new Location(x, y, z), letter, 1) >= maxCount) {
            //System.out.println("\t\t\t PLEASE SEE MNEEEE");
            if (y - (checkYMZPXP(new Location(x, y, z), letter, 1)) >= 0 && z + (checkYMZPXP(new Location(x, y, z), letter, 1)) < Z_SIZE && x + (checkYMZPXP(new Location(x, y, z), letter, 1)) < X_SIZE)
                if (board.getBoard()[z+(checkYMZPXP(new Location(x, y, z), letter, 1))][y-(checkYMZPXP(new Location(x, y, z), letter, 1))][x+(checkYMZPXP(new Location(x, y, z), letter, 1))] != opponentLetter) return new Move(x + (checkYMZPXP(new Location(x, y, z), letter, 1)), z + (checkYMZPXP(new Location(x, y, z), letter, 1)));
        }
        if (checkYPZMXP(new Location(x, y, z), letter, 1) >= maxCount) {
            //System.out.println("\t\t\t PLEASE SEE MNEEEE");
            if (z < Z_SIZE - (checkYPZMXP(new Location(x, y, z), letter, 1)) && x +(checkYPZMXP(new Location(x, y, z), letter, 1) )< X_SIZE)
                if (board.getBoard()[z-(checkYPZMXP(new Location(x, y, z), letter, 1) )][y+(checkYPZMXP(new Location(x, y, z), letter, 1) )][x+(checkYPZMXP(new Location(x, y, z), letter, 1) )] != opponentLetter) return new Move(x + (checkYPZMXP(new Location(x, y, z), letter, 1) ), z - (checkYPZMXP(new Location(x, y, z), letter, 1) ));
        }
        if (checkYPZPXM(new Location(x, y, z), letter, 1) >= maxCount) {
            //System.out.println("\t\t\t PLEASE SEE MNEEEE");
            if (z < Z_SIZE - (checkYPZPXM(new Location(x, y, z), letter, 1)) && x -(checkYPZPXM(new Location(x, y, z), letter, 1))>=0&&y<Y_SIZE-(checkYPZPXM(new Location(x, y, z), letter, 1)))
                if (board.getBoard()[z+(checkYPZPXM(new Location(x, y, z), letter, 1))][y+(checkYPZPXM(new Location(x, y, z), letter, 1))][x-(checkYPZPXM(new Location(x, y, z), letter, 1))] != opponentLetter) return new Move(x - (checkYPZPXM(new Location(x, y, z), letter, 1)), z + (checkYPZPXM(new Location(x, y, z), letter, 1)));
        }
        if (checkYPZPXP(new Location(x, y, z), letter, 1) >= maxCount) {
            //System.out.println("\t\t\t PLEASE SEE MNEEEE");
            if (z < Z_SIZE - (checkYPZPXP(new Location(x, y, z), letter, 1) ) && x +(checkYPZPXP(new Location(x, y, z), letter, 1) )< X_SIZE && y +(checkYPZPXP(new Location(x, y, z), letter, 1) )< Y_SIZE )
                if (board.getBoard()[z+(checkYPZPXP(new Location(x, y, z), letter, 1) )][y+(checkYPZPXP(new Location(x, y, z), letter, 1) )][x+(checkYPZPXP(new Location(x, y, z), letter, 1) )] != opponentLetter) return new Move(x + (checkYPZPXP(new Location(x, y, z), letter, 1) ), z + (checkYPZPXP(new Location(x, y, z), letter, 1) ));
        }
        return null;

    }

    public int checkXP(Location l, char player, int x) { //x starts off being 0
        if(l.x==X_SIZE) {
            return x;
        }
        if(l.x < X_SIZE) {
            if(board[l.z][l.y][l.x] == player) {
                return checkXP(cL(l,1, 0, 0), player, ++x);
            }
        }
        return x;
    }

    public int checkXM(Location l, char player, int x) {
        if(l.x==0) {
            return x;
        }
        if(l.x >= 0) {
            if(board[l.z][l.y][l.x] == player) {
                return    checkXM(cL(l,- 1, 0, 0), player, ++x);
            }
        }
        return x;
    }
    public int checkYP(Location l, char player, int y) {
        if (l.y == Y_SIZE) {
            return y;
        }
        if (l.y < Y_SIZE) {
            if (board[l.z][l.y][l.x] == player) {
                return   checkYP(cL(l,0, 1, 0), player, ++y);
            }
        }
        return y;
    }

    public int checkYPZP(Location l, char player, int y) {
        if (l.y == Y_SIZE||l.z==Z_SIZE) {
            return y;
        }
        if (l.y < Y_SIZE && l.z <Z_SIZE&& y < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return   checkYPZP(cL(l,0, 1, 1), player, ++y);
            }
        }
        return y;
    }
    public int checkYMZP(Location l, char player, int y) {
        if (l.y == 0||l.z==Z_SIZE) {
            return y;
        }
        if (l.y > 0 && l.z <Z_SIZE) {
            if (board[l.z][l.y][l.x] == player) {
                return   checkYMZP(cL(l,0, -1, 1), player, ++y);
            }
        }
        return y;
    }
    public int checkYMZM(Location l, char player, int y) {
        if (l.y == 0||l.z==0) {
            return y;
        }
        if (l.y >=0 && l.z>=0&& y < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return   checkYMZM(cL(l,0, -1, -1), player, ++y);
            }
        }
        return y;
    }

    public int checkYPZM(Location l, char player, int y) {
        if (l.y == Y_SIZE||l.z == 0) {
            return y;
        }
        if (l.y < Y_SIZE &&l.z>=0 &&y < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return   checkYPZM(cL(l, 0, 1, -1), player, ++y);
            }
        }
        return y;
    }
    public int checkYMZPXP(Location l, char player, int y) {
        if (l.y == 0||l.z ==Z_SIZE||l.x==X_SIZE) {
            return y;
        }
        if (l.y >=0 &&l.z<Z_SIZE &&l.x<X_SIZE&&y < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return   checkYMZPXP(cL(l, 1, -1, 1), player, ++y);
            }
        }
        return y;
    }
    public int checkYPZMXP(Location l, char player, int y) {
        if (l.y == Y_SIZE||l.z == 0||l.x==X_SIZE) {
            return y;
        }
        if (l.y < Y_SIZE &&l.z>=0 &&l.x<X_SIZE&&y < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return   checkYPZMXP(cL(l,1, 1, -1), player, ++y);
            }
        }
        return y;
    }
    public int checkYPZPXM(Location l, char player, int y) {
        if (l.y == Y_SIZE||l.z == Z_SIZE||l.x==0) {
            return y;
        }
        if (l.y < Y_SIZE &&l.z<Z_SIZE &&l.x>=0&&y < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return   checkYPZPXM(cL(l,-1, 1, 1), player, ++y);
            }
        }
        return y;
    }
    public int checkYPZPXP(Location l, char player, int y) {
        if (l.y== Y_SIZE||l.z == Z_SIZE||l.x == X_SIZE) {
            return y;
        }
        if (l.y < Y_SIZE &&l.z<Z_SIZE &&l.x<X_SIZE&&y < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return   checkYPZPXP(cL(l,1, 1, 1), player, ++y);
            }
        }
        return y;
    }

    public int checkZPXP(Location l, char player, int y) {
        if (l.z == Z_SIZE||l.x==X_SIZE) {
            return y;
        }
        if (l.z < Z_SIZE &&l.x<X_SIZE&& y < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return   checkZPXP(cL(l,1, 0, 1), player, ++y);
            }
        }
        return y;
    }
    public int checkZPXM(Location l, char player, int y) {
        if (l.x ==0||l.z==Z_SIZE) {
            return y;
        }
        if (l.x >=0 &&l.z<Z_SIZE &&y < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return   checkZPXM(cL(l,-1, 0, 1), player, ++y);
            }
        }
        return y;
    }

    public int checkYPXP(Location l, char player, int y) {
        if (l.y == Y_SIZE||l.x==X_SIZE) {
            return y;
        }
        if (l.y < Y_SIZE &&l.x<X_SIZE &&y < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return   checkYPXP(cL(l,1, 1, 0), player, ++y);
            }
        }
        return y;
    }


    public int checkYMXP(Location l, char player, int y) {
        if (l.y == 0||l.x==X_SIZE) {
            return y;
        }
        if (l.y >=0 &&l.x<X_SIZE&& y < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return   checkYMXP(cL(l,1, -1, 0), player,++y);
            }
        }
        return y;
    }



    public int checkYM(Location l, char player, int y) {
        if(l.y==Y_SIZE) {
            return y;
        }
        if(l.y >= 0 && y < 5) {
            if(board[l.z][l.y][l.x] == player) {
                return   checkYM(cL(l,0,-1,0), player, ++y);
            }
        }

        return y;
    }
    public int checkZP(Location l, char player, int z) {
        if(l.z==Z_SIZE) {
            return z;
        }
        if(l.z < Z_SIZE && z < 5) {
            if(board[l.z][l.y][l.x] == player) {
                return   checkZP(cL(l,0,0,1), player, ++z);
            }
        }
        return z;
    }
    public int checkZM(Location l, char player, int z) {
        if (l.z == 0) {
            return z;
        }
        if (l.z >= 0 && z < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return   checkZM(cL(l, 0, 0, -1), player, ++z);
            }
        }
        return z;
    }

    Location cL(Location l, int x, int y, int z) {
        return new Location(l.x + x, l.y + y, l.z + z);
    }
}
