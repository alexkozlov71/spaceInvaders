import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class GameObject {
    private int height;
    private int width;
    private final List<String> gameObjectLines;

    public GameObject(List<String> lines) {
        this.gameObjectLines = new ArrayList<>();
        this.height = 0;
        loadFromList(lines);
    }

    public void loadFromList(List<String> lines) {
        if (lines == null || lines.size() <= 1) {
            throw new IllegalArgumentException("Input list is too small");
        } else {
            for (String line : lines) {
                this.gameObjectLines.add(line);
                this.height++;
            }
            width = gameObjectLines.get(0).length();
        }
    }

    public double score(GameObject o1) {
        int matched = 0;
        double objectSize = (double) getWidth() * getHeight();

        if (this.width != o1.width || this.height != o1.height) {
            throw new IllegalStateException("Should never happen. objects should be equal ");
        }
        for (int i = 0; i < this.getHeight(); i++) {

            char[] line1 = this.getGameObjectLines().get(i).toCharArray();
            char[] line2 = o1.gameObjectLines.get(i).toCharArray();

            for (int j = 0; j < this.getWidth(); j++) {
                if (line1[j] == '*') {
                    matched++;
                } else if (line1[j] == line2[j]) {
                    matched++;
                }
            }
        }
        return matched/objectSize;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public List<String> getGameObjectLines() {
        return gameObjectLines;
    }

    @Override
    public String toString() {
        return gameObjectLines.stream().map(i-> i + "\n").collect(Collectors.joining());
    }

}
