package com.apetkova.web.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.apetkova.web.pages.AccountPage;
import com.apetkova.web.pages.CopyTraderPanel;

public class StopCopyingTests extends AbstractTest {

	@BeforeMethod
	private void copyAccount() {
		// check if account is already copied
		getPage(userProfilePage);
		AccountPage accountPage = new AccountPage();
		if (!accountPage.isTraderCopied(copiedTraderName)) {
			// copy account
			accountPage.openCopyPanel(copiedTraderPageName);
			panel = new CopyTraderPanel();
			panel.selectStandardTab();
			panel.getLotsInput().clear();
			panel.getLotsInput().sendKeys("5");
			panel.getSaveCopyingButton().click();
		}
	}

	@Test
	public void stopCopyingTest() {
		getPage(userProfilePage);
		AccountPage accountPage = new AccountPage();
		accountPage.openCopyPanel(copiedTraderPageName);
		panel = new CopyTraderPanel();
		panel.getStopCopyingButton().click();
		panel.confirm();
		getPage(userProfilePage);
		reload();
		accountPage = new AccountPage();
		Assert.assertFalse(accountPage.isTraderCopied(copiedTraderName), "ASSERTION FAILURE: Trader is still copied");
	}

	@Override
	public void navigateToCopyPanel() {
		// do nothing
	}
}
