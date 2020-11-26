import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class Invader extends GameObject{
    private double threshold = 0.5;
    private int visibleWidth;
    private int visibleHeight;

    public Invader() {
        super();
    }
    public int getVisibleWidth() {
        return visibleWidth;
    }

    public int getVisibleHeight() {
        return visibleHeight;
    }

    private void setVisibleSize() {
        this.visibleWidth = (int) Math.round(getWidth() * getThreshold());
        this.visibleHeight = (int) Math.round(getHeight() * getThreshold());
    }


    public Invader(List<String> lines) {
        super(lines);
        setVisibleSize();
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        if (threshold <= 1 && threshold >= 0) {
            this.threshold = threshold;
            setVisibleSize();
        } else {
            log.error("Invalid value provided for invader threshold");
            log.error("Using value of " + getThreshold());
        }
    }
}
