package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import patterns.Observer;
import patterns.ViewInterface;

/**
 * The graphical view following from the FXML.
 * 
 * @author Sam Burchell, adapted from FxObserver @ Moodle
 *
 */
public class GuiView implements ViewInterface {
  @FXML
  private TextField exprField;

  @FXML
  private Label answer;

  @FXML
  private Button evalButton;

  /**
   * Notifies the observer that there is a change (evaluate button was clicked) and therefore it
   * should update.
   * 
   * @param observer A reference method of an observer i.e the controller which calls calculate
   */
  public void addCalcObserver(Observer observer) {
    evalButton.setOnAction(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent event) {
        observer.update();
      }
    });
  }


  /**
   * Get the expression to be evaluated from the view.
   * 
   * @return The expression that was entered by the user in the text field.
   */
  public String getExpression() {
    return exprField.getText();
  }

  /**
   * Sets the answer field on the view.
   * 
   * @param result The result of the expression after it has been evaluated.
   */
  public void setAnswer(String result) {
    answer.setText(result);
  }


}
