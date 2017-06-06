package pl.lizardproject.qe2017.helpers;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public final class ActionHelper {
    public static void clickOnId(int id) {
        onView(withId(id)).check(matches(isDisplayed()));
        onView(withId(id)).check(matches(isClickable()));
        onView(withId(id)).perform(click());
    }
}
