package com.vendingmachine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.vendingmachine.ContainerList;

public class VendingMachineImpl implements VendingMachineI {

	private ProductContents<CoinDetails> cashInventory = new ProductContents<CoinDetails>();
	private ProductContents<Products> productInventory = new ProductContents<Products>();
	private long totalSales;
	private Products currentProduct;
	private long currentBalance;

	public VendingMachineImpl() {
		initialize();
	}

	private void initialize() {
		for (CoinDetails c : CoinDetails.values()) {
			cashInventory.put(c, 5);
		}

		for (Products i : Products.values()) {
			productInventory.put(i, 5);
		}
	}

	public long selectProductDetailsAndGetPrice(Products product) {
		if (productInventory.hasProduct(product)) {
			currentProduct = product;
			return currentProduct.getPrice();
		}

		throw new ProductsSoldOutException("Products are sold out, buy another product");

	}

	public void putCoin(CoinDetails coin) {
		currentBalance = currentBalance + coin.getCount();
		cashInventory.add(coin);
	}

	public ContainerList<Products, List<CoinDetails>> collectProductAndChange() {
		Products product = collectProduct();
		totalSales = totalSales + currentProduct.getPrice();
		List<CoinDetails> change = collectChange();

		return new ContainerList<Products, List<CoinDetails>>(product, change);
	}

	public Products collectProduct() throws NotSufficientChangeException, NotFullPaidException {
		if (isFullPaid()) {
			if (hasSufficientChange()) {
				productInventory.deduct(currentProduct);
				return currentProduct;
			}

			throw new NotSufficientChangeException("Not Sufficient change in Inventory");
		}
		long remainingBalance = currentProduct.getPrice() - currentBalance;
		throw new NotFullPaidException("Price not full paid, remaining : ", remainingBalance);
	}

	private List<CoinDetails> collectChange() {
		long changeAmount = currentBalance - currentProduct.getPrice();
		List<CoinDetails> change = getChange(changeAmount);
		updateCashInventory(change);
		currentBalance = 0;
		currentProduct = null;
		return change;
	}

	private boolean isFullPaid() {

		if (currentBalance >= currentProduct.getPrice()) {
			return true;
		}
		return false;
	}

	public List<CoinDetails> getChange(long amount) throws NotSufficientChangeException {

		List<CoinDetails> changes = Collections.emptyList();

		if (amount > 0) {
			changes = new ArrayList<CoinDetails>();
			long balance = amount;

			while (balance > 0) {
				if (balance >= CoinDetails.QUARTER.getCount() && cashInventory.hasProduct(CoinDetails.QUARTER)) {
					changes.add(CoinDetails.QUARTER);
					balance = balance - CoinDetails.QUARTER.getCount();
					continue;
				} else if (balance >= CoinDetails.DIME.getCount() && cashInventory.hasProduct(CoinDetails.DIME)) {

					changes.add(CoinDetails.DIME);
					balance = balance - CoinDetails.DIME.getCount();
					continue;
				} else if (balance >= CoinDetails.NICKLE.getCount() && cashInventory.hasProduct(CoinDetails.NICKLE)) {

					changes.add(CoinDetails.NICKLE);
					balance = balance - CoinDetails.NICKLE.getCount();
					continue;
				} else if (balance >= CoinDetails.CENT.getCount() && cashInventory.hasProduct(CoinDetails.CENT)) {
					changes.add(CoinDetails.CENT);
					balance = balance - CoinDetails.CENT.getCount();
					continue;
				} else {
					throw new NotSufficientChangeException("You do not have sufficient change try another product");
				}

			}

		}
		return changes;
	}

	private boolean hasSufficientChange() {
		return hasSufficientChangeForAmount(currentBalance - currentProduct.getPrice());
	}

	private boolean hasSufficientChangeForAmount(long amount) {
		boolean hasChange = true;
		try {
			getChange(amount);
		} catch (NotSufficientChangeException nsce) {
			return hasChange = false;
		}
		return hasChange;
	}

	private void updateCashInventory(List<CoinDetails> change) {
		for (Object c : change) {
			cashInventory.deduct((CoinDetails) c);
		}
	}

	public long getTotalSales() {
		return totalSales;
	}

}
