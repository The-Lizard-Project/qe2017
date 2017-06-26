package pl.lizardproject.qe2017.pageobject;

import android.support.test.espresso.ViewInteraction;

import pl.lizardproject.qe2017.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class LoginPageObject {

    private static final int INPUT_TYPE = 129;

    private final ViewInteraction usernameEditText;
//    private final ViewInteraction passwordEditText;
//    private final ViewInteraction loginButton;
//    private final ViewInteraction registerButton;

    public LoginPageObject() {
        usernameEditText = onView(withId(R.id.usernameEditText));
        // initialize all views

    }

    public ItemListPageObject login(String username, String password) {

        return new ItemListPageObject();
    }

    public RegisterPageObject openRegisterScreen() {

        return new RegisterPageObject();
    }


    public LoginPageObject validate() {
        // new method: withInputType

        return this;
    }

    ////////////////// For volunteers //////////////////

    public LoginPageObject loginWithError(String username, String password) {
        // reuse existing method

        return this;
    }

    public LoginPageObject validateError() {
        // new method: withText

        return this;
    }
}