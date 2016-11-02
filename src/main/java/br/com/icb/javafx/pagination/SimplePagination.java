package br.com.icb.javafx.pagination;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Pagination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SimplePagination extends Application {

	private Pagination pagination;

	private Node changePage(Integer pageIndex) {
		VBox box = new VBox(5);
		
		for (int i = 0; i < 10; i++) {
			Hyperlink link = new Hyperlink(pageIndex.toString() + " - " + i);
			box.getChildren().add(link);
		}
		
		return box;
	}

	@Override
	public void start(final Stage primaryStage) throws Exception {
		pagination = new Pagination(10);
		pagination.setPageFactory((pageIndex) -> changePage(pageIndex));

		AnchorPane pane = new AnchorPane();
		pane.getChildren().add(pagination);

		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		System.out.println("Iniciando aplicação");
		launch(args);
		System.out.println("apos launch");
	}
}
