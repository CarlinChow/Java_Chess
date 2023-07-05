package chess.domain.pieces;

import chess.domain.*;
import static java.lang.Math.abs;

public class Bishop extends Piece{
    public Bishop(boolean white){
        super(white);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if(start.equals(end)){
            return false;
        }
        int horizontalMovement = start.getRow() - end.getRow();
        int verticalMovement = start.getColumn() - end.getColumn();
        if(abs(horizontalMovement) != abs(verticalMovement)){
            return false;
        }
        int startRow = start.getRow();
        int startColumn = start.getColumn();
        int endRow = end.getRow();
        int endColumn = end.getColumn();
        if(horizontalMovement < 0){
            if(verticalMovement < 0){
                // diagonal movement down & right
                for(int i = startRow + 1, j = startColumn + 1; i < endRow && j < endColumn; i++, j++){
                    if(!board.getSpotAt(i, j).isEmpty()){
                        return false;
                    }
                }
                return end.getPiece().isWhite() != this.isWhite();
            }else{
                // diagonal movement up & right
                for(int i = startRow - 1, j = startColumn + 1; i > endRow && j < endColumn; i--, j++){
                    if(!board.getSpotAt(i, j).isEmpty()){
                        return false;
                    }
                }
                return end.getPiece().isWhite() != this.isWhite();
            }
        }else{
            if(verticalMovement < 0){
                // diagonal movement down & left
                for(int i = startRow + 1, j = startColumn - 1; i < endRow && j > endColumn; i++, j--){
                    if(!board.getSpotAt(i, j).isEmpty()){
                        return false;
                    }
                }
                return end.getPiece().isWhite() != this.isWhite();
            }else{
                // diagonal movement up & left
                for(int i = startRow - 1, j = startColumn - 1; i > endRow && j > endColumn; i--, j--){
                    if(!board.getSpotAt(i, j).isEmpty()){
                        return false;
                    }
                }
                return end.getPiece().isWhite() != this.isWhite();
            }
        }
    }

    @Override
    public String toString(){
        return "B";
    }
}
