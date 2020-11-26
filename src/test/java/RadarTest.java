import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RadarTest {
    Radar radar;
    @BeforeEach
    void setUp() {
        List<String> radarList = new ArrayList<>();
        radarList.add("oo-------");
        radarList.add("---ooo---");
        radarList.add("---ooo---");
        radarList.add("o-------o");
        radar = new Radar(radarList);
    }

    @Test
    void scan() {
        Invader invader = new Invader(List.of("ooo", "ooo"));
        invader.setThreshold(0.7);
        radar.setRadarAccuracy(0.9);
        radar.scan(invader);
    }

    @Test
    void getRadarAccuracy() {
        assertEquals(0.8, radar.getRadarAccuracy());
    }

    @Test
    void setRadarAccuracy() {
        radar.setRadarAccuracy(0.5);
        assertEquals(0.5, radar.getRadarAccuracy());
    }

    @Test
    void testInvalidRadarAccuracy() {
        radar.setRadarAccuracy(2);
        assertEquals(0.8, radar.getRadarAccuracy());
    }
}