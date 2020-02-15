package patterns;

/**
 * The interface for a view meaning each type of view (i.e ascii or gui) must implement these
 * methods for the application to work correctly.
 * 
 * @author Sam Burchell, adapted from FxObserver @ Moodle
 *
 */
public interface ViewInterface {

  /**
   * Adds a calculator observer.
   * 
   * @param observer The observer to be added.
   */
  public void addCalcObserver(Observer observer);

  /**
   * Gets the expression to be evaluated.
   * 
   * @return The expression to be evaluated.
   */
  public String getExpression();

  /**
   * Sets the answer to be evaluated.
   * 
   * @param result The result of the expression after being evaluated.
   */
  public void setAnswer(String result);
}
