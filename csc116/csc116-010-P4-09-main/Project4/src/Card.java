import java.awt.*;

/**
 * Object oriented program centered around the card object
 * 
 * @author Max Farthing
 */
public class Card {
    
    /** Card Suit Clubs */
    public static final char CLUBS = 'c';

    /** Card Suit Diamonds */
    public static final char DIAMONDS = 'd';

    /** Card Suit Hearts */
    public static final char HEARTS = 'h';

    /** Card Suit Spades */
    public static final char SPADES = 's';

    /** Lowest possible card value */
    public static final int LOWEST_VALUE = 2;

    /** Highest possible card value */
    public static final int HIGHEST_VALUE = 14;

    /** Variable for suit of card */
    private char suit;

    /** Variable for value of card */
    private int value;

    /** Variable for Color of card */
    private Color color;

    /** Variable for if card has been found */
    private boolean hasBeenFound;

    /** 
     * Constructor method for initializing Card objects
     * 
     * @param suit suit of card
     * @param value value of card
     * @throws IllegalArgumentException for invalid suit or value
     */
    public Card(char suit, int value){
        if(suit != CLUBS && suit != DIAMONDS && suit != HEARTS && suit != SPADES){
            throw new IllegalArgumentException("Invalid suit");
        }
        if(value < LOWEST_VALUE || value > HIGHEST_VALUE){
            throw new IllegalArgumentException("Invalid value");
        }
        
        if(suit == DIAMONDS || suit == HEARTS){
            this.color = Color.RED;
        }
        if(suit == CLUBS || suit == SPADES){
            this.color = Color.BLACK;
        }

        this.suit = suit;
        this.value = value;
    }

    /**
     * Getter method for suit of card
     * 
     * @return suit
     */
    public char getSuit(){
        return suit;
    }

    /**
     * Getter method for value of card
     * 
     * @return value
     */
    public int getValue(){
        return value;
    }

    /**
     * Getter method for color of card
     * 
     * @return color
     */
    public Color getColor(){
        return color;
    }

    /**
     * Method for checking if card has been found
     * 
     * @return boolean if card has been found
     */
    public boolean hasBeenFound(){
        return hasBeenFound;
    }

    /**
     * Setter method for hasBeenFound
     * 
     * @param hasBeenFound sets is card has been found
     */
    public void setHasBeenFound(boolean hasBeenFound){
        this.hasBeenFound = hasBeenFound;
    }

    /**
     * check if two cards have same value
     * 
     * @param other Another card object
     * @return if cards have same value
     */
    public boolean hasSameValue(Card other){
        if (this.value == other.value){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if 2 cards have same value and color
     * 
     * @param other another card object
     * @return boolean if they're the same in value and color
     */
    public boolean hasSameValueAndColor(Card other){
        if (this.color.equals(other.color) && this.value == other.value){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Equals method checks if card already exists
     * 
     * @param o instance of another object
     * @return boolean if cards are equal or not
     */
    public boolean equals(Object o){
        if (o instanceof Card){
            Card that = (Card) o;
            return this.suit == that.suit &&
                this.value == that.value;
        } else {
            return false;
        }
    }

    /**
     * This method outputs card object in string format
     * 
     * @return String of card object
     */
    public String toString(){
        String blank = "";
        return blank + suit + value;
    }
}
