package com.apetkova.web.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.apetkova.web.pages.AccountPage;
import com.apetkova.web.pages.CopyTraderPanel;

public class SameTypeTests extends AbstractTest {

	@BeforeMethod
	public void stopCopyingAccount() {
		// check if account is already copied
		getPage(userProfilePage);
		AccountPage accountPage = new AccountPage();
		if (accountPage.isTraderCopied(copiedTraderName)) {
			// stop copying account
			accountPage.openCopyPanel(copiedTraderPageName);
			panel = new CopyTraderPanel();
			panel.getStopCopyingButton().click();
			panel.confirm();
		}
	}

	@Test
	public void copyStandardTest() {
		navigateToCopyPanel();
		panel.selectStandardTab();
		panel.getLotsInput().clear();
		panel.getLotsInput().sendKeys("5");
		panel.getSaveCopyingButton().click();
		verifyResult(copiedTraderName);
		// TODO: check that parameters are also correct
	}

	@Test
	public void copyDynamicTest() {
		navigateToCopyPanel();
		panel.selectDynamicTab();
		panel.getPreferredInput().clear();
		panel.getPreferredInput().sendKeys("5");
		panel.getMaximumInput().clear();
		panel.getMaximumInput().sendKeys("10");
		panel.getSaveCopyingButton().click();
		verifyResult(copiedTraderName);
		// TODO: check that parameters are also correct
	}

	private void verifyResult(String traderName) {
		getPage(userProfilePage);
		reload();
		AccountPage accountPage = new AccountPage();
		Assert.assertTrue(accountPage.isTraderCopied(traderName));
	}

	@Override
	public void navigateToCopyPanel() {
		getPage(userProfilePage);
		AccountPage accountPage = new AccountPage();
		accountPage.openCopyPanel(copiedTraderPageName);
		panel = new CopyTraderPanel();
	}
}
