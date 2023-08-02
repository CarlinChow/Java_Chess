package chess.domain.pieces;

import chess.domain.*;
import static java.lang.Math.abs;
import java.util.HashSet;
import java.util.Set;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Knight extends Piece{
    public Knight(boolean white){
        super(white);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end){
        int verticalMovement = start.getRow() - end.getRow();
        int horizontalMovement = start.getColumn() - end.getColumn();
        if(abs(verticalMovement * horizontalMovement) == 2){
            return end.isEmpty() || end.getPiece().isWhite() != this.isWhite();
        }
        return false;
    }

    @Override
    public Set<Spot> getMoves(Board board){
        Set<Spot> moves = new HashSet<>();
        Spot currSpot = this.getSpot();
        int currRow = currSpot.getRow();
        int currCol = currSpot.getColumn();
        // construct array of indices for all possible moves, 8 total in [row,col] format
        List<int[]> moveIndices = new LinkedList<>();
        moveIndices.add(new int[]{currRow + 2, currCol - 1});
        moveIndices.add(new int[]{currRow + 1, currCol - 2});
        moveIndices.add(new int[]{currRow - 1, currCol - 2});
        moveIndices.add(new int[]{currRow - 2, currCol - 1});
        moveIndices.add(new int[]{currRow - 2, currCol + 1});
        moveIndices.add(new int[]{currRow - 1, currCol + 2});
        moveIndices.add(new int[]{currRow + 1, currCol + 2});
        moveIndices.add(new int[]{currRow + 2, currCol + 1});
        // filter out illegal moves
        moveIndices = moveIndices
                        .stream()
                        .filter(arr -> arr[0] >= 0 && arr[0] < 8 && arr[1] >= 0 && arr[1] < 8)
                        .collect(Collectors.toList());
        for(int[] index : moveIndices){
            Spot moveSpot = board.getSpotAt(index[0], index[1]);
            if(this.canMove(board, moveSpot)){
                moves.add(moveSpot);
            }
        }
        return moves;
    }

    @Override
    public String toString(){
        return isWhite() ? "\u2658" : "\u265E";
    }
}
