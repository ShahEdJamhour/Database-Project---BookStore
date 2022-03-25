package application;


/**
 *
 * @author ShahEd
 */


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

public class AvailableBooksController implements Initializable{
	private String dbURL;
	private static String dbUsername = "root";
	private static String dbPassword = "TheShahed@$$$";
	private static String URL = "127.0.0.1";
	private static String port = "3306";
	private static String dbName = "COMP333";
	private static Connection con;


	
        //Children 
	
	@FXML
	private Button order;


	

	@FXML
	private TextField search;

	@FXML
	private TableView<AvailableBooks> AvailableBooksTable;

	@FXML
	private TableColumn<AvailableBooks, Integer> id_col;

	@FXML
	private TableColumn<AvailableBooks, String> name_col;

	@FXML
	private TableColumn<AvailableBooks, String> comp_col;

	@FXML
	private TableColumn<AvailableBooks, String> type_col;

	@FXML
	private TableColumn<AvailableBooks, Double> p;

	@FXML
	private TableColumn<AvailableBooks, Double> retail_col;

	@FXML
	private TableColumn<AvailableBooks, Integer> count_col;

	@FXML
	private TableColumn<AvailableBooks, String> a;

	@FXML
	private TableColumn<AvailableBooks, Date> exDate_col;

	@FXML
	private TextField id;

	@FXML
	private TextField BookName;
        @FXML
	private TextField BookName1;

	@FXML
	private TextField comp;

	@FXML
	private TextField cat;

	@FXML
	private TextField price;

	@FXML
	private TextField count;

	@FXML
	private TextField costprice;

	@FXML
	private TextField Author;

	@FXML
	private DatePicker publishdate;

	@FXML
	private Button add_button;

	@FXML
	private Button delete_button;

	@FXML
	private Button search_button;


	@FXML
	private TextField ClientOrder;

	@FXML
	private TextField CountOrder;

	@FXML
	private Button AddOrder;

	@FXML
	private TextField OrderNum;

	@FXML
	private Button confirm;
        
        // combo box for search

