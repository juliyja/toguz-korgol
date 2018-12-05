package toguzKorgool;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotEquals;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class FileLineTest {
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {1, 1, "1-1"}, {0, 5, "0-5"}, {10, 25, "10-25"},
                {0, 0, "0-0"}, {2, 102, "2-102"}, {5, 4, "5-4"},
                {25, 10, null}, {30, 0, null}, {-1 , 0, null},
                {3, -3, null}, {0, 163, null}, {-1, -1, null}
        });
    }


    private int holeIndex;
    private int kargoosValue;
    private String line;

    public FileLineTest(int holeIndex, int kargoosValue, String line) {
        this.holeIndex = holeIndex;
        this.kargoosValue = kargoosValue;
        this.line = line;
    }


    @Test
    public void testGetLinePositive() {
        assertThat(FileLine.getLine(holeIndex, kargoosValue), equalTo(line));
    }

    @Test
    public void testGetLineNegative(){
        assertNotEquals(FileLine.getLine(holeIndex, kargoosValue), line+"\n");
    }

    //TODO a test that it returns the correct number
    @Test
    public void testGetKorgoolsValue() {
        //assertThat(this.getKargoolsValue(), equalTo(kagoolsValue);
    }
}