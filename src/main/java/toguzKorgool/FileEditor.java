package toguzKorgool;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * A class in charge of reading and editing the text files. It is used when loading the game either for the first time
 * or from a file containing information about the saved game.
 * The files the game is originally loaded from are never changed apart from the saved.txt file.
 * It has the ability to save information in a new text file creating a save of the game that can be used by the user
 * when starting the application.
 *
 * @author Emiliyana Tsanova
 * @version 1.0
 */
public class FileEditor {

    //Loaded information from either the default file or the saved game file
    private static ArrayList<Integer> dataList;
    private static ArrayList<Boolean> player;

    /**
     * The constructor for FileEditor class. The fileName is where the game will be loaded from.
     * If the text file is intended only for testing purposes it will be read from a different directory and
     * the name will contain the substring "Test".
     *
     * @param fileName the file the game will be loaded from.
     */
    FileEditor(String fileName){
        if(fileName == null) {
            writeFile("default");
            makeDataArray("./src/resources/default.txt");
        }
        else {
            if (fileName.endsWith(".txt")) {
                new FileEditor(fileName.split(".txt")[0]);
            } else {
                if(fileName.contains("Test")){
                    makeDataArray("./src/resources/test/" + fileName + ".txt");
                }
                else makeDataArray("./src/resources/" + fileName + ".txt");
            }
        }
    }

    /**
     * A method used to save data in the save.txt file.
     * The information to be saved in the file is provided by the PlayerBoard class.
     *
     * @return true if the game has been saved and false otherwise
     */
    public static boolean saveGame() {
        FileWriter writeToFile;
        try {
            writeToFile = new FileWriter("./src/resources/save.txt", false);
        } catch (IOException e) {
            new File("./src/resources/save.txt");
            return saveGame();
        }
        PrintWriter printLine = new PrintWriter(writeToFile);
        ArrayList<Hole> toWrite = PlayerBoard.getButtons();

        for(int i = 0; i < toWrite.size(); i++){
            printLine.print(FileLine.getLine(i, toWrite.get(i).getPlayer(), toWrite.get(i).getKorgools())+"\n");
        }
        printLine.close();
        return true;
    }

    /**
     * Getter method to return the dataList.
     *
     * @return the dataList.
     */
    public static ArrayList<Integer> getDataList(){
        return dataList;
    }

    /**
     * Getter method to return the player list.
     *
     * @return the player list.
     */
    public static ArrayList<Boolean> getPlayer(){
        return player;
    }

    /**
     * A method to initialize this class from a different file.
     *
     * @param fileName of a file to reinitialize
     */
    public static void reinitializeFileEditor(String fileName){
        if (fileName.endsWith(".txt")) {
            new FileEditor(fileName.split(".txt")[0]);
        } else {
            if(fileName.contains("Test")){
                makeDataArray("./src/resources/test/" + fileName + ".txt");
            }
            else makeDataArray("./src/resources/" + fileName + ".txt");
        }
    }

    /**
     * Create an array of integers based on the information in the text file.
     * If the final arrayList has different than 20 entries there is an error with the data in the file.
     *
     * @param fileName The name of the file we are reading from.
     */
    private static ArrayList<Integer> makeDataArray(String fileName) {
        dataList = new ArrayList<>();
        player = new ArrayList<>();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(fileName));
        } catch (IOException e) {
                writeFile(fileName);
                return makeDataArray(fileName);
            }
            String line;
        try {
            while ((line = br.readLine()) != null) {
                FileLine newLine = new FileLine(line);
                if(newLine.getKargoolsValue() == -1 || newLine.getHoleIndex() == -1){
                    writeFile(fileName);
                    return makeDataArray(fileName);
                }
                dataList.add(newLine.getKargoolsValue());
                player.add(newLine.getPlayer());
            }
            if (!validIntegerList(dataList) || !validBooleanList(player)) {
                writeFile(fileName);
                return makeDataArray(fileName);
            }
            br.close();
        }
        catch (IOException e){
            writeFile(fileName);
            return makeDataArray(fileName);
        }
        if(!validDataSum(dataList)){
            writeFile(fileName);
            return makeDataArray(fileName);
        }
        return dataList;
    }

    /**
     * Checks if the length of the provided ArrayList of Itegers is more than 20.
     *
     * @param dataList The arrayList to check the length of.
     * @return true if the length of the list is correct and false otherwise.
     */
    private static boolean validIntegerList(ArrayList<Integer> dataList){
        return dataList.size() == 20;
    }

    /**
     * Checks if the length of the provided ArrayList of Booleans is more than 20.
     *
     * @param dataList The arrayList to check the length of.
     * @return true if the length of the list is correct and false otherwise.
     */
    private static boolean validBooleanList(ArrayList<Boolean> dataList){
        return dataList.size() == 20;
    }

    /**
     * Check if the sum of the data in an arrayList is different than 162.
     *
     * @param dataList the list to check the data sum of.
     * @return true if the sum is 162 and false otherwise.
     */
    private static boolean validDataSum(ArrayList<Integer> dataList){
        return dataList.stream().mapToInt(Integer::intValue).sum() == 162;
    }


    /**
     * Write "default" data in a file. The method is called when the provided text file is missing
     * or corrupted.
     *
     * @param fileName the file to write to.
     */
    private static void writeFile(String fileName){
        FileWriter writeToFile = null;
        try {
            writeToFile = new FileWriter(fileName, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter printLine = new PrintWriter(Objects.requireNonNull(writeToFile));

        for(int i = 0; i < 20; i++){
            if(i < 10){
                if(i == 0){
                    printLine.print(FileLine.getLine(i, false, 0) + "\n");
                }
                else printLine.print(FileLine.getLine(i, false, 9) + '\n');
            }
            else {
                if(i == 10) {
                    printLine.print(FileLine.getLine(i, true, 0) + "\n");
                }
                else printLine.print(FileLine.getLine(i, true, 9) + "\n");
            }
        }
        printLine.close();
    }
}
