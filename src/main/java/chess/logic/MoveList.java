package chess.logic;

import java.util.List;
import java.util.LinkedList;
import chess.domain.*;

public class MoveList {
    private final List<Move> moves;

    public MoveList(){
        this.moves = new LinkedList<>();
    }

    public List<Move> getAllMoves(){
        return this.moves;
    }

    public void addMove(Move move){
        this.moves.add(move);
    }

    public boolean hasMoved(Piece piece){
        for(Move move: this.moves){
            if(move.getPiece().equals(piece)){
                return true;
            }
        }
        return false;
    }
}
