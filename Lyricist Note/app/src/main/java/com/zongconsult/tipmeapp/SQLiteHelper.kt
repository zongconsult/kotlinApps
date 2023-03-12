package com.zongconsult.tipmeapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class SQLiteHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1

        /*  private const val DATABASE_NAME = "lyrics_p.db"
          private const val TBL_STUDENT = "tbl_lyrics"*/
        private const val DATABASE_NAME = "lyrics.db"
        const val TBL_STUDENT = "tbl_lyrics"
        var ID = "id"
        private const val TITLE = "title"
        private const val DESC = "desc"
        private const val LYRICS = "lyrics"
    }


    override fun onCreate(db: SQLiteDatabase?) {
        val createTblStudent = ("CREATE TABLE " + TBL_STUDENT + "("
                + ID + " INTEGER PRIMARY KEY," + TITLE + " TEXT,"
                + DESC + " TEXT," + LYRICS + " TEXT" + ")")
        db?.execSQL(createTblStudent)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_STUDENT")
        onCreate(db)
    }


    fun insertStudent(std: StudentModel): Long {

        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, std.id)
        contentValues.put(TITLE, std.title)
        contentValues.put(DESC, std.desc)
        contentValues.put(LYRICS, std.lyrics)

        val success = db.insert(TBL_STUDENT, null, contentValues)
        db.close()
        return success

    }

    fun getAllStudent(): ArrayList<StudentModel> {
        val stdList: ArrayList<StudentModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_STUDENT"
        val db = this.readableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)
            //cursor.close()

        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var title: String
        var desc: String
        // var lyrics: String

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"))
                title = cursor.getString(cursor.getColumnIndex("title"))
                desc = cursor.getString(cursor.getColumnIndex("desc"))
                //     lyrics = cursor.getString(cursor.getColumnIndex("lyrics"))

                val std = StudentModel(id = id, title = title, desc = desc)
                stdList.add(std)


            } while (cursor.moveToNext())
        }
        return stdList
    }


    fun updateStudent(std: StudentModel): Int {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, std.id)
        contentValues.put(TITLE, std.title)
        contentValues.put(DESC, std.desc)
        //contentValues.put(LYRICS, std.lyrics)


        val success = db.update(TBL_STUDENT, contentValues, "id=" + std.id, null)
        db.close()
        return success


    }

    fun updateLyric(std: StudentModel): Int {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put("ID", std.id)
        contentValues.put(TITLE, std.title)
        contentValues.put(DESC, std.desc)
        contentValues.put(LYRICS, std.lyrics)

//        val success = db.update(TBL_STUDENT, contentValues, "id=" + std.id, null)
        val success = db.update(TBL_STUDENT, contentValues, "id=" + std.id, null)
        db.close()
        return success


    }

    fun deleteStudentById(id: Int): Int {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, id)


        val success = db.delete(TBL_STUDENT, "id=$id", null)

        db.close()
        return success
    }

    //i'm trying out this work around this function

    fun viewStudentById(id: Int) {
        val db = this.readableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, id)

        val success = db.select(TBL_STUDENT, "id=$id", null)

        db.close()
        return success
    }

    //this is the code to call the selected lyrics on the view button to edit the lyrics

    fun getLyric(): ArrayList<StudentModel> {
        val stdList: ArrayList<StudentModel> = ArrayList()

        val selectQuery = "SELECT * FROM $TBL_STUDENT WHERE ID=$ID"
        val db = this.readableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)


            //cursor.close()

        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var title: String
        var desc: String
        var lyrics: String

        if (cursor.moveToFirst()) {

            id = cursor.getInt(cursor.getColumnIndex("id"))
            title = cursor.getString(cursor.getColumnIndex("title"))
            desc = cursor.getString(cursor.getColumnIndex("desc"))
            lyrics = cursor.getString(cursor.getColumnIndex("lyrics"))

            val std = StudentModel(id = id, title = title, desc = desc, lyrics = lyrics)
            stdList.add(std)
        }
        return stdList
    }

    private fun SQLiteDatabase.select(tblStudent: String, s: String, nothing: Nothing?) {

    }


}


