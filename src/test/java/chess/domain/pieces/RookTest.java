package chess.domain.pieces;

import org.junit.Test;

import static chess.types.Color.*;
import static org.junit.Assert.*;
import chess.domain.*;
import java.util.Set;

public class RookTest {
    private void setUp(Board board){
        board.clear();
        Piece blackRook1 = new Rook(BLACK);
        Piece blackRook2 = new Rook(BLACK);
        Piece whiteRook1 = new Rook(WHITE);
        Piece whiteRook2 = new Rook(WHITE);
        board.getSpotAt(0,0).setPiece(blackRook1);
        board.getSpotAt(0,7).setPiece(blackRook2);
        board.getSpotAt(7,0).setPiece(whiteRook1);
        board.getSpotAt(7,7).setPiece(whiteRook2);
    }

    @Test
    public void testLegalMove(){
        Board board = new Board();
        this.setUp(board);
        Piece blackRook1 = board.getSpotAt(0,0).getPiece();
        Piece blackRook2 = board.getSpotAt(0,7).getPiece();
        Piece whiteRook1 = board.getSpotAt(7,0).getPiece();
        Piece whiteRook2 = board.getSpotAt(7,7).getPiece();
        for(int i = 1; i < 7; i++){
            assertTrue(blackRook1.canMove(board,  board.getSpotAt(0,i)));
            assertTrue(blackRook1.canMove(board,  board.getSpotAt(i,0)));
        }
        // legal capture move
        assertTrue(blackRook1.canMove(board, board.getSpotAt(7,0)));
        for(int i = 1; i < 7; i++){
            assertTrue(blackRook2.canMove(board, board.getSpotAt(0,7 - i)));
            assertTrue(blackRook2.canMove(board, board.getSpotAt(i,7)));
        }
        assertTrue(blackRook2.canMove(board, board.getSpotAt(7,7)));
        for(int i = 1; i < 7; i++){
            assertTrue(whiteRook1.canMove(board, board.getSpotAt(7,i)));
            assertTrue(whiteRook1.canMove(board, board.getSpotAt(7 - i,0)));
        }
        assertTrue(whiteRook1.canMove(board, board.getSpotAt(0,0)));
        for(int i = 1; i < 7; i++){
            assertTrue(whiteRook2.canMove(board, board.getSpotAt(7,7 - i)));
            assertTrue(whiteRook2.canMove(board, board.getSpotAt(7 - i,7)));
        }
        assertTrue(whiteRook2.canMove(board, board.getSpotAt(0,7)));
    }

    @Test
    public void testIllegalMove(){
        Board board = new Board();
        this.setUp(board);
        Piece blackRook1 = board.getSpotAt(0,0).getPiece();
        Piece blackRook2 = board.getSpotAt(0,7).getPiece();
        Piece whiteRook1 = board.getSpotAt(7,0).getPiece();
        Piece whiteRook2 = board.getSpotAt(7,7).getPiece();

        // diagonal move
        assertFalse(blackRook1.canMove(board, board.getSpotAt(6,6)));
        assertFalse(blackRook2.canMove(board, board.getSpotAt(1,6)));
        assertFalse(whiteRook1.canMove(board, board.getSpotAt(1,6)));
        assertFalse(whiteRook2.canMove(board, board.getSpotAt(1,1)));

        // illegal capture friendly piece
        assertFalse(blackRook1.canMove(board, board.getSpotAt(0,7)));
        assertFalse(blackRook2.canMove(board, board.getSpotAt(0,0)));
        assertFalse(whiteRook1.canMove(board, board.getSpotAt(7,7)));
        assertFalse(whiteRook2.canMove(board, board.getSpotAt(7,0)));

        // movement with interference
        board.getSpotAt(0,3).setPiece(new Pawn(WHITE));
        board.getSpotAt(5,0).setPiece(new Pawn(BLACK));
        board.getSpotAt(7,4).setPiece(new Pawn(BLACK));
        board.getSpotAt(5,7).setPiece(new Pawn(WHITE));
        assertFalse(blackRook1.canMove(board, board.getSpotAt(0,5)));
        assertFalse(blackRook1.canMove(board, board.getSpotAt(6,0)));
        assertFalse(blackRook2.canMove(board, board.getSpotAt(6,7)));
        assertFalse(whiteRook1.canMove(board, board.getSpotAt(7,6)));
        assertFalse(whiteRook1.canMove(board, board.getSpotAt(1,0)));
        assertFalse(whiteRook2.canMove(board, board.getSpotAt(1,7)));
    }

