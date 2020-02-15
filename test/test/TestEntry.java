package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import calculator.Entry;
import calculator.Symbol;
import calculator.Type;
import exception.BadTypeException;
import org.junit.Before;
import org.junit.Test;


/**
 * A JUnit 4 test class to test the {@link calculator.Entry} class.
 * 
 * @author Sam Burchell
 *
 */
public class TestEntry {
  private Entry numberEntry;
  private Entry symbolEntry;
  private Entry invalidSymbolEntry;

  /**
   * Initialises multiple {@link calculator.Entry} variables used in the tests to avoid repetition.
   * 
   * @throws Exception Indicates that there is an exception thrown that needs to be caught.
   */
  @Before
  public void setUp() throws Exception {
    numberEntry = new Entry(2);
    symbolEntry = new Entry(Symbol.PLUS);
    invalidSymbolEntry = new Entry(Symbol.INVALID);
  }

  /**
   * Test 7: Tests {@link calculator.Entry} to see if it constructs a number typed entry.<br>
   * How did I pass the test: I made {@link calculator.Entry#getValue} to return 2.<br>
   * Refactoring: I changed {@link calculator.Entry#getValue} to return the number field to
   * generalise.
   * 
   * @throws BadTypeException Thrown to indicate that a getter has been called on an invalid entry
   *         type.
   */
  @Test
  public void testNumberConstructor() throws BadTypeException {
    assertEquals("Test 7: testNumberConstructor -", 2, numberEntry.getValue(), 0);
  }

  /**
   * Test 8: Tests {@link calculator.Entry} to see if it accepts and returns a symbol typed
   * {@link calculator.Entry}.<br>
   * How did I pass the test: I made {@link calculator.Entry#getSymbol} return Symbol.PLUS.<br>
   * Refactoring: I changed {@link calculator.Entry#getSymbol} to return the 'other' field for
   * generalisation.
   * 
   * @throws BadTypeException Thrown to indicate that a getter has been called on an invalid entry
   *         type.
   */
  @Test
  public void testSymbolConstructor() throws BadTypeException {
    assertEquals("Test 8: testSymbolConstructor -", Symbol.PLUS, symbolEntry.getSymbol());
  }

  /**
   * Test 10: Tests that the correct type is being returned given a valid
   * {@link calculator.Entry}<br>
   * How did I pass the test: I wrote {@link calculator.Entry#getType} and hard-coded the expected
   * value for one type.<br>
   * Refactoring: I made {@link calculator.Entry#getType} return the type field to generalise the
   * solution. Additionally, I added assert tests for each other valid type.
   */
  @Test
  public void testGetType() {
    assertEquals("Test 10: testGetType NUMBER -", Type.NUMBER, numberEntry.getType());
    assertEquals("Test 10: testGetType SYMBOL -", Type.SYMBOL, symbolEntry.getType());
  }

  /**
   * Test 11: Tests the throwing of {@link exception.BadTypeException} when trying to call
   * {@link calculator.Entry#getValue} with {@link calculator.Entry} having a bad type.<br>
   * How did I pass the test: I made {@link calculator.Entry#getValue} throw a
   * {@link exception.BadTypeException}.<br>
   * Refactoring: I did a check to only throw the {@link exception.BadTypeException} if
   * {@link calculator.Entry} is not of Type.NUMBER.
   * 
   * @throws BadTypeException Thrown to indicate that a getter has been called on an invalid entry
   *         type.
   */
  @Test(expected = BadTypeException.class)
  public void testBadTypeGetValue() throws BadTypeException {
    symbolEntry.getValue();
  }

