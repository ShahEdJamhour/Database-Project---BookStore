package application;

import java.io.IOException;


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	private static Stage stg;

	@Override
	public void start(Stage primaryStage) {
		try {
                    System.out.println("Primary Stage initialized");
                    
                      System.out.println("Loading");
                         System.out.println("Stage loaded");
			stg = primaryStage;
			primaryStage.setResizable(false);
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("afterLogin.fxml"));
                         primaryStage.setTitle("Choose one!");
			Scene scene = new Scene(root, 550, 630);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void changeScene(String fxml) throws IOException {
		Parent pane = FXMLLoader.load(getClass().getResource(fxml));
		stg.getScene().setRoot(pane);
	}
}
