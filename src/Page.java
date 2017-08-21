import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by d526f on 8/15/2017.
 */
public class Page {
    public void waitForObject(String elementID) {
    }

    public void typeInTextField(WebElement field, String string) {
        field.clear();
        field.sendKeys(string);
    }

    public WebElement waitForElementVisibility(WebDriverWait wait, WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }
}
