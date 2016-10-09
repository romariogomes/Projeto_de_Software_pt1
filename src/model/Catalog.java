package model;

import java.util.Map;

public class Catalog {

	private Map<Integer, Product> productMap;
	
	public Catalog() {
		
	}

	public Map<Integer, Product> getProductMap() {
		return productMap;
	}

	public void setProductMap(Map<Integer, Product> productMap) {
		this.productMap = productMap;
	}

	@Override
	public String toString() {
		return "Catalog [productMap=" + productMap + "]";
	}
	
	boolean addProduct(Product p){
		
		if ((productMap.put(p.getId(), p)).equals(null)) {
			
			return false;
			
		} else {
			
			return true;
		}
		
	}
	
	boolean removeProduct(Product p){
		
		if ((productMap.remove(p.getId())).equals(null)) {
			
			return false;
			
		} else {
			
			return true;
		}
		
	}
	
	Product searchProductById(Integer id){
		
		if (productMap.containsKey(id)){
			
			return productMap.get(id);
			
		} else
			
		return null; 
		
	}
	
	Product searchProductByName(String name){
		
		if (productMap.containsValue(name)){
			
			return productMap.get(name);
			
		} else
			
		return null;
		
	}
	
}
