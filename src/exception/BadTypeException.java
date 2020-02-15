package exception;

/**
 * An exception which is meant to be thrown whenever an {@link calculator.Entry} getter is called on
 * a bad type.<br><br>
 * For example: An entry may be constructed with a {@link calculator.Symbol} making it a symbol
 * typed entry. So if you called {@link calculator.Entry#getString} it should throw this exception
 * as its meant for a string typed entry.
 * 
 * @see calculator.Entry
 * @author Sam Burchell
 *
 */
public class BadTypeException extends Exception {

  private static final long serialVersionUID = -3574471497600220686L;

  /**
   * Calls the parent class constructor to create an {@link java.lang.Exception} boilerplate object
   * with an additional error message parameter.
   * 
   * @param errorMsg The user-friendly message you want to output to the user.
   */
  public BadTypeException(String errorMsg) {
    super(errorMsg);
  }


}
