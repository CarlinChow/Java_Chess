package chess.domain.pieces;

import chess.domain.*;
import java.lang.Math;
import java.util.List;
import chess.logic.*;

public class King extends Piece{
    private boolean isCastlingDone;

    public King(boolean white){
        super(white);
        this.isCastlingDone = false;
    }

    public boolean getIsCastlingDone(){
        return this.isCastlingDone;
    }

    public void setIsCastlingDone(boolean done){
        this.isCastlingDone = done;
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
                .getAllActivePieces()
                .stream()
                .filter(piece -> this.isWhite() != piece.isWhite())
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

    public boolean canCastle(Board board, Spot end, MoveList moveList){
        if(this.isCastlingDone || moveList.hasPieceMoved(this) || this.isCurrentlyInCheck(board)){
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
                int currentRow = this.getSpot().getRow();
                if(rookSpot.getColumn() == 0){
                    for(int j = 1; j <= 3; j++){
                        if(!board.getSpotAt(currentRow, j).isEmpty()){
                            return false;
                        }
                    }
                }
                else{
                    for(int j = 5; j <= 6; j++){
                        if(!board.getSpotAt(currentRow, j).isEmpty()){
                            return false;
                        }
                    }
                }
                return moveList.hasPieceMoved(rook);
            }
        }
        return false;
    }

    @Override
    public String toString(){
        return isWhite() ? "\u2654" : "\u265A";
    }
}