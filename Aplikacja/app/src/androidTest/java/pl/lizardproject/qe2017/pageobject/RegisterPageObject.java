package pl.lizardproject.qe2017.pageobject;

import android.support.annotation.StringRes;

public class RegisterPageObject {

    private static final int INPUT_TYPE = 129;

//    private final ViewInteraction usernameEditText;
//    private final ViewInteraction passwordEditText;
//    private final ViewInteraction registerButton;

    public RegisterPageObject() {

    }

    public ItemListPageObject createUser(String username, String password) {

        return new ItemListPageObject();
    }


    public RegisterPageObject validate() {

        return this;
    }

    public LoginPageObject goBack() {
        // remember to close keyboard

        return new LoginPageObject();
    }

    ////////////////// For volunteers //////////////////

    public RegisterPageObject createUserWithError(String username, String password) {

        return this;
    }

    public RegisterPageObject validateError(@StringRes int errorTextId) {

        return this;
    }
}