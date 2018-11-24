package logic.level;

import logic.brick.Brick;

import java.util.List;
import java.util.Observer;

/**
 * Interface that represents the basics of a level to be played on.
 *
 * @author Juan-Pablo Silva, Tomás Torres
 */
public interface Level extends Observer {
    /**
     * Gets the level's name. Each level must have a name.
     *
     * @return the table's name
     */
    String getName();

    /**
     * Gets the number of {@link Brick} in the level.
     *
     * @return the number of Bricks in the level
     */
    int getNumberOfBricks();

    /**
     * Gets the {@link List} of {@link Brick}s in the level.
     *
     * @return the bricks in the level
     */
    List<Brick> getBricks();

    /**
     * Gets the next level of a level object. Each level have a reference to the next level to play.
     *
     * @return the next level
     */
    Level getNextLevel();

    /**
     * Gets whether the level is playable or not.
     *
     * @return true if the level is playable, false otherwise
     */
    boolean isPlayableLevel();

    /**
     * Whether the level's next level is playable or not.
     *
     * @return true if the level's next level is playable, false otherwise
     */
    boolean hasNextLevel();

    /**
     * Gets the total number of points obtainable in level.
     *
     * @return the number of points in the current level
     */
    int getPoints();

    /**
     * Adds a level to the list of levels. This adds the level in the last position of the list.
     *
     * @param level the level to be added
     */
    Level addPlayingLevel(Level level);

    /**
     * Sets the reference for the next level of a level object.
     *
     * @param level the next level of a level object
     */
    void setNextLevel(Level level);

    /**
     *
     * Adds an observer to the set of observers for this object,
     * provided that it is not the same as some observer already in the set.
     *
     * @param game observer to be added
     */
    void addObserverGame(Observer game);

    /**
     *
     * Adds the glass score to the total points of a level, used when generating levels
     *
     */
    void addMaxPointsGlass();

    /**
     *
     * Adds the wood score to the total points of a level, used when generating levels
     *
     */
    void addMaxPointsWood();

    /**
     *
     * gets the current points accumulated in a level
     *
     * @return number of current points
     */
    int getCurrentPoints();
}
