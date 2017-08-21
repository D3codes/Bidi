import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by d526f on 8/15/2017.
 */
public class HomePage extends Page{
    private String url = "https://www.ebay.com/";
    private WebDriver driver;
    private WebDriverWait wait;
    private By userGreeting = By.id("gh-ug");
    private By searchBar = By.id("gh-ac");
    private By searchButton = By.id("gh-btn");
    private By advancedSearchButton = By.id("gh-as-a");

    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        driver.get(url);
    }

    public boolean isUserSignedIn() {
        String greeting = driver.findElement(userGreeting).getText();
        System.out.println("Greeting: " + greeting);
        if(greeting.equals("Hi! Sign in or register")) return false;
        return true;
    }

    public void navigateToAdvancedSearch() {
        driver.findElement(advancedSearchButton).click();
    }

    public void search(String keyword) {
        System.out.println("Searching for " + keyword);
        typeInTextField(driver.findElement(searchBar), keyword);
        driver.findElement(searchButton).click();
    }
}
