package chess.logic.exceptions;

public class GameIsNoLongerActiveException extends RuntimeException {
    public GameIsNoLongerActiveException(String errorMessage){
        super(errorMessage);
    }
}
