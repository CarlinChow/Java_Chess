package chess.domain.pieces;

import org.junit.Test;

import static chess.types.Color.*;
import static org.junit.Assert.*;
import chess.domain.*;
import java.util.Set;


public class PawnTest {
    private void setUp(Board board){
        // set up board with two pawns, a white pawn on pos 6,3 and black pawn on pos 1,3
        board.clear();
        Piece whitePawn = new Pawn(WHITE);
        Piece blackPawn = new Pawn(BLACK);
        board.getSpotAt("d2").setPiece(whitePawn);
        board.getSpotAt("d7").setPiece(blackPawn);
    }

    @Test
    public void testForwardMove(){
        Board board = new Board();
        this.setUp(board);
        Piece whitePawn =  board.getSpotAt(6, 3).getPiece();
        Piece blackPawn = board.getSpotAt(1, 3).getPiece();
        // move forward one spot
        assertTrue(whitePawn.canMove(board, board.getSpotAt(5,3)));
        assertTrue(blackPawn.canMove(board, board.getSpotAt(2,3)));
        // move forward twice
        assertTrue(whitePawn.canMove(board, board.getSpotAt(4,3)));
        assertTrue(blackPawn.canMove(board, board.getSpotAt(3,3)));
        // move forward twice with interference
        Piece whitePawn1 = new Pawn(WHITE);
        Piece blackPawn1 = new Pawn(BLACK);
        board.getSpotAt(5, 3).setPiece(whitePawn1);
        board.getSpotAt(2, 3).setPiece(blackPawn1);
        assertFalse(whitePawn.canMove(board, board.getSpotAt(4, 3)));
        assertFalse(blackPawn.canMove(board, board.getSpotAt(3, 3)));
        assertFalse(whitePawn.canMove(board, board.getSpotAt(5, 3)));
        assertFalse(blackPawn.canMove(board, board.getSpotAt(2, 3)));
        assertFalse(whitePawn1.canMove(board, board.getSpotAt(3, 3)));
        assertFalse(blackPawn1.canMove(board, board.getSpotAt(4, 3)));
    }

    @Test
    public void testIllegalMove(){
        Board board = new Board();
        this.setUp(board);
        Piece whitePawn =  board.getSpotAt(6, 3).getPiece();
        Piece blackPawn = board.getSpotAt(1, 3).getPiece();

        // white pawn starting position on 6, 3
        assertFalse(whitePawn.canMove(board, board.getSpotAt(3, 3)));
        assertFalse(whitePawn.canMove(board, board.getSpotAt(5, 4)));
        assertFalse(whitePawn.canMove(board, board.getSpotAt(5, 2)));
        assertFalse(whitePawn.canMove(board, board.getSpotAt(0, 0)));
        assertFalse(whitePawn.canMove(board, board.getSpotAt(7, 3)));
        assertFalse(whitePawn.canMove(board, board.getSpotAt(7, 2)));
        assertFalse(whitePawn.canMove(board, board.getSpotAt(7, 4)));
        // black pawn starting position on 1, 3
        assertFalse(blackPawn.canMove(board, board.getSpotAt(4, 3)));
        assertFalse(blackPawn.canMove(board, board.getSpotAt(2, 2)));
        assertFalse(blackPawn.canMove(board, board.getSpotAt(2, 1)));
        assertFalse(blackPawn.canMove(board, board.getSpotAt(0, 3)));
        assertFalse(blackPawn.canMove(board, board.getSpotAt(0, 2)));
        assertFalse(blackPawn.canMove(board, board.getSpotAt(0, 4)));
        assertFalse(blackPawn.canMove(board, board.getSpotAt(7, 7)));

        board.getSpotAt(6,3).removePiece();
        board.getSpotAt(1,3).removePiece();
        board.getSpotAt(5,3).setPiece(whitePawn);
        board.getSpotAt(2,3).setPiece(blackPawn);
        assertFalse(whitePawn.canMove(board, board.getSpotAt(3,3)));
        assertFalse(blackPawn.canMove(board, board.getSpotAt(4,3)));
    }

    @Test
    public void testCapture(){
        Board board = new Board();
        this.setUp(board);
        Piece whitePawn =  board.getSpotAt(6, 3).getPiece();
        Piece blackPawn = board.getSpotAt(1, 3).getPiece();

        Piece whiteCapturePawn = new Pawn(WHITE);
        Piece blackCapturePawn = new Pawn(BLACK);

        board.getSpotAt(2,4).setPiece(whiteCapturePawn);
        assertTrue(blackPawn.canMove(board, board.getSpotAt(2,4)));
        board.getSpotAt(2,4).setPiece(blackCapturePawn);
        assertFalse(blackPawn.canMove(board, board.getSpotAt(2,4)));
        board.getSpotAt(2,4).removePiece();
        board.getSpotAt(2,2).setPiece(whiteCapturePawn);
        assertTrue(blackPawn.canMove(board, board.getSpotAt(2,2)));
        board.getSpotAt(2,2).setPiece(blackCapturePawn);
        assertFalse(blackPawn.canMove(board, board.getSpotAt(2,2)));
        board.getSpotAt(2,2).removePiece();

        board.getSpotAt(5,2).setPiece(blackCapturePawn);
        assertTrue(whitePawn.canMove(board, board.getSpotAt(5,2)));
        board.getSpotAt(5,2).setPiece(whiteCapturePawn);
        assertFalse(whitePawn.canMove(board, board.getSpotAt(5,2)));
        board.getSpotAt(5,2).removePiece();
        board.getSpotAt(5,4).setPiece(blackCapturePawn);
        assertTrue(whitePawn.canMove(board, board.getSpotAt(5,4)));
        board.getSpotAt(5,4).setPiece(whiteCapturePawn);
        assertFalse(whitePawn.canMove(board, board.getSpotAt(5,4)));
    }

