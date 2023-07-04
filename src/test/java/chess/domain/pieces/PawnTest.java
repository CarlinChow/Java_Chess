package chess.domain.pieces;

import org.junit.Test;
import static org.junit.Assert.*;
import chess.domain.*;


public class PawnTest {
    @Test
    public void testForwardMove(){
        Board board = new Board();
        this.setUpBoard(board);
        Piece whitePawn =  board.getSpot(6, 3).getPiece();
        Piece blackPawn = board.getSpot(1, 3).getPiece();
        // move forward one spot
        assertTrue(whitePawn.canMove(board,board.getSpot(6,3),board.getSpot(5,3)));
        assertTrue(blackPawn.canMove(board,board.getSpot(1,3),board.getSpot(2,3)));
        // move forward twice
        assertTrue(whitePawn.canMove(board,board.getSpot(6,3),board.getSpot(4,3)));
        assertTrue(blackPawn.canMove(board,board.getSpot(1,3),board.getSpot(3,3)));
        // move forward twice with interference
        Piece whitePawn1 = new Pawn(true);
        Piece blackPawn1 = new Pawn(false);
        board.getSpot(5, 3).setPiece(whitePawn1);
        board.getSpot(2, 3).setPiece(blackPawn1);
        assertEquals(false, whitePawn.canMove(board,board.getSpot(6,3),board.getSpot(4,3)));
        assertEquals(false, blackPawn.canMove(board,board.getSpot(1,3),board.getSpot(3,3)));
        assertEquals(false, whitePawn.canMove(board,board.getSpot(6,3),board.getSpot(5,3)));
        assertEquals(false, blackPawn.canMove(board,board.getSpot(1,3),board.getSpot(2,3)));
    }

    @Test
    public void testIllegalMove(){
        Board board = new Board();
        this.setUpBoard(board);
        Piece whitePawn =  board.getSpot(6, 3).getPiece();
        Piece blackPawn = board.getSpot(1, 3).getPiece();

        // white pawn starting position on 6, 3
        assertEquals(false, whitePawn.canMove(board, board.getSpot(6,3), board.getSpot(3, 3)));
        assertEquals(false, whitePawn.canMove(board, board.getSpot(6,3), board.getSpot(5, 4)));
        assertEquals(false, whitePawn.canMove(board, board.getSpot(6,3), board.getSpot(5, 2)));
        assertEquals(false, whitePawn.canMove(board, board.getSpot(6,3), board.getSpot(0, 0)));
        assertEquals(false, whitePawn.canMove(board, board.getSpot(6,3), board.getSpot(7, 3)));

        // black pawn starting position on 1, 3
        assertEquals(false, blackPawn.canMove(board, board.getSpot(1,3), board.getSpot(4,3)));
        assertEquals(false, blackPawn.canMove(board, board.getSpot(1,3), board.getSpot(2,2)));
        assertEquals(false, blackPawn.canMove(board, board.getSpot(1,3), board.getSpot(2,1)));
        assertEquals(false, blackPawn.canMove(board, board.getSpot(1,3), board.getSpot(0, 3)));

    }

    private void setUpBoard(Board board){
        // set up board with two pawns, a white pawn on pos 6,3 and black pawn on pos 1,3
        Piece whitePawn = new Pawn(true);
        Piece blackPawn = new Pawn(false);
        board.getSpot(6, 3).setPiece(whitePawn);
        board.getSpot(1, 3).setPiece(blackPawn);
    }
}
