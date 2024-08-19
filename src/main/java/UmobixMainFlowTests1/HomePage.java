package UmobixMainFlowTests1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collections;
import java.util.List;

public class HomePage extends BasePage {

    private static final By headerTryNow = By.cssSelector(".try-now-button.button.button--header.button-style-blinking.button-style-1");

    private static final String path = "?dont-send-to-stat=1";
    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver,wait);
    }

    @Override
    List<String> getPaths() {
        return Collections.singletonList(path);
    }

    private WebElement getHeaderTryNow(){
        return driver.findElement(headerTryNow);
    }

    public void isTryNowHeaderClickable(){
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(headerTryNow));
        getHeaderTryNow().click();
    }

    public boolean compareURL(String actualURL){
        return actualURL.contains(path);
    }
}

