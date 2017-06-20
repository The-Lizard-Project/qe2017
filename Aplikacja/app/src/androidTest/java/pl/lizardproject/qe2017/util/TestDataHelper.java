package pl.lizardproject.qe2017.util;

import pl.lizardproject.qe2017.database.DatabaseFacade;
import pl.lizardproject.qe2017.database.model.DbItemEntity;
import pl.lizardproject.qe2017.database.model.DbUserEntity;
import pl.lizardproject.qe2017.model.Category;
import pl.lizardproject.qe2017.model.Priority;
import pl.lizardproject.qe2017.model.User;
import pl.lizardproject.qe2017.session.UserSession;

public class TestDataHelper {

    private final DatabaseFacade databaseFacade;

    public TestDataHelper(DatabaseFacade databaseFacade) {
        this.databaseFacade = databaseFacade;
    }

    public DbUserEntity loginUser(String username, String password, UserSession userSession) {
        DbUserEntity user = addUserToDatabase(username, password);
        userSession.start(new User(user.getName(), user.getPassword(), user.getId()));
        return user;
    }

    public DbUserEntity addUserToDatabase(String username, String password) {
        DbUserEntity user = new DbUserEntity();
        user.setName(username);
        user.setPassword(password);

        databaseFacade.saveUser(user)
                .toBlocking()
                .value();

        return user;
    }

    public DbItemEntity addItemToDatabase(String name, Category category, Priority priority, boolean isChecked, DbUserEntity user) {
        DbItemEntity item = new DbItemEntity();
        item.setName(name);
        item.setCategory(category);
        item.setPriority(priority);
        item.setChecked(isChecked);
        item.setUser(user);

        databaseFacade.saveItem(item)
                .toBlocking()
                .value();

        return item;
    }

    public void dropDatabase() {
        databaseFacade.drop();
    }
}