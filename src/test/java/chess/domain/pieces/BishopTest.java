package chess.domain.pieces;

import chess.domain.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class BishopTest {
    private void setUp(Board board){
        board.getSpotAt(0,2).setPiece(new Bishop(false));
        board.getSpotAt(0,5).setPiece(new Bishop(false));
        board.getSpotAt(7,2).setPiece(new Bishop(true));
        board.getSpotAt(7,5).setPiece(new Bishop(true));
    }

    @Test
    public void testLegalMove(){
        Board board = new Board();
        this.setUp(board);
        Piece blackBishop1 = board.getSpotAt(0,2).getPiece();
        Piece blackBishop2 = board.getSpotAt(0,5).getPiece();
        Piece whiteBishop1 = board.getSpotAt(7,2).getPiece();
        Piece whiteBishop2 = board.getSpotAt(7,5).getPiece();

        // blackBishop 1 all possible moves
        for(int i = 1; i < 3; i++){
            int newRow = blackBishop1.getSpot().getRow() + i;
            int newCol = blackBishop1.getSpot().getColumn() - i;
            assertTrue(blackBishop1.canMove(board, blackBishop1.getSpot(), board.getSpotAt(newRow, newCol)));
        }
        for(int i = 1; i < 6; i++){
            int newRow = blackBishop1.getSpot().getRow() + i;
            int newCol = blackBishop1.getSpot().getColumn() + i;
            assertTrue(blackBishop1.canMove(board, blackBishop1.getSpot(), board.getSpotAt(newRow, newCol)));
        }
        // blackBishop 2 all possible moves
        for(int i = 1; i < 3; i++){
            int newRow = blackBishop2.getSpot().getRow() + i;
            int newCol = blackBishop2.getSpot().getColumn() + i;
            assertTrue(blackBishop2.canMove(board, blackBishop2.getSpot(), board.getSpotAt(newRow, newCol)));
        }
        for(int i = 1; i < 6; i++){
            int newRow = blackBishop2.getSpot().getRow() + i;
            int newCol = blackBishop2.getSpot().getColumn() - i;
            assertTrue(blackBishop2.canMove(board, blackBishop2.getSpot(), board.getSpotAt(newRow, newCol)));
        }

        // whiteBishop 1 all possible moves
        for(int i = 1; i < 3; i++){
            int newRow = whiteBishop1.getSpot().getRow() - i;
            int newCol = whiteBishop1.getSpot().getColumn() - i;
            assertTrue(whiteBishop1.canMove(board, whiteBishop1.getSpot(), board.getSpotAt(newRow, newCol)));
        }
        for(int i = 1; i < 6; i++){
            int newRow = whiteBishop1.getSpot().getRow() - i;
            int newCol = whiteBishop1.getSpot().getColumn() + i;
            assertTrue(whiteBishop1.canMove(board, whiteBishop1.getSpot(), board.getSpotAt(newRow, newCol)));
        }
        // whiteBishop 2 all possible moves
        for(int i = 1; i < 3; i++){
            int newRow = whiteBishop2.getSpot().getRow() - i;
            int newCol = whiteBishop2.getSpot().getColumn() + i;
            assertTrue(whiteBishop2.canMove(board, whiteBishop2.getSpot(), board.getSpotAt(newRow, newCol)));
        }
        for(int i = 1; i < 6; i++){
            int newRow = whiteBishop2.getSpot().getRow() - i;
            int newCol = whiteBishop2.getSpot().getColumn() - i;
            assertTrue(whiteBishop2.canMove(board, whiteBishop2.getSpot(), board.getSpotAt(newRow, newCol)));
        }
        // blackBishop legal capture
        blackBishop1.getSpot().removePiece();
        board.getSpotAt(1,1).setPiece(blackBishop1);
        board.getSpotAt(7,7).setPiece(new Pawn(true));
        assertTrue(blackBishop1.canMove(board, blackBishop1.getSpot(), board.getSpotAt(0,2)));
        assertTrue(blackBishop1.canMove(board, blackBishop1.getSpot(), board.getSpotAt(7,7)));
        assertTrue(blackBishop1.canMove(board, blackBishop1.getSpot(), board.getSpotAt(0,0)));
        assertTrue(blackBishop1.canMove(board, blackBishop1.getSpot(), board.getSpotAt(4,4)));
        // whiteBishop legal capture
        board.getSpotAt(0,7).setPiece(new Pawn(false));
        whiteBishop1.getSpot().removePiece();
        board.getSpotAt(6,1).setPiece(whiteBishop1);
        assertTrue(whiteBishop1.canMove(board, whiteBishop1.getSpot(), board.getSpotAt(7,2)));
        assertTrue(whiteBishop1.canMove(board, whiteBishop1.getSpot(), board.getSpotAt(0,7)));
        assertTrue(whiteBishop1.canMove(board, whiteBishop1.getSpot(), board.getSpotAt(7,0)));
        assertTrue(whiteBishop1.canMove(board, whiteBishop1.getSpot(), board.getSpotAt(1,6)));
    }
    @Test
    public void testIllegalMove(){
        Board board = new Board();
        this.setUp(board);
        Piece blackBishop1 = board.getSpotAt(0,2).getPiece();
        Piece blackBishop2 = board.getSpotAt(0,5).getPiece();
        Piece whiteBishop1 = board.getSpotAt(7,2).getPiece();
        Piece whiteBishop2 = board.getSpotAt(7,5).getPiece();

        assertFalse(blackBishop1.canMove(board, blackBishop1.getSpot(), board.getSpotAt(1,2)));
        assertFalse(blackBishop1.canMove(board, blackBishop1.getSpot(), board.getSpotAt(4,2)));
        assertFalse(whiteBishop1.canMove(board, whiteBishop1.getSpot(), board.getSpotAt(5,2)));
        assertFalse(whiteBishop1.canMove(board, whiteBishop1.getSpot(), board.getSpotAt(3,3)));

        board.getSpotAt(3,2).setPiece(new Pawn(true));
        board.getSpotAt(4,2).setPiece(new Pawn(true));
        board.getSpotAt(3,5).setPiece(new Pawn(true));
        board.getSpotAt(4,5).setPiece(new Pawn(true));
        board.getSpotAt(1,1).setPiece(new Pawn(false));
        board.getSpotAt(1,6).setPiece(new Pawn(false));
        board.getSpotAt(6,1).setPiece(new Pawn(false));
        board.getSpotAt(6,6).setPiece(new Pawn(false));

        board.print();
        assertFalse(blackBishop1.canMove(board, blackBishop1.getSpot(), board.getSpotAt(4,6)));
        assertFalse(blackBishop2.canMove(board, blackBishop2.getSpot(), board.getSpotAt(4,1)));
        assertFalse(whiteBishop1.canMove(board, whiteBishop1.getSpot(), board.getSpotAt(3,6)));
        assertFalse(whiteBishop2.canMove(board, whiteBishop2.getSpot(), board.getSpotAt(3,1)));
        assertFalse(blackBishop1.canMove(board, blackBishop1.getSpot(), board.getSpotAt(2,0)));
        assertFalse(blackBishop2.canMove(board, blackBishop2.getSpot(), board.getSpotAt(2,7)));
        assertFalse(whiteBishop1.canMove(board, whiteBishop1.getSpot(), board.getSpotAt(5,0)));
        assertFalse(whiteBishop2.canMove(board, whiteBishop2.getSpot(), board.getSpotAt(5,7)));

        // flip pawn colour
        board.getSpotAt(3,2).setPiece(new Pawn(false));
        board.getSpotAt(4,2).setPiece(new Pawn(false));
        board.getSpotAt(3,5).setPiece(new Pawn(false));
        board.getSpotAt(4,5).setPiece(new Pawn(false));
        board.getSpotAt(1,1).setPiece(new Pawn(true));
        board.getSpotAt(1,6).setPiece(new Pawn(true));
        board.getSpotAt(6,1).setPiece(new Pawn(true));
        board.getSpotAt(6,6).setPiece(new Pawn(true));

        assertFalse(blackBishop1.canMove(board, blackBishop1.getSpot(), board.getSpotAt(4,6)));
        assertFalse(blackBishop2.canMove(board, blackBishop2.getSpot(), board.getSpotAt(4,1)));
        assertFalse(whiteBishop1.canMove(board, whiteBishop1.getSpot(), board.getSpotAt(3,6)));
        assertFalse(whiteBishop2.canMove(board, whiteBishop2.getSpot(), board.getSpotAt(3,1)));
        assertFalse(blackBishop1.canMove(board, blackBishop1.getSpot(), board.getSpotAt(2,0)));
        assertFalse(blackBishop2.canMove(board, blackBishop2.getSpot(), board.getSpotAt(2,7)));
        assertFalse(whiteBishop1.canMove(board, whiteBishop1.getSpot(), board.getSpotAt(5,0)));
        assertFalse(whiteBishop2.canMove(board, whiteBishop2.getSpot(), board.getSpotAt(5,7)));
    }

}
