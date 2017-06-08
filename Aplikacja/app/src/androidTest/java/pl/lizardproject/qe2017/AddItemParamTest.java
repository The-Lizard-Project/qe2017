package pl.lizardproject.qe2017;

import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import pl.lizardproject.qe2017.itemlist.ItemListActivity;
import pl.lizardproject.qe2017.pages.AddItemPage;
import pl.lizardproject.qe2017.pages.ItemListPage;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(Parameterized.class)
@SmallTest
public class AddItemParamTest {

    private final String operand;

    private ItemListPage itemListPage;

    @Rule
    public ActivityTestRule<ItemListActivity> itemListActivityActivityTestRule = new ActivityTestRule<>(ItemListActivity.class);

    public AddItemParamTest(String operand) {
        this.operand = operand;
    }

    @Parameterized.Parameters(name = "{index}: {0}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"ziemniaki"},
                {"buraki"},
                {"crash"},
                {"pieczarki"},
                {"kasztany"},
        });
    }

    @Before
    public void before() {
        itemListPage = new ItemListPage();
    }

    @Test
    public void addItem() {
        AddItemPage addItemPage;

        addItemPage = itemListPage.goToAddItemPage();
        itemListPage = addItemPage.addItem(operand);
        onView(allOf(hasSibling(withText(operand)), withId(R.id.deleteButton))).perform(click());
    }
}