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
    Location location = null;
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
        int lastScore = 0;
        this.board=board.getBoard();
        char opponentLetter = (letter == 'R') ? 'B' : 'R';
        Move tempMove=null;
        boolean firstMove=true;
        for(int x=0;x<X_SIZE;x++) {
            for(int y=0;y<Y_SIZE;y++) {
                for(int z=0;z<Z_SIZE;z++) {
                    if(board.getBoard()[z][y][x]!='-') {
                        firstMove=false;
                    }
                }
            }
        }
        if(firstMove) {
            System.out.println("Its new woohoo");
            return new Move((int)Math.random()*7,(int)Math.random()*7);
        }
        for (int x = 0; x < X_SIZE; x++) {
            for (int y = 0; y < Y_SIZE; y++) {
                for (int z = 0; z<Z_SIZE; z++) {
                    tempMove=b.score(board, opponentLetter, x,y,z, letter);
                    if(tempMove!=null&&!board.isFull(tempMove)) {
                        bestMove=tempMove;
                    }

                    if(bestMove==null) {
                        System.out.println("bestMove is null.");
                        tempMove=new Move(x,z);
                        if(tempMove!=null&&!board.isFull(tempMove)) {
                            location = board.makeMove(tempMove, letter);
                        }
                        BoardGrader boardGrader = new BoardGrader(board, location, letter, 0, 0);
                        if(boardGrader.boardScorer(board,letter)>lastScore) {
                            lastScore=boardGrader.boardScorer(board,letter);
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
            return null;
        }
    }

    public Player freshCopy () {
        return new TRD1123(letter);
    }

    public String getScore () {
        return score;
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

    public boolean isOpportunity() {
        return opportunity;
    }

    public Move score(Board board, char letter, int x, int y, int z, char opponentLetter) {
        int trigger=3;
        Location l= new Location(x,y,z);
        this.board=board.getBoard();


        if((checkXP(l,letter,1))>trigger) {
            if(l.x+(checkXP(l,letter,1))<X_SIZE&&board.getBoard()[l.z][l.y][l.x+(checkXP(l,letter,1))]!=opponentLetter) {
                return new Move(l.x+(checkXP(l,letter,1)),l.z);
            }
        }
        if((checkXM(l,letter,1))>trigger) {
            if(l.x-(checkXM(l,letter,1))>=0&&board.getBoard()[l.z][l.y][l.x-(checkXM(l,letter,1))]!=opponentLetter) {
                return new Move(l.x-(checkXM(l,letter,1)),l.z);
            }
        }
        if(checkYP(l,letter,1)>trigger) {
            if(l.y+(checkYP(l,letter,1))<Y_SIZE&&board.getBoard()[l.z][l.y+(checkYP(l,letter,1))][l.x]!=opponentLetter) {
                return new Move(l.x,l.z);
            }
        }
        if((checkYM(l,letter,1))>trigger) {
            if(l.y-(checkYM(l,letter,1))>=0&&board.getBoard()[l.z][l.y-(checkYM(l,letter,1))][l.x]!=opponentLetter) {
                return new Move(l.x,l.z);
            }
        }
        if((checkZP(l,letter,1))>trigger) {
            if(l.z+(checkZP(l,letter,1))<Z_SIZE&&board.getBoard()[l.z+(checkZP(l,letter,1))][l.y][l.x]!=opponentLetter) {
                return new Move(x,z+(checkZP(l,letter,1)));
            }
        }
        if((checkZM(l,letter,1))>trigger) {
            if(l.z-(checkZM(l,letter,1))>=0&&board.getBoard()[l.z-(checkZM(l,letter,1))][l.y][l.x]!=opponentLetter) {
                return new Move(x,z-(checkZM(l,letter,1)));
            }

        }
        if((checkYPZP(l,letter,1))>trigger) {
            if(l.z+(checkYPZP(l,letter,1))<Z_SIZE&&l.y+(checkYPZP(l,letter,1))<Y_SIZE&&board.getBoard()[l.z+(checkYPZP(l,letter,1))][l.y+(checkYPZP(l,letter,1))][l.x]!=opponentLetter) {
                return new Move(x, l.z+(checkYPZP(l,letter,1)));
            }
        }
        if((checkYPZM(l,letter,1))>trigger) {
            if (l.y + (checkYPZM(l, letter, 1)) < Y_SIZE && l.z - (checkYPZM(l, letter, 1)) >=0 && board.getBoard()[l.z-(checkYPZM(l,letter,1))][l.y+(checkYPZM(l,letter,1))][l.x]!=opponentLetter) {
                return new Move(x,z+l.z-(checkYPZM(l,letter,1)));
            }
        }
        if((checkZPXP(l,letter,1))>trigger) {
            if(l.z+(checkZPXP(l,letter,1))<Z_SIZE&&l.x+(checkZPXP(l,letter,1))<X_SIZE&&board.getBoard()[l.z+(checkZPXP(l,letter,1))][l.y][l.y+(checkZPXP(l,letter,1))]!=opponentLetter) {
                return new Move(x+(checkZPXP(l,letter,1)),z+(checkZPXP(l,letter,1)));
            }
        }
        if((checkZPXM(l,letter,1))>trigger) {
            if(l.z+(checkZPXM(l,letter,1))<Z_SIZE&&l.x-(checkZPXM(l,letter,1))>=0&&board.getBoard()[l.z+(checkZPXM(l,letter,1))][l.y][l.x+(checkZPXM(l,letter,1))]!=opponentLetter) {
                return new Move(x-(checkZPXM(l,letter,1)),z+(checkZPXM(l,letter,1)));
            }
        }
        if((checkYPXP(l,letter,1))>trigger) {
            if(l.x+(checkYPXP(l,letter,1))<X_SIZE&&l.y+(checkYPXP(l,letter,1))<Y_SIZE&&board.getBoard()[l.z][l.y+(checkYPXP(l,letter,1))][l.x+(checkYPXP(l,letter,1))]!=opponentLetter) {
                return new Move(l.x+(checkYPXP(l,letter,1)),z);
            }
        }
        if((checkYMXP(l,letter,1))>trigger) {
            if(l.y-(checkYMXP(l,letter,1))>=0&&l.x+(checkYMXP(l,letter,1))<X_SIZE&&board.getBoard()[l.z][l.y-(checkYMXP(l,letter,1))][l.x+(checkYMXP(l,letter,1))]!=opponentLetter) {
                return new Move(l.x+(checkYMXP(l,letter,1)),l.z);
            }
        }
        if((checkYMZP(l,letter,1))>trigger) {
            if(l.y-(checkYMZP(l,letter,1))>=0&&l.z+(checkYMZP(l,letter,1))<Z_SIZE&&board.getBoard()[l.z+(checkYMZP(l,letter,1))][l.y-(checkYMZPXP(l,letter,1))][l.x]!=opponentLetter) {
                return new Move(l.x,l.z+(checkYMZP(l,letter,1)));
            }
        }
        if ((checkYMZM(l,letter,1))>trigger) {
            if(l.y-(checkYMZP(l,letter,1))>=0&&l.z-(checkYMZP(l,letter,1))>=0&&board.getBoard()[l.z-(checkYMZP(l,letter,1))][l.y-(checkYMZPXP(l,letter,1))][l.x]!=opponentLetter) {
                return new Move(l.x,l.z-(checkYMZP(l,letter,1)));
            }
        }
        if((checkYMZPXP(l,letter,1))>trigger) {
            if(l.x+(checkYMZPXP(l,letter,1))<X_SIZE&&l.y-(checkYMZPXP(l,letter,1))>=0&&l.z+(checkYMZPXP(l,letter,1))<Z_SIZE&&board.getBoard()[l.z+(checkYMZPXP(l,letter,1))][l.y-(checkYMZPXP(l,letter,1))][l.x+(checkYMZPXP(l,letter,1))]!=opponentLetter) {
                return new Move(l.x+(checkYMZPXP(l,letter,1)),l.z+(checkYMZPXP(l,letter,1)));
            }
        }
        if((checkYPZMXP(l,letter,1))>trigger) {
            if (l.x+(checkYPZMXP(l,letter,1))<X_SIZE&&l.y + (checkYPZMXP(l, letter, 1)) < Y_SIZE && l.z - (checkYPZMXP(l, letter, 1)) >=0 && board.getBoard()[l.z-(checkYPZMXP(l,letter,1))][l.y+(checkYPZMXP(l,letter,1))][l.x+(checkYPZMXP(l,letter,1))]!=opponentLetter) {
                return new Move(x+(checkYPZMXP(l,letter,1)),z+l.z-(checkYPZMXP(l,letter,1)));
            }
        }
        if((checkYPZPXM(l,letter,1))>trigger) {
            if(l.x-(checkYPZPXM(l,letter,1))>=0&&l.y+(checkYPZPXM(l,letter,1))<Y_SIZE&&l.z+(checkYPZPXM(l,letter,1))<Z_SIZE&&board.getBoard()[l.z+(checkYPZPXM(l,letter,1))][l.y+(checkYPZPXM(l,letter,1))][l.x-(checkYPZPXM(l,letter,1))]!=opponentLetter) {
                return new Move(l.x-(checkYPZPXM(l,letter,1)),l.z+(checkYPZPXM(l,letter,1)));
            }
        }
        if((checkYPZPXP(l,letter,1))>trigger) {
            if(l.x+(checkYPZPXP(l,letter,1))<X_SIZE&&l.y+(checkYPZPXP(l,letter,1))<Y_SIZE&&l.z+(checkYPZPXP(l,letter,1))<Z_SIZE&&board.getBoard()[l.z+(checkYPZPXP(l,letter,1))][l.y+(checkYPZPXP(l,letter,1))][l.x+(checkYPZPXP(l,letter,1))]!=opponentLetter) {
                return new Move(l.x+(checkYPZPXP(l,letter,1)),l.z+(checkYPZPXP(l,letter,1)));
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
    
    /*public Move potentialTaker(Board board, Move move, char c, char opponentLetter) {// THIS IS JUST TULLY'S BOARDSCORE METHOD REWRITTEN TO FIT OUR MOVE
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
        for(int xx=x-1); xx>=0;xx--)
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
        for(int zz=z-1); zz>=0;zz--)
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
        for(int yy=y-1); yy>=0;yy--)
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
            if(boardy[z+(idc-1))][y][x]=='-') {
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

            if(boardy[z+(idc-1))][y][x]=='-') {
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

            if(boardy[z+(idc-1))][y][x+(idc-1))]=='-') {
                return new Move(x+(idc-1)), z + idc);
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
            if(boardy[z+(idc-1))][y][x-(idc+1)]=='-') {
                return new Move(x-(idc+1), z + idc);
            }
            else if(boardy[z-(idc+1)][y][x+(idc-1))]=='-') {
                return new Move(x+(idc-1)),z-(idc+1));
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
            if(boardy[z][y][x+(idc-1))]=='-') {
                return new Move(x+(idc-1)), z);
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
            else if(boardy[z][y][x+(idc-1))]=='-') {
                return new Move(x+(idc-1)),z);
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
            if(boardy[z+(idc-1))][y][x+(idc-1))]=='-') {
                return new Move(x+(idc-1)), z + idc);
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

            if(boardy[z+(idc-1))-1)][y][x-(idc+1)]=='-') {
                return new Move(x-(idc+1), z + idc);
            }
            else if(boardy[z-(idc+1)][y][x+(idc-1))]=='-') {
                return new Move(x+(idc-1)),z-(idc+1));
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

            if(boardy[z+(idc-1))][y][x+(idc-1))]=='-') {
                return new Move(x+(idc-1)), z + idc);
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
            if(boardy[z+(idc-1))][y][x-(idc+1)]=='-') {
                return new Move(x-(idc+1), z + idc);
            }
            else if(boardy[z-(idc+1)][y][x+(idc-1))]=='-') {
                return new Move(x+(idc-1)),z-(idc+1));
            }
        }
        return null;
    }*/
}
