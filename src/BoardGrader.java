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

    public int boardScorer(Board board) {
   /*
   checkXP(new Location(x,y,z),BLUE,0)||checkYP(new Location(x,y,z),BLUE,0)||checkZP(new Location(x,y,z),BLUE,0)||checkXM(new Location(x,y,z),BLUE,0)||checkYM(new Location(x,y,z),BLUE,0)||checkZM(new Location(x,y,z),BLUE,0)||checkYPZP(new Location(x,y,z),BLUE,0)||
           checkYPZM(new Location(x,y,z),BLUE,0)||checkZPXP(new Location(x,y,z),BLUE,0)||checkZPXM(new Location(x,y,z),BLUE,0)||checkYPXP(new Location(x,y,z),BLUE,0)||checkYMXP(new Location(x,y,z),BLUE,0)||checkYMZP(new Location(x,y,z),BLUE,0)||checkYMZM(new Location(x,y,z),BLUE,0)
           ||checkYMZPXP(new Location(x,y,z),BLUE,0)||checkYPZMXP(new Location(x,y,z),BLUE,0)
           ||checkYPZPXM(new Location(x,y,z),BLUE,0)||checkYPZPXP(new Location(x,y,z),BLUE,0)*/

        return 0;
    }

    //Very well. If you're making a scorer of your own, I will do the same. We can compare scorers to try to find new ideas.
    public int otherScorer() {
        int score = 0;
        boolean[][][] checked = new boolean[8][7][8];
        for(int z = 0; z < board.length; z++) {
            for(int y = 0; y < board[0].length; y++) {
                for(int x = 0; x < board.length; x++) {
                    Location l = new Location(x, y, z);
                    if(board[z][y][x] != EMPTY) {
                        char c = board[z][y][x];
                        score += (scorerHelper(l, 1, 0, 0, c)
                         + scorerHelper(l, 0, 1, 0, c)
                         + scorerHelper(l, 0, 0, 1, c)
                         + scorerHelper(l, 1, 1, 0, c)
                         + scorerHelper(l, -1, 1,0,  c)
                         + scorerHelper(l, 1, 0, 1, c)
                         + scorerHelper(l, 1, 0, -1, c)
                         + scorerHelper(l, 0, 1, 1, c)
                         + scorerHelper(l, 0, -1, 1, c)
                         + scorerHelper(l, 1, 1, 1, c)
                         + scorerHelper(l, 1, 1 ,-1, c)
                         + scorerHelper(l, -1,1,1, c)
                         + scorerHelper(l,-1,1,-1, c));
                        /*score = (scorerHelper(l, 1, 0, 0, c)
                         + scorerHelper(l, -1, 0, 0, c)
                         + scorerHelper(l, 0, 1, 0, c)
                         + scorerHelper(l, 0, -1, 0, c)
                         + scorerHelper(l, 0, 0, 1, c)
                         + scorerHelper(l, 0, 0, -1, c)
                         + scorerHelper(l, 1, 1, 0, c)
                         + scorerHelper(l, -1, 1, 0, c)
                         + scorerHelper(l, 1, -1, 0, c)
                         + scorerHelper(l, -1, -1, 0, c)
                         + scorerHelper(l, 0, 1, 1, c)
                         + scorerHelper(l, 0, 1, -1, c)
                         + scorerHelper(l, 0, -1, 1, c)
                         + scorerHelper(l, 0, -1, -1, c)
                         + scorerHelper(l, 1, 0, 1, c)
                         + scorerHelper(l, 1, 0, -1, c)
                         + scorerHelper(l, -1, 0, 1, c)
                         + scorerHelper(l, -1, 0, -1, c)
                         + scorerHelper())*/
                    }
                }
            }
        }
        return score;
    }
    public int scorerHelper(Location l, int x, int y, int z, char c) {
        if((l.z >= 0 && l.z < board.length) && (l.y >= 0 && l.y < board[0].length) && (l.x >= 0 && l.x < board.length)) {
            if (board[l.z + z][l.y + y][l.x + x] == c) {
                return 10 * scorerHelper(new Location(l.x + x, l.y + y, l.z + z), x, y, z, c);
            }
        }
        return 1;
    }
    public int checkXP(Location l, char player, int x, int score) { //x starts off being 0
        if(l.x==X_SIZE) {
            return x;
        }
        if(l.x < X_SIZE) {
            if(board[l.z][l.y][l.x] == player) {
                return checkXP(cL(l,1, 0, 0), player, ++x, ++score);
            }
        }
        return score;
    }

    public int checkXM(Location l, char player, int x, int score) {
        if(l.x==0) {
            return score;
        }
        if(l.x >= 0) {
            if(board[l.z][l.y][l.x] == player) {
                return checkXM(cL(l,- 1, 0, 0), player, ++x, ++score);
            }
        }
        return score;
    }
    public int checkYP(Location l, char player, int y) {
        if (l.y == Y_SIZE) {
            return y;
        }
        if (l.y < Y_SIZE) {
            if (board[l.z][l.y][l.x] == player) {
                return checkYP(cL(l,0, 1, 0), player, ++y);
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
                return checkYPZP(cL(l,0, 1, 1), player, ++y);
            }
        }
        return y;
    }
    public int checkYMZP(Location l, char player, int y) {
        if (l.y == 0||l.z==Z_SIZE) {
            return 0;
        }
        if (l.y > 0 && l.z <Z_SIZE&& y < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return checkYMZP(cL(l,0, -1, 1), player, ++y);
            } else {
                return 0;
            }
        }
        if (y == 5) {
            return 0;
        }
        return 0;
    }
    public int checkYMZM(Location l, char player, int y) {
        if (l.y == 0||l.z==0) {
            return 0;
        }
        if (l.y >=0 && l.z>=0&& y < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return checkYMZM(cL(l,0, -1, -1), player, ++y);
            } else {
                return 0;
            }
        }
        if (y == 5) {
            return 0;
        }
        return 0;
    }


    public int checkYPZM(Location l, char player, int y) {
        if (l.y == Y_SIZE||l.z == 0) {
            return 0;
        }
        if (l.y < Y_SIZE &&l.z>=0 &&y < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return checkYPZM(cL(l,0, 1, -1), player, ++y);
            } else {
                return 0;
            }
        }
        if (y == 5) {
            return 0;
        }
        return 0;
    }
    public int checkYMZPXP(Location l, char player, int y) {
        if (l.y == 0||l.z ==Z_SIZE||l.x==X_SIZE) {
            return 0;
        }
        if (l.y >=0 &&l.z<Z_SIZE &&l.x<X_SIZE&&y < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return checkYMZPXP(cL(l,1, -1, 1), player, ++y);
            } else {
                return 0;
            }
        }
        if (y == 5) {
            return 0;
        }
        return 0;
    }
    public int checkYPZMXP(Location l, char player, int y) {
        if (l.y == Y_SIZE||l.z == 0||l.x==X_SIZE) {
            return 0;
        }
        if (l.y < Y_SIZE &&l.z>=0 &&l.x<X_SIZE&&y < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return checkYPZMXP(cL(l,1, 1, -1), player, ++y);
            } else {
                return 0;
            }
        }
        if (y == 5) {
            return 0;
        }
        return 0;
    }
    public int checkYPZPXM(Location l, char player, int y) {
        if (l.y == Y_SIZE||l.z == Z_SIZE||l.x==0) {
            return 0;
        }
        if (l.y < Y_SIZE &&l.z<Z_SIZE &&l.x>=0&&y < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return checkYPZPXM(cL(l,-1, 1, 1), player, ++y);
            } else {
                return 0;
            }
        }
        if (y == 5) {
            return 0;
        }
        return 0;
    }
    public int checkYPZPXP(Location l, char player, int y) {
        if (l.y== Y_SIZE||l.z == Z_SIZE||l.x == X_SIZE) {
            return 0;
        }
        if (l.y < Y_SIZE &&l.z<Z_SIZE &&l.x<X_SIZE&&y < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return checkYPZPXP(cL(l,1, 1, 1), player, ++y);
            } else {
                return 0;
            }
        }
        if (y == 5) {
            return 0;
        }
        return 0;
    }

    public int checkZPXP(Location l, char player, int y) {
        if (l.z == Z_SIZE||l.x==X_SIZE) {
            return 0;
        }
        if (l.z < Z_SIZE &&l.x<X_SIZE&& y < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return checkZPXP(cL(l,1, 0, 1), player, ++y);
            } else {
                return 0;
            }
        }
        if (y == 5) {
            return 0;
        }
        return 0;
    }
    public int checkZPXM(Location l, char player, int y) {
        if (l.x ==0||l.z==Z_SIZE) {
            return 0;
        }
        if (l.x >=0 &&l.z<Z_SIZE &&y < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return checkZPXM(cL(l,-1, 0, 1), player, ++y);
            } else {
                return 0;
            }
        }
        if (y == 5) {
            return 0;
        }
        return 0;
    }

    public int checkYPXP(Location l, char player, int y) {
        if (l.y == Y_SIZE||l.x==X_SIZE) {
            return 0;
        }
        if (l.y < Y_SIZE &&l.x<X_SIZE &&y < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return checkYPXP(cL(l,1, 1, 0), player, ++y);
            } else {
                return 0;
            }
        }
        if (y == 5) {
            return 0;
        }
        return 0;
    }


    public int checkYMXP(Location l, char player, int y) {
        if (l.y == 0||l.x==X_SIZE) {
            return 0;
        }
        if (l.y >=0 &&l.x<X_SIZE&& y < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return checkYMXP(cL(l,1, -1, 0), player, ++y);
            } else {
                return 0;
            }
        }
        if (y == 5) {
            return 0;
        }
        return 0;
    }



    public int checkYM(Location l, char player, int y) {
        if(l.y==Y_SIZE) {
            return 0;
        }
        if(l.y >= 0 && y < 5) {
            if(board[l.z][l.y][l.x] == player) {
                return checkYM(cL(l,0,-1,0), player, ++y);
            } else {
                return 0;
            }
        }
        if(y==5) {
            return 0;
        }

        return 0;
    }
    public int checkZP(Location l, char player, int z) {
        if(l.z==Z_SIZE) {
            return 0;
        }
        if(l.z < Z_SIZE && z < 5) {
            if(board[l.z][l.y][l.x] == player) {
                return checkZP(cL(l,0,0,1), player, ++z);
            } else {
                return 0;
            }
        }

        if(z==5) {
            return 0;
        }
        return 0;
    }
    public int checkZM(Location l, char player, int z) {
        if (l.z == 0) {
            return 0;
        }
        if (l.z >= 0 && z < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return checkZM(cL(l, 0, 0, -1), player, ++z);
            } else {
                return 0;
            }
        }

        if (z == 5) {
            return 0;
        }
        return 0;
    }

    Location cL(Location l, int x, int y, int z) {
        return new Location(l.x + x, l.y + y, l.z + z);
    }


}
/*
new Location(x,y,z),BLUE,0)||checkYP(new Location(x,y,z),BLUE,0
*/