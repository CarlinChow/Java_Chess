package chess.domain;
import java.util.UUID;
public abstract class Piece {
    private final String id;
    private Spot spot;
    private final boolean white;
    private boolean captured;

    public Piece(boolean white){
        this.white = white;
        this.id = UUID.randomUUID().toString();
        this.captured = false;
    }

    public abstract boolean canMove(Board board, Spot start, Spot end);

    public boolean canMove(Board board, Spot end){
        return this.canMove(board, this.spot, end);
    }

    public boolean canMove(Board board, String chessCoordinates){
        return this.canMove(board, board.getSpotAt(chessCoordinates));
    }

    public boolean canCapture(Board board, Spot end){
        return this.canMove(board, end);
    }

    public boolean canCapture(Board board, String chessCoordinates){
        return this.canCapture(board, board.getSpotAt(chessCoordinates));
    }

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

    public String getId(){
        return this.id;
    }

    @Override
    public abstract String toString();

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj instanceof Piece piece){
            return UUID.fromString(this.id).equals(UUID.fromString(piece.getId()));
        }
        return false;
    }
}
