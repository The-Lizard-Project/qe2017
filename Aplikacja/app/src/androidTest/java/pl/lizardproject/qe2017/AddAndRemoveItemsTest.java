package pl.lizardproject.qe2017;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.lizardproject.qe2017.itemlist.ItemListActivity;
import pl.lizardproject.qe2017.pages.AddItemPage;
import pl.lizardproject.qe2017.pages.ItemListPage;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AddAndRemoveItemsTest {
    private ItemListPage itemListPage;

    @Rule
    public ActivityTestRule<ItemListActivity> itemListActivityActivityTestRule = new ActivityTestRule<ItemListActivity>(ItemListActivity.class);

    @Before
    public void before() {
        itemListPage = new ItemListPage();
    }

    @Test
    public void addItems() {
        AddItemPage addItemPage;

        for (int i = 0; i < 5; i++) {
            addItemPage = itemListPage.goToAddItemPage();
            itemListPage = addItemPage.addItem("1");
        }
        for (int i = 0; i < 5; i++) {
            itemListPage = itemListPage.removeItem(0);
        }
    }
}