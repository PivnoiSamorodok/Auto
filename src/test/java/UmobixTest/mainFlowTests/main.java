package UmobixTest.mainFlowTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;


public class main {
    HomePage homePage;
    UmobixTest.mainFlowTests.EmailPage EmailPage;
    UmobixTest.mainFlowTests.DevicePage DevicePage;
    UmobixTest.mainFlowTests.PricesPage PricesPage;
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        homePage = new HomePage(driver, wait);
        EmailPage = new EmailPage(driver, wait);
        DevicePage = new DevicePage(driver, wait);
        PricesPage = new PricesPage(driver, wait);

    }

    @Test
    public void HomePageTryNowClick() {
        driver.get(DevicePage.getFullURL(homePage.getPath()));
        homePage.isTryNowHeaderClickable();
        boolean currentURL = homePage.compareURL(driver.getCurrentUrl());
        Assert.assertTrue(currentURL, "Email URL doesnt have expected part");
    }

    @Test(dependsOnMethods = {"HomePageTryNowClick"})
    public void emailInputFieldDisplay() {
        boolean emailInputField = EmailPage.checkEmailInputVisibility();
        Assert.assertTrue(emailInputField, "Expected email input field isnt displaying");
    }

    @Test(dependsOnMethods = {"emailInputFieldDisplay"})
    public void emailPlaceholderCheck() {
        String placeholder = EmailPage.isEmailPlaceholderCorrect();
        Assert.assertEquals(placeholder, "Enter your valid email", "Inccorrect placeholder name");
    }

    @Test(dependsOnMethods = {"emailPlaceholderCheck"})
    public void emailInputIntoField() {
        EmailPage.checkEmailValueInput();
    }

    @Test(dependsOnMethods = {"emailPlaceholderCheck"})
    //Временная заглушка с emailPlaceholderCheck должно быть emailInputIntoField
    public void emailButtonDisplay() {
        EmailPage.isEmailButtonVisible();
    }

    @Test(dependsOnMethods = {"emailButtonDisplay"})
    public void emailButtonClick() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("#tryStep > div > form > button")));
        WebElement emailButton = EmailPage.isEmailButtonVisible();
        EmailPage.isEmailButtonClickable(emailButton);
    }

    @Test(dependsOnMethods = {"emailButtonClick"})
    public void compareDeviceURL() {
        boolean isURLCorrect = EmailPage.CompareDeviceURl(driver.getCurrentUrl());
        Assert.assertTrue(isURLCorrect, "Device URL doesnt have expected part");
    }

    @Test(dependsOnMethods = {"compareDeviceURL"})
    public void deviceH3TitleCheck() {
        DevicePage.isDeviceH3TitleVisible();
    }

    @Test(dependsOnMethods = {"deviceH3TitleCheck"}, alwaysRun = true)
    public void deviceAndroidButtonCheck() {
        DevicePage.isAndroidButtonVisible();
    }

    @Test(dependsOnMethods = {"deviceAndroidButtonCheck"}, alwaysRun = true)
    public void deviceIosButtonCheck() {
        DevicePage.isIosButtonVisible();
    }

    @Test(dependsOnMethods = {"deviceAndroidButtonCheck"}, alwaysRun = true)
    public void deviceAndroidButtonText() {
        WebElement androidButton = DevicePage.isAndroidButtonTextCorrect();
        Assert.assertEquals(androidButton.getText(), "Android phone or tablet");
    }

    @Test(dependsOnMethods = {"deviceIosButtonCheck"}, alwaysRun = true)
    public void deviceIosButtonText() {
        WebElement iosButton = DevicePage.isIosButtonTextCorrect();
        Assert.assertEquals(iosButton.getText(), "iOS, iPhone, iPad");
    }
    @DataProvider(name = "devicesTypes")
    public Object[][] devicesTypes() {
        return new Object[][]{
                {"Android phone or tablet"},
                {"iOS, iPhone, iPad"}
        };
    }

    @Test(dataProvider = "devicesTypes", dependsOnMethods = {"deviceIosButtonText"})
    public void deviceTypeChoosing(String devicesTypes) {
        DevicePage.isDevicesButtonClickable(devicesTypes);
    }

    @Test(dependsOnMethods = {"deviceTypeChoosing"})
    public void pricesCompareURL() {
        boolean pricesURL = PricesPage.compareURL(driver.getCurrentUrl());
        Assert.assertTrue(pricesURL, "Expected pricesURL doesnt have expected part");
    }

    @Test(dependsOnMethods = {"pricesCompareURL"})
    public void pricesOneMonthTittleDisplayed() {
        PricesPage.isOneMonthTittleVisible();
    }

    @Test(dependsOnMethods = {"pricesOneMonthTittleDisplayed"}, alwaysRun = true)
    public void pricesOneMonthTittleCorrectText() {
        WebElement oneMonthTittle = PricesPage.isOneMonthTittleCorrectText();
        Assert.assertEquals(oneMonthTittle.getText(), "1 Month");

    }

    @Test(dependsOnMethods = {"pricesOneMonthTittleCorrectText"})
    public void pricesOneMonthFullPackDisplayed() {
        PricesPage.isOneMonthFullPackVisible();
    }

    @Test(dependsOnMethods = {"pricesOneMonthFullPackDisplayed"}, alwaysRun = true)
    public void pricesOneMonthFullPackCorrectText() {
        WebElement fullPack = PricesPage.isOneMonthFullPackCorrectText();
        Assert.assertEquals(fullPack.getText(), "FULL PACK");
    }

    @Test(dependsOnMethods = {"pricesOneMonthFullPackCorrectText"})
    public void pricesOneMonthCurrencyCheck() {
        WebElement currency = PricesPage.isOneMonthCurrencyCorrect();
        Assert.assertEquals(currency.getText(), "€");
    }

    @Test(dependsOnMethods = {"pricesOneMonthCurrencyCheck"})
    public void pricesOneMonthPriceDisplaying() {
        PricesPage.isOneMonthPriceVisible();
    }

    @Test(dependsOnMethods = {"pricesOneMonthPriceDisplaying"}, alwaysRun = true)
    public void pricesOneMonthPriceCorrectValue() {
        WebElement duration = PricesPage.isOneMonthPriceCorrectDuration();
        Assert.assertEquals(duration.getText(), "/mo");

    }

    @Test(dependsOnMethods = {"pricesOneMonthPriceDisplaying"}, alwaysRun = true)
    public void isOneMonthPriceOldOfferVisible() {
        PricesPage.isOneMonthPriceOldOfferVisible();
    }

    @Test(dependsOnMethods = {"isOneMonthPriceOldOfferVisible"})
    public void pricesOneMonthPriceCorrectOldOffer() {
        WebElement oldOffer = PricesPage.isOneMonthPriceCorrectOldOfferValue();
        Assert.assertEquals(oldOffer.getText(), "€ 59.99 /mo");

    }

    @Test(dependsOnMethods = {"pricesOneMonthPriceCorrectValue"}, alwaysRun = true)
    public void pricesOneMonthTryNowButtonDisplaying() {
        PricesPage.isOneMonthTryNowButtonVisible();
    }

    @Test(dependsOnMethods = {"pricesOneMonthTryNowButtonDisplaying"})
    public void pricesOneMonthTryNowButtonCorrectText() {
        WebElement oneTryNow = PricesPage.isOneMonthTryNowButtonCorrectText();
        Assert.assertEquals(oneTryNow.getText(), "TRY NOW");

    }

    @DataProvider(name = "subscriptionsTypes")
    public Object[][] subscriptionsTypes() {
        return new Object[][]{
                {"um_mf1_50"},
                {"um_mf3_90"},
                {"um_mf12_150"}
        };
    }

    @Test(dataProvider = "subscriptionsTypes", dependsOnMethods = {"pricesOneMonthTryNowButtonCorrectText"})
    public void tryNowSubButtonClick(String subscriptionsTypes) {
        //driver.get(BasePage.getFullURL(PricesPage.path));
        PricesPage.isTryNowButtonClickable(subscriptionsTypes);
    }

    @Test(dependsOnMethods = {"tryNowSubButtonClick"})
    public void checkoutURLCompare() {
        boolean checkoutURL = PricesPage.isCheckoutURLCorrect(driver.getCurrentUrl());
        Assert.assertTrue(checkoutURL, "Checkout URL doesnt have expected part");
    }


}
