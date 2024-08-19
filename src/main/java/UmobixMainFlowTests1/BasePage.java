package UmobixMainFlowTests1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private static final String host = "https://umobix.com";

    public String getFullURL() {
        return host + getPaths().get(0);
    }
    abstract List<String> getPaths();
}