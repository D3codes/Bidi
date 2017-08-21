import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Scanner;

/**
 * Created by d526f on 8/15/2017.
 */
public class Bidi {
    private WebDriver chrome;
    private WebDriverWait wait;
    private final String EMAIL = "email";
    private final String PASSWORD = "password";
    private final int ITEMS_PER_PAGE = 50;

    private String KEYWORD;
    private final String MAX_BID = "0.01";
    private final int ITEMS_TO_SEARCH = 10000;

    public Bidi(String keyword) {
        System.setProperty("webdriver.chrome.driver", "src/Resources/chromedriver.exe");
        chrome = new ChromeDriver();
        wait = new WebDriverWait(chrome, 10);
        KEYWORD = keyword;
    }

    public void start() {
        System.out.println("Beginning search for " + Integer.toString(ITEMS_TO_SEARCH) + " items matching \"" + KEYWORD + "\"");

        HomePage hp = new HomePage(chrome, wait);
        if(!hp.isUserSignedIn()) {
            SignInPage sip = new SignInPage(chrome, wait);
            sip.signIn(EMAIL, PASSWORD);
        }
        hp.search(KEYWORD);

        SearchResultsPage srp = new SearchResultsPage(chrome, wait);
        srp.setMaxPrice(MAX_BID);
        srp.toggleFreeShipping();
        System.out.println("--------------------------------------------------");
        srp.setCurrentResultsPageUrl();
        int numberOfItemsBidOn = 0;
        for(int i = 0; i < ITEMS_TO_SEARCH/ITEMS_PER_PAGE; i++) {
            List<String> resultUrls = srp.getSearchResultUrlsWithNoBids();
            for (String resultUrl : resultUrls) {
                try {
                    ResultPage rp = new ResultPage(chrome, wait, resultUrl);
                    if (rp.isShippingFree() && rp.getCurrentBid().endsWith(MAX_BID) && !rp.isBidOn()) {
                        rp.placeBid(MAX_BID);
                        numberOfItemsBidOn++;
                    }
                } catch (Exception e) {
                    System.out.println("Error Caught");
                    e.printStackTrace();
                }
                System.out.println("--------------------------------------------------");
            }
            srp.goToNextPage();
        }

        System.out.println("Completed search for " + Integer.toString(ITEMS_TO_SEARCH) + " items matching \"" + KEYWORD + "\"");
        System.out.println(Integer.toString(numberOfItemsBidOn) + " bids placed");
    }
}
