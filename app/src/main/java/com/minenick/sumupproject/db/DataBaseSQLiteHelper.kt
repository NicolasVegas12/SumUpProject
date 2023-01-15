package com.minenick.sumupproject.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.google.gson.Gson
import com.minenick.sumupproject.entities.Card
import com.minenick.sumupproject.entities.Stream

class DataBaseSQLiteHelper(context: Context):SQLiteOpenHelper(context,"sumup.db",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query_create_user = "CREATE TABLE users(email VARCHAR(40) PRIMARY KEY, password VARCHAR(20))"
        val query_create_stream="CREATE TABLE streams(idStream INTEGER PRIMARY KEY , stream VARCHAR(20), fechapago INTEGER, precio FLOAT, img TEXT,cardNumber VARCHAR(19),FOREIGN KEY(cardNumber) REFERENCES cards(number))"
        val query_create_card="CREATE TABLE cards(number VARCHAR(19) PRIMARY KEY, owner varchar(40),date VARCHAR(5), img text,cdEmail VARCHAR(40), FOREIGN KEY(cdEmail) REFERENCES users(email))"

        db!!.execSQL(query_create_user)
        db!!.execSQL(query_create_stream)
        db!!.execSQL(query_create_card)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        val query_delete1 = "DROP TABLE IF EXISTS users"
        val query_delete2= "DROP TABLE IF EXISTS streams"
        val query_delete3 = "DROP TABLE IF EXISTS cards"
        db!!.execSQL(query_delete1)
        db!!.execSQL(query_delete2)
        db!!.execSQL(query_delete3)
        onCreate(db)
    }

    fun addDataUser(email:String, password:String){
        val data = ContentValues()
        data.put("email", email)
        data.put("password", password)
        val db=this.writableDatabase
        db.insert("users",null, data)
        db.close()
    }
    fun addDataCard(email:String,card: Card){
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
    fun selectAllCards(email:String): Cursor {
        val db=this.writableDatabase
        val args = arrayOf(email)
        return db.rawQuery("SELECT * FROM cards  WHERE cdEmail=?",args )
    }
    fun selectAllStream(number:String): Cursor {
        val db=this.writableDatabase
        val args= arrayOf(number)

        return db.rawQuery("SELECT * FROM streams WHERE cardNumber = ?",args)
    }
    fun deleteStream(idStream:Int){
        val db=this.writableDatabase
        val args= arrayOf(idStream.toString())
        val borrados = db.delete("streams","idStream = ? ",args)
        Log.d("Borrados",Gson().toJson(borrados))
        db.close()
    }
    fun addDataStream(number:String, stream: Stream){
        val data=ContentValues()
        data.put("idStream",stream.idStream)
        data.put("stream", stream.stream)
        data.put("fechapago",stream.fechaPago)
        data.put("precio", stream.precio)
        data.put("img",stream.img)
        data.put("cardNumber",number)
        val db=this.writableDatabase
        db.insert("streams",null, data)
        db.close()
    }
}