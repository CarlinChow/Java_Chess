package chess.domain;

public class Board {
    private Spot[][] spots;

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

    public Spot getSpotAt(String coordinates){
        int row = Math.abs(Character.getNumericValue(coordinates.charAt(1)) - 8);
        int column = coordinates.charAt(0) - 'a';
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

    public void printBoard(){
        for(int row = 0; row < this.spots.length; row++){
            for(int col = 0; col < this.spots[0].length; col++){
                if(spots[row][col].isEmpty()){
                    System.out.print("[ ]");
                }else{
                    System.out.print("["+ spots[row][col].getPiece() +"]");
                }
            }
            System.out.println(" ");
        }
    }
}

