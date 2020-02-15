package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import calculator.Entry;
import calculator.Stack;
import calculator.Symbol;
import exception.EmptyStackException;
import org.junit.Before;
import org.junit.Test;


/**
 * A JUnit 4 test class to test the {@link calculator.Stack} class.
 * 
 * @author Sam Burchell
 *
 */
public class TestStack {
  private Stack stack;
  private Entry numberEntry;
  private Entry symbolEntry;

  /**
   * Initialises a new Stack for each test case.
   * 
   * @throws java.lang.Exception Indicates that there is an exception thrown that needs to be
   *         caught.
   */
  @Before
  public void setUp() throws Exception {
    stack = new Stack();
    numberEntry = new Entry(5);
    symbolEntry = new Entry(Symbol.PLUS);
  }

  /**
   * Test 17: Tests the stack constructor actually initialises the entries field.<br>
   * How did I pass the test: I created the Stack class and constructor that initialised the entries
   * field to a new {@link java.util.ArrayList} then created a basic
   * {@link calculator.Stack#toString} method to return the toString of entries in order to check
   * that the initialisation works as expected. <br>
   * Refactoring: None required.
   */
  @Test
  public void testStackConstructor() {
    assertNotNull("Test 17: testStackConstructor -", stack.toString());
  }

  /**
   * Test 18: Tests that {@link calculator.Stack#push} pushes an {@link calculator.Entry} onto the
   * Stack.<br>
   * How did I pass the test: I pushed an entry onto the stack and expected the hard-coded value for
   * that entry using {@link calculator.Stack#toString}<br>
   * Refactoring: Added variables like lastEntry and lastIndex to make the code read more like
   * English.
   */
  @Test
  public void testPush() {
    stack.push(numberEntry);
    assertEquals("Test 18: testPush -",
        "[Entry(number=5.000000, symbol=N/A, string=5.0, type=Number)]", stack.toString());
  }

  /**
   * Test 19: Tests that {@link calculator.Stack#size} returns the correct size of the stack.<br>
   * How did I pass the test: I made {@link calculator.Stack#size} and returned the expected
   * hard-coded value (15).<br>
   * Refactoring: I returned entries.size() instead of the hard-coded value so that it would work
   * dynamically. NOTE: The size field of {@link calculator.Stack} is redundant when we can just use
   * the size() method which is why I have made the conscious decision to not include it as a field
   * in the stack.
   */
  @Test
  public void testSize() {

    for (int i = 0; i < 15; i++) {
      stack.push(symbolEntry);
    }

    assertEquals("Test 19: testSize -", 15, stack.size());
  }

  /**
   * Test 20: Tests that if we push an entry onto the stack, we should be able to view that it's at
   * the top of the stack.<br>
   * How did I pass the test: I pushed a symbol to the stack and created
   * {@link calculator.Stack#top} that gets the last entry in the stack and returns it.<br>
   * Refactoring: Added variables like lastEntry and lastIndex to make the code read more like
   * English.
   */
  @Test
  public void testPushTop() throws EmptyStackException {
    stack.push(symbolEntry);
    assertEquals("Test 20: testPushTop -", symbolEntry, stack.top());
  }

  /**
   * Test 21: Tests that if we push an entry onto the stack, pop should return the entry we pushed
   * and the entries list should be empty.<br>
   * How did I pass the test: I created {@link calculator.Stack#pop} which saves the last entry to a
   * variable, removes the last entry from the stack and then returns our saved variable.<br>
   * Refactoring: None required.
   * 
   */
  @Test
  public void testPushPop() throws EmptyStackException {
    stack.push(symbolEntry);

    assertEquals("Test 21: testPushPop RETURN -", symbolEntry, stack.pop());
    assertEquals("Test 21: testPushPop REMOVE -", "[]", stack.toString());
  }

  /**
   * Test 22: Tests that {@link exception.EmptyStackException} is thrown when you call pop on an
   * empty stack.<br>
   * How did I pass the test: I created an EmptyStackException class. Made a check in
   * {@link calculator.Stack#pop} to check that if the size was zero, it should throw an
   * EmptyStackException. Added throws to necessary places in previous tests etc.<br>
   * Refactoring: None required.
   */

  @Test(expected = EmptyStackException.class)
  public void testEmptyStackPop() throws EmptyStackException {
    stack.pop();
  }

  /**
   * Test 23: Tests that {@link exception.EmptyStackException} is thrown when you call top on an
   * empty stack.<br>
   * How did I pass the test: Made a check in {@link calculator.Stack#top} to check that if the size
   * was zero, it should throw an EmptyStackException.<br>
   * Refactoring: None required.
   */
  @Test(expected = EmptyStackException.class)
  public void testEmptyStackTop() throws EmptyStackException {
    stack.top();
  }

  /**
   * Test 24: Tests that when we push twice and pop, that we get the correct value returned, and
   * that the size and stack has correct contents.<br>
   * How did I pass the test: A more complex test that passed without failure due to the
   * implementation of the production code from the previous tests.<br>
   * Refactoring: Instead of creating new Entry objects every time in assertEquals and push, I made
   * them readable variables instead.
   */
  @Test
  public void testPushPushPop() throws EmptyStackException {

    stack.push(numberEntry);
    stack.push(symbolEntry);

    assertEquals("Test 24: testPushPushPop POP RETURN -", symbolEntry, stack.pop());
    assertEquals("Test 24: testPushPushPop SIZE -", 1, stack.size());
    assertEquals("Test 24: testPushPushPop STACK ENTRIES", "[" + numberEntry.toString() + "]",
        stack.toString());
  }

  /**
   * Test 25: Tests multiple pushes and even more pops. Also that
   * {@link exception.EmptyStackException} works given popping multiple entries.<br>
   * How did I pass this test: Nothing was required to pass this test, the current production code
   * is working.<br>
   * Refactoring: As it was the final test, I made all test Entry variables standardised by placing
   * them in the setUp method as opposed to creating them in each test case.
   */
  @Test(expected = EmptyStackException.class)
  public void testMultPushPop() throws EmptyStackException {
    for (int i = 0; i < 10; i++) {
      stack.push(symbolEntry);
    }

    assertEquals("Test 25: testMultPushPop - SIZE 10", 10, stack.size());

    for (int i = 0; i < 11; i++) {
      stack.pop();
    }
  }
}
