package toguzKorgool;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertFalse;

    @RunWith(Parameterized.class)
    public class FileLineTest {

        @Parameters
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][] {
                    {1, "true", 9, "1-true-9"}, {0, "false", 10, "0-false-10"},
                    {20, "false", 0, null}, {-1, "false", 0, null},
                    {1, "false", -1, null}, {1, "false", 163, null},
                    {1, "yes", 1, "1-false-1"}, {10, "none", 10, "10-false-10"}
            });
        }

        private int holeIndex;
        private int kargoosValue;
        private boolean player;
        private String line;
        private FileLine fileLine;


        public FileLineTest(int holeIndex, String player, int kargoosValue, String line) {
            this.holeIndex = holeIndex;
            this.player = Boolean.parseBoolean(player);
            this.kargoosValue = kargoosValue;
            this.line = line;
            fileLine = new FileLine(line);
        }

    @Test
    public void getLinePositive() {
            assertThat(FileLine.getLine(holeIndex, player, kargoosValue), equalTo(line));
    }

    @Test
    public void getLineNegative(){
            String tempLine = line + '\n';
            assertNotEquals(FileLine.getLine(holeIndex, player, kargoosValue), tempLine);
    }

    @Test
    public void getKargoolsValuePositive() {
        if(line == null){
                assertThat(fileLine.getKargoolsValue(), equalTo(-1));
        }
        else{
            assertThat(fileLine.getKargoolsValue(), equalTo(kargoosValue));
        }
    }

    @Test
    public void getKargoolsValueNegative() {
            if(line == null){
                assertFalse(fileLine.getKargoolsValue() != -1);
            }
            else {
                assertFalse(fileLine.getKargoolsValue() < (0) && fileLine.getKargoolsValue() > 162);
            }
    }

    @Test
    public void getPlayer() {
            assertThat(fileLine.getPlayer(), equalTo(player));
    }
}