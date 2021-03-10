package config;

import org.aeonbits.owner.Config;


@Config.Sources("classpath:${environment}.properties")
public interface WebDriverConfig extends Config {

	@Key("browser")
	String browser();

	@Key("browser.version")
	String browserVersion();

	@Key("remote.url")
	String webDriverUrl();

	@Key("video.storage")
	String videoStorage();
}
