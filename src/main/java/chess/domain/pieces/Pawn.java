package chess.domain.pieces;

import chess.domain.Piece;
import chess.domain.Board;
import chess.domain.Spot;
import java.lang.Math;

public class Pawn extends Piece{

    public Pawn(boolean white){
        super(white);
    }

    public boolean canMove(Board board, Spot start, Spot end){
        int verticalMovement = start.getRow() - end.getRow();
        int horizontalMovement = start.getColumn() - end.getColumn();
        if (Math.abs(verticalMovement + horizontalMovement) > 2 ||
                Math.abs(horizontalMovement) > 1 ||
                verticalMovement == 0)
        {
            // check if piece is making an illegal move,
            return false;
        }
        if((this.isWhite() && verticalMovement < 0) || (!this.isWhite() && verticalMovement > 0)){
            // check if piece is moving backwards
            return false;
        }

        int startingRow = this.isWhite() ? 6 : 1;
        if(Math.abs(verticalMovement) == 2 && start.getRow() == startingRow){
            // handle moving forward twice
            int diff = this.isWhite() ? -1 : 1;
            // check if spots in front are empty
            Spot spot = board.getSpotAt(start.getRow() + diff, start.getColumn());
            return spot.isEmpty() && end.isEmpty();
        }
        if(horizontalMovement == 0 && Math.abs(verticalMovement) == 1){
            return end.isEmpty();
        }
        // check if attack is legal
        if(Math.abs(verticalMovement) == 1 && Math.abs(horizontalMovement) == 1){
            return !end.isEmpty() && end.getPiece().isWhite() != this.isWhite();
        }
        return false;
    }

    @Override
    public String toString(){
        return "P";
    }

}
