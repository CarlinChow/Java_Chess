package chess.domain;
public abstract class Piece {
    private Spot spot;
    private boolean white;
    private boolean captured;

    public Piece(boolean white){
        this.white = white;
    }

    public abstract boolean canMove(Board board, Spot start, Spot end);

    public boolean isWhite(){
        return this.white;
    }

    public boolean isCaptured(){
        return this.captured;
    }

    public void setCaptured(boolean captured){
        this.captured = captured;
    }

    public void setSpot(Spot spot){
        this.spot = spot;
    }

    public Spot getSpot(){
        return this.spot;
    }

    @Override
    public abstract String toString();
}
