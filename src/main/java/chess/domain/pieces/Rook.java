package chess.domain.pieces;

import chess.domain.Piece;
import chess.domain.Board;
import chess.domain.Spot;
import java.util.HashSet;
import java.util.Set;

public class Rook extends Piece {
    public Rook(boolean white){
        super(white);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if(start.equals(end)){ return false; }
        int verticalMovement = start.getRow() - end.getRow();
        int horizontalMovement = start.getColumn() - end.getColumn();
        if (start.equals(end)) {
            return false;
        }
        if (verticalMovement == 0) {
            // check for obstacles on path of movement
            int startColumn = start.getColumn();
            int endColumn = end.getColumn();
            int row = start.getRow();
            if (startColumn < endColumn) {
                for (int j = startColumn + 1; j < endColumn; j++) {
                    if (!board.getSpotAt(row, j).isEmpty()) {
                        return false;
                    }
                }
            } else {
                for (int j = startColumn - 1; j > endColumn; j--) {
                    if (!board.getSpotAt(row, j).isEmpty()) {
                        return false;
                    }
                }
            }
            return end.isEmpty() || end.getPiece().isWhite() != this.isWhite();
        }
        if (horizontalMovement == 0) {
            int startRow = start.getRow();
            int endRow = end.getRow();
            int column = start.getColumn();
            if (startRow < endRow) {
                for (int i = startRow + 1; i < endRow; i++) {
                    if (!board.getSpotAt(i, column).isEmpty()) {
                        return false;
                    }
                }
            } else {
                for (int i = startRow - 1; i > endRow; i--) {
                    if (!board.getSpotAt(i, column).isEmpty()) {
                        return false;
                    }
                }
            }
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
        return isWhite() ? "\u2656" : "\u265C";
    }
}

