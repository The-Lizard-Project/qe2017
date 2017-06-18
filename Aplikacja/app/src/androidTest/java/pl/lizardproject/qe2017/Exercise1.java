package pl.lizardproject.qe2017;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.lizardproject.qe2017.login.LoginActivity;

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
public class Exercise1 {

    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Before
    public void setUp() {
        ((MyApplication) activityTestRule.getActivity().getApplication()).getDatabaseFacade().drop();
    }

    /* TODO TASK 1
     *
     * 1. Find register button
     * 2. Click on it
     * 3. Validate if the screen is opened
     * 4. Move to the previous screen
     * 5. Validate if the screen is opened
     *
     * Methods: onView(), withId(), perform(), click(), check(), matches(), isDisplayed(), pressBack()
    */
    @Test
    public void openRegisterScreen() {
        //TODO 1,2,3
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.newUsernameEditText)).check(matches(isDisplayed()));

        //TODO 4,5
        pressBack();
        onView(withId(R.id.usernameEditText)).check(matches(isDisplayed()));
    }

    /* TODO TASK 2
     *
     * 1. Click on register button
     * 2. Validate if the register screen is opened
     * 3. Type username
     * 4. Type password
     * 5. Click on register button
     * 6. Validate if the item list screen is opened
     *
     * New methods: typeText()
    */
    @Test
    public void registerUser() {
        //TODO 1,2
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.newUsernameEditText)).check(matches(isDisplayed()));

        //TODO 3,4,5
        onView(withId(R.id.newUsernameEditText)).perform(typeText("user"));
        onView(withId(R.id.newPasswordEditText)).perform(typeText("pass"));
        onView(withId(R.id.registerButton)).perform(click());

        //TODO 6
        onView(withId(R.id.fabAdd)).check(matches(isDisplayed()));
    }

    /* TODO TASK 3
     *
     * 1. Click on register button
     * 2. Validate if the register screen is opened
     * 3. Type username
     * 4. Type password
     * 5. Click on register button
     * 6. Validate if the item list screen is opened
     * 7. Click on add item button
     * 8. Validate if the add item screen is opened
     * 9. Type item name
     * 10. Click save button
     * 11. Verify if item is added
     *
     * New methods: closeSoftKeyboard()
     *
     */
    @Test
    public void addItem() {
        //TODO 1,2
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.newUsernameEditText)).check(matches(isDisplayed()));

        //TODO 3,4,5,6
        onView(withId(R.id.newUsernameEditText)).perform(typeText("user"));
        onView(withId(R.id.newPasswordEditText)).perform(typeText("pass"));
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.fabAdd)).check(matches(isDisplayed()));

        //TODO 7,8
        onView(withId(R.id.fabAdd)).perform(click());
        onView(withId(R.id.newItemEditText)).check(matches(isDisplayed()));

        // TODO 9,10
        onView(withId(R.id.newItemEditText)).perform(typeText("ziemniaki"), closeSoftKeyboard());
        onView(withId(R.id.fabSave)).perform(click());

        // TODO 11
        onView(withId(R.id.text)).check(matches(withText("ziemniaki")));
    }

    /* TODO TASK 4
     *
     * 1. Click on register button
     * 2. Validate if the register screen is opened
     * 3. Type username
     * 4. Type password
     * 5. Click on register button
     * 6. Validate if the item list screen is opened
     * 7. Click on add item button
     * 8. Validate if the add item screen is opened
     * 9. Type item name
     * 10. Click on category spinner
     * 11. Click on chosen category
     * 12. Click save button
     * 13. Click on add item button
     * 14. Validate if the add item screen is opened
     * 15. Type item name
     * 16. Click on priority spinner
     * 17. Click on chosen priority
     * 18. Click save button
     * 19. Verify if items are added
     */
    @Test
    public void addTheSameItemNameTwice() {
        //TODO 1,2
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.newUsernameEditText)).check(matches(isDisplayed()));

        //TODO 3,4,5,6
        onView(withId(R.id.newUsernameEditText)).perform(typeText("user"));
        onView(withId(R.id.newPasswordEditText)).perform(typeText("pass"));
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.fabAdd)).check(matches(isDisplayed()));

        //TODO 7,8,9,10,11,12
        onView(withId(R.id.fabAdd)).perform(click());
        onView(withId(R.id.newItemEditText)).check(matches(isDisplayed()));
        onView(withId(R.id.newItemEditText)).perform(typeText("buraki"), closeSoftKeyboard());
        onView(withId(R.id.category_spinner)).perform(click());
        onView(withText("other")).perform(click());
        onView(withId(R.id.fabSave)).perform(click());

        //TODO 13,14,15,16,17,18
        onView(withId(R.id.fabAdd)).perform(click());
        onView(withId(R.id.newItemEditText)).check(matches(isDisplayed()));
        onView(withId(R.id.newItemEditText)).perform(typeText("buraki"), closeSoftKeyboard());
        onView(withId(R.id.priority_spinner)).perform(click());
        onView(withText("critical")).perform(click());
        onView(withId(R.id.fabSave)).perform(click());

        //TODO 19
        onView(allOf(withId(R.id.text), withText("buraki"), hasSibling(withText("Category: other")))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.text), withText("buraki"), hasSibling(withText("Priority: critical")))).check(matches(isDisplayed()));
    }

    // TODO Task 5
    // based on DRY pattern create methods that are common for TASKS 1 - 3 and then use this methods inside them
}