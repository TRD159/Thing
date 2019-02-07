import java.lang.reflect.Method;
import java.util.Random;
import java.util.concurrent.Callable;


/*
TODO 2/5/19: FIGURE OUT BLOCKER. I THINK WE SHOULD JUST GO WITH THE boardScorer() METHOD, UNLESS YOU'RE PRETTY CLOSE TO MAKING YOUR otherScorer() WORK.
TODO: IN ADDITION, we need to create a blocker section for our AI. Without it, our AI loses 10 and wins 10 against others' AIs, because if they go first, they win.
TODO: Change your AI to the label on your friend's computer: Something like "T308024", if you want to battle them. Tully's server doesn't work today.
TODO: Let's start coding a lookAhead method, to do Alpha Beta Pruning.
TODO: WE CAN DO THIS, ROHAN! LETSA GOOOOOOOOOOOOOOOOOOOOO!!
___________________▄▄▄▀▀▀▀▀▀▀▄
_______________▄▀▀____▀▀▀▀▄____█
___________▄▀▀__▀▀▀▀▀▀▄___▀▄___█
__________█▄▄▄▄▄▄_______▀▄__▀▄__█
_________█_________▀▄______█____█_█
______▄█_____________▀▄_____▐___▐_▌
______██_______________▀▄___▐_▄▀▀▀▄
______█________██_______▌__▐▄▀______█
______█_________█_______▌__▐▐________▐
_____▐__________▌_____▄▀▀▀__▌_______▐_____________▄▄▄▄▄▄
______▌__________▀▀▀▀________▀▀▄▄▄▀______▄▄████▓▓▓▓▓▓▓███▄
______▌____________________________▄▀__▄▄█▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▄
______▐__________________________▄▀_▄█▓▓▓▓▓▓▓▓▓▓_____▓▓____▓▓█▄
_______▌______________________▄▀_▄█▓▓▓▓▓▓▓▓▓▓▓____▓▓_▓▓_▓▓__▓▓█
_____▄▀▄_________________▄▀▀▌██▓▓▓▓▓▓▓▓▓▓▓▓▓__▓▓▓___▓▓_▓▓__▓▓█
____▌____▀▀▀▄▄▄▄▄▄▄▄▀▀___▌█▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓__▓________▓▓___▓▓▓█
_____▀▄_________________▄▀▀▓▓▓▓▓▓▓▓█████████████▄▄_____▓▓__▓▓▓█
_______█▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓█▓▓▓▓▓██▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓██▄▄___▓▓▓▓▓█
_______█▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓█▓▓███▓▓▓▓████▓▓▓▓▓▓▓▓▓▓▓▓▓██▓▓▓▓▓▓█
________█▓▓▓▓▓▓▓▓▓▓▓▓▓▓█▓█▓▓██░░███████░██▓▓▓▓▓▓▓▓▓▓██▓▓▓▓▓█
________█▓▓▓▓▓▓▓▓▓▓▓▓▓▓██▓░░░░░█░░░░░██░░░░██▓▓▓▓▓▓▓▓▓██▓▓▓▓▌
________█▓▓▓▓▓▓▓▓▓▓▓▓▓▓███░░░░░░░░____░██░░░░░░░██▓▓▓▓▓▓▓██▓▓▌
________▐▓▓▓▓▓▓▓▓▓▓▓▓▓▓██░░░░░░░________░░░░░░░░░██████▓▓▓▓▓█▓▌
________▐▓▓▓▓▓▓▓▓▓▓▓▓▓▓██░░░░░░___▓▓▓▓▓░░░░░░░███░░███▓▓▓▓▓█▓▌
_________█▓▓▓▓▓▓▓▓▓▓▓▓▓██░░░░░___▓▓█▄▄▓░░░░░░░░___░░░░█▓▓▓▓▓█▓▌
_________█▓▓▓▓▓▓▓▓▓▓▓▓▓█░░█░░░___▓▓██░░░░░░░░▓▓▓▓__░░░░█▓▓▓▓██
_________█▓▓▓▓▓▓▓▓▓▓▓▓▓█░███░░____▓░░░░░░░░░░░█▄█▓__░░░░█▓▓█▓█
_________▐▓▓▓▓▓▓▓▓▓▓▓▓▓█░█████░░░░░░░░░░░░░░░░░█▓__░░░░███▓█
__________█▓▓▓▓▓▓▓▓▓▓▓▓█░░███████░░░░░░░░░░░░░░░▓_░░░░░██▓█
__________█▓▓▓▓▓▓▓▓▓▓▓▓█░░░███████░░░░░░░░░░░░░░_░░░░░██▓█
__________█▓▓▓▓▓▓▓▓▓▓▓▓█░░░███████░░░░░░░░░░░░░░░░░░░██▓█
___________█▓▓▓▓▓▓▓▓▓▓▓▓█░░░░███████░░░░░░░░░░░█████░██░░░
___________█▓▓▓▓▓▓▓▓▓▓▓▓█░░░░░░__███████░░░░░███████░░█░░░░
___________█▓▓▓▓▓▓▓▓▓▓▓▓▓█░░░░░░█▄▄▄▀▀▀▀████████████░░█░░░░
___________▐▓▓▓▓▓▓▓▓▓▓▓▓█░░░░░░██████▄__▀▀░░░███░░░░░█░░░
___________▐▓▓▓▓▓▓▓▓▓▓▓█▒█░░░░░░▓▓▓▓▓███▄░░░░░░░░░░░░░░░______▄▄▄
___________█▓▓▓▓▓▓▓▓▓█▒▒▒▒█░░░░░░▓▓▓▓▓█░░░░░░░░░░░░░░░▄▄▄_▄▀▀____▀▄
__________█▓▓▓▓▓▓▓▓▓█▒▒▒▒█▓▓░░░░░░░░░░░░░░░░░░░░░____▄▀____▀▄_________▀▄
_________█▓▓▓▓▓▓▓▓▓█▒▒▒▒█▓▓▓▓░░░░░░░░░░░░░░░░░______▐▄________█▄▄▀▀▀▄__█
________█▓▓▓▓▓▓▓▓█▒▒▒▒▒▒█▓▓▓▓▓▓▓░░░░░░░░░____________█_█______▐_________▀▄▌
_______█▓▓▓▓▓▓▓▓█▒▒▒▒▒▒███▓▓▓▓▓▓▓▓▓▓▓█▒▒▄___________█__▀▄____█____▄▄▄____▐
______█▓▓▓▓▓▓▓█_______▒▒█▒▒██▓▓▓▓▓▓▓▓▓▓█▒▒▒▄_________█____▀▀█▀▄▀▀▀___▀▀▄▄▐
_____█▓▓▓▓▓██▒_________▒█▒▒▒▒▒███▓▓▓▓▓▓█▒▒▒██________▐_______▀█_____________█
____█▓▓████▒█▒_________▒█▒▒▒▒▒▒▒▒███████▒▒▒▒██_______█_______▐______▄▄▄_____█
__█▒██▒▒▒▒▒▒█▒▒____▒▒▒█▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▒____▒█▓█__▄█__█______▀▄▄▀▀____▀▀▄▄█
__█▒▒▒▒▒▒▒▒▒▒█▒▒▒████▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█_______█▓▓█▓▓▌_▐________▐____________▐
__█▒▒▒▒▒▒▒▒▒▒▒███▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▒_______█▓▓▓█▓▌__▌_______▐_____▄▄____▐
_█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▒▒_____█▓▓▓█▓▓▌__▌_______▀▄▄▀______▐
_█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒███████▓▓█▓▓▓▌__▀▄_______________▄▀
_█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒███▒▒▒▒▒▒▒██▓▓▓▓▓▌___▀▄_________▄▀▀
█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒██▒▒▒▒▒▒▒▒▒▒▒▒▒█▓▓▓▓▓▀▄__▀▄▄█▀▀▀
█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒██▓▓▓▓██▄▄▄▀
█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒████
█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█
_█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▒▒▒█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▄▄▄▄▄
_█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▒▒▒▒█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒███▒▒▒▒▒▒██▄▄
__█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▒▒▒▒█▒▒▒▒▒▒▒▒▒▒▒▒███▒▒▒▒▒▒▒▒▒▒▒▒▒█▄
__█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▒▒▒▒█▒▒▒▒▒▒▒▒▒▒▒█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█
__█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▒▒▒▒█▒▒▒▒▒▒▒▒▒██▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█
___█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▒▒▒▒█▒▒▒▒▒▒▒▒█▒▒▒▒▒▒▒▒▒▒▒▒▒░░░░▒▒▒▒▒▒▌
____█▒▒▒▒▒▒▒▒▒▒▒▒▒██▒▒▒▒▒▒▒█▒▒▒▒█▒▒▒▒▒▒█▒▒▒▒▒▒▒▒▒░░░░░░░░░░░░░▒▒▌
____█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█████████████▒▒▒▒▒█▒▒▒▒▒▒▒▒░░░░▒▒▒▒▒▒▒▒▒▒▒░▒▌
_____█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█_______▐▒▒▒▒█▒▒▒▒▒▒▒░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒░▌
______█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█________█▒▒█▒▒▒▒▒▒░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▌
_______█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█________█▒█▒▒▒▒▒▒░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▌
________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█________█▒▒▒▒▒▒░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█
_________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█________█▒▒▒▒░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█
_________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█________█▒▒▒░░░░░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▀
__________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█_______█▒░░░▒▒▒▒▒░░░░░░░░▒▒▒█▀▀▀
___________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█_______█░▒▒▒▒▒▒▒▒▒▒▒▒▒░░░░█▀
____________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█_______█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▀
_____________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█_______█▒▒▒▒▒▒▒▒▒▒▒▒█▀
_____________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█_______▀▀▀███████▀▀
______________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█
_______________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█
________________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█
_________________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█
__________________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒██▒█
___________________█▒▒▒▒▒▒▒▒▒▒▒▒▒██▒▒▒▒█
___________________█▒▒▒▒▒▒▒▒████▒▒▒▒▒▒▒█
___________________█████████▒▒▒▒▒▒▒▒▒▒▒█
____________________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█
____________________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█
_____________________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒░▌
_____________________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒░▌
______________________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒░░▌
_______________________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒░░█
________________________█▒▒▒▒▒▒▒▒▒▒▒░░░█
__________________________██▒▒▒▒▒▒░░░█▀
_____________________________█░░░░░█▀
_______________________________▀▀▀▀


                                               :
                                              ::
                                             ::
                                             ::
                                            ::
                                            ::
                              __           ::
   _..-'/-¯¯--/_          ,.--. ''.     |`\=,..
-:--.---''-..  /_         |\\_\..  \    `-.=._/
.-|¯         '.  \         \_ \-`/\ |    ::`
  /  @  \      \  -_   _..--|-\¯¯``'--.-/_\
  |   .-'|      \  \\-'\_/     ¯/-.|-.\_\_/
  \_./` /        \_//-''/    .-'
       |           '-/'@====/              _.--.
   __.'             /¯¯'-. \-'.          _/   /¯'
.''____|   /       |'--\__\/-._        .'    |
 \ \_. \  |       _| -/        \-.__  /     /
  \___\ '/   _.  ('-..| /       '_  ''   _.'
        /  .'     ¯¯¯¯ /        | ``'--''
       (  / ¯```¯¯¯¯¯|-|        |
        \ \_.         \ \      /
         \___\         '.'.   /
                         /    |
                        /   .'
                       /  .' |
                     .'  / \  \
                    /___| /___'


*/

