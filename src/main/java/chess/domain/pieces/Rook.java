package chess.domain.pieces;

import chess.domain.Piece;
import chess.domain.Board;
import chess.domain.Spot;
import java.lang.Math;

public class Rook extends Piece {
    private boolean onFirstMove;

    public Rook(boolean white){
        super(white);
    }

    public boolean canMove(Board board, Spot start, Spot end){
        int verticalMovement = start.getRow() - end.getRow();
        int horizontalMovement = start.getColumn() - end.getColumn();

        if(verticalMovement == 0){
            // check for obstacles on path of movement
            int leftEndpoint = Math.min(start.getColumn(), end.getColumn());
            int rightEndpoint = Math.max(start.getColumn(), end.getColumn());
            int row = start.getRow();
            for(int j = leftEndpoint; j < rightEndpoint; j++){
                if(!board.getSpot(row, j).isEmpty()){
                    return false;
                }
            }
            return true;
        }
        if(horizontalMovement == 0){
            // check for obstacles on path of movement
            int topEndpoint = Math.min(start.getRow(), end.getRow());
            int bottomEndpoint = Math.max(start.getRow(), end.getRow());
            int column = start.getColumn();
            for(int i = topEndpoint; i < bottomEndpoint; i++){
                if(!board.getSpot(column, i).isEmpty()){
                    return false;
                }
            }
            return true;
        }
        return false;
    }


    public boolean getOnFirstMove(){
        return this.onFirstMove;
    }
    public void setOnFirstMove(boolean onFirstMove){
        this.setOnFirstMove(onFirstMove);
    }

    public String toString(){
        return "R";
    }
}

