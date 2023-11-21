import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


/**
 * Program to test PhraseAnalyzer methods
 * @author Suzanne Balik
 * @author Michelle Glatz 
 * @author Max Farthing
 */
public class PhraseAnalyzerTest {

    
    /** Delta value used for average length tests */
    public static final double DELTA = 0.00001;

    /**
     * Test IsValidPhrase with only lower case letters
     */
    @Test
    public void testIsValidPhraseOnlyLowerCaseLetters() {
        String[] phrase = {"apple", "ball", "cat"};
        assertTrue(PhraseAnalyzer.isValidPhrase(phrase), 
                   "PhraseAnalyzer.isValidPhrase({\"apple\", \"ball\", \"cat\"})");
        String[] phraseCopy = {"apple", "ball", "cat"};
        // Test that method does not modify array
        assertArrayEquals(phraseCopy, phrase, 
                          "Check that array not modified");
    }

    /**
     * Test IsValidPhrase with mixed case
     */
    @Test
    public void testIsValidPhraseOnlyLettersMixedCase() {
        String[] phrase = {"Apple", "Ball", "Cat"};
        assertTrue(PhraseAnalyzer.isValidPhrase(phrase), 
                   "PhraseAnalyzer.isValidPhrase({\"Apple\", \"Ball\", \"Cat\"})");
        String[] phraseCopy = {"Apple", "Ball", "Cat"};
        // Test that method does not modify array
        assertArrayEquals(phraseCopy, phrase, 
                          "Check that array not modified");
    }    
  
    /**
     * Test IsValidPhrase with non word letters
     */
    @Test
    public void testIsValidPhraseNonWordLetters() {
        String[] phrase = {"x", "ahl", "wccqxom"};
        assertTrue(PhraseAnalyzer.isValidPhrase(phrase), 
                   "PhraseAnalyzer.isValidPhrase({\"x\", \"ahl\", \"wccqxom\"})");
        String[] phraseCopy = {"x", "ahl", "wccqxom"};
        // Test that method does not modify array
        assertArrayEquals(phraseCopy, phrase, 
                          "Check that array not modified");
    } 
    
    /**
     * Test IsValidPhrase with valid punctuation
     */
    @Test
    public void testIsValidPhraseWithValidPunctuation() {
        String[] phrase = {"apple,", "ball;", "cat.", "happy!"};
        assertTrue(PhraseAnalyzer.isValidPhrase(phrase), 
                   "PhraseAnalyzer.isValidPhrase({\"apple,\", \"ball;\", \"cat.\", \"happy!\"})");
        String[] phraseCopy = {"apple,", "ball;", "cat.", "happy!"};
        assertArrayEquals(phraseCopy, phrase, 
                          "Check that array not modified");
    }  

     
    /**
     * Test IsValidPhrase with multiple punctuation
     */
    @Test
    public void testIsValidPhraseWordWithMultiplePunctuation() {
        String[] phrase = {"Apple;,", "Ball", "Cat"};
        assertFalse(PhraseAnalyzer.isValidPhrase(phrase), 
                   "PhraseAnalyzer.isValidPhrase({\"Apple;,\", \"Ball\", \"Cat\"})");
        String[] phraseCopy = {"Apple;,", "Ball", "Cat"};
        // Test that method does not modify array
        assertArrayEquals(phraseCopy, phrase, 
                          "Check that array not modified");
    } 

    /**
     * Test IsValidPhrase with zero length word
     */
    @Test
    public void testIsValidPhraseZeroLengthWord() {
        String[] phrase = {"", "Ball", "Cat"};
        assertFalse(PhraseAnalyzer.isValidPhrase(phrase), 
                   "PhraseAnalyzer.isValidPhrase({\"\", \"Ball\", \"Cat\"})");
        String[] phraseCopy = {"", "Ball", "Cat"};
        // Test that method does not modify array
        assertArrayEquals(phraseCopy, phrase, 
                          "Check that array not modified");
    } 

