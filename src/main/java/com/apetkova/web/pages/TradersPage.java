package com.apetkova.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.apetkova.webdriver.Driver;

public class TradersPage extends AbstractPage {

	private static By anchorElementLocator = By.cssSelector("div#traders-page");

	@FindBy(css = "input#name")
	WebElement traderSearchInput;

	@FindBy(css = "input.search-icon")
	WebElement searchIcon;

	@FindBy(css = "div#traders-count")
	WebElement searchResultSummary;

	@FindBy(css = "div.show.show-hide-accounts-button")
	WebElement accountsButton;

	private final static By popupLocator = By.cssSelector("div#jpwTooltip");
	private final static By popupCloseButtonLocator = By.cssSelector("div#jpwTooltip button.jpw-close");

	public TradersPage() {
		super();
		dismissPopup();
	}

	public void searchForTradersByName(String traderName) {
		traderSearchInput.sendKeys(traderName);
		traderSearchInput.sendKeys(Keys.ENTER);
		Driver.getNewWait(10).until(ExpectedConditions.visibilityOf(searchResultSummary));
	}

	private void dismissPopup() {
		try {
			Driver.getNewWait(2).until(ExpectedConditions.visibilityOfElementLocated(popupLocator));
		} catch (TimeoutException te) {
			return;
		}
		Driver.getDriver().findElement(popupCloseButtonLocator).click();
	}

	@Override
	public By getAnchorElementLocation() {
		return anchorElementLocator;
	}

	public void expandAccountsSection() {

	}
}
