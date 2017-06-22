package pl.lizardproject.qe2017.pageobject;

import android.support.annotation.StringRes;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewInteraction;

import pl.lizardproject.qe2017.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withInputType;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class RegisterPageObject {

    private static final int INPUT_TYPE = 129;

    private final ViewInteraction usernameEditText;
    private final ViewInteraction passwordEditText;
    private final ViewInteraction registerButton;

    public RegisterPageObject() {
        usernameEditText = onView(withId(R.id.newUsernameEditText));
        passwordEditText = onView(withId(R.id.newPasswordEditText));
        registerButton = onView(withId(R.id.registerButton));
    }

    public ItemListPageObject createUser(String username, String password) {
        usernameEditText.perform(typeText(username));
        passwordEditText.perform(typeText(password));

        registerButton.perform(click());

        return new ItemListPageObject();
    }


    public RegisterPageObject validate() {
        usernameEditText.check(matches(isDisplayed()));
        passwordEditText.check(matches(isDisplayed()));
        passwordEditText.check(matches(withInputType(INPUT_TYPE)));
        registerButton.check(matches(isDisplayed()));

        return this;
    }

    public LoginPageObject goBack() {
        Espresso.closeSoftKeyboard();
        Espresso.pressBack();

        return new LoginPageObject();
    }

    ////////////////// For volunteers //////////////////

    public RegisterPageObject createUserWithError(String username, String password) {
        createUser(username, password);

        return this;
    }

    public RegisterPageObject validateError(@StringRes int errorTextId) {
        onView(withText(errorTextId)).check(matches(isDisplayed()));

        return this;
    }
}