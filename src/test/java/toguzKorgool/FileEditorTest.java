package toguzKorgool;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class FileEditorTest {
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {1, 1, "1-1"}, {0, 5, "0-5"}, {10, 25, "10-25"},
                {0, 0, "0-0"}, {2, 102, "2-102"}, {5, 4, "5-4"},
                {25, 10, null}, {30, 0, null}, {-1, 0, null},
                {3, -3, null}, {0, 163, null}, {-1, -1, null}
        });
    }

    private static ArrayList<Integer> dataList;

}
