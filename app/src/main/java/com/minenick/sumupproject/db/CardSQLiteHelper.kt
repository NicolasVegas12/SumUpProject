package com.minenick.sumupproject.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.minenick.sumupproject.entities.Card

class CardSQLiteHelper(context : Context): SQLiteOpenHelper(context,"sumup.db",null,2) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query_creation="CREATE TABLE cards(number VARCHAR(19) PRIMARY KEY, owner varchar(40),date VARCHAR(5), img text,cdEmail VARCHAR(40), FOREIGN KEY(cdEmail) REFERENCES users(email))"
        db!!.execSQL(query_creation)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
       val query_elimination="DROP TABLE IF EXISTS cards"
        db!!.execSQL(query_elimination)
        onCreate(db)
    }
    fun selectAll(email:String): Cursor {
        val db=this.writableDatabase
        val args = arrayOf(email)
        return db.rawQuery("SELECT * FROM cards  WHERE cdEmail=?",args )
    }
    fun addData(email:String,card: Card){
        val data= ContentValues()
        data.put("number",card.number)
        data.put("owner",card.owner)
        data.put("date",card.date)
        data.put("img",card.img)
        data.put("cdEmail",email)
        val db=this.writableDatabase
        db.insert("cards",null ,data)
        db.close()
    }
}