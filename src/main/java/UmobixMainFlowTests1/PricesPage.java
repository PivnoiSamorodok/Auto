package UmobixMainFlowTests1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;


public class PricesPage extends BasePage {
    private static final String path1 = "/prices.html";
    private static final String path2 = "/prices_new.html";
    private static final String monthTittle = "//div[@class='price-plan-card__period' and contains(text(),'%s')]";
    private static final By fullPack = By.xpath("/html/body/div[4]/main/section/div[1]/form[1]/div[1]/div[2]"); // не смог получить cssSelector не нашел за что зацепиться, subscribe-plan-card__actual-price не находит данный текст
    private String fullPackAbsolute = "//div[@class='price-plan-card__info'][.//div[@class='price-plan-card__period' and contains(text(),'%s')]]//div[@class='price-plan-card__type' and contains(text(),'FULL pack')]"; // не смог получить cssSelector не нашел за что зацепится, сделал абсолютный путь под дата провайдер
    private static final By currency = By.xpath("/html/body/div[4]/main/section/div[1]/form[3]/div[2]/div[1]/span[1]"); // не понимаю за что цепляться price-plan-card__price-small дублируется 3 раза, не нашел дочерних
    private static final By price = By.xpath("/html/body/div[4]/main/section/div[1]/form[3]/div[2]/div[1]");
    private static final By duration = By.xpath("//form[contains(@class, 'price-plan-card') and contains(@class, 'price-plan-card--min')]//span[contains(@class, 'price-plan-card__price-small') and contains(text(),'/mo')]");
    private static final By oldOffer = By.xpath("/html/body/div[4]/main/section/div[1]/form[3]/div[2]/div[2]");
    private static final By oneTryNow = By.xpath("/html/body/div[4]/main/section/div[1]/form[3]/button");
    private static final String buttonProduct = "//div[@class='prices-plans']//input[contains(@value,'%s')]/parent::form/button";
    private String urlPattern = "https://checkout.umobix.com/en/FR/cart/(um_mf1_50|um_mf3_90|um_mf12_150)/umobix.*";
    private String subscriptionPriceAbsolute = "//div[contains(@class,'price-plan-card__price')] /text()[normalize-space(.)='%s']";
    public PricesPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @Override
    List<String> getPaths() {
        return Arrays.asList(path1,path2);
    }

    public boolean compareURL() {
        String currentURL = driver.getCurrentUrl();
        boolean pathContains = currentURL.contains(path1) || currentURL.contains(path2);
        return pathContains;
    }

    public boolean subTypeTittleVisible(String monthPeriod) {
        String formattedMonthTittle = String.format(monthTittle,monthPeriod);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(formattedMonthTittle)));
        WebElement oneMonthTittle = driver.findElement(By.xpath(formattedMonthTittle));
        oneMonthTittle.isDisplayed();
        return true;
    }

    public String subTypeTittleCorrectText(String monthPeriod) {
        String formattedMonthTittle = String.format(monthTittle,monthPeriod);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(formattedMonthTittle)));
        WebElement oneMonthTittle = driver.findElement(By.xpath(formattedMonthTittle));
        return oneMonthTittle.getText();
    }

//    public boolean isOneMonthFullPackVisible() {
//        WebElement fullPack = driver.findElement(PricesPage.fullPack);
//        fullPack.isDisplayed();
//        return true;
//    }
    public boolean isFullPackTittleVisible(String monthPeriod) {
        String formattedMonthTittle = String.format(fullPackAbsolute, monthPeriod);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(formattedMonthTittle)));
        WebElement fullPack = driver.findElement(By.xpath(formattedMonthTittle));
        fullPack.isDisplayed();
        return true;
    }

    public WebElement isFullPackCorrectText(String monthPeriod) {
        String formatterMonthPeriod = String.format(fullPackAbsolute, monthPeriod);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(formatterMonthPeriod)));
        WebElement fullPack = driver.findElement(By.xpath(formatterMonthPeriod));
        return fullPack;
    }

    public WebElement isOneMonthCurrencyCorrect() {
        WebElement currency = driver.findElement(PricesPage.currency);
        return currency;
    }

    public boolean isOneMonthPriceVisible() {
        WebElement price = driver.findElement(PricesPage.price);
        price.isDisplayed();
        return true;
    }

    public WebElement isOneMonthPriceCorrectDuration() {
        WebElement duration = driver.findElement(PricesPage.duration);
        return duration;
    }

    public boolean isOneMonthPriceOldOfferVisible() {
        WebElement oldOffer = driver.findElement(PricesPage.oldOffer);
        oldOffer.isDisplayed();
        return true;
    }

    public WebElement isOneMonthPriceCorrectOldOfferValue() {
        WebElement oldOffer = driver.findElement(PricesPage.oldOffer);
        return oldOffer;
    }

    public boolean isOneMonthTryNowButtonVisible() {
        WebElement oneTryNow = driver.findElement(PricesPage.oneTryNow);
        oneTryNow.isDisplayed();
        return true;
    }

    public WebElement isOneMonthTryNowButtonCorrectText() {
        WebElement oneTryNow = driver.findElement(PricesPage.oneTryNow);
        return oneTryNow;
    }
    public boolean isCheckoutURLCorrect() {
        return driver.getCurrentUrl().matches(urlPattern);
    }

    public boolean isTryNowButtonClickable(String productId) {
        String buttonProduct = String.format(PricesPage.buttonProduct, productId);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(buttonProduct)));
        WebElement tryNowButton = driver.findElement(By.xpath(buttonProduct));
        tryNowButton.click();
        return true;
    }

    public boolean isPriceVisible(String value){
        String formattedPrice = String.format(subscriptionPriceAbsolute, value);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(formattedPrice)));
        WebElement price = driver.findElement(By.xpath(formattedPrice));
        price.isDisplayed();
        return true;
    }

}