	@FXML
	private ComboBox<String> searchBy = new ComboBox<>();
	ObservableList<String> combobox = FXCollections.observableArrayList("ISBN", "Book Title", "Publishing House",
			"Category", "Author");
	private ArrayList<AvailableBooks> BookInfo;
	private ObservableList<AvailableBooks> BookList;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		BookInfo = new ArrayList<>();
		try {
			getdata();
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
                
                
                //////////////////////
                //Book Table coloums
                ////////////////////

		id_col.setCellValueFactory(new PropertyValueFactory<AvailableBooks, Integer>("BookID"));

		Titlecolo();
		comp_col.setCellValueFactory(new PropertyValueFactory<AvailableBooks, String>("PublishingHouse"));
		comp_col.setCellFactory(TextFieldTableCell.<AvailableBooks>forTableColumn());
		comp_col.setOnEditCommit(  
				(CellEditEvent<AvailableBooks, String> t) -> {
					((AvailableBooks) t.getTableView().getItems().get(
							t.getTablePosition().getRow())
							).setPublishingHouse(t.getNewValue());
				PublishHouseUpdate( t.getRowValue().getBookID(),t.getNewValue());
				});
		type_col.setCellValueFactory(new PropertyValueFactory<AvailableBooks, String>("Category"));
		type_col.setCellFactory(TextFieldTableCell.<AvailableBooks>forTableColumn());
		type_col.setOnEditCommit(  
				(CellEditEvent<AvailableBooks, String> t) -> {
					((AvailableBooks) t.getTableView().getItems().get(
							t.getTablePosition().getRow())
							).setCategory(t.getNewValue());
					updateCategory( t.getRowValue().getBookID(),t.getNewValue());
				});
		p.setCellValueFactory(new PropertyValueFactory<AvailableBooks, Double>("Price")); 
		p.setCellFactory(TextFieldTableCell.<AvailableBooks,Double>forTableColumn(new DoubleStringConverter()));
		p.setOnEditCommit(  
				(CellEditEvent<AvailableBooks, Double> t) -> {
					((AvailableBooks) t.getTableView().getItems().get(
							t.getTablePosition().getRow())
							).setPrice(t.getNewValue());
					updateprice( t.getRowValue().getBookID(),t.getNewValue());
				});
		retail_col.setCellValueFactory(new PropertyValueFactory<AvailableBooks, Double>("CostPrice")); 
		retail_col.setCellFactory(TextFieldTableCell.<AvailableBooks,Double>forTableColumn(new DoubleStringConverter()));
		retail_col.setOnEditCommit(  
				(CellEditEvent<AvailableBooks, Double> t) -> {
					((AvailableBooks) t.getTableView().getItems().get(
							t.getTablePosition().getRow())
							).setCostPrice(t.getNewValue());
					updateCostp( t.getRowValue().getBookID(),t.getNewValue());
				});
		count_col.setCellValueFactory(new PropertyValueFactory<AvailableBooks, Integer>("NumberOfCopies")); 
		count_col.setCellFactory(TextFieldTableCell.<AvailableBooks,Integer>forTableColumn(new IntegerStringConverter()));
		count_col.setOnEditCommit(  
				(CellEditEvent<AvailableBooks, Integer> t) -> {
					((AvailableBooks) t.getTableView().getItems().get(
							t.getTablePosition().getRow())
							).setNumberOfCopies(t.getNewValue());
					updateCount( t.getRowValue().getBookID(),t.getNewValue());
				});
		a.setCellValueFactory(new PropertyValueFactory<AvailableBooks, String>("Author"));
		a.setCellFactory(TextFieldTableCell.<AvailableBooks>forTableColumn());
		a.setOnEditCommit(  
				(CellEditEvent<AvailableBooks, String> t) -> {
					((AvailableBooks) t.getTableView().getItems().get(
							t.getTablePosition().getRow())
							).setAuthor(t.getNewValue());
				});
		exDate_col.setCellValueFactory(new PropertyValueFactory<AvailableBooks, Date>("PublishDate"));

		AvailableBooksTable.setItems(get(BookInfo));
		searchBy.setItems(combobox);
	}

    private void Titlecolo() {
        name_col.setCellValueFactory(new PropertyValueFactory<AvailableBooks, String>("BookName"));
        name_col.setCellFactory(TextFieldTableCell.<AvailableBooks>forTableColumn());
        name_col.setOnEditCommit(
                (CellEditEvent<AvailableBooks, String> t) -> {
                    ((AvailableBooks) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setBookName(t.getNewValue());
                    TitleUpdate( t.getRowValue().getBookID(),t.getNewValue());
                });
    }

	private ObservableList<AvailableBooks> get(ArrayList<AvailableBooks> c) {
		BookList = FXCollections.observableArrayList();

		for (int i = 0; i < c.size(); i++) {

			if(c.get(i) != null)
				BookList.add(c.get(i));
		}
		return BookList;
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private void getdata() throws ClassNotFoundException, SQLException {
		String SQL = "select * from Books";
		connectDB();
		System.out.println("Connection established");
                
                /////////////////////////////////////////////////////////////
                //count num of books
                /////////////////////////////////////////////////////////////
                
                              Statement stmt1 = con.createStatement();
                                    ResultSet rs1 = stmt1.executeQuery("SELECT COUNT(*) FROM Books  ");
                                        rs1.next();
                                                BookName1.setText(rs1.getString(1));
                /////////////////////////////////////////////////////////
     
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);
		while ( rs.next() ) {
//			System.out.println(rs.getString(1)+ " "+ rs.getString(2) + " "+rs.getString(3) + " " + rs.getString(4)+
//					" " + rs.getString(5)+" " + rs.getString(6)+" " + rs.getString(7)+" " + rs.getString(8)+" " + rs.getString(9));
			BookInfo.add(new AvailableBooks(
					rs.getString(1),
					Integer.parseInt(rs.getString(2)),
					rs.getString(3),
					rs.getString(4),
					Double.parseDouble(rs.getString(5)),
					Double.parseDouble(rs.getString(6)),
					Integer.parseInt(rs.getString(7)),
					rs.getString(8),
					Date.valueOf(rs.getString(9))));
		}
		rs.close();
		con.close();
		
	}
        ////////////////////////
        //count number of orders
        ////////////////////////
	private void getCount() throws ClassNotFoundException, SQLException {
		connectDB();
		String SQL = "select order_num from orders order by 1 desc;";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);
		int num;
		while(rs.next()) {
			num = Integer.parseInt(rs.getString(1))+1;
			break;
		}
		
		stmt.close();
		con.close();
	}
//////////////////
//connect to db
//////////////        
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

