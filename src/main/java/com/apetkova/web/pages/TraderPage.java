package com.apetkova.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.apetkova.webdriver.Driver;

public class TraderPage extends TradersPage {
	
	private static By anchorElementLocator = By.cssSelector("div.accounts-and-settings-page");

	private String accountPanelXpath = "//span[@class='name' and text()='%s']/ancestor::div[@class='account-wrapper']";

	private By copyTraderButtonLocator = By.cssSelector("button.copy-ribbon");

	public WebElement getAccountPanel(String accountName) {
		return searchContext.findElement(By.xpath(String.format(accountPanelXpath, accountName)));
	}

	public void openCopyTraderPanel(String accountName) {
		Driver.getNewWait(10).until(ExpectedConditions.visibilityOfElementLocated(copyTraderButtonLocator));
		getAccountPanel(accountName).findElement(copyTraderButtonLocator).click();
	}

	@Override
	public By getAnchorElementLocation() {
		return anchorElementLocator;
	}
}
