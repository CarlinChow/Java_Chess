package chess.domain.pieces;

import chess.domain.*;
import java.lang.Math;
import java.util.HashSet;
import java.util.Set;

public class Knight extends Piece{
    public Knight(boolean white){
        super(white);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end){
        int verticalMovement = start.getRow() - end.getRow();
        int horizontalMovement = start.getColumn() - end.getColumn();
        if(Math.abs(verticalMovement * horizontalMovement) == 2){
            return end.isEmpty() || end.getPiece().isWhite() != this.isWhite();
        }
        return false;
    }

    @Override
    public Set<Spot> getMoves(Board board){
        Set<Spot> moves = new HashSet<>();
        return moves;
    }

    @Override
    public String toString(){
        return isWhite() ? "\u2658" : "\u265E";
    }
}
