package UmobixMainFlowTests1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;


public class main {
    HomePage homePage;
    UmobixMainFlowTests1.EmailPage EmailPage;
    UmobixMainFlowTests1.DevicePage DevicePage;
    UmobixMainFlowTests1.PricesPage PricesPage;
    WebDriver driver;
    WebDriverWait wait;
    randomEmailGeneration RandomEmailGeneration;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        homePage = new HomePage(driver, wait);
        EmailPage = new EmailPage(driver, wait);
        DevicePage = new DevicePage(driver, wait);
        PricesPage = new PricesPage(driver, wait);
        RandomEmailGeneration = new randomEmailGeneration(5);

    }
    @Test
    public void HomePageTryNowClick() {
        driver.get(homePage.getFullURL());
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
        String placeholder = EmailPage.checkEmailPlaceholderCorrection();
        Assert.assertEquals(placeholder, "Enter your valid email", "Inccorrect placeholder name");
    }

    @Test(dependsOnMethods = {"emailPlaceholderCheck"})
    public void emailInputIntoField() {
        String emailInput = EmailPage.checkEmailValueInput();
        Assert.assertTrue(emailInput.contains(RandomEmailGeneration.getDomain()));
    }

    @Test(dependsOnMethods = {"emailPlaceholderCheck"})
    public void emailButtonDisplay() {
        boolean isVisible = EmailPage.isEmailButtonVisible();
        Assert.assertTrue(isVisible, "The Button doesnt displayed on the page");
    }

    @Test(dependsOnMethods = {"emailButtonDisplay"})
    public void emailButtonClick() {
        String initialURL = driver.getCurrentUrl();
        EmailPage.clickabilityOfEmailButton();
        String newURL = driver.getCurrentUrl();
        Assert.assertNotEquals(newURL, initialURL, "URL didnt change after click on the email button");
    }

    @Test(dependsOnMethods = {"emailButtonClick"})
    public void compareDeviceURL() {
        String isURLCorrect = DevicePage.compareEmailUrl();
        Assert.assertEquals(isURLCorrect, "https://umobix.com/devices.html", "Device URL doesnt have expected part");
    }

    @Test(dependsOnMethods = {"compareDeviceURL"})
    public void deviceH3TitleCheck() {
        WebElement tittle = DevicePage.checkDeviceH3TitleVisible();
        Assert.assertEquals(tittle.getText(), "Select the target device to be monitored", "Device tittle doesnt have expected part");
    }

    @Test(dependsOnMethods = {"deviceH3TitleCheck"}, alwaysRun = true)
    public void deviceAndroidButtonCheck() {
        boolean isButtonVisible = DevicePage.isAndroidButtonVisible();
        Assert.assertTrue(isButtonVisible, "Android button doesnt displayed on page");

    }

    @Test(dependsOnMethods = {"deviceAndroidButtonCheck"}, alwaysRun = true)
    public void deviceIosButtonCheck() {
        boolean isButtonVisible = DevicePage.isIosButtonVisible();
        Assert.assertTrue(isButtonVisible, "iOS button doesnt displayed on page");
    }

    @Test(dependsOnMethods = {"deviceAndroidButtonCheck"}, alwaysRun = true)
    public void deviceAndroidButtonText() {
        WebElement androidButton = DevicePage.checkAndroidButtonTextCorrect();
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
        String devicesURL = driver.getCurrentUrl();
        boolean isClickable = DevicePage.isDevicesButtonClickable(devicesTypes);
        Assert.assertTrue(isClickable, "Button for device type " + devicesTypes + "isnt clickable");
        if (devicesTypes.equals("Android phone or tablet")) {
            Assert.assertTrue(driver.getCurrentUrl().contains("/prices"), "URL doesnt contains expected part");
            driver.get(devicesURL);
            Assert.assertTrue(isClickable, "IOS button doesnt clickable");
        } else if (devicesTypes.equals("iOS, iPhone, iPad")) {
            Assert.assertTrue(driver.getCurrentUrl().contains("/prices"));
        } else {
            System.out.println("Failed redirect to PricesPage");
        }
    }

    @Test(dependsOnMethods = {"deviceTypeChoosing"}, alwaysRun = true)
    public void pricesCompareURL() {
        boolean isValidURL = PricesPage.compareURL();
        Assert.assertTrue(isValidURL, "Current URL doesnt have expected part");
    }

    @DataProvider(name = "subTittleName")
    public Object[][] subTittleName() {
        return new Object[][]{
                {"1 Month"},
                {"12 Month"},
                {"3 Month"}
        };
    }

    @Test(dataProvider = "subTittleName", dependsOnMethods = {"pricesCompareURL"})
    public void subTypeTittleVisible(String subTittleName) {
        boolean isVisible = PricesPage.subTypeTittleVisible(subTittleName);
        Assert.assertTrue(isVisible, "Subtitle" + subTittleName + "should be visible");
    }

    @Test(dataProvider = "subTittleName", dependsOnMethods = {"subTypeTittleVisible"})
    public void subTypeTittleCorrectText(String subTittleName) {
        String actualText = PricesPage.subTypeTittleCorrectText(subTittleName);
        Assert.assertEquals(actualText, subTittleName, "Subtitle text should be " + subTittleName);
    }

    @Test(dataProvider = "subTittleName", dependsOnMethods = {"subTypeTittleCorrectText"})
    public void pricesFullPackTittleDisplayed(String subTittleName) {
        boolean isVisible = PricesPage.isFullPackTittleVisible(subTittleName);
        Assert.assertTrue(isVisible, "Full pack doenst visible on the page");
    }

    @Test(dataProvider = "subTittleName", dependsOnMethods = {"pricesFullPackTittleDisplayed"}, alwaysRun = true)
    public void pricesFullPackTittleCorrectText(String subTittleName) {
        WebElement fullPack = PricesPage.isFullPackCorrectText(subTittleName);
        Assert.assertEquals(fullPack.getText(), "FULL PACK");
    }

    @Test(dependsOnMethods = {"pricesFullPackTittleCorrectText"})
    public void pricesOneMonthCurrencyCheck() {
        WebElement currency = PricesPage.isOneMonthCurrencyCorrect();
        Assert.assertEquals(currency.getText(), "€");
    }

    @Test(dependsOnMethods = {"pricesOneMonthCurrencyCheck"})
    public void pricesOneMonthPriceDisplaying() {
        boolean isVisible = PricesPage.isOneMonthPriceVisible();
        Assert.assertTrue(isVisible, "Price should be visible on the page");
    }

    @Test(dependsOnMethods = {"pricesOneMonthPriceDisplaying"}, alwaysRun = true)
    public void pricesOneMonthPriceCorrectValue() {
        WebElement duration = PricesPage.isOneMonthPriceCorrectDuration();
        Assert.assertEquals(duration.getText(), "/mo");

    }

    @Test(dependsOnMethods = {"pricesOneMonthPriceDisplaying"}, alwaysRun = true)
    public void isOneMonthPriceOldOfferVisible() {
        boolean isVisible = PricesPage.isOneMonthPriceOldOfferVisible();
        Assert.assertTrue(isVisible, "Old offer should be visible on the page");
    }

    @Test(dependsOnMethods = {"isOneMonthPriceOldOfferVisible"})
    public void pricesOneMonthPriceCorrectOldOffer() {
        WebElement oldOffer = PricesPage.isOneMonthPriceCorrectOldOfferValue();
        Assert.assertEquals(oldOffer.getText(), "€ 59.99 /mo");

    }

    @Test(dependsOnMethods = {"pricesOneMonthPriceCorrectValue"}, alwaysRun = true)
    public void pricesOneMonthTryNowButtonDisplaying() {
        boolean isVisible = PricesPage.isOneMonthTryNowButtonVisible();
        Assert.assertTrue(isVisible, "Try Now button should be visible on the page");
    }

    @Test(dependsOnMethods = {"pricesOneMonthTryNowButtonDisplaying"})
    public void pricesOneMonthTryNowButtonCorrectText() {
        WebElement oneTryNow = PricesPage.isOneMonthTryNowButtonCorrectText();
        Assert.assertEquals(oneTryNow.getText(), "TRY NOW");

    }
    @DataProvider(name = "SubscriptionValue")
    public Object[][] SubscriptionValue(){
        return new Object[][]{
                {"49.99"},
                {"29.99"},
                {"12.49"}
        };
    }

    @Test (dataProvider = "SubscriptionValue", dependsOnMethods = "pricesOneMonthTryNowButtonCorrectText")
    public void subscriptionPricesVisibility(String price){
        boolean isVisible = PricesPage.isPriceVisible(price);
        Assert.assertTrue(isVisible, "Expected price doesnt visible");
    }

    @DataProvider(name = "subscriptionsTypes")
    public Object[][] subscriptionsTypes() {
        return new Object[][]{
                {"um_mf1_50"},
                {"um_mf3_90"},
                {"um_mf12_150"}
        };
    }

    @Test(dataProvider = "subscriptionsTypes", dependsOnMethods = {"subscriptionPricesVisibility"})
    public void tryNowSubButtonClick(String subscriptionsTypes) {
        //driver.get("https://umobix.com/prices.html");
        String currentURL = driver.getCurrentUrl();
        boolean isClickable = PricesPage.isTryNowButtonClickable(subscriptionsTypes);
        Assert.assertTrue(isClickable, "Button for subscription type " + subscriptionsTypes + "isn't clickable");
        Assert.assertTrue(PricesPage.isCheckoutURLCorrect(), "URL doesnt have expected part");
        if (!subscriptionsTypes.equals("um_mf12_150")) {
            driver.get(currentURL);
        } else System.out.println("Error. Product id wasnt chosen");
    }

    @Test(dependsOnMethods = {"tryNowSubButtonClick"}, alwaysRun = true)
    public void checkoutURLCompare() {
        boolean checkoutURL = PricesPage.isCheckoutURLCorrect();
        Assert.assertTrue(checkoutURL, "Checkout URL doesnt have expected part");
    }


//12
}
