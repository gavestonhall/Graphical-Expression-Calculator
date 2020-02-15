package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import calculator.Symbol;
import org.junit.Test;


/**
 * A JUnit 4 test class to test the {@link calculator.Symbol} class.
 * 
 * @author Sam Burchell
 *
 */
public class TestSymbol {
  Symbol symbol;

  /**
   * Test 1: Tests {@link calculator.Symbol} constants are not null to see if they have been
   * created.<br>
   * How did I pass the test: Create all necessary constants in {@link calculator.Symbol}.<br>
   * Refactoring: None required.
   */
  @Test
  public void testNotNull() {
    for (Symbol symbol : Symbol.values()) {
      assertNotNull("Test 1: testNotNull -", symbol);
    }
  }

  /**
   * Test 2: Tests {@link calculator.Symbol#toString} to see if we get a string representation of a
   * singular enum constant.<br>
   * How did I pass the test: Made {@link calculator.Symbol} constructor and made
   * {@link calculator.Symbol#toString} return "Plus".<br>
   * Refactoring: Returned the symbol field to make it account for the rest of the constants.
   */
  @Test
  public void testToStringPlus() {
    symbol = Symbol.PLUS;
    assertEquals("Test 2: testToStringPlus -", "Plus", symbol.toString());
  }

  /**
   * Test 3: Tests {@link calculator.Symbol#toString} to see if we get a string representation of
   * all {@link calculator.Symbol} constants.<br>
   * How did I pass the test: Gave all {@link calculator.Symbol} constants representable strings
   * i.e. Symbol.DIVIDE('Divide')<br>
   * Refactoring: Initially I tested each constant individually so I refactored by creating a loop
   * to do that for me more efficiently.
   */
  @Test
  public void testToStringAll() {
    String[] constants =
        {"Left Bracket", "Right Bracket", "Times", "Divide", "Plus", "Minus", "Invalid"};
    Symbol[] symbols = Symbol.values();
    for (int i = 0; i < constants.length; i++) {
      assertEquals("Test 3: testToStringAll -", constants[i], symbols[i].toString());
    }
  }

  /**
   * Tests that the getOpLiteral method returns the correct character constant for each Symbol.
   */
  @Test
  public void testGetOpLiteral() {
    char[] constants = {'(', ')', '*', '/', '+', '-', 'x'};
    Symbol[] symbols = Symbol.values();
    for (int i = 0; i < constants.length; i++) {
      assertEquals("testGetOpLiteral -", constants[i], symbols[i].getOpLiteral());
    }
  }
}
