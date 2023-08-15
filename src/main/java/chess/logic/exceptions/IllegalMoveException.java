package chess.logic.exceptions;

public class IllegalMoveException extends Exception {
    public IllegalMoveException(String errorMessage){
        super(errorMessage);
    }
}