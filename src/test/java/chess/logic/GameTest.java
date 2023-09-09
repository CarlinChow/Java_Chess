package chess.logic;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;
import chess.domain.*;
import chess.domain.pieces.*;
import static chess.types.Color.*;
import static chess.logic.types.GameStatus.*;
import chess.logic.exceptions.*;

public class GameTest {
    @Test
    public void testMakeMove(){
        Game game = new Game();
        Board board = game.getBoard();
        Piece whitePawn = board.getSpotAt("e2").getPiece();
        Piece blackPawn = board.getSpotAt("d7").getPiece();
        try{
            game.makeMove(whitePawn, "e4");
            assertEquals(whitePawn.getSpot(), board.getSpotAt("e4"));
            assertEquals(whitePawn, board.getSpotAt("e4").getPiece());
            game.makeMove(blackPawn, "d5");
            game.makeMove(whitePawn, "d5");
            assertEquals(whitePawn.getSpot(), board.getSpotAt("d5"));
            assertEquals(whitePawn, board.getSpotAt("d5").getPiece());
            assertTrue(blackPawn.isCaptured());
            assertNull(blackPawn.getSpot());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testInCheck(){
        Game game = new Game();
        this.setUp(game);
        Board board = game.getBoard();

        Piece blackQueen = new Queen(BLACK);
        Piece whitePawn = new Pawn(WHITE);
        board.addAllPieces(Arrays.asList(blackQueen, whitePawn));
        board.getSpotAt("b7").setPiece(blackQueen);
        board.getSpotAt("f6").setPiece(whitePawn);
        try{
            game.makeMove(whitePawn, "f7");
            assertSame(BLACK, game.getInCheck());
            game.makeMove(board.getSpotAt("e8").getPiece(), "f7");
            assertSame(null, game.getInCheck());
            Piece whiteRook = new Rook(WHITE);
            board.getSpotAt("e8").setPiece(whiteRook);
            board.addPiece(whiteRook);
            game.makeMove(whiteRook, "e7");
            assertSame(BLACK, game.getInCheck());
            game.makeMove(blackQueen, "e7");
            assertSame(WHITE, game.getInCheck());
            game.makeMove(board.getSpotAt("e1").getPiece(), "d1");
            assertSame(null, game.getInCheck());
            assertSame(ACTIVE, game.getGameStatus());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testIsInCheckmate(){
        this.checkmateTest1();
        this.checkmateTest2();
        this.checkmateTest3();
        this.checkmateTest4();
        this.checkmateTest5();
        this.checkmateTest6();
    }

    @Test
    public void testIsDraw(){
        this.drawTest1();
        this.drawTest2();
        this.drawTest3();
        this.drawTest4();
    }

    @Test
    public void testCastle(){
        Game game = new Game();
        this.setUp(game);
        Board board = game.getBoard();
        Piece whiteKing = board.getSpotAt("e1").getPiece();
        Piece blackKing = board.getSpotAt("e8").getPiece();
        Piece whiteRook = new Rook(WHITE);
        Piece blackRook = new Rook(BLACK);
        board.getSpotAt("a1").setPiece(whiteRook);
        board.getSpotAt("h8").setPiece(blackRook);
        board.addAllPieces(Arrays.asList(whiteRook, blackRook));
        try{
            game.makeMove(whiteKing, "c1"); // white castle left
            assertEquals(whiteKing, board.getSpotAt("c1").getPiece());
            assertEquals(board.getSpotAt("c1"), whiteKing.getSpot());
            assertEquals(whiteRook, board.getSpotAt("d1").getPiece());
            assertEquals(board.getSpotAt("d1"), whiteRook.getSpot());
            game.makeMove(blackKing, "g8"); // black castle right
            assertEquals(blackKing, board.getSpotAt("g8").getPiece());
            assertEquals(board.getSpotAt("g8"), blackKing.getSpot());
            assertEquals(blackRook, board.getSpotAt("e8").getPiece());
            assertEquals(board.getSpotAt("e8"), blackRook.getSpot());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testEnPassant(){
        Game game = new Game();
        Board board = game.getBoard();
        Piece whitePawnA = board.getSpotAt("a2").removePiece();
        board.getSpotAt("a5").setPiece(whitePawnA);
        Piece whitePawnB = board.getSpotAt("b2").getPiece();
        Piece whitePawnC = board.getSpotAt("c2").removePiece();
        board.getSpotAt("c5").setPiece(whitePawnC);
        Piece whitePawnD = board.getSpotAt("d2").getPiece();
        Piece whitePawnE = board.getSpotAt("e2").removePiece();
        board.getSpotAt("e5").setPiece(whitePawnE);
        Piece whitePawnF = board.getSpotAt("f2").getPiece();
        Piece whitePawnG = board.getSpotAt("g2").removePiece();
        board.getSpotAt("g5").setPiece(whitePawnG);
        Piece whitePawnH = board.getSpotAt("h2").getPiece();
        Piece blackPawnA = board.getSpotAt("a7").removePiece();
        board.getSpotAt("a4").setPiece(blackPawnA);
        Piece blackPawnB = board.getSpotAt("b7").getPiece();
        Piece blackPawnC = board.getSpotAt("c7").removePiece();
        board.getSpotAt("c4").setPiece(blackPawnC);
        Piece blackPawnD = board.getSpotAt("d7").getPiece();
        Piece blackPawnE = board.getSpotAt("e7").removePiece();
        board.getSpotAt("e4").setPiece(blackPawnE);
        Piece blackPawnF = board.getSpotAt("f7").getPiece();
        Piece blackPawnG = board.getSpotAt("g7").removePiece();
        board.getSpotAt("g4").setPiece(blackPawnG);
        Piece blackPawnH = board.getSpotAt("h7").getPiece();

        try{
            game.makeMove(board.getSpotAt("e1").getPiece(), "e2");
            game.makeMove(blackPawnB, "b5");
            game.makeMove(whitePawnA, "b6");
            assertTrue(blackPawnB.isCaptured());
            assertSame(board.getSpotAt("b6"), whitePawnA.getSpot());
            game.makeMove(blackPawnD, "d5");
            game.makeMove(whitePawnE, "d6");
            assertTrue(blackPawnD.isCaptured());
            assertSame(board.getSpotAt("d6"), whitePawnE.getSpot());
            game.makeMove(blackPawnH, "h5");
            game.makeMove(whitePawnG, "h6");
            assertTrue(blackPawnH.isCaptured());
            assertSame(board.getSpotAt("h6"), whitePawnG.getSpot());
            game.makeMove(board.getSpotAt("e8").getPiece(), "d7");
            game.makeMove(whitePawnB, "b4");
            game.makeMove(blackPawnA, "b3");
            assertTrue(whitePawnB.isCaptured());
            assertSame(board.getSpotAt("b3"), blackPawnA.getSpot());
            game.makeMove(whitePawnD, "d4");
            game.makeMove(blackPawnE, "d3");
            assertTrue(whitePawnD.isCaptured());
            assertSame(board.getSpotAt("d3"), blackPawnE.getSpot());
            board.print();
            game.makeMove(board.getSpotAt("e2").getPiece(), "e1");
            game.makeMove(board.getSpotAt("d7").getPiece(), "e8");
            game.makeMove(whitePawnH, "h4");
            game.makeMove(blackPawnG, "h3");
            assertTrue(whitePawnH.isCaptured());
            assertSame(board.getSpotAt("h3"), blackPawnG.getSpot());
            board.print();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test(expected = IllegalMoveException.class)
    public void testIllegalEnPassant() throws KingInCheckException, IllegalMoveException, IncorrectPlayerTurnException, PawnPromotionException{
        Game game = new Game();
        Board board = game.getBoard();
        Piece whitePawn = board.getSpotAt("e2").getPiece();
        Piece blackPawn = board.getSpotAt("f7").getPiece();
        game.makeMove(whitePawn, "e4");
        game.makeMove(blackPawn, "f5");
        game.makeMove(whitePawn, "e5");
        game.makeMove(board.getSpotAt("b8").getPiece(), "a6");
        game.makeMove(whitePawn, "f6");
    }

    // exceptions testing
    @Test(expected = GameIsNoLongerActiveException.class)
    public void testGameIsNoLongerActiveException() throws KingInCheckException, IllegalMoveException, IncorrectPlayerTurnException, PawnPromotionException {
        Game game = new Game();
        Board board = game.getBoard();
        game.resignGame();
        game.makeMove(board.getSpotAt("e2").getPiece(), "e4");
    }
    @Test(expected = IllegalMoveException.class)
    public void testIllegalMoveException() throws KingInCheckException, IllegalMoveException, IncorrectPlayerTurnException, PawnPromotionException {
        Game game = new Game();
        Board board = game.getBoard();
        game.makeMove(board.getSpotAt("b1").getPiece(), "d2");
    }
    @Test(expected = IncorrectPlayerTurnException.class)
    public void testIncorrectPlayerTurnException() throws KingInCheckException, IllegalMoveException, IncorrectPlayerTurnException, PawnPromotionException {
        Game game = new Game();
        Board board = game.getBoard();
        game.makeMove(board.getSpotAt("e7").getPiece(), "e5");
    }
    @Test(expected = KingInCheckException.class)
    public void testKingInCheckException1() throws KingInCheckException, IllegalMoveException, IncorrectPlayerTurnException, PawnPromotionException {
        Game game = new Game();
        Board board = game.getBoard();
        this.setUp(game);
        Piece blackQueen = new Queen(BLACK);
        Piece whiteQueen = new Queen(WHITE);
        board.addAllPieces(Arrays.asList(blackQueen, whiteQueen));
        board.getSpotAt("e7").setPiece(blackQueen);
        board.getSpotAt("e2").setPiece(whiteQueen);
        game.makeMove(board.getSpotAt("e2").getPiece(), "c4");
    }
    @Test(expected = KingInCheckException.class)
    public void testKingInCheckException2() throws KingInCheckException, IllegalMoveException, IncorrectPlayerTurnException, PawnPromotionException {
        Game game = new Game();
        Board board = game.getBoard();
        this.setUp(game);
        Piece blackQueen = new Queen(BLACK);
        Piece whiteQueen = new Queen(WHITE);
        board.addAllPieces(Arrays.asList(blackQueen, whiteQueen));
        board.getSpotAt("d8").setPiece(blackQueen);
        board.getSpotAt("d2").setPiece(whiteQueen);
        game.makeMove(board.getSpotAt("d2").getPiece(), "e2");
        game.makeMove(board.getSpotAt("d8").getPiece(), "d2");
    }

    @Test
    public void testPawnPromotion(){
        Game game = new Game();
        Board board = game.getBoard();
        board.clear();
        Piece whiteKing = new King(WHITE);
        Piece blackKing = new King(BLACK);
        board.addAllPieces(Arrays.asList(whiteKing, blackKing));
        board.getSpotAt("e8").setPiece(whiteKing);
        board.getSpotAt("e1").setPiece(blackKing);
        Piece blackPawn1 = new Pawn(BLACK);
        Piece blackPawn2 = new Pawn(BLACK);
        Piece blackPawn3 = new Pawn(BLACK);
        Piece blackPawn4 = new Pawn(BLACK);
        Piece blackPawn5 = new Pawn(BLACK);
        Piece blackPawn6 = new Pawn(BLACK);
        Piece blackPawn7 = new Pawn(BLACK);
        Piece blackPawn8 = new Pawn(BLACK);
        Piece whitePawn1 = new Pawn(WHITE);
        Piece whitePawn2 = new Pawn(WHITE);
        Piece whitePawn3 = new Pawn(WHITE);
        Piece whitePawn4 = new Pawn(WHITE);
        Piece whitePawn5 = new Pawn(WHITE);
        Piece whitePawn6 = new Pawn(WHITE);
        Piece whitePawn7 = new Pawn(WHITE);
        Piece whitePawn8 = new Pawn(WHITE);
        board.addAllPieces(Arrays.asList(blackPawn1, blackPawn2, blackPawn3, blackPawn4, blackPawn5, blackPawn6, blackPawn7, blackPawn8,
                        whitePawn1, whitePawn2, whitePawn3, whitePawn4, whitePawn5, whitePawn6, whitePawn7, whitePawn8));
        board.getSpotAt("a2").setPiece(blackPawn1);
        board.getSpotAt("b2").setPiece(blackPawn2);
        board.getSpotAt("c2").setPiece(blackPawn3);
        board.getSpotAt("d2").setPiece(blackPawn4);
        board.getSpotAt("e2").setPiece(blackPawn5);
        board.getSpotAt("f2").setPiece(blackPawn6);
        board.getSpotAt("g2").setPiece(blackPawn7);
        board.getSpotAt("h2").setPiece(blackPawn8);
        board.getSpotAt("a7").setPiece(whitePawn1);
        board.getSpotAt("b7").setPiece(whitePawn2);
        board.getSpotAt("c7").setPiece(whitePawn3);
        board.getSpotAt("d7").setPiece(whitePawn4);
        board.getSpotAt("e7").setPiece(whitePawn5);
        board.getSpotAt("f7").setPiece(whitePawn6);
        board.getSpotAt("g7").setPiece(whitePawn7);
        board.getSpotAt("h7").setPiece(whitePawn8);
        try{
            game.makeMove(whitePawn1, "a8");
        }catch(PawnPromotionException p){
            game.promotePawn("q");
            assertTrue(board.getSpotAt("a8").getPiece() instanceof Queen);
        }catch(Exception e){
            e.printStackTrace();
        }
        try{
            game.makeMove(blackPawn1, "a1");
        }catch(PawnPromotionException p){
            game.promotePawn("q");
            assertTrue(board.getSpotAt("a1").getPiece() instanceof Queen);
        }catch(Exception e){
            e.printStackTrace();
        }
        try{
            game.makeMove(whitePawn2, "b8");
        }catch(PawnPromotionException p){
            game.promotePawn("b");
            assertTrue(board.getSpotAt("b8").getPiece() instanceof Bishop);
        }catch(Exception e){
            e.printStackTrace();
        }
        try{
            game.makeMove(blackPawn2, "b1");
        }catch(PawnPromotionException p){
            game.promotePawn("b");
            assertTrue(board.getSpotAt("b1").getPiece() instanceof Bishop);
        }catch(Exception e){
            e.printStackTrace();
        }
        try{
            game.makeMove(whitePawn3, "c8");
        }catch(PawnPromotionException p){
            game.promotePawn("k");
            assertTrue(board.getSpotAt("c8").getPiece() instanceof Knight);
        }catch(Exception e){
            e.printStackTrace();
        }
        try{
            game.makeMove(blackPawn3, "c1");
        }catch(PawnPromotionException p){
            game.promotePawn("k");
            assertTrue(board.getSpotAt("c1").getPiece() instanceof Knight);
        }catch(Exception e){
            e.printStackTrace();
        }
        try{
            game.makeMove(whitePawn4, "d8");
        }catch(PawnPromotionException p){
            game.promotePawn("r");
            assertTrue(board.getSpotAt("d8").getPiece() instanceof Rook);
        }catch(Exception e){
            e.printStackTrace();
        }
        try{
            game.makeMove(blackPawn4, "d1");
        }catch(PawnPromotionException p){
            game.promotePawn("r");
            assertTrue(board.getSpotAt("d1").getPiece() instanceof Rook);
        }catch(Exception e){
            e.printStackTrace();
        }
        try{
            game.makeMove(whitePawn6, "f8");
        }catch(PawnPromotionException p){
            game.promotePawn("B");
            assertTrue(board.getSpotAt("f8").getPiece() instanceof Bishop);
        }catch(Exception e){
            e.printStackTrace();
        }
        try{
            game.makeMove(blackPawn6, "f1");
        }catch(PawnPromotionException p){
            game.promotePawn("B");
            assertTrue(board.getSpotAt("f1").getPiece() instanceof Bishop);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void setUp(Game game){
        // set up game w/ with only two kings on the board
        Board board = game.getBoard();
        board.clear();
        Piece blackKing = new King(BLACK);
        Piece whiteKing = new King(WHITE);
        board.getSpotAt("e8").setPiece(blackKing);
        board.getSpotAt("e1").setPiece(whiteKing);
        board.addPiece(whiteKing);
        board.addPiece(blackKing);
    }

    public void checkmateTest1(){
        Game game = new Game();
        Board board = game.getBoard();
        board.clear();
        Piece blackRook = new Rook(BLACK);
        Piece blackKing = new King(BLACK);
        Piece blackPawn = new Pawn(BLACK);
        Piece whitePawn = new Pawn(WHITE);
        Piece whiteQueen = new Queen(WHITE);
        Piece whiteKing = new King(WHITE);
        board.getSpotAt("f8").setPiece(blackRook);
        board.getSpotAt("g8").setPiece(blackKing);
        board.getSpotAt("g7").setPiece(blackPawn);
        board.getSpotAt("g6").setPiece(whitePawn);
        board.getSpotAt("h3").setPiece(whiteQueen);
        board.getSpotAt("g1").setPiece(whiteKing);
        board.addAllPieces(Arrays.asList(blackRook, blackKing, blackPawn, whitePawn, whiteQueen, whiteKing));
        try{
            game.makeMove(whiteQueen, "h7");
        }catch(Exception e){
            e.printStackTrace();
        }
        assertSame(WHITE_WIN, game.getGameStatus());
    }
    public void checkmateTest2(){
        Game game = new Game();
        Board board = game.getBoard();
        board.clear();
        Piece blackRook = new Rook(BLACK);
        Piece blackKing = new King(BLACK);
        Piece blackQueen = new Queen(BLACK);
        Piece blackBishop = new Bishop(BLACK);
        Piece blackPawn = new Pawn(BLACK);
        Piece blackKnight = new Knight(BLACK);
        Piece whiteRook = new Rook(WHITE);
        Piece whiteBishop = new Bishop(WHITE);
        Piece whiteKing = new King(WHITE);
        board.getSpotAt("h8").setPiece(blackRook);
        board.getSpotAt("e8").setPiece(blackKing);
        board.getSpotAt("f8").setPiece(blackBishop);
        board.getSpotAt("f7").setPiece(blackPawn);
        board.getSpotAt("b8").setPiece(blackKnight);
        board.getSpotAt("e6").setPiece(blackQueen);
        board.getSpotAt("d1").setPiece(whiteRook);
        board.getSpotAt("g5").setPiece(whiteBishop);
        board.getSpotAt("c1").setPiece(whiteKing);
        board.addAllPieces(Arrays.asList(blackRook, blackKing, blackQueen, blackBishop,
                blackPawn, blackKnight, whiteRook, whiteBishop, whiteKing));
        try{
            game.makeMove(whiteRook, "d8");
        }catch(Exception e){
            e.printStackTrace();
        }
        assertSame(WHITE_WIN, game.getGameStatus());
    }
    public void checkmateTest3(){ // fool's mate
        Game game = new Game();
        Board board = game.getBoard();
        try{
            game.makeMove(board.getSpotAt("f2").getPiece(), "f3");
            game.makeMove(board.getSpotAt("e7").getPiece(), "e5");
            game.makeMove(board.getSpotAt("g2").getPiece(), "g4");
            game.makeMove(board.getSpotAt("d8").getPiece(), "h4");
        } catch(Exception e){
            e.printStackTrace();
        }
        assertSame(BLACK_WIN, game.getGameStatus());
    }
    public void checkmateTest4(){ // Smothered Checkmate
        Game game = new Game();
        Board board = game.getBoard();
        try{
            game.makeMove(board.getSpotAt("e2").getPiece(), "e4");
            game.makeMove(board.getSpotAt("e7").getPiece(), "e5");
            game.makeMove(board.getSpotAt("g1").getPiece(), "e2");
            game.makeMove(board.getSpotAt("b8").getPiece(), "c6");
            game.makeMove(board.getSpotAt("b1").getPiece(), "c3");
            game.makeMove(board.getSpotAt("c6").getPiece(), "d4");
            game.makeMove(board.getSpotAt("g2").getPiece(), "g3");
            game.makeMove(board.getSpotAt("d4").getPiece(), "f3");
        } catch(Exception e){
            e.printStackTrace();
        }
        assertSame(BLACK_WIN, game.getGameStatus());
    }
    public void checkmateTest5(){ // hippopotamus mate
        Game game = new Game();
        Board board = game.getBoard();
        try{
            game.makeMove(board.getSpotAt("e2").getPiece(), "e4");
            game.makeMove(board.getSpotAt("e7").getPiece(), "e5");
            game.makeMove(board.getSpotAt("g1").getPiece(), "e2");
            game.makeMove(board.getSpotAt("d8").getPiece(), "h4");
            game.makeMove(board.getSpotAt("b1").getPiece(), "c3");
            game.makeMove(board.getSpotAt("b8").getPiece(), "c6");
            game.makeMove(board.getSpotAt("g2").getPiece(), "g3");
            game.makeMove(board.getSpotAt("h4").getPiece(), "g5");
            game.makeMove(board.getSpotAt("d2").getPiece(), "d4");
            game.makeMove(board.getSpotAt("c6").getPiece(), "d4");
            game.makeMove(board.getSpotAt("c1").getPiece(), "g5");
            game.makeMove(board.getSpotAt("d4").getPiece(), "f3");
        } catch(Exception e){
            e.printStackTrace();
        }
        assertSame(BLACK_WIN, game.getGameStatus());
    }
    public void checkmateTest6(){ // legal's mate
        Game game = new Game();
        Board board = game.getBoard();
        try{
            game.makeMove(board.getSpotAt("e2").getPiece(), "e4");
            game.makeMove(board.getSpotAt("e7").getPiece(), "e5");
            game.makeMove(board.getSpotAt("f1").getPiece(), "c4");
            game.makeMove(board.getSpotAt("d7").getPiece(), "d6");
            game.makeMove(board.getSpotAt("g1").getPiece(), "f3");
            game.makeMove(board.getSpotAt("c8").getPiece(), "g4");
            game.makeMove(board.getSpotAt("b1").getPiece(), "c3");
            game.makeMove(board.getSpotAt("g7").getPiece(), "g6");
            game.makeMove(board.getSpotAt("f3").getPiece(), "e5");
            game.makeMove(board.getSpotAt("g4").getPiece(), "d1");
            game.makeMove(board.getSpotAt("c4").getPiece(), "f7");
            game.makeMove(board.getSpotAt("e8").getPiece(), "e7");
            game.makeMove(board.getSpotAt("c3").getPiece(), "d5");
        } catch(Exception e){
            e.printStackTrace();
        }
        assertSame(WHITE_WIN, game.getGameStatus());
    }

    public void drawTest1(){
        Game game = new Game();
        Board board = game.getBoard();
        try{
            game.makeMove(board.getSpotAt("e2").getPiece(), "e3");
            game.makeMove(board.getSpotAt("a7").getPiece(), "a5");
            game.makeMove(board.getSpotAt("d1").getPiece(), "h5");
            game.makeMove(board.getSpotAt("a8").getPiece(), "a6");
            game.makeMove(board.getSpotAt("h5").getPiece(), "a5");
            game.makeMove(board.getSpotAt("h7").getPiece(), "h5");
            game.makeMove(board.getSpotAt("h2").getPiece(), "h4");
            game.makeMove(board.getSpotAt("a6").getPiece(), "h6");
            game.makeMove(board.getSpotAt("a5").getPiece(), "c7");
            game.makeMove(board.getSpotAt("f7").getPiece(), "f6");
            game.makeMove(board.getSpotAt("c7").getPiece(), "d7");
            game.makeMove(board.getSpotAt("e8").getPiece(), "f7");
            game.makeMove(board.getSpotAt("d7").getPiece(), "b7");
            game.makeMove(board.getSpotAt("d8").getPiece(), "d3");
            game.makeMove(board.getSpotAt("b7").getPiece(), "b8");
            game.makeMove(board.getSpotAt("d3").getPiece(), "h7");
            game.makeMove(board.getSpotAt("b8").getPiece(), "c8");
            game.makeMove(board.getSpotAt("f7").getPiece(), "g6");
            game.makeMove(board.getSpotAt("c8").getPiece(), "e6");
        } catch(Exception e){
            e.printStackTrace();
        }
        assertSame(STALEMATE, game.getGameStatus());
    }
    public void drawTest2(){
        Game game = new Game();
        Board board = game.getBoard();
        board.clear();
        Piece blackKing = new King(BLACK);
        Piece whiteKing = new King(WHITE);
        Piece whiteQueen = new Queen(WHITE);
        board.getSpotAt("a8").setPiece(blackKing);
        board.getSpotAt("c4").setPiece(whiteKing);
        board.getSpotAt("h7").setPiece(whiteQueen);
        board.addAllPieces(Arrays.asList(blackKing, whiteKing, whiteQueen));
        try{
          game.makeMove(board.getSpotAt("h7").getPiece(), "c7");
        } catch(Exception e){
            e.printStackTrace();
        }
        assertSame(STALEMATE, game.getGameStatus());
    }
    public void drawTest3(){
        Game game = new Game();
        Board board = game.getBoard();
        board.clear();
        Piece blackKing = new King(BLACK);
        Piece blackRook = new Rook(BLACK);
        Piece whiteKing = new King(WHITE);
        Piece whiteKnight1 = new Knight(WHITE);
        Piece whiteKnight2 = new Knight(WHITE);
        Piece whiteBishop = new Bishop(WHITE);
        board.getSpotAt("h8").setPiece(blackKing);
        board.getSpotAt("a1").setPiece(blackRook);
        board.getSpotAt("f6").setPiece(whiteKing);
        board.getSpotAt("e7").setPiece(whiteKnight1);
        board.getSpotAt("f4").setPiece(whiteBishop);
        board.getSpotAt("e6").setPiece(whiteKnight2);
        board.addAllPieces(Arrays.asList(blackKing, blackRook, whiteKing, whiteKnight1, whiteKnight2, whiteBishop));
        try{
            game.makeMove(whiteKnight2, "g5");
            game.makeMove(blackRook, "a6");
            game.makeMove(whiteKing, "f7");
            game.makeMove(blackRook, "f6");
            game.makeMove(whiteKing, "f6");
        } catch(Exception e){
            e.printStackTrace();
        }
        assertSame(STALEMATE, game.getGameStatus());
    }
    public void drawTest4(){
        Game game = new Game();
        Board board = game.getBoard();
        board.clear();
        Piece blackKing = new King(BLACK);
        Piece whiteKing = new King(WHITE);
        Piece whitePawn = new Pawn(WHITE);
        board.getSpotAt("e8").setPiece(blackKing);
        board.getSpotAt("d6").setPiece(whiteKing);
        board.getSpotAt("e7").setPiece(whitePawn);
        board.addAllPieces(Arrays.asList(blackKing, whiteKing, whitePawn));
        try{
            game.makeMove(whiteKing, "e6");
        } catch(Exception e){
            e.printStackTrace();
        }
        assertSame(STALEMATE, game.getGameStatus());
    }
}

