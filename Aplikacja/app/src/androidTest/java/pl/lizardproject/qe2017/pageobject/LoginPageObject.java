package pl.lizardproject.qe2017.pageobject;

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

public class LoginPageObject {

    private static final int INPUT_TYPE = 129;

    private final ViewInteraction usernameEditText;
    private final ViewInteraction passwordEditText;
    private final ViewInteraction loginButton;
    private final ViewInteraction registerButton;

    public LoginPageObject() {
        usernameEditText = onView(withId(R.id.usernameEditText));
        passwordEditText = onView(withId(R.id.passwordEditText));
        loginButton = onView(withId(R.id.loginButton));
        registerButton = onView(withId(R.id.registerButton));
    }

    public ItemListPageObject login(String username, String password) {
        usernameEditText.perform(typeText(username));
        passwordEditText.perform(typeText(password));

        loginButton.perform(click());

        return new ItemListPageObject();
    }

    public RegisterPageObject openRegisterScreen() {
        registerButton.perform(click());

        return new RegisterPageObject();
    }


    public LoginPageObject validate() {
        usernameEditText.check(matches(isDisplayed()));
        passwordEditText.check(matches(isDisplayed()));
        passwordEditText.check(matches(withInputType(INPUT_TYPE)));
        loginButton.check(matches(isDisplayed()));
        registerButton.check(matches(isDisplayed()));

        return this;
    }

    ////////////////// For volunteers //////////////////

    public LoginPageObject loginWithError(String username, String password) {
        login(username, password);

        return this;
    }

    public LoginPageObject validateError() {
        onView(withText(R.string.loginError)).check(matches(isDisplayed()));

        return this;
    }
}