package toguzKorgool;

import java.io.*;
import java.net.URL;
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
public class File_editor{

    //The instance of the File_editor making it a singleton.
    private static File_editor instance;
    //Loaded information from either the default file or the saved game file
    private static ArrayList<Integer> dataList;

    /**
     * An empty constructor.
     */
    public File_editor(boolean newGame) throws IOException {
        if(newGame){
            makeDataArray("./src/main/java/toguzKorgool/default.txt");
            instance = this;
        }
        else{
            makeDataArray("./src/main/java/toguzKorgool/save.txt");
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
    public static ArrayList<Integer> makeDataArray(String fileName) throws IOException {
        dataList = new ArrayList<>();
        File file = new File(fileName);
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(fileName));
            }
            catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("no file");
            return null;
        }
        String line;
        while ((line = br.readLine()) != null) {
            File_line newLine = new File_line(line);
            dataList.add(newLine.getKargoosValue());
        }
        if(dataList.size() != 20){
            System.out.println("File is corrupted");
            return null;
        }
        br.close();
        return dataList;
    }

    public static ArrayList<Integer> getDataList(){
        return dataList;
    }
    /**
     * TODO: USE THIS METHOD TO WRITE A NEW DEFAULT FILE IN CASE THE OLD ONE WAS CORRUPTED OR MISSING.
     *              THE GAME NEEDS TO TURN ON EVEN IF THE FILE IS MESSED UP
     */
    private static void writeDefaultFile(){

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
                printLine.print(File_line.getLine(i, toWrite.get(i).getKorgools()));
            }
            printLine.close();
        }

        public static File_editor getInstance(){
            return instance;
        }

    /**
      * TODO: JUST FOR TESTING SO REMOVE ASAP
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        makeDataArray("default.txt");
        for(int i = 0; i < dataList.size(); i++){
            System.out.println(dataList.get(i));
        }
    }

}
