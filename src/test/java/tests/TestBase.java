package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;

import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static tests.AttachmentsHelper.*;

import config.WebDriverConfig;
import io.qameta.allure.selenide.AllureSelenide;


public class TestBase {
	static WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());

	@BeforeAll
	public static void setUp() {
		addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
		Configuration.browser = config.browser();
		Configuration.browserVersion = config.browserVersion();
		Configuration.startMaximized = true;

		if (config.webDriverUrl() != null) {
			// config for Java + Selenide
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("enableVNC", true);
//			capabilities.setCapability("enableVideo", true);
			Configuration.browserCapabilities = capabilities;
			Configuration.remote = config.webDriverUrl();
		}
	}

	@AfterEach
	public void afterEach() {
		attachScreenshot("Last screenshot");
		attachPageSource();
		attachAsText("Browser console logs", getConsoleLogs());
//		if (config.videoStorage() != null) {
//			attachVideo();
//		}
		closeWebDriver();
	}
}
