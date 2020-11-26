import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvaderTest {

    List<String> invaderList = List.of("---", "ooo", "---");


    Invader invader = new Invader(invaderList);

    @Test
    void testInvader() {

        assertEquals(3, invader.getGameObjectLines().size());
    }
    @Test
    void getThreshold() {
         assertEquals(0.5, invader.getThreshold());
    }

    @Test
    void setThreshold() {
        invader.setThreshold(0.7);
        assertEquals(0.7, invader.getThreshold());
    }

    @Test
    void getVisibleSize() {

        invader.setThreshold(0.7);
        assertEquals(2, invader.getVisibleWidth());
        assertEquals(2, invader.getVisibleHeight());

        invader.setThreshold(1);
        assertEquals(3, invader.getVisibleWidth());
        assertEquals(3, invader.getVisibleHeight());
    }

}