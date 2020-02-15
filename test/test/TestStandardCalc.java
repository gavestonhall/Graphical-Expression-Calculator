package test;

import static org.junit.Assert.assertEquals;

import calculator.StandardCalc;
import exception.InvalidExpressionException;
import org.junit.Before;
import org.junit.Test;


/**
 * Tests the {@link calculator.StandardCalc} evaluate implementation of the
 * {@link calculator.Calculator} interface.
 * 
 * @author Sam Burchell
 *
 */
public class TestStandardCalc {
  private StandardCalc standardCalc;

  /**
   * Initialises an instance of the infix calculator being used for each test case.
   * 
   * @throws java.lang.Exception Indicates that there is an exception thrown that needs to be
   *         caught.
   */
  @Before
  public void setUp() throws Exception {
    standardCalc = new StandardCalc();
  }


  /**
   * Tests that the evaluate method for infix notation is able to give the correct answer for simple
   * input (no brackets) with correct syntax.
   * 
   * @throws InvalidExpressionException Thrown to indicate an expression is not in postfix or infix
   *         form.
   */
  @Test
  public void testEvaluateInfixSimple() throws InvalidExpressionException {
    float expr1 = standardCalc.evaluate("3 + 4");
    float expr2 = standardCalc.evaluate("3 + 1 * 5 / 2");
    float expr3 = standardCalc.evaluate("2.5 * 4.0 / 2.0");

    assertEquals("testEvalauteInfixSimple TEST 1 -", 7, expr1, 0.00001);
    assertEquals("testEvaluateInfixSimple TEST 2 -", 5.5, expr2, 0.00001);
    assertEquals("testEvaluateInfixSimple TEST 3 -", 5, expr3, 0.00001);
  }

  /**
   * Tests that the evaluate method for infix notation is able to give the correct answer for more
   * complex input including brackets and negative numbers.
   * 
   * @throws InvalidExpressionException Thrown to indicate an expression is not in postfix or infix
   *         form.
   */
  @Test
  public void testEvaluateInfixComplex() throws InvalidExpressionException {
    String[] expressions = {"( 7 - 4 ) + 2", "( 5 * ( 6 + 7 ) ) - 2", "5 + -6.5 * ( 1 + 4 )"};
    float[] answers = {(float) 5, (float) 63, (float) -27.5};

    for (int i = 0; i < answers.length; i++) {
      String title = "testEvaluateInfixComplex " + i + " -";
      assertEquals(title, answers[i], standardCalc.evaluate(expressions[i]), 0.00001);
    }
  }

  /**
   * Tests that InvalidExpressionException is thrown when null is passed as an argument to evaluate.
   * 
   * @throws InvalidExpressionException Thrown to indicate an expression is not in postfix or infix
   *         form.
   */
  @Test(expected = InvalidExpressionException.class)
  public void testEvaluateInfixInvalidNull() throws InvalidExpressionException {
    standardCalc.evaluate(null);
  }

  /**
   * Tests that InvalidExpressionException is thrown when an empty string is passed as an argument
   * to evaluate.
   * 
   * @throws InvalidExpressionException Thrown to indicate an expression is not in postfix or infix
   *         form.
   */
  @Test(expected = InvalidExpressionException.class)
  public void testEvaluateInfixInvalidEmpty() throws InvalidExpressionException {
    standardCalc.evaluate("");
  }

  /**
   * Tests that InvalidExpressionException is thrown when an invalid infix string is passed into the
   * evaluate argument without any spaces.
   * 
   * @throws InvalidExpressionException Thrown to indicate an expression is not in postfix or infix
   *         form.
   */
  @Test(expected = InvalidExpressionException.class)
  public void testEvaluateInfixInvalidNoSpace1() throws InvalidExpressionException {
    standardCalc.evaluate("(1+5)-5");
  }

  /**
   * Tests that InvalidExpressionException is thrown when an invalid infix string is passed into the
   * evaluate argument with some characters spaced.
   * 
   * @throws InvalidExpressionException Thrown to indicate an expression is not in postfix or infix
   *         form.
   */
  @Test(expected = InvalidExpressionException.class)
  public void testEvaluateInfixInvalidNoSpace2() throws InvalidExpressionException {
    standardCalc.evaluate("(1 + 5) - 5");
  }

  /**
   * Tests that InvalidExpressionException is thrown when an invalid infix string is passed into the
   * evaluate argument with many invalid characters.
   * 
   * @throws InvalidExpressionException Thrown to indicate an expression is not in postfix or infix
   *         form.
   */
  @Test(expected = InvalidExpressionException.class)
  public void testEvaluateInfixInvalidCharacters() throws InvalidExpressionException {
    standardCalc.evaluate("5 + a");
  }

  /**
   * Tests that InvalidExpressionException is thrown when the right bracket is mismatched in the
   * evaluate expression.
   * 
   * @throws InvalidExpressionException Thrown to indicate an expression is not in postfix or infix
   *         form.
   */
  @Test(expected = InvalidExpressionException.class)
  public void testEvaluateInfixInvalidBracketsRight() throws InvalidExpressionException {
    standardCalc.evaluate("( ( 5 + 3 ) - 2");
  }

  /**
   * Tests that InvalidExpressionException is thrown when the left bracket is mismatched in the
   * evaluate expression.
   * 
   * @throws InvalidExpressionException Thrown to indicate an expression is not in postfix or infix
   *         form.
   */
  @Test(expected = InvalidExpressionException.class)
  public void testEvaluateInfixInvalidBracketsLeft() throws InvalidExpressionException {
    standardCalc.evaluate("( 5 + 3 ) - 2 )");
  }

  /**
   * Tests that InvalidExpressionException is thrown when we try to enter an expression dividing by
   * zero.
   * 
   * @throws InvalidExpressionException Thrown to indicate an expression is not in postfix or infix
   *         form.
   */
  @Test(expected = ArithmeticException.class)
  public void testEvalauteInfixInvalidDivideByZero() throws InvalidExpressionException {
    standardCalc.evaluate("( 5 / 0 ) - 2");
  }

  /**
   * Tests that InvalidExpressionException is thrown when we try to enter an infix expression with
   * too many numbers. Note: it is also invalid postfix due to ending in an operand.
   */
  @Test(expected = InvalidExpressionException.class)
  public void testEvaluateInfixInvalidInfixExpression() throws InvalidExpressionException {
    standardCalc.evaluate("4 5 + 1");
  }

  /**
   * Tests that InvalidExpressionException is thrown when we try to enter an infix expression with
   * too many operators. Note: it is also invalid postfix due to ending in an operand.
   */
  @Test(expected = InvalidExpressionException.class)
  public void testEvaluateInfixInvalidTooManyOperators() throws InvalidExpressionException {
    standardCalc.evaluate("1 * * 4 ");
  }
}
