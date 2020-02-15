package exception;

/**
 * An exception that is meant to be thrown when an invalid expression is about to be evaluated. This
 * can be determined by too many operands or operators including invalid characters.
 * 
 * @author Sam Burchell
 *
 */
public class InvalidExpressionException extends Exception {

  private static final long serialVersionUID = -2747911452484801040L;

  public InvalidExpressionException(String errorMsg) {
    super(errorMsg);
  }
}
