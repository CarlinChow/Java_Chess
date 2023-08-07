package chess.domain.pieces;

import chess.domain.*;
import org.junit.Test;

import static chess.types.Color.*;
import static org.junit.Assert.*;
import java.util.Set;

public class QueenTest {
    private void setUp(Board board){
        // set up queens in default position on an empty board
        board.clear();
        Piece whiteQueen = new Queen(WHITE);
        Piece blackQueen = new Queen(BLACK);
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
        board.getSpotAt(3,0).setPiece(new Pawn(WHITE));
        board.getSpotAt(4,7).setPiece(new Pawn(WHITE));
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
        board.getSpotAt(0,0).setPiece(new Pawn(WHITE));
        for(int i = 1; i < 4; i++){
            int newCol = blackQueen.getSpot().getColumn() - i;
            assertTrue(blackQueen.canMove(board, board.getSpotAt(0,newCol)));
        }
        board.getSpotAt(3,0).removePiece();
        // blackQueen right moves
        board.getSpotAt(0,7).setPiece(new Pawn(WHITE));
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
        board.getSpotAt(4,0).setPiece(new Pawn(BLACK));
        board.getSpotAt(3,7).setPiece(new Pawn(BLACK));
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
        board.getSpotAt(7,0).setPiece(new Pawn(BLACK));
        for(int i = 1; i < 4; i++){
            int newCol = whiteQueen.getSpot().getColumn() - i;
            assertTrue(whiteQueen.canMove(board, board.getSpotAt(7,newCol)));
        }
        board.getSpotAt(7,0).removePiece();
        // blackQueen right moves
        board.getSpotAt(7,7).setPiece(new Pawn(BLACK));
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
        board.getSpotAt(0,0).setPiece(new Pawn(BLACK));
        board.getSpotAt(0,7).setPiece(new Pawn(BLACK));
        board.getSpotAt(3,0).setPiece(new Pawn(BLACK));
        board.getSpotAt(4,7).setPiece(new Pawn(BLACK));
        board.getSpotAt(7,0).setPiece(new Pawn(WHITE));
        board.getSpotAt(7,7).setPiece(new Pawn(WHITE));
        board.getSpotAt(4,0).setPiece(new Pawn(WHITE));
        board.getSpotAt(3,7).setPiece(new Pawn(WHITE));
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
        board.getSpotAt(0,1).setPiece(new Pawn(BLACK));
        board.getSpotAt(0,6).setPiece(new Pawn(WHITE));
        board.getSpotAt(2,1).setPiece(new Pawn(BLACK));
        board.getSpotAt(3,6).setPiece(new Pawn(WHITE));
        board.getSpotAt(7,1).setPiece(new Pawn(BLACK));
        board.getSpotAt(7,6).setPiece(new Pawn(WHITE));
        board.getSpotAt(5,1).setPiece(new Pawn(BLACK));
        board.getSpotAt(4,6).setPiece(new Pawn(WHITE));
        assertFalse(blackQueen.canMove(board, board.getSpotAt(0,0)));
        assertFalse(blackQueen.canMove(board, board.getSpotAt(0,7)));
        assertFalse(blackQueen.canMove(board, board.getSpotAt(3,0)));
        assertFalse(blackQueen.canMove(board, board.getSpotAt(4,7)));
        assertFalse(whiteQueen.canMove(board, board.getSpotAt(7,0)));
        assertFalse(whiteQueen.canMove(board, board.getSpotAt(7,7)));
        assertFalse(whiteQueen.canMove(board, board.getSpotAt(4,0)));
        assertFalse(whiteQueen.canMove(board, board.getSpotAt(3,7)));
    }

