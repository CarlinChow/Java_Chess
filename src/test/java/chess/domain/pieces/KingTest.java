package chess.domain.pieces;

import chess.logic.*;
import org.junit.Test;

import static chess.types.Color.*;
import static org.junit.Assert.*;
import chess.domain.*;
import java.util.Arrays;
import java.util.Set;

public class KingTest {
    private void setUp(Board board){
        board.clear();
        Piece blackKing = new King(BLACK);
        Piece whiteKing = new King(WHITE);
        board.getSpotAt("e8").setPiece(blackKing);
        board.getSpotAt("e1").setPiece(whiteKing);
        board.addPiece(blackKing);
        board.addPiece(whiteKing);
    }

    @Test
    public void TestLegalMoves() {
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
    }
    @Test
    public void TestCapture(){
        Board board = new Board();
        this.setUp(board);
        Piece blackKing = board.getSpotAt("e8").getPiece();
        Piece whiteKing = board.getSpotAt("e1").getPiece();

        // black king
        Piece pawn1 = new Pawn(WHITE);
        Piece pawn2 = new Pawn(WHITE);
        Piece pawn3 = new Pawn(WHITE);
        Piece pawn4 = new Pawn(WHITE);
        Piece pawn5 = new Pawn(WHITE);
        board.getSpotAt("d8").setPiece(pawn1);
        board.getSpotAt("f8").setPiece(pawn2);
        board.getSpotAt("d7").setPiece(pawn3);
        board.getSpotAt("f7").setPiece(pawn4);
        board.getSpotAt("e7").setPiece(pawn5);
        board.addAllPieces(Arrays.asList(pawn1,pawn2,pawn3,pawn4,pawn5));
        assertTrue(blackKing.canMove(board, "d7"));
        assertTrue(blackKing.canMove(board, "e7"));
        assertTrue(blackKing.canMove(board, "f7"));
        assertFalse(blackKing.canMove(board, "d8"));
        assertFalse(blackKing.canMove(board, "f8"));

        // white king
        pawn1 = new Pawn(BLACK);
        pawn2 = new Pawn(BLACK);
        pawn3 = new Pawn(BLACK);
        pawn4 = new Pawn(BLACK);
        pawn5 = new Pawn(BLACK);
        board.getSpotAt("d2").setPiece(pawn1);
        board.getSpotAt("e2").setPiece(pawn2);
        board.getSpotAt("f2").setPiece(pawn3);
        board.getSpotAt("f1").setPiece(pawn4);
        board.getSpotAt("d1").setPiece(pawn5);
        board.addAllPieces(Arrays.asList(pawn1,pawn2,pawn3,pawn4,pawn5));
        assertTrue(whiteKing.canMove(board, "e2"));
        assertTrue(whiteKing.canMove(board, "d2"));
        assertTrue(whiteKing.canMove(board, "f2"));
        assertFalse(whiteKing.canMove(board, "d1"));
        assertFalse(whiteKing.canMove(board, "f1"));
    }

    @Test
    public void TestIllegalCapture(){
        Board board = new Board();
        this.setUp(board);
        Piece blackKing = board.getSpotAt("e8").getPiece();
        Piece whiteKing = board.getSpotAt("e1").getPiece();

        // black king
        Piece pawn1 = new Pawn(BLACK);
        Piece pawn2 = new Pawn(BLACK);
        Piece pawn3 = new Pawn(BLACK);
        Piece pawn4 = new Pawn(BLACK);
        Piece pawn5 = new Pawn(BLACK);
        board.getSpotAt("d8").setPiece(pawn1);
        board.getSpotAt("f8").setPiece(pawn2);
        board.getSpotAt("d7").setPiece(pawn3);
        board.getSpotAt("f7").setPiece(pawn4);
        board.getSpotAt("e7").setPiece(pawn5);
        board.addAllPieces(Arrays.asList(pawn1,pawn2,pawn3,pawn4,pawn5));
        assertFalse(blackKing.canMove(board, "d7"));
        assertFalse(blackKing.canMove(board, "e7"));
        assertFalse(blackKing.canMove(board, "f7"));
        assertFalse(blackKing.canMove(board, "d8"));
        assertFalse(blackKing.canMove(board, "f8"));

        // white king
        pawn1 = new Pawn(WHITE);
        pawn2 = new Pawn(WHITE);
        pawn3 = new Pawn(WHITE);
        pawn4 = new Pawn(WHITE);
        pawn5 = new Pawn(WHITE);
        board.getSpotAt("e2").setPiece(pawn1);
        board.getSpotAt("d2").setPiece(pawn2);
        board.getSpotAt("f2").setPiece(pawn3);
        board.getSpotAt("d1").setPiece(pawn4);
        board.getSpotAt("f1").setPiece(pawn5);
        board.addAllPieces(Arrays.asList(pawn1,pawn2,pawn3,pawn4,pawn5));
        assertFalse(whiteKing.canMove(board, "e2"));
        assertFalse(whiteKing.canMove(board, "d2"));
        assertFalse(whiteKing.canMove(board, "f2"));
        assertFalse(whiteKing.canMove(board, "d1"));
        assertFalse(whiteKing.canMove(board, "f1"));
    }

