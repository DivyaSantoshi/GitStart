import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class videoPlayCode {

	public static void main(String [] args) throws InterruptedException {
	System.setProperty("webdriver.chrome.driver", "/users/divya/Documents/ChromeDriver/chromedriver");
	WebDriver driver= new ChromeDriver();

	//Open the page containing video component.
	driver.get("http://html5video.org/wiki/JW_HTML5_Video_Player");
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

	//If video is placed in embed tag or iframe, navigate to the source.
	//However, we can even switch to frame. [Else, skip this step]
	WebElement elm = driver.findElement(By.id("video_iframe"));
	String urlStr = elm.getAttribute("src");
	System.out.println("Video Url : " + urlStr);
	driver.navigate().to(urlStr);
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

	JavascriptExecutor jse = (JavascriptExecutor) driver;
	//Click on play button
	jse.executeScript("jwplayer().play();");
	Thread.sleep(2000);
	//Pause
	jse.executeScript("jwplayer().pause()");
	Thread.sleep(2000);
	//Play
	jse.executeScript("jwplayer().play();");
	Thread.sleep(2000);
	// Set Volume
	Thread.sleep(2000);
	jse.executeScript("jwplayer().setVolume(50);");
	Thread.sleep(2000);
	//Mute Player
	jse.executeScript("jwplayer().setMute(true);");
	Thread.sleep(2000);
	//UnMute Player
	jse.executeScript("jwplayer().setMute(false);");
	Thread.sleep(2000);
	//Stop the player
	jse.executeScript("jwplayer().stop()");
	Thread.sleep(2000);

	driver.quit();

	}
}
