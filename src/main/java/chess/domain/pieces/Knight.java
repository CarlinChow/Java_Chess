package chess.domain.pieces;

import chess.domain.*;
import java.lang.Math;

public class Knight extends Piece{
    public Knight(boolean white){
        super(white);
    }

    public boolean canMove(Board board, Spot start, Spot end){
        int verticalMovement = start.getRow() - end.getRow();
        int horizontalMovement = start.getColumn() - end.getColumn();
        if(Math.abs(verticalMovement * horizontalMovement) == 2){
            return end.isEmpty() || end.getPiece().isWhite() != this.isWhite();
        }
        return false;
    }

    @Override
    public String toString(){
        return isWhite() ? "\u2658" : "\u265E";
    }
}
