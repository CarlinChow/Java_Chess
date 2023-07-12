package chess.domain.pieces;

import chess.domain.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class KnightTest {
    private void setUp(Board board){
        board.getSpotAt("b8").setPiece(new Knight(false));
        board.getSpotAt("g8").setPiece(new Knight(false));
        board.getSpotAt("b1").setPiece(new Knight(true));
        board.getSpotAt("g1").setPiece(new Knight(true));
    }

    @Test
    public void testLegalMove(){
        Board board = new Board();
        this.setUp(board);
        board.print();
    }
}
