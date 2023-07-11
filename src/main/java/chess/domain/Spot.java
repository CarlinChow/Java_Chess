package chess.domain;

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
        return this.piece;
    }

    public void setPiece(Piece piece){
        this.piece = piece;
        piece.setSpot(this);
    }

    public Piece removePiece(){
        Piece tmpPiece = this.piece;
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
        return String.valueOf((char)(this.column + 'a'));
    }

    public String getChessRow(){
        return String.valueOf(8 - this.row);
    }

    public String getChessCoordinates(){
        return getChessColumn() + getChessRow();
    }

    public static String getChessCoordinates(int row, int column){
        String chessColumn = String.valueOf((char)(column + 'a'));
        String chessRow = String.valueOf(8 - row);
        return chessColumn + chessRow;
    }

    public static String convertChessCoordinates(String chessCoordinates){
        String column = String.valueOf(chessCoordinates.charAt(0) - 'a');
        String row = String.valueOf(8 - Character.getNumericValue(chessCoordinates.charAt(1)));
        return row + column;
    }

    @Override
    public String toString(){
        return "position: " + this.getChessCoordinates() + " on row: " + this.row + " and column: " + this.column;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Spot) {
            Spot spot = (Spot)obj;
            return this.getRow() == spot.getRow() && this.getColumn() == spot.getColumn() && this.piece == spot.getPiece();
        }
        return false;
    }
}

