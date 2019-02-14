import jdk.nashorn.internal.ir.LocalVariableConversion;

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
        //System.out.println("fdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfd");
        this.boardy = new Board(board);
        //this.board = new Board(board).getBoard();
        //char[][][] baord = new char[8][7][8];/
        /*
        boolean first = true;*/
        int lastScore = 0;/*
        for(int x = 0; x < X_SIZE; x++) {
            for(int z = 0; z < Z_SIZE; z++) {
                if(board.getBoard()[z][0][x] == getLetter())
                    first = false;
            }
        }
        if(first)
            return new Move((int)(Math.random() * 8), (int)(Math.random() * 8));*/
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
                     tempMove=b.score(boardy, opponentLetter, x,y,z, letter);
                    if(tempMove!=null&&!board.isFull(tempMove)) {
                        bestMove=tempMove;

                        //System.out.println("bestMove =tempMove.");
                        //System.out.println("\t\t\t\t\t\tX: "+bestMove.getX()+" Z:"+bestMove.getZ());
                    }

                    if(bestMove==null) {
                        System.out.println("bestMove is null.");
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
        ArrayList<scoredMove> moves=makeMove(bestMoves,bestScores,'-',null,boardy);
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

    public Move score(Board board, char letter, int x, int y, int z, char opponentLetter) {
        int trigger=4;
        Location l= new Location(x,y,z);

        if(checkXP(l,letter,1)>maxCount) {
            if(l.x+checkXP(l,letter,1)<X_SIZE&&board.getBoard()[l.z][l.y][l.x+checkXP(l,letter,1)]!=opponentLetter) {
                return new Move(l.x+checkXP(l,letter,1),l.z);
            }
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
    
    public Move potentialTaker(Board board, Move move, char c, char opponentLetter) {// THIS IS JUST TULLY'S BOARDSCORE METHOD REWRITTEN TO FIT OUR MOVE
        //TODO 2/13/19

        int z=move.getZ();
        int x=move.getX();
        char[][][]boardy=board.getBoard();
        int needed=2;
        String winType="";
        char winner='-';
        int y=0;
        
        int hCount=1;

        for(int xx=x+1; xx<X_SIZE;xx++)
            if(boardy[z][y][xx]==c)
                hCount++;
            else
                break;
        for(int xx=x-1; xx>=0;xx--)
            if(boardy[z][y][xx]==c)
                hCount++;
            else
                break;

        if(hCount>=needed){
            winType="Horizontal";
            winner=c;
            if(boardy[z][y][x+hCount]=='-') {
                return new Move(x + hCount, z);
            }
            else if(boardy[z][y][x-hCount]=='-') {
                return new Move(x-hCount, z);
            }

        }

        // back
        int bCount=1;
        for(int zz=z+1; zz<Z_SIZE;zz++)
            if(boardy[zz][y][x]==c)
                bCount++;
            else
                break;
        for(int zz=z-1; zz>=0;zz--)
            if(boardy[zz][y][x]==c)
                bCount++;
            else
                break;
        if(bCount>=needed){
            winType="depth";
            winner=c;

            if(boardy[z+bCount][y][x]=='-') {
                return new Move(x, z + bCount);
            }
            else if(boardy[z-bCount][y][x]=='-') {
                return new Move(x,z-bCount);
            }
        }

        // up
        int uCount=1;
        for(int yy=y+1; yy<Y_SIZE;yy++)
            if(boardy[z][yy][x]==c)
                uCount++;
            else
                break;
        for(int yy=y-1; yy>=0;yy--)
            if(boardy[z][yy][x]==c)
                uCount++;
            else
                break;
        if(uCount>=needed){
            winType="Up";
            winner=c;

            return new Move(x,z);
        }


// diagonals on the x-plane
        // both increasing
        int idc=1;
        for(int change=1; change<=change+4;change++)
            if(z+change<Z_SIZE && y+change<Y_SIZE && boardy[z+change][y+change][x]==c)
                idc++;
            else
                break;
        for(int change=1; change<=change+4;change++)
            if(z-change>=0 && y-change>=0 && boardy[z-change][y-change][x]==c)
                idc++;
            else
                break;

        if(idc>=needed){
            winType="x-plane diagonal #1";
            winner=c;
            if(boardy[z+(idc-1)][y][x]=='-') {
                return new Move(x, z + idc);
            }
            else if(boardy[z-(idc+1)][y][x]=='-') {
                return new Move(x,z-(idc+1));
            }
        }
        // changing differently
        idc=1;
        for(int change=1; change<=change+4;change++)
            if(z-change>=0 && y+change<Y_SIZE && boardy[z-change][y+change][x]==c)
                idc++;
            else
                break;
        for(int change=1; change<=change+4;change++)
            if(z+change<Z_SIZE && y-change>=0 && boardy[z+change][y-change][x]==c)
                idc++;
            else
                break;

        if(idc>=needed){
            winType="x-plane diagonal #2";
            winner=c;

            if(boardy[z+(idc-1)][y][x]=='-') {
                return new Move(x, z + idc);
            }
            else if(boardy[z-(idc+1)][y][x]=='-') {
                return new Move(x,z-(idc+1));
            }
        }

// diagonals on the y-plane
        // both increasing
        idc=1;
        for(int change=1; change<=change+4;change++)
            if(z+change<Z_SIZE && x+change<X_SIZE && boardy[z+change][y][x+change]==c)
                idc++;
            else
                break;
        for(int change=1; change<=change+4;change++)
            if(z-change>=0 && x-change>=0 && boardy[z-change][y][x-change]==c)
                idc++;
            else
                break;

        if(idc>=needed){
            winner=c;
            winType="y-plane diagonal #1";

            if(boardy[z+(idc-1)][y][x+(idc-1)]=='-') {
                return new Move(x+(idc-1), z + idc);
            }
            else if(boardy[z-(idc+1)][y][x-(idc+1)]=='-') {
                return new Move(x-(idc+1),z-(idc+1));
            }
        }
        // changing differently
        idc=1;
        for(int change=1; change<=change+4;change++)
            if(z-change>=0 && x+change<X_SIZE && boardy[z-change][y][x+change]==c)
                idc++;
            else
                break;
        for(int change=1; change<=change+4;change++)
            if(z+change<Z_SIZE && x-change>=0 && boardy[z+change][y][x-change]==c)
                idc++;
            else
                break;

        if(idc>=needed){
            winType="y-plane diagonal #2";
            winner=c;
            if(boardy[z+(idc-1)][y][x-(idc+1)]=='-') {
                return new Move(x-(idc+1), z + idc);
            }
            else if(boardy[z-(idc+1)][y][x+(idc-1)]=='-') {
                return new Move(x+(idc-1),z-(idc+1));
            }
        }

// diagonals on the z-plane
        // both increasing
        idc=1;
        for(int change=1; change<=change+4;change++)
            if(y+change<Y_SIZE && x+change<X_SIZE && boardy[z][y+change][x+change]==c)
                idc++;
            else
                break;
        for(int change=1; change<=change+4;change++)
            if(y-change>=0 && x-change>=0 && boardy[z][y-change][x-change]==c)
                idc++;
            else
                break;

        if(idc>=needed){
            winType="z-plane diagonal #1";
            winner=c;
            if(boardy[z][y][x+(idc-1)]=='-') {
                return new Move(x+(idc-1), z);
            }
            else if(boardy[z][y][x-(idc+1)]=='-') {
                return new Move(x-(idc+1),z);
            }
        }
        // changing differently
        idc=1;
        for(int change=1; change<=change+4;change++)
            if(y-change>=0 && x+change<X_SIZE && boardy[z][y-change][x+change]==c)
                idc++;
            else
                break;
        for(int change=1; change<=change+4;change++)
            if(y+change<Y_SIZE && x-change>=0 && boardy[z][y+change][x-change]==c)
                idc++;
            else
                break;

        if(idc>=needed){
            winType="z-plane diagonal #2";
            winner=c;
            if(boardy[z][y][x-(idc+1)]=='-') {
                return new Move(x-(idc+1), z);
            }
            else if(boardy[z][y][x+(idc-1)]=='-') {
                return new Move(x+(idc-1),z);
            }
        }

// special digonal 1 + + +
        idc=1;
        for(int change=1; change<=change+4;change++)
            if(y+change<Y_SIZE && x+change<X_SIZE && z+change<Z_SIZE && boardy[z+change][y+change][x+change]==c)
                idc++;
            else
                break;
        for(int change=1; change<=change+4;change++)
            if(y-change>=0 && x-change>=0 && z-change>=0 &&boardy[z-change][y-change][x-change]==c)
                idc++;
            else
                break;

        if(idc>=needed){
            winType="special diagonal #1 + + +";
            winner=c;
            if(boardy[z+(idc-1)][y][x+(idc-1)]=='-') {
                return new Move(x+(idc-1), z + idc);
            }
            else if(boardy[z-(idc+1)][y][x-(idc+1)]=='-') {
                return new Move(x-(idc+1),z-(idc+1));
            }
        }

        // special digonal 2 + + -
        idc=1;
        for(int change=1; change<=change+4;change++)
            if(y+change<Y_SIZE && x+change<X_SIZE && z-change>=0 && boardy[z-change][y+change][x+change]==c)
                idc++;
            else
                break;
        for(int change=1; change<=change+4;change++)
            if(y-change>=0 && x-change>=0 && z+change<Z_SIZE &&boardy[z+change][y-change][x-change]==c)
                idc++;
            else
                break;

        if(idc>=needed){
            winner=c;
            winType="special diagonal #2 + + -";

            if(boardy[z+(idc-1)-1][y][x-(idc+1)]=='-') {
                return new Move(x-(idc+1), z + idc);
            }
            else if(boardy[z-(idc+1)][y][x+(idc-1)]=='-') {
                return new Move(x+(idc-1),z-(idc+1));
            }
        }

// special diagonal 3 - + -
        idc=1;
        for(int change=1; change<=change+4;change++)
            if(y+change<Y_SIZE && x-change>=0 && z-change>=0 && boardy[z-change][y+change][x-change]==c)
                idc++;
            else
                break;
        for(int change=1; change<=change+4;change++)
            if(y-change>=0 && x+change<X_SIZE && z+change<Z_SIZE &&boardy[z+change][y-change][x+change]==c)
                idc++;
            else
                break;

        if(idc>=needed){
            winner=c;
            winType="special diagonal #3 - + -";

            if(boardy[z+(idc-1)][y][x+(idc-1)]=='-') {
                return new Move(x+(idc-1), z + idc);
            }
            else if(boardy[z-(idc+1)][y][x-(idc+1)]=='-') {
                return new Move(x-(idc+1),z-(idc+1));
            }
        }

// special diagonal 4 - + +
        idc=1;
        for(int change=1; change<=change+4;change++)
            if(y+change<Y_SIZE && x-change>=0 && z+change<Z_SIZE && boardy[z+change][y+change][x-change]==c)
                idc++;
            else
                break;
        for(int change=1; change<=change+4;change++)
            if(y-change>=0 && x+change<X_SIZE && z-change>=0 &&boardy[z-change][y-change][x+change]==c)
                idc++;
            else
                break;

        if(idc>=needed){
            winType="special diagonal #4 - + +";
            winner=c;
            if(boardy[z+(idc-1)][y][x-(idc+1)]=='-') {
                return new Move(x-(idc+1), z + idc);
            }
            else if(boardy[z-(idc+1)][y][x+(idc-1)]=='-') {
                return new Move(x+(idc-1),z-(idc+1));
            }
        }
        return null;
    }
}
