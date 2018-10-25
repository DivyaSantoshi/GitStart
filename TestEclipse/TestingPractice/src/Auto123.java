import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Auto123 {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "/users/divya/Documents/ChromeDriver/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize();

		login(driver);
		String parentWindow = driver.getWindowHandle();
		boolean cartEmpty = checkCart(driver);
		placeOrder(driver);
		logOut(driver,parentWindow);
		
		driver.quit();
	}

	public static void login(WebDriver driver) {
		WebElement signIn = driver.findElement(By.xpath("//div[@class='desktop-user']/div[1]"));
		Actions action = new Actions(driver);
		action.moveToElement(signIn).build().perform();

		WebElement loginBtn = driver.findElement(By.xpath("//a[text()='login']"));
		loginBtn.click();

		WebElement userName = driver.findElement(By.xpath("//input[@name='email']"));
		userName.sendKeys("divya4352@gmail.com");
		System.out.println("User Name entered successfully.");

		WebElement passwordTxt = driver.findElement(By.xpath("//input[@name='password']"));
		passwordTxt.sendKeys("DivyaSantoshi21");
		System.out.println("Password entered successfully");

		WebElement loginBtnn = driver.findElement(By.xpath("//button[text()='Log in']"));
		loginBtnn.click();
		System.out.println("Login button clicked successfully.");

	}
	
	public static void logOut(WebDriver driver, String parentWindow) {
		
		driver.switchTo().window(parentWindow);
		WebElement signIn = driver.findElement(By.xpath("//div[@class='desktop-user']/div[1]"));
		Actions action = new Actions(driver);
		action.moveToElement(signIn).build().perform();
		
		WebElement logoutLnk = driver.findElement(By.xpath("//div[text()=' Logout ']"));
		logoutLnk.click();
		System.out.println("Logout link clicked successfully");
	}

	public static boolean checkCart(WebDriver driver) {
		WebElement cartBtn = driver.findElement(By.xpath("//a[@class='desktop-cart']"));
		cartBtn.click();
		boolean cartEmpty = false;
		try {
			cartEmpty=driver.findElement(By.xpath("//div[text()='Your Shopping Bag is empty']")).isDisplayed();
			cartEmpty = true;
		}
		catch (NoSuchElementException e){
			cartEmpty = false;
		}
		

		if(cartEmpty)
			return cartEmpty;
		else {
			List<WebElement> removeLnk = driver.findElements(By.xpath("//span[text()='REMOVE']"));
			if (removeLnk.size() > 0) {
				for (WebElement element : removeLnk) {
					element.click();
					WebElement removePopUpLnk = driver.findElement(By.xpath("//button[text()='REMOVE']"));
					removePopUpLnk.click();
				}
			}
			
			try {
				cartEmpty=driver.findElement(By.xpath("//div[text()='Your Shopping Bag is empty']")).isDisplayed();
				cartEmpty = true;
			}
			catch (NoSuchElementException e){
				cartEmpty = false;
			}
		}

		return cartEmpty;

	}

	public static void placeOrder(WebDriver driver) throws InterruptedException {
		driver.findElement(By.xpath("//span[@class='myntra-logo']")).click();
		WebElement lnkWomen = driver.findElement(By.xpath("//a[text()='Women']"));

		Actions action = new Actions(driver);
		action.moveToElement(lnkWomen).build().perform();

		WebElement lnkTops = driver.findElement(By.xpath("//a[text()='Tops, T-Shirts & Shirts']"));
		lnkTops.click();

		List<WebElement> noOfTops = driver.findElements(By.xpath("//li[@class='product-base']"));

		System.out.println("Total number of tops :" + noOfTops.size());

		String parentWindow = driver.getWindowHandle();

		noOfTops.get(1).click();
		Set<String> noOfWindows = driver.getWindowHandles();
		for (String window : noOfWindows) {
			if (!window.equals(parentWindow)) {
				driver.switchTo().window(window);
				break;
			}
		}

		Thread.sleep(3000);
		WebElement size = driver.findElement(By.xpath("//button[contains(@class,'size')]/p"));
		size.click();
		
		Thread.sleep(5000);

		driver.findElement(By.xpath("//div[text()='ADD TO BAG']")).click();
		Thread.sleep(2000);
		System.out.println("Item added to the bag successfully");

		driver.findElement(By.xpath("//span[text()='GO TO BAG']")).click();
		System.out.println("Navigated to place order page.");

		WebElement placeOrderBtn = driver.findElement(By.xpath("//button[text()='PLACE ORDER']"));
		placeOrderBtn.click();
		System.out.println(" Place order button clicked successfully.");
	}
}
