package chess.domain.pieces;

import chess.domain.*;
import java.lang.Math;

public class King extends Piece{
    private boolean isCastlingDone;
    private boolean hasMoved;

    public King(boolean white){
        super(white);
        this.isCastlingDone = false;
        this.hasMoved = false;
    }

    public boolean canMove(Board board, Spot start, Spot end){
        if(start.equals(end)){ return false; }
        int verticalMovement = start.getRow() - end.getRow();
        int horizontalMovement = start.getColumn() - end.getColumn();
        if(Math.abs(horizontalMovement) < 2 && Math.abs(verticalMovement) < 2){
            return !this.isChecked(board, end) && (end.isEmpty() || end.getPiece().isWhite() != this.isWhite());
        }
        return false;
    }

    public boolean getIsCastlingDone(){
        return this.isCastlingDone;
    }

    public void setIsCastlingDone(boolean done){
        this.isCastlingDone = done;
    }

    public boolean getHasMoved(){
        return this.hasMoved;
    }

    public void setHasMoved(boolean hasMoved){ this.hasMoved = hasMoved; }

    public boolean canCastle(Board board, Spot end){ // end spot must be two spots to the direction of castle
        if(this.isCastlingDone || this.hasMoved){
            return false;
        }else if(this.isChecked(board, this.getSpot())){ // check if king is currently in check
            return false;
        }
        int verticalMovement = this.getSpot().getRow() - end.getRow();
        int horizontalMovement = this.getSpot().getColumn() - end.getColumn();
        if(Math.abs(horizontalMovement) == 2 & verticalMovement == 0){
            Spot rookSpot;
            if(horizontalMovement > 0) { // castle left
                rookSpot = board.getSpotAt(this.getSpot().getRow(), 0);
            }else{ // castle right
                rookSpot = board.getSpotAt(this.getSpot().getRow(), 7);
            }
            if(!rookSpot.isEmpty() && rookSpot.getPiece() instanceof Rook rook){
                return !rook.getHasMoved();
            }
        }
        return false;
    }

    private boolean isChecked(Board board, Spot end){ // check if move puts king in a check
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                Spot spot = board.getSpotAt(i,j);
                if(spot.equals(this.getSpot())){
                    continue;
                } else if(!spot.isEmpty() && spot.getPiece().isWhite() != this.isWhite()){
                    Piece piece = spot.getPiece();
                    if(piece.canMove(board, this.getSpot())){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public String toString(){
        return "KI";
    }
}
