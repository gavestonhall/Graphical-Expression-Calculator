package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import calculator.NumStack;
import exception.EmptyStackException;
import org.junit.Before;
import org.junit.Test;


/**
 * A JUnit 4 test class to test the {@link calculator.NumStack} class.
 * 
 * @author Sam Burchell
 *
 */
public class TestNumStack {
  private NumStack numStack;

  /**
   * Initialises a new NumStack for each test case.
   * 
   * @throws java.lang.Exception Indicates that there is an exception thrown that needs to be
   *         caught.
   */
  @Before
  public void setUp() throws Exception {
    numStack = new NumStack();
  }

  /**
   * Tests that the NumStack is empty.
   */
  @Test
  public void testNumStackIsEmpty() {
    assertTrue("testNumStackIsEmpty -", numStack.isEmpty());
  }

  /**
   * Tests that the NumStack is not empty.
   */
  @Test
  public void testNumStackNotEmpty() {
    numStack.push(2);
    assertFalse("testNumStackNotEmpty -", numStack.isEmpty());
  }
  
  /**
   * Tests that the NumStack can pop properly for one item.
   * 
   * @throws EmptyStackException Thrown to indicate that an invalid method (pop) has been called on
   *         the stack because it is empty.
   */
  @Test
  public void testNumStackPop() throws EmptyStackException {
    numStack.push(2);
    assertEquals("testNumStackPop -", 2, numStack.pop(), 0.00001);
  }
  
  /**
   * Tests that the NumStack can pop properly for multiple items.
   * @throws EmptyStackException Thrown to indicate that an invalid method (pop) has been called
   *         on the stack because it is empty.
   */
  @Test
  public void testNumStackPopMultiple() throws EmptyStackException {
    numStack.push(2);
    numStack.push(5);
    assertEquals("testNumStackPopMultiple 5 -", 5, numStack.pop(), 0.00001);
    assertEquals("testNumStackPopMultiple 2 -", 2, numStack.pop(), 0.00001);
  }

}
