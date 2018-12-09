package toguzKorgool;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;

@RunWith(Parameterized.class)
public class FileEditorTest {

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                //Correct file (different values from default)
                {new Integer[]{0, 9, 9, 9, 9, 10, 7, 8, 11, 9, 0, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                        new Boolean[]{false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true},
                        "correctFileTest"},
                //Contains a value bigger than 162
                {new Integer[]{0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                        new Boolean[]{false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true},
                        "tooLargeValueTest"},
                //Contains a minus index
                {new Integer[]{0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                        new Boolean[]{false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true},
                        "minusIndexTest"},
                //Contains 1 too many entries
                {new Integer[]{0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                        new Boolean[]{false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true},
                        "tooManyEntriesTest"},
                //Contains a minus value of korgools
                {new Integer[]{0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                        new Boolean[]{false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true},
                        "minusValueTest"},
                //Contains 1 too many korgool (163 rather than 162)
                {new Integer[]{0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                        new Boolean[]{false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true},
                        "manyKorgools"},
                //File name is null
                {new Integer[]{0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                        new Boolean[]{false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true},
                        null}
        });
    }

    private static ArrayList<Integer> expectedDataList;
    private static ArrayList<Boolean> expectedPlayerList;
    private static FileEditor fileEditor;
    private static String fileName;


    public FileEditorTest(Integer[] newExpectedDataList, Boolean[] newExpectedPlayerList, String filename){
        expectedDataList = new ArrayList<>(Arrays.asList(newExpectedDataList));
        expectedPlayerList = new ArrayList<>(Arrays.asList(newExpectedPlayerList));
        fileEditor = new FileEditor(false, filename);
        fileName = filename;
    }


    @Test
    public void getDataList() {
        assertThat(FileEditor.getDataList(), equalTo(expectedDataList));
    }

    @Test
    public void getDataListNotNull() {
        assertNotNull(FileEditor.getDataList());
    }

    @Test
    public void saveGame() {
        assertThat(FileEditor.saveGame(), equalTo(true));
    }

    @Test
    public void getPlayer() {
        assertThat(FileEditor.getPlayer(), equalTo(expectedPlayerList));
    }

    @Test
    public void getPlayerNotNull(){
        assertNotNull(FileEditor.getPlayer());
    }

    @After
    public void desctruct(){
        File newFile = new File("./src/main/java/toguzKorgool/" + fileName + ".txt");
        newFile.delete();
    }

}