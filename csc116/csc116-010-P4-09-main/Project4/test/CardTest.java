import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;


/**
 * Tests Card class
 * @author Suzanne Balik
 * @author Max Farthing
 */
public class CardTest {

    /** card two of hearts for testing */
    private Card twoOfHearts;

    /** Student card seven of spades for testing */
    private Card sevenOfSpades;

    /**
     * Create cards for testing
     */
    @BeforeEach
    public void setUp() {
        twoOfHearts = new Card('h', 2);
        sevenOfSpades = new Card('s', 7); //student
    }

    /**
     * Tests constants
     */
    @Test
    public void testConstants() {
        assertEquals('c', Card.CLUBS, "CLUBS");
        assertEquals('d', Card.DIAMONDS, "DIAMONDS");
        assertEquals('s', Card.SPADES, "SPADES");
        assertEquals('h', Card.HEARTS, "HEARTS");
        assertEquals(2, Card.LOWEST_VALUE, "LOWEST_VALUE");
        assertEquals(14, Card.HIGHEST_VALUE, "HIGHEST_VALUE]");
    }

    /**
     * Tests constructor preconditions
     */
    @Test
    public void testConstructorPreConditions() {
        // Testing constructor with low invalid value
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> new Card('h', 1), "Constructor value 1");
        assertEquals("Invalid value", exception.getMessage(),
                "Testing value 1 message");
                
        // Testing constructor with high invalid value
        exception = assertThrows(IllegalArgumentException.class,
            () -> new Card('s', 15), "Constructor value 15");
        assertEquals("Invalid value", exception.getMessage(),
                "Testing value 15 message");
                
        // Testing constructor with invalid suit and invalid value
        exception = assertThrows(IllegalArgumentException.class,
            () -> new Card('w', 0), "Constructor suit w value 0");
        assertEquals("Invalid suit", exception.getMessage(),
                "Testing suit w value 0 message");
                
        // Testing constructor with invalid lowercase suit
        exception = assertThrows(IllegalArgumentException.class,
            () -> new Card('x', 5), "Constructor suit x");
        assertEquals("Invalid suit", exception.getMessage(),
                "Testing suit x message");
                
