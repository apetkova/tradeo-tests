package com.apetkova.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.apetkova.webdriver.Driver;

public class CopyTraderPanel extends AbstractPage {

	private static By anchorElementLocator = By.cssSelector("div#copying-popup");

	@FindBy(css = "div.header>h3")
	WebElement title;

	@FindBy(css = "a.copy-help-toggle")
	WebElement helpIcon;

	@FindBy(css = "a.copy-close")
	WebElement closeIcon;

	@FindBy(css = "div.avatar-frame-big>img")
	WebElement avatarImage;


	@FindBy(css = "div.account-details>h4")
	WebElement traderName;

	@FindBy(css = "span#copying-flag")
	WebElement flagIcon;

	@FindBy(css = "div#copying-account-ribbon")
	WebElement accountTypeIndicator;

	@FindBy(css = "div.carousel-container>div.account-name>span.value")
	WebElement acountNameValue;

	@FindBy(css = "div.carousel-container>div.most-traded-pair>span.value")
	WebElement mostTradedPairValue;

	@FindBy(css = "div.carousel-container>div.gain>span.value")
	WebElement gainValue;

	@FindBy(css = "div.carousel-container>div.copiers-count>span.value")
	WebElement copiersCountValue;

	@FindBy(css = "div.carousel-container>div.win-ratio>span.value")
	WebElement winRatioValue;

	@FindBy(css = "div.carousel-container>div.pips-won>span.value")
	WebElement PipsWonValue;

	@FindBy(css = "div.carousel-container>div.max_drawdown>span.value")
	WebElement maxDrawdownValue;

	@FindBy(xpath = "//div[@data-tab='standard']")
	WebElement standardTab;

	@FindBy(xpath = "//div[@data-tab='dynamic']")
	WebElement dynamicTab;

	WebElement lotsInput;

	WebElement preferredInput;

	WebElement maximumInput;

	@FindBy(css = "div.not-allowed-copying-text")
	WebElement crossTypeCopyingErrorMessage;

	@FindBy(css = "input.copying-button.stop")
	WebElement stopCopyingButton;

	@FindBy(css = "input.copying-button.save")
	WebElement saveCopyingButton;

	private By activeCarouselButtonLocator = By.cssSelector("div.carousel-button.active");
	private By inactiveCarouselButtonLocator = By.cssSelector("div.carousel-button:not(.active)");
	private By helpPanelLocator = By.cssSelector("div.copying-popup-help");
	private By lotsInputLocator = By.cssSelector("div.settings-form.standard input.position-size");
	private By preferredInputLocator = By.cssSelector("div.settings-form.dynamic input.position-size");
	private By maximumInputLocator = By.cssSelector("input.max-position-size");
	private By confirmStopCopyingButtonLocator = By.cssSelector("button.pretty-confirm-confirm");

	public static By getAnchorElementLocator() {
		return anchorElementLocator;
	}

	public WebElement getTitle() {
		return title;
	}

	public WebElement getHelpIcon() {
		return helpIcon;
	}

	public WebElement getCloseIcon() {
		return closeIcon;
	}

	public WebElement getAvatarImage() {
		return avatarImage;
	}

	public WebElement getTraderName() {
		return traderName;
	}

	public WebElement getFlagIcon() {
		return flagIcon;
	}

	public WebElement getAccountTypeIndicator() {
		return accountTypeIndicator;
	}

	public WebElement getAcountNameValue() {
		return acountNameValue;
	}

	public WebElement getMostTradedPairValue() {
		return mostTradedPairValue;
	}

	public WebElement getGainValue() {
		return gainValue;
	}

	public WebElement getCopiersCountValue() {
		return copiersCountValue;
	}

	public WebElement getWinRatioValue() {
		return winRatioValue;
	}

	public WebElement getPipsWonValue() {
		return PipsWonValue;
	}

	public WebElement getMaxDrawdownValue() {
		return maxDrawdownValue;
	}

	public WebElement getStandardTab() {
		return standardTab;
	}

	public WebElement getDynamicTab() {
		return dynamicTab;
	}

	public WebElement getLotsInput() {
		return lotsInput;
	}

	public WebElement getPreferredInput() {
		return preferredInput;
	}

	public WebElement getMaximumInput() {
		return maximumInput;
	}

	public By getLotsInputLocator() {
		return lotsInputLocator;
	}

	public By getPreferredInputLocator() {
		return preferredInputLocator;
	}

	public By getMaximumInputLocator() {
		return maximumInputLocator;
	}

	public By getActiveCarouselButtonLocator() {
		return activeCarouselButtonLocator;
	}

	public By getInactiveCarouselButtonLocator() {
		return inactiveCarouselButtonLocator;
	}

	public By getHelpPanelLocator() {
		return helpPanelLocator;
	}

	public WebElement getCrossTypeCopyingErrorMessage() {
		return crossTypeCopyingErrorMessage;
	}

	public WebElement getStopCopyingButton() {
		return stopCopyingButton;
	}

	public WebElement getSaveCopyingButton() {
		return saveCopyingButton;
	}

	public static CopyTraderPanel getOpenPanel() {
		try {
			Driver.getDriver().findElement(anchorElementLocator);
			return new CopyTraderPanel();
		} catch (NoSuchElementException nsee) {
			return null;
		}
	}

	public void selectStandardTab() {
		standardTab.click();
		lotsInput = searchContext.findElement(lotsInputLocator);
	}

	public void selectDynamicTab() {
		dynamicTab.click();
		preferredInput = searchContext.findElement(preferredInputLocator);
		maximumInput = searchContext.findElement(maximumInputLocator);
	}

	public void confirm() {
		try {
			Driver.getNewWait(2).until(ExpectedConditions.visibilityOfElementLocated(confirmStopCopyingButtonLocator));
		} catch (TimeoutException te) {
			// do nothing
		}
		searchContext.findElement(confirmStopCopyingButtonLocator).click();
	}

	public void close() {
		closeIcon.click();
	}

	@Override
	public By getAnchorElementLocation() {
		return anchorElementLocator;
	}
}
