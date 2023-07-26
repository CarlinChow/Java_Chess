package chess.logic;

import org.junit.Test;
import static org.junit.Assert.*;
import chess.domain.*;
import chess.domain.pieces.*;

public class MoveTest {
    @Test
    public void TestConstructor(){
        Board board = new Board();
        Piece whitePawn = board.getSpotAt("e2").getPiece();
        Spot start = whitePawn.getSpot();
        Spot end = board.getSpotAt("e4");
        Move move = new Move(whitePawn, end);
        assertEquals(whitePawn, move.getPiece());
        assertEquals(start, move.getStart());
        assertEquals(end, move.getEnd());
        assertNull(move.getCapturedPiece());
        assertTrue(move.getPiece() instanceof Pawn);
    }
}