    @Test
    public void TestIllegalMoves(){
        Board board = new Board();
        this.setUp(board);
        Piece blackKing = board.getSpotAt("e8").getPiece();
        Piece whiteKing = board.getSpotAt("e1").getPiece();

        assertFalse(blackKing.canMove(board, "e2"));
        assertFalse(blackKing.canMove(board, "a8"));
        assertFalse(blackKing.canMove(board, "a1"));
        assertFalse(blackKing.canMove(board, "d6"));
        assertFalse(blackKing.canMove(board, "c6"));
        assertFalse(blackKing.canMove(board, "e6"));
        assertFalse(blackKing.canMove(board, "h5"));

        assertFalse(whiteKing.canMove(board, "e7"));
        assertFalse(whiteKing.canMove(board, "a8"));
        assertFalse(whiteKing.canMove(board, "a1"));
        assertFalse(whiteKing.canMove(board, "h1"));
        assertFalse(whiteKing.canMove(board, "f3"));
        assertFalse(whiteKing.canMove(board, "g3"));
        assertFalse(whiteKing.canMove(board, "e3"));
    }

    @Test
    public void TestIsChecked(){
        Board board = new Board();
        this.setUp(board);
        Piece blackKing = board.getSpotAt("e8").getPiece();
        Piece whiteKing = board.getSpotAt("e1").getPiece();

        Piece rook1 = new Rook(WHITE);
        Piece rook2 = new Rook(WHITE);
        board.addAllPieces(Arrays.asList(rook1,rook2));
        board.getSpotAt("d1").setPiece(rook1);
        board.getSpotAt("f1").setPiece(rook2);

        // black king moving into checked position
        assertFalse(blackKing.canMove(board, "d8"));
        assertFalse(blackKing.canMove(board, "f8"));
        assertFalse(blackKing.canMove(board, "d7"));
        assertFalse(blackKing.canMove(board, "f7"));
        assertTrue(blackKing.canMove(board, "e7"));
        rook1.setCaptured(true);
        rook2.setCaptured(true);
        rook1.getSpot().removePiece();
        rook2.getSpot().removePiece();
        assertTrue(blackKing.canMove(board, "d7"));
        assertTrue(blackKing.canMove(board, "e7"));
        assertTrue(blackKing.canMove(board, "f7"));
        assertTrue(blackKing.canMove(board, "d8"));
        assertTrue(blackKing.canMove(board, "f8"));

        Piece pawn1 = new Pawn(WHITE);
        board.addPiece(pawn1);
        board.getSpotAt("e7").setPiece(pawn1);

        assertFalse(blackKing.canMove(board, "d8"));
        assertFalse(blackKing.canMove(board, "f8"));
        assertTrue(blackKing.canMove(board, "e7"));
        assertTrue(blackKing.canMove(board, "d7"));
        assertTrue(blackKing.canMove(board, "f7"));

        Piece pawn2 = new Pawn(WHITE);
        board.addPiece(pawn2);
        board.getSpotAt("e6").setPiece(pawn2);
        assertFalse(blackKing.canMove(board, "d8"));
        assertFalse(blackKing.canMove(board, "f8"));
        assertTrue(blackKing.canMove(board, "e7"));
        assertFalse(blackKing.canMove(board, "d7"));
        assertFalse(blackKing.canMove(board, "f7"));

        Piece rook3 = new Rook(WHITE);
        board.getSpotAt("a7").setPiece(rook3);
        board.addPiece(rook3);
        assertFalse(blackKing.canMove(board, "e7"));
        assertFalse(blackKing.canMove(board, "d7"));
        board.getSpotAt("e6").removePiece();
        pawn2.setCaptured(true);
        assertTrue(blackKing.canMove(board, "f7"));

        Piece queen = new Queen(BLACK);
        Piece knight = new Knight(BLACK);
        Piece bishop = new Bishop(BLACK);
        board.getSpotAt("c2").setPiece(queen);
        board.getSpotAt("g3").setPiece(knight);
        board.getSpotAt("f2").setPiece(bishop);
        board.addAllPieces(Arrays.asList(queen, knight, bishop));
        if(whiteKing instanceof King k){
            assertTrue(k.isCurrentlyInCheck(board));
        }
        assertFalse(whiteKing.canMove(board, "d1"));
        assertFalse(whiteKing.canMove(board, "d2"));
        assertFalse(whiteKing.canMove(board, "e2"));
        assertFalse(whiteKing.canMove(board, "f2"));
        assertFalse(whiteKing.canMove(board, "f1"));

        queen.getSpot().removePiece().setCaptured(true);
        assertTrue(whiteKing.canMove(board, "f2"));
        assertFalse(whiteKing.canMove(board, "f1"));
    }

