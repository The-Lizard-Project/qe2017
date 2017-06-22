package pl.lizardproject.qe2017.pageobject;

import pl.lizardproject.qe2017.model.Category;
import pl.lizardproject.qe2017.model.Priority;

public class EditItemPageObject {

//    private final ViewInteraction itemNameEditText;
//    private final ViewInteraction categorySpinner;
//    private final ViewInteraction prioritySpinner;
//    private final ViewInteraction saveItemFab;

    public EditItemPageObject() {

    }

    public ItemListPageObject saveItem(String name, Category category, Priority priority) {
        // new method: clearText

        return new ItemListPageObject();
    }

    public EditItemPageObject validate(String itemName, Category itemCategory, Priority itemPriority) {
        // new method: withChild

        return this;
    }
}