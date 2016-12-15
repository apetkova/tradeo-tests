package com.apetkova.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.apetkova.webdriver.Driver;

public abstract class AbstractPage extends PageFactory {

	abstract public By getAnchorElementLocation();

	protected WebElement searchContext;

	public AbstractPage() {
		Driver.getNewWait(15).until(ExpectedConditions.presenceOfElementLocated(getAnchorElementLocation()));
		PageFactory.initElements(Driver.getDriver(), this);
		searchContext = Driver.getDriver().findElement(By.tagName("body"));
	}
}
