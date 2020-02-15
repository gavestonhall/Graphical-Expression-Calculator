package exception;

/**
 * An exception that is meant to be thrown when you attempt to retrieve an {@link calculator.Entry}
 * from an empty {@link calculator.Stack}.<br><br>
 * For example: if you have just created a stack and you use top or pop, it should throw as there is
 * nothing on the stack to retrieve.
 * 
 * @see calculator.Stack
 * @author Sam Burchell
 *
 */
public class EmptyStackException extends Exception {

  private static final long serialVersionUID = -7629154945369434810L;

  /**
   * Calls the parent class constructor to create an {@link java.lang.Exception} boilerplate object
   * with an additional error message parameter.
   * 
   * @param errorMsg The user-friendly message you want to output to the user.
   */
  public EmptyStackException(String errorMsg) {
    super(errorMsg);
  }
}
