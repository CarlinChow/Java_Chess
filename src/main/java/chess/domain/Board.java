package chess.domain;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import chess.domain.pieces.*;

public class Board {
    private final Spot[][] spots;
    private final List<Piece> pieces;

    public Board(){
        this.spots = new Spot[8][8];
        for(int row = 0; row < this.spots.length; row++){
            for(int column = 0; column < this.spots[0].length; column++){
                this.spots[row][column] = new Spot(row, column);
            }
        }
        this.pieces = new ArrayList<>();
        this.reset();
    }

    public void reset(){ // reset board to piece starting position
        this.clear();
        // black pawns
        this.getSpotAt("a7").setPiece(new Pawn(false));
        this.getSpotAt("b7").setPiece(new Pawn(false));
        this.getSpotAt("c7").setPiece(new Pawn(false));
        this.getSpotAt("d7").setPiece(new Pawn(false));
        this.getSpotAt("e7").setPiece(new Pawn(false));
        this.getSpotAt("f7").setPiece(new Pawn(false));
        this.getSpotAt("g7").setPiece(new Pawn(false));
        this.getSpotAt("h7").setPiece(new Pawn(false));
        // white pawns
        this.getSpotAt("a2").setPiece(new Pawn(true));
        this.getSpotAt("b2").setPiece(new Pawn(true));
        this.getSpotAt("c2").setPiece(new Pawn(true));
        this.getSpotAt("d2").setPiece(new Pawn(true));
        this.getSpotAt("e2").setPiece(new Pawn(true));
        this.getSpotAt("f2").setPiece(new Pawn(true));
        this.getSpotAt("g2").setPiece(new Pawn(true));
        this.getSpotAt("h2").setPiece(new Pawn(true));
        // kings
        this.getSpotAt("e8").setPiece(new King(false));
        this.getSpotAt("e1").setPiece(new King(true));
        // queens
        this.getSpotAt("d8").setPiece(new Queen(false));
        this.getSpotAt("d1").setPiece(new Queen(true));
        // bishops
        this.getSpotAt("c8").setPiece(new Bishop(false));
        this.getSpotAt("f8").setPiece(new Bishop(false));
        this.getSpotAt("c1").setPiece(new Bishop(true));
        this.getSpotAt("f1").setPiece(new Bishop(true));
        // knights
        this.getSpotAt("b8").setPiece(new Knight(false));
        this.getSpotAt("g8").setPiece(new Knight(false));
        this.getSpotAt("b1").setPiece(new Knight(true));
        this.getSpotAt("g1").setPiece(new Knight(true));
        // rooks
        this.getSpotAt("a8").setPiece(new Rook(false));
        this.getSpotAt("h8").setPiece(new Rook(false));
        this.getSpotAt("a1").setPiece(new Rook(true));
        this.getSpotAt("h1").setPiece(new Rook(true));

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
        String coordinates = Spot.getMatrixCoordinates(chessCoordinates);
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
            return null;
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

    public List<Piece> getAllPieces(){
        return this.pieces;
    }

    public List<Piece> getAllActivePieces() {
        return this.pieces.stream()
                .filter(piece -> !piece.isCaptured())
                .toList();
    }

    public List<Piece> getAllCapturedPieces(){
        return this.pieces.stream()
                .filter(piece -> piece.isCaptured())
                .toList();
    }

    public void addPiece(Piece piece){ this.pieces.add(piece); }

    public void removePiece(Piece piece){ this.pieces.remove(piece); }

    public void addAllPieces(Collection<Piece> pieces) { this.pieces.addAll(pieces); }
}

