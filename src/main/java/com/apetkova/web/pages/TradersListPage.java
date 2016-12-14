package com.apetkova.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.apetkova.webdriver.Driver;

public class TradersListPage extends TradersPage {
	
	public enum AccountType {
		REAL("Real"),
		DEMO("Demo");
		private AccountType(String label){};
	}

	private static By anchorElementLocator = By.cssSelector("div#traders");

	private String traderPanelLocator = "//div[@class='trader-name' and text()='%s']/ancestor::div[@class='trader-wrapper']";

	private By showAccountsButtonLocator = By.cssSelector("div.show-hide-accounts-button.show");

	private String accountPanelXpath = "//span[@class='account-type']/ancestor::div[@class='account-wrapper']";

	private By copyTraderButtonLocator = By.cssSelector("button.copy-ribbon");

	@FindBy(css = "div.show.show-hide-accounts-button")
	WebElement accountsButton;

	public WebElement getTraderPanelByName(String traderName) {
		return searchContext.findElement(By.xpath(String.format(traderPanelLocator, traderName)));
	}

	public void expandTraderAccountsByName(String traderName) throws InterruptedException {
		WebElement panel = getTraderPanelByName(traderName);
		WebElement accountsButton = panel.findElement(showAccountsButtonLocator);
		Actions build = new Actions(Driver.getDriver());
		int xOffset = new Double(accountsButton.getSize().width * 0.5).intValue();
		int yOffset = new Double(accountsButton.getSize().height * 0.8).intValue();
		Thread.sleep(1000);
		build.moveToElement(accountsButton, xOffset, yOffset).click().build().perform();
//		accountsButton.click();
	}

	public WebElement getAccountPanel(AccountType type) {
		return searchContext.findElement(By.xpath(String.format(accountPanelXpath, type)));
	}

	public void openCopyTraderPanel(AccountType type) {
		Driver.getNewWait(10).until(ExpectedConditions.visibilityOfElementLocated(copyTraderButtonLocator));
		getAccountPanel(type).findElement(copyTraderButtonLocator).click();
	}
	
	public void openCopyTraderPanel(String traderName, AccountType type) {
		try {
			expandTraderAccountsByName(traderName);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		openCopyTraderPanel(type);
	}

	@Override
	public By getAnchorElementLocation() {
		return anchorElementLocator;
	}
}
