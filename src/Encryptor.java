import java.util.Arrays;

public class Encryptor
{
    /** A two-dimensional array of single-character strings, instantiated in the constructor */
    private String[][] letterBlock;

    /** The number of rows of letterBlock, set by the constructor */
    private int numRows;

    /** The number of columns of letterBlock, set by the constructor */
    private int numCols;

    /** Constructor*/
    public Encryptor(int r, int c)
    {
        letterBlock = new String[r][c];
        numRows = r;
        numCols = c;
    }

    public String[][] getLetterBlock()
    {
        return letterBlock;
    }

    /** Places a string into letterBlock in row-major order.
     *
     *   @param str  the string to be processed
     *
     *   Postcondition:
     *     if str.length() < numRows * numCols, "A" in each unfilled cell
     *     if str.length() > numRows * numCols, trailing characters are ignored
     */
    public void fillBlock(String str)
    {
        int letter = 0;
        for(String[] strs: letterBlock){
            for(int i = 0; i < strs.length && letter < str.length(); i++){
                strs[i] = str.substring(letter,letter+1);
                letter++;
            }
        }
        for(String[] strs: letterBlock){
            for(int i = 0; i < strs.length;i++){
                if(strs[i] == null) strs[i] = "A";
                if(strs[i].equals("")) strs[i] = "A";
            }
        }
    }
    /** Extracts encrypted string from letterBlock in column-major order.
     *
     *  Precondition: letterBlock has been filled
     *
     *  @return  the encrypted string from letterBlock
     */
    public String encryptBlock()
    {
        StringBuilder s = new StringBuilder();
        for(int col = 0; col < numCols; col++){
            for(int row = 0; row < numRows; row++){
                s.append(letterBlock[row][col]);
            }
        }
        return s.toString();
    }
    /** Encrypts a message.
     *
     *  @param message the string to be encrypted
     *
     *  @return  the encrypted message; if message is the empty string,
     *           returns the empty string
     */
    public String encryptMessage(String message)
    {
        StringBuilder result = new StringBuilder();
        if(message.equals("")) return "";
        for(StringBuilder str = new StringBuilder(message); str.length() > 0;str.delete(0,numCols*numRows)) {
            letterBlock = new String[numRows][numCols];
            fillBlock(str.toString());
            result.append(encryptBlock());
        }
        letterBlock = new String[numRows][numCols];
        return result.toString();
    }
    /**  Decrypts an encrypted message. All filler 'A's that may have been
     *   added during encryption will be removed, so this assumes that the
     *   original message (BEFORE it was encrypted) did NOT end in a capital A!
     *
     *   NOTE! When you are decrypting an encrypted message,
     *         be sure that you have initialized your Encryptor object
     *         with the same row/column used to encrypted the message! (i.e.
     *         the “encryption key” that is necessary for successful decryption)
     *         This is outlined in the precondition below.
     *
     *   Precondition: the Encryptor object being used for decryption has been
     *                 initialized with the same number of rows and columns
     *                 as was used for the Encryptor object used for encryption.
     *
     *   @param encryptedMessage  the encrypted message to decrypt
     *
     *   @return  the decrypted, original message (which had been encrypted)
     *
     *   TIP: Feel free to create other helper methods as you see fit
     *        (e.g. a method to decrypt each section of the decrypted message)
     */
    public String decryptMessage(String encryptedMessage)
    {
        StringBuilder result = new StringBuilder();
        int letter = 0;
        letterBlock = new String[numRows][numCols];
        for(StringBuilder str = new StringBuilder(encryptedMessage); str.length() > 0;str.delete(0,numCols*numRows)) {
            if(letter > str.length()) letter = 0;
            for(int col = 0; col < numCols; col++){
                for(int row = 0; row < numRows; row++){
                    letterBlock[row][col] = str.substring(letter,letter+1);
                    letter++;
                    System.out.println("--------------------");
                    EncryptorTester.print2DArray(letterBlock);
                }
            }
            for(String[] strs: letterBlock){
                for(String s: strs){
                    result.append(s);
                    System.out.println("result...."+result.toString());
                }
            }
            letterBlock = new String[numRows][numCols];
        }
        return result.toString();
    }

}