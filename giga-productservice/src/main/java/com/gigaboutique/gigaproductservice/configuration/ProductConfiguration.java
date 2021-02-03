package com.gigaboutique.gigaproductservice.configuration;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProductConfiguration {
	
	@Value("#{${product-configs.genres}}")
	private List<String> genres;
	
	@Value("#{${product-configs.categories}}")
	private Map<String, List<String>> categories;

	public List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	public Map<String, List<String>> getCategories() {
		return categories;
	}

	public void setCategories(Map<String, List<String>> categories) {
		this.categories = categories;
	}

}
