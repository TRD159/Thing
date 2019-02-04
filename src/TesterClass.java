public class TesterClass {
    public static void main(String args[]) {
        int x=0;
        TRD1123 Craig=new TRD1123('R');
        TRD1123 Greg=new TRD1123('B');

        Board board=new Board();

        do{
            board.makeMove(Craig.getMove(board),Craig.getLetter());
            board.makeMove(Greg.getMove(board),Greg.getLetter());
            System.out.println("Craig: "+Craig.getScore());
            System.out.println("Greg: "+Greg.getScore());
            x++;
        }while (Craig.getMove(board)!=null);
    }
}
