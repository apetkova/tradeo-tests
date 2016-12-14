package com.apetkova.web.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AccountPage extends TradersPage {
	
	private static By anchorElementLocator = By.cssSelector("div#accounts-page");

	private By copiedTradersLocator = By.cssSelector("div.copying-wrapper:not(.state-disabled) a span");

	private String copyPanelXpath = "//a[@href='/traders/%s']/../div[@class='copied-trades']";

	public void openCopyPanel(String traderName){
		searchContext.findElement(By.xpath(
				String.format(copyPanelXpath, traderName))).click();
	}
	
	public List<String> getAllCopiedTraders() {
		List<WebElement> tradersSpans = searchContext.findElements(copiedTradersLocator);
		List<String> tradersNames = new ArrayList<String>();
		for (WebElement span : tradersSpans) {
			tradersNames.add(span.getText());
		}
		return tradersNames;
	}

	public boolean isTraderCopied(String traderName) {
		return getAllCopiedTraders().contains(traderName);
	}

	@Override
	public By getAnchorElementLocation() {
		return anchorElementLocator;
	}
}
