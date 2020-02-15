package calculator;

import exception.EmptyStackException;
import exception.InvalidExpressionException;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * Evaluates expressions using infix notation. It uses the shunting-yard algorithm to convert infix
 * to postfix for the RPN calculator to evaluate it.
 * 
 * @author Sam Burchell
 *
 */
public class StandardCalc implements Calculator {
  private RevPolishCalc rpnCalc;
  private OpStack opStack;
  private Deque<String> outputQueue;
  private Deque<String> inputQueue;

  /**
   * Initialises the reverse polish calculator used for evaluating after it has converted the
   * expression from infix to postfix.
   */
  public StandardCalc() {
    rpnCalc = new RevPolishCalc();
  }

  /**
   * A method that checks the precedence of each valid operator used for the shunting-yard
   * algorithm.
   * 
   * @param character The operator to get the precedence for.
   * @return The precedence of that operator as an integer. Higher integers mean higher precedence.
   */
  private static int getPrecedence(char op) {

    switch (op) {
      case '+':
      case '-':
        return 0;

      case '*':
      case '/':
        return 1;

      case '(':
        return 2;

      default:
        return -1;
    }
  }

  /**
   * Converts a normal character literal into a Symbol type to be used with the OpStack.
   * 
   * @param literal The character literal you want to convert.
   * @return A Symbol of the chosen character literal.
   */
  private static Symbol opLiteralToSymbol(char literal) {
    switch (literal) {
      case '+':
        return Symbol.PLUS;

      case '-':
        return Symbol.MINUS;

      case '*':
        return Symbol.TIMES;

      case '/':
        return Symbol.DIVIDE;

      default:
        return Symbol.INVALID;
    }
  }

  /**
   * Checks if a given string can be converted to a float.
   * 
   * @param stringNum A string token that you expect to to be a float.
   * @return true if stringNum is able to be converted to an integer, if it cannot return false.
   */
  private static boolean isNumeric(String stringNum) {
    if (stringNum == null) {
      return false;
    }
    try {
      Float.parseFloat(stringNum);
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

  /**
   * Parses and returns the result of an infix expression using the Shunting-yard algorithm.
   * 
   * @param expression The expression to be evaluated in infix form.
   * @return The result of the infix expression.
   */
  public float evaluate(String expression) throws InvalidExpressionException {

    if (expression == null || expression.equals("")) {
      throw new InvalidExpressionException("Cannot evaluate a null or empy string.");
    }

    opStack = new OpStack();
    inputQueue = new ArrayDeque<>();
    outputQueue = new ArrayDeque<>();


    // Populate the input queue
    Scanner expressionScanner = new Scanner(expression);

    while (expressionScanner.hasNext()) {

      if (expressionScanner.hasNextFloat()) {
        String floatToken = String.valueOf(expressionScanner.nextFloat());
        inputQueue.add(floatToken);

      } else {
        String token = expressionScanner.next();
        inputQueue.add(token);
      }
    }

    expressionScanner.close();

    // Used to validate the infix expression
    // We are first expecting a numeric value then an
    // operator alternating until finishes
    boolean isOperatorFlag = false;

    // Infix to postfix via the Shunting-yard algorithm
    for (String token : inputQueue) {
      if (isNumeric(token)) {

        if (!isOperatorFlag) {
          isOperatorFlag = !isOperatorFlag;
        } else {
          throw new InvalidExpressionException(
              "Invalid Expression: Consecutive numbers not allowed in an infix expression"
                  + "\n(postfix should not end in an operand or bracket)");
        }

        outputQueue.add(token);


      } else if (token.equals("(")) {
        opStack.push(Symbol.LEFT_BRACKET);

      } else if (token.equals(")")) {
        try {
          while (!(opStack.isEmpty()) && opStack.top().getOpLiteral() != '(') {
            outputQueue.add(String.valueOf(opStack.pop().getOpLiteral()));
          }

          // Discard left bracket
          opStack.pop();

        } catch (EmptyStackException e) {
          throw new InvalidExpressionException("Unbalanced Expression: Unable to find left bracket "
              + "(postfix should not contain brackets)");
        }

        // Token is an operator
      } else {

        if (token.length() > 1) {
          throw new InvalidExpressionException(
              "Invalid Expression: More than 1 character is not spaced correctly!");
        }

        if (isOperatorFlag) {
          isOperatorFlag = !isOperatorFlag;
        } else {
          throw new InvalidExpressionException(
              "Invalid Expression: Consecutive operators not allowed in an infix expression"
                  + "\n(postfix should not end in an operand or bracket)");
        }


        try {
          while (!(opStack.isEmpty())
              && getPrecedence(opStack.top().getOpLiteral()) >= getPrecedence(token.charAt(0))
              && opStack.top().getOpLiteral() != '(') {
            outputQueue.add(String.valueOf(opStack.pop().getOpLiteral()));
          }

          opStack.push(opLiteralToSymbol(token.charAt(0)));

        } catch (EmptyStackException e) {
          throw new InvalidExpressionException("Invalid Expression Entered");
        }
      }
    }

    // Pop all remaining operators on the stack
    while (!(opStack.isEmpty())) {

      try {

        if (opStack.top().getOpLiteral() == '(') {
          throw new InvalidExpressionException(
              "Unbalanced Expression: Unable to find right bracket "
                  + "(postfix should not contain brackets)");
        }
        outputQueue.add(String.valueOf(opStack.pop().getOpLiteral()));

      } catch (EmptyStackException e) {
        throw new InvalidExpressionException("Invalid Expression Entered");
      }

    }

    // Generate postfix string based on output queue
    StringBuilder postfixExpr = new StringBuilder();
    for (String item : outputQueue) {
      postfixExpr.append(item + " ");
    }

    String result = postfixExpr.toString();

    // Expression was parsed improperly due to a invalid input string
    if (result.contains("x")) {
      throw new InvalidExpressionException(
          "Invalid Expression: Make sure characters are VALID (0-9, + - * / ( ) only) "
              + "and there is a space between them");
    }

    if (result.contains("Infinity")) {
      throw new ArithmeticException("Arithmetic Exception: Calculation too large!");
    }

    return rpnCalc.evaluate(result);
  }
}
