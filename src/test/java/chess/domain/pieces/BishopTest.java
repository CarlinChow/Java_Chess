package chess.domain.pieces;

import chess.domain.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Set;

public class BishopTest {
    private void setUp(Board board){
        board.clear();
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
            assertTrue(blackBishop1.canMove(board, board.getSpotAt(newRow, newCol)));
        }
        for(int i = 1; i < 6; i++){
            int newRow = blackBishop1.getSpot().getRow() + i;
            int newCol = blackBishop1.getSpot().getColumn() + i;
            assertTrue(blackBishop1.canMove(board, board.getSpotAt(newRow, newCol)));
        }
        // blackBishop 2 all possible moves
        for(int i = 1; i < 3; i++){
            int newRow = blackBishop2.getSpot().getRow() + i;
            int newCol = blackBishop2.getSpot().getColumn() + i;
            assertTrue(blackBishop2.canMove(board,  board.getSpotAt(newRow, newCol)));
        }
        for(int i = 1; i < 6; i++){
            int newRow = blackBishop2.getSpot().getRow() + i;
            int newCol = blackBishop2.getSpot().getColumn() - i;
            assertTrue(blackBishop2.canMove(board, board.getSpotAt(newRow, newCol)));
        }

        // whiteBishop 1 all possible moves
        for(int i = 1; i < 3; i++){
            int newRow = whiteBishop1.getSpot().getRow() - i;
            int newCol = whiteBishop1.getSpot().getColumn() - i;
            assertTrue(whiteBishop1.canMove(board, board.getSpotAt(newRow, newCol)));
        }
        for(int i = 1; i < 6; i++){
            int newRow = whiteBishop1.getSpot().getRow() - i;
            int newCol = whiteBishop1.getSpot().getColumn() + i;
            assertTrue(whiteBishop1.canMove(board, board.getSpotAt(newRow, newCol)));
        }
        // whiteBishop 2 all possible moves
        for(int i = 1; i < 3; i++){
            int newRow = whiteBishop2.getSpot().getRow() - i;
            int newCol = whiteBishop2.getSpot().getColumn() + i;
            assertTrue(whiteBishop2.canMove(board,  board.getSpotAt(newRow, newCol)));
        }
        for(int i = 1; i < 6; i++){
            int newRow = whiteBishop2.getSpot().getRow() - i;
            int newCol = whiteBishop2.getSpot().getColumn() - i;
            assertTrue(whiteBishop2.canMove(board,  board.getSpotAt(newRow, newCol)));
        }
        // blackBishop legal capture
        blackBishop1.getSpot().removePiece();
        board.getSpotAt(1,1).setPiece(blackBishop1);
        board.getSpotAt(7,7).setPiece(new Pawn(true));
        assertTrue(blackBishop1.canMove(board, board.getSpotAt(0,2)));
        assertTrue(blackBishop1.canMove(board, board.getSpotAt(7,7)));
        assertTrue(blackBishop1.canMove(board, board.getSpotAt(0,0)));
        assertTrue(blackBishop1.canMove(board, board.getSpotAt(4,4)));
        // whiteBishop legal capture
        board.getSpotAt(0,7).setPiece(new Pawn(false));
        whiteBishop1.getSpot().removePiece();
        board.getSpotAt(6,1).setPiece(whiteBishop1);
        assertTrue(whiteBishop1.canMove(board, board.getSpotAt(7,2)));
        assertTrue(whiteBishop1.canMove(board, board.getSpotAt(0,7)));
        assertTrue(whiteBishop1.canMove(board, board.getSpotAt(7,0)));
        assertTrue(whiteBishop1.canMove(board, board.getSpotAt(1,6)));
    }
    @Test
    public void testIllegalMove(){
        Board board = new Board();
        this.setUp(board);
        Piece blackBishop1 = board.getSpotAt(0,2).getPiece();
        Piece blackBishop2 = board.getSpotAt(0,5).getPiece();
        Piece whiteBishop1 = board.getSpotAt(7,2).getPiece();
        Piece whiteBishop2 = board.getSpotAt(7,5).getPiece();

        assertFalse(blackBishop1.canMove(board, board.getSpotAt(1,2)));
        assertFalse(blackBishop1.canMove(board, board.getSpotAt(4,2)));
        assertFalse(whiteBishop1.canMove(board, board.getSpotAt(5,2)));
        assertFalse(whiteBishop1.canMove(board, board.getSpotAt(3,3)));

        board.getSpotAt(3,2).setPiece(new Pawn(true));
        board.getSpotAt(4,2).setPiece(new Pawn(true));
        board.getSpotAt(3,5).setPiece(new Pawn(true));
        board.getSpotAt(4,5).setPiece(new Pawn(true));
        board.getSpotAt(1,1).setPiece(new Pawn(false));
        board.getSpotAt(1,6).setPiece(new Pawn(false));
        board.getSpotAt(6,1).setPiece(new Pawn(false));
        board.getSpotAt(6,6).setPiece(new Pawn(false));

        assertFalse(blackBishop1.canMove(board, board.getSpotAt(4,6)));
        assertFalse(blackBishop2.canMove(board, board.getSpotAt(4,1)));
        assertFalse(whiteBishop1.canMove(board, board.getSpotAt(3,6)));
        assertFalse(whiteBishop2.canMove(board, board.getSpotAt(3,1)));
        assertFalse(blackBishop1.canMove(board, board.getSpotAt(2,0)));
        assertFalse(blackBishop2.canMove(board, board.getSpotAt(2,7)));
        assertFalse(whiteBishop1.canMove(board, board.getSpotAt(5,0)));
        assertFalse(whiteBishop2.canMove(board, board.getSpotAt(5,7)));

        // flip pawn colour
        board.getSpotAt(3,2).setPiece(new Pawn(false));
        board.getSpotAt(4,2).setPiece(new Pawn(false));
        board.getSpotAt(3,5).setPiece(new Pawn(false));
        board.getSpotAt(4,5).setPiece(new Pawn(false));
        board.getSpotAt(1,1).setPiece(new Pawn(true));
        board.getSpotAt(1,6).setPiece(new Pawn(true));
        board.getSpotAt(6,1).setPiece(new Pawn(true));
        board.getSpotAt(6,6).setPiece(new Pawn(true));

        assertFalse(blackBishop1.canMove(board, board.getSpotAt(4,6)));
        assertFalse(blackBishop2.canMove(board, board.getSpotAt(4,1)));
        assertFalse(whiteBishop1.canMove(board, board.getSpotAt(3,6)));
        assertFalse(whiteBishop2.canMove(board, board.getSpotAt(3,1)));
        assertFalse(blackBishop1.canMove(board, board.getSpotAt(2,0)));
        assertFalse(blackBishop2.canMove(board, board.getSpotAt(2,7)));
        assertFalse(whiteBishop1.canMove(board, board.getSpotAt(5,0)));
        assertFalse(whiteBishop2.canMove(board, board.getSpotAt(5,7)));
    }

    @Test
    public void testGetMoves(){
        Board board = new Board();
        this.setUp(board);
        Piece whiteQueenBishop = board.getSpotAt("c1").getPiece();
        Piece whiteKingBishop = board.getSpotAt("f1").getPiece();
        Piece blackQueenBishop = board.getSpotAt("c8").getPiece();
        Piece blackKingBishop = board.getSpotAt("f8").getPiece();
        board.getSpotAt("a3").setPiece(new Pawn(false));
        board.getSpotAt("h6").setPiece(new Pawn(true));
        board.getSpotAt("a6").setPiece(new Pawn(false));
        board.getSpotAt("h3").setPiece(new Pawn(true));

        Set<Spot> whiteQueenBishopMoves = whiteQueenBishop.getMoves(board);
        Set<Spot> whiteKingBishopMoves = whiteKingBishop.getMoves(board);
        Set<Spot> blackQueenBishopMoves = blackQueenBishop.getMoves(board);
        Set<Spot> blackKingBishopMoves = blackKingBishop.getMoves(board);

        assertTrue(whiteQueenBishopMoves.contains(board.getSpotAt("b2")));
        assertTrue(whiteQueenBishopMoves.contains(board.getSpotAt("a3")));
        assertTrue(whiteQueenBishopMoves.contains(board.getSpotAt("d2")));
        assertTrue(whiteQueenBishopMoves.contains(board.getSpotAt("e3")));
        assertTrue(whiteQueenBishopMoves.contains(board.getSpotAt("f4")));
        assertTrue(whiteQueenBishopMoves.contains(board.getSpotAt("g5")));
        assertFalse(whiteQueenBishopMoves.contains(board.getSpotAt("h6")));
        assertEquals(6, whiteKingBishopMoves.size());

        assertTrue(whiteKingBishopMoves.contains(board.getSpotAt("e2")));
        assertTrue(whiteKingBishopMoves.contains(board.getSpotAt("d3")));
        assertTrue(whiteKingBishopMoves.contains(board.getSpotAt("c4")));
        assertTrue(whiteKingBishopMoves.contains(board.getSpotAt("b5")));
        assertTrue(whiteKingBishopMoves.contains(board.getSpotAt("a6")));
        assertTrue(whiteKingBishopMoves.contains(board.getSpotAt("g2")));
        assertFalse(whiteKingBishopMoves.contains(board.getSpotAt("h3")));
        assertEquals(6, whiteKingBishopMoves.size());

        assertTrue(blackQueenBishopMoves.contains(board.getSpotAt("b7")));
        assertTrue(blackQueenBishopMoves.contains(board.getSpotAt("d7")));
        assertTrue(blackQueenBishopMoves.contains(board.getSpotAt("e6")));
        assertTrue(blackQueenBishopMoves.contains(board.getSpotAt("f5")));
        assertTrue(blackQueenBishopMoves.contains(board.getSpotAt("g4")));
        assertTrue(blackQueenBishopMoves.contains(board.getSpotAt("h3")));
        assertFalse(blackQueenBishopMoves.contains(board.getSpotAt("h6")));
        assertEquals(6, blackQueenBishopMoves.size());

        assertTrue(blackKingBishopMoves.contains(board.getSpotAt("e7")));
        assertTrue(blackKingBishopMoves.contains(board.getSpotAt("d6")));
        assertTrue(blackKingBishopMoves.contains(board.getSpotAt("c5")));
        assertTrue(blackKingBishopMoves.contains(board.getSpotAt("b4")));
        assertTrue(blackKingBishopMoves.contains(board.getSpotAt("g7")));
        assertTrue(blackKingBishopMoves.contains(board.getSpotAt("h6")));
        assertFalse(blackKingBishopMoves.contains(board.getSpotAt("a3")));
        assertEquals(6, blackKingBishopMoves.size());

        // adding interference and potential capture moves
        board.getSpotAt("d3").setPiece(new Pawn(false));
        board.getSpotAt("e3").setPiece(new Pawn(true));
        board.getSpotAt("d6").setPiece(new Pawn(false));
        board.getSpotAt("e6").setPiece(new Pawn(true));

        whiteQueenBishopMoves = whiteQueenBishop.getMoves(board);
        whiteKingBishopMoves = whiteKingBishop.getMoves(board);
        blackQueenBishopMoves = blackQueenBishop.getMoves(board);
        blackKingBishopMoves = blackKingBishop.getMoves(board);

        assertTrue(whiteQueenBishopMoves.contains(board.getSpotAt("b2")));
        assertTrue(whiteQueenBishopMoves.contains(board.getSpotAt("a3")));
        assertTrue(whiteQueenBishopMoves.contains(board.getSpotAt("d2")));
        assertFalse(whiteQueenBishopMoves.contains(board.getSpotAt("f4")));
        assertEquals(3, whiteQueenBishopMoves.size());

        assertTrue(whiteKingBishopMoves.contains(board.getSpotAt("e2")));
        assertTrue(whiteKingBishopMoves.contains(board.getSpotAt("g2")));
        assertTrue(whiteKingBishopMoves.contains(board.getSpotAt("d3")));
        assertFalse(whiteKingBishopMoves.contains(board.getSpotAt("c4")));
        assertEquals(3, whiteKingBishopMoves.size());

        assertTrue(blackQueenBishopMoves.contains(board.getSpotAt("b7")));
        assertTrue(blackQueenBishopMoves.contains(board.getSpotAt("d7")));
        assertTrue(blackQueenBishopMoves.contains(board.getSpotAt("e6")));
        assertFalse(blackQueenBishopMoves.contains(board.getSpotAt("f5")));
        assertEquals(3, blackQueenBishopMoves.size());

        assertTrue(blackKingBishopMoves.contains(board.getSpotAt("e7")));
        assertTrue(blackKingBishopMoves.contains(board.getSpotAt("g7")));
        assertTrue(blackKingBishopMoves.contains(board.getSpotAt("h6")));
        assertFalse(blackKingBishopMoves.contains(board.getSpotAt("c5")));
        assertEquals(3, blackKingBishopMoves.size());
    }
}
