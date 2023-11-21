import java.util.Arrays;
//import java.util.Collections;

/**
 * This program allows users to input a phrase or string of words and
 * returns the smallest and largest words, average word length and
 * most and least used letters in the phrase.
 * 
 * @author Max Farthing
 */
public class PhraseAnalyzer {

    /** constant for the length of the alphabet */
    public static final int ALPHABETCOUNT = 26;


    /**
     * Main method asks for user input then retrieves other methods
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // System.out.println("Command line arguments count: " + args.length);
        // for (int i = 0; i < args.length; i++) {
        // System.out.println("args [" + i + "]: \"" + args[i] + "\"");
        // }
        if(args.length == 0){
            System.exit(1);
        }
        String[] phrase = args;
        if (!isValidPhrase(phrase)) {
            System.out.println("Invalid Phrase");
            System.exit(1);
        }

        // System.out.println(Arrays.toString(phrase));
        // System.out.println(Arrays.toString(getWords(phrase)));
        String words[] = getWords(phrase);
        System.out.println("Smallest word(s): " + getSmallestWords(words));
        System.out.println("Largest word(s): " + getLargestWords(words));
        System.out.printf("Average word length: %.2f\n", getAverageWordLength(words));
        String alphabet[] = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
        int[] newphrase = getLetterTally(words);
        int maxamt = newphrase[0];
        String lm = "";
        String mm = "";
        // System.out.println(Arrays.toString(alphabet));
        // System.out.println(Arrays.toString(getLetterTally(phrase)));
        for (int i = 0; i < newphrase.length; i++) {
            if (newphrase[i] > maxamt) {
                maxamt = newphrase[i];
            }
        }
        for (int k = 0; k < newphrase.length; k++) {
            if (newphrase[k] == maxamt) {
                lm += alphabet[k] + ", ";
            }
        }
        if (lm.length() >= 1) {
            lm = lm.substring(0, lm.length() - 2);
        }

        int min = newphrase[0];
        for (int j = 0; j < newphrase.length; j++) {
            if (newphrase[j] > 0 && newphrase[j] < min) {
                min = newphrase[j];
            } else {
                min = 1;
            }
        }
        for (int x = 0; x < newphrase.length; x++) {
            if (newphrase[x] == min) {
                mm += alphabet[x] + ", ";
            }
        }
        if (mm.length() >= 1) {
            mm = mm.substring(0, mm.length() - 2);
        }
        System.out.println("Least frequently used letter(s): " + mm);
        System.out.println("Most frequently used letter(s): " + lm);
    }

    /**
     * This method takes in user phrase and gets the different words
     * 
     * @param phrase requires user phrase
     * @return array of strings containing the words
     * @throws IllegalArgumentException if invalid phrase is input
     */
    public static String[] getWords(String[] phrase) {
        if (!isValidPhrase(phrase)) {
            throw new IllegalArgumentException("Invalid phrase");
        }
        if (phrase.length == 0){
            throw new IllegalArgumentException("Invalid phrase");
        }
        String[] words = Arrays.copyOf(phrase, phrase.length);
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                if (!Character.isLetter(words[i].charAt(j))) {
                    words[i] = words[i].substring(0, j) + words[i].substring(j + 1);
                } 
            }
        }
        return words;
    }

    /**
     * this method takes the smallest string
     * 
     * @param words retrieves the array of words from method words
     * @return a string with the smallest word
     * @throws IllegalArgumentException is words array is null if the length
     *                                  is zero or if any invalid words are input
     */
    public static String getSmallestWords(String[] words) {
        if (words == null) {
            throw new IllegalArgumentException("Null words");
        }
        if (words.length <= 0) {
            throw new IllegalArgumentException("Zero length");
        }
        if (isValidPhrase(words) == false) {
            throw new IllegalArgumentException("Invalid words");
        }

        String temp = words[0];
        String min = "";
        int minlength = 0;
        String duplicates[] = new String[words.length];
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() <= temp.length()) {
                minlength = words[i].length();
                temp = words[i];
            }
        }

        for(int k = 0; k < words.length; k++){
            if(words[k].length() == minlength){
                boolean isduplicate = false;
                for(int l = 0; l < duplicates.length; l++){
                    if(duplicates[l] != null && duplicates[l].equals(words[k])){
                        isduplicate = true;
                    }
                }
                if(isduplicate == false){
                    duplicates[k] = words[k];
                }
                isduplicate = false;
            }
        }

        for (int j = 0; j < duplicates.length; j++) {
            // System.out.println(duplicates[j]);
            if (duplicates[j] != null && duplicates[j].length() == minlength) {
                min += duplicates[j] + ", ";
            }
        }
        
        if (!Character.isLetter(min.length() - 2)) {
            min = min.substring(0, min.length() - 2);
        }
        // System.out.println(minlength);
        // System.out.println(min);
        return min;

    }

    /**
     * this method returns largest word user input
     * 
     * @param words retrieves string of words from getwords method
     * @return returns string of largest word
     * @throws IllegalArgumentException is words array is null if the length
     *                                  is zero or if any invalid words are input
     */
    public static String getLargestWords(String[] words) {
        if (words == null) {
            throw new IllegalArgumentException("Null words");
        }
        if (words.length <= 0) {
            throw new IllegalArgumentException("Zero length");
        }
        if (isValidPhrase(words) == false) {
            throw new IllegalArgumentException("Invalid words");
        }
        // 1 loop find local max length separate loop to compare
        //if 2 words at separate indexes are equal and the index != -1 then 
        //index array at that point == -1. Output cannot be -1
        int maxlength = 0;
        String temp = words[0];
        String max = "";
        String duplicates[] = new String[words.length];

        for (int i = 0; i < words.length; i++) {
            if (words[i].length() >= temp.length()) {
                maxlength = words[i].length();
                temp = words[i];
            }
        }
        
        for(int k = 0; k < words.length; k++){
            if(words[k].length() == maxlength){
                boolean isduplicate = false;
                for(int l = 0; l < duplicates.length; l++){
                    if(duplicates[l] != null && duplicates[l].equals(words[k])){
                        isduplicate = true;
                    }
                }
                if(isduplicate == false){
                    duplicates[k] = words[k];
                }
                isduplicate = false;
            }
        }

        for (int j = 0; j < words.length; j++) {
            if (duplicates[j] != null && words[j].length() == maxlength) {
                max += words[j] + ", ";
            }
        }

        if (!Character.isLetter(max.length() - 2)) {
            max = max.substring(0, max.length() - 2);
        }

        return max;

    }

    /**
     * This method finds average word length
     * 
     * @param words takes all words from method getwords
     * @return return a double amount based on average word length
     * @throws IllegalArgumentException is words array is null if the length
     *                                  is zero or if any invalid words are input
     */
    public static double getAverageWordLength(String[] words) {
        if (words == null) {
            throw new IllegalArgumentException("Null words");
        }
        if (words.length <= 0) {
            throw new IllegalArgumentException("Zero length");
        }
        if (isValidPhrase(words) == false) {
            throw new IllegalArgumentException("Invalid words");
        }
        double avgln = 0.0;
        for (int i = 0; i < words.length; i++) {
            avgln += words[i].length();
        }
        avgln /= words.length;
        return avgln;
    }

    /**
     * This method returns a number amount based upon count of each letter
     * 
     * @param words uses array of strings from getwords method
     * @return returns a number count in array to represent each letter count
     * @throws IllegalArgumentException is words array is null if the length
     *                                  is zero or if any invalid words are input
     */
    public static int[] getLetterTally(String[] words) {
        if (words == null) {
            throw new IllegalArgumentException("Null words");
        }
        if (words.length <= 0) {
            throw new IllegalArgumentException("Zero length");
        }
        if (isValidPhrase(words) == false) {
            throw new IllegalArgumentException("Invalid words");
        }
        int[] tally = new int[ALPHABETCOUNT];
        for (int i = 0; i < words.length; i++) {
            String store = words[i].toUpperCase();
            for (int j = 0; j < store.length(); j++) {
                int ch = store.charAt(j) - 'A';
                tally[ch]++;
            }
        }
        return tally;
    }

    /**
     * Method checks is user input phrase is valid
     * 
     * @param phrase takes user phrase from main method
     * @return boolean whether phrase is valid or not
     */
    public static boolean isValidPhrase(String[] phrase) {
        if(phrase == null || phrase.equals("") || phrase.length == 0){
            return false;
        }
        for (int i = 0; i < phrase.length; i++) {
            if (phrase[i] == null) {
                return false;
            }
            if (phrase[i].isEmpty()){
                return false;
            }
            if (phrase[i].length() == 0) {
                return false;
            }
            if (phrase[i].length() == 1 && !Character.isLetter(phrase[i].charAt(0))) {
                return false;
            }
            if (phrase[i].length() == 1 && Character.isLetter(phrase[i].charAt(0))){
                return true;
            }
            

            for (int j = 0; j < phrase[i].length(); j++) {
                Character ch = phrase[i].charAt(j);
                Character ch1 = phrase[i].charAt(phrase[i].length() - 1);
                Character ch2 = phrase[i].charAt(phrase[i].length() - 2);
                if (!Character.isLetter(ch) && ch != ',' && ch != '!' && ch != '.' && ch != ';') {
                    return false;
                }
                if (!Character.isLetter(ch1) && ch1 != ','
                        && ch1 != '!' && ch1 != '.' && ch1 != ';') {
                    return false;
                } 
                if (!Character.isLetter(ch1) && !Character.isLetter(ch2) && phrase[i].length() > 1){
                    return false;
                }
                if (ch != ch1 && !Character.isLetter(ch)){
                    return false;
                }
               

            }
        }

        return true;

    }

}
