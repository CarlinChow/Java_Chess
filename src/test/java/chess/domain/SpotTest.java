package chess.domain;

import org.junit.Test;
import static org.junit.Assert.*;
import chess.domain.*;
import chess.domain.pieces.*;

public class SpotTest {
    @Test
    public void testIsEmpty(){
        Spot spot = new Spot(0,0);
        assertTrue(spot.isEmpty());
        spot.setPiece(new Pawn(true));
        assertFalse(spot.isEmpty());
    }

    @Test
    public void testRemovePiece(){
        Spot spot = new Spot(0,0);
        Piece pawn = new Pawn(true);
        spot.setPiece(pawn);
        Piece returnedPiece = spot.removePiece();
        assertEquals(pawn, returnedPiece);
        assertTrue(spot.isEmpty());
    }

    @Test
    public void testGetChessCoordinates(){
        Spot spot = new Spot(0,0);
        assertEquals("a8", spot.getChessCoordinates());
        assertEquals("00", Spot.convertChessCoordinates(spot.getChessCoordinates()));
        spot = new Spot(7,7);
        assertEquals("h1", spot.getChessCoordinates());
        assertEquals("77", Spot.convertChessCoordinates(spot.getChessCoordinates()));
        spot = new Spot(3,6);
        assertEquals("g5", spot.getChessCoordinates());
        assertEquals("36", Spot.convertChessCoordinates(spot.getChessCoordinates()));
        spot = new Spot(2,4);
        assertEquals("e6", spot.getChessCoordinates());
        assertEquals("24", Spot.convertChessCoordinates(spot.getChessCoordinates()));
        spot = new Spot(1,2);
        assertEquals("c7", spot.getChessCoordinates());
        assertEquals("12", Spot.convertChessCoordinates(spot.getChessCoordinates()));
        spot = new Spot(1, 5);
        assertEquals("f7", spot.getChessCoordinates());
        assertEquals("15", Spot.convertChessCoordinates(spot.getChessCoordinates()));
        spot = new Spot(0, 7);
        assertEquals("h8", spot.getChessCoordinates());
        assertEquals("07", Spot.convertChessCoordinates(spot.getChessCoordinates()));
        spot = new Spot(6, 0);
        assertEquals("a2", spot.getChessCoordinates());
        assertEquals("60", Spot.convertChessCoordinates(spot.getChessCoordinates()));
        spot = new Spot(5, 1);
        assertEquals("b3", spot.getChessCoordinates());
        assertEquals("51", Spot.convertChessCoordinates(spot.getChessCoordinates()));
    }

    @Test
    public void testEquals(){
        Spot spot = new Spot(5,5);
        Object spotCopy1 = new Spot(5,5);
        Object spotCopy2 = spot;
        Spot spotCopy3 = spot;
        Spot nonSpotCopy1 = new Spot(3,3);
        Object nonSpotCopy2 = nonSpotCopy1;
        assertEquals(spot, spotCopy1);
        assertEquals(spot, spotCopy2);
        assertEquals(spot, spotCopy3);
        assertNotEquals(spot, nonSpotCopy1);
        assertNotEquals(spot, nonSpotCopy2);
    }
}