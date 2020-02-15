package calculator;

import exception.BadTypeException;

import java.util.Objects;

/**
 * A value stored in a {@link calculator.Stack}.
 * 
 * @see Stack
 * @author Sam Burchell
 *
 */
public class Entry {
  float number;
  Symbol symbol;
  String string; // An expression consisting of numbers and symbols.
  Type type;

  /**
   * A constructor for creating a number typed entry. It sets the number, type and string (value) of
   * the entry.
   * 
   * @param number The number contained in Entry.
   */
  public Entry(float number) {
    this.number = number;
    this.type = Type.NUMBER;
    this.string = Float.toString(number);
  }

  /**
   * A constructor for creating a symbol typed entry. It sets the symbol, type and string (value) of
   * the entry.
   * 
   * @param symbol The symbol contained in Entry.
   */
  public Entry(Symbol symbol) {
    this.symbol = symbol;
    this.type = Type.SYMBOL;
    this.string = symbol.toString();
  }

  /**
   * A utility function to check if the type given is the type expected for an Entry.
   * 
   * @param type The type of the Entry to check.
   * @param expected The correct type expected.
   * @return true if type matches expected, otherwise fails.
   */
  private boolean isGoodType(Type type, Type expected) {
    return type == expected;
  }

  /**
   * Gets the value of a number typed entry.
   * 
   * @return The value of the number entry.
   * @throws BadTypeException Thrown to indicate that a getter has been called on an invalid entry
   *         type.
   */
  public float getValue() throws BadTypeException {

    if (isGoodType(type, Type.NUMBER)) {
      return number;
    } else {
      throw new BadTypeException("BadTypeException: Expected a number typed entry.");
    }
  }

  /**
   * Gets the value of a symbol typed entry.
   * 
   * @return The value of the symbol entry.
   * @throws BadTypeException Thrown to indicate that a getter has been called on an invalid entry
   *         type.
   */
  public Symbol getSymbol() throws BadTypeException {

    if (isGoodType(type, Type.SYMBOL)) {
      return symbol;
    } else {
      throw new BadTypeException("BadTypeException: Expected a symbol typed entry.");
    }
  }

  public Type getType() {
    return type;
  }

  /**
   * Indicates whether some other object is "equal" to this one.
   * 
   * @param o The reference object of which to compare.
   * @return true if the object is the same object as the argument; false otherwise.
   */
  @Override
  public boolean equals(Object o) {

    if (!(o instanceof Entry)) {
      return false;
    }

    Entry other = (Entry) o;

    return type == other.type && string.equals(other.string);
  }

  /**
   * Returns the hash code value of the entry.
   * 
   * @return A hash code value for the object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(type, string);
  }

  /**
   * Returns a string representation of the entry.
   * 
   * @return A string representation of an entry.
   */
  @Override
  public String toString() {

    if (this.type == Type.NUMBER) {
      String numberTemplate =
          String.format("Entry(number=%f, symbol=N/A, string=%s, type=%s)", number, string, type);
      return numberTemplate;

    } else if (this.type == Type.SYMBOL && this.string != "Invalid") {
      String symbolTemplate =
          String.format("Entry(number=N/A, symbol=%s, string=%s, type=%s)", symbol, string, type);
      return symbolTemplate;

    } else {
      return "Entry(Invalid)";
    }
  }
}
