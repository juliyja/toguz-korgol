package toguzKorgool;

import java.io.*;
import java.util.ArrayList;

/**
 * A class in charge of reading and editing the text files. It is used when loading the game either for the first time
 * or from a file containing information about the saved game.
 * It has the ability to save information in a new text file creating a save of the game that can be used by the user
 * when starting the application.
 */
public class FileEditor {

    //Loaded information from either the default file or the saved game file
    private static ArrayList<Integer> dataList;
    private static ArrayList<Boolean> player;

    /**
     * An empty constructor.
     */
    public FileEditor(String fileName){
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
            if (dataList.size() != 20) {
                writeFile(fileName);
                return makeDataArray(fileName);
            }
            br.close();
        }
        catch (IOException e){
            writeFile(fileName);
            return makeDataArray(fileName);
        }
        if(dataList.stream().mapToInt(Integer::intValue).sum() > 162){
            writeFile(fileName);
            return makeDataArray(fileName);
        }
        return dataList;
    }

    public static ArrayList<Integer> getDataList(){
        return dataList;
    }
    /**
     *
     */
    private static void writeFile(String fileName){
        FileWriter writeToFile = null;
        try {
            writeToFile = new FileWriter(fileName, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter printLine = new PrintWriter(writeToFile);

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

    /**
     *
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
            ArrayList<Hole> toWrite = Player_Board.getButtons();

            for(int i = 0; i < Player_Board.getButtons().size(); i++){
                printLine.print(FileLine.getLine(i, toWrite.get(i).getPlayer(), toWrite.get(i).getKorgools())+"\n");
            }
            printLine.close();
            return true;
        }

        public static ArrayList<Boolean> getPlayer(){
            return player;
        }

}
