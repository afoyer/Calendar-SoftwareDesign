import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;

/**
 * This will be the main window of our program that has several columns for now
 * @author Irene, Aymeric, Surbhi
 *
 */
public class Main extends Application {

    private Stage window;
    private TableView<Event> table;
 
  /**
   * sets up GUI window
   */
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Calendar");
        
      
        window = primaryStage;
        window.setTitle("Calendar");
        Parent root = FXMLLoader.load(getClass().getResource("Calendar.fxml"));
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.getIcons().add(new Image("calendaricon.png"));
        primaryStage.setResizable(false);
        window.show();
    }
    
  
   
    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }


}