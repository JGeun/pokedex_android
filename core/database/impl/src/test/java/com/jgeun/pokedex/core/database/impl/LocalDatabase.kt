package com.jgeun.pokedex.core.database.impl

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.jgeun.pokedex.core.database.api.PokedexDatabase
import com.jgeun.pokedex.core.database.api.TypeResponseConverter
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [26])
abstract class LocalDatabase {
    lateinit var db: PokedexDatabase

    @Before
    fun initDB() {
        db = Room.inMemoryDatabaseBuilder(getApplicationContext(), PokedexDatabase::class.java)
            .allowMainThreadQueries()
            .addTypeConverter(TypeResponseConverter())
            .build()
    }

    @After
    fun closeDB() {
        db.close()
    }
}