    /**
     * Test IsValidPhrase with Zero length word with punctuation
     */
    @Test
    public void testIsValidPhraseZeroLengthWordWithPunctuation() {
        String[] phrase = {"apple", ",", "Cat"};
        assertFalse(PhraseAnalyzer.isValidPhrase(phrase), 
                   "PhraseAnalyzer.isValidPhrase({\"apple\", \",\", \"Cat\"})");
        String[] phraseCopy = {"apple", ",", "Cat"};
        // Test that method does not modify array
        assertArrayEquals(phraseCopy, phrase, 
                          "Check that array not modified");
    } 

    /**
     * Test IsValidPhrase with Null word
     */
    @Test
    public void testIsValidPhraseNullWord() {
        String[] phrase = {"apple", null, "Cat"};
        assertFalse(PhraseAnalyzer.isValidPhrase(phrase), 
                   "PhraseAnalyzer.isValidPhrase({\"apple\", null, \"Cat\"})");
        String[] phraseCopy = {"apple", null, "Cat"};
        // Test that method does not modify array
        assertArrayEquals(phraseCopy, phrase, 
                          "Check that array not modified");
    } 
    
    /**
     * Test IsValidPhrase null phrase
     */
    @Test
    public void testIsValidPhraseNull() {
        assertFalse(PhraseAnalyzer.isValidPhrase(null), 
                   "PhraseAnalyzer.isValidPhrase(null)");
    }

    /**
     * Test IsValidPhrase if empty
     */
    @Test
    public void testIsValidPhraseEmpty() {
        String[] phrase = {};
        assertFalse(PhraseAnalyzer.isValidPhrase(phrase), 
                   "PhraseAnalyzer.isValidPhrase({})");
        String[] phraseCopy = {};
        // Test that method does not modify array
        assertArrayEquals(phraseCopy, phrase, 
                          "Check that array not modified");                  
    }   
    
    /**
     * User Test IsValidPhrase if with numbers
     */
    @Test
    public void testIsValidPhraseNumbers() {
        String[] phrase = {"apple", "do22g", "Cat"};
        assertFalse(PhraseAnalyzer.isValidPhrase(phrase), 
                   "PhraseAnalyzer.isValidPhrase({\"apple\", \"do22gg\", \"Cat\"})");
        String[] phraseCopy = {"apple", "do22g", "Cat"};
        // Test that method does not modify array
        assertArrayEquals(phraseCopy, phrase, 
                          "Check that array not modified");                  
    }   

    /**
     * User Test IsValidPhrase with a "?"
     */
    @Test
    public void testIsValidPhraseQuestion() {
        String[] phrase = {"apple", "do?g", "Cat"};
        assertFalse(PhraseAnalyzer.isValidPhrase(phrase), 
                   "PhraseAnalyzer.isValidPhrase({\"apple\", \"do?gg\", \"Cat\"})");
        String[] phraseCopy = {"apple", "do?g", "Cat"};
        // Test that method does not modify array
        assertArrayEquals(phraseCopy, phrase, 
                          "Check that array not modified");                  
    } 
    
    /**
     * User Test IsValidPhrase with all upper case
     */
    @Test
    public void testIsValidPhraseAllUpperCase() {
        String[] phrase = {"APPLE", "DOG", "CAT"};
        assertTrue(PhraseAnalyzer.isValidPhrase(phrase), 
                   "PhraseAnalyzer.isValidPhrase({\"APPLE\", \"DOG\", \"CAT\"})");
        String[] phraseCopy = {"APPLE", "DOG", "CAT"};
        // Test that method does not modify array
        assertArrayEquals(phraseCopy, phrase, 
                          "Check that array not modified");                  
    } 
     
