package pl.lizardproject.qe2017.pageobject;

import android.support.test.espresso.ViewInteraction;

import pl.lizardproject.qe2017.R;
import pl.lizardproject.qe2017.model.Category;
import pl.lizardproject.qe2017.model.Priority;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class EditItemPageObject {

    private final ViewInteraction itemNameEditText;
    private final ViewInteraction categorySpinner;
    private final ViewInteraction prioritySpinner;
    private final ViewInteraction saveItemFab;

    public EditItemPageObject() {
        itemNameEditText = onView(withId(R.id.newItemEditText));
        categorySpinner = onView(withId(R.id.category_spinner));
        prioritySpinner = onView(withId(R.id.priority_spinner));
        saveItemFab = onView(withId(R.id.fabSave));
    }

    public ItemListPageObject saveItem(String name, Category category, Priority priority) {
        itemNameEditText.perform(clearText(), typeText(name));

        categorySpinner.perform(click());
        onView(withText(category.name().toLowerCase())).perform(click());

        prioritySpinner.perform(click());
        onView(withText(priority.name().toLowerCase())).perform(click());

        saveItemFab.perform(click());

        return new ItemListPageObject();
    }

    public EditItemPageObject validate(String itemName, Category itemCategory, Priority itemPriority) {
        itemNameEditText.check(matches(isDisplayed()));
        itemNameEditText.check(matches(withText(itemName)));
        categorySpinner.check(matches(isDisplayed()));
        categorySpinner.check(matches(withChild(withText(itemCategory.name().toLowerCase()))));
        prioritySpinner.check(matches(isDisplayed()));
        prioritySpinner.check(matches(withChild(withText(itemPriority.name().toLowerCase()))));
        saveItemFab.check(matches(isDisplayed()));

        return this;
    }
}