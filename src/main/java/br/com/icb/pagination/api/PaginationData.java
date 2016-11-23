package br.com.icb.pagination.api;

import java.util.List;

public interface PaginationData<T> {
	
	public void setPageItemAmount(int value);
	
	public int getPageAmount();
	public List<T> getPageByIndex(int value);
}