        // Testing constructor with invalid uppercase suit
        exception = assertThrows(IllegalArgumentException.class,
            () -> new Card('D', 5), "Constructor suit D");
        assertEquals("Invalid suit", exception.getMessage(),
                "Testing suit D message");
    }
    
    /**
     * Test getSuit() for two of hearts
     */
    @Test
    public void testGetSuitTwoOfHearts() {
        assertEquals('h', twoOfHearts.getSuit(), "twoOfHearts suit");
    }

    /**
     * Student Test getSuit() for seven of spades
     */
    @Test
    public void testGetSuitSevenOfSpades() {
        assertEquals('s', sevenOfSpades.getSuit(), "sevenOfSpades suit");
    }

    /**
     * Test getValue() for two of hearts
     */
    @Test
    public void testGetValueTwoOfHearts() {
        assertEquals( 2, twoOfHearts.getValue(), "twoOfHearts value");
    }


    /**
     * Test getColor() for two of hearts
     */
    @Test
    public void testGetColorTwoOfHearts() {
        assertEquals(Color.RED, twoOfHearts.getColor(), "twoOfHearts color");
    }

    /**
     * Student Test getColor() for seven of spades
     */
    @Test
    public void testGetColorSevenOfSpades() {
        assertEquals(Color.BLACK, sevenOfSpades.getColor(), "sevenOfSpades color");
    }

    /**
     * Test toString() for two of hearts
     */
    @Test
    public void testToStringTwoOfHearts() {
        assertEquals("h2", twoOfHearts.toString(), "twoOfHearts toString");
    }

    /**
     * Test hasBeenFound() for two of hearts
     */
    @Test
    public void testHasBeenFoundTwoOfHearts() {
        assertFalse(twoOfHearts.hasBeenFound(), "twoOfHearts hasBeenFound");
    }

    /**
     * Student Test hasBeenFound() for seven of spades
     */
    @Test
    public void testHasBeenFoundSevenOfSpades() {
        assertFalse(sevenOfSpades.hasBeenFound(), "sevenOfSpades hasBeenFound");
    }

    /**
     * Test setHasBeenFound() for two of hearts
     */
    @Test
    public void testSetHasBeenFoundTwoOfHearts() {
        twoOfHearts.setHasBeenFound(true);
        assertTrue(twoOfHearts.hasBeenFound(), "twoOfHearts setHasBeenFound true");
        twoOfHearts.setHasBeenFound(false);
        assertFalse(twoOfHearts.hasBeenFound(), "twoOfHearts setHasBeenFound false");
    }

    /**
     * Student Test setHasBeenFound() for seven of spades
     */
    @Test
    public void testSetHasBeenFoundSevenOfSpades() {
        sevenOfSpades.setHasBeenFound(true);
        assertTrue(sevenOfSpades.hasBeenFound(), "sevenOfSpades setHasBeenFound true");
        sevenOfSpades.setHasBeenFound(false);
        assertFalse(sevenOfSpades.hasBeenFound(), "sevenOfSpades setHasBeenFound false");
    }

    /**
     * Test hasSameValue() for two of hearts
     */
    @Test
    public void testHasSameValue() {
        assertTrue(twoOfHearts.hasSameValue(new Card('c', 2)), "twoOfHearts has same value");
        assertFalse(twoOfHearts.hasSameValue(new Card('s', 3)), "twoOfHearts has different value");
    }

    /**
     * Student Test hasSameValue() for seven of spades
     */
    @Test
    public void testHasSameValue2() {
        assertTrue(sevenOfSpades.hasSameValue(new Card('c', 7)), "sevenOfSpades has same value");
        assertFalse(sevenOfSpades.hasSameValue(new Card('s', 3)),
            "sevenOfSpades has different value");
    }

    /**
     * Test hasSameValueAndColor() for two of hearts
     */
    @Test
    public void testHasSameValueAndColor() {
        assertTrue(twoOfHearts.hasSameValueAndColor(new Card('d', 2)),
                   "twoOfHearts has same value and color");
        assertFalse(twoOfHearts.hasSameValueAndColor(new Card('s', 3)),
                    "twoOfHearts has different value and color");
        assertFalse(twoOfHearts.hasSameValueAndColor(new Card('d', 8)),
                    "twoOfHearts has different value and same color");
        assertFalse(twoOfHearts.hasSameValueAndColor(new Card('s', 2)), 
                    "twoOfHearts has same value and different color");
    }

    /**
     * Student Test hasSameValueAndColor() for seven of spades
     */
    @Test
    public void testHasSameValueAndColor2() {
        assertTrue(sevenOfSpades.hasSameValueAndColor(new Card('c', 7)),
                   "sevenOfSpades has same value and color");
        assertFalse(sevenOfSpades.hasSameValueAndColor(new Card('h', 3)),
                    "sevenOfSpades has different value and color");
        assertFalse(sevenOfSpades.hasSameValueAndColor(new Card('c', 8)),
                    "sevenOfSpades has different value and same color");
        assertFalse(sevenOfSpades.hasSameValueAndColor(new Card('d', 7)), 
                    "sevenOfSpades has same value and different color");
    }

    /**
     * Test equals() for two of hearts
     */
    @Test
    public void testEqualsTwoOfHearts() {
        assertTrue(twoOfHearts.equals(twoOfHearts), "twoOfHearts equals with same instance");
        assertTrue(twoOfHearts.equals(new Card('h', 2)), 
                   "twoOfHearts equals with different instances");
        assertFalse(twoOfHearts.equals(new Card('h', 4)), "twoOfHearts with different value");
        assertFalse(twoOfHearts.equals(new Card('s', 2)), "twoOfHearts with different suit");
        assertFalse(twoOfHearts.equals(new Card('c', 5)), 
                    "twoOfHearts with different value and suit");
        assertFalse(twoOfHearts.equals(null), "twoOfHearts compared to null object");
        assertFalse(twoOfHearts.equals("twoOfHearts"), "twoOfHearts compared to String");
    }

    /**
     * Student Test equals() for seven of spades
     */
    @Test
    public void testEqualsSevenOfSpades() {
        assertTrue(sevenOfSpades.equals(sevenOfSpades), "sevenOfSpades equals with same instance");
        assertTrue(sevenOfSpades.equals(new Card('s', 7)), 
                   "sevenOfSpades equals with different instances");
        assertFalse(sevenOfSpades.equals(new Card('s', 4)), "sevenOfSpades with different value");
        assertFalse(sevenOfSpades.equals(new Card('h', 7)), "sevenOfSpades with different suit");
        assertFalse(sevenOfSpades.equals(new Card('c', 5)), 
                    "sevenOfSpades with different value and suit");
        assertFalse(sevenOfSpades.equals(null), "sevenOfSpades compared to null object");
        assertFalse(sevenOfSpades.equals("sevenOfSpades"), "sevenOfSpades compared to String");
    }
}
