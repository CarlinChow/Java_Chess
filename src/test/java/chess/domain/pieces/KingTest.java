package chess.domain.pieces;

import org.junit.Test;
import org.junit.Assert.*;
import chess.domain.*;


public class KingTest {
    @Test
    public void TestLegalMoves(){
        Board board = new Board();
        Piece king = new King(false);
        board.getSpotAt("e8").setPiece(king);
        if(king instanceof King k){
            System.out.println(k.canCastle(board, board.getSpotAt("g8")));
        }
    }
}
