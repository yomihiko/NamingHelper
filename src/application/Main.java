package application;

import static application.Define.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Main extends Application {
	public static Stage stg;
	@Override
	public void start(Stage primaryStage) {
		try {
			GridPane root = (GridPane)FXMLLoader.load(getClass().getResource(FILEPATH));
			Scene scene = new Scene(root,WIDTH,HEIGHT);
			scene.getStylesheets().add(getClass().getResource(CSS).toExternalForm());
			stg = primaryStage;
			stg.setScene(scene);
			stg.setResizable(false);
			stg.setTitle(TITLE);
			stg.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 *画面を閉じる
	 */
	public static void close() {
		stg.close();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
