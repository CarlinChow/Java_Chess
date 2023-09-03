package chess.logic.exceptions;

public class KingInCheckException extends Exception {
    public KingInCheckException(String errorMessage){
        super(errorMessage);
    }
}
