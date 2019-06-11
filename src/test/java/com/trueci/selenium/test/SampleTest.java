package com.trueci.selenium.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.trueci.selenium.BaseClass;

public class SampleTest extends BaseClass{
	

	@Test
	public void getPageTitle(){
		
		String title = getDriver().getTitle();
		Assert.assertEquals(title, "Sample Page");
		disposeDriver();
	}
	
}
