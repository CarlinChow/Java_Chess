package chess.domain.pieces;

import chess.domain.*;

public class Queen extends Piece{
    public Queen(boolean white){
        super(white);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        Piece bishop = new Bishop(this.isWhite());
        Piece rook = new Rook(this.isWhite());
        return bishop.canMove(board, start, end) || rook.canMove(board, start, end);
    }
    public String toString(){
        return isWhite() ? "\u2655" : "\u265B";
    }
}
