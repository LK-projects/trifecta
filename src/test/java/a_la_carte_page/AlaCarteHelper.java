package a_la_carte_page;

import _app_manager.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;


/**
 * This class contains methods to perform different actions or/and get data from the A LA CARTE page
 */
public class AlaCarteHelper extends Utils {
    private static final By SHOP_NOW_BUTTON_LOCATOR = By.xpath("//a[@class='alacarte-but' and contains(text(), 'Shop')]");
    private static final By ADD_ANY_PROTEIN_BUTTON_LOCATOR = By.xpath("//button[contains(@id,'protein') and contains(@id, 'add-btn')]");
    private static final By CHECKOUT_BUTTON_LOCATOR = By.xpath("//button[@id='ts-ps-save-btn']");
    private static final By ORDER_MINIMUM_NOTIFICATION_LOCATOR = By.xpath("//div[@class='notyf__wrapper']");
    private static final By ADD_BUTTON_IN_CART_LOCATOR = By.xpath("//div[contains(@id, 'cart-item')]//button[contains(@id, 'qtyAdd')]");
    private static final By CART_TOTAL_LOCATOR = By.cssSelector("span[id='tf-ps-cart-total']");


    public AlaCarteHelper(WebDriver wd) {
        super(wd);
    }

    public void clickOnShopNow() {
        scrollToElementAndClick(SHOP_NOW_BUTTON_LOCATOR, wd);
    }

    public void addAnyProteinItem() {
        wd.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        click(ADD_ANY_PROTEIN_BUTTON_LOCATOR);
        }

    public void clickOnCheckout() {
        click(CHECKOUT_BUTTON_LOCATOR);
        wd.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    public String getOrderMinimumNotification() {
        return getText(ORDER_MINIMUM_NOTIFICATION_LOCATOR);
    }


    public void clickOnAddInCart(){
        click(ADD_BUTTON_IN_CART_LOCATOR);
    }

    public int getCartTotal(){
        return Integer.parseInt(getText(CART_TOTAL_LOCATOR).replaceAll("[^\\d]", "" ));
    }

    public void addCartItemsUpToMinimum(){
        do{
           clickOnAddInCart();
        }
        while (getCartTotal() < 11900);
    }

    public void cartReadyToCheckoutWithProtein(){
        clickOnShopNow();
        addAnyProteinItem();
        addCartItemsUpToMinimum();
        }



}
