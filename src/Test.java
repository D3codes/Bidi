import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by d526f on 8/15/2017.
 */
public class Test {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src/Resources/chromedriver.exe");
        WebDriver chrome = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(chrome, 10);
        HomePage hp = new HomePage(chrome, wait);
        if(!hp.isUserSignedIn()) {
            SignInPage sip = new SignInPage(chrome, wait);
            sip.signIn("trina.l.howard@gmail.com", "howard31e");
        }
    }
}

//username: trina.l.howard@gmail.com
//password: howard31e