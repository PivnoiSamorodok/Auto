package UmobixTest.mainFlowTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmailPage extends BasePage {

    private static final String path = "/email.html";

    private static final By emailInputSelector = By.cssSelector("#tryNowEmail");
    private static final By emailButtonSelector = By.cssSelector("#tryStep > div > form > button");

    public EmailPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean checkEmailInputVisibility() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(emailInputSelector));
        WebElement emailInput = driver.findElement(emailInputSelector);
        return true;
    }

    public String isEmailPlaceholderCorrect() {
        WebElement emailInput = driver.findElement(emailInputSelector);
        String placeholder = emailInput.getAttribute("placeholder");
        return placeholder;
    }

    public void checkEmailValueInput() {
        WebElement emailInput = driver.findElement(emailInputSelector);
        randomEmailGeneration rndEmail = new randomEmailGeneration(5);
        String randomEmail = rndEmail.GenerateEmail();
        emailInput.sendKeys(randomEmail);
    }

    public WebElement isEmailButtonVisible() {
        WebElement emailButton = driver.findElement(emailButtonSelector);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("#tryStep > div > form > button")));
        emailButton.isDisplayed();
        return emailButton;
    }


    public void isEmailButtonClickable(WebElement emailButton) {
        emailButton.click();
    }

    public boolean CompareDeviceURl(String actualURL) {
        return actualURL.contains(DevicePage.returnPath());
    }
}
