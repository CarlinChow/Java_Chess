package chess.logic.exceptions;

public class KingInCheckAfterMoveException extends Exception {
    public KingInCheckAfterMoveException(String errorMessage){
        super(errorMessage);
    }
}
