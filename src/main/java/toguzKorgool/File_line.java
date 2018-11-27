package toguzKorgool;

/**
 * A class that is in charge of reading line by line from the text files loading the game. It looks at every line
 * and deconstructs it into simple parts so that it is easier to read from the file and use the information.
 */


public class File_line {
    private int holeIndex;
    private int kargoosValue;


    /**
     * A constructor that takes a line from the file and deconstructs it.
     *
     * @param line - the line to deconstruct in the form of
     *             int-int
     */

    public File_line(String line)
    {
        this.holeIndex =  Integer.parseInt(line.split("\\-")[0]);
        this.kargoosValue = Integer.parseInt(line.split("\\-")[1]);
    }

    /**
     * Returns a well-formatted string that can be added in the .txt files.
     *
     * @param index The index of the whole that we are writing in the file
     * @param value The value that is in the whole at the provided index
     * @return a well formatted String that follows the structure of the files.
     */
    public static String getLine(int index, int value){
        if(index > 19){
            return null;
        }
        return index + "-" + value;
    }
    /**
     * A getter method to return the index of the hole (the first part of the line).
     *
     * @return the index of the hole.
     */
    public int getHoleIndex(){ return holeIndex; }

    /**
     * A setter method to set the index of the hole.
     *
     * @param holeIndex the new index of the whole.
     */
    public void setHoleIndex(int holeIndex) {this.holeIndex = holeIndex; }

    /**
     * A getter method to return the number of kargools inside the hole (the second part of the line).
     *
     * @return the number of kargools in the hole.
     */
    public int getKargoosValue(){ return kargoosValue; }

    /**
     * A setter method to set the number of kargools in the whole.
     *
     * @param kargoosValue the new number of kargools in the whole.
     */
    public void setKargoosValue(int kargoosValue) { this.kargoosValue = kargoosValue; }
}
