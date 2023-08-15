package chess.logic.exceptions;

public class IncorrectPlayerTurnException extends Exception{
    public IncorrectPlayerTurnException(String errorMessage){
        super(errorMessage);
    }
}