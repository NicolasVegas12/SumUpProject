package com.minenick.sumupproject.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.minenick.sumupproject.entities.Stream

class StreamSQLiteHelper(context:Context):SQLiteOpenHelper(context, "sumup.db",null,3) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query_create="CREATE TABLE streams(id INTEGER PRIMARY KEY AUTOINCREMENT, stream VARCHAR(20), fechapago INTEGER, precio FLOAT, img TEXT,cardNumber VARCHAR(19),FOREIGN KEY(cardNumber) REFERENCES cards(number))"
        db!!.execSQL(query_create)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        val query_elimination="DROP TABLE IF EXISTS streams"
        db!!.execSQL(query_elimination)
        onCreate(db)
    }
    fun addData(number:String, stream:Stream){
        val data=ContentValues()
        data.put("stream", stream.stream)
        data.put("fechapago",stream.fechaPago)
        data.put("precio", stream.precio)
        data.put("img",stream.img)
        data.put("cardNumber",number)
        val db=this.writableDatabase
        db.insert("streams",null, data)
        db.close()
    }
    fun selectAll(number:String): Cursor {
        val db=this.writableDatabase
        val args= arrayOf(number)
        return db.rawQuery("SELECT * FROM streams WHERE cardNumber = ?",args)
    }

}