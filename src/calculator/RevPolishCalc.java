package calculator;

import exception.EmptyStackException;
import exception.InvalidExpressionException;
import java.util.Scanner;

/**
 * Evaluates expressions using Reverse Polish Notation (RPN) otherwise known as postfix.
 * 
 * @author Sam Burchell
 *
 */
public class RevPolishCalc implements Calculator {
  private NumStack evalStack;

  /**
   * A helper method for the calculation when we encounter an operator.
   * 
   * @param operator The operator used in the calculation.
   * @param oper1 The top-most stack operand in the calculation.
   * @param oper2 The next stack operand in the calculation.
   * @return The result of the calculation using each operator. e.g 1+1 returns 2.
   */
  private static float calculate(char operator, float oper1, float oper2) {
    switch (operator) {
      case '+':
        return oper2 + oper1;

      case '-':
        return oper2 - oper1;

      case '*':
        return oper2 * oper1;

      case '/':
        if (oper1 == 0) {
          throw new ArithmeticException("Arithmetic Exception: You cannot divide by zero!");
        }
        return oper2 / oper1;

      default:
        return Float.POSITIVE_INFINITY;
    }
  }

  /**
   * Parses and returns the result of an RPN expression.
   * 
   * @param expression The expression to be evaluated in postfix form.
   * @return The result of the reverse polish notation RPN (postfix) expression.
   */
  @Override
  public float evaluate(String expression) throws InvalidExpressionException {

    if (expression == null || expression.equals("")) {
      throw new InvalidExpressionException("You cannot evaluate null or the empty string.");
    }

    float finalResult;
    Scanner expressionScanner = new Scanner(expression);
    evalStack = new NumStack();

    while (expressionScanner.hasNext()) {

      if (expressionScanner.hasNextFloat()) {
        evalStack.push(expressionScanner.nextFloat());

      } else {

        String symbolToken = expressionScanner.next();

        if (symbolToken.length() > 1) {
          expressionScanner.close();
          throw new InvalidExpressionException(
              "Invalid Expression: More than 1 character is not spaced correctly!");
        }

        char operator = symbolToken.charAt(0);

        float operand1;
        float operand2;
        float result;

        try {
          operand1 = evalStack.pop();
          operand2 = evalStack.pop();

        } catch (EmptyStackException e) {
          expressionScanner.close();
          throw new InvalidExpressionException(
              "Unbalanced expression: not enough numbers in postfix expression"
                  + "\n(infix cannot end with an operator)");
        }

        result = calculate(operator, operand1, operand2);

        if (result == Float.POSITIVE_INFINITY) {
          expressionScanner.close();
          throw new InvalidExpressionException("Arithmetic Exception: Calculation too large!");
        }
        evalStack.push(result);
      }
    }

    try {
      finalResult = evalStack.pop();

    } catch (EmptyStackException e) {
      return Float.POSITIVE_INFINITY;

    } finally {
      expressionScanner.close();
    }

    if (!(evalStack.isEmpty())) {
      throw new InvalidExpressionException(
          "Unbalanced expression: too many numbers in postfix expression"
              + "\n(infix cannot end with an operator)");
    }

    return finalResult;
  }
}
