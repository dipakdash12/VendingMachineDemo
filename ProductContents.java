package com.vendingmachine;

import java.util.HashMap;
import java.util.Map;

public class ProductContents<T> {

	private Map<T, Integer> productContents = new HashMap<T, Integer>();

	public int getQuantity(T product) {

		Integer value = productContents.get(product);
		return value == null ? 0 : value;

	}

	public void add(T item) {
		int count = productContents.get(item);
		productContents.put(item, count + 1);
	}

	public void deduct(T product) {

		if (hasProduct(product)) {
			int count = productContents.get(product);
			productContents.put(product, count - 1);
		}
	}

	public boolean hasProduct(T product) {
		return getQuantity(product) > 0;
	}

	public void put(T item, int quantity) {
		productContents.put(item, quantity);
	}

}
