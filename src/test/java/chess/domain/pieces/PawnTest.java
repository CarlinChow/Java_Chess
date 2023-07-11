package chess.domain.pieces;

import org.junit.Test;
import static org.junit.Assert.*;
import chess.domain.*;


public class PawnTest {
    @Test
    public void testForwardMove(){
        Board board = new Board();
        this.setUpBoard(board);
        Piece whitePawn =  board.getSpotAt(6, 3).getPiece();
        Piece blackPawn = board.getSpotAt(1, 3).getPiece();
        // move forward one spot
        assertTrue(whitePawn.canMove(board, board.getSpotAt(5,3)));
        assertTrue(blackPawn.canMove(board, board.getSpotAt(2,3)));
        // move forward twice
        assertTrue(whitePawn.canMove(board, board.getSpotAt(4,3)));
        assertTrue(blackPawn.canMove(board, board.getSpotAt(3,3)));
        // move forward twice with interference
        Piece whitePawn1 = new Pawn(true);
        Piece blackPawn1 = new Pawn(false);
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
        this.setUpBoard(board);
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
        this.setUpBoard(board);
        Piece whitePawn =  board.getSpotAt(6, 3).getPiece();
        Piece blackPawn = board.getSpotAt(1, 3).getPiece();

        Piece whiteCapturePawn = new Pawn(true);
        Piece blackCapturePawn = new Pawn(false);

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

    private void setUpBoard(Board board){
        // set up board with two pawns, a white pawn on pos 6,3 and black pawn on pos 1,3
        Piece whitePawn = new Pawn(true);
        Piece blackPawn = new Pawn(false);
        board.getSpotAt(6, 3).setPiece(whitePawn);
        board.getSpotAt(1, 3).setPiece(blackPawn);
    }
}
