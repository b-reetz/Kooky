package com.kooky

import android.app.Application
import com.kooky.data.Database
import com.squareup.sqldelight.android.AndroidSqliteDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabaseModule(context: Application): Database {
        val sqlDriver = AndroidSqliteDriver(Database.Schema, context, null)
        return Database(sqlDriver)
    }

    @Singleton
    @Provides
    fun provideIngredientQueries(database: Database) = database.ingredientQueries
}