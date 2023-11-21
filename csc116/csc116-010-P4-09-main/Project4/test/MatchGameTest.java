import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests MatchGame class
 * @author Suzanne Balik
 * @author
 */
public class MatchGameTest {
    
    /** epsilon used for testing getGuessAverage() method */
    public static final double EPSILON = .001;

    /** Easy MatchGame instance used for testing */
    private MatchGame mgEasy;

    /** Hard MatchGame instance used for testing */
    private MatchGame mgHard;
    
    
    /**
     * Create new Match Game instances for testing
     */
    @BeforeEach
    public void setUp() {
        mgEasy = new MatchGame(true, true);
        mgHard = new MatchGame(true, false);
    }

    /**
     * Test MatchGame constants
     */
    @Test
    public void testConstants() {
        assertEquals(4, MatchGame.ROWS, "ROWS");
        assertEquals(13, MatchGame.COLS, "COLS");
    }

    /**
     * Test constructor
     */
    @Test
    public void testConstructor() {

        assertEquals( 0, mgEasy.getNumberOfGuesses(), "Initial number of guesses");
        assertEquals( 0, mgEasy.getNumberOfCorrectGuesses(), 
                     "Initial number of correct guesses");
        assertEquals(0, mgEasy.getGuessAverage(), EPSILON, "Initial guess average");
        assertEquals("c2", mgEasy.getCardName(0, 0), "Correct card at 0 0");
        assertEquals( "s14", mgEasy.getCardName(3, 12), "Correct card at 3 12");
        assertFalse(mgEasy.hasBeenFound(2, 5), "Not found at 2 5");
    }

    /**
     * Test hasBeenFound() preconditions
     */
    @Test
    public void testHasBeenFoundPreConditions() {
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> mgEasy.hasBeenFound(-3, 2), "Invalid row");
        assertEquals("Invalid row", exception.getMessage(),
                "Correct IllegalArgumentException message");
        
