package chess.logic;

import chess.domain.*;
import chess.types.Color;

public class Move {
    private final Color turn;
    private Piece piece;
    private final Spot start;
    private final Spot end;
    private final Piece capturedPiece;

    public Move(Piece piece, Spot start, Spot end, Piece capturedPiece){
        this.turn = piece.getColor();
        this.piece = piece;
        this.start = start;
        this.end = end;
        this.capturedPiece = capturedPiece;
    }

    public Move(Piece piece, Spot end){
        this(piece, piece.getSpot(), end, null);
    }

    public Move(Piece piece, Spot start, Spot end){
        this(piece, start, end, null);
    }

    public Move(Piece piece, Spot end, Piece capturedPiece){
        this(piece, piece.getSpot(), end, capturedPiece);
    }

    public Color getTurn(){
        return this.turn;
    }

    public Piece getPiece(){
        return this.piece;
    }

    public Spot getStart(){
        return this.start;
    }

    public Spot getEnd(){
        return this.end;
    }

    public Piece getCapturedPiece(){
        return this.capturedPiece;
    }
}
