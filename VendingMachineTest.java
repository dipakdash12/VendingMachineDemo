package com.vendingmachine;

public class VendingMachineTest {

	public static void main(String[] args) {
		VendingMachineImpl impl = new VendingMachineImpl();
		System.out.println("Get Change amount: "+impl.getChange(20));

	}

}
