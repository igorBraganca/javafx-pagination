package br.com.icb.javafx.scene.control;

import java.io.IOException;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class MyPagination extends VBox {

	@FXML
	private StackPane content;

	@FXML
	private Button bt_first;

	@FXML
	private Button bt_previous_ten;

	@FXML
	private Pagination pagination;

	@FXML
	private Button bt_next_ten;

	@FXML
	private Button bt_last;

	public MyPagination() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/my-pagination.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		bt_previous_ten.disableProperty().bind(pagination.currentPageIndexProperty().lessThan(10));
		bt_next_ten.disableProperty().bind(pagination.currentPageIndexProperty().greaterThan(pagination.getPageCount() - 2 - ((pagination.getPageCount() - 1) % 10)));
		bt_first.disableProperty().bind(pagination.currentPageIndexProperty().isEqualTo(0));
		bt_last.disableProperty().bind(pagination.currentPageIndexProperty().isEqualTo(pagination.getPageCount() - 1));

		bt_previous_ten.setOnAction(e -> {
			int currentPage = pagination.currentPageIndexProperty().getValue();
			int newPage = currentPage - 10;
			newPage = newPage - (currentPage % 10);

			pagination.setCurrentPageIndex(newPage);
		});

		bt_next_ten.setOnAction(e -> {
			int currentPage = pagination.currentPageIndexProperty().getValue();
			int newPage = currentPage + 10;
			newPage = newPage - (currentPage % 10);

			pagination.setCurrentPageIndex(newPage);
		});

		bt_first.setOnAction(e -> {
			pagination.setCurrentPageIndex(0);
		});

		bt_last.setOnAction(e -> {
			pagination.setCurrentPageIndex(pagination.getPageCount());
		});
		
		pagination.currentPageIndexProperty().addListener((arg0, arg1, arg2) -> {
			createPage(content, arg2.intValue());
        });
	}

	private boolean createPage(StackPane pane, int index) {
        if (getPageFactory() != null && pane.getChildren().isEmpty()) {
        	Node content = getPageFactory().call(index);
        	
        	if(content != null) {
        		pane.getChildren().setAll(content);
                return true;
        	} else {
        		pagination.setCurrentPageIndex(0);
        		return false;
        	}
        }
		
		return false;
	}
	
	public final void setMaxPageIndicatorCount(int value) {
		pagination.setMaxPageIndicatorCount(value);
	}

	/**
	 * Returns the maximum number of page indicators.
	 */
	public final int getMaxPageIndicatorCount() {
		return pagination.getMaxPageIndicatorCount();
	}

	/**
	 * The maximum number of page indicators to use for this pagination control.
	 * The maximum number of pages indicators will remain unchanged if the value is less than 1
	 * or greater than the {@link #pageCount}. The number of page indicators will be
	 * reduced to fit the control if the {@code maxPageIndicatorCount} cannot fit.
	 *
	 * The default is 10 page indicators.
	 */
	public final IntegerProperty maxPageIndicatorCountProperty() {
		return pagination.maxPageIndicatorCountProperty();
	}

	/**
	 * Sets the number of pages.
	 *
	 * @param value
	 *            the number of pages
	 */
	public final void setPageCount(int value) {
		pagination.setPageCount(value);
	}

	/**
	 * Returns the number of pages.
	 */
	public final int getPageCount() {
		return pagination.getPageCount();
	}

	/**
	 * The number of pages for this pagination control. This
	 * value must be greater than or equal to 1. {@link #INDETERMINATE}
	 * should be used as the page count if the total number of pages is unknown.
	 *
	 * The default is an {@link #INDETERMINATE} number of pages.
	 */
	public final IntegerProperty pageCountProperty() {
		return pagination.pageCountProperty();
	}

	/**
	 * Sets the current page index.
	 * 
	 * @param value
	 *            the current page index.
	 */
	public final void setCurrentPageIndex(int value) {
		pagination.setCurrentPageIndex(value);
	}

	/**
	 * Returns the current page index.
	 */
	public final int getCurrentPageIndex() {
		return pagination.getCurrentPageIndex();
	}

	/**
	 * The current page index to display for this pagination control. The first page will be
	 * the current page if the value is less than 0. Similarly the last page
	 * will be the current page if the value is greater than the {@link #pageCount}
	 *
	 * The default is 0 for the first page.
	 * <p>
	 * Because the page indicators set the current page index, the currentPageIndex property permits only
	 * bidirectional binding.
	 * The {@link javafx.beans.property.IntegerProperty#bind(javafx.beans.value.ObservableValue) bind} method
	 * throws an UnsupportedOperationException.
	 * </p>
	 */
	public final IntegerProperty currentPageIndexProperty() {
		return pagination.currentPageIndexProperty();
	}

	private ObjectProperty<Callback<Integer, Node>> pageFactory = new SimpleObjectProperty<Callback<Integer, Node>>(this, "pageFactory");

	/**
	 * Sets the page factory callback function.
	 */
	public final void setPageFactory(Callback<Integer, Node> value) {
		pageFactory.set(value);
	}

	/**
	 * Returns the page factory callback function.
	 */
	public final Callback<Integer, Node> getPageFactory() {
		return pageFactory.get();
	}

	/**
	 * The pageFactory callback function that is called when a page has been
	 * selected by the application or the user.
	 *
	 * This function is required for the functionality of the pagination
	 * control. The callback function should load and return the contents the page index.
	 * Null should be returned if the page index does not exist. The currentPageIndex
	 * will not change when null is returned.
	 *
	 * The default is null if there is no page factory set.
	 */
	public final ObjectProperty<Callback<Integer, Node>> pageFactoryProperty() {
		return pageFactory;
	}
}
