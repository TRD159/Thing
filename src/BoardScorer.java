/*TODO:
 * 1. Create 11 Arraylists: 2 sets of five, one for each player, counting doubles, triples, quadruples, and quintuples, and one for useless garbage
 * 2. Implement a system to scan the board and categorize each and every piece into one of these Arraylists
 * 3. Score the contents of each Arraylist according to their priority, and make sure that Craig knows the difference between them.
 * -RK
 * */

import java.lang.reflect.Method;
import java.util.ArrayList;

//TODO: HOW TO BEAT BLOCKER. Look at all the spots that give you 6. Look at all the spots for them, block them. 

public class BoardScorer {

    public static final char EMPTY = '-', RED = 'R', BLUE = 'B', PLAYING = '-', TIE = 'T';
    public static final int X_SIZE = 8, Y_SIZE = 7, Z_SIZE = 8;
    char[][][] board=null;
    boolean opportunity=false;
    Method m= null;
    Class<?>className;

    public boolean isOpportunity() {
        return opportunity;
    }

    public Move score(Board board, char letter, int x, int y, int z) {
        this.board = board.getBoard();
        ArrayList<Method> methods = new ArrayList<Method>();
        for (int c = 0; c < BoardScorer.class.getDeclaredMethods().length; c++) {
            if (BoardScorer.class.getDeclaredMethods()[c].getName().contains("check")) {
                methods.add(BoardScorer.class.getDeclaredMethods()[c]);
            }
        }
        for (Method m:
             methods) {
            //System.out.println(m.toString());

        }
        Object[]params={new Location(x, y, z), letter, 0};
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
                    System.out.println("AYYO");
                    if ((Integer) (methods.get(c).invoke(/*new Location(x, y, z), letter, 0)*/className,params)) >= 4) {
                        System.out.println("HEY ");
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
        }
        return null;
    }

    public Integer checkXP(Location l, char player, int x) { //x starts off being 0
        if(l.x==X_SIZE) {
            return x;
        }
        if(l.x < X_SIZE) {
            if(board[l.z][l.y][l.x] == player) {
                return   checkXP(cL(l,1, 0, 0), player, x);
            }
        }
        return x;
    }

    public Integer checkXM(Location l, char player, int x) {
        if(l.x==0) {
            return x;
        }
        if(l.x >= 0) {
            if(board[l.z][l.y][l.x] == player) {
                return    checkXM(cL(l,- 1, 0, 0), player, x);
            }
        }
        return x;
    }
    public Integer checkYP(Location l, char player, int y) {
        if (l.y == Y_SIZE) {
            return y;
        }
        if (l.y < Y_SIZE) {
            if (board[l.z][l.y][l.x] == player) {
                return   checkYP(cL(l,0, 1, 0), player, y);
            }
        }
        return y;
    }

