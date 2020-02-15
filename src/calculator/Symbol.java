package calculator;

/**
 * A list of the non-number tokens possible in an expression.
 * 
 * @author Sam Burchell
 *
 */
public enum Symbol {
  /**
   * Represents a 'Left Bracket ('.
   */
  LEFT_BRACKET("Left Bracket", '('),

  /**
   * Represents a 'Right Bracket )'.
   */
  RIGHT_BRACKET("Right Bracket", ')'),

  /**
   * Represents a 'Times *'.
   */
  TIMES("Times", '*'),

  /**
   * Represents a 'Divide /'.
   */
  DIVIDE("Divide", '/'),

  /**
   * Represents a 'Plus +'.
   */
  PLUS("Plus", '+'),

  /**
   * Represents a 'Minus -'.
   */
  MINUS("Minus", '-'),

  /**
   * Represents an unsupported symbol 'Invalid'.
   */
  INVALID("Invalid", 'x');

  private String symbol;
  private char symLiteral;

  /**
   * Construct a value for the constants.
   * 
   * @param symbol A string representation of the constant. i.e Symbol.DIVIDE is "Divide"
   * @param symLIteral A char representation of the constant. i.e Symbol.DIVIDE is '/'
   */
  Symbol(String symbol, char symLiteral) {
    this.symbol = symbol;
    this.symLiteral = symLiteral;
  }

  /**
   * Returns a printable string representation of the enum constant.<br>
   * i.e Symbol.PLUS prints "Plus".
   * 
   * @return A string representation of the constant.
   */
  @Override
  public String toString() {
    return symbol;
  }

  /**
   * Gets the operator character for the corresponding Symbol constant.
   * 
   * @return the character representation of the operator.
   */
  public char getOpLiteral() {
    return this.symLiteral;
  }
}