    /**
     * Test GetWords with punctuation
     */
    @Test
    public void testGetWordsWithPunctuation() {
        String[] phrase = {"apple!", "ball,", "cat;", "done."};
        String[] expected = {"apple", "ball", "cat", "done"};
        assertArrayEquals(expected, PhraseAnalyzer.getWords(phrase), 
                          "PhraseAnalyzer.getWords({\"apple!\", \"ball,\", \"cat;\". \"done.\"})");
        String[] phraseCopy = {"apple!", "ball,", "cat;", "done."};
        // Test that method does not modify array
        assertArrayEquals(phraseCopy, phrase, 
                          "Check that array not modified");                         
    }
    
    /**
     * Test GetWords with no punctuation
     */
    @Test
    public void testGetWordsNoPunctuation() {
        String[] phrase = {"APPLE", "BALL", "CAT"};
        String[] expected = {"APPLE", "BALL", "CAT"};
        assertArrayEquals(expected, PhraseAnalyzer.getWords(phrase), 
                          "PhraseAnalyzer.getWords({\"APPLE\", \"BALL\", \"CAT\"})");
        String[] phraseCopy = {"APPLE", "BALL", "CAT"};
        // Test that method does not modify array
        assertArrayEquals(phraseCopy, phrase, 
                          "Check that array not modified");                            
    }
    
    /**
     * Test GetWords with non word letters
     */
    @Test
    public void testGetWordsNonWordLetters() {
        String[] phrase = {"x", "ahl", "wccqxom"};
        String[] expected = {"x", "ahl", "wccqxom"};
        assertArrayEquals(expected, PhraseAnalyzer.getWords(phrase), 
                          "PhraseAnalyzer.getWords({\"x\", \"ahl\", \"wccqxom\"})");
        String[] phraseCopy = {"x", "ahl", "wccqxom"};
        // Test that method does not modify array
        assertArrayEquals(phraseCopy, phrase, 
                          "Check that array not modified");                           
    } 

    /**
     * User Test GetWords with Mixed Case
     */
    @Test
    public void testGetWordsMixedCase() {
        String[] phrase = {"aPPle", "baLL", "Cat"};
        String[] expected = {"aPPle", "baLL", "Cat"};
        assertArrayEquals(expected, PhraseAnalyzer.getWords(phrase), 
                          "PhraseAnalyzer.getWords({\"aPPle\", \"baLL\", \"Cat\"})");
        String[] phraseCopy = {"aPPle", "baLL", "Cat"};
        // Test that method does not modify array
        assertArrayEquals(phraseCopy, phrase, 
                          "Check that array not modified");                            
    }    
        
    /**
    * Test GetSmallestWords with one 
    */
    @Test
    public void testGetSmallestWordsOne() {
        String[] words = {"apple", "ball", "cat"};
        String expected = "cat";
        assertEquals(expected, PhraseAnalyzer.getSmallestWords(words),
                     "PhraseAnalyzer.getSmallestWords({\"apple\", \"ball\", \"cat\"})");
        String[] wordsCopy = {"apple", "ball", "cat"};
        // Test that method does not modify array
        assertArrayEquals(wordsCopy, words, 
                          "Check that array not modified");                     
    }
    
    /**
    * Test GetSmallestWords same word different case 
    */
    @Test
    public void testGetSmallestWordsSameWordDifferentCase() {
        String[] words = {"apple", "ball", "cat", "CaT"};
        String expected = "cat, CaT";
        assertEquals(expected, PhraseAnalyzer.getSmallestWords(words),
                     "PhraseAnalyzer.getSmallestWords({\"apple\", \"ball\", \"cat\", \"CaT\"})");
        String[] wordsCopy = {"apple", "ball", "cat", "CaT"};
        // Test that method does not modify array
        assertArrayEquals(wordsCopy, words, 
                          "Check that array not modified");                      
    } 

