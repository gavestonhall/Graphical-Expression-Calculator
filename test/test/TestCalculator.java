package test;

import calculator.RevPolishCalc;
import calculator.StandardCalc;
import exception.InvalidExpressionException;
import org.junit.Before;
import org.junit.Test;


/**
 * A JUnit 4 test class to test the {@link calculator.Calculator} interface.
 * 
 * @author Sam Burchell
 *
 */
public class TestCalculator {
  RevPolishCalc revPolishCalc;
  StandardCalc standardCalc;

  /**
   * Initialises instances that are being interfaced for each test case.
   * 
   * @throws java.lang.Exception Indicates that there is an exception thrown that needs to be
   *         caught.
   */
  @Before
  public void setUp() throws Exception {
    revPolishCalc = new RevPolishCalc();
    standardCalc = new StandardCalc();
  }

  /**
   * Tests that the evaluate method is implemented properly by the interface. The initialised
   * classes must implement evaluate for this to compile and pass.
   */
  @Test
  public void testEvaluateImplemented() throws InvalidExpressionException {
    revPolishCalc.evaluate("1 2 +");
    standardCalc.evaluate("5 - 4");
  }
}
