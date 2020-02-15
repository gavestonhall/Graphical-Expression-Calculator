package calculator;

import exception.BadTypeException;
import exception.EmptyStackException;

/**
 * <p>
 * A stack of symbols (operators).
 * </p>
 * Since the Shunting algorithm only uses a stack of symbols, we provide a facade to hide the
 * details.
 * 
 * @author Sam Burchell
 *
 */
public class OpStack {
  private Stack opStack;

  /**
   * Creates a new stack only to be used with symbol typed entries.
   */
  public OpStack() {
    opStack = new Stack();
  }

  /**
   * Tests whether the OpStack is empty.
   * 
   * @return true if size is 0 otherwise false.
   */
  public boolean isEmpty() {
    return opStack.size() == 0;
  }

  /**
   * Places an operator on top of the OpStack.
   * 
   * @param symbol An operator you want to push onto the stack.
   */
  public void push(Symbol symbol) {
    opStack.push(new Entry(symbol));
  }

  /**
   * Returns the operator at the top of the stack and removes it.
   * 
   * @return The operator on top of the OpStack.
   * @throws EmptyStackException Thrown to indicate that an invalid method (pop) has been called on
   *         the stack because it is empty.
   */
  public Symbol pop() throws EmptyStackException {
    try {
      return opStack.pop().getSymbol();
    } catch (BadTypeException e) {
      return Symbol.INVALID;
    }
  }

  /**
   * Looks at the operator on top of the stack and places it back without removing it.
   * 
   * @return The operator on top of the OpStack.
   * @throws EmptyStackException Thrown to indicate that an invalid method (pop) has been called on
   *         the stack because it is empty.
   */
  public Symbol top() throws EmptyStackException {
    try {
      return opStack.top().getSymbol();
    } catch (BadTypeException e) {
      return Symbol.INVALID;
    }
  }

}
