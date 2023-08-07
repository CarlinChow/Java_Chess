package chess.domain.pieces;

import chess.domain.*;
import static java.lang.Math.abs;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;
import chess.types.Color;

public class Bishop extends Piece{
    public Bishop(Color color){
        super(color);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if(start.equals(end)){ return false; }
        int horizontalMovement = start.getColumn() - end.getColumn();
        int verticalMovement = start.getRow() - end.getRow();
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
            }else{
                // diagonal movement up & right
                for(int i = startRow - 1, j = startColumn + 1; i > endRow && j < endColumn; i--, j++){
                    if(!board.getSpotAt(i, j).isEmpty()){
                        return false;
                    }
                }
            }
            return end.isEmpty() || end.getPiece().getColor() != this.getColor();
        }else{
            if(verticalMovement < 0){
                // diagonal movement down & left
                for(int i = startRow + 1, j = startColumn - 1; i < endRow && j > endColumn; i++, j--){
                    if(!board.getSpotAt(i, j).isEmpty()){
                        return false;
                    }
                }
            }else{
                // diagonal movement up & left
                for(int i = startRow - 1, j = startColumn - 1; i > endRow && j > endColumn; i--, j--){
                    if(!board.getSpotAt(i, j).isEmpty()){
                        return false;
                    }
                }
            }
            return end.isEmpty() || end.getPiece().getColor() != this.getColor();
        }
    }

    @Override
    public Set<Spot> getMoves(Board board){
        Set<Spot> moves = new HashSet<>();
        Spot currSpot = this.getSpot();
        int currRow = currSpot.getRow();
        int currColumn = currSpot.getColumn();
        // movement down and right
        for(int i = currRow + 1, j = currColumn + 1; i <= 7 && j <= 7; i++, j++){
            moves.add(board.getSpotAt(i, j));
        }
        // movement up and right
        for(int i = currRow - 1, j = currColumn + 1; i >= 0 && j <= 7; i--, j++){
            moves.add(board.getSpotAt(i, j));
        }
        // movement down and left
        for(int i = currRow + 1, j = currColumn - 1; i <= 7 && j >= 0; i++, j--){
            moves.add(board.getSpotAt(i, j));
        }
        // movement up and left
        for(int i = currRow - 1, j = currColumn - 1; i >= 0 && j >= 0; i--, j--){
            moves.add(board.getSpotAt(i, j));
        }
        return moves
                .stream()
                .filter(spot -> this.canMove(board, spot))
                .collect(Collectors.toSet());
    }

    @Override
    public String toString(){
        return this.getColor() == Color.WHITE ? "\u2657" : "\u265D";
    }
}
