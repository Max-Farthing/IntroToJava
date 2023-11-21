import java.util.Arrays;
import java.util.*;

/**
 * Object oriented program centered around Deck object
 * 
 * @author Max Farthing
 */
public class Deck {
    
    /** Constant of number of cards in deck */
    public static final int CARDS_IN_DECK = 52;

    /** Constant for number of cards per suit */
    public static final int CARDS_IN_SUIT = 13;

    /** constants for number of suits in deck */
    public static final int NUMBER_OF_SUITS = 4;

    /** object cards */
    private Card[] cards;

    /** int representing next card */
    private int next;

    /**
     * Constructor class to initialize objects
     * 
     */
    public Deck(){
        this.cards = new Card[CARDS_IN_DECK];
        
        int counter = 0;
        for(int i = 0; i < CARDS_IN_SUIT; i++){
            cards[counter] = new Card(Card.CLUBS, Card.LOWEST_VALUE + i);
            counter++;
        }  

        for(int i = 0; i < CARDS_IN_SUIT; i++){
            cards[counter] = new Card(Card.DIAMONDS, Card.LOWEST_VALUE + i);
            counter++;
        }

        for(int i = 0; i < CARDS_IN_SUIT; i++){
            cards[counter] = new Card(Card.HEARTS, Card.LOWEST_VALUE + i);
            counter++;
        }

        for(int i = 0; i < CARDS_IN_SUIT; i++){
            cards[counter] = new Card(Card.SPADES, Card.LOWEST_VALUE + i);
            counter++;
        }
    }

    /**
     * Getter method for next
     * 
     * @return next
     */
    public int getNext(){
        return next;
    }

    /**
     * Getter method for cards
     * 
     * @return cards
     */
    public Card[] getCards(){
        return cards;
    }

    /**
     * this method uses random class to shuffle the deck of cards
     * 
     */
    public void shuffle(){
        Random rand = new Random();
        int n = cards.length;
        for(int i = n - 1; i >= 1; i--){
            Card temp = cards[i];
            int y = rand.nextInt(i + 1);
            cards[i] = cards[y];
            cards[y] = temp;
        }
    }

    /**
     * This card returns the next card in the deck
     * 
     * @return next card
     * @throws IllegalStateException if no more cards are available
     */
    public Card nextCard(){
        if(next >= CARDS_IN_DECK){
            throw new IllegalStateException("No more cards");
        }
        
        return cards[next++];
    }

    /**
     * This method checks if two objects are equal
     * 
     * @param o instance of object
     * @return boolean if objects are equal or not
     */
    public boolean equals(Object o){
        if(o instanceof Deck){
            Deck that = (Deck) o;
            return Arrays.equals(this.cards, that.cards) &&
                this.next == that.next;
        } else {
            return false;
        }
    }

    /**
     * This method returns a string of the deck of cards
     * 
     * @return String representation of deck
     */
    public String toString(){
        String blank = "";
        for(int i = 0; i < cards.length; i++){
            blank += "card " + i + ": " + cards[i] + "\n";
        }
        return blank;
    }
}
