import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class BoardGrader {
    Location l=null;
    char letter=0;
    int x=0;
    int score=0;
    char[][][] board=null;
    public static final char EMPTY = '-', RED = 'R', BLUE = 'B', PLAYING = '-', TIE = 'T';
    public static final int X_SIZE=8,Y_SIZE=7, Z_SIZE = 8;

    public BoardGrader(Board board,Location l, char letter, int x, int score) {
        this.l = l;
        this.letter = letter;
        this.x = x;
        this.score = score;
        this.board=board.getBoard();
    }


    /*Idea: Code a new method to gauge the priority of a move. Horizontal has the highest priority, then vertical, then diagonal, then special diagonal.*/

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

/*
* New plan: Our current grader is getting us nowhere. We need a new one
* 1. Create 11 Arraylists: 2 sets of five, one for each player, counting doubles, triples, quadruples, and quintuples, and one for useless garbage
* 2. Implement a system to scan the board and categorize each and every piece into one of these Arraylists
* 3. Score the contents of each Arraylist according to their priority, and make sure that Craig knows the difference between them.
* -RK
* */

//TODO: I HAVE CREATED A NEW CLASS FOR US TO USE. called BoardScorer
    public int boardGrader(Board board) {
        int score = 0;
        int count = 0;

        for (int z = 0; z < board.getBoard().length; z++) {
            for (int y = 0; y < board.getBoard()[0].length; y++) {
                for (int x = 0; x < board.getBoard().length; x++) {
                    int lz = board.getBoard().length;
                    int lx = board.getBoard()[0][0].length;
                    int ly = board.getBoard()[0].length;
               /*Will go through the current instance (called in getMove) of the board. Checks through the board, and finds the grade. I'm thinking that we should
               check the board based on our occurrences to win. (We would only go four scenarios deep) Whichever has the most occurrences to win, we count as the best,
               return that score, then put that move as the getMove (already done in getMove)
               -VK*/

                    //You remember how we coded win checks? We're gonna do that, except with scoring.
                    //I made a few examples for you. We're gonna be coding these together, in this format. -RK

                    //Got it. I will work through this and begin to understand. I had to delete my clone
                    // on my computer, so I could actually work...but hey we're good, so yay

                    if (board.getBoard()[z][y][x] != EMPTY) {
                        for (int xx = x; xx < board.getBoard()[0][0].length; xx++) {
                            if (board.getBoard()[z][y][xx] == letter) {
                                count++;
                            } else {
                                break;
                            }
                        }
                        score+= count;
                        count = 0;

                        for (int yy = y; yy < board.getBoard()[0].length; yy++) {
                            if (board.getBoard()[z][yy][x] == letter) {
                                count++;
                            } else {
                                break;
                            }
                        }
                        score+= count;
                        count = 0;

                        for (int zz = z; zz < board.getBoard().length; zz++) {
                            if (board.getBoard()[zz][y][x] == letter) {
                                count++;
                            } else {
                                break;
                            }
                        }
                        score+= count;
                        count = 0;

                        for (int zx = 0; zx + z < lz && zx + x < lx; zx++) {
                            if (board.getBoard()[z + zx][y][x + zx] == letter) {
                                count++;
                            } else {
                                break;
                            }
                        }
                        score+= count;
                        count = 0;

                        for (int yx = 0; yx + y < ly && yx + x < lx; yx++) {
                            if (board.getBoard()[z][y + yx][x + yx] == letter) {
                                count++;
                            } else {
                                break;
                            }
                        }

                        score+= count;
                        count = 0;

                        for (int yz = 0; yz + y < ly && yz + z < lz; yz++) {
                            if (board.getBoard()[z + yz][y + yz][x] == letter) {
                                count++;
                            } else {
                                break;
                            }
                        }

                        score+= count;
                        count = 0;

                        for (int xyz = 0; xyz + y < ly && xyz + z < lz && xyz + x < lx; xyz++) {
                            if (board.getBoard()[z + xyz][y + xyz][x + xyz] == letter) {
                                count++;
                            } else {
                                break;
                            }
                        }
                        score+= count;
                        count = 0;

                        //MINUSSSSSSSsdjif j;asd fj;lasd]fa]j sdj faksldjf aksldjfkljh
                        //AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
                        //MINUS GUYS LETS DO MINUSSWWSSSSDlji hadsgmhfasdbyfse yhdf
                        //AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
                        //AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
                        for (int xx = x; xx >= 0; xx--) {
                            if (board.getBoard()[z][y][xx] == letter) {
                                count++;
                            } else {
                                break;
                            }
                        }
                        score+=count;
                        count = 0;

                        for (int yy = y; yy >= 0; yy--) {
                            if (board.getBoard()[z][yy][x] == letter) {
                                count++;
                            } else {
                                break;
                            }
                        }
                        score+= count;
                        count = 0;

                        for (int zz = z; zz >= 0; zz--) {
                            if (board.getBoard()[zz][y][x] == letter) {
                                count++;
                            } else {
                                break;
                            }
                        }
                        score+= count;
                        count = 0;

                        for (int zxm = 0; z - zxm >= 0 && x - zxm >= 0; zxm++) {
                            if (board.getBoard()[z - zxm][y][x - zxm] == letter) {
                                count++;
                            } else {
                                break;
                            }
                        }
                        score+= count;
                        count = 0;

                        for (int yxm = 0; y - yxm >= 0 && x - yxm >= 0; yxm++) {
                            if (board.getBoard()[z][y - yxm][x - yxm] == letter) {
                                count++;
                            } else {
                                break;
                            }
                        }

                        score+= count;
                        count = 0;

                        for (int yz = 0; yz + y < ly && yz + z < lz; yz++) {
                            if (board.getBoard()[z + yz][y + yz][x] == letter) {
                                count++;
                            } else {
                                break;
                            }
                        }

                        score+= count;
                        count = 0;

                        for (int xyz = 0; xyz + y < ly && xyz + z < lz && xyz + x < lx; xyz++) {
                            if (board.getBoard()[z + xyz][y + xyz][x + xyz] == letter) {
                                count++;
                            } else {
                                break;
                            }
                        }

                        score+= count;
                        count = 0;

                    }
                }
            }
        }
        return score;
    }

//TODO: I AM GOING TO MAKE MY OWN VERSION OF BOARDGRADER() called BoardScorer(), using our old recursive methods so i can understand it


    public int boardScorer(Board board, char letter) {
        int score=0, finalScore=0;
        int lastScore=3;
        for (int z = 0; z < board.getBoard().length; z++) {
            for (int y = 0; y < board.getBoard()[0].length; y++) {
                for (int x = 0; x < board.getBoard().length; x++) {
                    /*score = checkXP(new Location(x, y, z), letter, 0, 0) + checkYP(new Location(x, y, z), letter, 0) + checkZP(new Location(x, y, z), letter, 0) + checkXM(new Location(x, y, z), letter, 0, 0) + checkYM(new Location(x, y, z), letter, 0) + checkZM(new Location(x, y, z), letter, 0) + checkYPZP(new Location(x, y, z), letter, 0) +
                            checkYPZM(new Location(x, y, z), letter, 0) + checkZPXP(new Location(x, y, z), letter, 0) + checkZPXM(new Location(x, y, z), letter, 0) + checkYPXP(new Location(x, y, z), letter, 0) + checkYMXP(new Location(x, y, z), letter, 0) + checkYMZP(new Location(x, y, z), letter, 0) + checkYMZM(new Location(x, y, z), letter, 0)
                            + checkYMZPXP(new Location(x, y, z), letter, 0) + checkYPZMXP(new Location(x, y, z), letter, 0)
                            + checkYPZPXM(new Location(x, y, z), letter, 0) + checkYPZPXP(new Location(x, y, z), letter, 0);*/
                    score = boardScorerHelper(board, letter, x, y, z, score);
                    if(score>lastScore) {
                        lastScore=score;
                    }
                }
            }
        }
        finalScore=lastScore;
        return finalScore;
    }

    public int boardScorerHelper(Board board, char letter, int x, int y, int z, int score) {
        return checkXP(new Location(x, y, z), letter, 1)
                + checkYP(new Location(x, y, z), letter, 1)
                + checkZP(new Location(x, y, z), letter, 1)
                + checkXM(new Location(x, y, z), letter, 1)
                + checkYM(new Location(x, y, z), letter, 1)
                + checkZM(new Location(x, y, z), letter, 1)
                + checkYPZP(new Location(x, y, z), letter, 1)
                + checkYPZM(new Location(x, y, z), letter, 1)
                + checkZPXP(new Location(x, y, z), letter, 1)
                + checkZPXM(new Location(x, y, z), letter, 1)
                + checkYPXP(new Location(x, y, z), letter, 1)
                + checkYMXP(new Location(x, y, z), letter, 1)
                + checkYMZP(new Location(x, y, z), letter, 1)
                + checkYMZM(new Location(x, y, z), letter, 1)
                + checkYMZPXP(new Location(x, y, z), letter, 1)
                + checkYPZMXP(new Location(x, y, z), letter, 1)
                + checkYPZPXM(new Location(x, y, z), letter, 1)
                + checkYPZPXP(new Location(x, y, z), letter, 1);
    }
    
   /* public Move boardEmergencyDetector(Board board, char letter, int x, int y, int z) {

        boolean xplus = false, xminus = false, yplus = false, yminus = false, zplus = false, zminus = false;
        int xvalue = x, yvalue = y, zvalue = z;
        Integer returnValue = 0;

        boolean xdonep = false, ydonep = false, zdonep = false, xdonem = false, ydonem = false, zdonem = false;

        ArrayList<Method> methods = new ArrayList<Method>();
        for (int c = 0; c < BoardGrader.class.getDeclaredMethods().length; c++) {
            if (BoardGrader.class.getDeclaredMethods()[c].getName().contains("check")) {
                methods.add(BoardGrader.class.getDeclaredMethods()[c]);
            }
        }

        for (Method m : methods) {
            try {
                if (m.getGenericReturnType() == Integer.class) {
                    if ((Integer) m.invoke(new Location(x, y, z), letter, 0) >= 4) {
                        returnValue = (Integer) m.invoke(new Location(x, y, z), letter, 0);
                        //TODO: Return the String of the method, check to see if you have a P or M, then check where it is near to see what to do.
                        for (int v = 0; v < m.getName().length(); v++) {
                            if (m.getName().charAt(v) == 'P') {
                                if (m.getName().charAt(v--) == 'X' && !xdonep) {
                                    xplus = true;
                                    xdonep = true;
                                }
                                if (m.getName().charAt(v--) == 'Y' && !ydonep) {
                                    yplus = true;
                                    ydonep = true;
                                }
                                if (m.getName().charAt(v--) == 'Z' && !zdonep) {
                                    zplus = true;
                                    zdonep = true;
                                }
                            }

                            if (m.getName().charAt(v) == 'M') {
                                if (m.getName().charAt(v--) == 'X' && !xdonem) {
                                    xminus = true;
                                    xdonem = true;
                                }
                                if (m.getName().charAt(v--) == 'Y' && !ydonem) {
                                    yminus = true;
                                    ydonem = true;
                                }
                                if (m.getName().charAt(v--) == 'Z' && !zdonem) {
                                    zminus = true;
                                    zdonem = true;
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if (xplus) {
            xvalue += (Integer) returnValue;
        }
        if (yplus) {
            yvalue += (Integer) returnValue;
        }
        if (zplus) {
            zvalue += (Integer) returnValue;
        }
        if (xminus) {
            xvalue -= (Integer) returnValue;
        }
        if (yminus) {
            yvalue -= (Integer) returnValue;
        }
        if (zminus) {
            zvalue -= (Integer) returnValue;
        }
            return new Move(xvalue, zvalue);
        /*if(checkXP(new Location(x, y, z), letter, 0) >= 3|| checkYP(new Location(x, y, z), letter, 0) >= 3|| checkZP(new Location(x, y, z), letter, 0) >= 3|| checkXM(new Location(x, y, z), letter, 0, 0) >= 3|| checkYM(new Location(x, y, z), letter, 0) >= 3|| checkZM(new Location(x, y, z), letter, 0) >= 3|| checkYPZP(new Location(x, y, z), letter, 0) >= 3||
                checkYPZM(new Location(x, y, z), letter, 0) >= 3|| checkZPXP(new Location(x, y, z), letter, 0) >= 3|| checkZPXM(new Location(x, y, z), letter, 0) >= 3|| checkYPXP(new Location(x, y, z), letter, 0) >= 3|| checkYMXP(new Location(x, y, z), letter, 0) >= 3|| checkYMZP(new Location(x, y, z), letter, 0) >= 3|| checkYMZM(new Location(x, y, z), letter, 0)
                >= 3|| checkYMZPXP(new Location(x, y, z), letter, 0) >= 3|| checkYPZMXP(new Location(x, y, z), letter, 0)
                >= 3|| checkYPZPXM(new Location(x, y, z), letter, 0) >= 3|| checkYPZPXP(new Location(x, y, z), letter, 0)>=3) {

            return true;
        }

        return false;
    }*/



    //Very well. If you're making a scorer of your own, I will do the same. We can compare scorers to try to find new ideas.
    public int otherScorer(Board b, char letter) {
        int score = 0, finalScore = 0, lastScore = 0;
        boolean[][][] checked = new boolean[8][7][8];
        for(int z = 0; z < b.getBoard().length; z++) {
            for(int y = 0; y < b.getBoard()[0].length; y++) {
                for(int x = 0; x < b.getBoard().length; x++) {
                    Location l = new Location(x, y, z);
                    score = scorerHelper(l, 1, 0, 0, letter, 0, b)
                            + scorerHelper(l, 0, 1, 0, letter, 0, b)
                            + scorerHelper(l, 0, 0, 1, letter, 0, b)
                            + scorerHelper(l, -1, 0, 0, letter, 0, b)
                            + scorerHelper(l, 0, -1, 0, letter, 0, b)
                            + scorerHelper(l, 0, 0, -1, letter, 0, b)
                            + scorerHelper(l, 0, 1, 1, letter, 0, b)
                            + scorerHelper(l, 0, 1, -1, letter, 0, b)
                            + scorerHelper(l, 1, 0, 1, letter, 0, b)
                            + scorerHelper(l, -1, 0, 1, letter, 0, b)
                            + scorerHelper(l, 1, 1, 0, letter, 0, b)
                            + scorerHelper(l, 1, -1, 0, letter, 0, b)
                            + scorerHelper(l, 0, -1, 1, letter, 0, b)
                            + scorerHelper(l, 0, -1, -1, letter, 0, b)
                            + scorerHelper(l, 1, -1, 1, letter, 0, b)
                            + scorerHelper(l, 1, 1, -1, letter, 0, b)
                            + scorerHelper(l, -1, 1, 1, letter, 0, b)
                            + scorerHelper(l, 1, 1, 1, letter, 0, b);
                    if(score > lastScore)
                        lastScore = score;
                }
            }
        }
        finalScore = lastScore;
        return finalScore;
    }
    public int scorerHelper(Location l, int x, int y, int z, char c, int s, Board b) {
        if((l.z + z >= 0 && l.z + z < b.getBoard().length) && (l.y + y >= 0 && l.y + y < b.getBoard()[0].length) && (l.x + x >= 0 && l.x + x < b.getBoard().length)) {
            if (b.getBoard()[l.z + z][l.y + y][l.x + x] == c) {
                return scorerHelper(new Location(l.x + x, l.y + y, l.z + z), x, y, z, c, ++s, b);
            }
        }
        return s;
    }
    public int checkXP(Location l, char player, int x) { //x starts off being 0
        if(l.x==X_SIZE) {
            return x;
        }
        if(l.x < X_SIZE) {
            if(board[l.z][l.y][l.x] == player) {
                return 10 * checkXP(cL(l,1, 0, 0), player, x);
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
                return 10 *  checkXM(cL(l,- 1, 0, 0), player, x);
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
                return 10 * checkYP(cL(l,0, 1, 0), player, y);
            }
        }
        return score;
    }

    public int checkYPZP(Location l, char player, int y) {
        if (l.y == Y_SIZE||l.z==Z_SIZE) {
            return y;
        }
        if (l.y < Y_SIZE && l.z <Z_SIZE&& y < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return 10 * checkYPZP(cL(l,0, 1, 1), player, y);
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
                return 10 * checkYMZP(cL(l,0, -1, 1), player, y);
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
                return 10 * checkYMZM(cL(l,0, -1, -1), player, y);
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
                return 10 * checkYPZM(cL(l, 0, 1, -1), player, y);
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
                return 10 * checkYMZPXP(cL(l, 1, -1, 1), player, y);
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
                return 10 * checkYPZMXP(cL(l,1, 1, -1), player, y);
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
                return 10 * checkYPZPXM(cL(l,-1, 1, 1), player, y);
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
                return 10 * checkYPZPXP(cL(l,1, 1, 1), player, y);
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
                return 10 * checkZPXP(cL(l,1, 0, 1), player, y);
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
                return 10 * checkZPXM(cL(l,-1, 0, 1), player, y);
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
                return 10 * checkYPXP(cL(l,1, 1, 0), player, y);
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
                return 10 * checkYMXP(cL(l,1, -1, 0), player,y);
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
                return 10 * checkYM(cL(l,0,-1,0), player, y);
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
                return 10 * checkZP(cL(l,0,0,1), player, z);
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
                return 10 * checkZM(cL(l, 0, 0, -1), player, z);
            } 
        }
        return z;
    }

    Location cL(Location l, int x, int y, int z) {
        return new Location(l.x + x, l.y + y, l.z + z);
    }


}
/*
new Location(x,y,z),BLUE,0)||checkYP(new Location(x,y,z),BLUE,0
*/