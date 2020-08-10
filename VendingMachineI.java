package com.vendingmachine;

import java.util.List;


public interface VendingMachineI {
	public long selectProductDetailsAndGetPrice(Products itemDetails);
	public void putCoin(CoinDetails coin);
	public ContainerList<Products, List<CoinDetails>> collectProductAndChange();

}
