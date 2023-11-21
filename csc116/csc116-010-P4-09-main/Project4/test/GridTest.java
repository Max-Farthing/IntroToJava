import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests Grid class
 * @author Suzanne Balik
 */
public class GridTest {
    
    /** Grid for testing */
    private Grid grid;

    /**
     * Creates Grid for testing
     */
    @BeforeEach
    public void setUp() {
        grid = new Grid(2, 3);
    }

    /**
     * Tests Constructor preconditions
     */
    @Test
    public void testConstructorPreConditions() {
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> new Grid(0, 2), "Grid with 0 rows");
        assertEquals("Invalid rows/cols", exception.getMessage(),
                "Testing Grid with 0 rows message");
        
        exception = assertThrows(IllegalArgumentException.class,
            () -> new Grid(3, -4), "Grid with -4 cols");
        assertEquals("Invalid rows/cols", exception.getMessage(),
                "Testing Grid with -4 cols message");
    
    }

    /**
     * Tests setCard() preconditions
     */
    @Test
    public void testSetCardPreConditions() {
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> grid.setCard(1, 1, null), "Null card");
        assertEquals("Null card", exception.getMessage(),
                "Testing setCard with Null card message");
                
        exception = assertThrows(IllegalArgumentException.class,
            () -> grid.setCard(5, 1, new Card('c', 2)), "Invalid row");
        assertEquals("Invalid row", exception.getMessage(),
                "Testing setCard with Invalid row message");
                
        exception = assertThrows(IllegalArgumentException.class,
            () -> grid.setCard(0, -1, new Card('c', 2)), "Invalid col");
        assertEquals("Invalid col", exception.getMessage(),
                "Testing setCard with Invalid col message");
                
    }


    /**
     * Tests getCard() preconditions
     */
    @Test
    public void testGetCardPreConditions() {
    
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> grid.getCard(5, 1), "Invalid row");
        assertEquals("Invalid row", exception.getMessage(),
                "Testing getCard with Invalid row message");
                
        exception = assertThrows(IllegalArgumentException.class,
            () -> grid.getCard(0, -1), "Invalid col");
        assertEquals("Invalid col", exception.getMessage(),
                "Testing getCard with Invalid col message");    
  
    }

    /**
     * Tests setCard() and getCard()
     */
    @Test
    public void testSetAndGetCard() {
        grid.setCard(0, 2, new Card('h', 8));
        assertEquals(new Card('h', 8), grid.getCard(0, 2), "Card ");
    }

    /** 
     * Tests toString()
     */
    @Test
    public void testToString() {
        for (int j = 0; j < 3; j++) {
            grid.setCard(0, j, new Card('c', j + 2));
        }
        for (int j = 0; j < 3; j++) {
            grid.setCard(1, j, new Card('d', j + 2));
        }
        String expected = "c2 c3 c4\nd2 d3 d4\n";
        assertEquals(expected, grid.toString(), "toString  after constructed");
    }
}
