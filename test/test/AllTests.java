package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Creates a test suite which runs all of the tests created for the production code.
 * 
 * @author Sam Burchell
 *
 */
@RunWith(Suite.class)
@SuiteClasses({TestEntry.class, TestStack.class, TestSymbol.class, TestType.class,
    TestNumStack.class, TestOpStack.class, TestCalculator.class,
    TestRevPolishCalc.class, TestStandardCalc.class, TestCalcModel.class})
public class AllTests {

}
