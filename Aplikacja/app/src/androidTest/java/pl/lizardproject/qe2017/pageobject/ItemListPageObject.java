package pl.lizardproject.qe2017.pageobject;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.view.View;

import org.hamcrest.Matcher;

import pl.lizardproject.qe2017.R;
import pl.lizardproject.qe2017.database.DatabaseFacade;
import pl.lizardproject.qe2017.database.model.DbItemEntity;
import pl.lizardproject.qe2017.database.model.DbUserEntity;
import pl.lizardproject.qe2017.model.Category;
import pl.lizardproject.qe2017.model.Priority;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static pl.lizardproject.qe2017.util.action.CustomViewActions.clickOnChild;

public class ItemListPageObject {
    private final ViewInteraction itemList;
    private final ViewInteraction addItemFab;

    public ItemListPageObject() {
        itemList = onView(withId(R.id.recyclerViewList));
        addItemFab = onView(withId(R.id.fabAdd));
    }

    public EditItemPageObject openAddItemScreen() {
        addItemFab.perform(click());

        return new EditItemPageObject();
    }

    public ItemListPageObject removeItem(String itemName) {
        itemList.perform(RecyclerViewActions.actionOnItem(withChild(withText(itemName)), clickOnChild(R.id.deleteButton)));

        return this;
    }

    public ItemListPageObject checkItem(String itemName) {
        itemList.perform(RecyclerViewActions.actionOnItem(withChild(withText(itemName)), clickOnChild(R.id.checkbox)));

        return this;
    }

    public void validateItemExists(String name, Category category, Priority priority, boolean isChecked) {
        getItemView(name, category, priority, isChecked).check(matches(isDisplayed()));
    }

    public void validateItemNotExists(String name, Category category, Priority priority, boolean isChecked) {
        getItemView(name, category, priority, isChecked).check(doesNotExist());
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

    public ItemListPageObject validate() {
        itemList.check(matches(isDisplayed()));
        addItemFab.check(matches(isDisplayed()));

        return this;
    }

    public ItemListPageObject addItemToDatabase(String name, Category category, Priority priority, boolean isChecked, DbUserEntity user, DatabaseFacade databaseFacade) {
        DbItemEntity item = new DbItemEntity();
        item.setName(name);
        item.setCategory(category);
        item.setPriority(priority);
        item.setChecked(isChecked);
        item.setUser(user);

        databaseFacade.saveItem(item)
                .toBlocking()
                .value();

        return this;
    }

    public DbUserEntity addUserToDatabase(String username, String password, DatabaseFacade databaseFacade) {
        DbUserEntity user = new DbUserEntity();
        user.setName(username);
        user.setPassword(password);

        databaseFacade.saveUser(user)
                .toBlocking()
                .value();

        return user;
    }
}