    /**
    * Test GetSmallestWords with multiple of same word 
    */
    @Test
    public void testGetSmallestWordsMultipleSameWord() {
        String[] words = {"apple", "ball", "cat", "cat"};
        String expected = "cat";
        assertEquals(expected, PhraseAnalyzer.getSmallestWords(words),
                     "PhraseAnalyzer.getSmallestWords({\"apple\", \"ball\", \"cat\", \"cat\"})");
        String[] wordsCopy = {"apple", "ball", "cat", "cat"};
        // Test that method does not modify array
        assertArrayEquals(wordsCopy, words, 
                          "Check that array not modified");   
        // Checking that using String equals method rather than ==
        String[] words2 = {"apple", "ball", "cat", new String("cat")};
        assertEquals(expected, PhraseAnalyzer.getSmallestWords(words2),
                     "PhraseAnalyzer.getSmallestWords({\"apple\", \"ball\", \"cat\", \"cat\"})");
        // Test that method does not modify array
        assertArrayEquals(wordsCopy, words2, 
                          "Check that array not modified");                      
                       
    }  

    /**
    * User Test GetSmallestWords with multiple small words 
    */
    @Test
    public void testGetSmallestWordsMultipleDifferentSmallWord() {
        String[] words = {"apple", "hog", "cat", "dog"};
        String expected = "hog, cat, dog" ;
        assertEquals(expected, PhraseAnalyzer.getSmallestWords(words),
                     "PhraseAnalyzer.getSmallestWords({\"apple\", \"hog\", \"cat\", \"dog\"})");
        String[] wordsCopy = {"apple", "hog", "cat", "dog"};
        // Test that method does not modify array
        assertArrayEquals(wordsCopy, words, 
                          "Check that array not modified");                         
                       
    }  

    /**
    * Test GetLargestWords with one 
    */
    @Test
    public void testGetLargestWordsOne() {
        String[] words = {"apple", "ball", "cat"};
        String expected = "apple";
        assertEquals(expected, PhraseAnalyzer.getLargestWords(words),
                     "PhraseAnalyzer.getLargestWords({\"apple\", \"ball\", \"cat\"})");
        String[] wordsCopy = {"apple", "ball", "cat"};
        // Test that method does not modify array
        assertArrayEquals(wordsCopy, words, 
                          "Check that array not modified");                      
    }
    
    /**
    * Test GetLargestWords with words multiple 
    */
    @Test
    public void testGetLargestWordsMultiple() {
        String[] words = {"apple", "kitty", "aaa"};
        String expected = "apple, kitty";
        assertEquals(expected, PhraseAnalyzer.getLargestWords(words),
                     "PhraseAnalyzer.getLargestWords({\"apple\", \"kitty\", \"aaa\"})");
        String[] wordsCopy = {"apple", "kitty", "aaa"};
        // Test that method does not modify array
        assertArrayEquals(wordsCopy, words, 
                          "Check that array not modified");                      
    } 

    /**
    * Test GetLargestWords with multiple same words 
    */
    @Test
    public void testGetLargestWordsMultipleSameWord() {
        String[] words = {"apple", "ball", "cat", "apple"};
        String expected = "apple";
        assertEquals(expected, PhraseAnalyzer.getLargestWords(words),
                     "PhraseAnalyzer.getSmallestWords({\"apple\", \"ball\", \"cat\", \"apple\"})");
        String[] wordsCopy = {"apple", "ball", "cat", "apple"};
        // Test that method does not modify array
        assertArrayEquals(wordsCopy, words, 
                          "Check that array not modified");
        // Checking that using String equals method rather than ==
        String[] words2 = {new String("apple"), "ball", "cat", "apple"};
        assertEquals(expected, PhraseAnalyzer.getLargestWords(words2),
                     "PhraseAnalyzer.getSmallestWords({\"apple\", \"ball\", \"cat\", \"apple\"})");
        // Test that method does not modify array
        assertArrayEquals(wordsCopy, words2, 
                          "Check that array not modified");                      
    }     
   