    @Test
    public void testGetMoves(){
        Board board = new Board();
        this.setUp(board);
        Piece blackQueenRook = board.getSpotAt("a8").getPiece();
        Piece blackKingRook = board.getSpotAt("h8").getPiece();
        Piece whiteQueenRook = board.getSpotAt("a1").getPiece();
        Piece whiteKingRook = board.getSpotAt("h1").getPiece();

        Set<Spot> blackQueenRookMoves = blackQueenRook.getMoves(board);
        Set<Spot> blackKingRookMoves = blackKingRook.getMoves(board);
        Set<Spot> whiteQueenRookMoves = whiteQueenRook.getMoves(board);
        Set<Spot> whiteKingRookMoves = whiteKingRook.getMoves(board);

        // black queens side rook
        assertTrue(blackQueenRookMoves.contains(board.getSpotAt("a7")));
        assertTrue(blackQueenRookMoves.contains(board.getSpotAt("a6")));
        assertTrue(blackQueenRookMoves.contains(board.getSpotAt("a5")));
        assertTrue(blackQueenRookMoves.contains(board.getSpotAt("a4")));
        assertTrue(blackQueenRookMoves.contains(board.getSpotAt("a3")));
        assertTrue(blackQueenRookMoves.contains(board.getSpotAt("a2")));
        assertTrue(blackQueenRookMoves.contains(board.getSpotAt("a1")));
        assertTrue(blackQueenRookMoves.contains(board.getSpotAt("b8")));
        assertTrue(blackQueenRookMoves.contains(board.getSpotAt("c8")));
        assertTrue(blackQueenRookMoves.contains(board.getSpotAt("d8")));
        assertTrue(blackQueenRookMoves.contains(board.getSpotAt("e8")));
        assertTrue(blackQueenRookMoves.contains(board.getSpotAt("f8")));
        assertTrue(blackQueenRookMoves.contains(board.getSpotAt("g8")));
        assertFalse(blackQueenRookMoves.contains(board.getSpotAt("h8")));
        assertEquals(13, blackQueenRookMoves.size());

        // black king side rook
        assertTrue(blackKingRookMoves.contains(board.getSpotAt("h7")));
        assertTrue(blackKingRookMoves.contains(board.getSpotAt("h6")));
        assertTrue(blackKingRookMoves.contains(board.getSpotAt("h5")));
        assertTrue(blackKingRookMoves.contains(board.getSpotAt("h4")));
        assertTrue(blackKingRookMoves.contains(board.getSpotAt("h3")));
        assertTrue(blackKingRookMoves.contains(board.getSpotAt("h2")));
        assertTrue(blackKingRookMoves.contains(board.getSpotAt("h1")));
        assertTrue(blackKingRookMoves.contains(board.getSpotAt("b8")));
        assertTrue(blackKingRookMoves.contains(board.getSpotAt("c8")));
        assertTrue(blackKingRookMoves.contains(board.getSpotAt("d8")));
        assertTrue(blackKingRookMoves.contains(board.getSpotAt("e8")));
        assertTrue(blackKingRookMoves.contains(board.getSpotAt("f8")));
        assertTrue(blackKingRookMoves.contains(board.getSpotAt("g8")));
        assertFalse(blackKingRookMoves.contains(board.getSpotAt("a8")));
        assertEquals(13, blackKingRookMoves.size());

        // white queen side rook
        assertTrue(whiteQueenRookMoves.contains(board.getSpotAt("a2")));
        assertTrue(whiteQueenRookMoves.contains(board.getSpotAt("a3")));
        assertTrue(whiteQueenRookMoves.contains(board.getSpotAt("a4")));
        assertTrue(whiteQueenRookMoves.contains(board.getSpotAt("a5")));
        assertTrue(whiteQueenRookMoves.contains(board.getSpotAt("a6")));
        assertTrue(whiteQueenRookMoves.contains(board.getSpotAt("a7")));
        assertTrue(whiteQueenRookMoves.contains(board.getSpotAt("a8")));
        assertTrue(whiteQueenRookMoves.contains(board.getSpotAt("b1")));
        assertTrue(whiteQueenRookMoves.contains(board.getSpotAt("c1")));
        assertTrue(whiteQueenRookMoves.contains(board.getSpotAt("d1")));
        assertTrue(whiteQueenRookMoves.contains(board.getSpotAt("e1")));
        assertTrue(whiteQueenRookMoves.contains(board.getSpotAt("f1")));
        assertTrue(whiteQueenRookMoves.contains(board.getSpotAt("g1")));
        assertFalse(whiteQueenRookMoves.contains(board.getSpotAt("h1")));
        assertEquals(13, whiteQueenRookMoves.size());

        // black king side rook
        assertTrue(whiteKingRookMoves.contains(board.getSpotAt("h2")));
        assertTrue(whiteKingRookMoves.contains(board.getSpotAt("h3")));
        assertTrue(whiteKingRookMoves.contains(board.getSpotAt("h4")));
        assertTrue(whiteKingRookMoves.contains(board.getSpotAt("h5")));
        assertTrue(whiteKingRookMoves.contains(board.getSpotAt("h6")));
        assertTrue(whiteKingRookMoves.contains(board.getSpotAt("h7")));
        assertTrue(whiteKingRookMoves.contains(board.getSpotAt("h8")));
        assertTrue(whiteKingRookMoves.contains(board.getSpotAt("g1")));
        assertTrue(whiteKingRookMoves.contains(board.getSpotAt("f1")));
        assertTrue(whiteKingRookMoves.contains(board.getSpotAt("e1")));
        assertTrue(whiteKingRookMoves.contains(board.getSpotAt("d1")));
        assertTrue(whiteKingRookMoves.contains(board.getSpotAt("c1")));
        assertTrue(whiteKingRookMoves.contains(board.getSpotAt("b1")));
        assertFalse(whiteKingRookMoves.contains(board.getSpotAt("a1")));
        assertEquals(13, whiteKingRookMoves.size());

        // adding interference
        board.getSpotAt("a6").setPiece(new Knight(WHITE));
        board.getSpotAt("c8").setPiece(new Knight(BLACK));
        board.getSpotAt("f8").setPiece(new Knight(WHITE));
        board.getSpotAt("h6").setPiece(new Knight(BLACK));
        board.getSpotAt("a3").setPiece(new Knight(WHITE));
        board.getSpotAt("c1").setPiece(new Knight(BLACK));
        board.getSpotAt("f1").setPiece(new Knight(WHITE));
        board.getSpotAt("h3").setPiece(new Knight(BLACK));

        blackQueenRookMoves = blackQueenRook.getMoves(board);
        blackKingRookMoves = blackKingRook.getMoves(board);
        whiteQueenRookMoves = whiteQueenRook.getMoves(board);
        whiteKingRookMoves = whiteKingRook.getMoves(board);
        // black queen side rook
        assertEquals(3, blackQueenRookMoves.size());
        assertTrue(blackQueenRookMoves.contains(board.getSpotAt("a7")));
        assertTrue(blackQueenRookMoves.contains(board.getSpotAt("a6")));
        assertTrue(blackQueenRookMoves.contains(board.getSpotAt("b8")));
        // black king side rook
        assertEquals(3, blackKingRookMoves.size());
        assertTrue(blackKingRookMoves.contains(board.getSpotAt("f8")));
        assertTrue(blackKingRookMoves.contains(board.getSpotAt("g8")));
        assertTrue(blackKingRookMoves.contains(board.getSpotAt("h7")));
        // white queen side rook
        assertEquals(3, whiteQueenRookMoves.size());
        assertTrue(whiteQueenRookMoves.contains(board.getSpotAt("a2")));
        assertTrue(whiteQueenRookMoves.contains(board.getSpotAt("b1")));
        assertTrue(whiteQueenRookMoves.contains(board.getSpotAt("c1")));
        // white king side rook
        assertEquals(3, whiteKingRookMoves.size());
        assertTrue(whiteKingRookMoves.contains(board.getSpotAt("g1")));
        assertTrue(whiteKingRookMoves.contains(board.getSpotAt("h2")));
        assertTrue(whiteKingRookMoves.contains(board.getSpotAt("h3")));
    }
}
