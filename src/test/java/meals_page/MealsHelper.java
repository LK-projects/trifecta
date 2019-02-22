package meals_page;

import _app_manager.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;


/**
 * This class contains methods to perform different actions or/and get data from the Meals page
 */
public class MealsHelper extends Utils {
    private static final By LIST_OF_PLANS_LOCATOR = By.xpath("//div[@class='meals-plans-plan']");
    private static final By LIST_OF_PLAN_NAMES_LOCATOR = By.xpath("//div[@class='meals-plans-plan-inner']/h3");


    public MealsHelper(WebDriver wd) {
        super(wd);
    }


    public List<String> getListOfMeals(){
        List<String> macs = getListOfDropdownValues(LIST_OF_PLAN_NAMES_LOCATOR);
        return macs;
    }





}
