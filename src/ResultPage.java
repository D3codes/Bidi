import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by d526f on 8/17/2017.
 */
public class ResultPage extends Page {
    private WebDriver driver;
    private WebDriverWait wait;
    private By shippingType = By.id("shSummary");
    private By shippingCost = By.id("fshippingCost");
    private By currentBid = By.id("prcIsum_bidPrice");
    private By numberOfBids = By.id("qty-test");
    private By maxBidTextBox = By.id("MaxBidId");
    private By placeBidButton = By.id("bidBtn_btn");
    private By confirmBidButton = By.cssSelector("[id$=reviewBidSec_btn]");

    public ResultPage(WebDriver driver, WebDriverWait wait, String url) {
        this.driver = driver;
        this.wait = wait;
        System.out.println("Navigating to " + url);
        driver.get(url);
    }

    public boolean isShippingFree() {
        String shipping;
        try {
            shipping = waitForElementVisibility(wait, driver.findElement(shippingType)).getText().substring(0,4);
        } catch(Exception e) {
            shipping = waitForElementVisibility(wait, driver.findElement(shippingCost)).getText().substring(0,4);
        }
        System.out.println("Shipping: " + shipping);
        if(shipping.equals("FREE")) return true;
        return false;
    }

    public String getCurrentBid() {
        String bid = driver.findElement(currentBid).getText();
        System.out.println("Current Bid: " + bid);
        return bid;
    }

    public String getNumberOfBids() {
        String bids = driver.findElement(numberOfBids).getText();
        System.out.println("Number of bids: " + bids);
        return bids;
    }

    public boolean isBidOn() {
        if(getNumberOfBids().equals("0")) return false;
        return true;
    }

    public void placeBid(String bid) {
        System.out.println("Placing bid of $" + bid);
        typeInTextField(waitForElementVisibility(wait, driver.findElement(maxBidTextBox)), bid);
        waitForElementVisibility(wait, driver.findElement(placeBidButton)).click();
        waitForElementVisibility(wait, driver.findElement(confirmBidButton)).click();
    }
}
