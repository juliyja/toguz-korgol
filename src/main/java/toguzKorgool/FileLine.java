package toguzKorgool;

/**
 * A class that is in used to edit file lines and extract the needed information in the required format
 * for the FileEditor class. It formats passed data so that it can be written in a file and accessed by the
 * same class later on.
 *
 *
 * @author Emiliyana Tsanova
 * @version 2018
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
     *
     * @param index The index of the whole that we are writing in the file
     * @param value The value that is in the whole at the provided index
     * @return a well formatted String that follows the structure of the files.
     */
    public static String getLine(int index, boolean player, int value){
        if(index > 19 || index < 0 || value < 0 || value > 162){
            return null;
        }
        return index + "/" + player + "/" + value;
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

    public int getHoleIndex() { return holeIndex; }
}
