/**
 * Object oriented program that represents the matchgame
 * 
 * @author Max Farthing
 */
public class MatchGame {

    /** amount of rows in game */
    public static final int ROWS = 4;
    
    /** amount of columns in game */
    public static final int COLS = 13;

    /** Deck object */
    private Deck deck;

    /** Grid object */
    private Grid grid;

    /** Number of correct guess */
    private int numberOfCorrectGuesses;

    /** number of total guesses */
    private int numberOfGuesses;

    /** boolean if game was easy */
    private boolean isEasy;

    /**
     * Constructor method for initalizing the matchgame object
     * 
     * @param isTesting if game is being run in testing mode
     * @param isEasy if game is being run in easy mode
     */
    public MatchGame(boolean isTesting, boolean isEasy){
        this.isEasy = isEasy;
        
        this.deck = new Deck();
        
        this.grid = new Grid(ROWS, COLS);
        if(isTesting == false){
            deck.shuffle();
        } 
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){
                grid.setCard(i, j, deck.nextCard());
            }
        }
        
    }

    /**
     * Getter method for number of guesses 
     * 
     * @return number of guesses
     */
    public int getNumberOfGuesses(){
        return numberOfGuesses;
    }

    /**
     * Getter method for correct number of guesses
     * 
     * @return number of correct guesses
     */
    public int getNumberOfCorrectGuesses(){
        return numberOfCorrectGuesses;
    }

    /**
     * getter method for number of guesses
     * 
     * @return number of guesses
     */
    public double getGuessAverage(){
        if(numberOfGuesses == 0){
            return 0;
        }
        double d = numberOfCorrectGuesses;
        double i = numberOfGuesses;
        double guessAvg = d / i;
        return guessAvg;
    }

    /**
     * getter method for get card name
     * 
     * @param row row of card
     * @param col column of card
     * @return string of card name
     * @throws IllegalArgumentException for invalid row/col
     */
    public String getCardName(int row, int col){
        if(row < 0 || row > ROWS){
            throw new IllegalArgumentException("Invalid row");
        }
        if(col < 0 || col > COLS){
            throw new IllegalArgumentException("Invalid col");
        }
        
        return grid.getCard(row, col).toString();
    }

    /**
     * method that returns if card has been found
     * 
     * @param row row of card
     * @param col column of card
     * @return boolean if card has been found
     * @throws IllegalArgumentException for invalid row/col
     */
    public boolean hasBeenFound(int row, int col){
        if(row < 0 || row > ROWS){
            throw new IllegalArgumentException("Invalid row");
        }
        if(col < 0 || col > COLS){
            throw new IllegalArgumentException("Invalid col");
        }
        
        if(grid.getCard(row, col).hasBeenFound() == true){
            return true;
        }

        return false;
    }

    /**
     * checks if 2 cards are matches
     * 
     * @param card1Row row of card 1 
     * @param card1Col column of card 1
     * @param card2Row row of card 2 
     * @param card2Col column of card 2 
     * @return boolean if 2 cards are matches
     * @throws IllegalArgumentException for invalid rows/col
     */
    public boolean isMatch(int card1Row, int card1Col, int card2Row, int card2Col){
        if(card1Row < 0 || card1Row > ROWS){
            throw new IllegalArgumentException("Invalid card1Row");
        }
        if(card1Col < 0 || card1Col > COLS){
            throw new IllegalArgumentException("Invalid card1Col");
        }
        if(card2Row < 0 || card2Row > ROWS){
            throw new IllegalArgumentException("Invalid card2Row");
        }
        if(card2Col < 0 || card2Col > COLS){
            throw new IllegalArgumentException("Invalid card2Col");
        }
        
        numberOfGuesses++;

        if(isEasy == true &&
            grid.getCard(card1Row, card1Col).hasSameValue(grid.getCard(card2Row, card2Col)) &&
            hasBeenFound(card1Row, card1Col) != true && hasBeenFound(card2Row, card2Col) != true){
            numberOfCorrectGuesses++;
            grid.getCard(card1Row, card1Col).setHasBeenFound(true);
            grid.getCard(card2Row, card2Col).setHasBeenFound(true);
            return true;
        }
        if(isEasy == false &&
            grid.getCard(card1Row, card1Col).hasSameValueAndColor(grid.getCard(card2Row, card2Col))
            && grid.getCard(card2Row, card2Col).hasBeenFound() != true &&
            grid.getCard(card1Row, card1Col).hasBeenFound() != true){
            numberOfCorrectGuesses++;
            grid.getCard(card1Row, card1Col).setHasBeenFound(true);
            grid.getCard(card2Row, card2Col).setHasBeenFound(true);
            return true;
        }
        return false;
    }

    /**
     * Getter method for deck
     * 
     * @return Deck 
     */
    public Deck getDeck(){
        return deck;
    }

    /**
     * Getter method for Grid
     * 
     * @return Grid
     */
    public Grid getGrid(){
        return grid;
    }
}
