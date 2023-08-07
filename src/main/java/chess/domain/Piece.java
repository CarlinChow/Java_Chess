package chess.domain;

import java.util.UUID;
import java.util.Set;
import chess.types.Color;
public abstract class Piece {
    private final String id;
    private Spot spot;
    private final Color color;
    private boolean captured;

    public Piece(Color color){
        this.color = color;
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

    public boolean canCapture(Board board, Spot start, Spot end){
        return this.canMove(board, start, end);
    }

    public boolean canCapture(Board board, Spot end){
        return this.canCapture(board, this.spot, end);
    }

    public boolean canCapture(Board board, String chessCoordinates){
        return this.canCapture(board, board.getSpotAt(chessCoordinates));
    }

    public abstract Set<Spot> getMoves(Board board);

    public Color getColor(){
        return this.color;
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
        if(obj != null && obj.getClass() == getClass()){
            Piece piece = (Piece)obj;
            return UUID.fromString(this.id).equals(UUID.fromString(piece.getId()));
        }
        return false;
    }
}
