package it.polito.tdp.poweroutages;
	
import it.polito.tdp.poweroutages.model.Model;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("PowerOutages.fxml"));
			BorderPane root = (BorderPane) loader.load();
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
			PowerOutagesController controller = loader.getController();
			Model model = new Model();
			controller.setModel(model);
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.append("NON va ");
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
