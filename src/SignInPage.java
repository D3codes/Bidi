import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by d526f on 8/17/2017.
 */
public class SignInPage extends Page{
    private WebDriver driver;
    private WebDriverWait wait;
    private String url = "https://signin.ebay.com/ws/eBayISAPI.dll?SignIn&ru=https%3A%2F%2Fwww.ebay.com%2F";
    private WebElement emailField, passwordField;
    private By fldElementClass = By.className("fld");
    private By signInButton = By.id("sgnBt");

    public SignInPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        driver.get(url);
        List<WebElement> fldElements = driver.findElements(fldElementClass);
        emailField = fldElements.get(2);
        passwordField = fldElements.get(3);
    }

    public void signIn(String email, String password) {
        System.out.println("Signing in as " + email);
        typeInTextField(emailField, email);
        typeInTextField(passwordField, password);
        driver.findElement(signInButton).click();
    }
}
