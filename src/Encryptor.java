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
            }
        }
    }

}