package chess.domain;

public class Board {
    private final Spot[][] spots;

    public Board(){
        this.spots = new Spot[8][8];
        this.resetBoard();
    }

    public void resetBoard(){
        // set up empty spots
        for(int row = 0; row < this.spots.length; row++){
            for(int column = 0; column < this.spots[0].length; column++){
                this.spots[row][column] = new Spot(row, column);
            }
        }
        // set up pieces

    }

    public Spot getSpotAt(String chessCoordinates){
        String coordinates = Spot.convertChessCoordinates(chessCoordinates);
        int row = Character.getNumericValue(coordinates.charAt(1));
        int column =  Character.getNumericValue(coordinates.charAt(0));
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
}

