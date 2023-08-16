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
    private void nextTurn(){
        this.currentTurn = this.currentTurn == Color.WHITE ? Color.BLACK : Color.WHITE;
    }

    public void makeMove(Piece piece, String chessCoordinates) throws IllegalMoveException, IncorrectPlayerTurnException, KingInCheckException {
        if(this.gameStatus != GameStatus.ACTIVE){
            throw new GameIsNoLongerActiveException("Game has finished with " + this.gameStatus);
        }
        if(piece.getColor() != this.currentTurn){
            throw new IncorrectPlayerTurnException("It is currently player " + this.currentTurn + "'s turn, not player " + piece.getColor() + "'s");
        }
        if(piece instanceof King kingPiece){ // check for castling move
            if(kingPiece.canCastle(this.board, chessCoordinates, this.moveList)){
                this.castle(piece, chessCoordinates);
            }
        }else{
            if(!piece.canMove(this.board, chessCoordinates)){
                throw new IllegalMoveException("Piece cannot be moved to " + chessCoordinates);
            }
            if(this.isInCheckAfterMove(this.currentTurn, piece, chessCoordinates)){
                throw new KingInCheckException("Your King cannot be in check after move");
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
        }
        this.inCheck = this.computeInCheck();
        if(this.inCheck != null){
            // check for checkmate, and rewrite game status if need be
            if(this.isInCheckmate(this.inCheck)){
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
        boolean whiteKingInCheck = this.isInCheck(Color.WHITE);
        boolean blackKingInCheck = this.isInCheck(Color.BLACK);
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

    private boolean isInCheck(Color color){ // needs testing
        List<King> kingList = this.board.getKingPieces()
                .stream()
                .filter(piece -> piece.getColor() == color)
                .toList();
        if(kingList.size() != 1){
            throw new RuntimeException("There is a duplicate king piece of the color " + color);
        }
        return kingList.get(0).isCurrentlyInCheck(this.board);
    }

    private boolean isInCheckmate(Color color){ // needs testing
        Set<Piece> friendlyPieces = this.board.getAllActivePieces()
                                        .stream()
                                        .filter(piece -> piece.getColor() == color)
                                        .collect(Collectors.toSet());
        for(Piece friendlyPiece : friendlyPieces){
            Set<Spot> moves = friendlyPiece.getMoves(this.board);
            for(Spot moveSpot : moves){
                if(this.isInCheckAfterMove(this.currentTurn, friendlyPiece, moveSpot.getChessCoordinates())){
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
                    if (this.isInCheckAfterMove(this.currentTurn, piece, moveSpot.getChessCoordinates())) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean isInCheckAfterMove(Color color, Piece piece, String chessCoordinates){ // needs testing
        // return true if move puts king in check, false otherwise
        Spot endSpot = board.getSpotAt(chessCoordinates);
        Spot startSpot = piece.getSpot();
        Piece capturedPiece = null;
        if(!endSpot.isEmpty()){
            capturedPiece = endSpot.getPiece();
            capturedPiece.setCaptured(true);
        }
        startSpot.removePiece();
        endSpot.setPiece(piece);
        boolean rv = this.isInCheck(color);
        startSpot.setPiece(piece); // revert move
        endSpot.removePiece();
        if(capturedPiece != null){
            endSpot.setPiece(capturedPiece);
            capturedPiece.setCaptured(false);
        }
        return rv;
    }

    private void castle(Piece king, String chessCoordinates){
        Spot rookSpot;
        Spot newRookSpot;
        int currentRow = king.getSpot().getRow();
        if(Character.toString(chessCoordinates.charAt(0)).equals("g")){ // castle right
            rookSpot = board.getSpotAt(currentRow, 7);
            newRookSpot = board.getSpotAt(currentRow, 4);
        }else{ // castle left
            rookSpot = board.getSpotAt(king.getSpot().getRow(), 0);
            newRookSpot = board.getSpotAt(currentRow, 3);
        }
        Piece rook = rookSpot.getPiece();
        Spot newKingSpot = board.getSpotAt(chessCoordinates);
        moveList.add(new Move(king, newKingSpot));
        moveList.add(new Move(rook, newRookSpot));
        king.getSpot().removePiece();
        rookSpot.removePiece();
        newRookSpot.setPiece(rook);
        newKingSpot.setPiece(king);
    }
}
