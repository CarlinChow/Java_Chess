package chess.domain.pieces;

import chess.domain.*;
import chess.types.Color;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Rook extends Piece {
    public Rook(Color color){
        super(color);
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
            return end.isEmpty() || end.getPiece().getColor() != this.getColor();
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
            return end.isEmpty() || end.getPiece().getColor() != this.getColor();
        }
        return false;
    }

    @Override
    public Set<Spot> getMoves(Board board){
        Set<Spot> moves = new HashSet<>();
        Spot currSpot = this.getSpot();
        int currRow = currSpot.getRow();
        int currCol = currSpot.getColumn();
        // down
        for(int i = currRow + 1; i <= 7; i++){
            moves.add(board.getSpotAt(i, currCol));
        }
        // up
        for(int i = currRow - 1; i >= 0; i--){
            moves.add(board.getSpotAt(i, currCol));
        }
        // right
        for(int j = currCol + 1; j <= 7; j++){
            moves.add(board.getSpotAt(currRow, j));
        }
        // left
        for(int j = currCol - 1; j >= 0; j--){
            moves.add(board.getSpotAt(currRow, j));
        }
        return moves
                .stream()
                .filter(move -> this.canMove(board, move))
                .collect(Collectors.toSet());
    }

    @Override
    public String toString(){
        return this.getColor() == Color.WHITE ? "\u2656" : "\u265C";
    }
}

