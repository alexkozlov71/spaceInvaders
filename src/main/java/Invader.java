import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class Invader extends GameObject{
    private double threshold = 0.5;
    private int visibleWidth;
    private int visibleHeight;

    public Invader(List<String> lines) {
        super(lines);
        setVisibleSize();
    }

    private void setVisibleSize() {
        this.visibleWidth = (int) Math.round(getWidth() * getThreshold());
        this.visibleHeight = (int) Math.round(getHeight() * getThreshold());
    }

    public void setThreshold(double threshold) {
        if (threshold <= 1 && threshold > 0) {
            this.threshold = threshold;
            setVisibleSize();
        } else {
            log.warn("Invalid value provided for invader threshold");
            log.warn("Using value of " + getThreshold());
        }
    }

    public int getVisibleWidth() {
        return visibleWidth;
    }

    public int getVisibleHeight() {
        return visibleHeight;
    }

    public double getThreshold() {
        return threshold;
    }
}
