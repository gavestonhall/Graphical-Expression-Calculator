package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import calculator.OpStack;
import calculator.Symbol;
import exception.EmptyStackException;
import org.junit.Before;
import org.junit.Test;


/**
 * A JUnit 4 test class to test the {@link calculator.OpStack} class.
 * 
 * @author Sam Burchell
 *
 */
public class TestOpStack {
  private OpStack opStack;

  /**
   * Initialises a new OpStack for each test case.
   * 
   * @throws java.lang.Exception Indicates that there is an exception thrown that needs to be
   *         caught.
   */
  @Before
  public void setUp() throws Exception {
    opStack = new OpStack();
  }

  /**
   * Tests that the OpStack is empty.
   */
  @Test
  public void testOpStackIsEmpty() {
    assertTrue("testOpStackIsEmpty -", opStack.isEmpty());
  }

  /**
   * Tests that the OpStack is not empty.
   */
  @Test
  public void testOpStackNotEmpty() {
    opStack.push(Symbol.PLUS);
    assertFalse("testOpStackIsNotEmpty -", opStack.isEmpty());

  }

  /**
   * Tests that OpStack can properly pop one item.
   * 
   * @throws EmptyStackException Thrown to indicate that an invalid method (pop) has been called on
   *         the stack because it is empty.
   */
  @Test
  public void testOpStackPop() throws EmptyStackException {
    opStack.push(Symbol.PLUS);
    assertEquals("testOpStackPop -", Symbol.PLUS, opStack.pop());
  }

  /**
   * Tests that OpStack can properly pop multiple items.
   * 
   * @throws EmptyStackException Thrown to indicate that an invalid method (pop) has been called on
   *         the stack because it is empty.
   */
  @Test
  public void testOpStackPopMultiple() throws EmptyStackException {
    opStack.push(Symbol.PLUS);
    opStack.push(Symbol.MINUS);
    assertEquals("testOpStackPop MINUS -", Symbol.MINUS, opStack.pop());
    assertEquals("testOpStackPop PLUS -", Symbol.PLUS, opStack.pop());
  }

}
