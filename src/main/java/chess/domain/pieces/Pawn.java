package chess.domain.pieces;

import chess.domain.*;
import chess.types.Color;
import static java.lang.Math.abs;
import java.util.Set;
import java.util.HashSet;

public class Pawn extends Piece{

    public Pawn(Color color){
        super(color);
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
        if((this.getColor() == Color.WHITE && verticalMovement < 0) || (this.getColor() == Color.BLACK && verticalMovement > 0)){
            // check if piece is moving backwards
            return false;
        }

        int startingRow = this.getColor() == Color.WHITE ? 6 : 1;
        if(abs(verticalMovement) == 2 && start.getRow() == startingRow){
            // handle moving forward twice
            int diff = this.getColor() == Color.WHITE ? -1 : 1;
            // check if spots in front are empty
            Spot spot = board.getSpotAt(start.getRow() + diff, start.getColumn());
            return spot.isEmpty() && end.isEmpty();
        }
        if(horizontalMovement == 0 && abs(verticalMovement) == 1){
            return end.isEmpty();
        }
        // check if attack is legal
        return canCapture(board, start, end);
    }

    @Override
    public boolean canCapture(Board board, Spot start, Spot end){
        int verticalMovement = start.getRow() - end.getRow();
        int horizontalMovement = start.getColumn() - end.getColumn();
        int forwardDirection = this.getColor() == Color.WHITE ? 1 : -1;
        if(abs(horizontalMovement) == 1 && verticalMovement == forwardDirection){
            return !end.isEmpty() && end.getPiece().getColor() != this.getColor();
        }
        return false;
    }

    public boolean canEnPassant(Board board, String chessCoordinates, Piece enPassantVulnerablePiece){
        if(enPassantVulnerablePiece == null){
            return false;
        }
        Spot start = this.getSpot();
        Spot end = board.getSpotAt(chessCoordinates);
        int verticalMovement = start.getRow() - end.getRow();
        int horizontalMovement = start.getColumn() - end.getColumn();
        int forwardDirection = this.getColor() == Color.WHITE ? 1 : -1;
        Spot enPassantSpot = board.getSpotAt(start.getRow(), end.getColumn());
        if(abs(horizontalMovement) == 1 && verticalMovement == forwardDirection && !enPassantSpot.isEmpty()){
            Piece capturePiece = enPassantSpot.getPiece();
            return enPassantVulnerablePiece.equals(capturePiece) &&
                   capturePiece.getColor() != this.getColor() &&
                   end.isEmpty() &&
                   capturePiece instanceof Pawn;
        }
        return false;
    }

    @Override
    public Set<Spot> getMoves(Board board){
        Set<Spot> moves = new HashSet<>();
        Spot currSpot = this.getSpot();
        int forwardDirection = this.getColor() == Color.WHITE ? -1 : 1;
        // move forward
        Spot forwardSpot = board.getSpotAt(currSpot.getRow() + forwardDirection, currSpot.getColumn());
        if(this.canMove(board, forwardSpot)){
            moves.add(forwardSpot);
        }
        // move forward two spots
        if((this.getColor() == Color.WHITE && currSpot.getRow() == 6) || (this.getColor() == Color.BLACK && currSpot.getRow() == 1)){
            Spot forwardTwoSpot = board.getSpotAt(currSpot.getRow() + forwardDirection * 2, currSpot.getColumn());
            if(this.canMove(board, forwardTwoSpot)){
                moves.add(forwardTwoSpot);
            }
        }
        // capture left
        if(currSpot.getColumn() > 0){
            Spot captureLeftSpot = board.getSpotAt(currSpot.getRow() + forwardDirection, currSpot.getColumn() - 1);
            if(!captureLeftSpot.isEmpty() && captureLeftSpot.getPiece().getColor() != this.getColor()){
                moves.add(captureLeftSpot);
            }
        }
        // capture right
        if(currSpot.getColumn() < 7){
            Spot captureRightSpot = board.getSpotAt(currSpot.getRow() + forwardDirection, currSpot.getColumn() + 1);
            if(!captureRightSpot.isEmpty() && captureRightSpot.getPiece().getColor() != this.getColor()){
                moves.add(captureRightSpot);
            }
        }
        return moves;
    }

    @Override
    public String toString(){
        return this.getColor() == Color.WHITE ? "\u2659" : "\u265F";
    }
}
