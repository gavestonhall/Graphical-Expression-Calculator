package ascii;

import java.util.Scanner;
import patterns.Observer;
import patterns.ViewInterface;

/**
 * The text-based view for the calculator. Initiated when ran from the command line.
 * 
 * @author Sam Burchell, adapted from FxObserver @ Moodle
 *
 */
public class AsciiView implements ViewInterface {
  private String expression;
  private Observer calc = null;

  /**
   * The actual interface the user will interact with. Very simple, only requires the user to enter
   * their expression and built-in commands help and quit. For those who can't access GUI.
   */
  public void menu() {
    Scanner exprScanner = new Scanner(System.in);
    boolean isFinished = false;
    help();

    while (!(isFinished) && exprScanner.hasNextLine()) {
      String currentLine = exprScanner.nextLine();

      if (currentLine.equals("quit")) {
        isFinished = true;
        exprScanner.close();
      } else if (currentLine.equals("help")) {
        help();
      } else {
        expression = currentLine;

        // The view has updated since the user entered an expression, notify the observer
        if (calc != null) {
          calc.update();
        }

        System.out.print("> ");
      }
    }
  }

  /**
   * Provides the help message to the user, reminding them of the commands if they forget.
   */
  private void help() {
    System.out.println("To use the Calculator enter an infix or postfix expression below!");
    System.out.println("REMEMBER TO FULLY SPACE YOUR OPERANDS, OPERATORS AND BRACKETS!");
    System.out.println("If your expression was not correct make sure to adhere to"
        + " everything the error message says!");
    System.out.println("Type [help] to view this message again!");
    System.out.println("Type [quit] to quit!");
    System.out.print("> ");
  }


  /**
   * Adds the observer so it can monitor updates in this class.
   * 
   * @param observer The observer to be added.
   */
  @Override
  public void addCalcObserver(Observer observer) {
    calc = observer;
  }

  /**
   * Gets the expression entered by the user.
   * 
   * @return the expression given by the user.
   */
  @Override
  public String getExpression() {
    return expression;
  }

  /**
   * Prints out the answer of the expression entered to the screen for the user to see.
   * 
   * @param result the result of the expression after it has been evaluated.
   */
  @Override
  public void setAnswer(String result) {
    System.out.println(result);
  }
}