    @Test
    public void TestCastling(){
        Board board = new Board();
        this.setUp(board);
        MoveList moveList = new MoveList();
        King whiteKing = (King) board.getSpotAt("e1").getPiece();
        King blackKing = (King) board.getSpotAt("e8").getPiece();

        // rooks not at default pos
        assertFalse(blackKing.canCastle(board, "c8", moveList));
        assertFalse(blackKing.canCastle(board, "g8", moveList));
        assertFalse(whiteKing.canCastle(board, "c1", moveList));
        assertFalse(whiteKing.canCastle(board, "g1", moveList));

        Piece blackQueenSideRook = new Rook(BLACK);
        Piece blackKingSideRook = new Rook(BLACK);
        Piece whiteQueenSideRook = new Rook(WHITE);
        Piece whiteKingSideRook = new Rook(WHITE);
        board.getSpotAt("a8").setPiece(blackQueenSideRook);
        board.getSpotAt("h8").setPiece(blackKingSideRook);
        board.getSpotAt("a1").setPiece(whiteQueenSideRook);
        board.getSpotAt("h1").setPiece(whiteKingSideRook);
        board.addPiece(blackQueenSideRook);
        board.addPiece(blackKingSideRook);
        board.addPiece(whiteQueenSideRook);
        board.addPiece(whiteKingSideRook);

        // legal castle
        assertTrue(blackKing.canCastle(board, "c8", moveList));
        assertTrue(blackKing.canCastle(board, "g8", moveList));
        assertTrue(whiteKing.canCastle(board, "c1", moveList));
        assertTrue(whiteKing.canCastle(board, "g1", moveList));

        // non castle moves
        assertFalse(blackKing.canCastle(board, "b8", moveList));
        assertFalse(blackKing.canCastle(board, "d8", moveList));
        assertFalse(blackKing.canCastle(board, "f8", moveList));
        assertFalse(blackKing.canCastle(board, "h8", moveList));
        assertFalse(whiteKing.canCastle(board, "b1", moveList));
        assertFalse(whiteKing.canCastle(board, "d1", moveList));
        assertFalse(whiteKing.canCastle(board, "f1", moveList));
        assertFalse(whiteKing.canCastle(board, "h1", moveList));

        // castling after rook movement
        moveList.add(new Move(blackQueenSideRook, board.getSpotAt("a5")));
        moveList.add(new Move(blackQueenSideRook, board.getSpotAt("a8")));
        moveList.add(new Move(whiteKingSideRook, board.getSpotAt("h6")));
        moveList.add(new Move(whiteKingSideRook, board.getSpotAt("h1")));
        assertFalse(blackKing.canCastle(board, "c8", moveList));
        assertTrue(blackKing.canCastle(board, "g8", moveList));
        assertFalse(whiteKing.canCastle(board, "g1", moveList));
        assertTrue(whiteKing.canCastle(board, "c1", moveList));

        // castling after king movement
        moveList.add(new Move(blackKing, board.getSpotAt("e7")));
        moveList.add(new Move(blackKing, board.getSpotAt("e8")));
        moveList.add(new Move(whiteKing, board.getSpotAt("d2")));
        moveList.add(new Move(whiteKing, board.getSpotAt("e1")));
        assertFalse(blackKing.canCastle(board, "g8", moveList));
        assertFalse(whiteKing.canCastle(board, "c1", moveList));

        // castling w/ interference
        board.reset();
        moveList = new MoveList();
        blackKing = (King) board.getSpotAt("e8").getPiece();
        whiteKing = (King) board.getSpotAt("e1").getPiece();
        assertFalse(blackKing.canCastle(board, "c8", moveList));
        assertFalse(blackKing.canCastle(board, "g8", moveList));
        assertFalse(whiteKing.canCastle(board, "c1", moveList));
        assertFalse(whiteKing.canCastle(board, "g1", moveList));
        // remove queens and king side bishop
        board.getSpotAt("d8").getPiece().setCaptured(true);
        board.getSpotAt("d8").removePiece();
        board.getSpotAt("d1").getPiece().setCaptured(true);
        board.getSpotAt("d1").removePiece();
        board.getSpotAt("f8").getPiece().setCaptured(true);
        board.getSpotAt("f8").removePiece();
        board.getSpotAt("f1").getPiece().setCaptured(true);
        board.getSpotAt("f1").removePiece();
        assertFalse(blackKing.canCastle(board, "c8", moveList));
        assertFalse(blackKing.canCastle(board, "g8", moveList));
        assertFalse(whiteKing.canCastle(board, "c1", moveList));
        assertFalse(whiteKing.canCastle(board, "g1", moveList));
        // remove queen side bishop
        board.getSpotAt("c8").getPiece().setCaptured(true);
        board.getSpotAt("c8").removePiece();
        board.getSpotAt("c1").getPiece().setCaptured(true);
        board.getSpotAt("c1").removePiece();
        assertFalse(blackKing.canCastle(board, "c8", moveList));
        assertFalse(whiteKing.canCastle(board, "c1", moveList));
        // remove knights
        board.getSpotAt("b8").getPiece().setCaptured(true);
        board.getSpotAt("b8").removePiece();
        board.getSpotAt("g8").getPiece().setCaptured(true);
        board.getSpotAt("g8").removePiece();
        board.getSpotAt("b1").getPiece().setCaptured(true);
        board.getSpotAt("b1").removePiece();
        board.getSpotAt("g1").getPiece().setCaptured(true);
        board.getSpotAt("g1").removePiece();
        assertTrue(blackKing.canCastle(board, "c8", moveList));
        assertTrue(blackKing.canCastle(board, "g8", moveList));
        assertTrue(whiteKing.canCastle(board, "c1", moveList));
        assertTrue(whiteKing.canCastle(board, "g1", moveList));
    }

