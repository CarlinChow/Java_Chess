package chess.domain.pieces;

import chess.domain.*;
import chess.logic.*;
import chess.types.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;
import static java.lang.Math.abs;


public class King extends Piece{
    private boolean isCastlingDone;

    public King(Color color){
        super(color);
        this.isCastlingDone = false;
    }

    public boolean getIsCastlingDone(){
        return this.isCastlingDone;
    }

    public void setIsCastlingDone(boolean done){
        this.isCastlingDone = done;
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end){
        if(start.equals(end)){ return false; }
        int verticalMovement = start.getRow() - end.getRow();
        int horizontalMovement = start.getColumn() - end.getColumn();
        if(abs(horizontalMovement) <= 1 && abs(verticalMovement) <= 1){
            return !this.isChecked(board, end) && (end.isEmpty() || end.getPiece().getColor()!= this.getColor());
        }
        return false;
    }

    public boolean isCurrentlyInCheck(Board board){ // check if current position is in check
        return this.isChecked(board, this.getSpot());
    }

    private boolean isChecked(Board board, Spot end){ // check if end spot puts king in check
        Piece capturedPiece = null;
        Spot start = this.getSpot();
        if(!end.isEmpty()){ // special case: king performs a capture move
            capturedPiece = end.removePiece();
            capturedPiece.setCaptured(true);
        }
        start.removePiece(); // change board to the state when move is complete
        end.setPiece(this);
        Set<Piece> activeOpponentPieces = board
                .getAllActivePieces()
                .stream()
                .filter(piece -> this.getColor() != piece.getColor())
                .collect(Collectors.toSet());
        for(Piece piece:activeOpponentPieces){
            if(piece.canCapture(board, end)){
                // reverting board back to original
                end.removePiece();
                start.setPiece(this);
                if(capturedPiece != null){
                    capturedPiece.setCaptured(false);
                    end.setPiece(capturedPiece);
                }
                return true;
            }
        }
        // reverting board back to original
        end.removePiece();
        start.setPiece(this);
        if(capturedPiece != null){
            capturedPiece.setCaptured(false);
            end.setPiece(capturedPiece);
        }
        return false;
    }

    public boolean canCastle(Board board, Spot end, MoveList moveList){
        if(this.isCastlingDone || moveList.hasPieceMoved(this) || this.isCurrentlyInCheck(board)){
            return false;
        }
        int verticalMovement = this.getSpot().getRow() - end.getRow();
        int horizontalMovement = this.getSpot().getColumn() - end.getColumn();
        if(abs(horizontalMovement) == 2 & verticalMovement == 0){
            Spot rookSpot;
            if(horizontalMovement > 0) { // castle left
                rookSpot = board.getSpotAt(this.getSpot().getRow(), 0);
            }else{ // castle right
                rookSpot = board.getSpotAt(this.getSpot().getRow(), 7);
            }
            if(!rookSpot.isEmpty() && rookSpot.getPiece() instanceof Rook rook){
                int currentRow = this.getSpot().getRow();
                Spot newRookSpot;
                if(moveList.hasPieceMoved(rook)){
                    return false;
                }
                if(rookSpot.getColumn() == 0){
                    // castle left
                    newRookSpot = board.getSpotAt(currentRow, 3);
                    for(int j = 1; j <= 3; j++){
                        if(!board.getSpotAt(currentRow, j).isEmpty()){
                            return false;
                        }
                    }
                }else{
                    // castle right
                    newRookSpot = board.getSpotAt(currentRow, 4);
                    for(int j = 5; j <= 6; j++){
                        if(!board.getSpotAt(currentRow, j).isEmpty()){
                            return false;
                        }
                    }
                }
                // check if castling puts king in check
                rookSpot.removePiece();
                Spot kingStartingSpot = this.getSpot();
                kingStartingSpot.removePiece();
                newRookSpot.setPiece(rook);
                end.setPiece(this);
                boolean rv = !this.isCurrentlyInCheck(board);
                // revert board back to original
                newRookSpot.removePiece();
                end.removePiece();
                rookSpot.setPiece(rook);
                kingStartingSpot.setPiece(this);
                return rv;
            }
        }
        return false;
    }

    public boolean canCastle(Board board, String chessCoordinates, MoveList moveList){
        return this.canCastle(board, board.getSpotAt(chessCoordinates), moveList);
    }

    @Override
    public Set<Spot> getMoves(Board board){
        Set<Spot> moves = new HashSet<>();
        Spot currSpot = this.getSpot();
        int currRow = currSpot.getRow();
        int currCol = currSpot.getColumn();
        // construct array of indices for all possible moves, 8 total in [row,col] format
        List<int[]> moveIndices = new LinkedList<>();
        moveIndices.add(new int[]{currRow, currCol - 1 });
        moveIndices.add(new int[]{currRow - 1, currCol - 1});
        moveIndices.add(new int[]{currRow - 1, currCol});
        moveIndices.add(new int[]{currRow - 1, currCol + 1});
        moveIndices.add(new int[]{currRow, currCol + 1});
        moveIndices.add(new int[]{currRow + 1, currCol + 1});
        moveIndices.add(new int[]{currRow + 1, currCol});
        moveIndices.add(new int[]{currRow + 1, currCol - 1});
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
        return this.getColor() == Color.WHITE ? "\u2654" : "\u265A";
    }
}
