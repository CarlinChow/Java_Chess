package chess.logic;

import chess.domain.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class MoveListTest {
    @Test
    public void testAddMove(){
        Board board = new Board();
        Piece whiteKnight = board.getSpotAt("g1").getPiece();
        Piece whitePawn = board.getSpotAt("e2").getPiece();
        Piece blackPawn = board.getSpotAt("e7").getPiece();
        MoveList moveList = new MoveList();
        moveList.add(new Move(whitePawn, board.getSpotAt("e4")));
        moveList.add(new Move(blackPawn, board.getSpotAt("d5")));
        moveList.add(new Move(whiteKnight, board.getSpotAt("f3")));
        assertEquals(3, moveList.getAllMoves().size());
        moveList.add(new Move(blackPawn, board.getSpotAt("e4"), whitePawn));
        assertEquals(4, moveList.getAllMoves().size());
        assertEquals(whitePawn, moveList.getAllMoves().get(0).getPiece());
        assertEquals(blackPawn, moveList.getAllMoves().get(1).getPiece());
        assertEquals(whiteKnight, moveList.getAllMoves().get(2).getPiece());
        assertEquals(blackPawn, moveList.getAllMoves().get(3).getPiece());
        assertEquals(whitePawn, moveList.getAllMoves().get(3).getCapturedPiece());
    }

    @Test
    public void testHasMoved(){
        Board board = new Board();
        Piece whitePawn = board.getSpotAt("e2").getPiece();
        Piece blackRook = board.getSpotAt("a8").getPiece();
        MoveList moveList = new MoveList();
        assertFalse(moveList.hasMoved(whitePawn));
        assertFalse(moveList.hasMoved(blackRook));
        moveList.add(new Move(whitePawn, board.getSpotAt("e4")));
        assertTrue(moveList.hasMoved(whitePawn));
        assertFalse(moveList.hasMoved(blackRook));
    }
}
