import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameObjectTest {
    List<String> gameObjectList = new ArrayList<>();
    GameObject gameObject;

    @BeforeEach
    void setUp() {
        gameObjectList.add("-o-");
        gameObjectList.add("o-o");
        gameObjectList.add("ooo");
        gameObject = new GameObject(gameObjectList);

    }

    @Test
    void loadFromList() {
        GameObject go = new GameObject(gameObjectList);
        assertEquals("-o-", go.getGameObjectLines().get(0));
    }
    @Test
    void loadSmallOrNullList() {
        List<String> tempList = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> new GameObject(tempList));
        assertThrows(IllegalArgumentException.class, () -> new GameObject(null));
    }

    @Test
    void score() {
        List<String> secondList = gameObjectList;
        secondList.set(0, "---");
        GameObject second = new GameObject(secondList);
        assertTrue(gameObject.score(second) < 1);

        gameObjectList.set(0, "***");
        gameObject = new GameObject(gameObjectList);

        assertEquals(1, gameObject.score(second));

        secondList.add("---");
        GameObject third = new GameObject(secondList);
        assertThrows (IllegalStateException.class, () -> {
            gameObject.score(third);
        });

    }

    @Test
    void getHeight() {
        assertEquals(3, gameObject.getHeight());
    }

    @Test
    void getWidth() {

        assertEquals(3, gameObject.getWidth());
    }

    @Test
    void getGameObjectLines() {

        assertEquals(3,gameObject.getGameObjectLines().size());

    }
}