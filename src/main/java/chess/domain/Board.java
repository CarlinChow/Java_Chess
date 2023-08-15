package chess.domain;

import java.util.Collection;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;
import chess.domain.pieces.*;
import static chess.types.Color.*;
import static java.util.function.Predicate.not;

public class Board {
    private final Spot[][] spots;
    private final Set<Piece> pieces;

    public Board(){
        this.spots = new Spot[8][8];
        for(int row = 0; row < this.spots.length; row++){
            for(int column = 0; column < this.spots[0].length; column++){
                this.spots[row][column] = new Spot(row, column);
            }
        }
        this.pieces = new HashSet<>();
        this.reset();
    }

    public void reset(){ // reset board to piece starting position
        this.clear();
        // black pawns
        this.getSpotAt("a7").setPiece(new Pawn(BLACK));
        this.getSpotAt("b7").setPiece(new Pawn(BLACK));
        this.getSpotAt("c7").setPiece(new Pawn(BLACK));
        this.getSpotAt("d7").setPiece(new Pawn(BLACK));
        this.getSpotAt("e7").setPiece(new Pawn(BLACK));
        this.getSpotAt("f7").setPiece(new Pawn(BLACK));
        this.getSpotAt("g7").setPiece(new Pawn(BLACK));
        this.getSpotAt("h7").setPiece(new Pawn(BLACK));
        // white pawns
        this.getSpotAt("a2").setPiece(new Pawn(WHITE));
        this.getSpotAt("b2").setPiece(new Pawn(WHITE));
        this.getSpotAt("c2").setPiece(new Pawn(WHITE));
        this.getSpotAt("d2").setPiece(new Pawn(WHITE));
        this.getSpotAt("e2").setPiece(new Pawn(WHITE));
        this.getSpotAt("f2").setPiece(new Pawn(WHITE));
        this.getSpotAt("g2").setPiece(new Pawn(WHITE));
        this.getSpotAt("h2").setPiece(new Pawn(WHITE));
        // kings
        this.getSpotAt("e8").setPiece(new King(BLACK));
        this.getSpotAt("e1").setPiece(new King(WHITE));
        // queens
        this.getSpotAt("d8").setPiece(new Queen(BLACK));
        this.getSpotAt("d1").setPiece(new Queen(WHITE));
        // bishops
        this.getSpotAt("c8").setPiece(new Bishop(BLACK));
        this.getSpotAt("f8").setPiece(new Bishop(BLACK));
        this.getSpotAt("c1").setPiece(new Bishop(WHITE));
        this.getSpotAt("f1").setPiece(new Bishop(WHITE));
        // knights
        this.getSpotAt("b8").setPiece(new Knight(BLACK));
        this.getSpotAt("g8").setPiece(new Knight(BLACK));
        this.getSpotAt("b1").setPiece(new Knight(WHITE));
        this.getSpotAt("g1").setPiece(new Knight(WHITE));
        // rooks
        this.getSpotAt("a8").setPiece(new Rook(BLACK));
        this.getSpotAt("h8").setPiece(new Rook(BLACK));
        this.getSpotAt("a1").setPiece(new Rook(WHITE));
        this.getSpotAt("h1").setPiece(new Rook(WHITE));

        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 8; j++){
                this.pieces.add(this.getSpotAt(i, j).getPiece());
            }
        }
        for(int i = 6; i < 8; i++){
            for(int j = 0; j < 8; j++){
                this.pieces.add(this.getSpotAt(i, j).getPiece());
            }
        }
    }

    public void clear(){
        for(Spot[] row : this.spots){
            for(Spot spot : row){
                spot.removePiece();
            }
        }
        this.pieces.clear();
    }

    public Spot getSpotAt(String chessCoordinates){
        String coordinates = Spot.convertToMatrixCoordinates(chessCoordinates);
        int row = Character.getNumericValue(coordinates.charAt(0));
        int column =  Character.getNumericValue(coordinates.charAt(1));
        return this.getSpotAt(row, column);
    }

    public Spot getSpotAt(int row, int column){
        if (row < 0 ||
            column < 0 ||
            row > this.spots.length - 1 ||
            column > this.spots.length - 1)
        {
            throw new ArrayIndexOutOfBoundsException("row and/or column are not within bounds");
        }
        return this.spots[row][column];
    }

    public void print(){
        System.out.print("  ");
        for(char alphabet = 'a'; alphabet < 'h' + 1; alphabet++){
            System.out.print(" " + alphabet + " ");
        }
        System.out.println();
        for (int row = 0; row < this.spots.length; row++) {
            System.out.print(spots[row][0].getChessRow() + " ");
            for (int col = 0; col < this.spots[0].length; col++) {
                if (spots[row][col].isEmpty()) {
                    System.out.print("[ ]");
                } else {
                    System.out.print("[" + spots[row][col].getPiece() + "]");
                }
            }
            System.out.println(" " + spots[row][0].getChessRow());
        }
        System.out.print("  ");
        for(char alphabet = 'a'; alphabet < 'h' + 1; alphabet++){
            System.out.print(" " + alphabet + " ");
        }
        System.out.println("  ");
    }

    public void printInverse(){
        System.out.print("  ");
        for(char alphabet = 'a'; alphabet < 'h' + 1; alphabet++){
            System.out.print(" " + alphabet + " ");
        }
        System.out.println();
        for (int row = spots.length - 1; row > -1; row--) {
            System.out.print(spots[row][0].getChessRow() + " ");
            for (int col = 0; col < this.spots[0].length; col++) {
                if (spots[row][col].isEmpty()) {
                    System.out.print("[ ]");
                } else {
                    System.out.print("[" + spots[row][col].getPiece() + "]");
                }
            }
            System.out.println(" " + spots[row][0].getChessRow());
        }
        System.out.print("  ");
        for(char alphabet = 'a'; alphabet < 'h' + 1; alphabet++){
            System.out.print(" " + alphabet + " ");
        }
        System.out.println("  ");
    }

    public Set<Piece> getAllPieces(){
        return this.pieces;
    }

    public Set<Piece> getAllActivePieces() {
        return this.pieces
                .stream()
                .filter(not(Piece::isCaptured))
                .collect(Collectors.toSet());
    }

    public Set<Piece> getAllCapturedPieces(){
        return this.pieces
                .stream()
                .filter(Piece::isCaptured)
                .collect(Collectors.toSet());
    }

    public Set<King> getKingPieces(){
        return this.pieces
                .stream()
                .filter(King.class::isInstance)
                .map(King.class::cast)
                .collect(Collectors.toSet());
    }

    public void addPiece(Piece piece){
        if(piece == null){
            throw new IllegalArgumentException("Piece parameter cannot be null");
        }
        this.pieces.add(piece);
    }

    public void removePiece(Piece piece){ this.pieces.remove(piece); }

    public void addAllPieces(Collection<Piece> pieces) { this.pieces.addAll(pieces); }
}

