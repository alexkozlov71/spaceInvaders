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

    /*
    Add padding to radar image. Padded sections will be assumed as a 100% match
     to the image outside of the visible area.

     Scan radar by taking snapshots of the screen equal in size to the invader size

     Produce result list of snapshots with sufficient similarity factor


     */
    public List<String> scan(Invader invader) {

        workArea = addRadarShadow(invader.getWidth() - invader.getVisibleWidth(),
                invader.getHeight() - invader.getVisibleHeight());

        List<RadarSector> result = new ArrayList<>();

        log.debug("Scanning for invader ");
        log.debug("\n" + invader.toString());

        IntStream.rangeClosed(0, workArea.getHeight() - invader.getHeight())
                .forEach(line -> {
                    IntStream.rangeClosed(0, workArea.getWidth() - invader.getWidth())
                            .forEach(col -> {

                                RadarSector sector =
                                        takeRadarSector(workArea, line, col, invader.getHeight(), invader.getWidth());

                                if (sector.getGameObject().score(invader) >= getRadarAccuracy()) {
                                    log.debug("line:" + sector.getY());
                                    log.debug("Col:" + sector.getX());
                                    log.debug("\n" + sector.getGameObject().toString());
                                    result.add(sector);
                                }
                            });
                        });

        return getScanImage(result,
                invader.getWidth() - invader.getVisibleWidth(),
                invader.getHeight() - invader.getVisibleHeight());
    }
/*
Remove padding from radar image and produce clean image equal in size to the original radar image size
 */
    private List<String> getScanImage(List<RadarSector> invaders, int padX, int padY) {

        String line = initLine(getWidth() + 2 * padX, ' ');

        List<String> scan = new ArrayList<>();
        List<String> cleanScan = new ArrayList<>();

        IntStream.range(0, getHeight() + 2 * padY).forEach(i -> scan.add(line));

        invaders.forEach(invader -> {
            for (int y = 0; y < invader.getGameObject().getHeight(); y++) {

                StringBuilder sb = new StringBuilder(scan.get(y + invader.getY()));

                StringBuilder returnLine = sb.replace(invader.getX(),
                        invader.getGameObject().getWidth() + invader.getX(),
                        invader.getGameObject().getGameObjectLines().get(y));

                scan.set(y + invader.getY(), returnLine.toString());
            }

        });

        for (int i = padY; i < getHeight() + padY; i++) {
            String str = scan.get(i).substring(padX, getWidth() + padX);
            cleanScan.add(str);
        }


        return cleanScan;
    }

    private String initLine(int width, char pad) {
        String initLine = new String();

        for (int i=0; i < width; i++){
            initLine += pad;
        }

        return initLine;
    }
/*
Take a snapshot of a radar area equal to invader size
 */
    private RadarSector takeRadarSector(GameObject workArea, int line, int col, int height, int width) {

        List<String> list = IntStream.range(line, line + height)
                .mapToObj(l -> workArea.getGameObjectLines().get(l).substring(col, col + width))
                .collect(Collectors.toList());

        return new RadarSector(new GameObject(list), col, line);
    }
/*
Add margin of "*" to radar area.
 */

    private GameObject addRadarShadow(int x, int y) {

        List<String> workAreaList = new ArrayList<>();
        String s = "*";

        int width = this.getWidth() + 2 * x;

        String shadowLine = s.repeat(width);

        for (int i=0; i < y; i++  ) {
            workAreaList.add(shadowLine);
        }

        for (int i = 0; i < getHeight(); i++) {
            String tempLine = s.repeat(x) + getGameObjectLines().get(i) + s.repeat(x);
            workAreaList.add(tempLine);
        }

        for (int i=0; i < y; i++  ) {
            workAreaList.add(shadowLine);
        }
        return new GameObject(workAreaList);
    }

    public double getRadarAccuracy() {
        return radarAccuracy;
    }

    public void setRadarAccuracy(double radarAccuracy) {

        if (radarAccuracy <= 1 && radarAccuracy > 0) {
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

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

    }
}
