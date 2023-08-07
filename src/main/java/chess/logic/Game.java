package chess.logic;

import chess.domain.*;
import chess.logic.types.*;

public class Game {
    private Board board;
    private GameStatus gameStatus;
    private CheckStatus checkStatus;
    private Boolean currentTurn;

    public Game(){
        this.board = new Board();
        this.gameStatus = GameStatus.ACTIVE;
        this.checkStatus = CheckStatus.DEFAULT;
    }


}
