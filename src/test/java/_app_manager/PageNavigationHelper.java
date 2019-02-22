package _app_manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * This class contains methods to move between tabs / pages
 */
public class PageNavigationHelper extends Utils {
    private static final By MEALS_TAB_LOCATOR = By.xpath("//*[@role='menuitem' and contains(text(), 'MEALS')]");
    private static final By A_LA_CARTE_TAB_LOCATOR = By.xpath("//*[@role='menuitem' and contains(text(), 'A LA CARTE')]");

    public PageNavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void clickOnMealsTab() {
        clickIfPresent(MEALS_TAB_LOCATOR);
    }

    public void clickOnAlaCarteTab() {
        clickIfPresent(A_LA_CARTE_TAB_LOCATOR);
    }
}
