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
        StringBuilder s = new StringBuilder();
        if(message.equals("")) return "";
        for(StringBuilder str = new StringBuilder(message); str.length() > 0; str.delete(0,numCols*numRows)) {
            System.out.println("str......."+str.toString());
            EncryptorTester.print2DArray(letterBlock);
            fillBlock(str.toString());
            s.append(encryptBlock());

        }
        return s.toString();
    }

}