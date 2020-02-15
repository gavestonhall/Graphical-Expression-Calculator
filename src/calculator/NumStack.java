package calculator;

import exception.BadTypeException;
import exception.EmptyStackException;

/**
 * <p>
 * A stack containing number entries.
 * </p>
 * Since Reverse Polish evaluation only uses a stack of numeric values, we provide a facade to hide
 * details.
 * 
 * @author Sam Burchell
 *
 */
public class NumStack {
  private Stack numStack;

  /**
   * Creates a new stack to be used for only number typed entries.
   */
  public NumStack() {
    numStack = new Stack();
  }

  /**
   * Tests whether the NumStack is empty.
   * 
   * @return true if size is 0 otherwise false.
   */
  public boolean isEmpty() {
    return numStack.size() == 0;
  }

  /**
   * Places a number on top of the NumStack.
   * 
   * @param number A float you want to push onto the stack.
   */
  public void push(float number) {
    numStack.push(new Entry(number));
  }

  /**
   * Returns the number on top of the NumStack and removes it.
   * 
   * @return The number on top of the NumStack.
   * @throws EmptyStackException Thrown to indicate that an invalid method (pop) has been called on
   *         the stack because it is empty.
   */
  public float pop() throws EmptyStackException {
    try {
      return numStack.pop().getValue();
    } catch (BadTypeException e) {
      return Float.POSITIVE_INFINITY;
    }
  }
}
