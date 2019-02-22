package _app_manager;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {

    protected static final ProjectManager pm = new ProjectManager(System.getProperty("browser", BrowserType.CHROME));

    /**
     * This is being run before any test suite
     * @throws Exception
     */
    @BeforeSuite
    public void setUp() throws Exception {
        pm.init();
    }


    /**
     * This is being run after any test suite
     */
    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        pm.stop();
    }
}
