package calculator;

/**
 * A list of the types of value an {@link calculator.Entry} can represent.
 * 
 * @see Entry
 * @author Sam Burchell
 *
 */
public enum Type {
  /**
   * A singular number that was entered i.e 4.2, -2, 5.
   */
  NUMBER("Number"),

  /**
   * A singular symbol defined by {@link calculator.Symbol}.
   */
  SYMBOL("Symbol");

  private String type;

  /**
   * Construct a string representation of the constants. i.e Type.SYMBOL becomes "Symbol".
   * 
   * @param type The string representation of the actual type.
   */
  Type(String type) {
    this.type = type;
  }

  /**
   * Returns a printable string representation of the enum constant.<br>
   * i.e Type.NUMBER will print "Number".
   * 
   * @return A string representation of the constant.
   */
  @Override
  public String toString() {
    return type;
  }
}
