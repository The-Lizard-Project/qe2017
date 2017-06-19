package pl.lizardproject.qe2017.test.screen;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.lizardproject.qe2017.MyApplication;
import pl.lizardproject.qe2017.database.DatabaseFacade;
import pl.lizardproject.qe2017.database.model.DbUserEntity;
import pl.lizardproject.qe2017.itemlist.ItemListActivity;
import pl.lizardproject.qe2017.model.Category;
import pl.lizardproject.qe2017.model.Priority;
import pl.lizardproject.qe2017.model.User;
import pl.lizardproject.qe2017.pageobject.ItemListPageObject;
import pl.lizardproject.qe2017.session.UserSession;

/*
 * Item list screen test
 */
@RunWith(AndroidJUnit4.class)
public class Exercise4 {

    @Rule public ActivityTestRule<ItemListActivity> activityTestRule = new ActivityTestRule<>(ItemListActivity.class);

    private DatabaseFacade databaseFacade;
    private DbUserEntity dbUser;

    @Before
    public void setUp() {
        databaseFacade = ((MyApplication) activityTestRule.getActivity().getApplication()).getDatabaseFacade();

        //todo: fix mock user
        UserSession userSession = ((MyApplication) activityTestRule.getActivity().getApplication()).getUserSession();

        // todo: move to sessionhelper
        dbUser = new ItemListPageObject().addUserToDatabase("user", "pass", databaseFacade);
        userSession.start(new User("user", "pass", dbUser.getId()));
    }

    @After
    public void tearDown() {
        databaseFacade.drop();
    }

    /* TODO TASK 1
     *
     * 1. Validate if the screen is opened
     *
    */
    @Test
    public void validateScreen() {
        new ItemListPageObject()
                .validate();
    }

    /* TODO TASK 2
     *
     * 1, Click on add button
     * 2. Validate if the screen is opened
     *
    */
    @Test
    public void openAddItemScreen() {
        new ItemListPageObject()
                .openAddItemScreen()
                .validate();
    }

    /* TODO TASK 3
     *
     * 1, Add item to database - use addItemToDatabase method from PageObject
     * 2. Validate if the screen is opened
     *
    */
    @Test
    public void removeItem() {
        String itemName = "new item";
        Category itemCategory = Category.FRUITS;
        Priority itemPriority = Priority.NORMAL;
        boolean isChecked = false;

        new ItemListPageObject()
                .addItemToDatabase(itemName, itemCategory, itemPriority, isChecked, dbUser, databaseFacade)
                .removeItem(itemName)
                .validateItemExists(itemName, itemCategory, itemPriority, isChecked);
    }
}