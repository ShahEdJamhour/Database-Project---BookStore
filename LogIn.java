/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
import javafx.scene.Scene;
public class LogIn {

    public LogIn() {

    }

    @FXML
    Button button;
    @FXML
    private Label wrongLogIn;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    	@FXML
    private Button sign;
	



    public void userLogIn(ActionEvent event) throws IOException {
        checkLogin();

    }
    
	private Stage stage;
	private Scene scene;
	private Parent root;

 

    private void checkLogin() throws IOException {
        Main m = new Main();
        if(username.getText().toString().equals("shahed") && password.getText().toString().equals("123")) {
            wrongLogIn.setText("Success!");
            
            
     
            
            
            
            
            

            m.changeScene("afterLogin.fxml");
        }

        else if(username.getText().isEmpty() && password.getText().isEmpty()) {
            wrongLogIn.setText("Please enter your data.");
        }


        else {
            wrongLogIn.setText("Wrong username or password!");
        }
    }


}

   