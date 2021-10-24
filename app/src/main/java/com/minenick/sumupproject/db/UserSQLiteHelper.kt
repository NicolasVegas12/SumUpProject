package com.minenick.sumupproject.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserSQLiteHelper(context: Context):SQLiteOpenHelper(context,"sumup.db",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query_create = "CREATE TABLE users(email VARCHAR(40) PRIMARY KEY, password VARCHAR(20))"
        db!!.execSQL(query_create)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        val query_delete = "DROP TABLE IF EXISTS users"
        db!!.execSQL(query_delete)
        onCreate(db)
    }
    fun addData(email:String, password:String){
        val data = ContentValues()
        data.put("email", email)
        data.put("password", password)
        val db=this.writableDatabase
        db.insert("users",null, data)
        db.close()
    }

}