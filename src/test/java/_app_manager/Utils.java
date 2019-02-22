package _app_manager;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


/**
 * This class contains methods to perform some actions with help of Selenium WebDriver
 */
public class Utils {
    protected static WebDriver wd;


    public Utils(WebDriver wd) {
        this.wd = wd;
    }


    /**
     * This is boolean info about an element (by locator), if it's present
     *
     * @param locator The locator of an element
     * @return True - if the element is present after 100 milliseconds if waiting, otherwise - False
     */
    public boolean isElementPresent(By locator) {
        try {
            wd.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }


    /**
     * This waits for an element (by locator) to be clickable
     *
     * @param locator The locator of an element
     */
    public void waitUntilClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(wd, 1);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }


    /**
     * This clicks on an element by its locator
     *
     * @param locator The locator of an element
     */
    protected void click(By locator) {
        wd.findElement(locator).click();
    }


    /**
     * This checks if an element is present (not blocked) and clicks on it. A special locator, which handles "readonly" is needed
     *
     * @param locator The locator of an element
     */
    protected void clickIfNotBlocked(By locator) {
        if (isElementPresent(locator)) {
            click(locator);
        }
    }


    /**
     * This clicks on an element by its locator, if it's present on the page
     *
     * @param locator The locator of an element
     */
    public void clickIfPresent(By locator) {
        if (isElementPresent(locator)) {
            click(locator);
        }
    }


    /**
     * This method uses Java Script to click on the element by its locator
     *
     * @param locator The locator (xpath, css or other) of the element to be clicked
     * @param wd      Selenium WebDriver object to perform actions on the page
     */
    public static void clickJSE(By locator, WebDriver wd) {
        WebElement element = wd.findElement(locator);
        JavascriptExecutor jse = (JavascriptExecutor) wd;
        jse.executeScript("arguments[0].click();", element);
    }


    /**
     * This scrolls to an element and clicks on it by its locator
     *
     * @param locator
     * @param wd
     */
    public void scrollToElement(By locator, WebDriver wd) {
        WebElement element = wd.findElement(locator);
        Actions actions = new Actions(wd);
        actions.moveToElement(element).build().perform();
    }

    /**
     * This scrolls to an element and clicks on it by its locator
     *
     * @param locator
     * @param wd
     */
    public void scrollToElementAndClick(By locator, WebDriver wd) {
        if (isElementPresent(locator)) {
            WebElement element = wd.findElement(locator);
            Actions actions = new Actions(wd);
            actions.moveToElement(element).click().build().perform();
        }
    }


    /**
     * This performs right click on the element by its locator
     *
     * @param locator
     * @param wd
     */
    public void rightClick(By locator, WebDriver wd) {
        WebElement element = wd.findElement(locator);
        Actions actions = new Actions(wd);
        actions.contextClick(element).build().perform();

    }


    /**
     * This types in a text field
     *
     * @param locator
     * @param text
     */
    protected void type(By locator, String text) {
        wd.findElement(locator).sendKeys(text);
    }


    /**
     * This types in a text field if it's ready
     *
     * @param locator
     * @param text
     */
    protected void clearAndType(By locator, String text) {
//        WebDriverWait wait = new WebDriverWait(wd, 1);
//        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        waitUntilClickable(locator);
        click(locator);
        wd.findElement(locator).clear();
        type(locator, text);
    }


    /**
     * This types in a text field if it's present
     *
     * @param locator
     * @param text
     */
    protected void typeIfPresent(By locator, String text) {
        if (isElementPresent(locator)) {
            wd.findElement(locator).sendKeys(text);
        }
    }


    /**
     * This types in a text field if it's visible
     *
     * @param locator
     * @param text
     */
    protected void typeIfVisible(By locator, String text) {
        WebDriverWait wait = new WebDriverWait(wd, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).click();
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
    }


    /**
     * This is to get text from a selected option in a drop-down
     *
     * @param locator
     * @return
     */
    public String getTextFromDropdown(By locator) {
        WebElement element = wd.findElement(locator);
        Select dropDown = new Select(element);
        String selectedText = dropDown.getFirstSelectedOption().getText();
        return selectedText;
    }


    /**
     * This picks an element from a drop-down by text
     *
     * @param text
     * @param element
     * @throws InterruptedException
     */
    public void selectFromDropdownByVisibleText(String text, WebElement element) {
        Select parties = new Select(element);
        parties.selectByVisibleText(text);
        wd.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
    }


    public void selectFromDropdownByVisibleText(By locator, String text) {
        Select parties = new Select(wd.findElement(locator));
        parties.selectByVisibleText(text);
        wd.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
    }

    /**
     * This selects an option from a regular "select" type drop-down
     *
     * @param locator
     * @param numberOfOptionInDropdown
     * @throws InterruptedException
     */
    public void selectFromDropdownByIndex(By locator, int numberOfOptionInDropdown) {
        Select optionsInDropDown = new Select(wd.findElement(locator));
        optionsInDropDown.selectByIndex(numberOfOptionInDropdown);
        wd.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
    }


    /**
     * This provides a list of options from drop-down if it is "select" element
     *
     * @param locator
     * @return
     */
    public List<String> getListOfSelectDropdownOptions(By locator) {
        List<String> options = new ArrayList<String>();
        for (WebElement option : new Select(wd.findElement(locator)).getOptions()) {
            // String txt = option.getText();
            if (option.getAttribute("value") != "")
                options.add(option.getText());
        }
        return options;
    }

    /**
     * This provides a list of options from drop-down if it is NOT "select" element
     *
     * @param locator
     * @return
     */
    public List<String> getListOfDropdownValues(By locator) {
        List<String> values = new ArrayList<String>();
        List<WebElement> elements = wd.findElements(locator);
        for (WebElement element : elements) {
            values.add(element.getText());
        }
        return values;
    }


    /**
     * This selects a random value from the list of any elements, not only "select"
     *
     * @param locator
     * @throws InterruptedException
     */
    public void clickOnRandomOptionFromDropDown(By locator) throws InterruptedException {
        List<WebElement> itemsInDropdown = wd.findElements(locator);
        itemsInDropdown.get(new Random().nextInt(itemsInDropdown.size())).click();
        Thread.sleep(500);
    }

    /**
     * This selects an element from the list of any elements, not only "select"
     *
     * @param locator
     * @param optionNumber
     * @throws InterruptedException
     */

    public void clickOnOptionFromDropDownByIndex(By locator, int optionNumber) throws InterruptedException {
        List<WebElement> itemsInDropdown = wd.findElements(locator);
        itemsInDropdown.get(optionNumber).click();
        Thread.sleep(500);
    }


    /**
     * This gets a regular text by a locator
     */
    public String getText(By locator) {
        return wd.findElement(locator).getText();
    }


    /**
     * This gets a value of an element
     *
     * @param locator
     * @return
     */
    public String getValue(By locator) {
        return wd.findElement(locator).getAttribute("value");
    }


    /**
     * This gets a hidden in HTML text by an element's value
     *
     * @param locator
     * @return
     */
    public String getTextFromValue(By locator) {
        WebElement element = wd.findElement(locator);
        String textFromValue = element.getAttribute("value");
        return textFromValue;
    }


    /**
     * This gets a hidden in HTML text by an element's value with help of JavascriptExecutor
     *
     * @param locator
     * @param wd
     * @return
     */
    public static String getTextByJavaScript(By locator, WebDriver wd) {
        WebElement element = wd.findElement(locator);
        JavascriptExecutor jse = (JavascriptExecutor) wd;
        String text = String.valueOf(jse.executeScript("return arguments[0].value;", element));
        return text;
    }


}