          //////////////////
          // load order stage
          /////////////////
        

        private Stage stage;
	private Scene scene;
	private Parent root;

     
           //For order button 
	@FXML
	void orderOnAction(ActionEvent event) throws IOException {
		try {
			root = FXMLLoader.load(getClass().getResource("Order.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stage = new Stage();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	

//////////////////////////////////
 // Let user Insert books details
 ////////////////////////////////////
	

  

	@FXML
	void InsertBook(ActionEvent event) throws NumberFormatException, ClassNotFoundException, SQLException {
            // input error
		if(!id.getText().matches("[0-9]+") || !price.getText().matches("[0-9]+") || !costprice.getText().matches("[0-9]+") ||
				!count.getText().matches("[0-9]+") || !(publishdate.getValue() != null) || !(BookName.getText().length() > 0) ||
				!(cat.getText().length() > 0) || !(comp.getText().length() > 0) || !(Author.getText().length() > 0)) {
			errormessage("Check your input fields");
		}
                // if there is already data
		else if(dataExist(Integer.parseInt(id.getText()),BookName.getText(),comp.getText(),Author.getText(),cat.getText(),
				Double.parseDouble(price.getText()),Double.parseDouble(costprice.getText()),Date.valueOf(publishdate.getValue()))) {
                    
                    //update statment
			String SQL = "update Books set NumberOfCopies = NumberOfCopies+"+Integer.parseInt(count.getText())+" where BookID="+Integer.parseInt(id.getText())+";";
			connectDB();
			Statement stmt = con.createStatement();
			stmt.executeUpdate(SQL);
			stmt.close();
			AvailableBooksTable.refresh();
			comp.clear();
			id.clear();
			BookName.clear();
			cat.clear();
			price.clear();
			costprice.clear();
			count.clear();
			Author.clear();
			publishdate.getEditor().clear();
		}
		else if(!ISBNExists(Integer.parseInt(id.getText())) && Titleexits(BookName.getText()) &&
				!PubDate(Integer.parseInt(id.getText()), BookName.getText(), Date.valueOf(publishdate.getValue()))){
                    // if there isnt data add book
                    AddBook();
		}
		else if(ISBNExists(Integer.parseInt(id.getText()))){
			errormessage(" Isbn Already Exists!");
		}
		else if(Titleexits(BookName.getText())){
			errormessage("Title  Already Exists!");
		}
		else {
			AvailableBooks Books = new AvailableBooks(
					comp.getText(),
					Integer.parseInt(id.getText()),
					BookName.getText(),
					cat.getText(),
					Double.parseDouble(price.getText()),
					Double.parseDouble(costprice.getText()),
					Integer.parseInt(count.getText()),
					Author.getText(),
					Date.valueOf(publishdate.getValue()));
			BookList.add(Books);
			insertData(Books);
			comp.clear();
			id.clear();
			BookName.clear();
			cat.clear();
			price.clear();
			publishdate.getEditor().clear();
                        costprice.clear();
			count.clear();
			Author.clear();
		}
	}
///////////////////////////////////////////////////////////////////////////
//        add  book to list
/////////////////////////////////////////////////////////////        
    private void AddBook() throws NumberFormatException {
        AvailableBooks Books = new AvailableBooks(
                comp.getText(),
                Integer.parseInt(id.getText()),
                BookName.getText(),
                cat.getText(),
                Double.parseDouble(price.getText()),
                Double.parseDouble(costprice.getText()),
                Integer.parseInt(count.getText()),
                Author.getText(),
                Date.valueOf(publishdate.getValue()));
        BookList.add(Books);
        insertData(Books);
        comp.clear();
        id.clear();
        BookName.clear();
        cat.clear();
        price.clear();
        costprice.clear();
        count.clear();
        Author.clear();
        publishdate.getEditor().clear();
        AvailableBooksTable.refresh();
    }
        ////////////////////
        //check if title exists
         /////////////////////////////
	private boolean Titleexits(String BookName) throws ClassNotFoundException, SQLException {
		String SQL = "select * from Books where BookName='"+BookName+"';";
		connectDB();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);
		if(rs.last()) 
			return true;
		return false;
	}
/////////////////////////////////////////////////////////////////
//check if ISBN exists
	private boolean ISBNExists(int id) throws ClassNotFoundException, SQLException {
		String SQL = "select * from Books where BookID="+id+";";
		connectDB();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);
		if(rs.last()) 
			return true;
		return false;
	}

        /////////////////////////////////////////////////////////////////////////////
        //if there is already data
        /////////////////////////////////////////////////////////////////////////////        
	private boolean dataExist(int id, String BookName, String comp, String storage, String type, double costprice, 
			double price, Date date) throws ClassNotFoundException, SQLException {
		String SQL = "select * from Books where BookID="+id+" and BookName='"+BookName+"' and PublishingHouse='"+comp+"' and Category='"+type+
				"' and Author='"+storage+"' and Price="+costprice+" and CostPrice="+price+
				" and PublishDate='"+date+"';";
		connectDB();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);
		if(rs.last()) 
			return true;
		return false;
	}
        
        ///////////////////////////
        //CHECK IF PUBLISH HOUSE EXISTS
        /////////////////////////////
        private boolean PHEXISTS(String BookName) throws ClassNotFoundException, SQLException {
		String SQL = "select * from Books where PublishingHouse='"+BookName+"';";
		connectDB();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);
		if(rs.getRow() == 1) 
			return true;
		return false;
	}
        ///////
        //PUBLISH DATE
        ///////

