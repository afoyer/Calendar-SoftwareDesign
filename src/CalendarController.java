import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Controller for the General Calendar View 
 * @author Aymeric, Irene, Surbhi
 *
 */

public class CalendarController implements Initializable {

 /**
  * variables
  */
	private boolean datestrategy;
	private LocalDate localDate;
    @FXML
    TableView<Event> table;
    @FXML
    Button plusButton;
    @FXML
    Button minusButton;
    @FXML
    DatePicker datePicker;
    @FXML
    Label dateLabel;
    @FXML
    Label blockLabel;
    @FXML
    Button refresh;
    @FXML
    Button showAllEvents;
    @FXML
    Button blockButton;
	private static String datetext;
	private static final String DATABASENAME = "EventData2";
	private static final String TABLENAME = "Events";
	


	
	/**
	 * Uses current strategy to get the events (whether it is all events or specific date events
	 * @return list of events
	 */
    public ObservableList<Event> getEvent(){
    	ObservableList<Event> events = FXCollections.observableArrayList();
    	
    	
    	FilterFactory factory = new FilterFactory();
		Filter filter = factory.createFilter(datestrategy,DATABASENAME,TABLENAME,datetext);
    	events = filter.showDisplay(DATABASENAME,TABLENAME,datetext);
        table.setItems(events);
        return events;
    }

    /**
     * Initializes tables
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	DataBaseHandler handler = new DataBaseHandler();
    	handler.createConnection(DATABASENAME);
    	handler.createTable(TABLENAME);
    	

    	 //Title column
        TableColumn<Event,String> TitleColumn = new TableColumn<>("Title");
        
        TitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        //Start_Time column
        TableColumn<Event,String> StartTimeColumn = new TableColumn<>("Start_Time");
        
        StartTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));

        //End_Time column
        TableColumn<Event, String> EndTimeColumn = new TableColumn<>("End_Time");
       
        EndTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        
        //priority column
        TableColumn<Event,Integer> PriorityColumn = new TableColumn<>("Priority");
      
        PriorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));
        
        //Date Column
        TableColumn<Event, String> DateColumn = new TableColumn<>("Date");
     
        DateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));

        
        //Description column
        TableColumn<Event, String> DescriptionColumn = new TableColumn<>("Description");
        DescriptionColumn.setMinWidth(300);
        DescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
        
       
       
        table.getColumns().addAll(TitleColumn, StartTimeColumn, EndTimeColumn, PriorityColumn, DateColumn, DescriptionColumn);
        
        getEvent();

        localDate = LocalDate.now();
        dateLabel.setText("All Events");
    }
    
    
    /**
     * Starts AddEvent Window
     * @param actionEvent - required for JavaFX (Apparently)
     * @throws IOException
     */
    @FXML
    
    
    /**
     * Creates add event window.
     * @param actionEvent
     * @throws IOException
     */
    public void createEventWindow(javafx.event.ActionEvent actionEvent) throws IOException{
        createAddWindow("AddEventWindow.fxml");
    }
    /**
     * Sets strategy to get specific date (using boolean) and sets specific date.
     * @param actionEvent
     */
    public void getDate(javafx.event.ActionEvent actionEvent) {
    	
    	LocalDate temp = datePicker.getValue();
        localDate = temp;
        datetext=temp.toString();
        UpdateBlockHandler handler = new UpdateBlockHandler();
        String text = handler.getBlockName(datetext);
        dateLabel.setText("");
        blockLabel.setText(text);
        
        datestrategy = true;
        getEvent();
       
        
        
    }
    /**
     * Returns string type of date
     * @return - date in string.
     */
    public static String getDatetext() {
    	return datetext;
    }
    /**
     * Deletes event when button is pressed
     */
    public void deleteEvent(){
        ObservableList<Event> eventSelected, allEvents;
        allEvents = table.getItems();
        eventSelected = table.getSelectionModel().getSelectedItems();
        if(eventSelected.size() == 0){
            Alert al = new Alert(Alert.AlertType.WARNING);
            Stage scene = (Stage) al.getDialogPane().getScene().getWindow();
            scene.getIcons().add(new Image("alert.png"));
            al.setTitle("Warning");
            al.setContentText("No event selected to delete. \nPlease select an event.");
            al.setHeaderText(null);
            al.showAndWait();
        }
        else {
            DeleteEventHandler delete = new DeleteEventHandler();
            for (Event event : eventSelected) {
                int id = event.getID();
                
                delete.deleteEvent(id);
            }
            getEvent();
            Alert al = new Alert(Alert.AlertType.CONFIRMATION);
            Stage scene = (Stage) al.getDialogPane().getScene().getWindow();
            scene.getIcons().add(new Image("alert.png"));
            al.setTitle("Success.");
            al.setContentText("Event Deleted.");
            al.setHeaderText(null);
            al.showAndWait();
        }

    }
    /**
     * Shows all events by resetting strategy and updating table.
     */
    public void showAllEvents(){
        datestrategy = false;
        dateLabel.setText("All Events");
        blockLabel.setText("");
        getEvent();
    }
    /**
     * Creates window set block.
     * @throws IOException
     */
    public void createBlockWindow() throws IOException{
        createAddWindow("BlockWindow.fxml");


    }
    /**
     * Creates and shows window given path.
     * @param path - pathname to fxml file.
     * @throws IOException
     */
    private void createAddWindow(String path)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource(path));
        Scene scene2 = new Scene(root);
        Stage window2 = new Stage();
        window2.setTitle("Set Block");
        window2.setScene(scene2);
        window2.getIcons().add(new Image("addEventIcon.png"));
        window2.setResizable(false);
        window2.showAndWait();
    }
   

}