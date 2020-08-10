package com.vendingmachine;

public enum CoinDetails {	

	CENT(2), NICKLE(5), DIME(15), QUARTER(20);
	
	private int count;

	private CoinDetails(int count) {
		this.count = count;
	}
	
	public int getCount(){
        return count;
    }



}
