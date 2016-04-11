/*import java.io.Console;*/
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * Simple program to transform document to list of words. One word per line
 */

public final class DocToWord {

    /** Private constructor.
     * Implemented to stop instantiation of utility class.
     */
    private DocToWord() {
        
    }
    /*
     * I could use some built-in filtering to get rid of special characters, but
     * chose to roll my own
     */

    /**
     * Method to determ if a char is lower case.
     * @param c is the char being checked
     * @return true if c is lower case
     */
    static boolean isLowerCase(char c) {
        return (c >= 'a') & (c <= 'z');
    }

    /**
     * Method to determ if a char is upper case.
     * @param c is the char being checked
     * @return true if c is upper case
     */
    static boolean isUpperCase(char c) {
        return (c >= 'A') & (c <= 'Z');
    }

    /**
     * Method to determ if a char is a special character.
     * @param c is the char being checked
     * @return true if c is special character
     */
    static boolean isSpecial(char c) {
        return c == '\'';
    }

    /**
     * Method to make sure char is either lower, upper, or special case.
     * @param c is the char being checked
     * @return char if c is lower, upper, or special case, else, new line.
     */
    static char filterChar(char c) {
        if (isLowerCase(c) | isUpperCase(c) | isSpecial(c)) {
            return c;
        } else {
            return '\n';
        }
    }

    /**
     * Simple parser to transform a list of text to a list of words.
     * Words printed as found
     * @param line is the string being parsed.
     */
    static void lineToWords(String line) {

        char[] c = line.toCharArray();
        int i = 0;
        boolean inword = false;
        int first = 0;
        int last = 0;

        while (i < c.length) {

            // When a word is detected, find the end of it and print it
            // If white space is found, spit out the word and skip over the
            // white space
            if (filterChar(c[i]) == c[i]) {
                if (!inword) {
                    inword = true;
                    first = i;
                    last = i;
                } else {
                    last++;
                }
            } else {

                if (inword) {
                    System.out.println(line.substring(first, last + 1));
                    inword = false;
                }
                
            }

            i++;
        }

        // push out the last word

        if (inword) {
            System.out.println(line.substring(first, last + 1));
        }
    }

    /**
     * Main method driving the utility class to convert doc to word.
     * @param args is the string array of command line inptus
     */
    public static void main(String[] args) {

        // Create a console input source

        Scanner cnsl;
        try {
            cnsl = new Scanner(new File(args[0]));
        } catch (FileNotFoundException e) {
            System.err.println("Sorry we couldn't find the input file.");
            return;
        }

        // Chew through the doc line by line, spitting out the words

        String line;
        while (cnsl.hasNext()) {
            line = cnsl.nextLine();
            lineToWords(line);
        }

    }

}
