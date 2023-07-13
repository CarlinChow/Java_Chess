package chess.domain.pieces;

import org.junit.Test;
import static org.junit.Assert.*;
import chess.domain.*;

public class KingTest {
    private void setUp(Board board){
        Piece blackKing = new King(false);
        Piece whiteKing = new King(true);
        board.getSpotAt("e8").setPiece(blackKing);
        board.getSpotAt("e1").setPiece(whiteKing);
        board.addPiece(blackKing);
        board.addPiece(whiteKing);
    }

    @Test
    public void TestLegalMoves(){
        Board board = new Board();
        this.setUp(board);
        Piece blackKing = board.getSpotAt("e8").getPiece();
        Piece whiteKing = board.getSpotAt("e1").getPiece();

        // black king
        assertTrue(blackKing.canMove(board, "d7"));
        assertTrue(blackKing.canMove(board, "e7"));
        assertTrue(blackKing.canMove(board, "f7"));
        assertTrue(blackKing.canMove(board, "d8"));
        assertTrue(blackKing.canMove(board, "f8"));

        // white king
        assertTrue(whiteKing.canMove(board, "e2"));
        assertTrue(whiteKing.canMove(board, "d2"));
        assertTrue(whiteKing.canMove(board, "f2"));
        assertTrue(whiteKing.canMove(board, "d1"));
        assertTrue(whiteKing.canMove(board, "f1"));

        // black king capture
        Piece pawn1 = new Pawn(true);
        Piece pawn2 = new Pawn(true);
        Piece pawn3 = new Pawn(true);


    }

    @Test
    public void TestIllegalMoves(){
        Board board = new Board();
        this.setUp(board);
        Piece blackKing = board.getSpotAt("e8").getPiece();
        Piece whiteKing = board.getSpotAt("e1").getPiece();
        Piece blackPawn = new Pawn(false);
        board.getSpotAt("e7").setPiece(blackPawn);
        board.addPiece(blackPawn);
        Piece whitePawn = new Pawn(true);
        board.getSpotAt("e2").setPiece(whitePawn);
        board.addPiece(whitePawn);
        board.print();
    }

    @Test
    public void TestCastling(){

    }

    @Test
    public void TestMovingToCheckedPosition(){

    }
}
