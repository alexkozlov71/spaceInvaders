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
        radarList.add("oo------o");
        radarList.add("---ooo---");
        radarList.add("---ooo-oo");
        radarList.add("o------oo");
        radar = new Radar(radarList);
    }

    @Test
    void scan() {
        Invader invader = new Invader(List.of("ooo", "ooo"));
        invader.setThreshold(0.5);
        radar.setRadarAccuracy(0.85);
        List<String> scan = radar.scan(invader);
        assertEquals("oo       ", scan.get(0));
        assertEquals("   ooo   ", scan.get(1));
        assertEquals("   ooo oo", scan.get(2));
        assertEquals("       oo", scan.get(3));
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