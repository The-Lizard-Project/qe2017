package pl.lizardproject.qe2017;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.lizardproject.qe2017.itemlist.ItemListActivity;
import pl.lizardproject.qe2017.pages.ItemListPage;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class Exercise1 {

    @Rule
    public ActivityTestRule<ItemListActivity> itemListActivityActivityTestRule = new ActivityTestRule<>(ItemListActivity.class);

    /* TODO TASK 1
     * Use methods available via Espresso: onView(), withId(), perform(), click()
     *
     * 1. Find button that adds item
     * 2. Click on it
     *
     * 3. Move to the previous screen(hint: use pressBack())
    */
    @Test
    public void openAddItemScreen() {
        //TODO 1,2
        onView(withId(R.id.fabAdd)).perform(click());
        onView(withId(R.id.fabSave)).check(matches(isDisplayed())); // validation (leave)

        //TODO 3
        pressBack(); // hide keyboard
        pressBack();
        onView(withId(R.id.fabAdd)).check(matches(isDisplayed())); // validation (leave)
    }

    /* TODO TASK 2
     * Use selected methods from task 1 and new ones: typeText(), closeSoftKeyboard(), check(), matches(), isDisplayed()
     *
     * 1. Find button that adds item
     * 2. Click on it
     * 3. Verify if user is on the right activity
     *
     * 4. Find edit text item
     * 5. Type text there and close keyboard
     * 6. Save added item
     *
     * 7. Verify if user was moved to the right activity
     * 8. Verify if proper item was added
     *
     */
    @Test
    public void addItem() {

        ItemListPage itemListPage = new ItemListPage();
        itemListPage.clearList();

        // remove all below

        // TODO 1,2,3
        onView(withId(R.id.fabAdd)).perform(click());
        onView(withId(R.id.newItemEditText)).check(matches(isDisplayed()));

        // TODO 4,5,6
        onView(withId(R.id.newItemEditText)).perform(typeText("ziemniaki"), closeSoftKeyboard());
        onView(withId(R.id.fabSave)).perform(click());

        // TODO 7,8
        onView(withId(R.id.text)).check(matches(withText("ziemniaki")));

    }

    /* TODO TASK 3
     * Use knowledge and selected methods from previous tasks and new ones: allOf(), withText()
     *
     * Add more than one item and verify
     *
     */
    @Test
    public void addMoreThanOneItem() {

        ItemListPage itemListPage = new ItemListPage();
        itemListPage.clearList();

        // remove all below

        // on the Product List click Add Button
        onView(withId(R.id.fabAdd)).perform(click());

        // On New Item screen type product name...
        onView(withId(R.id.newItemEditText)).perform(typeText("ziemniaki"), closeSoftKeyboard());

        // ...and Save
        onView(withId(R.id.fabSave)).perform(click());

        // Verify if Item was added
        onView(withId(R.id.text)).check(matches(withText("ziemniaki")));

        // Add second element
        onView(withId(R.id.fabAdd)).perform(click());
        onView(withId(R.id.newItemEditText)).perform(typeText("buraki"), closeSoftKeyboard());
        onView(withId(R.id.fabSave)).perform(click());

        // Validate if it was added

        // wrong solution
        //onView(withId(R.id.text)).check(matches(withText("buraki")));

        // good solution
        onView(allOf(withId(R.id.text), withText("buraki"))).check(matches(isDisplayed()));

    }

    /* TODO TASK 4 for volunteers
     * Using chosen methods from previous tasks and Google :)
     *
     * Add more than one item with the same name but with different type or priority, then verify
     *
     */
    @Test
    public void addTheSameItemNameMoreThanOnce() {
        //first item
        onView(withId(R.id.fabAdd)).perform(click());
        onView(withId(R.id.newItemEditText)).perform(typeText("buraki"), closeSoftKeyboard());
        onView(withId(R.id.category_spinner)).perform(click());
        onView(withText("other")).perform(click());
        onView(withId(R.id.fabSave)).perform(click());

        //second one
        onView(withId(R.id.fabAdd)).perform(click());
        onView(withId(R.id.newItemEditText)).perform(typeText("buraki"), closeSoftKeyboard());
        onView(withId(R.id.priority_spinner)).perform(click());
        onView(withText("critical")).perform(click());
        onView(withId(R.id.fabSave)).perform(click());

        onView(allOf(withId(R.id.text), withText("buraki"), hasSibling(withText("Category: other")))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.text), withText("buraki"), hasSibling(withText("Priority: critical")))).check(matches(isDisplayed()));
    }

    // TODO Task 5
    // based on DRY pattern create methods that are common for TASKS 1 - 3 and then use this methods inside them
}
