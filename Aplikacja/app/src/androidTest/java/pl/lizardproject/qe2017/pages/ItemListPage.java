package pl.lizardproject.qe2017.pages;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.util.Log;
import android.view.View;

import org.hamcrest.Matcher;

import pl.lizardproject.qe2017.R;
import pl.lizardproject.qe2017.helpers.ActionHelper;
import pl.lizardproject.qe2017.helpers.ActivityHelper;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

public class ItemListPage {
    public ItemListPage() {
        Log.i("Activity: ", ActivityHelper.getActivityName());
    }

    public AddItemPage goToAddItemPage() {
        ActionHelper.clickOnId(R.id.fabAdd);
        return new AddItemPage();
    }

    public int clearList() {
        int i = 0;
        while (true) {
            try {
                removeItem(0);
            } catch (Exception e) {
                break;
            }
            i++;
        }
        return i;
    }

    public ItemListPage removeItem(int position) {
        onView(withId(R.id.recyclerViewList)).perform(RecyclerViewActions.actionOnItemAtPosition(position, new ClickDeleteButton()));
        return new ItemListPage();
    }

    public void assertPageIsOpened() {
        onView(withId(R.id.fabAdd)).check(matches(isDisplayed()));
    }

    public void assertItemExists(String name, String category, String priority) {
        if (category == null) {
            category = "Category: fruits";
        } else {
            category = "Category:" + category;
        }

        if (priority == null) {
            priority = "Priority: normal";
        } else {
            priority = "Priority:" + priority;
        }
        onView(allOf(withId(R.id.text), withText(name), hasSibling(withText(priority)), hasSibling(withText(category)))).check(matches(isDisplayed()));
    }

    public void assertItemExists(String name) {
        onView(allOf(withId(R.id.text), withText(name))).check(matches(isDisplayed()));
    }

    private class ClickDeleteButton implements ViewAction {
        ViewAction click = click();

        @Override
        public Matcher<View> getConstraints() {
            return click.getConstraints();
        }

        @Override
        public String getDescription() {
            return " Click Delete Button";
        }

        @Override
        public void perform(UiController uiController, View view) {
            click.perform(uiController, view.findViewById(R.id.deleteButton));
        }
    }
}