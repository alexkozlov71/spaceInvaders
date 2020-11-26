import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Game {
    public static void main(String[] args) {
        Invader invader1 = new Invader(readFile("invader-1.txt"));
        Invader invader2 = new Invader(readFile("invader-2.txt"));
        Radar radar = new Radar(readFile("radar.txt"));
        radar.setRadarAccuracy(2);

        invader1.setThreshold(0.7);
        radar.scan(invader1);

        invader2.setThreshold(11);
        radar.scan(invader2);
    }

    public static List<String> readFile(String name) {
        List<String> lines = new ArrayList<>();

        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            URL resource = classloader.getResource(name);

            if (resource == null) {
                throw new IllegalStateException("File not found. Check file name" + name);
            }

            File file = new File(resource.getFile());
            lines = Files.readAllLines(file.toPath());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException("File not found. Check file name " + name, e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
