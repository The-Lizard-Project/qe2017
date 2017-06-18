package pl.lizardproject.qe2017;

import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import pl.lizardproject.qe2017.itemlist.ItemListActivity;
import pl.lizardproject.qe2017.model.Category;
import pl.lizardproject.qe2017.model.Priority;
import pl.lizardproject.qe2017.pages.AddItemPage;
import pl.lizardproject.qe2017.pages.ItemListPage;

public class OldExercise2 {
    @Rule
    public ActivityTestRule<ItemListActivity> itemListActivityActivityTestRule = new ActivityTestRule<>(ItemListActivity.class);

    private ItemListPage itemListPage;

    @Before
    public void prepareActivity() {
        itemListPage = new ItemListPage();
        itemListPage.clearList();
    }

    /* TODO Task1
     * Use knowledge gained in previous exercise and code from the last task(Exercise 1, task 5)
     * to create simple page objects of the following activities AddItemPage and ItemListPage
     *
     * Use them to implement task 3 (task 4 is optional).
    */
    @Test
    public void openAddItemScreen() {
        AddItemPage addItemPage;
        addItemPage = itemListPage.goToAddItemPage();
        addItemPage.assertPageIsOpened();
    }

    @Test
    public void addItem() {
        AddItemPage addItemPage;
        addItemPage = itemListPage.goToAddItemPage();
        itemListPage = addItemPage.addItem("gruszki");
        itemListPage.assertItemExists("gruszki");
    }

    @Test
    public void addMoreThanOneItem() {
        AddItemPage addItemPage;
        addItemPage = itemListPage.goToAddItemPage();
        itemListPage = addItemPage.addItem("gruszki");

        addItemPage = itemListPage.goToAddItemPage();
        itemListPage = addItemPage.addItem("sliwki");

        itemListPage.assertItemExists("gruszki");
        itemListPage.assertItemExists("sliwki");
    }

    @Test
    public void addTheSameItemNameMoreThanOnce() {
        AddItemPage addItemPage;
        addItemPage = itemListPage.goToAddItemPage();
        itemListPage = addItemPage.addItem("gruszki", null, Priority.CRITICAL.toString().toLowerCase());

        addItemPage = itemListPage.goToAddItemPage();
        itemListPage = addItemPage.addItem("gruszki", Category.VEGETABLES.toString().toLowerCase(), null);
    }

    /* TODO Task2
     *
     * Extend abilities of created page objects so that they will be able to remove given item.
     */

    @Test
    public void addAndRemoveItem() {

    }

    /* TODO Task3
     * Write a page object for EditItemActivity using which you will be able to execute test with
     * given steps:
     *
     * 1. Create an item
     * 2. Enter edit mode
     * 3. Change one of the fields
     * 4. Save changes
     * 5. Validate if item's data has been changed
     */


     /* TODO Task4
      * Create list of items, then using it add and remove each item
      */
}