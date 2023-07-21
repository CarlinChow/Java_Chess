package chess.domain;

import chess.domain.pieces.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class BoardTest {
    @Test
    public void testReset(){
        Board board = new Board();
        // pawn positions
        for(int j = 0; j < 8; j++){
            assertTrue(board.getSpotAt(1,j).getPiece() instanceof Pawn);
            assertTrue(board.getSpotAt(6, j).getPiece() instanceof Pawn);
        }
        // Rook positions
        assertTrue(board.getSpotAt("a8").getPiece() instanceof Rook);
        assertTrue(board.getSpotAt("h8").getPiece() instanceof Rook);
        assertTrue(board.getSpotAt("a1").getPiece() instanceof Rook);
        assertTrue(board.getSpotAt("h1").getPiece() instanceof Rook);
        // knight positions
        assertTrue(board.getSpotAt("b8").getPiece() instanceof Knight);
        assertTrue(board.getSpotAt("g8").getPiece() instanceof Knight);
        assertTrue(board.getSpotAt("b1").getPiece() instanceof Knight);
        assertTrue(board.getSpotAt("g1").getPiece() instanceof Knight);
        // bishop positions
        assertTrue(board.getSpotAt("c8").getPiece() instanceof Bishop);
        assertTrue(board.getSpotAt("f8").getPiece() instanceof Bishop);
        assertTrue(board.getSpotAt("c1").getPiece() instanceof Bishop);
        assertTrue(board.getSpotAt("f1").getPiece() instanceof Bishop);
        // king positions
        assertTrue(board.getSpotAt("e8").getPiece() instanceof King);
        assertTrue(board.getSpotAt("e1").getPiece() instanceof King);
        // queen positions
        assertTrue(board.getSpotAt("d8").getPiece() instanceof Queen);
        assertTrue(board.getSpotAt("d1").getPiece() instanceof Queen);
    }

    @Test
    public void testGetSpotAt(){
        Board board = new Board();
        assertEquals(1, board.getSpotAt(1,0).getRow());
        assertEquals(0, board.getSpotAt(1,0).getColumn());
        assertEquals(5, board.getSpotAt(5,2).getRow());
        assertEquals(2, board.getSpotAt(5,2).getColumn());

        assertEquals("a7", board.getSpotAt(1,0).getChessCoordinates());
        assertEquals("g5", board.getSpotAt(3,6).getChessCoordinates());
    }
}


