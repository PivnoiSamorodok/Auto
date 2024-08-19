package UmobixMainFlowTests1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collections;
import java.util.List;

public class EmailPage extends BasePage {

    private static final String path = "/email.html";

    private static final By emailInputSelector = By.cssSelector("#tryNowEmail");
    private static final By emailButtonSelector = By.cssSelector("button.button--primary");

    public EmailPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }
    @Override
    List<String> getPaths() {
        return Collections.singletonList(path);
    }

    public boolean checkEmailInputVisibility() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(emailInputSelector));
        WebElement emailInput = driver.findElement(emailInputSelector);
        return true;
    }

    public String checkEmailPlaceholderCorrection() {
        WebElement emailInput = driver.findElement(emailInputSelector);
        String placeholder = emailInput.getAttribute("placeholder");
        return placeholder;
    }

    public String checkEmailValueInput() {
        WebElement emailInput = driver.findElement(emailInputSelector);
        randomEmailGeneration rndEmail = new randomEmailGeneration(5);
        String randomEmail = rndEmail.GenerateEmail();
        emailInput.sendKeys(randomEmail);
        return  randomEmail;
    }

    public boolean isEmailButtonVisible() {
        WebElement emailButton = driver.findElement(emailButtonSelector);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(emailButtonSelector));
        emailButton.isDisplayed();
        return true;
    }


    public void clickabilityOfEmailButton() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(emailButtonSelector));
        WebElement emailButton = driver.findElement(emailButtonSelector);
        emailButton.click();
    }

    public String compareEmailUrl() {
        return getFullURL();
    }

}