//TODO: LOOK AT THE COMMENTS ABOVE!!!!!!!!!!!
//TODO: FIND THE MISSING CURLY BRACE. GOOD LUCK!!!
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

    public Move blocker(Board board, char opponentLetter) {
        BoardGrader boardGrader=null;
        Move tempMove=null;
        Location tempLocation=null;
        for (int z = 0; z < board.getBoard().length; z++) {
            for (int y = 0; y < board.getBoard()[0].length; y++) {
                for (int x = 0; x < board.getBoard()[0][0].length; x++) {
                    boardGrader =  new BoardGrader(board,new Location(x,y,z),opponentLetter,0,0);//TODO 2/6/19: USING BOARDGRADER METHODS, CHECK IF OPPONENT IS AT 5 IN ANY DIRECTION, THEN BLOCK HIM.
                    //TODO: There has to be a shortcut somewhere here. We need to find it.
                    
                    //TODO: I changed the requirements to trigger returning to >= 3, that way we wont be stuck and lose at 5 


                    /*score = checkXP(new Location(x, y, z), letter, 0, 0) + checkYP(new Location(x, y, z), letter, 0) + checkZP(new Location(x, y, z), letter, 0) +
                    checkXM(new Location(x, y, z), letter, 0, 0) +  checkYM(new Location(x, y, z), letter, 0) + checkZM(new Location(x, y, z), letter, 0) +
                     checkYPZP(new Location(x, y, z), letter, 0) +checkYPZM(new Location(x, y, z), letter, 0) + checkZPXP(new Location(x, y, z), letter, 0) + checkZPXM(new Location(x, y, z), letter, 0) +
                    checkYPXP(new Location(x, y, z), letter, 0) + checkYMXP(new Location(x, y, z), letter, 0) + checkYMZP(new Location(x, y, z), letter, 0) +
                    checkYMZM(new Location(x, y, z), letter, 0)+ checkYMZPXP(new Location(x, y, z), letter, 0) + checkYPZMXP(new Location(x, y, z), letter, 0)
                    + checkYPZPXM(new Location(x, y, z), letter, 0) + checkYPZPXP(new Location(x, y, z), letter, 0);*/


                    if(board.getBoard()[z][y][x] == opponentLetter) {
                        if (x + 6 < X_SIZE) {
                            if (boardGrader.checkXP(new Location(x, y, z), opponentLetter, 0) >= 3 && board.getBoard()[z][y][x + 6] != letter/*TODO: THIS MAKES SURE THAT WE HAVEN'T ALREADY BLOCKED IT*/) {
                                return new Move(x + 6, z);
                            }
                        }
                        if (y + 6 < Y_SIZE) {
                            if (boardGrader.checkYP(new Location(x, y, z), opponentLetter, 0) >= 3 && board.getBoard()[z][y + 6][x] != letter) {
                                return new Move(x, z);
                            }
                        }
                        if (z + 6 < Z_SIZE) {
                            if (boardGrader.checkZP(new Location(x, y, z), opponentLetter, 0) >= 3 && board.getBoard()[z+6][y][x] != letter/*TODO: THIS MAKES SURE THAT WE HAVEN'T ALREADY BLOCKED IT*/) {
                                return new Move(x, z+6);
                            }
                        }
                        if (x - 6 > 0) {
                            if (boardGrader.checkXM(new Location(x, y, z), opponentLetter, 0) >= 3 && board.getBoard()[z][y][x - 6] != letter/*TODO: THIS MAKES SURE THAT WE HAVEN'T ALREADY BLOCKED IT*/) {
                                return new Move(x - 6, z);
                            }
                        }
                        if (y - 6 > 0) {
                            if (boardGrader.checkYM(new Location(x, y, z), opponentLetter, 0) >= 3 && board.getBoard()[z][y-6][x] != letter/*TODO: THIS MAKES SURE THAT WE HAVEN'T ALREADY BLOCKED IT*/) {
                                return new Move(x, z);
                            }
                        }

                        if (z - 6 > 0) {
                            if (boardGrader.checkZM(new Location(x, y, z), opponentLetter, 0) >= 3 && board.getBoard()[z-6][y][x] != letter/*TODO: THIS MAKES SURE THAT WE HAVEN'T ALREADY BLOCKED IT*/) {
                                return new Move(x, z-6);
                            }
                        }

                        if(y+6 < Y_SIZE&&z+6 < Z_SIZE) {
                            if (boardGrader.checkYPZP(new Location(x, y, z), opponentLetter, 0) >= 3 && board.getBoard()[z+6][y+6][x] != letter/*TODO: THIS MAKES SURE THAT WE HAVEN'T ALREADY BLOCKED IT*/) {
                                return new Move(x, z+6);
                            }
                        }

                        if(y+6 < Y_SIZE&&z-6>0) {
                            if (boardGrader.checkYPZP(new Location(x, y, z), opponentLetter, 0) >= 3 && board.getBoard()[z-6][y+6][x] != letter/*TODO: THIS MAKES SURE THAT WE HAVEN'T ALREADY BLOCKED IT*/) {
                                return new Move(x, z-6);
                            }
                        }
                        
                        //ZPXP
                        if(z+6 < Z_SIZE&&x+6<X_SIZE) {
                            if (boardGrader.checkZPXP(new Location(x, y, z), opponentLetter, 0) >= 3 && board.getBoard()[z+6][y][x+6] != letter/*TODO: THIS MAKES SURE THAT WE HAVEN'T ALREADY BLOCKED IT*/) {
                                return new Move(x+6, z+6);
                            }
                        }


                        if(z+6 < Z_SIZE&&x+6<X_SIZE) {
                            if (boardGrader.checkZPXP(new Location(x, y, z), opponentLetter, 0) >= 3 && board.getBoard()[z+6][y][x+6] != letter/*TODO: THIS MAKES SURE THAT WE HAVEN'T ALREADY BLOCKED IT*/) {
                                return new Move(x+6, z+6);
                            }
                        }


                    }
                }
            }
        }
        return null;//TODO: If the blocker method returns a null, we know that it is not necessary to block. If not, we take the move that blocker returns and use it to block the opposing AI's move
    }
}
