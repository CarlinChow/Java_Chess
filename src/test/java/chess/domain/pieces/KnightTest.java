package chess.domain.pieces;

import chess.domain.*;
import org.junit.Test;
import java.util.Set;

import static org.junit.Assert.*;

public class KnightTest {
    private void setUp(Board board){
        board.clear();
        board.getSpotAt("b8").setPiece(new Knight(false));
        board.getSpotAt("g8").setPiece(new Knight(false));
        board.getSpotAt("b1").setPiece(new Knight(true));
        board.getSpotAt("g1").setPiece(new Knight(true));
    }

    @Test
    public void testLegalMove(){
        Board board = new Board();
        this.setUp(board);
        Piece blackKnight1 = board.getSpotAt("b8").getPiece();
        Piece blackKnight2 = board.getSpotAt("g8").getPiece();
        Piece whiteKnight1= board.getSpotAt("b1").getPiece();
        Piece whiteKnight2 = board.getSpotAt("g1").getPiece();

        // black knights
        assertTrue(blackKnight1.canMove(board, board.getSpotAt("a6")));
        assertTrue(blackKnight1.canMove(board, board.getSpotAt("c6")));
        assertTrue(blackKnight1.canMove(board, board.getSpotAt("d7")));
        assertTrue(blackKnight2.canMove(board, board.getSpotAt("e7")));
        assertTrue(blackKnight2.canMove(board, board.getSpotAt("f6")));
        assertTrue(blackKnight2.canMove(board, board.getSpotAt("h6")));

        // white knights
        assertTrue(whiteKnight1.canMove(board, board.getSpotAt("a3")));
        assertTrue(whiteKnight1.canMove(board, board.getSpotAt("c3")));
        assertTrue(whiteKnight1.canMove(board, board.getSpotAt("d2")));
        assertTrue(whiteKnight2.canMove(board, board.getSpotAt("f3")));
        assertTrue(whiteKnight2.canMove(board, board.getSpotAt("h3")));
        assertTrue(whiteKnight2.canMove(board, board.getSpotAt("e2")));

        // black knight legal capture
        board.getSpotAt("a6").setPiece(new Pawn(true));
        board.getSpotAt("d7").setPiece(new Pawn(true));
        board.getSpotAt("e7").setPiece(new Pawn(true));
        board.getSpotAt("h6").setPiece(new Pawn(true));
        assertTrue(blackKnight1.canMove(board, board.getSpotAt("a6")));
        assertTrue(blackKnight1.canMove(board, board.getSpotAt("d7")));
        assertTrue(blackKnight2.canMove(board, board.getSpotAt("e7")));
        assertTrue(blackKnight2.canMove(board, board.getSpotAt("h6")));

        // white knight legal capture
        board.getSpotAt("a3").setPiece(new Pawn(false));
        board.getSpotAt("d2").setPiece(new Pawn(false));
        board.getSpotAt("f3").setPiece(new Pawn(false));
        board.getSpotAt("e2").setPiece(new Pawn(false));
        assertTrue(whiteKnight1.canMove(board, board.getSpotAt("a3")));
        assertTrue(whiteKnight1.canMove(board, board.getSpotAt("d2")));
        assertTrue(whiteKnight2.canMove(board, board.getSpotAt("f3")));
        assertTrue(whiteKnight2.canMove(board, board.getSpotAt("e2")));
    }

    @Test
    public void testIllegalMove(){
        Board board = new Board();
        this.setUp(board);
        Piece blackKnight1 = board.getSpotAt("b8").getPiece();
        Piece blackKnight2 = board.getSpotAt("g8").getPiece();
        Piece whiteKnight1= board.getSpotAt("b1").getPiece();
        Piece whiteKnight2 = board.getSpotAt("g1").getPiece();

        // black knights illegal friendly capture
        board.getSpotAt("a6").setPiece(new Pawn(false));
        board.getSpotAt("d7").setPiece(new Pawn(false));
        board.getSpotAt("e7").setPiece(new Pawn(false));
        board.getSpotAt("h6").setPiece(new Pawn(false));
        assertFalse(blackKnight1.canMove(board, board.getSpotAt("a6")));
        assertFalse(blackKnight1.canMove(board, board.getSpotAt("d7")));
        assertFalse(blackKnight2.canMove(board, board.getSpotAt("e7")));
        assertFalse(blackKnight2.canMove(board, board.getSpotAt("h6")));

        // white knights illegal friendly capture
        board.getSpotAt("a3").setPiece(new Pawn(true));
        board.getSpotAt("d2").setPiece(new Pawn(true));
        board.getSpotAt("f3").setPiece(new Pawn(true));
        board.getSpotAt("e2").setPiece(new Pawn(true));
        assertFalse(whiteKnight1.canMove(board, board.getSpotAt("a3")));
        assertFalse(whiteKnight1.canMove(board, board.getSpotAt("d2")));
        assertFalse(whiteKnight2.canMove(board, board.getSpotAt("f3")));
        assertFalse(whiteKnight2.canMove(board, board.getSpotAt("e2")));
    }

