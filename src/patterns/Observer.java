package patterns;

/**
 * The interface for an Observer using the Observer design pattern.
 * 
 * @author Sam Burchell, adapted from FxObserver @ Moodle
 *
 */
@FunctionalInterface
public interface Observer {

  /**
   * Updates the observer when there is a change.
   */
  public void update();
}
