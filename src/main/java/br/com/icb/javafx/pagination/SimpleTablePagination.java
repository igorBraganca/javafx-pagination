package br.com.icb.javafx.pagination;

import java.time.LocalDate;

import br.com.icb.javafx.cell.factory.SimplePropertyValueFactory;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SimpleTablePagination extends Application {

	protected Pagination pagination;

	private TableView<Pessoa> tabela;

	private TableColumn<Pessoa, String> nome;
	private TableColumn<Pessoa, String> genero;
	private TableColumn<Pessoa, LocalDate> dataNascimento;
	private TableColumn<Pessoa, Integer> idade;

	private ObservableList<Pessoa> itensTabela = FXCollections.observableArrayList();

	private Node changePage(Integer pageIndex) {
		itensTabela.clear();

		for (int i = pageIndex * 10; i < (pageIndex * 10) + 10; i++) {
			itensTabela.add(PessoaBuilder.build(i));
		}

		return new Pane(tabela);
	}

	@Override
	public void init() throws Exception {
		pagination = new Pagination(29);
		pagination.setPageFactory((pageIndex) -> changePage(pageIndex));

		tabela = new TableView<>();

		nome = new TableColumn<>("Nome");
		genero = new TableColumn<>("Gênero");
		dataNascimento = new TableColumn<>("Data de Nascimento");
		idade = new TableColumn<>("Idade");

		tabela.getColumns().add(nome);
		tabela.getColumns().add(genero);
		tabela.getColumns().add(dataNascimento);
		tabela.getColumns().add(idade);

		nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		genero.setCellValueFactory(new SimplePropertyValueFactory<>("genero"));
		dataNascimento.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
		idade.setCellValueFactory(new SimplePropertyValueFactory<>("idade"));

		tabela.setItems(itensTabela);
	}

	@Override
	public void start(final Stage primaryStage) throws Exception {

		AnchorPane pane = new AnchorPane();

		pane.getChildren().add(pagination);

		Scene scene = new Scene(pane);
		
		primaryStage.setScene(scene);

		primaryStage.setWidth(1024);
		primaryStage.setHeight(768);

		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
