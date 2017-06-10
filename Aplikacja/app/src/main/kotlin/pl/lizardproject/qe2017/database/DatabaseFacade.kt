package pl.lizardproject.qe2017.database

import android.content.Context
import io.requery.Persistable
import io.requery.android.sqlitex.SqlitexDatabaseSource
import io.requery.rx.RxSupport
import io.requery.sql.EntityDataStore
import io.requery.sql.TableCreationMode
import pl.lizardproject.qe2017.BuildConfig
import pl.lizardproject.qe2017.database.model.DbItemEntity
import pl.lizardproject.qe2017.database.model.DbUserEntity
import pl.lizardproject.qe2017.database.model.Models
import rx.Single
import rx.lang.kotlin.toSingle
import rx.schedulers.Schedulers
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class DatabaseFacade(private val context: Context) {

    private val scheduler = Schedulers.from(Executors.newSingleThreadExecutor())

    val storage by lazy {
        // override onUpgrade to handle migrating to a new version
        val source = SqlitexDatabaseSource(context, Models.DEFAULT, 1)
        source.setLoggingEnabled(true)

        if (BuildConfig.DEBUG) {
            // use this in development mode to drop and recreate the tables on every upgrade
            source.setTableCreationMode(TableCreationMode.DROP_CREATE)
        }

        RxSupport.toReactiveStore(EntityDataStore<Persistable>(source.configuration))
    }

    fun saveItem(item: DbItemEntity) {
        val single: Single<DbItemEntity>
        if (item.id > 0) {
            single = storage.update(item)
        } else {
            single = storage.insert(item)
        }

        single.subscribeOn(scheduler)
                .subscribe { }
    }

    fun loadItems() = storage.select(DbItemEntity::class.java)
            .orderBy(DbItemEntity.CHECKED.asc())
            .get()
            .toSelfObservable()
            .subscribeOn(scheduler)

    fun deleteItem(item: DbItemEntity) {
        storage.delete(item)
                .subscribeOn(scheduler)
                .subscribe { }
    }

    fun loadItem(itemId: Int?) = loadItems()
            .map { it.first { it.id == itemId } }

    fun hasUser(user: DbUserEntity) = storage.select(DbUserEntity::class.java)
            .where(DbUserEntity.NAME.lower().eq(user.name))
            .get()
            .toSingle()
            .subscribeOn(scheduler)
            .delay(3, TimeUnit.SECONDS)
            .map { it.mapNotNull { it }.isNotEmpty() }

    fun saveUser(user: DbUserEntity) =
            storage.insert(user)
                    .subscribeOn(scheduler)
                    .delay(3, TimeUnit.SECONDS)
}