    @Test
    public void testGetMoves(){
        Board board = new Board();
        this.setUp(board);
        Piece blackQueen = board.getSpotAt("d8").getPiece();
        Piece whiteQueen = board.getSpotAt("d1").getPiece();
        Set<Spot> blackQueenMoves = blackQueen.getMoves(board);
        Set<Spot> whiteQueenMoves = whiteQueen.getMoves(board);
        // black queen
        assertTrue(blackQueenMoves.contains(board.getSpotAt("c8")));
        assertTrue(blackQueenMoves.contains(board.getSpotAt("b8")));
        assertTrue(blackQueenMoves.contains(board.getSpotAt("a8")));
        assertTrue(blackQueenMoves.contains(board.getSpotAt("c7")));
        assertTrue(blackQueenMoves.contains(board.getSpotAt("b6")));
        assertTrue(blackQueenMoves.contains(board.getSpotAt("a5")));
        assertTrue(blackQueenMoves.contains(board.getSpotAt("d7")));
        assertTrue(blackQueenMoves.contains(board.getSpotAt("d6")));
        assertTrue(blackQueenMoves.contains(board.getSpotAt("d5")));
        assertTrue(blackQueenMoves.contains(board.getSpotAt("d4")));
        assertTrue(blackQueenMoves.contains(board.getSpotAt("d3")));
        assertTrue(blackQueenMoves.contains(board.getSpotAt("d2")));
        assertTrue(blackQueenMoves.contains(board.getSpotAt("d1")));
        assertTrue(blackQueenMoves.contains(board.getSpotAt("e7")));
        assertTrue(blackQueenMoves.contains(board.getSpotAt("f6")));
        assertTrue(blackQueenMoves.contains(board.getSpotAt("g5")));
        assertTrue(blackQueenMoves.contains(board.getSpotAt("h4")));
        assertTrue(blackQueenMoves.contains(board.getSpotAt("e8")));
        assertTrue(blackQueenMoves.contains(board.getSpotAt("f8")));
        assertTrue(blackQueenMoves.contains(board.getSpotAt("g8")));
        assertTrue(blackQueenMoves.contains(board.getSpotAt("h8")));
        assertEquals(21, blackQueenMoves.size());
        // white queen
        assertTrue(whiteQueenMoves.contains(board.getSpotAt("c1")));
        assertTrue(whiteQueenMoves.contains(board.getSpotAt("b1")));
        assertTrue(whiteQueenMoves.contains(board.getSpotAt("a1")));
        assertTrue(whiteQueenMoves.contains(board.getSpotAt("c2")));
        assertTrue(whiteQueenMoves.contains(board.getSpotAt("b3")));
        assertTrue(whiteQueenMoves.contains(board.getSpotAt("a4")));
        assertTrue(whiteQueenMoves.contains(board.getSpotAt("d2")));
        assertTrue(whiteQueenMoves.contains(board.getSpotAt("d3")));
        assertTrue(whiteQueenMoves.contains(board.getSpotAt("d4")));
        assertTrue(whiteQueenMoves.contains(board.getSpotAt("d5")));
        assertTrue(whiteQueenMoves.contains(board.getSpotAt("d6")));
        assertTrue(whiteQueenMoves.contains(board.getSpotAt("d7")));
        assertTrue(whiteQueenMoves.contains(board.getSpotAt("d8")));
        assertTrue(whiteQueenMoves.contains(board.getSpotAt("e2")));
        assertTrue(whiteQueenMoves.contains(board.getSpotAt("f3")));
        assertTrue(whiteQueenMoves.contains(board.getSpotAt("g4")));
        assertTrue(whiteQueenMoves.contains(board.getSpotAt("h5")));
        assertTrue(whiteQueenMoves.contains(board.getSpotAt("e1")));
        assertTrue(whiteQueenMoves.contains(board.getSpotAt("f1")));
        assertTrue(whiteQueenMoves.contains(board.getSpotAt("g1")));
        assertTrue(whiteQueenMoves.contains(board.getSpotAt("h1")));
        assertEquals(21, whiteQueenMoves.size());

        // adding interference
        board.getSpotAt("b8").setPiece(new Pawn(BLACK));
        board.getSpotAt("a5").setPiece(new Pawn(BLACK));
        board.getSpotAt("d5").setPiece(new Pawn(BLACK));
        board.getSpotAt("f6").setPiece(new Pawn(WHITE));
        board.getSpotAt("g8").setPiece(new Pawn(WHITE));
        board.getSpotAt("a4").setPiece(new Pawn(WHITE));
        board.getSpotAt("b1").setPiece(new Pawn(WHITE));
        board.getSpotAt("d4").setPiece(new Pawn(WHITE));
        board.getSpotAt("f3").setPiece(new Pawn(BLACK));
        board.getSpotAt("g1").setPiece(new Pawn(BLACK));

        blackQueenMoves = blackQueen.getMoves(board);
        whiteQueenMoves = whiteQueen.getMoves(board);

        // black queen
        assertTrue(blackQueenMoves.contains(board.getSpotAt("c8")));
        assertTrue(blackQueenMoves.contains(board.getSpotAt("c7")));
        assertTrue(blackQueenMoves.contains(board.getSpotAt("b6")));
        assertTrue(blackQueenMoves.contains(board.getSpotAt("d7")));
        assertTrue(blackQueenMoves.contains(board.getSpotAt("d6")));
        assertTrue(blackQueenMoves.contains(board.getSpotAt("e7")));
        assertTrue(blackQueenMoves.contains(board.getSpotAt("f6")));
        assertTrue(blackQueenMoves.contains(board.getSpotAt("e8")));
        assertTrue(blackQueenMoves.contains(board.getSpotAt("f8")));
        assertTrue(blackQueenMoves.contains(board.getSpotAt("g8")));
        assertEquals(10, blackQueenMoves.size());
        // white queen
        assertTrue(whiteQueenMoves.contains(board.getSpotAt("c1")));
        assertTrue(whiteQueenMoves.contains(board.getSpotAt("c2")));
        assertTrue(whiteQueenMoves.contains(board.getSpotAt("b3")));
        assertTrue(whiteQueenMoves.contains(board.getSpotAt("d2")));
        assertTrue(whiteQueenMoves.contains(board.getSpotAt("d3")));
        assertTrue(whiteQueenMoves.contains(board.getSpotAt("e2")));
        assertTrue(whiteQueenMoves.contains(board.getSpotAt("f3")));
        assertTrue(whiteQueenMoves.contains(board.getSpotAt("e1")));
        assertTrue(whiteQueenMoves.contains(board.getSpotAt("f1")));
        assertTrue(whiteQueenMoves.contains(board.getSpotAt("g1")));
        assertEquals(10, whiteQueenMoves.size());
    }
}
