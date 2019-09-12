package com.template;

import java.math.BigDecimal;

public class Product {

	private String[] products = { "Package of 3 ", "Package of 10", "Package of 20" };
	private BigDecimal[] prices = { BigDecimal.valueOf(210.00), BigDecimal.valueOf(600.00),
			BigDecimal.valueOf(1100.00) };
	private int option;

	Product(int option) { // chose one of three products
		this.option = option;
	}

	public BigDecimal getPrice() {// chose 1 through 3
		BigDecimal bd = prices[--option];
		return bd;
	}

	public String getProduct() {// chose 1 through 3
		return products[--option];
	}

}
