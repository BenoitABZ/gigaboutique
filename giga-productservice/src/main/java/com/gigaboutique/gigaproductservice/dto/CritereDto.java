package com.gigaboutique.gigaproductservice.dto;

import java.util.List;

public class CritereDto {
	
	private List<String> marques;
	
	private String genre;
	
	private List<String> categories;

    public int size;
    
    public int page;

	public List<String> getMarques() {
		return marques;
	}

	public void setMarques(List<String> marques) {
		this.marques = marques;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

}
