package br.com.icb.pagination.api;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class TablePaginationController<T> implements Controller<T> {
	private Pane pane;

	private Pagination pagination = new Pagination();
	
	private PaginationData<T> paginationData;
	private TableView<T> table;
	private ObservableList<T> tableData = FXCollections.observableArrayList();
	
	public TablePaginationController(TableView<T> table, PaginationData<T> paginationData) {
		if(table == null || paginationData == null) {
			throw new IllegalArgumentException("table.and.pagination.data.are.mandatory");
		}

		this.table = table;
		this.paginationData = paginationData;
		
		constructFrame();
		configurePagination();
	}
	
	private void constructFrame() {
		pane = new Pane();
		
		VBox vBox = new VBox();
		HBox hBox = new HBox();
		
		hBox.getChildren().addAll(pagination);
		vBox.getChildren().addAll(table, hBox);
		pane.getChildren().addAll(vBox);
	}
	
	private void configurePagination() {
		pagination.setPageFactory((param) -> tableFactory(param));
	}
	
	private Node tableFactory(Integer param) {
		Pane pane = new Pane(table);
		
		tableData.clear();
		tableData.addAll(paginationData.getPageByIndex(param));
		
		return pane;
	}
	
	@Override
	public void setCurrentPage(int value) {
		pagination.setCurrentPageIndex(value);
	}

	@Override
	public void setPaginationData(PaginationData<T> paginationData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Pane getContent() {
		return pane;
	}
}
