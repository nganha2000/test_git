package tc02;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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

// test git
public class TC02 {
    private AndroidDriver androidDriver;
    private WebDriverWait wait;
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
        androidDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(androidDriver, Duration.ofSeconds(30));

        androidDriver.navigate().to("http://shopee.vn");

        swipeMobileUp(By.xpath("(//div[@class=\"header-section__header-title\"])[2]"));
        WebElement shopeeMall = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@class=\"header-section__header-link\"])[2]")));
        shopeeMall.click();
        WebElement ncds = androidDriver.findElement(By.xpath("//android.widget.TextView[@text=\"Nhà Cửa & Đời Sống\"]"));
        ncds.click();
        WebElement firstResult = androidDriver.findElement(By.xpath("(//android.widget.TextView)[7]"));
        firstResult.click();

        /** verify */
        WebElement proImg = androidDriver.findElement(By.xpath("(//android.widget.Image)[4]"));
        proImg.isDisplayed();
        WebElement proName = androidDriver.findElement(By.xpath("(//android.widget.TextView)[13]"));
        proName.isDisplayed();
        WebElement proPrice = androidDriver.findElement(By.xpath("(//android.widget.TextView)[14]"));
        proPrice.isDisplayed();

    }

    public void swipe(int startx, int starty, int endx, int endy) {
        new TouchAction(androidDriver).longPress(PointOption.point(startx, starty))
                .moveTo(PointOption.point(endx, endy))
                .release().perform();
    }

    public void swipeMobileUp(By by) {
        boolean check = false;
        Dimension size = androidDriver.manage().window().getSize();
        int starty = (int) (size.height * 0.8);
        int endy = (int) (size.height * 0.2);
        int startx = size.width / 2;
        try {
            check = androidDriver.findElement(by).isDisplayed();
        }catch (Exception e) {

        }
        while (!check) {
            swipe(startx, starty, startx, endy);
            try{
                check = androidDriver.findElement(by).isDisplayed();
            }catch (Exception e) {

            }
        }
    }
    public void swipeMobileDown(By by) {
        boolean check = false;
        Dimension size = androidDriver.manage().window().getSize();
        int starty = (int) (size.height * 0.8);
        int endy = (int) (size.height * 0.2);
        int startx = size.width / 2;
        try {
            check = androidDriver.findElement(by).isDisplayed();
        }catch (Exception e) {

        }
        while (!check) {
            swipe(startx, endy, startx, starty );
            try{
                check = androidDriver.findElement(by).isDisplayed();
            }catch (Exception e) {

            }
        }
    }

}