  /**
   * Test 12: Tests the throwing of {@link exception.BadTypeException} when trying to call
   * {@link calculator.Entry#getSymbol} with {@link calculator.Entry} having a bad type.<br>
   * How did I pass the test: I made {@link calculator.Entry#getSymbol} throw a
   * {@link exception.BadTypeException}.<br>
   * Refactoring: I wrote a check to only throw the {@link exception.BadTypeException} if
   * {@link calculator.Entry} is not of Type.SYMBOL.
   * 
   * @throws BadTypeException Thrown to indicate that a getter has been called on an invalid entry
   *         type.
   */
  @Test(expected = BadTypeException.class)
  public void testBadTypeGetSymbol() throws BadTypeException {
    numberEntry.getSymbol();
  }

  /**
   * Test 14: Tests that two entries are equal if and only if they are of the same type and value
   * (same text in Strings).<br>
   * How did I pass the test: Test necessary types and values (including null) using Eclipse
   * generated {@link calculator.Entry#equals} which accommodates for those expected values.<br>
   * Refactoring: Rewrote {@link calculator.Entry#equals} to a simpler less cluttered version which
   * first checks if the comparing object is an {@link calculator.Entry} and then the two conditions
   * (same type and value) with a simple comparisons.
   */
  @Test
  public void testEquals() {

    // Same type different value
    assertFalse("Test 14: testEqualsSameTypeDifferentValue -", numberEntry.equals(new Entry(10)));

    // Same type same value
    assertTrue("Test 14: testEqualsSameTypeSameValue -", numberEntry.equals(new Entry(2)));

    // Different type different value
    assertFalse("Test 14: testEqualsDifferentTypeDifferentValue -",
        numberEntry.equals(new Entry(Symbol.PLUS)));

    // Test null
    assertFalse("Test 14: testEqualsNull -", numberEntry.equals(null));
  }

  /**
   * Test 15: Tests that two equal objects have the same hashCodes and that two different objects
   * should have different hashCodes.<br>
   * How did I pass the test: Generated {@link calculator.Entry#hashCode} using Eclipse.<br>
   * Refactoring: Rewritten so it was more readable and simple by returning
   * {@link java.util.Objects#hash} with the type and string of the {@link calculator.Entry}.
   */
  @Test
  public void testHashCode() {
    Entry sameEntry = new Entry(Symbol.PLUS);
    Entry differentEntry = new Entry(Symbol.DIVIDE);

    assertTrue("Test 15: testHashCode EQUALITY -",
        symbolEntry.equals(sameEntry) && sameEntry.equals(symbolEntry));
    assertTrue("Test 15: testHashCode SAME OBJ HASH -",
        symbolEntry.hashCode() == sameEntry.hashCode());

    assertFalse("Test 15: testHashCode INEQUALITY -",
        symbolEntry.equals(differentEntry) && differentEntry.equals(symbolEntry));
    assertFalse("Test 15: testHashCode DIFFERENT OBJ HASH -",
        symbolEntry.hashCode() == differentEntry.hashCode());
  }

  /**
   * Test 16: Tests to see if {@link calculator.Entry#toString} returns an expected string
   * representation of {@link calculator.Entry}.<br>
   * How did I pass the test: Write hard-coded values for each field of the entry for each type of
   * entry and returned them in {@link calculator.Entry#toString}.<br>
   * Refactoring: Wrote an if structure to create different format strings to allow toString to be
   * generalised to all the possible types of entry. Additionally, as this was my final Entry test,
   * I refactored all of the Entry variables I was using for test cases, before I would create new
   * entry variables for each test but now they all are all created in the setUp method using JUnit
   * 4 to avoid repetition.
   */
  @Test
  public void testToStringEntry() {

    // For Number Entries.
    assertEquals("Test 16: testToStringEntry NUMBER -",
        "Entry(number=2.000000, symbol=N/A, string=2.0, type=Number)", numberEntry.toString());

    // For Symbol Entries.
    assertEquals("Test 16: testToStringEntry SYMBOL -",
        "Entry(number=N/A, symbol=Plus, string=Plus, type=Symbol)", symbolEntry.toString());

    // For Invalid Entries.
    assertEquals("Test 16: testToStringEntry symbolINVALID -", "Entry(Invalid)",
        invalidSymbolEntry.toString());
  }
}
