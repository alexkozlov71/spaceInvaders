import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class Invader extends GameObject{
    private double threshold = 0.5;

    public Invader() {
        super();
    }

    public Invader(List<String> lines) {
        super(lines);
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        if (threshold <= 1 && threshold >= 0) {
            this.threshold = threshold;
        } else {
            log.error("Invalid value provided for invader threshold");
            log.error("Using value of " + getThreshold());
        }
    }
}
