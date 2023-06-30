package chess.domain;
public abstract class Piece {
    private boolean white;
    private boolean captured;

    public Piece(boolean white){
        this.white = white;
    }

    public abstract boolean canMove(Board board, Spot start, Spot end); // may need to double-check this

    public boolean isWhite(){
        return this.white;
    }

    public boolean isCaptured(){
        return this.captured;
    }

    public void setCaptured(boolean captured){
        this.captured = captured;
    }

    @Override
    public abstract String toString();
}
