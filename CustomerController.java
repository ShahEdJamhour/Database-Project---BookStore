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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

public class CustomerController implements Initializable{
	private String dbURL;
	private static String dbUsername = "root";
	private static String dbPassword = "TheShahed@$$$";
	private static String URL = "127.0.0.1";
	private static String port = "3306";
	private static String dbName = "COMP333";
	private static Connection con;




	@FXML
	private ResourceBundle resources;
	@FXML
	private TableView<Customer> client_table;

	@FXML
	private TableColumn<Customer, Integer> id_col;

	@FXML
	private TableColumn<Customer, String> name_col;

	@FXML
	private TableColumn<Customer, String> area_col;

	@FXML
	private TableColumn<Customer, Integer> phone_col;

	@FXML
	private TextField id;

	@FXML
	private TextField name;
        
        //to count the number of customers
        @FXML
         private TextField txNumberOfCustomer;

        

	@FXML
	private TextField area;

	@FXML
	private TextField phone;

	@FXML
	private Button add_button;
        @FXML
	private Button custBack;

	@FXML
	private Button delete_button;

	@FXML
	private TextField search;

	@FXML
	private Button search_button;

	@FXML
	private Button refresh_button;

	private ArrayList<Customer> client_data;
	private ObservableList<Customer> client_list;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		client_data = new ArrayList<>();
		try {
			getdata();

		}catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		id_col.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));

		name_col.setCellValueFactory(new PropertyValueFactory<Customer,String>("cname"));
		name_col.setCellFactory(TextFieldTableCell.<Customer>forTableColumn());
		name_col.setOnEditCommit(  
				(CellEditEvent<Customer, String> t) -> {
					((Customer) t.getTableView().getItems().get(
							t.getTablePosition().getRow())
							).setCname(t.getNewValue());
					updateName( t.getRowValue().getId(),t.getNewValue());
				});
		area_col.setCellValueFactory(new PropertyValueFactory<Customer,String>("area"));
		area_col.setCellFactory(TextFieldTableCell.<Customer>forTableColumn());
		area_col.setOnEditCommit(  
				(CellEditEvent<Customer, String> t) -> {
					((Customer) t.getTableView().getItems().get(
							t.getTablePosition().getRow())
							).setArea(t.getNewValue());
					updateAddress( t.getRowValue().getId(),t.getNewValue());
				});
		phone_col.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("phone_num"));
		phone_col.setCellFactory(TextFieldTableCell.<Customer,Integer>forTableColumn(new IntegerStringConverter()));
		phone_col.setOnEditCommit(        
				(CellEditEvent<Customer, Integer> t) -> {
					((Customer) t.getTableView().getItems().get(
							t.getTablePosition().getRow())
							).setPhone_num(t.getNewValue());
					updatePhone( t.getRowValue().getId(),t.getNewValue());
				});
		
		
		client_table.setItems(get(client_data));
	}

	private ObservableList<Customer> get(ArrayList<Customer> c) {
		client_list = FXCollections.observableArrayList();

		for (int i = 0; i < c.size(); i++) {

			if(c.get(i) != null)
				client_list.add(c.get(i));
		}
		return client_list;
	}

	private void getdata() throws ClassNotFoundException, SQLException {
		String SQL = "select * from clients";
		connectDB();
		System.out.println("Connection established");
                ///////////////////////////////////////////
                //show number of customers
                
                              Statement stmt1 = con.createStatement();
          ResultSet rs1 = stmt1.executeQuery("SELECT COUNT(*) FROM clients  ");
         rs1.next();
           txNumberOfCustomer.setText(rs1.getString(1));
               
                
                ///////////////////////////////////////////
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);
		while ( rs.next()) {
			Customer client = new Customer(
					Integer.parseInt(rs.getString(1)),
					rs.getString(2),
					rs.getString(3),
					Integer.parseInt(rs.getString(4)));	
			client_data.add(client);
		}
		rs.close();
		con.close();
		System.out.println("Connection closed " );
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

	
	
	

	
		@FXML
	void custBackOnAction(ActionEvent event) {
		Node node = (Node) event.getSource();
		Stage thisStage = (Stage) node.getScene().getWindow();
		thisStage.close();
	}
        
        
        @FXML
	void employeeBackOnAction(ActionEvent event) {
		Node node = (Node) event.getSource();
		Stage thisStage = (Stage) node.getScene().getWindow();
		thisStage.close();
	}

	
	

	@FXML
	void deleteClient(ActionEvent event) {
		ObservableList<Customer> selectedRows = client_table.getSelectionModel().getSelectedItems();
		ArrayList<Customer> rows = new ArrayList<>(selectedRows);
		rows.forEach(row -> { 
			deleteRow(row); 
			client_table.refresh();

		});
	}

	@FXML
	void addClient(ActionEvent event) throws NumberFormatException, ClassNotFoundException, SQLException {
		
		 if(idExist(Integer.parseInt(id.getText()))) {
			Alert("This ID Exists");
		}
		
		else {
			Customer client = new Customer(
					Integer.parseInt(id.getText()),
					name.getText(),
					area.getText(),
					
					Integer.parseInt(phone.getText()));
			client_list.add(client);
			insertData(client);
			id.clear();
			name.clear();
			area.clear();
		
			phone.clear();
		}
	}

	private boolean idExist(int id) throws ClassNotFoundException, SQLException {
		String SQL = "select * from clients where cid="+id+";";
		connectDB();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);
		if(rs.last()) 
			return true;
		return false;
	}

	@FXML
	void refreshClient(ActionEvent event) {
		client_list.clear();
		search.clear();
		id_col.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));
		name_col.setCellValueFactory(new PropertyValueFactory<Customer,String>("cname"));
		area_col.setCellValueFactory(new PropertyValueFactory<Customer,String>("area"));
		phone_col.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("phone_num"));
		client_table.setItems(get(client_data));
	}

	@FXML
	void searchClient(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
		if(!search.getText().matches("[0-9]+"))
			Alert("Enter Client ID to Search");
		else {
			connectDB();
			try {
				client_list.clear();			
				String SQL = "select * from clients where cid="+Integer.parseInt(search.getText())+";";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(SQL);
				while ( rs.next()) {
					client_list.add(new Customer(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),
							Integer.parseInt(rs.getString(4))));		
				}
				rs.close();
				con.close();
			}
			catch(Exception EX){
				System.out.print(EX);
			}
		}
	}

	private void insertData(Customer client) {
		try {
			System.out.println("Insert into clients values ("+client.getId()+",'"+client.getCname()+"','"+client.getArea()+"'"
					+ ","+client.getPhone_num()+");");
			connectDB();
			ExecuteStatement("Insert into clients values ("+client.getId()+",'"+client.getCname()+"','"+client.getArea()+"'"
					+ ","+client.getPhone_num()+");");
			con.close();
			System.out.println("Connection closed" + client_data.size());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void ExecuteStatement(String SQL) throws SQLException {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(SQL);
			stmt.close();	 
		}
		catch(SQLException s) {
			s.printStackTrace();
			System.out.println("SQL statement is not executed!");		  
		}
	}
	
	private void deleteRow(Customer row) {
		try {
			
				connectDB();
				String SQL = "select * from orders where cid="+row.getId()+";";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(SQL);
				if(rs.next() == true)
	
				
					System.out.println("delete from clients where cid="+row.getId() + ";");
					connectDB();
					ExecuteStatement("delete from clients where cid="+row.getId()+ ";");
					client_table.getItems().remove(row);
					
				con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateName(int id, String name) {	
		try {
			System.out.println("update clients set cname = '"+name + "' where cid = "+id);
			connectDB();
			ExecuteStatement("update clients set cname='"+name+"' where cid="+id+";");
			con.close();
			System.out.println("Connection closed");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateAddress(int id, String area) {	
		try {
			
			connectDB();
			ExecuteStatement("update clients set area='"+area+"' where cid="+id+";");
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updatePhone(int id, int phone) {
		try {
			System.out.println("update clients set phone_num = "+phone + " where cid = "+id);
			connectDB();
			ExecuteStatement("update clients set phone_num = "+phone + " where cid = "+id+";");
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	

	

	void Alert(String message) {
		javafx.scene.control.Alert alert = new Alert(AlertType.ERROR);
		alert.setContentText(message);
		alert.setTitle("Error!");
		alert.setHeaderText(null);
		alert.setResizable(false);
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.show();
	}
}

