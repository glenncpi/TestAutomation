package com.trueci.selenium;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;

public class BaseClass {

	private WebDriver driver;

	@BeforeClass
	public void setUp() {

		String path = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", path + "//drivers//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		Map<String, Object> map = this.readParameter();

		driver.get(map.get("appUrl").toString());
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	public WebDriver getDriver() {
		return this.driver;
	}
	
	public void disposeDriver(){
		driver.quit();
	}

	public Map<String, Object> readParameter() {

		Properties prop = new Properties();
		InputStream input = null;
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			String fileName = "testCase.properties";
			input = BaseClass.class.getClassLoader().getResourceAsStream(fileName);

			if (input == null) {
				System.out.println("Sorry, unable to find " + fileName);
			}

			prop.load(input);
			map.put("appUrl", prop.getProperty("appUrl"));

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return map;
	}
}
