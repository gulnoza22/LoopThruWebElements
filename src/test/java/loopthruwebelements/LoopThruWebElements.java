package loopthruwebelements;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoopThruWebElements {
	
	Faker faker = new Faker();
	Random rnd = new Random();
	WebDriver driver;

	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	// @Test
	public void myLinks() {
		driver.get("https://github.com");
		List<WebElement> links = driver.findElements(By.xpath("//a"));
		int numberOfLinks = links.size();
		System.out.println("Number of links: " + numberOfLinks);

		int count = 1;
		for (WebElement link : links) {
			if (!link.getText().isEmpty()) {
				System.out.println(count + ": " + link.getText());
				count++;

			}
		}

		count = 1;
		List<String> linkNames = new ArrayList<>();
		for (WebElement link : links) {
			if (!link.getText().isEmpty()) {
				linkNames.add(link.getText());
				count++;
			}
		}
		System.out.println(count + ": " + linkNames.toString());

	}

	/*
	 * Homework: Loop through each inputbox and enter random names Loop through each
	 * dropDown and randomly select by index Loop through each checkBoxes and select
	 * each one Loop through each radioButton and click one by one by waiting one
	 * second intervals click all buttons
	 */
	@Test
	public void SeleniumWebElementsForm() throws InterruptedException {
		driver.get(
				"https://forms.zohopublic.com/murodil/form/SeleniumWebElements/formperma/eCecYgX4WMcmjxvXVq6UdhA2ABXIoqPAxnAF8H8CCJg");
		List<WebElement> inputBoxes = driver.findElements(By.xpath("//input[@type='text']"));
		Assert.assertEquals(inputBoxes.size(), 2);
		//Loop through each inputbox and enter random names Loop through each
		for (WebElement eachbox : inputBoxes) {
			eachbox.sendKeys(faker.name().fullName());
		}

		List<WebElement> dropdowns = driver.findElements(By.xpath("//select"));
		Assert.assertEquals(dropdowns.size(), 3);

		for (WebElement eachDropDown : dropdowns) {
			new Select(eachDropDown).selectByIndex(faker.number().numberBetween(1, 4));
		}

		Thread.sleep(3000);

		List<WebElement> checkBoxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
		Assert.assertEquals(checkBoxes.size(), 9);

		for (WebElement eachCheckBoxes : checkBoxes) {

			eachCheckBoxes.click();
		}

		List<WebElement> radioButtons = driver.findElements(By.xpath("//input[@type='radio']"));
		Assert.assertEquals(radioButtons.size(), 9);

		for (WebElement eachRadioButtons : radioButtons) {
			eachRadioButtons.click();
		}

		List<WebElement> buttons = driver.findElements(By.xpath("//button"));
		Assert.assertEquals(buttons.size(), 1);

		for (WebElement eachButtons : buttons) {
			eachButtons.click();

		}
         Thread.sleep(80000);
		driver.close();

	}


	// @Test
	public void slideShow() throws InterruptedException {
		driver.get("https://www.hbloom.com/Gifts/birthday-flowers");
		List<WebElement> images = driver.findElements(By.tagName("img"));
		List<String> srcs = new ArrayList<>();

		for (WebElement flower : images) {
			srcs.add(flower.getAttribute("src"));
		}

		for (String link : srcs) {
			driver.get(link);
			Thread.sleep(1234);
		}

	}

}



