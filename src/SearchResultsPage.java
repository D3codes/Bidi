import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by d526f on 8/17/2017.
 */
public class SearchResultsPage extends Page{
    private WebDriver driver;
    private WebDriverWait wait;
    private By resultsClass = By.className("vip");
    private By numberOfBids = By.className("lvformat");
    private By freeShippingCheckBox = By.xpath("//span[@class='cbx' and contains(text(), 'Free shipping')]");
    private By maxPriceTextBox = By.xpath("//input[@class='price'][2]");
    private By maxPriceTextBoxAlt = By.xpath("//input[@aria-label='Maximum Value']");
    private By priceSubmitButton = By.xpath("//input[@title='Submit price range']");
    private By nextPageButton = By.className("pagn-next");
    private String currentResultsPage;

    public SearchResultsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void setMaxPrice(String price) {
        System.out.println("Setting maximum price to " + price);
        try {
            typeInTextField(waitForElementVisibility(wait, driver.findElement(maxPriceTextBox)), price);
        } catch(Exception e) {
            typeInTextField(waitForElementVisibility(wait, driver.findElement(maxPriceTextBoxAlt)), price);
        }
        driver.findElement(priceSubmitButton).click();
    }

    public void toggleFreeShipping() {
        System.out.println("Toggling free shipping");
        waitForElementVisibility(wait, driver.findElement(freeShippingCheckBox)).click();
    }

    public List<String> getSearchResultUrlsWithNoBids() {
        List<WebElement> resultElements = driver.findElements(resultsClass);
        List<WebElement> resultBidCounts = driver.findElements(numberOfBids);
        List<String> resultUrls = new ArrayList<String>();
        for(int i = 0; i < resultElements.size(); i++) {
            if(resultBidCounts.get(i).getText().equals("0 bids")) {
                resultUrls.add(resultElements.get(i).getAttribute("href"));
            }
        }
        return resultUrls;
    }

    public void goToNextPage() {
        System.out.println("--------------------------------------------------");
        System.out.println("Navigating to next search result page");
        driver.get(currentResultsPage);
        waitForElementVisibility(wait, driver.findElement(nextPageButton)).click();
        setCurrentResultsPageUrl();
    }

    public void setCurrentResultsPageUrl() {
        System.out.println("Setting current search result page url");
        if(!driver.getCurrentUrl().contains("/sch/")) {
            System.out.println("FAILED: Not currently on a search result page");
        } else {
            currentResultsPage = driver.getCurrentUrl();
            System.out.println("Current result page url set to: " + currentResultsPage);
        }
        System.out.println("--------------------------------------------------");
    }
}
