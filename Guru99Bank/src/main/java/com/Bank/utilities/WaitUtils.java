package com.Bank.utilities;

import java.security.SecureRandom;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {

	public void waitForElement(WebElement element, WebDriver driver) {
		System.out.println(">> Waiting for element to be loaded.");
		try {
			Thread.sleep(5000);
			if (element != null) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
				wait.until(ExpectedConditions.visibilityOf(element));
			}
		} catch (InterruptedException e) {
			System.out.println("waitForElement() failed due to InterruptedException" + e);
			Thread.currentThread().interrupt();
		}
	}

	public void waitForElementUntilVisible(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementUntilClickable(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

}
