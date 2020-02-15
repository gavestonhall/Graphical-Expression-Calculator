package calculator;

import exception.InvalidExpressionException;

/**
 * An interface which allows the evaluation of string expressions.
 * 
 * @author Sam Burchell
 *
 */
public interface Calculator {
  float evaluate(String expression) throws InvalidExpressionException;
}
