package tc01;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

//test git
public class TC01 {
    private AndroidDriver androidDriver;
    WebDriverWait wait;
    @Test
    public void test() throws MalformedURLException {

        //1. Open BigC App
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("appium:deviceName", "Samsung");
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:udid", "R9JN706FH9J");
        desiredCapabilities.setCapability("appium:automationName", "UiAutomator2");
        desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME, "Chrome");

        androidDriver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);
        androidDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        wait = new WebDriverWait(androidDriver, Duration.ofSeconds(30));

        androidDriver.navigate().to("http://google.com");
        WebElement boxText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='gLFyf']")));
        boxText.sendKeys("appium testing english");
        boxText.sendKeys(Keys.ENTER);

        WebElement firstResult = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class=\"yKd8Hd qzEoUe\"])[1]")));
        firstResult.click();

        Assert.assertEquals(androidDriver.getTitle(), "Appium: Mobile App Automation Made Awesome.");
    }
}
