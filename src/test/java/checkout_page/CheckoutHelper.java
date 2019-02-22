package checkout_page;

import _app_manager.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


/**
 * This class contains methods to perform different actions or/and get data from the Checkout page
 */
public class CheckoutHelper extends Utils {
    private static final By CHECK_OUT_PAGE_HEADER_LOCATOR = By.xpath("//h1[contains(text(), 'Checkout')]");




    public CheckoutHelper(WebDriver wd) {
        super(wd);
    }



    public String getCheckOutPageHeader(){
        return getText(CHECK_OUT_PAGE_HEADER_LOCATOR);
    }
}
