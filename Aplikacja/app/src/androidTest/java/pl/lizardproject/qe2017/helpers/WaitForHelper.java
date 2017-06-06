package pl.lizardproject.qe2017.helpers;

import android.content.res.Resources;
import android.support.test.espresso.NoMatchingViewException;

import junit.framework.AssertionFailedError;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class WaitForHelper {

    private final static int EXCEPTION_TRIES = 10;
    private final static int SLEEP_TIME = 1000;

    private WaitForHelper() {
    }

    public static void waitFor(String text) {

        for (int i = 1; i < EXCEPTION_TRIES; i++) {
            try {
                onView(withText(text)).check(matches(isDisplayed()));
                break;
            } catch (AssertionFailedError | NoMatchingViewException | Resources.NotFoundException ignored) {
                sleep();
            }
        }
        onView(withText(text)).check(matches(isDisplayed()));
    }

    public static void waitFor(int id) {

        for (int i = 1; i < EXCEPTION_TRIES; i++) {
            try {
                onView(withId(id)).check(matches(isDisplayed()));
                break;
            } catch (AssertionFailedError | NoMatchingViewException | Resources.NotFoundException ignored) {
                sleep();
            }
        }
        onView(withId(id)).check(matches(isDisplayed()));
    }

    private static void sleep() {
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}