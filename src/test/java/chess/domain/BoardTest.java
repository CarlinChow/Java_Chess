package chess.domain;

import chess.domain.pieces.*;
import org.junit.Test;

public class BoardTest {
    @Test
    public void testReset(){
        Board board = new Board();
        board.reset();
        board.print();
        board.printInverse();
    }

    @Test
    public void testClear(){
        Board board = new Board();
        board.reset();
        board.print();
        board.clear();
    }
}
