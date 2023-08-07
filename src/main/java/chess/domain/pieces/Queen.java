package chess.domain.pieces;

import chess.domain.*;
import chess.types.Color;
import java.util.HashSet;
import java.util.Set;

public class Queen extends Piece{
    public Queen(Color color){
        super(color);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        Piece bishop = new Bishop(this.getColor());
        Piece rook = new Rook(this.getColor());
        return bishop.canMove(board, start, end) || rook.canMove(board, start, end);
    }

    @Override
    public Set<Spot> getMoves(Board board){
        Set<Spot> moves = new HashSet<>();
        Piece bishop = new Bishop(this.getColor());
        Piece rook = new Rook(this.getColor());
        bishop.setSpot(this.getSpot());
        rook.setSpot(this.getSpot());
        moves.addAll(bishop.getMoves(board));
        moves.addAll(rook.getMoves(board));
        return moves;
    }

    @Override
    public String toString(){
        return this.getColor() == Color.WHITE ? "\u2655" : "\u265B";
    }
}
