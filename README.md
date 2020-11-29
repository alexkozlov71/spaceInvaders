[![CircleCI](https://circleci.com/gh/alexkozlov71/spaceInvaders/tree/master.svg?style=svg&circle-token=7233c58b3c05482ed2bbf56faa49e325fb087a29)](https://circleci.com/gh/alexkozlov71/spaceInvaders/tree/master)
# Radar Detection for space invaders


## Detect two different invader ships on a noisy radar screen.

Task of the assignment is to detect invader ships on the radar screen. Screen is noisy and 1:1 matching cannot be used. Fuzzy matching will be used to identify invaders on the screen.
For this we will take sectors of the radar screen (equal in size to the invader ship) and calculate similarity factor of this sector to the original invader image.

Similarity factor is calculated by dividing a total number of pixels equal (in radar sector and invader) by total number of pixels in the invader image.

If similarity factor is greater or equal to the radar Accuracy, location and value of the screen sector is saved for future reference.

#### Second iteration of the project.

Logic to match only partially displayed invaders was added. Invader can be partially visible on any border of the radar image. For this purpuse I've added RadarShadow area outside of the visible radar screen.
Section of the invader located in the Shadow Area is assumed to be 100% match and similarity factor is calculated based on the visible sections only.

### Configuration
Percentage of the visible of the invader visible on the radar is control trough invader.setThreshold(Double) value can be between 0 and 1.
Similarity factor of the objects to display on the radar is controlled by radar.setRadarAccuracy(Double) value can be between 0 and 1.


### Project notes.

1. I've assumed that invaders don't rotate on the radar screen and can be only displayed in original orientation. My code can easily be refactored to detect rotation of the invaders.
2. Logic to add Shadow Area to the radar was used to simplify the code and avoid excessive use of nested loops. This way code can stay clean and readable. 




 
