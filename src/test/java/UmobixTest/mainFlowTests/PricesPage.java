package UmobixTest.mainFlowTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PricesPage extends BasePage {
    private static final String path = "/prices.html";
    private static final By oneMonthTittle = By.xpath("/html/body/div[4]/main/section/div[1]/form[3]/div[1]/div[1]");
    private static final By fullPack = By.xpath("/html/body/div[4]/main/section/div[1]/form[1]/div[1]/div[2]");
    private static final By currency = By.xpath("/html/body/div[4]/main/section/div[1]/form[3]/div[2]/div[1]/span[1]");
    private static final By price = By.xpath("/html/body/div[4]/main/section/div[1]/form[3]/div[2]/div[1]");
    private static final By duration = By.xpath("//form[contains(@class, 'price-plan-card') and contains(@class, 'price-plan-card--min')]//span[contains(@class, 'price-plan-card__price-small') and contains(text(),'/mo')]");
    private static final By oldOffer = By.xpath("/html/body/div[4]/main/section/div[1]/form[3]/div[2]/div[2]");
    private static final By oneTryNow = By.xpath("/html/body/div[4]/main/section/div[1]/form[3]/button");
    private static final String buttonProduct = "//div[@class='prices-plans']//input[contains(@value,'%s')]/parent::form/button";

    public PricesPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean compareURL(String currentURL) {
        return currentURL.contains("/prices.html");
    }

    public void isOneMonthTittleVisible() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(oneMonthTittle));
        WebElement oneMonthTittle = driver.findElement(PricesPage.oneMonthTittle);
        oneMonthTittle.isDisplayed();
    }

    public WebElement isOneMonthTittleCorrectText() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(oneMonthTittle));
        WebElement oneMonthTittle = driver.findElement(PricesPage.oneMonthTittle);
        return oneMonthTittle;
    }

    public void isOneMonthFullPackVisible() {
        WebElement fullPack = driver.findElement(PricesPage.fullPack);
        fullPack.isDisplayed();
    }

    public WebElement isOneMonthFullPackCorrectText() {
        WebElement fullPack = driver.findElement(PricesPage.fullPack);
        return fullPack;
    }

    public WebElement isOneMonthCurrencyCorrect() {
        WebElement currency = driver.findElement(PricesPage.currency);
        return currency;
    }

    public void isOneMonthPriceVisible() {
        WebElement price = driver.findElement(PricesPage.price);
        price.isDisplayed();
    }

    public WebElement isOneMonthPriceCorrectDuration() {
        WebElement duration = driver.findElement(PricesPage.duration);
        return duration;
    }

    public void isOneMonthPriceOldOfferVisible() {
        WebElement oldOffer = driver.findElement(PricesPage.oldOffer);
        oldOffer.isDisplayed();
    }

    public WebElement isOneMonthPriceCorrectOldOfferValue() {
        WebElement oldOffer = driver.findElement(PricesPage.oldOffer);
        return oldOffer;
    }

    public void isOneMonthTryNowButtonVisible() {
        WebElement oneTryNow = driver.findElement(PricesPage.oneTryNow);
        oneTryNow.isDisplayed();
    }

    public WebElement isOneMonthTryNowButtonCorrectText() {
        WebElement oneTryNow = driver.findElement(PricesPage.oneTryNow);
        return oneTryNow;
    }
    public boolean isCheckoutURLCorrect(String actualURL) {
        return actualURL.contains("https://checkout.umobix.com/en/FR/cart/um_mf1_50/umobix");

    }

    public void isTryNowButtonClickable(String productId) {
        driver.get(BasePage.getFullURL(PricesPage.path));
        String buttonProduct = String.format(PricesPage.buttonProduct, productId);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(buttonProduct)));
        WebElement tryNowButton = driver.findElement(By.xpath(buttonProduct));
        tryNowButton.click();
    }

}