	private boolean PubDate(int id, String BookName, Date date ) throws ClassNotFoundException, SQLException{
		String SQL = "select * from Books where PublishDate='"+date+"' and BookID="+id+" and BookName='"+BookName+"';";
		connectDB();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);
		if(rs.last()) 
			return true;
		return false;
	}
        
// delete from BOOK list
	@FXML
	void deleteBook(ActionEvent event) {
		ObservableList<AvailableBooks> selectedRows = AvailableBooksTable.getSelectionModel().getSelectedItems();
		ArrayList<AvailableBooks> rows = new ArrayList<>(selectedRows);
		rows.forEach(row -> {
			AvailableBooksTable.getItems().remove(row); 
			deleteRow(row); 
			AvailableBooksTable.refresh();
		}); 
	}
// search by 
	@FXML
	void SearchABook(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
		connectDB();
		try {
			String SQL;
			Statement stmt = con.createStatement();
			ResultSet rs = null;
                        //search by isbn
                            if(searchBy.getValue() == "ISBN") {
				if(!search.getText().matches("[0-9]+")) {
					errormessage(" Isbn must be an Integer!");
					search.clear();
				}
				else {
					BookList.clear();
					SQL = "select * from Books where BookId="+Integer.parseInt(search.getText())+";";
					rs = stmt.executeQuery(SQL);
					while(rs.next())
						BookList.add(new AvailableBooks(rs.getString(1),Integer.parseInt(rs.getString(2)),rs.getString(3),rs.getString(4),
								Double.parseDouble(rs.getString(5)),Double.parseDouble(rs.getString(6)),Integer.parseInt(rs.getString(7)),
								rs.getString(8),Date.valueOf(rs.getString(9))));
				}
			}
                            //search by title
			else if(searchBy.getValue() == "Book Title") {
				if(!(search.getText().length() > 0)) {
					errormessage("Error");
                                        errormessage("Make sure to enter the Book title !");
					search.clear();
				}
				else {
					BookList.clear();
					SQL = "select * from Books where BookName='"+search.getText()+"';";
					rs = stmt.executeQuery(SQL);
					while(rs.next())
						BookList.add(new AvailableBooks(rs.getString(1),Integer.parseInt(rs.getString(2)),rs.getString(3),rs.getString(4),
								Double.parseDouble(rs.getString(5)),Double.parseDouble(rs.getString(6)),Integer.parseInt(rs.getString(7)),
								rs.getString(8),Date.valueOf(rs.getString(9))));
				}
			}
                        //search by publishhouse
			else if(searchBy.getValue() == "Publishing House") {
				if(!(search.getText().length() > 0)) {
					errormessage("No Publishing House entered");
					search.clear();
				}
				else {
					BookList.clear();
					SQL = "select * from Books where PublishingHouse='"+search.getText()+"';";
					rs = stmt.executeQuery(SQL);
					while(rs.next())
						BookList.add(new AvailableBooks(rs.getString(1),Integer.parseInt(rs.getString(2)),rs.getString(3),rs.getString(4),
								Double.parseDouble(rs.getString(5)),Double.parseDouble(rs.getString(6)),Integer.parseInt(rs.getString(7)),
								rs.getString(8),Date.valueOf(rs.getString(9))));
				}
			}
                        //search by category
			else if(searchBy.getValue() == "Category") {
				if(!(search.getText().length() > 0)) {
					errormessage("No category entered!");
					search.clear();
				}
				else {
					BookList.clear();
					SQL = "select * from Books where Category='"+search.getText()+"';";
					rs = stmt.executeQuery(SQL);
					while(rs.next())
						BookList.add(new AvailableBooks(rs.getString(1),Integer.parseInt(rs.getString(2)),rs.getString(3),rs.getString(4),
								Double.parseDouble(rs.getString(5)),Double.parseDouble(rs.getString(6)),Integer.parseInt(rs.getString(7)),
								rs.getString(8),Date.valueOf(rs.getString(9))));
				}
			}
                        //search by author
			else if(searchBy.getValue() == "Author") {
				if(!(search.getText().length() > 0)) {
					errormessage("Please Enter the Storage Location you want to Search for!");
					search.clear();
				}
				else {
					BookList.clear();
					SQL = "select * from Books where Author='"+search.getText()+"';";
					rs = stmt.executeQuery(SQL);
					while(rs.next())
						BookList.add(new AvailableBooks(rs.getString(1),Integer.parseInt(rs.getString(2)),rs.getString(3),rs.getString(4),
								Double.parseDouble(rs.getString(5)),Double.parseDouble(rs.getString(6)),Integer.parseInt(rs.getString(7)),
								rs.getString(8),Date.valueOf(rs.getString(9))));
				}
			}
			else {
				errormessage("How would u like to serach?");
			}
			rs.close();
			con.close();
		}catch(Exception EX) {
			System.out.println(EX);
		}
	}

	@FXML
	void Refresh(ActionEvent event) throws ClassNotFoundException, SQLException {
		BookList.clear();
		search.clear();
		id_col.setCellValueFactory(new PropertyValueFactory<AvailableBooks, Integer>("BookID"));
		name_col.setCellValueFactory(new PropertyValueFactory<AvailableBooks, String>("BookName"));
		comp_col.setCellValueFactory(new PropertyValueFactory<AvailableBooks, String>("PublishingHouse"));
		type_col.setCellValueFactory(new PropertyValueFactory<AvailableBooks, String>("Category"));
		p.setCellValueFactory(new PropertyValueFactory<AvailableBooks, Double>("Price")); 
		retail_col.setCellValueFactory(new PropertyValueFactory<AvailableBooks, Double>("CostPrice")); 
		count_col.setCellValueFactory(new PropertyValueFactory<AvailableBooks, Integer>("NumberOfCopies")); 
		a.setCellValueFactory(new PropertyValueFactory<AvailableBooks, String>("Author"));
		exDate_col.setCellValueFactory(new PropertyValueFactory<AvailableBooks, Date>("PublishDate"));
		searchBy.setItems(combobox);
		ClientOrder.clear();
		CountOrder.clear();
		comp.clear();
		id.clear();
		BookName.clear();
		cat.clear();
		price.clear();
		costprice.clear();
		count.clear();
		Author.clear();
		publishdate.getEditor().clear();
		getCount();
	}

	private void insertData(AvailableBooks AvailableBooks) {
		try {
//connect to database then excute isert statement 
			connectDB();
			ExecuteStatement("Insert into Books values ('"+AvailableBooks.getPublishingHouse()+"',"+AvailableBooks.getBookID()+",'"+AvailableBooks.getBookName()+"'"
					+ ",'"+ AvailableBooks.getCategory() +"',"+AvailableBooks.getPrice()+","+ AvailableBooks.getCostPrice()+","+AvailableBooks.getNumberOfCopies()+",'"+AvailableBooks.getAuthor()+"','"+AvailableBooks.getPublishDate()+"');");
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
//excute methode
	public void ExecuteStatement(String SQL) throws SQLException {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(SQL);
			stmt.close();	 
		}
		catch(SQLException s) {
			s.printStackTrace();
			System.out.println("Failed to excute");		  
		}
	}
  /////////////////////////     
  // delete from database
  ////////////////////
	private void deleteRow(AvailableBooks row) {
		try {
			connectDB();
			ExecuteStatement("delete from Books where BookID="+row.getBookID()+ ";");
			con.close();
                        System.out.println(" Book Deleted");
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
        
        ///////////////
        //  Update////
       ///////////////

	public void TitleUpdate(int id, String name) {	
		try {
			if(Titleexits(name))
				errormessage("Title exist");
			else {
                            
                            // open database then write an update statment
				
				connectDB();
				ExecuteStatement("update books set BookName='"+name+"' where BookID="+id+";");
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void PublishHouseUpdate(int id, String PublishingHouse) {	
		try {
			if(!PHEXISTS(PublishingHouse)) {
			
		}
		else {
				
				connectDB();
				ExecuteStatement("update Books set PublishingHouse='"+PublishingHouse+"' where BookID="+id+";");
				con.close();
			}
	} catch (SQLException e) {
		e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateCategory(int id, String type) {	
		try {
			
			connectDB();
			ExecuteStatement("update Books set Category='"+type+"' where BookID="+id+";");
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	

	public void updateCostp(int id, Double costp) {	
		try {
			
			connectDB();
			ExecuteStatement("update Books set CostPrice='"+costp+"' where BookID="+id+";");
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
        public void updateprice(int id, Double Price) {	
		try {
			
			connectDB();
			ExecuteStatement("update Books set Price='"+Price+"' where BookID="+id+";");
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateCount(int id, int count) {	
		try {
			
			connectDB();
			ExecuteStatement("update Books set NumberOfCopies='"+count+"' where BookID="+id+";");
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
        ///////////////////
        // make an  order
        ///////////////

	@FXML
	void getOrderNum(MouseEvent event) throws ClassNotFoundException, SQLException {
		getCount();
	}

	@FXML
	void AddOrder(ActionEvent event) throws ClassNotFoundException, SQLException {
		if(!ClientOrder.getText().matches("[0-9]+") || !CountOrder.getText().matches("[0-9]+") || 
				!OrderNum.getText().matches("[0-9]+")) {
                    System.out.println("Error while entering data");
			errormessage("Make sure to enter everything");
			ClientOrder.clear();
			CountOrder.clear();
		}
		else {
                  //  System.out.println("k");
			int num = Integer.parseInt(OrderNum.getText());	
			String SQL = "select * from bill where order_num="+num+" ";
			connectDB();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			if(rs.last()) {
				errormessage("Cannot take this order number!");
			}
			else {
                            
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
				LocalDateTime now = LocalDateTime.now();  
				Date date = Date.valueOf(dtf.format(now));	
                                 System.out.println("....");
                                
				ObservableList<AvailableBooks> selectedRows = AvailableBooksTable.getSelectionModel().getSelectedItems();
				ArrayList<AvailableBooks> rows = new ArrayList<>(selectedRows);
                             
				rows.forEach(row -> {
					AvailableBooksTable.getItems();
                                        // System.out.println("k");
					if(Integer.parseInt(CountOrder.getText()) <= row.getNumberOfCopies()) {
                                             System.out.println(".........");
						OrderDetails order = new OrderDetails(
								num,
								row.getBookID(),
								Integer.parseInt(ClientOrder.getText()),
								row.getBookName(),
								Integer.parseInt(CountOrder.getText()),
								row.getCostPrice(),
								date);
						insertOrder(order);
                                             //    System.out.println("k");
						int count = row.getNumberOfCopies() - Integer.parseInt(CountOrder.getText());
                                                // System.out.println("k");
						row.setNumberOfCopies(count);
                                                 System.out.println(".........");
						AvailableBooksTable.refresh();
						updateCount(row.getBookID(), count);
					}
					else
						errormessage("Book Count Not Available!!");
				});
				CountOrder.clear();
			}
		}
	}

	private void insertOrder(OrderDetails order) {
		try {
                     System.out.println(".........");
			connectDB();
			String SQL = "select order_num from orders where order_num="+order.getOrder_num()+";";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			rs.last();
			if(rs.getRow() == 0) {
//				System.out.println("insert into orders values("+order.getOrder_num()+","+order.getCid()+",'"+order.getOrder_date()+"');");
//				System.out.println("Insert into order_line values("+order.getOrder_num()+","+order.getISBN()+
//						",'"+ order.getTitle() +"',"+order.getCount()+","+order.getPrice()+");");
				ExecuteStatement("insert into orders values("+order.getOrder_num()+","+order.getCid()+",'"+order.getOrder_date()+"');");
				ExecuteStatement("Insert into order_line values("+order.getOrder_num()+","+order.getISBN()+
						",'"+ order.getTitle() +"',"+order.getCount()+","+order.getPrice()+");");
			}
			else {
//				System.out.println("Insert into order_line values("+order.getOrder_num()+","+order.getISBN()+
//						",'"+ order.getTitle() +"',"+order.getCount()+","+order.getPrice()+");");
                              
				ExecuteStatement("Insert into order_line values("+order.getOrder_num()+","+order.getISBN()+
						",'"+ order.getTitle() +"',"+order.getCount()+","+order.getPrice()+");");
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void confirmOrder(ActionEvent event) throws ClassNotFoundException, SQLException {
             System.out.println("Confirm");
		//CountOrder.clear();
		//ClientOrder.clear();
		getCount();
	}






	void errormessage(String message) {
		javafx.scene.control.Alert errormessage = new Alert(AlertType.ERROR);
		errormessage.setContentText(message);
		errormessage.setHeaderText(null);
		errormessage.setResizable(false);
		errormessage.initModality(Modality.APPLICATION_MODAL);
		errormessage.show();
	}
}


