import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.stage.Stage;
/**
 * Controls the set block window
 * @author Aymeric, Irene, Surbhi
 *
 */
public class BlockWindowController {
	//Variables
    @FXML
    TextField title;
    @FXML
    ToggleGroup block;
    @FXML
    RadioButton block1;
    @FXML
    RadioButton block2;
    @FXML
    RadioButton block3;
    @FXML
    RadioButton block4;
    @FXML
    RadioButton block5;
    @FXML
    RadioButton block6;
    @FXML
    RadioButton block7;
    @FXML
    RadioButton block8;
    @FXML
    Button addButton;
    private String alert;
    
    private boolean allChecked = true;

    /**
     * Checks if title is set then calls method to set block name to corresponding block on SQL table
     */
    public void getBlock(){
        if(title.getText().length() == 0){
            alert += "\n No title inserted.";
            allChecked = false;
        }
        if(!allChecked){


            Alert();
            alert = "ERROR:";
            allChecked = true;
        }
        else{
            RadioButton button = (RadioButton) block.getSelectedToggle();
            int numPriority = 0;
            if(button.getText().equals("Block 1")){
                numPriority = 1;
            }
            else if(button.getText().equals("Block 2")){
                numPriority = 2;
            }
            else if(button.getText().equals("Block 3")){
                numPriority = 3;
            }
            else if(button.getText().equals("Block 4")){
                numPriority = 4;
            }
            else if(button.getText().equals("Block 5")){
                numPriority = 1;
            }
            else if(button.getText().equals("Block 6")){
                numPriority = 2;
            }
            else if(button.getText().equals("Block 7")){
                numPriority = 3;
            }
            else if(button.getText().equals("Block 8")){
                numPriority = 4;
            }
            UpdateBlockHandler setter = new UpdateBlockHandler();
            setter.UpdateBlockName(numPriority, title.getText());
            Stage stage = (Stage) addButton.getScene().getWindow();
            stage.close();
        }
    }
    /**
     * Alerts user if title is not set.
     */
    public void Alert(){
        Alert al = new Alert(Alert.AlertType.WARNING);
        Stage scene = (Stage) al.getDialogPane().getScene().getWindow();
        scene.getIcons().add(new Image("alert.png"));
        al.setTitle("Warning");
        al.setContentText(alert);
        al.setHeaderText(null);
        al.showAndWait();
    }
}
