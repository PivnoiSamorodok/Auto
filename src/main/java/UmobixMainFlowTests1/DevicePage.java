package UmobixMainFlowTests1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collections;
import java.util.List;

public class DevicePage extends BasePage {

    protected static final String path = "/devices.html"; //Изменено на протектед потому что метод BasePAge.getFullURL раотает с нестатчиными данными, PricesPage.path был private static
    private static final By deviceH3 = By.cssSelector(".choose-platform-title");
    private static final By androidButton = By.cssSelector("[data-localstorege-device=android]");
    private static final By iosButton = By.cssSelector("[data-localstorege-device=ios]");
    private static final String deviceButton = "//div[@class='devices-card']//span[contains(text(),'%s')]";

    public DevicePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @Override
    List<String> getPaths() {
        return Collections.singletonList(path);
    }

//    @Override
//    String getPath() {
//        return path;
//    }


    public WebElement checkDeviceH3TitleVisible() {
        WebElement deviceH3 = driver.findElement(DevicePage.deviceH3);
        deviceH3.isDisplayed();
        return deviceH3;
    }

    public boolean isAndroidButtonVisible() {
        WebElement androidButton = driver.findElement(DevicePage.androidButton);
        return androidButton.isDisplayed();
    }

    public boolean isIosButtonVisible() {
        WebElement iosButton = driver.findElement(DevicePage.iosButton);
        return iosButton.isDisplayed();
    }

    public WebElement checkAndroidButtonTextCorrect() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(androidButton));
        WebElement androidButton = driver.findElement(DevicePage.androidButton);
        return androidButton;
    }

    public WebElement isIosButtonTextCorrect() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(iosButton));
        WebElement iosButton = driver.findElement(DevicePage.iosButton);
        return iosButton;

    }
    public boolean isDevicesButtonClickable(String deviceType) {
        String devicesButton = String.format(DevicePage.deviceButton, deviceType);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(devicesButton)));
        WebElement buttons = driver.findElement(By.xpath(devicesButton));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(devicesButton)));
        buttons.click();
        return true;
    }
    public String compareEmailUrl() {
        return getFullURL();
    }
}
