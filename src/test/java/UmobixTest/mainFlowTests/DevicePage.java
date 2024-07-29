package UmobixTest.mainFlowTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DevicePage extends BasePage {

    private static final String path = "/devices.html";

    public static String returnPath(){
        return path;

    }
    private static final By deviceH3 = By.cssSelector(".choose-platform-title");
    private static final By androidButton = By.cssSelector("[data-localstorege-device=android]");
    private static final By iosButton = By.cssSelector("[data-localstorege-device=ios]");
    private static final String deviceButton = "//div[@class='devices-card']//span[contains(text(),'%s')]";

    public DevicePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }


    public void isDeviceH3TitleVisible() {
        WebElement deviceH3 = driver.findElement(DevicePage.deviceH3);
        deviceH3.isDisplayed();
    }

    public void isAndroidButtonVisible() {
        WebElement androidButton = driver.findElement(DevicePage.androidButton);
        androidButton.isDisplayed();
    }

    public void isIosButtonVisible() {
        WebElement iosButton = driver.findElement(DevicePage.iosButton);
        iosButton.isDisplayed();
    }

    public WebElement isAndroidButtonTextCorrect() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(androidButton));
        WebElement androidButton = driver.findElement(DevicePage.androidButton);
        return androidButton;
    }

    public WebElement isIosButtonTextCorrect() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(iosButton));
        WebElement iosButton = driver.findElement(DevicePage.iosButton);
        return iosButton;

    }
    public void isDevicesButtonClickable(String deviceType) {
        //driver.get(BasePage.getFullURL(DevicePage.path));
        String devicesButton = String.format(DevicePage.deviceButton, deviceType);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(devicesButton)));
        WebElement buttons = driver.findElement(By.xpath(devicesButton));
        buttons.click();
    }
}
