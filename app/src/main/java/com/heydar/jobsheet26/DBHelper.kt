package com.heydar.jobsheet26

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?):
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    companion object {
        private val DATABASE_NAME = "data"
        private val DATABASE_VERSION = 1
        val TABLE_NAME = "data_model"
        val ID_COL = "id"
        val JUDUL_COL = "judul"
        val ISI_COL = "isi"

    }
    override fun onCreate(db: SQLiteDatabase) {
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + "INTEGER PRIMARY KEY, " +
                JUDUL_COL + "TEXT" +
                ISI_COL + "TEXT" + ")")
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun getData():ArrayList<Data> {
        val list = ArrayList<Data>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null)

        if (cursor.moveToFirst()) {
            do {
                list.add(Data(cursor.getString(0), cursor.getString(1), cursor.getString(2)))
            } while (cursor.moveToNext());
        }
        cursor.close()

        return list;
    }
    fun addData(judul: String, isi: String) {
        val values = ContentValues()
        val db = this.writableDatabase

        values.put(JUDUL_COL, judul)
        values.put(ISI_COL, isi)

        db.insert(TABLE_NAME, null, values)
        db.close()
    }
    fun deleteData(id: String): Int {
        val db = this.readableDatabase
        val status = db.delete(TABLE_NAME,  "id"+id, null )
        db.close()

        return status
    }
    fun updateData(data: Data):Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID_COL, data.id)
        contentValues.put(JUDUL_COL, data.judul)
        contentValues.put(ISI_COL, data.isi)

        val success = db.update(TABLE_NAME, contentValues, "id="+data.id, null)
        db.close()
        return success
    }
}
