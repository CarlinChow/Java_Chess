package chess.logic;

import java.util.List;
import java.util.LinkedList;
import chess.domain.*;

public class MoveList {
    private final List<Move> moves;

    public MoveList(){
        this.moves = new LinkedList<Move>();
    }

    public List<Move> getAllMoves(){
        return this.moves;
    }

    public void add(Move move){
        this.moves.add(move);
    }

    public boolean hasPieceMoved(Piece piece){
        int timesMoved = this.moves
                            .stream()
                            .filter(move -> move.getPiece().equals(piece))
                            .toList()
                            .size();
        return timesMoved > 0;
    }

    @Override
    public String toString(){
        return this.moves.toString();
    }
}
