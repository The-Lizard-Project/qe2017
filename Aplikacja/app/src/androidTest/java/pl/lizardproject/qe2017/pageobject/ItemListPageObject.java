package pl.lizardproject.qe2017.pageobject;

import android.support.test.espresso.ViewInteraction;
import android.view.View;

import org.hamcrest.Matcher;

import pl.lizardproject.qe2017.R;
import pl.lizardproject.qe2017.model.Category;
import pl.lizardproject.qe2017.model.Priority;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

public class ItemListPageObject {
//    private final ViewInteraction itemList;
//    private final ViewInteraction addItemFab;

    public ItemListPageObject() {

    }

    public EditItemPageObject openAddItemScreen() {

        return new EditItemPageObject();
    }

    public EditItemPageObject clickOnItem(String itemName) {
        // new methods: withChild, RecyclerViewActions.actionOnItem

        return new EditItemPageObject();
    }

    public ItemListPageObject removeItem(String itemName) {
        // add code to CustomViewActions - clickOnChild

        return this;
    }

    public ItemListPageObject checkItem(String itemName) {

        return this;
    }

    public ItemListPageObject validateItemExists(String name, Category category, Priority priority, boolean isChecked) {
        // use getItemView method

        return this;
    }

    public ItemListPageObject validateItemNotExists(String name, Category category, Priority priority, boolean isChecked) {
        // use getItemView method
        // new method: doesNotExist

        return this;
    }

    public ItemListPageObject validate() {

        return this;
    }

    private ViewInteraction getItemView(String name, Category category, Priority priority, boolean isChecked) {
        String categoryString = "Category: " + category.name().toLowerCase();
        String priorityString = "Priority: " + priority.name().toLowerCase();

        Matcher<View> checkedMatcher;
        if (isChecked) {
            checkedMatcher = isChecked();
        } else {
            checkedMatcher = isNotChecked();
        }

        return onView(allOf(withId(R.id.checkbox), hasSibling(withText(name)), hasSibling(withText(categoryString)), hasSibling(withText(priorityString)), hasSibling(checkedMatcher)));
    }
}