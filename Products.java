package com.vendingmachine;

public enum Products {

	CHOCOLATES("Chocolates", 25), CANDY("Candy", 35), COLDDRINKS("ColdDrinks", 45);
	
	private String productName;
    private int price;
    
	private Products(String productName, int price) {
		this.productName = productName;
		this.price = price;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
