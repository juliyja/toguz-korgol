package toguzKorgool;

/**
 * A class that is in charge of reading line by line from the text files loading the game. It looks at every line
 * and deconstructs it into simple parts so that it is easier to read from the file and use the information.
 */


public class FileLine {
    private int holeIndex;
    private int kargoosValue;
    private boolean player;

    /**
     * A constructor that takes a line from the file and deconstructs it.
     *
     * @param line - the line to deconstruct in the form of
     *             int-int
     */

    public FileLine(String line)
    {
        holeIndex =  Integer.parseInt(line.split("\\-")[0]);
        player = Boolean.parseBoolean(line.split("\\-")[1]);
        kargoosValue = Integer.parseInt(line.split("\\-")[2]);
    }

    /**
     * Returns a well-formatted string that can be added in the .txt files.
     *
     * @param index The index of the whole that we are writing in the file
     * @param value The value that is in the whole at the provided index
     * @return a well formatted String that follows the structure of the files.
     */
    public static String getLine(int index, boolean player, int value){
        if(index > 19 || index < 0 || value < 0 || value > 162){
            return null;
        }
        return index + "-" + player + "-" + value;
    }

    /**
     * A getter method to return the number of kargools inside the hole (the second part of the line).
     *
     * @return the number of kargools in the hole.
     */
    public int getKargoolsValue(){ return kargoosValue; }

    public boolean getPlayer() {
        return player;
    }
}