    @Test
    public void testGetMoves(){
        Board board = new Board();
        this.setUp(board);
        Piece blackKing = board.getSpotAt("e8").getPiece();
        Piece whiteKing = board.getSpotAt("e1").getPiece();
        Set<Spot> blackKingMoves = blackKing.getMoves(board);
        Set<Spot> whiteKingMoves = whiteKing.getMoves(board);

        assertTrue(blackKingMoves.contains(board.getSpotAt("d8")));
        assertTrue(blackKingMoves.contains(board.getSpotAt("d7")));
        assertTrue(blackKingMoves.contains(board.getSpotAt("e7")));
        assertTrue(blackKingMoves.contains(board.getSpotAt("f7")));
        assertTrue(blackKingMoves.contains(board.getSpotAt("f8")));
        assertEquals(5, blackKingMoves.size());
        assertTrue(whiteKingMoves.contains(board.getSpotAt("d1")));
        assertTrue(whiteKingMoves.contains(board.getSpotAt("d2")));
        assertTrue(whiteKingMoves.contains(board.getSpotAt("e2")));
        assertTrue(whiteKingMoves.contains(board.getSpotAt("f2")));
        assertTrue(whiteKingMoves.contains(board.getSpotAt("f1")));
        assertEquals(5, whiteKingMoves.size());

        // adding interference
        Piece whitePawn = new Pawn(WHITE);
        Piece blackPawn = new Pawn(BLACK);
        Piece whiteKnight = new Knight(WHITE);
        Piece blackKnight = new Knight(BLACK);
        board.getSpotAt("g6").setPiece(whitePawn);
        board.getSpotAt("g3").setPiece(blackPawn);
        board.getSpotAt("c6").setPiece(whiteKnight);
        board.getSpotAt("c3").setPiece(blackKnight);
        board.addAllPieces(Arrays.asList(whitePawn,blackPawn,whiteKnight,blackKnight));
        blackKingMoves = blackKing.getMoves(board);
        whiteKingMoves = whiteKing.getMoves(board);

        assertTrue(blackKingMoves.contains(board.getSpotAt("d7")));
        assertTrue(blackKingMoves.contains(board.getSpotAt("f8")));
        assertEquals(2, blackKingMoves.size());
        assertTrue(whiteKingMoves.contains(board.getSpotAt("d2")));
        assertTrue(whiteKingMoves.contains(board.getSpotAt("f1")));
        assertEquals(2, whiteKingMoves.size());

        // adding a checkmate
        Piece blackQueen = new Queen(BLACK);
        Piece whiteQueen = new Queen(WHITE);
        board.getSpotAt("d8").setPiece(whiteQueen);
        board.getSpotAt("f2").setPiece(blackQueen);
        board.addAllPieces(Arrays.asList(whiteQueen, blackQueen));
        blackKingMoves = blackKing.getMoves(board);
        whiteKingMoves = whiteKing.getMoves(board);

        assertEquals(0, blackKingMoves.size());
        assertEquals(0, whiteKingMoves.size());
    }
}
