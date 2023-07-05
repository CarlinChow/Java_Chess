package chess.domain.pieces;

import chess.domain.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class BishopTest {
    private void setUp(Board board){
        board.getSpotAt(0,2).setPiece(new Bishop(false));
        board.getSpotAt(0,2).getPiece().setSpot(board.getSpotAt(0,2));
        board.getSpotAt(0,5).setPiece(new Bishop(false));
        board.getSpotAt(0,5).getPiece().setSpot(board.getSpotAt(0,5));
        board.getSpotAt(7,2).setPiece(new Bishop(true));
        board.getSpotAt(7,2).getPiece().setSpot(board.getSpotAt(7,2));
        board.getSpotAt(7,5).setPiece(new Bishop(true));
        board.getSpotAt(7,5).getPiece().setSpot(board.getSpotAt(7,5));
    }

    @Test
    public void testLegalMove(){
        Board board = new Board();
        this.setUp(board);
        Piece blackBishop1 = board.getSpotAt(0,2).getPiece();
        Piece blackBishop2 = board.getSpotAt(0,5).getPiece();
        Piece whiteBishop1 = board.getSpotAt(7,2).getPiece();
        Piece whiteBishop2 = board.getSpotAt(7,5).getPiece();

        for(int i = 1; i < 3; i++){
            assertTrue(blackBishop1.canMove(board, blackBishop1.getSpot(), board.getSpotAt(blackBishop1.getSpot().getRow() + i, blackBishop1.getSpot().getColumn() - i)));
        }
        for(int i = 1; i < 6; i++){
            assertTrue(blackBishop1.canMove(board, blackBishop1.getSpot(), board.getSpotAt(blackBishop1.getSpot().getRow() + i, blackBishop1.getSpot().getColumn() + i)));
        }

    }
}
