package br.com.icb.pagination.api;

import javafx.scene.layout.Pane;

public interface Controller<T> {
	public Pane getContent();
	
	public void setCurrentPage(int index);

	public void setPaginationData(PaginationData<T> paginationData);
}
