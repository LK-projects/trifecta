package zz_tests;

import _app_manager.TestBase;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AlaCarteFunctionalTests extends TestBase {

    /**
     * This test verifies, that you are getting an error message,
     * when clicking on Checkout button if the total is less than $119
     * The assertion is based on having the error message
     * after a user clicked on Checkout button in the cart, when it's total less than $119
     */
    @Test
    public void testOrderMinimumErrorMessageAppears(){
        pm.getPageNavigationHelper().clickOnAlaCarteTab();
        pm.getAlaCarteHelper().clickOnShopNow();
        pm.getAlaCarteHelper().addAnyProteinItem();
        pm.getAlaCarteHelper().clickOnCheckout();
        String expectedOrderMinimumMessage = "Order minimum is $119.00";
        String actualOrderMinimumMessageInUi= pm.getAlaCarteHelper().getOrderMinimumNotification();

        assertEquals(actualOrderMinimumMessageInUi, expectedOrderMinimumMessage);
    }


    /**
     * This test verifies, that you are being transferred to checkout page,
     * when yor cart is filled out with some items and the total is more than $119
     * The assertion is based on having "CHECKOUT" header for the landing page after a user clicked on Checkout button in the cart
     */
    @Test
    public void testCheckoutButton(){
        pm.getPageNavigationHelper().clickOnAlaCarteTab();
        pm.getAlaCarteHelper().cartReadyToCheckoutWithProtein();
        pm.getAlaCarteHelper().clickOnCheckout();
        String expectedCheckOutPageHeader = "CHECKOUT";
        String actualCheckOutPageHeaderInUi= pm.getCheckoutHelper().getCheckOutPageHeader();

        assertEquals(actualCheckOutPageHeaderInUi, expectedCheckOutPageHeader);
    }




}
