package gui;

import ascii.AsciiView;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import patterns.ViewInterface;


/**
 * The main class of the calculator which will launch an interface of the calculator. Is
 * able to tell whether the code was ran from the command line or not and give an appropriate view
 * based on that.
 * 
 * @author Sam Burchell, adapted from FxObserver @ Moodle
 *
 */
public class Main extends Application {
  static FXMLLoader loader;
  static Parent root;

  /**
   * Standard start method for JavaFX to launch an application window.
   * 
   * @param primaryStage The primary JavaFX stage.
   */
  @Override
  public void start(Stage primaryStage) {
    try {
      Scene scene = new Scene(root);
      scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Calculator application entry-point, sets up and loads controller and views.
   * 
   * @param args The arguments to be entered as input to the program.
   * @throws IOException An I/O error has been interrupted or failed.
   */
  public static void main(String[] args) throws IOException {

    ViewInterface view = null;
    boolean isAscii = false;

    // Will load ASCII view if you run Main from a terminal
    if (System.console() != null) {
      isAscii = true;
    }

    if (isAscii) {
      view = new AsciiView();
    } else {
      // Loads the GUI view
      loader = new FXMLLoader(Main.class.getResource("Calculator.fxml"));
      root = loader.load();
      view = loader.getController();
    }

    // Creates the observer controller with the selected view as the subject being observed
    new CalcController(view);

    if (isAscii) {
      ((AsciiView) view).menu();

      // Ensure the program quits afterwards otherwise will hang
      System.exit(0);
    } else {
      // Launch GUI interface
      launch(args);
    }
  }
}