    @Test
    public void testGetMoves(){
        Board board = new Board();
        this.setUp(board);
        Piece blackQueenKnight = board.getSpotAt("b8").getPiece();
        Piece blackKingKnight = board.getSpotAt("g8").getPiece();
        Piece whiteQueenKnight = board.getSpotAt("b1").getPiece();
        Piece whiteKingKnight = board.getSpotAt("g1").getPiece();

        Set<Spot> blackQueenKnightMoves = blackQueenKnight.getMoves(board);
        Set<Spot> blackKingKnightMoves = blackKingKnight.getMoves(board);
        Set<Spot> whiteQueenKnightMoves = whiteQueenKnight.getMoves(board);
        Set<Spot> whiteKingKnightMoves = whiteKingKnight.getMoves(board);

        assertTrue(blackQueenKnightMoves.contains(board.getSpotAt("a6")));
        assertTrue(blackQueenKnightMoves.contains(board.getSpotAt("c6")));
        assertTrue(blackQueenKnightMoves.contains(board.getSpotAt("d7")));
        assertEquals(3, blackQueenKnightMoves.size());
        assertTrue(blackKingKnightMoves.contains(board.getSpotAt("e7")));
        assertTrue(blackKingKnightMoves.contains(board.getSpotAt("f6")));
        assertTrue(blackKingKnightMoves.contains(board.getSpotAt("h6")));
        assertEquals(3, blackKingKnightMoves.size());
        assertTrue(whiteQueenKnightMoves.contains(board.getSpotAt("a3")));
        assertTrue(whiteQueenKnightMoves.contains(board.getSpotAt("c3")));
        assertTrue(whiteQueenKnightMoves.contains(board.getSpotAt("d2")));
        assertEquals(3, whiteQueenKnightMoves.size());
        assertTrue(whiteKingKnightMoves.contains(board.getSpotAt("e2")));
        assertTrue(whiteKingKnightMoves.contains(board.getSpotAt("f3")));
        assertTrue(whiteKingKnightMoves.contains(board.getSpotAt("h3")));
        assertEquals(3, whiteKingKnightMoves.size());

        // adding interference
        board.getSpotAt("d7").setPiece(new Pawn(false));
        board.getSpotAt("e7").setPiece(new Pawn(false));
        board.getSpotAt("a6").setPiece(new Pawn(true));
        board.getSpotAt("f6").setPiece(new Pawn(true));
        board.getSpotAt("d2").setPiece(new Pawn(true));
        board.getSpotAt("e2").setPiece(new Pawn(true));
        board.getSpotAt("a3").setPiece(new Pawn(false));
        board.getSpotAt("f3").setPiece(new Pawn(false));

        blackQueenKnightMoves = blackQueenKnight.getMoves(board);
        blackKingKnightMoves = blackKingKnight.getMoves(board);
        whiteQueenKnightMoves = whiteQueenKnight.getMoves(board);
        whiteKingKnightMoves = whiteKingKnight.getMoves(board);

        assertTrue(blackQueenKnightMoves.contains(board.getSpotAt("a6")));
        assertTrue(blackQueenKnightMoves.contains(board.getSpotAt("c6")));
        assertEquals(2, blackQueenKnightMoves.size());
        assertTrue(blackKingKnightMoves.contains(board.getSpotAt("f6")));
        assertTrue(blackKingKnightMoves.contains(board.getSpotAt("h6")));
        assertEquals(2, blackKingKnightMoves.size());
        assertTrue(whiteQueenKnightMoves.contains(board.getSpotAt("a3")));
        assertTrue(whiteQueenKnightMoves.contains(board.getSpotAt("c3")));
        assertEquals(2, whiteQueenKnightMoves.size());
        assertTrue(whiteKingKnightMoves.contains(board.getSpotAt("f3")));
        assertTrue(whiteKingKnightMoves.contains(board.getSpotAt("h3")));
        assertEquals(2, whiteKingKnightMoves.size());

        // moving black queen side knight to center of board
        board.getSpotAt("b8").removePiece();
        board.getSpotAt("c6").setPiece(blackQueenKnight);
        blackQueenKnightMoves = blackQueenKnight.getMoves(board);

        assertTrue(blackQueenKnightMoves.contains(board.getSpotAt("b8")));
        assertTrue(blackQueenKnightMoves.contains(board.getSpotAt("d8")));
        assertTrue(blackQueenKnightMoves.contains(board.getSpotAt("a7")));
        assertTrue(blackQueenKnightMoves.contains(board.getSpotAt("a5")));
        assertTrue(blackQueenKnightMoves.contains(board.getSpotAt("b4")));
        assertTrue(blackQueenKnightMoves.contains(board.getSpotAt("d4")));
        assertTrue(blackQueenKnightMoves.contains(board.getSpotAt("e5")));
        assertEquals(7, blackQueenKnightMoves.size());
    }
}
