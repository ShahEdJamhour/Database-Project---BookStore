package application;
/**
 *
 * @author ShahEd
 */
///////////////////////////////////////////////////////////////////////////////////////////////////
//  Pls note that this is only my part of the project . 
// My part is to deal with The Employees, Customers  and Books tables as well as building the general interfaces. 
// Operations like ' Add, Search, Delete  and update ' can be done. 
// The basic queries like " insert, delete , update , Count by , Order by" were used. 
////////////////////////////////////////////////////////////////////////////////////////////////////

import java.io.IOException;

import com.sun.glass.ui.Menu;
import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class AfterLogin {

	private Stage stage;
	private Scene scene;
	private Parent root;


	@FXML
	private Button employee;

	@FXML
    private Button customer;
	
	
	@FXML
	private Button order;
        
        	
	@FXML
	private Button reports;
        	
	@FXML
	private Button btCustomerBills;


        
	public void initialize() {
		
		BackgroundSize bSize = new BackgroundSize(7.0, 7.0, true, true, false, false);
		

	
	}
	
	@FXML
	void employeeOnAction(ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("Employee.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stage = new Stage();
		scene = new Scene(root);
		stage.setScene(scene);
                 stage.setTitle(" Employees");
		stage.show();
                
	}
        
        
        @FXML
	void Aboutus(ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("AboutUSLayout.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stage = new Stage();
		scene = new Scene(root);
		stage.setScene(scene);
                 stage.setTitle(" Employees");
		stage.show();
                
	}
        
        
        
        
          @FXML
    void reportOnAction(ActionEvent event) throws IOException {
		AnchorPane report = (AnchorPane)FXMLLoader.load(getClass().getResource("Report.fxml"));
		Scene scene = new Scene(report);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
    }

	 @FXML
	    void customerOnAction(ActionEvent event) {
		 try {
				root = FXMLLoader.load(getClass().getResource("Customer.fxml"));
				stage = new Stage();
				scene = new Scene(root);
				stage.setScene(scene);
                                 stage.setTitle("Customers");
				stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	    }

            

        
        //For Books button 
	@FXML
	void BooksOnAction(ActionEvent event) throws IOException {
		try {
			root = FXMLLoader.load(getClass().getResource("AvailableBooks.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stage = new Stage();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	


        
        

        @FXML
	void orderOnAction(ActionEvent event) throws IOException {
		AnchorPane order = (AnchorPane)FXMLLoader.load(getClass().getResource("Order.fxml"));
		Scene scene = new Scene(order);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
        
        
        ////////////////////////////////////////////////////////////////////////////////////
        
        
  
        
        
        
        
 
        
      



}
