package com.apetkova.web.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.apetkova.web.pages.AccountPage;
import com.apetkova.web.pages.CopyTraderPanel;
import com.apetkova.web.pages.TraderPage;
import com.apetkova.webdriver.Driver;

public class SameTypeTests extends AbstractTest {
	private CopyTraderPanel panel;

	@Override
	@BeforeMethod
	public void navigateToCopyPanel() {
		Driver.loadUrl(traderToCopyPage);
		TraderPage page = new TraderPage();
		page.openCopyTraderPanel("Tradeo real 2");
		this.panel = new CopyTraderPanel();
	}

	@Test
	public void copyStandardTest() {
		panel.selectStandardTab();
		panel.getLotsInput().sendKeys("5");
		panel.getSaveCopyingButton().click();
		//verify
		getPage(userProfilePage);
		AccountPage accountPage = new AccountPage();
		Assert.assertFalse(accountPage.isTraderCopied(copiedTraderName));
		CopyTraderPanel.getOpenPanel();
		// Driver.loadUrl();
	}

	@Test
	public void copyDynamicTest() {
		panel.selectDynamicTab();
		panel.getPreferredInput().sendKeys("5");
		panel.getMaximumInput().sendKeys("10");

	}

}
