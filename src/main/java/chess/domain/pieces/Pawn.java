package chess.domain.pieces;

import chess.domain.*;
import static java.lang.Math.abs;
import java.util.Set;
import java.util.HashSet;

public class Pawn extends Piece{

    public Pawn(boolean white){
        super(white);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end){
        if(start.equals(end)){ return false; }
        int verticalMovement = start.getRow() - end.getRow();
        int horizontalMovement = start.getColumn() - end.getColumn();
        if (abs(verticalMovement + horizontalMovement) > 2 ||
                abs(horizontalMovement) > 1 ||
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
        if(abs(verticalMovement) == 2 && start.getRow() == startingRow){
            // handle moving forward twice
            int diff = this.isWhite() ? -1 : 1;
            // check if spots in front are empty
            Spot spot = board.getSpotAt(start.getRow() + diff, start.getColumn());
            return spot.isEmpty() && end.isEmpty();
        }
        if(horizontalMovement == 0 && abs(verticalMovement) == 1){
            return end.isEmpty();
        }
        // check if attack is legal
        if(abs(verticalMovement) == 1 && abs(horizontalMovement) == 1){
            return !end.isEmpty() && end.getPiece().isWhite() != this.isWhite();
        }
        return false;
    }

    @Override
    public boolean canCapture(Board board, Spot start, Spot end){
        int verticalMovement = start.getRow() - end.getRow();
        int horizontalMovement = start.getColumn() - end.getColumn();
        int direction = this.isWhite() ? 1 : -1;
        if(abs(horizontalMovement) == 1 && verticalMovement == direction){
            return !end.isEmpty() && end.getPiece().isWhite() != this.isWhite();
        }
        return false;
    }

    @Override
    public Set<Spot> getMoves(Board board){
        Set<Spot> moves = new HashSet<>();
        Spot currSpot = this.getSpot();
        int forwardDirection = this.isWhite() ? -1 : 1;
        // move forward
        Spot forwardSpot = board.getSpotAt(currSpot.getRow() + forwardDirection, currSpot.getColumn());
        if(this.canMove(board, forwardSpot)){
            moves.add(forwardSpot);
        }
        // capture left
        if(currSpot.getColumn() > 0){
            Spot captureLeftSpot = board.getSpotAt(currSpot.getRow() + forwardDirection, currSpot.getColumn() - 1);
            if(!captureLeftSpot.isEmpty() && captureLeftSpot.getPiece().isWhite() != this.isWhite()){
                moves.add(captureLeftSpot);
            }
        }
        // capture right
        if(currSpot.getColumn() < 7){
            Spot captureRightSpot = board.getSpotAt(currSpot.getRow() + forwardDirection, currSpot.getColumn() + 1);
            if(!captureRightSpot.isEmpty() && captureRightSpot.getPiece().isWhite() != this.isWhite()){
                moves.add(captureRightSpot);
            }
        }
        return moves;
    }

    @Override
    public String toString(){
        return isWhite() ? "\u2659" : "\u265F";
    }
}
