package zz_tests;

import _app_manager.TestBase;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class MealsGuiTests extends TestBase {


    /**
     * This test verifies that all groups of meals are present on the page
     */
    @Test
    public void testMealsGroups() {
        pm.getPageNavigationHelper().clickOnMealsTab();
        List<String> listOfMealsExpected = pm.getMealsRequirements().requiredListOfMeals();
        List<String> listOfMealsActualInUi = pm.getMealsHelper().getListOfMeals();

        assertEquals(listOfMealsActualInUi, listOfMealsExpected);
    }



}
