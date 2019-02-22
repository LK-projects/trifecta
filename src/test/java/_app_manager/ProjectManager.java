package _app_manager;

import a_la_carte_page.AlaCarteHelper;
import checkout_page.CheckoutHelper;
import meals_page.MealsHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import requirements_static_data.MealsRequirementsStaticData;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


/**
 * This class contains the main information to initialize other classes in TestBase
 */
public class ProjectManager {
    WebDriver wd;
    private String browser;
    private ProjectProperties pp;
    private PageNavigationHelper pageNavigationHelper;
    private MealsRequirementsStaticData mealsRequirements;
    private MealsHelper mealsHelper;
    private AlaCarteHelper alaCarteHelper;
    private CheckoutHelper checkoutHelper;


    public ProjectManager(String browser) {
        this.browser = browser;
    }



    /**
     * This method initialize all the connections
     * @throws IOException
     */
    public void init() throws IOException {
        pp = new ProjectProperties();


        if (browser.equals(BrowserType.FIREFOX)) {
            wd = new FirefoxDriver();
        } else if (browser.equals(BrowserType.CHROME)) {
            wd = new ChromeDriver();
        } else if (browser.equals(BrowserType.IE)) {
            wd = new InternetExplorerDriver();
        }
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wd.manage().window().maximize();
        pageNavigationHelper = new PageNavigationHelper(wd);
        mealsRequirements = new MealsRequirementsStaticData();
        mealsHelper = new MealsHelper(wd);
        alaCarteHelper = new AlaCarteHelper(wd);
        checkoutHelper = new CheckoutHelper(wd);


        wd.get(pp.getBaseUrl());

    }

    /**
     * This method cancels all the connections
     */
    public void stop() {
        wd.quit();
    }

    public ProjectProperties getPp() {
        return pp;
    }

    public PageNavigationHelper getPageNavigationHelper() {
        return pageNavigationHelper;
    }

    public MealsRequirementsStaticData getMealsRequirements() {
        return mealsRequirements;
    }

    public MealsHelper getMealsHelper() {
        return mealsHelper;
    }


    public AlaCarteHelper getAlaCarteHelper() {
        return alaCarteHelper;
    }

    public CheckoutHelper getCheckoutHelper() {
        return checkoutHelper;
    }

}
