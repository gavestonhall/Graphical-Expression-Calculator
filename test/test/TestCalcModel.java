package test;

import static org.junit.Assert.assertEquals;

import calculator.RevPolishCalc;
import calculator.StandardCalc;
import exception.InvalidExpressionException;
import org.junit.Before;
import org.junit.Test;
import patterns.CalcModel;

/**
 * A JUnit 4 Test class to test the implementation of {@link patterns.CalcModel}.
 * 
 * @author Sam Burchell
 *
 */
public class TestCalcModel {
  private CalcModel model;
  private RevPolishCalc postfix;
  private StandardCalc infix;

  /**
   * Initialises the model and the infix/postfix calculators before each test case.
   * 
   * @throws java.lang.Exception Indicates that there is an exception thrown that needs to be
   *         caught.
   */
  @Before
  public void setUp() throws Exception {
    model = CalcModel.getInstance();
    postfix = new RevPolishCalc();
    infix = new StandardCalc();

  }

  /**
   * Tests that the model is being able to use evaluate from both the infix and postfix evaluate
   * methods.
   */
  @Test
  public void testEvaluate() throws InvalidExpressionException {

    // These call infix evaluate from the model
    String expression = "6 / 2 * ( 1 + 2 )";
    assertEquals("testEvaluateInfix TEST 1 -", infix.evaluate(expression),
        model.evaluate(expression), 0.00001);

    expression = "6 / 2 * 3 + 2 + ( 5 * 12 )   ";
    assertEquals("testEvaluateInfix TEST 2 -", infix.evaluate(expression),
        model.evaluate(expression), 0.00001);

    // These call postfix evaluate from the model
    expression = "1 2 / 3 -";
    assertEquals("testEvaluatePostfix TEST 1 -", postfix.evaluate(expression),
        model.evaluate(expression), 0.00001);

    expression = "1 2 * 3 +";
    assertEquals("testEvaluatePostfix TEST 2 -", postfix.evaluate(expression),
        model.evaluate(expression), 0.00001);
  }

  /**
   * Tests that an InvalidExpressionException is thrown when we enter an invalid infix expression.
   * 
   * @throws InvalidExpressionException Thrown to indicate an expression is not in postfix or infix
   *         form.
   */
  @Test(expected = InvalidExpressionException.class)
  public void testInvalidOperatorsInfix() throws InvalidExpressionException {
    String expression = "1 +* 2";
    model.evaluate(expression);
  }

  /**
   * Tests that an InvalidExpressionException is thrown when we enter an invalid postfix expression.
   * 
   * @throws InvalidExpressionException Thrown to indicate an expression is not in postfix or infix
   *         form.
   */
  @Test(expected = InvalidExpressionException.class)
  public void testInvalidOperatorsPostfix() throws InvalidExpressionException {
    String expression = "1 2 *+";
    model.evaluate(expression);
  }
}
