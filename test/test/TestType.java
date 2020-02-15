package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import calculator.Type;
import org.junit.Test;


/**
 * A JUnit 4 test class to test the {@link calculator.Type} class.
 * 
 * @author Sam Burchell
 *
 */
public class TestType {
  Type type;

  /**
   * Test 4: Tests {@link calculator.Type} constants are not null and that they have actually been
   * created.<br>
   * How did I pass the test: Create constants in the enum {@link calculator.Type}.<br>
   * Refactoring: None required.
   */
  @Test
  public void testNotNull() {
    for (Type type : Type.values()) {
      assertNotNull("Test 4: testNotNull -", type);
    }
  }

  /**
   * Test 5: Tests {@link calculator.Type#toString} and {@link calculator.Type} to ensure we get a
   * string form of a singular constant.<br>
   * How did I pass the test: Made {@link calculator.Type#toString} return "Symbol".<br>
   * Refactoring: Made {@link calculator.Type#toString} return the type field to generalise the
   * solution.
   */
  @Test
  public void testToStringSymbol() {
    type = Type.SYMBOL;
    assertEquals("Test 5: testToStringSymbol -", "Symbol", type.toString());
  }

  /**
   * Test 6: Tests {@link calculator.Type#toString} to ensure we get the string form of all
   * constants.<br>
   * How did I pass the test: Gave all {@link calculator.Type} constants a string representation i.e
   * Type.INVALID("Invalid")<br>
   * Refactoring: None required.
   */
  @Test
  public void testToStringAll() {
    Type[] types = Type.values();
    String[] typeStrings = {"Number", "Symbol"};
    for (int i = 0; i < typeStrings.length; i++) {
      assertEquals("Test 6: testToStringAll -", typeStrings[i], types[i].toString());
    }
  }
}
