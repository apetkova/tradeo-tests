package com.apetkova.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {

	private static By anchorElementLocator = By.id("header-logo");

	@FindBy(id = "sign-in-button")
	WebElement loginButton;

	@FindBy(id = "user_login")
	WebElement usernameInput;

	@FindBy(id = "user_password")
	WebElement passwordInput;

	@FindBy(name = "commit")
	WebElement submitButton;

	public void login(String username, String password) {
		while (!usernameInput.isDisplayed()) {
			loginButton.click();
		}
		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);
		submitButton.click();
	}

	@Override
	public By getAnchorElementLocation() {
		return anchorElementLocator;
	}

}
