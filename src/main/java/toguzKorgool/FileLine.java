package toguzKorgool;

/**
 * A class that is in charge of reading line by line from the text files loading the game. It looks at every line
 * and deconstructs it into simple parts so that it is easier to read from the file and use the information.
 */


public class FileLine {
    private int holeIndex;
    private int kargoosValue;


    /**
     * A constructor that takes a line from the file and deconstructs it.
     *
     * @param line - the line to deconstruct in the form of
     *             int-int
     */

    public FileLine(String line)
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
        if(index > 19 || index < 0 || value < 0 || value > 162){
            return null;
        }
        return index + "-" + value;
    }

    /**
     * A getter method to return the number of kargools inside the hole (the second part of the line).
     *
     * @return the number of kargools in the hole.
     */
    public int getKargoolsValue(){ return kargoosValue; }

}