    /**
     * User Test Getlargestword method using same words different case
     */
    @Test
    public void testGetLargestWordsMultipleSameDifferentCaseWord() {
        String[] words = {"apple", "ApPle", "cat", "apple"};
        String expected = "apple, ApPle";
        assertEquals(expected, PhraseAnalyzer.getLargestWords(words),
                     "PhraseAnalyzer.getSmallestWords({\"apple\", \"ApPle\", \"cat\", \"apple\"})");
        String[] wordsCopy = {"apple", "ApPle", "cat", "apple"};
        // Test that method does not modify array
        assertArrayEquals(wordsCopy, words, 
                          "Check that array not modified");
    } 
    
    /**
    * Test GetAverageWordLength with different lengths
    */
    @Test
    public void testGetAverageWordLengthDifferentLengths() {
        String[] words = {"apple", "ball", "cat"};
        assertEquals(4.00000, PhraseAnalyzer.getAverageWordLength(words), DELTA,
                     "PhraseAnalyzer.getAverageWordLength({\"apple\", \"ball\", \"cat\"})");
                     
        String[] wordsCopy = {"apple", "ball", "cat"};
        // Test that method does not modify array
        assertArrayEquals(wordsCopy, words, 
                          "Check that array not modified");                       
    }
     
   /**
    * Test GetAverageWordLength with non integers 
    */
    @Test
    public void testGetAverageWordLengthNotInteger() {
        String[] words = {"apple", "cat", "XY", "kitty", "dog", "eight", "yes"};
        assertEquals(3.71429, PhraseAnalyzer.getAverageWordLength(words), DELTA, 
                     "PhraseAnalyzer.getAverageWordLength(" + 
                     "{\"apple\",\"cat\",\"XY\",\"kitty\",\"dog\",\"eight\", \"yes\"})");
        String[] wordsCopy = {"apple", "cat", "XY", "kitty", "dog", "eight", "yes"};
        // Test that method does not modify array
        assertArrayEquals(wordsCopy, words, 
                          "Check that array not modified");                       
    }    
    
    /**
    * Test GetAverageWordLength with one word
    */
    @Test
    public void testGetAverageWordLengthOneWord() {
        String[] words = {"onlyone"};
        assertEquals(7.00000, PhraseAnalyzer.getAverageWordLength(words), DELTA, 
                     "PhraseAnalyzer.getAverageWordLength({\"onlyone\"})");
        String[] wordsCopy = {"onlyone"};
        // Test that method does not modify array
        assertArrayEquals(wordsCopy, words, 
                          "Check that array not modified");                       
    }

    /**
    * User Test GetAverageWordLength with multiple words same length
    */
    @Test
    public void testGetAverageWordLengthMultipleSameLengthWord() {
        String[] words = {"apple", "fives", "kitty","eight"};
        assertEquals(5.00000, PhraseAnalyzer.getAverageWordLength(words), DELTA, 
                     "PhraseAnalyzer.getAverageWordLength(" + 
                     "{\"apple\" \"fives\" \"kitty\" \"eight\"})");
        String[] wordsCopy = {"apple", "fives", "kitty","eight"};
        // Test that method does not modify array
        assertArrayEquals(wordsCopy, words, 
                          "Check that array not modified");                       
    }
    
    /**
    * Test GetLetterTally with all lower case
    */
    @Test
    public void testGetLetterTallyAllLowerCase() {
        String[] words = {"apple", "ball", "cat"};
        //                a, b, c, d, e, f, g, h, i, j, k, l, m,
        int[] expected = {3, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 3, 0,
        //                n, o, p, q, r, s, t, u, v, w, x, y, z
                          0, 0, 2, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0};
        assertArrayEquals(expected, PhraseAnalyzer.getLetterTally(words),
                          "PhraseAnalyzer.getLetterTally({\"apple\", \"ball\", \"cat\"})");
        String[] wordsCopy = {"apple", "ball", "cat"};
        // Test that method does not modify array
        assertArrayEquals(wordsCopy, words, 
                          "Check that array not modified");                            
    }    
    
