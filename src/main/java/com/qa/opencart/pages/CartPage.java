package com.qa.opencart.pages;

import org.openqa.selenium.By;

public class CartPage {

	private By carBtn = By.id("cart");
	
	public void getCart() {
		
	
		System.out.println("get cart"+carBtn);
	}
}
