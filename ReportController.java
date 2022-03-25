package application;
/**
 *
 * @author ShahEd
 */

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;

import javafx.stage.Modality;
import javafx.stage.Stage;

public class ReportController implements Initializable{
	private String dbURL;
	private static String dbUsername = "root";
	private static String dbPassword = "TheShahed@$$$";
	private static String URL = "127.0.0.1";
	private static String port = "3306";
	private static String dbName = "comp333";
	private static Connection con;

	@FXML
	private ResourceBundle resources;


	
	@FXML
	private DatePicker fromDate;

	@FXML
	private DatePicker toDate;

	@FXML
	private TextField text1;

	@FXML
	private TextField text2;
	
    @FXML
    private TextField text3;

    @FXML
    private TextField text4;
    
      @FXML
	private TextField BookName1;

	@FXML
	private Button confirm;

  @FXML
    private BarChart<String, Number> financialBarChart;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
            fromDate.getEditor().clear();
		toDate.getEditor().clear();
                   financialBarChart.getData().clear();
                        text1.clear();
                            text2.clear();
		
	}

	@FXML
	void confirmDate(ActionEvent event) {
		if(fromDate.getValue() == null || toDate.getValue() == null) {
			Errormsg("You need to specify a date ");
		}
		else {
			int OrdersCount = 0;
			double sales_value = 0;
                        double BooksValue = 0;
			int bcopies = 0;
		
			try {
                            	
                            
//                               Statement stmt1 = con.createStatement();
//                                    ResultSet rs1 = stmt1.executeQuery("SELECT COUNT(*) FROM Books  ");
//                                        rs1.next();
//                                                BookName1.setText(rs1.getString(1));
                            
                            
                            
                            
				connectDB();
				String SQL = "select bcount from orders O, orderdetails OL, bill B where order_date > '"+Date.valueOf(fromDate.getValue())+
						"'and order_date < '"+Date.valueOf(toDate.getValue())+"' and O.order_num = OL.order_num = B.order_num and B.isPaid =1;";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(SQL);
				while ( rs.next()) {
					OrdersCount += Integer.parseInt(rs.getString(1));
				}
				text1.setText(String.valueOf(OrdersCount));
				
                                
				SQL = "select total_price from bill where bill_date > '"+Date.valueOf(fromDate.getValue())+
						"'and bill_date < '"+Date.valueOf(toDate.getValue())+"' and isPaid = 1;";
				rs = stmt.executeQuery(SQL);
				while ( rs.next()) {
					sales_value += Double.parseDouble(rs.getString(1));
				}
				text2.setText(String.valueOf(sales_value));
				
				SQL = "select NumberOfCopies from Books;";
				rs = stmt.executeQuery(SQL);
				while ( rs.next()) {
					bcopies += Integer.parseInt(rs.getString(1));
				}
				text3.setText(String.valueOf(bcopies));
				
				SQL = "select NumberOfCopies, price from Books;";
				rs = stmt.executeQuery(SQL);
				while ( rs.next()) {
					BooksValue = BooksValue + (Integer.parseInt(rs.getString(1)) * Double.parseDouble(rs.getString(2)));
				}
				text4.setText(String.valueOf(BooksValue));
			}catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
        
	void Errormsg(String message) {
		javafx.scene.control.Alert alert = new Alert(AlertType.ERROR);
		alert.setContentText(message);
		alert.setTitle("Error!");
		alert.setHeaderText(null);
		alert.setResizable(false);
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.show();
	}
        
        @FXML
	void PrintOnAction(ActionEvent event) {

	}
        

	private void connectDB() throws ClassNotFoundException, SQLException {
		dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?verifyServerCertificate=false";
		Properties p = new Properties();
		p.setProperty("user", dbUsername);
		p.setProperty("password", dbPassword);
		p.setProperty("useSSL", "false");
		p.setProperty("autoReconnect", "true");
		Class.forName("com.mysql.jdbc.Driver");

		con = DriverManager.getConnection (dbURL, p);
	}




 

}

