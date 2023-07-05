package chess.domain.pieces;

import org.junit.Test;
import static org.junit.Assert.*;
import chess.domain.*;
import java.util.ArrayList;
import java.util.Arrays;

public class RookTest {
    private void setUp(Board board){
        Piece blackRook1 = new Rook(false);
        Piece blackRook2 = new Rook(false);
        Piece whiteRook1 = new Rook(true);
        Piece whiteRook2 = new Rook(true);
        board.getSpotAt(0,0).setPiece(blackRook1);
        blackRook1.setSpot(board.getSpotAt(0,0));
        board.getSpotAt(0,7).setPiece(blackRook2);
        blackRook2.setSpot(board.getSpotAt(0,7));
        board.getSpotAt(7,0).setPiece(whiteRook1);
        whiteRook1.setSpot(board.getSpotAt(7,0));
        board.getSpotAt(7,7).setPiece(whiteRook2);
        whiteRook2.setSpot(board.getSpotAt(7,7));
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
            assertTrue(blackRook1.canMove(board, blackRook1.getSpot(), board.getSpotAt(0,i)));
            assertTrue(blackRook1.canMove(board, blackRook1.getSpot(), board.getSpotAt(i,0)));
        }
        // legal capture move
        assertTrue(blackRook1.canMove(board, board.getSpotAt(0,0), board.getSpotAt(7,0)));
        for(int i = 1; i < 7; i++){
            assertTrue(blackRook2.canMove(board, blackRook2.getSpot(), board.getSpotAt(0,7 - i)));
            assertTrue(blackRook2.canMove(board, blackRook2.getSpot(), board.getSpotAt(i,7)));
        }
        assertTrue(blackRook2.canMove(board, blackRook2.getSpot(), board.getSpotAt(7,7)));
        for(int i = 1; i < 7; i++){
            assertTrue(whiteRook1.canMove(board, whiteRook1.getSpot(), board.getSpotAt(7,i)));
            assertTrue(whiteRook1.canMove(board, whiteRook1.getSpot(), board.getSpotAt(7 - i,0)));
        }
        assertTrue(whiteRook1.canMove(board, whiteRook1.getSpot(), board.getSpotAt(0,0)));
        for(int i = 1; i < 7; i++){
            assertTrue(whiteRook2.canMove(board, whiteRook2.getSpot(), board.getSpotAt(7,7 - i)));
            assertTrue(whiteRook2.canMove(board, whiteRook2.getSpot(), board.getSpotAt(7 - i,7)));
        }
        assertTrue(whiteRook2.canMove(board, whiteRook2.getSpot(), board.getSpotAt(0,7)));
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
        assertFalse(blackRook1.canMove(board, board.getSpotAt(0,0), board.getSpotAt(6,6)));
        assertFalse(blackRook2.canMove(board, board.getSpotAt(0,7), board.getSpotAt(1,6)));
        assertFalse(whiteRook1.canMove(board, board.getSpotAt(7,0), board.getSpotAt(1,6)));
        assertFalse(whiteRook2.canMove(board, board.getSpotAt(7,7), board.getSpotAt(1,1)));

        // illegal capture friendly piece
        assertFalse(blackRook1.canMove(board, blackRook1.getSpot(), board.getSpotAt(0,7)));
        assertFalse(blackRook2.canMove(board, blackRook2.getSpot(), board.getSpotAt(0,0)));
        assertFalse(whiteRook1.canMove(board, whiteRook1.getSpot(), board.getSpotAt(7,7)));
        assertFalse(whiteRook2.canMove(board, whiteRook2.getSpot(), board.getSpotAt(7,0)));
    }

}
