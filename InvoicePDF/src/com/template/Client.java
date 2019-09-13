package com.template;

public class Client {
	private String name;
	private boolean isEligibleDiscount;

	public Client(String name, boolean isEligibleDiscount) {
		this.name = name;
		this.isEligibleDiscount = isEligibleDiscount;
	}

	public String getName() {
		return name;
	}

	public boolean isEligibleDiscount() {
		return isEligibleDiscount;
	}

}
