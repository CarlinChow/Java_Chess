package chess.domain.pieces;

import chess.domain.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class QueenTest {
    private void setUp(Board board){
        // set up queens in default position on an empty board
        Piece whiteQueen = new Queen(true);
        Piece blackQueen = new Queen(false);
        board.getSpotAt(0,3).setPiece(blackQueen);
        board.getSpotAt(7,3).setPiece(whiteQueen);
    }

    @Test
    public void testLegalMove(){
        Board board = new Board();
        this.setUp(board);
        Piece whiteQueen = board.getSpotAt(7,3).getPiece();
        Piece blackQueen = board.getSpotAt(0,3).getPiece();

        // blackQueen diagonal moves & capture
        board.getSpotAt(3,0).setPiece(new Pawn(true));
        board.getSpotAt(4,7).setPiece(new Pawn(true));
        for(int i = 1; i < 4; i++){
            int newRow = blackQueen.getSpot().getRow() + i;
            int newCol = blackQueen.getSpot().getColumn() - i;
            assertTrue(blackQueen.canMove(board, board.getSpotAt(newRow, newCol)));
        }
        for(int i = 1; i < 5; i++){
            int newRow = blackQueen.getSpot().getRow() + i;
            int newCol = blackQueen.getSpot().getColumn() + i;
            assertTrue(blackQueen.canMove(board, board.getSpotAt(newRow, newCol)));
        }
        board.getSpotAt(3,0).removePiece();
        board.getSpotAt(4,7).removePiece();
        // blackQueen left moves & capture
        board.getSpotAt(0,0).setPiece(new Pawn(true));
        for(int i = 1; i < 4; i++){
            int newCol = blackQueen.getSpot().getColumn() - i;
            assertTrue(blackQueen.canMove(board, board.getSpotAt(0,newCol)));
        }
        board.getSpotAt(3,0).removePiece();
        // blackQueen right moves
        board.getSpotAt(0,7).setPiece(new Pawn(true));
        for(int i = 1; i < 5; i++){
            int newCol = blackQueen.getSpot().getColumn() + i;
            assertTrue(blackQueen.canMove(board, board.getSpotAt(0,newCol)));
        }
        board.getSpotAt(0,7).removePiece();
        // blackQueen forward moves
        for(int i = 1; i < 8; i++){
            int newRow = blackQueen.getSpot().getRow() + i;
            assertTrue(blackQueen.canMove(board, board.getSpotAt(newRow,3)));
        }

        // whiteQueen diagonal moves & capture
        board.getSpotAt(4,0).setPiece(new Pawn(false));
        board.getSpotAt(3,7).setPiece(new Pawn(false));
        for(int i = 1; i < 4; i++){
            int newRow = whiteQueen.getSpot().getRow() - i;
            int newCol = whiteQueen.getSpot().getColumn() - i;
            assertTrue(whiteQueen.canMove(board, board.getSpotAt(newRow, newCol)));
        }
        for(int i = 1; i < 5; i++){
            int newRow = whiteQueen.getSpot().getRow() - i;
            int newCol = whiteQueen.getSpot().getColumn() + i;
            assertTrue(whiteQueen.canMove(board, board.getSpotAt(newRow, newCol)));
        }
        board.getSpotAt(4,0).removePiece();
        board.getSpotAt(3,7).removePiece();
        // whiteQueen left moves & capture
        board.getSpotAt(7,0).setPiece(new Pawn(false));
        for(int i = 1; i < 4; i++){
            int newCol = whiteQueen.getSpot().getColumn() - i;
            assertTrue(whiteQueen.canMove(board, board.getSpotAt(7,newCol)));
        }
        board.getSpotAt(7,0).removePiece();
        // blackQueen right moves
        board.getSpotAt(7,7).setPiece(new Pawn(false));
        for(int i = 1; i < 5; i++){
            int newCol = whiteQueen.getSpot().getColumn() + i;
            assertTrue(whiteQueen.canMove(board, board.getSpotAt(7,newCol)));
        }
        board.getSpotAt(7,7).removePiece();
        // blackQueen forward moves
        for(int i = 1; i < 8; i++){
            int newRow = whiteQueen.getSpot().getRow() - i;
            assertTrue(whiteQueen.canMove(board, board.getSpotAt(newRow,3)));
        }
    }

    @Test
    public void testIllegalMove(){
        Board board = new Board();
        this.setUp(board);
        Piece whiteQueen = board.getSpotAt(7,3).getPiece();
        Piece blackQueen = board.getSpotAt(0,3).getPiece();

        // illegal movement
        assertFalse(whiteQueen.canMove(board, board.getSpotAt(6,1)));
        assertFalse(whiteQueen.canMove(board, board.getSpotAt(6,6)));
        assertFalse(whiteQueen.canMove(board, board.getSpotAt(6,5)));

        assertFalse(blackQueen.canMove(board, board.getSpotAt(1,1)));
        assertFalse(blackQueen.canMove(board, board.getSpotAt(1,6)));
        assertFalse(blackQueen.canMove(board, board.getSpotAt(1,5)));

        // illegal capture friendly piece
        board.getSpotAt(0,0).setPiece(new Pawn(false));
        board.getSpotAt(0,7).setPiece(new Pawn(false));
        board.getSpotAt(3,0).setPiece(new Pawn(false));
        board.getSpotAt(4,7).setPiece(new Pawn(false));
        board.getSpotAt(7,0).setPiece(new Pawn(true));
        board.getSpotAt(7,7).setPiece(new Pawn(true));
        board.getSpotAt(4,0).setPiece(new Pawn(true));
        board.getSpotAt(3,7).setPiece(new Pawn(true));
        assertFalse(blackQueen.canMove(board, board.getSpotAt(0,0)));
        assertFalse(blackQueen.canMove(board, board.getSpotAt(0,7)));
        assertFalse(blackQueen.canMove(board, board.getSpotAt(3,0)));
        assertFalse(blackQueen.canMove(board, board.getSpotAt(4,7)));
        assertFalse(whiteQueen.canMove(board, board.getSpotAt(7,0)));
        assertFalse(whiteQueen.canMove(board, board.getSpotAt(7,7)));
        assertFalse(whiteQueen.canMove(board, board.getSpotAt(4,0)));
        assertFalse(whiteQueen.canMove(board, board.getSpotAt(3,7)));
        board.getSpotAt(0,0).removePiece();
        board.getSpotAt(0,7).removePiece();
        board.getSpotAt(3,0).removePiece();
        board.getSpotAt(4,7).removePiece();
        board.getSpotAt(7,0).removePiece();
        board.getSpotAt(7,7).removePiece();
        board.getSpotAt(4,0).removePiece();
        board.getSpotAt(3,7).removePiece();

        // movement with interference
        board.getSpotAt(0,1).setPiece(new Pawn(false));
        board.getSpotAt(0,6).setPiece(new Pawn(true));
        board.getSpotAt(2,1).setPiece(new Pawn(false));
        board.getSpotAt(3,6).setPiece(new Pawn(true));
        board.getSpotAt(7,1).setPiece(new Pawn(false));
        board.getSpotAt(7,6).setPiece(new Pawn(true));
        board.getSpotAt(5,1).setPiece(new Pawn(false));
        board.getSpotAt(4,6).setPiece(new Pawn(true));
        assertFalse(blackQueen.canMove(board, board.getSpotAt(0,0)));
        assertFalse(blackQueen.canMove(board, board.getSpotAt(0,7)));
        assertFalse(blackQueen.canMove(board, board.getSpotAt(3,0)));
        assertFalse(blackQueen.canMove(board, board.getSpotAt(4,7)));
        assertFalse(whiteQueen.canMove(board, board.getSpotAt(7,0)));
        assertFalse(whiteQueen.canMove(board, board.getSpotAt(7,7)));
        assertFalse(whiteQueen.canMove(board, board.getSpotAt(4,0)));
        assertFalse(whiteQueen.canMove(board, board.getSpotAt(3,7)));
    }

}
