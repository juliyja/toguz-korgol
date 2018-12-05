package toguzKorgool;

import java.io.*;
import java.util.ArrayList;

/**
 * A class in charge of reading and editing the text files. It is used when loading the game either for the first time
 * or from a file containing information about the saved game.
 * It has the ability to save information in a new text file creating a save of the game that can be used by the user
 * when starting the application.
 * <p>
 * It is a Singleton class so that there are not two instances that can read and potentially edin the information at
 * the same time.
 */
public class FileEditor {

    //The instance of the FileEditor making it a singleton.
    private static FileEditor instance;
    //Loaded information from either the default file or the saved game file
    private static ArrayList<Integer> dataList;

    /**
     * An empty constructor.
     */
    public FileEditor(boolean newGame) throws IOException {
        if(newGame){
            makeDataArray("default.txt");
            instance = this;
        }
        else{
            makeDataArray("save.txt");
            instance = this;
        }
    }


    /**
     * TODO: HANDLE THE FILE NOT FOUND EXCEPTION BY JUST MAKING A DEFAULT ARRAY AND SAVING IT TO A TEXT FILE.
     * Create an array of integers based on the information in the text file.
     * If the final arrayList has different than 20 entries there is an error with the data in the file.
     *
     * @param fileName The name of the file we are reading from.
     * @throws IOException if the file cannot be opened or the data cannot be read for any reason.
     */
    private static ArrayList<Integer> makeDataArray(String fileName) {
        dataList = new ArrayList<>();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("./src/main/java/toguzKorgool/"+fileName));
        } catch (IOException e) {
                writeFile(fileName);
                return makeDataArray(fileName);
            }
            String line;
        try {
            while ((line = br.readLine()) != null) {
                FileLine newLine = new FileLine(line);
                dataList.add(newLine.getKargoolsValue());
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
        return dataList;
    }

    public static ArrayList<Integer> getDataList(){
        return dataList;
    }
    /**
     * TODO: USE THIS METHOD TO WRITE A NEW DEFAULT FILE IN CASE THE OLD ONE WAS CORRUPTED OR MISSING.
     *              THE GAME NEEDS TO TURN ON EVEN IF THE FILE IS MESSED UP
     */
    private static void writeFile(String fileName){
        FileWriter writeToFile = null;
        try {
            writeToFile = new FileWriter("./src/main/java/toguzKorgool/"+fileName, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter printLine = new PrintWriter(writeToFile);

        for(int i = 0; i < 20; i++){
            if(i == 0 || i == 10){
                printLine.print(FileLine.getLine(i, 0) + "\n");
            }
            else printLine.print(FileLine.getLine(i, 9) + "\n");
        }
        printLine.close();
    }

    /**
     * TODO: GET AN ARRAY OF ALL THAT IS STORED IN EACH BUTTON OF THE BOARD AND USE THAT TO WRITE A NEW FILE
     *              CALL THE METHOD IN THE FILE_LINE CLASS TO RETURN A WELL FORMATTED LINE OF CODE
     *              AND THEN JUST WRITE IT IN THE FILE
     *              IF IT IS MISSING MAKE IT
     *              IF IT EXISTS DELETE WHATEVER IS THERE
     *              CHECK THAT EVERYTHING HAS BEEN PROPERLY SAVED IN THE FILE AND RETURN TRUE
     *              IF AT ANY MOMENT ANYTHING GOES WRONG RETURN FALSE
     * @return true if the game has been properly saved and false otherwise.
     */
        public static void saveGame() throws IOException {
            FileWriter writeToFile = new FileWriter("./src/main/java/toguzKorgool/save.txt", false);
            PrintWriter printLine = new PrintWriter(writeToFile);

            ArrayList<Hole> toWrite = Player_Board.getButtons();

            for(int i = 0; i < Player_Board.getButtons().size(); i++){
                printLine.print(FileLine.getLine(i, toWrite.get(i).getKorgools())+"/n");
            }
            printLine.close();
        }

        public static FileEditor getInstance(){
            return instance;
        }

    /**
      * TODO: JUST FOR TESTING SO REMOVE ASAP
     * @param args
     * @throws IOException
     */
    public static void main(String[] args){
        makeDataArray("default.txt");
        for(int i = 0; i < dataList.size(); i++){
            System.out.println(dataList.get(i));
        }
    }

}
