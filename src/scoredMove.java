public class scoredMove extends Move implements Comparable {

    private int score;
    private scoredMove lastMove;

    public scoredMove(int x, int z, int score, scoredMove lastMove) {
        super(x, z);
        this.score = score;
        this.lastMove = lastMove;
    }
    @Override
    public int compareTo(Object o) {
        if(o instanceof scoredMove) {
            return score - ((scoredMove) o).score;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "(x=" + getX()+",z="+getZ()+ "): " + score;
    }

    public int getScore() {
        return score;
    }

    public scoredMove getLastMove() {
        return lastMove;
    }
}