    /**
    * Test GetLetterTally with all mixed case
    */
    @Test
    public void testGetLetterTallyMixedCase() {
        String[] words = {"ApPlE", "BaLL", "Cat"};
        //                a, b, c, d, e, f, g, h, i, j, k, l, m,
        int[] expected = {3, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 3, 0,
        //                n, o, p, q, r, s, t, u, v, w, x, y, z
                          0, 0, 2, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0};
        assertArrayEquals(expected, PhraseAnalyzer.getLetterTally(words),
                          "PhraseAnalyzer.getLetterTally({\"ApPlE\", \"BaLL\", \"Cat\"})");
        String[] wordsCopy = {"ApPlE", "BaLL", "Cat"};
        // Test that method does not modify array
        assertArrayEquals(wordsCopy, words, 
                          "Check that array not modified");                            
    }  

    /**
    * Test GetLetterTally with full alphabet
    */
    @Test
    public void testGetLetterTallyFullAlphabet() {
        String[] words = {"abcdefghijkl", "mnopqrstuvwxyz"};
        //                a, b, c, d, e, f, g, h, i, j, k, l, m,
        int[] expected = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
        //                n, o, p, q, r, s, t, u, v, w, x, y, z
                          1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        assertArrayEquals(expected, PhraseAnalyzer.getLetterTally(words),
                          "PhraseAnalyzer.getLetterTally({\"abcdefghijkl\", \"mnopqrstuvwxyz\"})");
        String[] wordsCopy = {"abcdefghijkl", "mnopqrstuvwxyz"};
        // Test that method does not modify array
        assertArrayEquals(wordsCopy, words, 
                          "Check that array not modified");                            
    }     
           
    /**
    * User Test GetLetterTally with all mixed case
    */
    @Test
    public void testGetLetterTallyUpperCase() {
        String[] words = {"APPLE", "BALL", "CAT"};
        //                a, b, c, d, e, f, g, h, i, j, k, l, m,
        int[] expected = {3, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 3, 0,
        //                n, o, p, q, r, s, t, u, v, w, x, y, z
                          0, 0, 2, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0};
        assertArrayEquals(expected, PhraseAnalyzer.getLetterTally(words),
                          "PhraseAnalyzer.getLetterTally({\"APPLE\", \"BALL\", \"CAT\"})");
        String[] wordsCopy = {"APPLE", "BALL", "CAT"};
        // Test that method does not modify array
        assertArrayEquals(wordsCopy, words, 
                          "Check that array not modified");                            
    }        

    /**
     * Test the PhraseAnalyzer methods with invalid values
     */
    @Test
    public void testInvalidMethods() {

        // Invalid test cases are provided for you below - You do NOT
        // need to add additional invalid tests. Just make sure these
        // pass!
                     
        String[] phrase = {};       
                      
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> PhraseAnalyzer.getWords(null),
                                 "PhraseAnalyzer.getWords(null)");
        assertEquals("Invalid phrase", exception.getMessage(),
                     "Testing PhraseAnalyzer.getWords(null) - " +
                     "exception message when phrase is null");
                     
        exception = assertThrows(IllegalArgumentException.class,
            () -> PhraseAnalyzer.getWords(phrase),
                                 "PhraseAnalyzer.getWords(phrase)");
        assertEquals("Invalid phrase", exception.getMessage(),
                     "Testing PhraseAnalyzer.getWords(phrase) - " +
                     "exception message when phrase has zero length");
                     
        String[] invalidWord = {"abc12"};
        String[] nullWord = {null};
        
        exception = assertThrows(IllegalArgumentException.class,
            () -> PhraseAnalyzer.getWords(invalidWord),
                                 "PhraseAnalyzer.getWords({\"abc12\"})");
        assertEquals("Invalid phrase", exception.getMessage(),
                     "Testing PhraseAnalyzer.getWords({\"abc12\"}) - " +
                     "exception message when phrase is invalid");
                     
