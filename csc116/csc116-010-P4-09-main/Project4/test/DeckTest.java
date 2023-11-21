import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests Deck class
 * @author Suzanne Balik
 */
public class DeckTest {
    
    /** deck instance for testing */
    private Deck deck;

    /** deck string representation for testing */
    private String deckString;

    /**
     * Creates deck and string representation for testing
     */
    @BeforeEach
    public void setUp() {
        deck = new Deck(); 
        deckString = "";
        int card = 0;
        for (int i = 2; i <= 14; i++) {
            deckString += "card " + card++ + ": c" + i + "\n";
        } 
        for (int i = 2; i <= 14; i++) {
            deckString += "card " + card++ + ": d" + i + "\n";
        } 
        for (int i = 2; i <= 14; i++) {
            deckString += "card " + card++ + ": h" + i + "\n";
        }
        for (int i = 2; i <= 14; i++) {
            deckString += "card " + card++ + ": s" + i + "\n";
        }
    }

    /**
     * Tests constants
     */
    @Test
    public void testConstants() {
        assertEquals(52, Deck.CARDS_IN_DECK, "CARDS_IN_DECK");
        assertEquals(4, Deck.NUMBER_OF_SUITS, "NUMBER_OF_SUITS");
    }

    /**
     * Tests getNext()
     */
    @Test
    public void testGetNext() {
        assertEquals(0, deck.getNext(), "next after constructed");
        deck.nextCard();
        assertEquals(1, deck.getNext(), "next after one card dealt"); 
        for (int i = 1; i <= 9; i++) {
            deck.nextCard();
        }
        assertEquals(10, deck.getNext(), "next after ten cards dealt"); 
    }
    
    /**
     * Tests toString()
     */
    @Test
    public void testToString() {
        assertEquals(deckString, deck.toString(), "toString  after constructed");
    }

    /**
     * Tests nextCard()
     */
    @Test
    public void testNextCard() {
        assertEquals(new Card('c', 2), deck.nextCard(), "next card after constructed");
        assertEquals(new Card('c', 3), deck.nextCard(), "second card");
    }

    /**
     * Tests shuffle()
     */
    @Test
    public void testShuffle() {
        deck.shuffle();
        assertNotSame(deckString, deck.toString(), "deck should change when shuffled");
    }

    /**
     * Test equals()
     */
    @Test
    public void testEquals() {
        Deck sameDeck = new Deck();
        assertTrue(deck.equals(deck), "deck equals with same instance");
        assertTrue(deck.equals(sameDeck), "deck equals with different instances");
        assertFalse(deck.equals(null), "deck compared to null object");
        assertFalse(deck.equals("Deck"), "deck compared to String");
        deck.shuffle();
        assertFalse(sameDeck.equals(deck), "deck compared to shuffled deck");
        deck = new Deck();
        deck.nextCard();
        assertFalse(sameDeck.equals(deck), "deck compared to deck with different next");     
    }
    
    /**
     * Tests exceptions
     */
    @Test
    public void testException() {
        // Testing nextCard() with no more cards
        for (int i = 1; i <= 52; i++ ) {
            deck.nextCard();
        }
        Exception exception = assertThrows(IllegalStateException.class,
            () -> deck.nextCard(), "nextCard() no more cards");
        assertEquals("No more cards", exception.getMessage(),
                "nextCard() no more cards message");
    }
}
