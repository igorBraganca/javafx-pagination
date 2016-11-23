package br.com.icb.javafx.pagination.skin;

import javafx.scene.control.Pagination;
import javafx.scene.control.SkinBase;

public class TesteSkin extends SkinBase<Pagination>{

	Pagination pagination;
	
	protected TesteSkin(final Pagination control) {
		super(control);
		
		this.pagination = control;
	}
}