        exception = assertThrows(IllegalArgumentException.class,
            () -> PhraseAnalyzer.getWords(nullWord),
                                 "PhraseAnalyzer.getWords({null})");
        assertEquals("Invalid phrase", exception.getMessage(),
                     "Testing PhraseAnalyzer.getWords({null}) - " +
                     "exception message when phrase contains null word");                    
                     
        exception = assertThrows(IllegalArgumentException.class,
            () -> PhraseAnalyzer.getSmallestWords(null),
                                 "PhraseAnalyzer.getSmallestWords(null)");
        assertEquals("Null words", exception.getMessage(),
                     "Testing PhraseAnalyzer.getSmallestWords(words) - " +
                     "exception message when words is null");
        
        String[] words = {};
        
        exception = assertThrows(IllegalArgumentException.class,
            () -> PhraseAnalyzer.getSmallestWords(words),
                                 "PhraseAnalyzer.getSmallestWords({})");
        assertEquals("Zero length", exception.getMessage(),
                     "Testing PhraseAnalyzer.getSmallestWords(words) - " +
                     "exception message when words has zero length");
                     
        String[] words2 = {""};
        String[] words3 = {"ac2"};

        exception = assertThrows(IllegalArgumentException.class,
            () -> PhraseAnalyzer.getSmallestWords(words2),
                                 "PhraseAnalyzer.getSmallestWords({\"\"})");
        assertEquals("Invalid words", exception.getMessage(),
                     "Testing PhraseAnalyzer.getSmallestWords({\"\"}) - " +
                     "exception message when a word has zero length"); 

        exception = assertThrows(IllegalArgumentException.class,
            () -> PhraseAnalyzer.getSmallestWords(words3),
                                 "PhraseAnalyzer.getSmallestWords({\"ac2\"})");
        assertEquals("Invalid words", exception.getMessage(),
                     "Testing PhraseAnalyzer.getSmallestWords({\"ac2\"}) - " +
                     "exception message when a word contains a number"); 

        exception = assertThrows(IllegalArgumentException.class,
            () -> PhraseAnalyzer.getSmallestWords(nullWord),
                                 "PhraseAnalyzer.getSmallestWords({null})");
        assertEquals("Invalid words", exception.getMessage(),
                     "Testing PhraseAnalyzer.getSmallestWords({null}) - " +
                     "exception message when a word is null");                       
                     
        exception = assertThrows(IllegalArgumentException.class,
            () -> PhraseAnalyzer.getLargestWords(null),
                                 "PhraseAnalyzer.getLargestWords(null)");
        assertEquals("Null words", exception.getMessage(),
                     "Testing PhraseAnalyzer.getLargestWords(null) - " +
                     "exception message when words is null");
        
        
        exception = assertThrows(IllegalArgumentException.class,
            () -> PhraseAnalyzer.getLargestWords(words),
                                 "PhraseAnalyzer.getLargestWords({})");
        assertEquals("Zero length", exception.getMessage(),
                     "Testing PhraseAnalyzer.getLargestWords(words) - " +
                     "exception message when words has zero length");
                     

        exception = assertThrows(IllegalArgumentException.class,
            () -> PhraseAnalyzer.getLargestWords(words2),
                                 "PhraseAnalyzer.getLargestWords({\"\"})");
        assertEquals("Invalid words", exception.getMessage(),
                     "Testing PhraseAnalyzer.getLargestWords({\"\"}) - " +
                     "exception message when a word has zero length"); 

        exception = assertThrows(IllegalArgumentException.class,
            () -> PhraseAnalyzer.getSmallestWords(words3),
                                 "PhraseAnalyzer.getLargestWords({\"ac2\"})");
        assertEquals("Invalid words", exception.getMessage(),
                     "Testing PhraseAnalyzer.getLargestWords({\"ac2\"}) - " +
                     "exception message when a word contains a number"); 


        exception = assertThrows(IllegalArgumentException.class,
            () -> PhraseAnalyzer.getLargestWords(nullWord),
                                 "PhraseAnalyzer.getLargestWords({null})");
        assertEquals("Invalid words", exception.getMessage(),
                     "Testing PhraseAnalyzer.getLargestWords({null}) - " +
                     "exception message when a word is null");                     
                     
