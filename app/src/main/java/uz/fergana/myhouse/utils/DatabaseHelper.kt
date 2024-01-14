package uz.fergana.myhouse.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "your_database_name"
        private const val DATABASE_VERSION = 1

        // Define table and column names
        const val TABLE_NAME = "doors"
        private const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_ROOM = "room"
        const val COLUMN_FAVORITES = "favorites"
        const val COLUMN_SNAPSHOT = "snapshot"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // Create the table
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY,
                $COLUMN_NAME TEXT,
                $COLUMN_ROOM TEXT,
                $COLUMN_FAVORITES INTEGER,
                $COLUMN_SNAPSHOT TEXT
            )
        """.trimIndent()
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Handle database upgrade if needed
    }

    // Additional methods for CRUD operations
}