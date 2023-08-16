package chess.logic;

import org.junit.Test;
import org.junit.Assert.*;
import chess.domain.*;

public class GameTest {
    @Test
    public void testMakeMove(){
        Game game = new Game();
        Board board = game.getBoard();
        game.getBoard().clear();

    }

    @Test
    public void testIsInCheck(){

    }

    @Test
    public void testIsInCheckmate(){}

    @Test
    public void isDraw(){}

    @Test
    public void testIsKingNotInCheckAfterMove(){}

    @Test
    public void testCastle(){}

}