        exception = assertThrows(IllegalArgumentException.class,
            () -> PhraseAnalyzer.getAverageWordLength(null),
                                 "PhraseAnalyzer.getAverageWordLength(null)");
        assertEquals("Null words", exception.getMessage(),
                     "Testing PhraseAnalyzer.getAverageWordLength(words) - " +
                     "exception message when words is null");
        
        
        exception = assertThrows(IllegalArgumentException.class,
            () -> PhraseAnalyzer.getAverageWordLength(words),
                                 "PhraseAnalyzer.getAverageWordLength({})");
        assertEquals("Zero length", exception.getMessage(),
                     "Testing PhraseAnalyzer.getAverageWordLength(words) - " +
                     "exception message when words has zero length");
                    

        exception = assertThrows(IllegalArgumentException.class,
            () -> PhraseAnalyzer.getAverageWordLength(words2),
                                 "PhraseAnalyzer.getAverageWordLength({\"\"})");
        assertEquals("Invalid words", exception.getMessage(),
                     "Testing PhraseAnalyzer.getAverageWordLength({\"\"}) - " +
                     "exception message when when a word has zero length"); 

        exception = assertThrows(IllegalArgumentException.class,
            () -> PhraseAnalyzer.getAverageWordLength(words3),
                                 "PhraseAnalyzer.getAverageWordLength({\"ac2\"})");
        assertEquals("Invalid words", exception.getMessage(),
                     "Testing PhraseAnalyzer.getAverageWordLength({\"ac2\"}) - " +
                     "exception message when a word contains a number"); 


        exception = assertThrows(IllegalArgumentException.class,
            () -> PhraseAnalyzer.getAverageWordLength(nullWord),
                                 "PhraseAnalyzer.getAverageWordLength({null})");
        assertEquals("Invalid words", exception.getMessage(),
                     "Testing PhraseAnalyzer.getAverageWordLength({null}) - " +
                     "exception message when a word is null");                      

                     
        exception = assertThrows(IllegalArgumentException.class,
            () -> PhraseAnalyzer.getLetterTally(null),
                                 "PhraseAnalyzer.getLetterTally(null)");
        assertEquals("Null words", exception.getMessage(),
                     "Testing PhraseAnalyzer.getLetterTally(words) - " +
                     "exception message when words is null");                     
        
        
        exception = assertThrows(IllegalArgumentException.class,
            () -> PhraseAnalyzer.getLetterTally(words),
                                 "PhraseAnalyzer.getLetterTally({})");
        assertEquals("Zero length", exception.getMessage(),
                     "Testing PhraseAnalyzer.getLetterTally(words) - " +
                     "exception message when words has zero length");
                     

        exception = assertThrows(IllegalArgumentException.class,
            () -> PhraseAnalyzer.getLetterTally(words2),
                                 "PhraseAnalyzer.getLetterTally({\"\"})");
        assertEquals("Invalid words", exception.getMessage(),
                     "Testing PhraseAnalyzer.LetterTally({\"\"}) - " +
                     "exception message when when a word has zero length"); 

        exception = assertThrows(IllegalArgumentException.class,
            () -> PhraseAnalyzer.getLetterTally(words3),
                                 "PhraseAnalyzer.getLetterTally({\"ac2\"})");
        assertEquals("Invalid words", exception.getMessage(),
                     "Testing PhraseAnalyzer.getLetterTally({\"ac2\"}) - " +
                     "exception message when a word contains a number"); 


        exception = assertThrows(IllegalArgumentException.class,
            () -> PhraseAnalyzer.getLetterTally(nullWord),
                                 "PhraseAnalyzer.getLetterTally({null})");
        assertEquals("Invalid words", exception.getMessage(),
                     "Testing PhraseAnalyzer.getLetterTally({null}) - " +
                     "exception message when a word is null");                     
                     
    }
}