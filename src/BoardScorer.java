/*TODO:
 * 1. Create 11 Arraylists: 2 sets of five, one for each player, counting doubles, triples, quadruples, and quintuples, and one for useless garbage
 * 2. Implement a //System to scan the board and categorize each and every piece into one of these Arraylists
 * 3. Score the contents of each Arraylist according to their priority, and make sure that Craig knows the difference between them.
 * -RK
 * */

import java.lang.reflect.Method;
import java.util.ArrayList;

//TODO: HOW TO BEAT BLOCKER. Look at all the spots that give you 6. Look at all the spots for them, block them. 

public class BoardScorer {

    public static final char EMPTY = '-', letter = 'R', BLUE = 'B', PLAYING = '-', TIE = 'T';
    public static final int X_SIZE = 8, Y_SIZE = 7, Z_SIZE = 8;
    char[][][] board=null;
    boolean opportunity=false;
    Method m= null;
    Class<?>className;
    int maxCount=1;

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

        maxCount = 0;
        System.out.println("\t\t\t\t\t"+checkXP(new Location(x, y, z), letter, 1));
        if (checkXP(new Location(x, y, z), letter, 1) >= maxCount) {
            //System.out.println("\t\t\t PLEASE SEE MNEEEE");
            if (x < X_SIZE - (checkXP(new Location(x, y, z), letter, 1))) {
                    //System.out.println("\t\t\t\t\tEY I MAKE MOVE");
                    if (board.getBoard()[z][y][x+(checkXP(new Location(x, y, z), letter, 1))] != opponentLetter)
                        return new Move(x + (checkXP(new Location(x, y, z), letter, 1)), z);
            }
        }
        if (checkYP(new Location(x, y, z), letter, 1) >= maxCount) {
            //System.out.println("\t\t\t PLEASE SEE MNEEEE");
            if (y < Y_SIZE - (checkYP(new Location(x, y, z), letter, 1))) {
                //System.out.println("\t\t\t\t\tEY I MAKE MOVE");
                if (board.getBoard()[z][y+(checkYP(new Location(x, y, z), letter, 1))][x] != opponentLetter) return new Move(x, z);
            }
        }
        if (checkZP(new Location(x, y, z), letter, 1) >= maxCount) {
            //System.out.println("\t\t\t PLEASE SEE MNEEEE");
            if (z < Z_SIZE - (checkZP(new Location(x, y, z), letter, 1)))
                if (board.getBoard()[z+checkZP(new Location(x, y, z), letter, 1)][y][x] != opponentLetter) return new Move(x, z + (checkZP(new Location(x, y, z), letter, 1)));
        }
        if (checkXM(new Location(x, y, z), letter, 1) >= maxCount) {
            //System.out.println("\t\t\t PLEASE SEE MNEEEE");
            if (x - (checkXM(new Location(x, y, z), letter, 1)) >= 0)
                if (board.getBoard()[z][y][x-(checkXM(new Location(x, y, z), letter, 1))] != opponentLetter) return new Move(x - (checkXM(new Location(x, y, z), letter, 1)), z);
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
            if (z < Z_SIZE - (checkYPZPXM(new Location(x, y, z), letter, 1)) && x -(checkYPZPXM(new Location(x, y, z), letter, 1))>=0)
                if (board.getBoard()[z+(checkYPZPXM(new Location(x, y, z), letter, 1))][y+(checkYPZPXM(new Location(x, y, z), letter, 1))][x-(checkYPZPXM(new Location(x, y, z), letter, 1))] != opponentLetter) return new Move(x - (checkYPZPXM(new Location(x, y, z), letter, 1)), z + (checkYPZPXM(new Location(x, y, z), letter, 1)));
        }
        if (checkYPZPXP(new Location(x, y, z), letter, 1) >= maxCount) {
            //System.out.println("\t\t\t PLEASE SEE MNEEEE");
            if (z < Z_SIZE - (checkYPZPXP(new Location(x, y, z), letter, 1) ) && x +(checkYPZPXP(new Location(x, y, z), letter, 1) )< X_SIZE)
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