    public Integer checkYPZP(Location l, char player, int y) {
        if (l.y == Y_SIZE||l.z==Z_SIZE) {
            return y;
        }
        if (l.y < Y_SIZE && l.z <Z_SIZE&& y < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return   checkYPZP(cL(l,0, 1, 1), player, y);
            }
        }
        return y;
    }
    public Integer checkYMZP(Location l, char player, int y) {
        if (l.y == 0||l.z==Z_SIZE) {
            return y;
        }
        if (l.y > 0 && l.z <Z_SIZE) {
            if (board[l.z][l.y][l.x] == player) {
                return   checkYMZP(cL(l,0, -1, 1), player, y);
            }
        }
        return y;
    }
    public Integer checkYMZM(Location l, char player, int y) {
        if (l.y == 0||l.z==0) {
            return y;
        }
        if (l.y >=0 && l.z>=0&& y < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return   checkYMZM(cL(l,0, -1, -1), player, y);
            }
        }
        return y;
    }

    public Integer checkYPZM(Location l, char player, int y) {
        if (l.y == Y_SIZE||l.z == 0) {
            return y;
        }
        if (l.y < Y_SIZE &&l.z>=0 &&y < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return   checkYPZM(cL(l, 0, 1, -1), player, y);
            }
        }
        return y;
    }
    public Integer checkYMZPXP(Location l, char player, int y) {
        if (l.y == 0||l.z ==Z_SIZE||l.x==X_SIZE) {
            return y;
        }
        if (l.y >=0 &&l.z<Z_SIZE &&l.x<X_SIZE&&y < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return   checkYMZPXP(cL(l, 1, -1, 1), player, y);
            }
        }
        return y;
    }
    public Integer checkYPZMXP(Location l, char player, int y) {
        if (l.y == Y_SIZE||l.z == 0||l.x==X_SIZE) {
            return y;
        }
        if (l.y < Y_SIZE &&l.z>=0 &&l.x<X_SIZE&&y < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return   checkYPZMXP(cL(l,1, 1, -1), player, y);
            }
        }
        return y;
    }
    public Integer checkYPZPXM(Location l, char player, int y) {
        if (l.y == Y_SIZE||l.z == Z_SIZE||l.x==0) {
            return y;
        }
        if (l.y < Y_SIZE &&l.z<Z_SIZE &&l.x>=0&&y < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return   checkYPZPXM(cL(l,-1, 1, 1), player, y);
            }
        }
        return y;
    }
    public Integer checkYPZPXP(Location l, char player, int y) {
        if (l.y== Y_SIZE||l.z == Z_SIZE||l.x == X_SIZE) {
            return y;
        }
        if (l.y < Y_SIZE &&l.z<Z_SIZE &&l.x<X_SIZE&&y < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return   checkYPZPXP(cL(l,1, 1, 1), player, y);
            }
        }
        return y;
    }

    public Integer checkZPXP(Location l, char player, int y) {
        if (l.z == Z_SIZE||l.x==X_SIZE) {
            return y;
        }
        if (l.z < Z_SIZE &&l.x<X_SIZE&& y < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return   checkZPXP(cL(l,1, 0, 1), player, y);
            }
        }
        return y;
    }
    public Integer checkZPXM(Location l, char player, int y) {
        if (l.x ==0||l.z==Z_SIZE) {
            return y;
        }
        if (l.x >=0 &&l.z<Z_SIZE &&y < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return   checkZPXM(cL(l,-1, 0, 1), player, y);
            }
        }
        return y;
    }

    public Integer checkYPXP(Location l, char player, int y) {
        if (l.y == Y_SIZE||l.x==X_SIZE) {
            return y;
        }
        if (l.y < Y_SIZE &&l.x<X_SIZE &&y < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return   checkYPXP(cL(l,1, 1, 0), player, y);
            }
        }
        return y;
    }


    public Integer checkYMXP(Location l, char player, int y) {
        if (l.y == 0||l.x==X_SIZE) {
            return y;
        }
        if (l.y >=0 &&l.x<X_SIZE&& y < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return   checkYMXP(cL(l,1, -1, 0), player,y);
            }
        }
        return y;
    }



    public Integer checkYM(Location l, char player, int y) {
        if(l.y==Y_SIZE) {
            return y;
        }
        if(l.y >= 0 && y < 5) {
            if(board[l.z][l.y][l.x] == player) {
                return   checkYM(cL(l,0,-1,0), player, y);
            }
        }

        return y;
    }
    public Integer checkZP(Location l, char player, int z) {
        if(l.z==Z_SIZE) {
            return z;
        }
        if(l.z < Z_SIZE && z < 5) {
            if(board[l.z][l.y][l.x] == player) {
                return   checkZP(cL(l,0,0,1), player, z);
            }
        }
        return z;
    }
    public Integer checkZM(Location l, char player, int z) {
        if (l.z == 0) {
            return z;
        }
        if (l.z >= 0 && z < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return   checkZM(cL(l, 0, 0, -1), player, z);
            }
        }
        return z;
    }

    Location cL(Location l, int x, int y, int z) {
        return new Location(l.x + x, l.y + y, l.z + z);
    }
}
