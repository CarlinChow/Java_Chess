package chess.domain;

public class Spot {
    private int i;
    private int j;
    private Piece piece;

    public Spot(int i, int j){
        this.i = i;
        this.j= j;
    }

    public Spot(int i, int j, Piece piece){
        this(i, j);
        this.piece = piece;
    }

    public boolean isEmpty(){
        return this.piece == null;
    }

    public Piece getPiece(){
        return this.piece;
    }

    public void setPiece(Piece piece){
        this.piece = piece;
    }

    public Piece removePiece(){
        Piece tmpPiece = this.piece;
        this.piece = null;
        return tmpPiece;
    }

    public int getRow(){
        return this.i;
    }

    public int getColumn(){
        return this.j;
    }

    public void setRow(int i){
        if(i < 0 || i > 7){
            throw new IllegalArgumentException("row index must be [0,7]");
        }
        this.i = i;
    }

    public void setColumn(int j){
        if(j < 0 || j > 7){
            throw new IllegalArgumentException("column index must be [0,7]");
        }
        this.j = j;
    }

    public String getChessColumn(){
        return String.valueOf((char)(this.j + 'a'));
    }

    public String getChessRow(){
        return String.valueOf(8 - this.i);
    }

    public String getChessCoordinates(){
        return  getChessColumn() + getChessRow();
    }

    @Override
    public String toString(){
        return "position: " + this.getChessCoordinates() + " on row: " + this.i + " and column: " + this.j;
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

