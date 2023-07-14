package chess.domain;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

public class Board {
    private final Spot[][] spots;
    private List<Piece> pieces;

    public Board(){
        this.spots = new Spot[8][8];
        for(int row = 0; row < this.spots.length; row++){
            for(int column = 0; column < this.spots[0].length; column++){
                this.spots[row][column] = new Spot(row, column);
            }
        }
        this.pieces = new ArrayList<>();

    }

    public void reset(){
        // set up pieces

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
        for (Spot[] spot : this.spots) {
            for (int col = 0; col < this.spots[0].length; col++) {
                if (spot[col].isEmpty()) {
                    System.out.print("[ ]");
                } else {
                    System.out.print("[" + spot[col].getPiece() + "]");
                }
            }
            System.out.println(" ");
        }
        System.out.println(" ");
    }

    public List<Piece> getAllPieces(){
        return this.pieces;
    }

    public void addPiece(Piece piece){ this.pieces.add(piece); }

    public void removePiece(Piece piece){ this.pieces.remove(piece); }

    public void addAllPieces(Collection<Piece> pieces) { this.pieces.addAll(pieces); }

}

