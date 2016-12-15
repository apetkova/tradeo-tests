package com.apetkova.web.tests;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.apetkova.web.pages.CopyTraderPanel;
import com.apetkova.webdriver.Driver;

public class BasicTests extends AbstractTest {

	@Test
	public void defaultStateTest() {
		CopyTraderPanel panel = new CopyTraderPanel();
		Assert.assertEquals(panel.getTitle().getText(), "Copy Trader");
		Assert.assertEquals(panel.getTraderName().getText(), copiedTraderName);
		Assert.assertTrue(panel.getFlagIcon().isDisplayed());
		Assert.assertTrue(panel.getAvatarImage().isDisplayed());
		Assert.assertEquals(panel.getAccountTypeIndicator().getAttribute("class"), "demo-account");
		// TODO: Verify specific user fields
	}

	@Test
	public void openHelpTest() {
		panel.getHelpIcon().click();
		try {
			Driver.getNewWait(3).until(ExpectedConditions.visibilityOfElementLocated(panel.getHelpPanelLocator()));
		} catch (TimeoutException te) {
			Assert.fail("ASSERTION FAIL: Help panel did not appear within 3 seconds");
		}
	}

	@Test
	public void closePanelTest() {
		panel.close();
		Assert.assertNull(CopyTraderPanel.getOpenPanel(), "ASSERTION FAIL: Copy Panel did not close by clicking on the 'x' icon");
	}
}
