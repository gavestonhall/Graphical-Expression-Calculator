package calculator;

import exception.EmptyStackException;

import java.util.ArrayList;
import java.util.List;

/**
 * A stack of {@link calculator.Entry} values. It is an {@link java.util.ArrayList} based stack.<br>
 * Contains 4 basic methods {@link #push}, {@link #pop}, {@link #top}, {@link #size()}.
 * 
 * @see Entry
 * @author Sam Burchell
 *
 */
public class Stack {
  private List<Entry> entries;

  /**
   * A constructor for the initial creation of the stack. Its purpose is to initialise an empty
   * {@link java.util.ArrayList} of possible entries of type {@link calculator.Entry}.
   */
  public Stack() {
    this.entries = new ArrayList<>();
  }

  /**
   * Adds an entry to the top of the stack.
   * 
   * @param entry The entry you want to push onto the stack.
   */
  public void push(Entry entry) {
    entries.add(entry);
  }

  /**
   * Removes the entry at the top of the stack and returns it.
   * 
   * @return The entry at the top of the stack.
   * @throws EmptyStackException Thrown to indicate that an invalid method (top/pop) has been called
   *         on the stack because it is empty.
   */
  public Entry pop() throws EmptyStackException {
    if (size() == 0) {
      throw new EmptyStackException("EmptyStackException: You cannot call pop on an empty stack!");
    }

    Entry lastEntry = top();
    int lastIndex = size() - 1;
    entries.remove(lastIndex);

    return lastEntry;
  }

  /**
   * Looks at the entry at the top of the stack and returns it without removing it.
   * 
   * @return The entry at the top of the stack.
   * @throws EmptyStackException Thrown to indicate that an invalid method (top/pop) has been called
   *         on the stack because it is empty.
   */
  public Entry top() throws EmptyStackException {
    if (size() == 0) {
      throw new EmptyStackException("EmptyStackException: You cannot call top on an empty stack!");
    }

    int lastIndex = size() - 1;
    Entry lastEntry = entries.get(lastIndex);

    return lastEntry;
  }

  /**
   * Returns the number of entries stored in the stack.
   * 
   * @return The number of entries stored in a stack.
   */
  public int size() {
    return entries.size();
  }

  /**
   * Returns a string representation of the stack. This would consist of all entries enclosed in
   * square brackets ("[]") using {@link calculator.Entry#toString}, where each entry is separated
   * by a comma. The beginning entry represents the bottom of the stack and the end entry represents
   * the top of the stack.
   * 
   * @return A string representation of the stack.
   */
  public String toString() {
    return entries.toString();
  }
}
