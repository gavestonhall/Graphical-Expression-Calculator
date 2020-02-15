package patterns;

import calculator.RevPolishCalc;
import calculator.StandardCalc;
import exception.InvalidExpressionException;

/**
 * Responsible for being the model of the calculator which will determine the functionality for the
 * calculator whether the expression is infix or postfix.
 * 
 * @author Sam Burchell
 *
 */
public class CalcModel {
  private RevPolishCalc rpnCalc;
  private StandardCalc standardCalc;
  private static CalcModel instance = new CalcModel();

  private CalcModel() {}

  /**
   * Gives you the CalcModel instance to use.
   * 
   * @return the CalcModel instance created.
   */
  public static CalcModel getInstance() {
    return instance;
  }

  /**
   * Determines whether an expression entered is postfix or infix in order to call the correct
   * evaluate method on it.
   * 
   * @param expression The expression to be evaluated.
   * @return A float representation of the expression after it has been evaluated.
   */
  public float evaluate(String expression) throws InvalidExpressionException {

    float result;

    // Ensures that we cannot get a space as the last character
    expression = expression.trim();

    String lastChar;

    // The user entered nothing
    if (expression.length() != 0) {
      lastChar = expression.substring(expression.length() - 1);
    } else {
      return 0;
    }

    // If the last character of the expression is a valid operator then we can assume it is postfix
    if ("+-*/".indexOf(lastChar) >= 0) {
      rpnCalc = new RevPolishCalc();
      result = rpnCalc.evaluate(expression);
    } else {
      standardCalc = new StandardCalc();
      result = standardCalc.evaluate(expression);
    }

    return result;
  }
}
