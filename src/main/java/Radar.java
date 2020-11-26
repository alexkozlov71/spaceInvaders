import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class Radar extends GameObject {

    GameObject workArea;
    double radarAccuracy = 0.80;

    public Radar(List<String> lines) {
        super(lines);
    }

    public void scan(Invader invader) {

        workArea = addRadarShadow(invader.getWidth() - (int) Math.round(invader.getWidth() * invader.getThreshold()),
                invader.getHeight() - (int) Math.round(invader.getHeight() * invader.getThreshold()));

        List<RadarSector> result = new ArrayList<>();

        log.debug("Scanning for invader ");
        log.debug("\n--------------\n" + invader.toString() + "\n--------------");

        IntStream.range(0, workArea.getHeight() - invader.getHeight())
                .forEach(line -> {
                    IntStream.range(0, workArea.getWidth() - invader.getWidth())
                            .forEach(col -> {

                                RadarSector sector =
                                        takeRadarSector(workArea, line, col, invader.getHeight(), invader.getWidth());

                                if (sector.getGameObject().score(invader) > getRadarAccuracy()) {
                                    System.out.println("line:" + line);
                                    System.out.println("Col:" + col);
                                    System.out.println(sector.getGameObject());
                                    result.add(sector);
                                }
                            });
                        });
    }

    private RadarSector takeRadarSector(GameObject workArea, int line, int col, int height, int width) {

        List<String> list = IntStream.range(line, line + height)
                .mapToObj(l -> workArea.getGameObjectLines().get(l).substring(col, col + width))
                .collect(Collectors.toList());

        return new RadarSector(new GameObject(list), line, col);
    }

    private GameObject addRadarShadow(int x, int y) {

        List<String> workAreaList = new ArrayList<>();
        String s = "*";

        int hight = this.getHeight() + 2 * y;
        int width = this.getWidth() + 2 * x;

        String shadowLine = s.repeat(this.getWidth() + 2 * x);

        for (int i=0; i < x; i++  ) {
            workAreaList.add(shadowLine);
        }

        for (int i = 0; i < getHeight(); i++) {
            String tempLine = s.repeat(x) + getGameObjectLines().get(i) + s.repeat(x);
            workAreaList.add(tempLine);
        }

        for (int i=0; i < x; i++  ) {
            workAreaList.add(shadowLine);
        }
        return new GameObject(workAreaList);
    }

    public double getRadarAccuracy() {
        return radarAccuracy;
    }

    public void setRadarAccuracy(double radarAccuracy) {

        if (radarAccuracy <= 1 && radarAccuracy >= 0) {
            this.radarAccuracy = radarAccuracy;
       } else {
            log.warn("Invalid value for Radar Accuracy");
            log.warn("Value ignored. Using: " + getRadarAccuracy());
        }
    }

    private class RadarSector {
        GameObject gameObject;
        int x;
        int y;


        RadarSector(GameObject gameObject, int x, int y) {
            this.gameObject = gameObject;
            this.x = x;
            this.y = y;
        }

        GameObject getGameObject() {
            return gameObject;
        }
    }
}
