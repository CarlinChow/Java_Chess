package chess.logic;

import chess.domain.*;
import chess.domain.pieces.*;
import chess.logic.types.*;
import chess.logic.exceptions.*;
import chess.types.Color;
import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;

public class Game {
    private final Board board;
    private GameStatus gameStatus;
    private Color inCheck;
    private Color currentTurn;
    private final MoveList moveList;

    public Game(){
        this.board = new Board();
        this.gameStatus = GameStatus.ACTIVE;
        this.inCheck = null;
        this.currentTurn = Color.WHITE;
        this.moveList = new MoveList();
    }

    // getters
    public Board getBoard(){
        return this.board;
    }
    public Color getCurrentTurn(){
        return this.currentTurn;
    }
    public GameStatus getGameStatus(){
        return this.gameStatus;
    }
    public Color inCheck(){
        return this.inCheck;
    }

    public void resignGame(){
        this.gameStatus = GameStatus.RESIGNATION;
    }
    private void nextTurn(){ this.currentTurn = this.currentTurn == Color.WHITE ? Color.BLACK : Color.WHITE; }

    public void makeMove(Piece piece, String chessCoordinates) throws IllegalMoveException, IncorrectPlayerTurnException, KingInCheckAfterMoveException {
        if(this.gameStatus != GameStatus.ACTIVE){
            throw new GameIsNoLongerActiveException("Game has finished with " + this.gameStatus);
        }
        if(piece.getColor() != this.currentTurn){
            throw new IncorrectPlayerTurnException("It is currently player " + this.currentTurn + "'s turn, not player " + piece.getColor() + "'s");
        }
        if(piece instanceof King kingPiece){ // check for castling move
            if(kingPiece.canCastle(this.board, chessCoordinates, this.moveList)){
                // add castle move to moveList
                this.castle(); // castle
                return;
            }
        }
        if(!piece.canMove(this.board, chessCoordinates)){
            throw new IllegalMoveException("Piece cannot be moved to " + chessCoordinates);
        }
        if(this.isKingNotInCheckAfterMove(piece, chessCoordinates)){
            throw new KingInCheckAfterMoveException("Your King cannot be in check after move");
        }

        // make move
        Spot endSpot = this.board.getSpotAt(chessCoordinates);
        Piece capturedPiece = null;
        if(!endSpot.isEmpty()){
            capturedPiece = endSpot.getPiece();
            capturedPiece.setCaptured(true);
        }
        piece.getSpot().removePiece();
        endSpot.setPiece(piece);
        this.moveList.add(new Move(piece, endSpot, capturedPiece));
        this.inCheck = this.computeInCheck();

        if(this.inCheck != null){
            // check for checkmate, and rewrite game status if need be
            if(this.isInCheckMate(this.inCheck)){
                this.gameStatus = this.inCheck == Color.WHITE ? GameStatus.WHITE_WIN : GameStatus.BLACK_WIN;
            }
        }else{
            Color opponentColor = currentTurn == Color.WHITE ? Color.BLACK : Color.WHITE;
            if(this.isDraw(opponentColor)){
                this.gameStatus = GameStatus.STALEMATE;
            }
        }
        this.nextTurn();
    }

    private Color computeInCheck(){ // needs testing
        boolean whiteKingInCheck = this.isKingInCheck(Color.WHITE);
        boolean blackKingInCheck = this.isKingInCheck(Color.BLACK);
        if(whiteKingInCheck && blackKingInCheck){
            throw new RuntimeException("both players cannot be in check at the same time");
        }
        if(whiteKingInCheck){
            return Color.WHITE;
        }
        if(blackKingInCheck){
            return Color.BLACK;
        }
        return null;
    }

    private boolean isKingInCheck(Color color){ // needs testing
        List<King> kingList = this.board.getKingPieces()
                .stream()
                .filter(piece -> piece.getColor() == color)
                .toList();
        if(kingList.size() != 1){
            throw new RuntimeException("There is a duplicate king piece of the color " + color);
        }
        return kingList.get(0).isCurrentlyInCheck(this.board);
    }

    private boolean isInCheckMate(Color color){ // needs testing
        if(this.inCheck != color){
            return false;
        }
        Set<Piece> friendlyPieces = this.board.getAllActivePieces()
                                        .stream()
                                        .filter(piece -> piece.getColor() == color)
                                        .collect(Collectors.toSet());
        for(Piece friendlyPiece : friendlyPieces){
            Set<Spot> moves = friendlyPiece.getMoves(this.board);
            for(Spot moveSpot : moves){
                if(!this.isKingNotInCheckAfterMove(friendlyPiece, moveSpot.getChessCoordinates())){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isDraw(Color color){  // needs testing
        if(this.inCheck != null){
            return false;
        }
        Set<Piece> activePieces = this.board.getAllActivePieces()
                                        .stream()
                                        .filter(piece -> piece.getColor() != color)
                                        .collect(Collectors.toSet());
        for(Piece piece : activePieces){
            Set<Spot> moves = piece.getMoves(this.board);
            if(!moves.isEmpty()){
                for(Spot moveSpot : moves) {
                    if (this.isKingNotInCheckAfterMove(piece, moveSpot.getChessCoordinates())) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean isKingNotInCheckAfterMove(Piece piece, String chessCoordinates){ // needs testing
        // return true if move does not put king in check, false otherwise
        Spot endSpot = board.getSpotAt(chessCoordinates);
        Spot startSpot = piece.getSpot();
        Piece capturedPiece = null;
        if(!endSpot.isEmpty()){
            capturedPiece = endSpot.getPiece();
            capturedPiece.setCaptured(true);
        }
        startSpot.removePiece();
        endSpot.setPiece(piece);
        boolean rv = this.currentTurn != this.computeInCheck();
        startSpot.setPiece(piece); // revert board
        endSpot.removePiece();
        if(capturedPiece != null){
            capturedPiece.setCaptured(false);
            endSpot.setPiece(capturedPiece);
        }
        return rv;
    }

    private void castle(){

    }
}
