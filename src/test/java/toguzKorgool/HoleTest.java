package toguzKorgool;

import javafx.embed.swing.JFXPanel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import javax.swing.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CountDownLatch;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotEquals;


@RunWith(Parameterized.class)
    public class HoleTest {

        @Parameterized.Parameters
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][] {
                    {false, 9, 1}, {true, 10, 11}, {false, 19, 10},
                    {true, 0, 1}, {false, 0, 19}, {false, 1, 14}
            });
        }

        private boolean player;
        private int korgools;
        private boolean tuz = false;
        private Hole hole;
        private int index;

        public HoleTest(boolean player, int korgools, int index){
            final CountDownLatch latch = new CountDownLatch(1);
            SwingUtilities.invokeLater(() -> {
                new JFXPanel();
                latch.countDown();
            });
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.player = player;
            this.korgools = korgools;
            this.index = index;
            hole = new Hole(player, korgools, index);
        }

    @Test
    public void paintButton() {
            if(hole.getPlayer()){
                assertThat(hole.getStyle(), equalTo("-fx-background-color: LightGray"));
            }
            else{
                assertThat(hole.getStyle(), equalTo("-fx-background-color: LightBlue"));
            }
    }

    @Test
    public void getKorgools() {
            assertThat(hole.getKorgools(), equalTo(korgools));
    }

    @Test
    public void setKorgoolsPositive() {
            hole.setKorgools(5);
            assertThat(hole.getKorgools(), equalTo(5));
    }

    @Test
    public void setKorgoolsNegative() {
            int temp = hole.getKorgools();
            hole.setKorgools(-1);
            assertThat(hole.getKorgools(), equalTo(temp));
    }

    @Test
    public void setKorgoolsLarge() {
            int temp = hole.getKorgools();
            hole.setKorgools(163);
            assertThat(hole.getKorgools(), equalTo(temp));
    }
    @Test
    public void clearKorgools() {
            hole.clearKorgools();
            assertThat(hole.getKorgools(), equalTo(0));
    }

    @Test
    public void isTuz() {
            assertThat(hole.isTuz(), equalTo(false));
    }


    @Test
    public void makeTuz() {
            boolean temp = hole.getPlayer();
            String background = hole.getStyle();
            hole.makeTuz();
            assertThat(hole.isTuz(), equalTo(true));
            assertThat(hole.getPlayer(), equalTo(!temp));
            assertNotEquals(hole.getStyle(), background);
    }

    @Test
    public void getPlayer() {
            assertThat(hole.getPlayer(), equalTo(player));
    }
}