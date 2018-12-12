package toguzKorgool;

/**
 * A class that is in used to edit file lines and extract the needed information in the required format
 * for the FileEditor class. It formats passed data so that it can be written in a file and accessed by the
 * same class later on.
 *
 *
 * @author Emiliyana Tsanova
 * @version 1.0
 */
public class FileLine {
    private int holeIndex;
    private int kargoosValue;
    private boolean player;

    /**
     * A constructor that takes a line from the file and deconstructs it.
     * The input is validated for correctness.
     *
     * @param line - the line in the form of int/boolean/int
     *             to deconstruct.
     */
    FileLine(String line)
    {
        if(line == null){
            holeIndex = -1;
            player = false;
            kargoosValue = -1;
        }
        else {
            String[] lineSplit = line.split("/");
            holeIndex = Integer.parseInt(lineSplit[0]);
            if(holeIndex < 0 || holeIndex > 20) holeIndex = -1;
            player = Boolean.parseBoolean(lineSplit[1]);
            kargoosValue = Integer.parseInt(lineSplit[2]);
            if(kargoosValue < 0 || kargoosValue > 162) kargoosValue = -1;
        }
    }

    /**
     * Returns a well-formatted string that can be added in the .txt files.
     * The passed data needs to be legal.
     *
     * @param index The index of the Hole that we are writing data for in the file
     * @param player The player the Hole is "owned" by
     * @param value The value that is in the Hole at the provided index
     * @return a well formatted String that follows the structure of the files.
     */
    public static String getLine(int index, boolean player, int value){
        if(index > 19 || index < 0 || value < 0 || value > 162){
            return null;
        }
        return index + "/" + player + "/" + value;
    }

    /**
     * A getter method to return the number of kargools inside the Hole.
     *
     * @return the number of kargools in the hole.
     */
    public int getKargoolsValue(){ return kargoosValue; }

    /**
     * A getter method to return the player the Hole is "owned" by.
     *
     * @return the player the Hole is "owned" by
     */
    public boolean getPlayer() {
        return player;
    }

    /**
     * A getter method to return the index of the current Hole.
     *
     * @return the index of the current Hole.
     */
    public int getHoleIndex() { return holeIndex; }
}
