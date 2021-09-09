package com.kooky.data

import android.app.Application
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
        val sqlDriver = AndroidSqliteDriver(Database.Schema, context, "test.db")
        return Database(sqlDriver)
    }
}