    @Test
    public void testGetMoves(){
        Board board = new Board();
        this.setUp(board);
        Piece whitePawn = board.getSpotAt("d2").getPiece();
        Piece blackPawn = board.getSpotAt("d7").getPiece();
        Set<Spot> whitePawnMoves = whitePawn.getMoves(board);
        Set<Spot> blackPawnMoves = blackPawn.getMoves(board);

        // assert possible moves
        assertTrue(whitePawnMoves.contains(board.getSpotAt("d3")));
        assertTrue(whitePawnMoves.contains(board.getSpotAt("d4")));
        assertEquals(2, whitePawnMoves.size());
        assertTrue(blackPawnMoves.contains(board.getSpotAt("d6")));
        assertTrue(blackPawnMoves.contains(board.getSpotAt("d5")));
        assertEquals(2, blackPawnMoves.size());

        board.getSpotAt("c3").setPiece(new Pawn(WHITE));
        board.getSpotAt("e3").setPiece(new Pawn(BLACK));
        board.getSpotAt("c6").setPiece(new Pawn(WHITE));
        board.getSpotAt("e6").setPiece(new Pawn(BLACK));

        whitePawnMoves = whitePawn.getMoves(board);
        blackPawnMoves = blackPawn.getMoves(board);

        // moves with potential capture spot
        assertTrue(whitePawnMoves.contains(board.getSpotAt("d3")));
        assertTrue(whitePawnMoves.contains(board.getSpotAt("d4")));
        assertTrue(whitePawnMoves.contains(board.getSpotAt("e3")));
        assertEquals(3, whitePawnMoves.size());
        assertTrue(blackPawnMoves.contains(board.getSpotAt("c6")));
        assertTrue(blackPawnMoves.contains(board.getSpotAt("d5")));
        assertTrue(blackPawnMoves.contains(board.getSpotAt("d6")));
        assertEquals(3, blackPawnMoves.size());

        board.getSpotAt("c3").setPiece(new Pawn(BLACK));
        board.getSpotAt("e6").setPiece(new Pawn(WHITE));

        whitePawnMoves = whitePawn.getMoves(board);
        blackPawnMoves = blackPawn.getMoves(board);

        assertTrue(whitePawnMoves.contains(board.getSpotAt("d3")));
        assertTrue(whitePawnMoves.contains(board.getSpotAt("e3")));
        assertTrue(whitePawnMoves.contains(board.getSpotAt("c3")));
        assertTrue(whitePawnMoves.contains(board.getSpotAt("d4")));
        assertEquals(4, whitePawnMoves.size());
        assertTrue(blackPawnMoves.contains(board.getSpotAt("c6")));
        assertTrue(blackPawnMoves.contains(board.getSpotAt("e6")));
        assertTrue(blackPawnMoves.contains(board.getSpotAt("d6")));
        assertTrue(blackPawnMoves.contains(board.getSpotAt("d5")));
        assertEquals(4, blackPawnMoves.size());

        board.getSpotAt("d3").setPiece(new Queen(BLACK));
        board.getSpotAt("d6").setPiece(new Queen(WHITE));

        whitePawnMoves = whitePawn.getMoves(board);
        blackPawnMoves = blackPawn.getMoves(board);

        assertTrue(whitePawnMoves.contains(board.getSpotAt("e3")));
        assertTrue(whitePawnMoves.contains(board.getSpotAt("c3")));
        assertEquals(2, whitePawnMoves.size());
        assertTrue(blackPawnMoves.contains(board.getSpotAt("e6")));
        assertTrue(blackPawnMoves.contains(board.getSpotAt("c6")));
        assertEquals(2, blackPawnMoves.size());

        board.getSpotAt("d3").setPiece(new Queen(WHITE));
        board.getSpotAt("d6").setPiece(new Queen(BLACK));

        whitePawnMoves = whitePawn.getMoves(board);
        blackPawnMoves = blackPawn.getMoves(board);

        assertTrue(whitePawnMoves.contains(board.getSpotAt("e3")));
        assertTrue(whitePawnMoves.contains(board.getSpotAt("c3")));
        assertEquals(2, whitePawnMoves.size());
        assertTrue(blackPawnMoves.contains(board.getSpotAt("e6")));
        assertTrue(blackPawnMoves.contains(board.getSpotAt("c6")));
        assertEquals(2, blackPawnMoves.size());
    }
}
