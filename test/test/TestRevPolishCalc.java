package test;

import static org.junit.Assert.assertEquals;

import calculator.RevPolishCalc;
import exception.InvalidExpressionException;
import org.junit.Before;
import org.junit.Test;


/**
 * Tests the {@link calculator.RevPolishCalc} evaluate implementation of the
 * {@link calculator.Calculator} interface.
 * 
 * @author Sam Burchell
 *
 */
public class TestRevPolishCalc {
  private RevPolishCalc revPolishCalc;

  /**
   * Initialises an instance of the RPN calculator being used for each test case.
   * 
   * @throws java.lang.Exception Indicates that there is an exception thrown that needs to be
   *         caught.
   */
  @Before
  public void setUp() throws Exception {
    revPolishCalc = new RevPolishCalc();
  }


  /**
   * Tests that the evaluate method for reverse polish notation (postfix) is able to give the
   * correct answer for simple input with correct syntax.
   */
  @Test
  public void testEvaluatePostfixSimple() throws InvalidExpressionException {
    float expr1 = revPolishCalc.evaluate("5 6 7 + * 2 -");
    float expr2 = revPolishCalc.evaluate("1 2 4 + *");
    float expr3 = revPolishCalc.evaluate("5 4 + 4 2 / 2 * +");

    assertEquals("testEvaluateRPN TEST 1 -", 63.0, expr1, 0.00001);
    assertEquals("testEvaluateRPN TEST 2 -", 6.0, expr2, 0.00001);
    assertEquals("testEvaluateRPN TEST 3 -", 13.0, expr3, 0.00001);
  }


  /**
   * Tests that the evaluate method for postfix gives correct answer for more complex expressions
   * involving floats and negative numbers.
   */
  @Test
  public void testEvaluatePostfixComplex() throws InvalidExpressionException {
    String[] expressions = {"6.4 3.4 /", "-5 2 / 5 +", "-5 10 *"};
    float[] answers = {(float) 1.88235294118, (float) 2.5, (float) -50};

    for (int i = 0; i < answers.length; i++) {
      String title = "testEvaluatePostfixComplex " + i + " -";
      assertEquals(title, answers[i], revPolishCalc.evaluate(expressions[i]), 0.00001);
    }
  }

  /**
   * Tests that InvalidExpressionException is thrown when we enter null as an argument to evaluate.
   * 
   * @throws InvalidExpressionException Thrown to indicate an expression is not in postfix or infix
   *         form.
   */
  @Test(expected = InvalidExpressionException.class)
  public void testEvaluatePostfixInvalidNull() throws InvalidExpressionException {
    revPolishCalc.evaluate(null);
  }

  /**
   * Tests that InvalidExpressionException is thrown when we enter an empty string argument to
   * evaluate.
   * 
   * @throws InvalidExpressionException Thrown to indicate an expression is not in postfix or infix
   *         form.
   */
  @Test(expected = InvalidExpressionException.class)
  public void testEvaluatePostfixInvalidEmpty() throws InvalidExpressionException {
    revPolishCalc.evaluate("");
  }

  /**
   * Tests that InvalidExpressionException is thrown when we enter an infix expression.
   * 
   * @throws InvalidExpressionException Thrown to indicate an expression is not in postfix or infix
   *         form.
   */
  @Test(expected = InvalidExpressionException.class)
  public void testEvaluatePostfixInvalidInfix() throws InvalidExpressionException {
    revPolishCalc.evaluate("1 + 2");
  }

  /**
   * Tests that InvalidExpressionException is thrown when we enter too many operators.
   * 
   * @throws InvalidExpressionException Thrown to indicate an expression is not in postfix or infix
   *         form.
   */
  @Test(expected = InvalidExpressionException.class)
  public void testEvaluatePostfixInvalidTooManyOps() throws InvalidExpressionException {
    revPolishCalc.evaluate("1 2 + * /");
  }

  /**
   * Tests that InvalidExpressionException is thrown when we enter too many operands (numbers).
   * 
   * @throws InvalidExpressionException Thrown to indicate an expression is not in postfix or infix
   *         form.
   */
  @Test(expected = InvalidExpressionException.class)
  public void testEvaluatePostfixInvalidTooManyNumbers() throws InvalidExpressionException {
    revPolishCalc.evaluate("1 4 5 * 1 -");
  }

  /**
   * Tests that InvalidExpressionException is thrown when we enter invalid characters.
   * 
   * @throws InvalidExpressionException Thrown to indicate an expression is not in postfix or infix
   *         form.
   */
  @Test(expected = InvalidExpressionException.class)
  public void testEvaluatePostfixInvalidCharacters1() throws InvalidExpressionException {
    revPolishCalc.evaluate("1 5 lol");
  }

  /**
   * Tests that InvalidExpressionException is thrown when we enter invalid characters.
   * 
   * @throws InvalidExpressionException Thrown to indicate an expression is not in postfix or infix
   *         form.
   */
  @Test(expected = InvalidExpressionException.class)
  public void testEvaluatePostfixInvalidCharacters2() throws InvalidExpressionException {
    revPolishCalc.evaluate("1 2 >");
  }

  /**
   * Tests that InvalidExpressionException is thrown when we enter invalid characters.
   * 
   * @throws InvalidExpressionException Thrown to indicate an expression is not in postfix or infix
   *         form.
   */
  @Test(expected = InvalidExpressionException.class)
  public void testEvaluatePostfixInvalidCharacters3() throws InvalidExpressionException {
    revPolishCalc.evaluate("boaijsdf");
  }

  /**
   * Tests that InvalidExpressionException is thrown when we try to enter an expression dividing by
   * zero.
   * 
   * @throws InvalidExpressionException Thrown to indicate an expression is not in postfix or infix
   *         form.
   */
  @Test(expected = ArithmeticException.class)
  public void testEvaluatePostfixInvalidDivideByZero() throws InvalidExpressionException {
    revPolishCalc.evaluate("5 0 /");
  }

}
