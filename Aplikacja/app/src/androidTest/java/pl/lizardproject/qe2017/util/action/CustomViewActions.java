package pl.lizardproject.qe2017.util.action;

import android.support.annotation.IdRes;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.view.View;

import org.hamcrest.Matcher;

import static android.support.test.espresso.action.ViewActions.click;

public final class CustomViewActions {

    public static ViewAction clickOnChild(@IdRes final int childId) {
        return new ViewAction() {

            @Override
            public void perform(UiController uiController, View view) {
                View viewToClick = view.findViewById(childId);
                click().perform(uiController, viewToClick);
            }

            @Override
            public String getDescription() {
                return "Click on child";
            }

            @Override
            public Matcher<View> getConstraints() {
                return ViewMatchers.isAssignableFrom(View.class);
            }
        };
    }
}