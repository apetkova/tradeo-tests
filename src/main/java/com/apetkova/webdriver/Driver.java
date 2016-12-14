package com.apetkova.webdriver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Driver {

	private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<WebDriver>();

	/**
	 * Singleton
	 */
	private Driver() {
	}

	public static void startDriver(String browser) {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		WebDriver driver = null;

		if (browser.equals("chrome")) {
			String cmdBrowserLocation = System.getProperty("browser.location");
			String cmdDriverLocation = System.getProperty("chromedriver.location");
			if (cmdBrowserLocation != null) {
				Map<String, Object> chromeOptions = new HashMap<String, Object>();
				chromeOptions.put("binary", cmdBrowserLocation);
				capabilities = DesiredCapabilities.chrome();
				capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			}
			if (cmdDriverLocation != null) {
				System.setProperty("webdriver.chrome.driver", cmdBrowserLocation);
			}
			driver = new ChromeDriver(capabilities);
		} else if (browser.equals("firefox")) {
			capabilities = DesiredCapabilities.firefox();
			driver = new FirefoxDriver(capabilities);
		} else if (browser.equals("ie")) {
			driver = new InternetExplorerDriver();
		} else {
			throw new IllegalArgumentException("Unknown browser parameter passed: " + browser);
		}
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		DRIVER.set(driver);
	}

	public static void stopDriver() {
		getDriver().quit();
		DRIVER.remove();
	}

	public static WebDriverWait getNewWait(long timeOutInSeconds) {
		return new WebDriverWait(getDriver(), 10);
	}

	public static void loadUrl(String url) {
		getDriver().get(url);
	}

	public static WebDriver getDriver() {
		WebDriver driver = DRIVER.get();
		if (driver != null) {
			return driver;
		} else {
			throw new IllegalStateException("Driver not initialized yet");
		}
	}

}
