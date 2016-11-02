package br.com.icb.javafx.pagination.skin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sun.javafx.scene.control.skin.PaginationSkin;

import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.layout.HBox;

@SuppressWarnings("restriction")
public class CustomPaginationSkin extends PaginationSkin {

	private Logger logger = LogManager.getFormatterLogger(CustomPaginationSkin.class);
	
	private HBox controlBox;
	private Button prev;
	private Button next;
	private Button prevTen;
	private Button nextTen;
	private Button first;
	private Button last;

	private void patchNavigation() {
		logger.trace("Iniciando patch");
		
		Pagination pagination = getSkinnable();
		Node control = pagination.lookup(".control-box");
		
		if (!(control instanceof HBox)) {
			logger.trace("controlBox nao encontrado");
			return;
		}
		
		controlBox = (HBox) control;
		logger.trace("ControlBox capturado");
		
		prev = (Button) controlBox.getChildren().get(0);
		next = (Button) controlBox.getChildren().get(controlBox.getChildren().size() - 1);

		logger.trace(prev.getStyleClass());
		logger.trace(next.getStyleClass());
		
		prevTen = new Button("<<");
		prevTen.setOnAction(e -> {
			int currentPage = pagination.currentPageIndexProperty().getValue();
			int newPage = currentPage - 10;
			newPage = newPage - (currentPage % 10);
			
			pagination.setCurrentPageIndex(newPage);
		});
		prevTen.disableProperty().bind(pagination.currentPageIndexProperty().lessThan(10));
		prevTen.getStyleClass().addAll("previous-ten-button");
		
		nextTen = new Button(">>");
		nextTen.setOnAction(e -> {
			int currentPage = pagination.currentPageIndexProperty().getValue();
			int newPage = currentPage + 10;
			newPage = newPage - (currentPage % 10);
			
			pagination.setCurrentPageIndex(newPage);
		});
		
		logger.trace(pagination.getCurrentPageIndex());
		logger.trace(pagination.getPageCount());
		logger.trace(pagination.getPageCount() - 1);
		logger.trace((pagination.getPageCount() - 1) % 10);
		logger.trace(pagination.getPageCount() - ((pagination.getPageCount() - 1) % 10));
		logger.trace(pagination.getPageCount() - ((pagination.getPageCount() - 1) % 10) - 1);
		
		nextTen.disableProperty().bind(pagination.currentPageIndexProperty().greaterThan(pagination.getPageCount() - 2 - ((pagination.getPageCount() - 1) % 10)));
		nextTen.getStyleClass().addAll("next-ten-button");
		
		first = new Button("first");
		first.setOnAction(e -> {
			pagination.setCurrentPageIndex(0);
		});
		first.disableProperty().bind(pagination.currentPageIndexProperty().isEqualTo(0));
		first.getStyleClass().addAll("first-button");

		last = new Button("last");
		last.setOnAction(e -> {
			pagination.setCurrentPageIndex(pagination.getPageCount());
		});
		last.disableProperty().bind(pagination.currentPageIndexProperty().isEqualTo(pagination.getPageCount() - 1));
		first.getStyleClass().addAll("last-button");

		ListChangeListener<? super Node> childrenListener = c -> {
			while (c.next()) {
				// implementation detail: when nextButton is added, the setup is complete
				if (c.wasAdded() && !c.wasRemoved() // real addition
						&& c.getAddedSize() == 1 // single addition
						&& c.getAddedSubList().get(0) == next) {
					addCustomNodes();
				}
			}
		};
		
		controlBox.getChildren().addListener(childrenListener);
		addCustomNodes();
	}

	protected void addCustomNodes() {
		// guarding against duplicate child exception
		// (some weird internals that I don't fully understand...)
		if (first.getParent() == controlBox)
			return;
		controlBox.getChildren().add(0, first);
		controlBox.getChildren().add(1, prevTen);
		controlBox.getChildren().add(nextTen);
		controlBox.getChildren().add(last);
	}

	/**
	 * @param pagination
	 */
	public CustomPaginationSkin(Pagination pagination) {
		super(pagination);
		patchNavigation();
	}

}