package com.trab01JFX.application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	public Stage stage;
	private static Stage primaryStage; 
	private static Class<? extends Main> thisClass;
	
	public Main(){
		thisClass = getClass();
	}
	
	@Override
	public void start(Stage primaryStage){
		Main.primaryStage = primaryStage;
		
		try{
			Parent root = FXMLLoader.load(getClass().getResource("/com/trab01JFX/view/Login.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Tela de Login");
			primaryStage.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * 
	 * @param nameFile
	 * @param titlePage
	 */
	public static void loadScene(String nameFile, String titlePage){
		Parent root;
		try{
			root = FXMLLoader.load(thisClass.getClass().getResource(nameFile));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle(titlePage);
			primaryStage.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
