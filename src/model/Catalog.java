package model;

import java.util.Map;

public class Catalog {

	private Map<String, Product> productMap;
	
	public Catalog() {
		
	}

	public Map<String, Product> getProductMap() {
		return productMap;
	}

	public void setProductMap(Map<String, Product> productMap) {
		this.productMap = productMap;
	}

	@Override
	public String toString() {
		return "Catalog [productMap=" + productMap + "]";
	}
	
}
