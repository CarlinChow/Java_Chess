package chess.domain;

import java.util.Objects;

public class Spot {
    private int row;
    private int column;
    private Piece piece;

    public Spot(int row, int column){
        this.row = row;
        this.column = column;
    }

    public Spot(int row, int column, Piece piece){
        this(row, column);
        this.setPiece(piece);
    }

    public boolean isEmpty(){
        return this.piece == null;
    }

    public Piece getPiece(){
        // https://stackoverflow.com/questions/271526/how-do-i-avoid-checking-for-nulls-in-java
        return Objects.requireNonNull(this.piece, "spot " + this.getChessCoordinates() + " does not contain a piece");
    }

    public void setPiece(Piece piece){
        if(piece == null){
            throw new IllegalArgumentException("attempting to Spot.setPiece() with null value");
        }
        this.piece = piece;
        piece.setSpot(this);
    }

    public Piece removePiece(){
        Piece tmpPiece = this.piece;
        if(tmpPiece == null){
            return null;
        }
        tmpPiece.setSpot(null);
        this.piece = null;
        return tmpPiece;
    }

    public int getRow(){
        return this.row;
    }

    public int getColumn(){
        return this.column;
    }

    public void setRow(int row){
        if(row < 0 || row > 7){
            throw new IllegalArgumentException("row index must be [0,7]");
        }
        this.row = row;
    }

    public void setColumn(int column){
        if(column < 0 || column > 7){
            throw new IllegalArgumentException("column index must be [0,7]");
        }
        this.column = column;
    }

    public String getChessColumn(){
        return convertToChessColumn(this.column);
    }

    public String getChessRow(){
        return convertToChessRow(this.row);
    }

    public String getChessCoordinates(){
        return convertToChessCoordinates(this.row, this.column);
    }

    private static String convertToChessColumn(int column){
        return String.valueOf((char)(column + 'a'));
    }

    private static String convertToChessRow(int row){
        return String.valueOf(8 - row);
    }

    public static String convertToChessCoordinates(int row, int column){
        return convertToChessColumn(column) + convertToChessRow(row);
    }

    public static String convertToMatrixCoordinates(String chessCoordinates){
        String column = String.valueOf(chessCoordinates.charAt(0) - 'a');
        String row = String.valueOf(8 - Character.getNumericValue(chessCoordinates.charAt(1)));
        return row + column;
    }

    @Override
    public String toString(){
        return this.getChessCoordinates();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Spot spot) {
            if(!this.isEmpty() && !spot.isEmpty()){
                return this.getRow() == spot.getRow() &&
                       this.getColumn() == spot.getColumn() &&
                       this.piece == spot.getPiece();
            }
            return this.getRow() == spot.getRow() && this.getColumn() == spot.getColumn();
        }
        return false;
    }
}

