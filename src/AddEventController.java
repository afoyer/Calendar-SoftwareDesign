import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.time.LocalDate;
/**
 * Controller for Add Event Window
 * @author Aymeric, Irene, Surbhi
 *
 */
public class AddEventController {
	//Lotsa Variables
    private static final int DEFAULT_STARTHOUR = 8;
    private static final int DEFAULT_ENDHOUR = 8;
    private static final int DEFAULT_MINUTE = 0;
    private boolean allChecked = true;
    private static final String DATABASENAME = "EventData2";
    private static final String TABLENAME = "Events";
    LocalDate date;
    String alert = "ERROR:";
    //Cannot be set to private.
    @FXML
    RadioButton priority3;
    @FXML
    RadioButton priority1;
    @FXML
    RadioButton priority2;
    @FXML
    TextField title;
    @FXML
    ComboBox startTimeHours;
    @FXML
    ComboBox startTimeMinutes;
    @FXML
    ComboBox endTimeHours;
    @FXML
    ComboBox endTimeMinutes;
    @FXML
    TextField description;
    @FXML
    Button addButton;
    @FXML
    DatePicker datePicker;
    @FXML
    ToggleGroup priority;
    /**
     * Initialize menu boxes with time options to limit user.
     */
    public void initialize(){
        for(int i = 0; i < 10; i++){
            endTimeHours.getItems().add("0" + i);
            startTimeHours.getItems().add("0"+ i);
        }
        for(int i = 10; i < 24; i++){
            endTimeHours.getItems().add((Integer.toString(i)));
            startTimeHours.getItems().add(Integer.toString(i));
        }
        for(int i = 0; i < 10; i++){
            endTimeMinutes.getItems().add("0"+i);
            startTimeMinutes.getItems().add("0" +i);
        }
        for(int i = 10; i < 60; i++){
            endTimeMinutes.getItems().add(Integer.toString(i));
            startTimeMinutes.getItems().add(Integer.toString(i));
        }
        //Set Default Values.
        startTimeHours.getSelectionModel().select(DEFAULT_STARTHOUR);
        startTimeMinutes.getSelectionModel().select(DEFAULT_MINUTE);
        endTimeHours.getSelectionModel().select(DEFAULT_ENDHOUR);
        endTimeMinutes.getSelectionModel().select(DEFAULT_MINUTE);
        datePicker.setValue(LocalDate.now());
        priority1.setSelected(true);
    }
    /**
     * Checks for user input then if valid calls addEventHandler to deal with SQL
     * @param e
     */
    public void addEvent(ActionEvent e){
        date = datePicker.getValue();


        if(title.getText().length() == 0){
            alert += "\n No title inserted.";
            allChecked = false;
        }

        if(description.getText().length()> 500){
            alert += "\n Description too long. Current length : " +description.getText().length();
            allChecked = false;
        }


        if(!allChecked){


            Alert();
            alert = "ERROR:";
            allChecked = true;
        }

        else{
            RadioButton button = (RadioButton) priority.getSelectedToggle();
            int numPriority = 0;
            if(button.getText().equals("!")){
                numPriority = 1;
            }
            if(button.getText().equals("!!")){
                numPriority = 2;
            }
            if(button.getText().equals("!!!")){
                numPriority = 3;
            }
        String title = this.title.getText();
        String startTime =(String) startTimeHours.getValue();
        startTime += ":" + startTimeMinutes.getValue();
        String endTime = (String) endTimeHours.getValue();
        endTime += ":" + endTimeMinutes.getValue();
        String info = description.getText();
        date = datePicker.getValue();
        String dateString = date.toString();
        AddEventHandler database = new AddEventHandler(title,startTime,endTime,numPriority,info,dateString);
        database.addtoDatabase(DATABASENAME,TABLENAME);
        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();

        }
    }
    /**
     * Alert Window if addEvent encounters problem to help user see what they missed.
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
    /**
     * Change the end time along with the start time
     */ 
    public void updateEndTime(){

        if (isStartGreaterThanEndHour()){
            int hour = startTimeHours.getSelectionModel().getSelectedIndex();
            endTimeHours.getSelectionModel().select(hour);

        }
        checkMinutes();

    }
    /**
     * Change the start time if the end time is earlier than the start time
     */
    public void updateStartTime(){

        if (isStartGreaterThanEndHour()){
            int hour = endTimeHours.getSelectionModel().getSelectedIndex();
            startTimeHours.getSelectionModel().select(hour);

        }
        checkMinutes();

    }
    
    /**
     * check whether the start time if the end time is earlier than the start time
     * return true or false
     */

    private boolean isStartGreaterThanEndHour() {
        return (startTimeHours.getSelectionModel().getSelectedIndex() > endTimeHours.getSelectionModel().getSelectedIndex());

    }
    /**
     * if the hours are the same and the end minutes, when set, are lower than the start minutes, the start minutes is updated.
     */
    public void checkMinutes(){
        if(startTimeHours.getSelectionModel().getSelectedIndex() == endTimeHours.getSelectionModel().getSelectedIndex()){
            int endMins = endTimeMinutes.getSelectionModel().getSelectedIndex();
            int startMins = startTimeMinutes.getSelectionModel().getSelectedIndex();
            if(startMins > endMins){
                startTimeMinutes.getSelectionModel().select(endMins);
            }
        }
    }

    /**
     * This method was specifically added because of Errors
     * It updates the end time if the start minute is updated and is above the end min.
     */
    public void checkStartMinutes(){
        if(startTimeHours.getSelectionModel().getSelectedIndex() == endTimeHours.getSelectionModel().getSelectedIndex()){
            int endMins = endTimeMinutes.getSelectionModel().getSelectedIndex();
            int startMins = startTimeMinutes.getSelectionModel().getSelectedIndex();
            if(startMins > endMins){
                endTimeMinutes.getSelectionModel().select(startMins);
            }
        }
    }



}
