package br.com.icb.javafx.pagination;

import javafx.stage.Stage;

public class SkynPagination extends SimpleTablePagination {

	@Override
	public void start(Stage primaryStage) throws Exception {
		super.start(primaryStage);
		
		primaryStage.getScene().getStylesheets().add("/css/javafx.css");
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
