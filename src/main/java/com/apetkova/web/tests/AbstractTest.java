package com.apetkova.web.tests;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.apetkova.web.pages.CopyTraderPanel;
import com.apetkova.web.pages.LoginPage;
import com.apetkova.web.pages.TraderPage;
import com.apetkova.webdriver.Driver;

public class AbstractTest {

	protected String copiedTraderName = "Vinicius Fadul";
	protected String copiedTraderPageName = "viniciusfadul";

	protected final String mainPage = "https://tradeo.com";
	protected final String traderToCopyPage = "https://tradeo.com/traders/viniciusfadul";
	protected final String userProfilePage = "https://tradeo.com/my-account";

	protected final By logoutLocator = By.cssSelector("a.sign-out,sign-out-button");

	protected CopyTraderPanel panel;

	@BeforeSuite
	public void startDriver() {
		Driver.startDriver("chrome");
	}

	@BeforeClass
	public void login() {
		getPage(mainPage);
		LoginPage loginPage = new LoginPage();
		loginPage.login("aneta.v.petkova@gmail.com", "tradeoPass2016");
	}

	@BeforeMethod
	public void navigateToCopyPanel() {
		navigateToCopyPanel("Tradeo demo");
	}

	@AfterMethod(alwaysRun = true)
	public void closePanel() {
		CopyTraderPanel panel = CopyTraderPanel.getOpenPanel();
		if (panel != null) {
			panel.close();
		}
	}

	@AfterClass
	public void logout() {
		Driver.getDriver().findElement(logoutLocator).click();
	}

	@AfterSuite(alwaysRun = true)
	public void closeBrowser() {
		Driver.stopDriver();
	}

	protected void getPage(String pageAddress) {
		Driver.loadUrl(pageAddress);
	}

	protected void navigateToCopyPanel(String accountName) {
		Driver.loadUrl(traderToCopyPage);
		TraderPage page = new TraderPage();
		page.openCopyTraderPanel(accountName);
		this.panel = new CopyTraderPanel();
	}
}
