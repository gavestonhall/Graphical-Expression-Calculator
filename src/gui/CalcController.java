package gui;

import exception.InvalidExpressionException;
import patterns.CalcModel;
import patterns.ViewInterface;

/**
 * The calculator's controller (part of MVC and Observer design patterns). Uses
 * {@link patterns.CalcModel} for updating the views.
 * 
 * @author Sam Burchell, adapted from FxObserver @ Moodle
 *
 */
public class CalcController {
  private CalcModel model = CalcModel.getInstance();
  private ViewInterface myView = null;

  /**
   * Constructor for creating a controller. Adds it as an observer to monitor changes in the views.
   * 
   * @param view The type of view you want to create a controller for.
   */
  public CalcController(ViewInterface view) {
    myView = view;
    view.addCalcObserver(this::calculate);
  }

  /**
   * Links logic up for the postfix {@link calculator.RevPolishCalc} and infix
   * {@link calculator.StandardCalc} calculator classes. Essentially gets the expression from the
   * view and uses the model to determine the answer.
   * 
   * @see CalcModel
   */
  private void calculate() {

    try {
      float result = model.evaluate(myView.getExpression());
      myView.setAnswer("Answer: " + result);

    } catch (InvalidExpressionException e) {
      myView.setAnswer(
          e.getMessage() + "\nRemember to fully space each operand, bracket or operator!");

    } catch (ArithmeticException e) {
      myView.setAnswer(e.getMessage());
    }

  }

}
