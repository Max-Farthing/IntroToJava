/**
 * object oriented program centered around the Grid of the matching gmae
 * 
 * @author Max Farthing
 */
public class Grid {
    
    /** int variable representing rows */
    private int rows;
    
    /** int variable representing columns */
    private int cols;

    /** 2D array of deck of cards to be played in game */
    private Card[][] cards;

    /**
     * Constructor method for constructing the Grid of the gamebaord
     * 
     * @param rows the rows in the game
     * @param cols columns in the game
     * @throws IllegalArgumentException for invalid rows or cols
     */
    public Grid(int rows, int cols){
        if(rows < 1 || cols < 1){
            throw new IllegalArgumentException("Invalid rows/cols");
        }

        this.cards = new Card[rows][cols];
        this.rows = rows;
        this.cols = cols;
    }

    /**
     * This method sets the card at various grid locations in the game
     * 
     * @param row row of the card
     * @param col column of the card
     * @param card the card object
     * @throws IllegalArgumentException for invalid row/col or null card
     */
    public void setCard(int row, int col, Card card){
        if(card == null){
            throw new IllegalArgumentException("Null card");
        }
        if(row < 0 || row >= rows){
            throw new IllegalArgumentException("Invalid row");
        }
        if(col < 0 || col >= cols){
            throw new IllegalArgumentException("Invalid col");
        }

        cards[row][col] = card;
    }

    /**
     * This method gets the card object 
     * 
     * @param row row of the card
     * @param col column of the card
     * @return card object
     * @throws IllegalArgumentException for invalid row/col
     */
    public Card getCard(int row, int col){
        
        if(row < 0 || row >= rows){
            throw new IllegalArgumentException("Invalid row");
        }
        if(col < 0 || col >= cols){
            throw new IllegalArgumentException("Invalid col");
        }
        
        return cards[row][col];
    }

    /**
     * Returns string representation of grid
     * 
     * @return String representation of grid
     */
    public String toString(){
        String blank = "";
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                blank += cards[i][j].toString() + " ";
            }
            blank = blank.trim();
            blank += "\n";
        }
        return blank;
    }
}
