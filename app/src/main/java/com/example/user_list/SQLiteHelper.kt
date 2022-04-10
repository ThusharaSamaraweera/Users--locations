package com.example.user_list

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper (context: Context): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION){
    companion object{
        private const val DB_VERSION=1
        private const val DB_NAME="user_locations.db"
        private const val TBL_LOCATION="tbl_location"
        private const val ID="id"
        private const val USERNAME = "username"
        private const val CITY="city"
        private const val LONG="longitude"
        private const val LATI="latitude"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTblLocation =("CREATE TABLE " + TBL_LOCATION + "( "
                + ID + " INTEGER PRIMARY KEY," + USERNAME + " TEXT, "+ CITY + " TEXT, " + LONG + " TEXT, " + LATI + " TEXT "+ ")")

        db?.execSQL(createTblLocation)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_LOCATION")
        onCreate(db)
    }

    fun insertLocation(user :UserModel): Long {

        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(ID,user.id)
        contentValues.put(USERNAME,user.username)
        contentValues.put(CITY, user.city)
        contentValues.put(LONG,user.longitude)
        contentValues.put(LATI,user.latitude)

        val success = db.insert(TBL_LOCATION,null,contentValues)
        db.close()
        return success

    }

}