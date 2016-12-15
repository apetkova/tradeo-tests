package com.apetkova.web.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CrossTypeTests extends AbstractTest {
	private final String errorMessage = "Copying between DEMO and REAL accounts is not possible.";

	@BeforeMethod
	@Override
	public void navigateToCopyPanel() {
		navigateToCopyPanel("Tradeo real 2");
	}

	@Test
	public void demoCopyRealTest() {
		Assert.assertTrue(panel.getCrossTypeCopyingErrorMessage().isDisplayed(), 
				"ASSERT FAILURE: Error message is not displayed");
		Assert.assertEquals(panel.getCrossTypeCopyingErrorMessage().getText(), errorMessage, 
				"ASSERT FAILURE: Message text differes from expected");
	}
}
