package chess.domain.pieces;

import chess.domain.*;
import java.lang.Math;
import java.util.List;

public class King extends Piece{
    private boolean isCastlingDone;
    private boolean hasMoved;

    public King(boolean white){
        super(white);
        this.isCastlingDone = false;
        this.hasMoved = false;
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

    public boolean canMove(Board board, Spot start, Spot end){
        if(start.equals(end)){ return false; }
        int verticalMovement = start.getRow() - end.getRow();
        int horizontalMovement = start.getColumn() - end.getColumn();
        if(Math.abs(horizontalMovement) < 2 && Math.abs(verticalMovement) < 2){
            return !this.isChecked(board, end) && (end.isEmpty() || end.getPiece().isWhite() != this.isWhite());
        }
        return false;
    }

    public boolean isCurrentlyInCheck(Board board){ // check if current position is in check
        return this.isChecked(board, this.getSpot());
    }

    private boolean isChecked(Board board, Spot end){ // check if end spot puts king in check
        Piece capturedPiece = null;
        if(!end.isEmpty()){ // special case: king performs a capture move
            capturedPiece = end.removePiece();
            capturedPiece.setCaptured(true);
        }
        List<Piece> activeOpponentPieces = board
                .getAllPieces()
                .stream()
                .filter(piece -> this.isWhite() != piece.isWhite() && !piece.isCaptured())
                .toList();
        for(Piece piece:activeOpponentPieces){
            if(piece.canCapture(board, end)){
                if(capturedPiece != null){ // reverting board back to original
                    capturedPiece.setCaptured(false);
                    end.setPiece(capturedPiece);
                }
                return true;
            }
        }
        if(capturedPiece != null){  // reverting board back to original
            capturedPiece.setCaptured(false);
            end.setPiece(capturedPiece);
        }
        return false;
    }

    public boolean canCastle(Board board, Spot end){ // work on later when moveList object is created, movelist can be a parameter
        if(this.isCastlingDone || this.hasMoved){
            return false;
        }else if(this.isCurrentlyInCheck(board)){
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

    @Override
    public String toString(){
        return isWhite() ? "\u2654" : "\u265A";
    }
}
