public class BOB {
private char letter;
private String name;
private Move bestMove = null, worstMove = null;
public static final char EMPTY = '-', RED = 'R', BLUE = 'B', PLAYING = '-', TIE = 'T';
public static final int X_SIZE = 8, Y_SIZE = 7, Z_SIZE = 8;
char[][][] board;
String score = "";

@Override
public Move getMove(Board board) {
return null;
}

@Override
public Player freshCopy() {
return null;
}

public BOB(char letter) {
super("Craig", letter);
}
public Move getMove(Board board) {
this.board = board.getBoard();
int lastScore = 0;
char opponentLetter = ' ';
Location location = null;
bestMove = new Move((int) Math.random() * 7, (int) Math.random() * 7);
//Move lastMove = null;
for(int z = 0; z < board.getBoard().length; z++) {
for (int x = 0; x < board.getBoard()[0][0].length; x++) {

if (!(board.isFull(new Move(x, z)))) {
location = board.makeMove(new Move(x, z), letter);//drops piece into board for grading later-VK
}
if (board.getBoard()[location.z][location.y][location.x] != '-' && board.getBoard()[location.z][location.y][location.x] != letter) {
opponentLetter = board.getBoard()[location.z][location.y][location.x];
}
BoardGrader boardGrader = new BoardGrader(board, location, letter, 0, 0);
System.out.println(x + ", " + z + ":" + boardGrader.boardScorer(board, letter));
if (boardGrader.boardScorer(board, letter) > lastScore) {//checks to see if this next move is better than our last. If so, it becomes bestMove, and lastScore equals boardScorer(board)-VK
//lastMove = new Move(x, z)
if (!board.isFull(new Move(x, z))) {
bestMove = new Move(location.x, location.z);
}
}

lastScore = boardGrader.boardScorer(board, letter);

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

public void blocker (Board board){
for (int z = 0; z < board.getBoard().length; z++) {
for (int y = 0; y < board.getBoard()[0].length; y++) {
for (int x = 0; x < board.getBoard()[0][0].length; x++) {
//mnmj bnhafds bfd gfds lkj gfd gfdj gfdlkjn getScore()
}

}

}
}
}
}