        exception = assertThrows(IllegalArgumentException.class,
            () -> mgEasy.hasBeenFound(3, 15), "Invalid col");
        assertEquals("Invalid col", exception.getMessage(),
                "Correct IllegalArgumentException message");
    }

    /**
     * Test isMatch() preconditions
     */
    @Test
    public void testIsMatchPreConditions() {
        
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> mgEasy.isMatch(-3, 2, 1, 2), "Invalid card1Row");
        assertEquals("Invalid card1Row", exception.getMessage(),
                "Correct IllegalArgumentException message");
        
        exception = assertThrows(IllegalArgumentException.class,
            () -> mgEasy.isMatch(3, 25, 1, 2), "Invalid card1Col");
        assertEquals("Invalid card1Col", exception.getMessage(),
                "Correct IllegalArgumentException message");
                
        exception = assertThrows(IllegalArgumentException.class,
            () -> mgEasy.isMatch(3, 2, 10, 2), "Invalid card2Row");
        assertEquals("Invalid card2Row", exception.getMessage(),
                "Correct IllegalArgumentException message");
        
        exception = assertThrows(IllegalArgumentException.class,
            () -> mgEasy.isMatch(3, 2, 1, -2), "Invalid card2Col");
        assertEquals("Invalid card2Col", exception.getMessage(),
                "Correct IllegalArgumentException message");

    }

    /**
     * Test grid
     */
    @Test
    public void testGrid() {
        Grid grid = mgEasy.getGrid();
        String exp = "c2 c3 c4 c5 c6 c7 c8 c9 c10 c11 c12 c13 c14\n" +
                     "d2 d3 d4 d5 d6 d7 d8 d9 d10 d11 d12 d13 d14\n" +
                     "h2 h3 h4 h5 h6 h7 h8 h9 h10 h11 h12 h13 h14\n" +
                     "s2 s3 s4 s5 s6 s7 s8 s9 s10 s11 s12 s13 s14\n";
        assertEquals(exp, grid.toString(), "Grid is correct");
    }

    /**
     * Test isMatch()
     */
    @Test
    public void testIsMatch() {
        assertTrue(mgEasy.isMatch(0, 0, 1, 0), "Easy game match");
        assertFalse(mgEasy.isMatch(0, 0, 0, 1), "Easy game not match");
        assertFalse(mgEasy.isMatch(0, 0, 2, 0), "Easy game card already found not match");
        assertTrue(mgHard.isMatch(0, 0, 3, 0), "Hard game match");
        assertFalse( mgHard.isMatch(0, 0, 1, 0), "Hard game same value not match");
        assertFalse( mgHard.isMatch(2, 1, 3, 8), "Hard game different value/color not match");
    }

    /** 
     * Test hasBeenFound()
     */
    @Test
    public void testHasBeenFound() {
        assertFalse(mgEasy.hasBeenFound(0, 0));   
        mgEasy.isMatch(0, 0, 1, 0);
        assertTrue(mgEasy.hasBeenFound(0, 0), "Has been found after match");
    }

    /**
     * Test easy game play with 2/5 correct guesses
     */
    @Test
    public void testPlayEasyGame1() {
        mgEasy.isMatch(2, 0, 2, 5);  //Not Match, different values
        mgEasy.isMatch(1, 1, 2, 1);  //Match
        mgEasy.isMatch(3, 8, 2, 8);  //Match
        mgEasy.isMatch(2, 7, 2, 10); //Not match, different values
        mgEasy.isMatch(1, 1, 3, 1);  //Not match, card already found    
        assertEquals(0.4, mgEasy.getGuessAverage(), EPSILON, 
                     "Easy game some correct guesses");
    }

    /**
     * Student Test1 easy game play with 0 correct guesses
     */
    @Test
    public void testPlayEasyGame2() {
        mgEasy.isMatch(2, 0, 2, 5);  //Not Match, different values
        mgEasy.isMatch(1, 1, 2, 2);  //Not Match, different values
        mgEasy.isMatch(3, 8, 2, 9);  //Not Match, different values
        mgEasy.isMatch(2, 7, 2, 10); //Not match, different values
        mgEasy.isMatch(1, 1, 3, 2);  //Not match, card already found    
        assertEquals(0.0, mgEasy.getGuessAverage(), EPSILON, 
                     "Easy game no correct guesses");
    }

    /**
     * Student Test2 easy game play with all correct guesses
     */
    @Test
    public void testPlayEasyGame3() {
        mgEasy.isMatch(2, 0, 2, 0);  //Match
        mgEasy.isMatch(1, 1, 2, 1);  //Match
        mgEasy.isMatch(3, 8, 2, 8);  //Match
        mgEasy.isMatch(2, 7, 2, 7); //Match
        mgEasy.isMatch(3, 3, 2, 3);  //Match    
        assertEquals(1.0, mgEasy.getGuessAverage(), EPSILON, 
                     "Easy game all correct guesses");
    }

    /**
     * Student Test3 hard game play with no correct guesses
     */
    @Test
    public void testPlayHardGame1() {
        mgHard.isMatch(2, 0, 2, 5);  //Not Match, different values
        mgHard.isMatch(3, 1, 1, 1);  //Not Match, different colors
        mgHard.isMatch(3, 8, 1, 8);  //Not Match, different colors
        mgHard.isMatch(2, 7, 2, 10); //Not match, different values
        mgHard.isMatch(3, 1, 1, 1);  //Not match, card already found    
        assertEquals(0.0, mgHard.getGuessAverage(), EPSILON, 
                     "Hard game no correct guesses");
    }

    /**
     * Student Test4 hard game play with some 2/5 correct guesses
     */
    @Test
    public void testPlayHardGame2() {
        mgHard.isMatch(2, 0, 2, 5);  //Not Match, different values
        mgHard.isMatch(0, 1, 3, 1);  //Match
        mgHard.isMatch(1, 8, 2, 8);  //Match
        mgHard.isMatch(2, 7, 2, 10); //Not match, different values
        mgHard.isMatch(1, 1, 3, 1);  //Not match, card already found
        assertEquals(0.4, mgHard.getGuessAverage(), EPSILON, 
                     "Hard game some correct guesses");
    }

    /**
     * Student Test5 hard game play with all correct guesses
     */
    @Test
    public void testPlayHardGame3() {
        mgHard.isMatch(2, 0, 1, 0);  //Match
        mgHard.isMatch(1, 1, 2, 1);  //Match
        mgHard.isMatch(3, 8, 0, 8);  //Match
        mgHard.isMatch(2, 7, 1, 7); //Match
        mgHard.isMatch(3, 1, 0, 1);  //Match   
        assertEquals(1.0, mgHard.getGuessAverage(), EPSILON, 
                     "Hard game all correct guesses");
    }

}