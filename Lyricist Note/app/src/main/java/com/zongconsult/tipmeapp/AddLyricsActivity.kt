package com.zongconsult.tipmeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AddLyricsActivity : AppCompatActivity() {

    private lateinit var btnViewSaved: Button
    private lateinit var btnSave: Button
    private lateinit var edtTitle: EditText
    private lateinit var edtDescription: EditText
    private lateinit var edtLyrics: EditText
    private lateinit var sqliteHelper: SQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_lyrics)
/*Declarations Here*/
        edtTitle = findViewById(R.id.edtTitle)
        edtDescription = findViewById(R.id.edtDescription)
        edtLyrics = findViewById(R.id.edtLyrics)
        btnViewSaved = findViewById(R.id.btnViewSaved)
        btnSave = findViewById(R.id.btnSave)
        sqliteHelper = SQLiteHelper((this))


        btnViewSaved.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnSave.setOnClickListener {
            addStudent()
        }


    }

    private fun addStudent() {
        val title = edtTitle.text.toString()
        val desc = edtDescription.text.toString()
        val lyrics = edtLyrics.text.toString()
        //val lyrics = edtLyrics.text.toString()

        if (title.isEmpty() || desc.isEmpty() || lyrics.isEmpty()) {
            Toast.makeText(this, "Please enter required fields", Toast.LENGTH_SHORT).show()
        } else {

            val std = StudentModel(title = title, desc = desc, lyrics = lyrics)
            val status = sqliteHelper.insertStudent(std)
            //checking to see if there are errors
            if (status > -1) {
                Toast.makeText(this, "Song has been Added...", Toast.LENGTH_SHORT).show()
                clearEditText()

            } else {
                Toast.makeText(this, "Song not saved...", Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun clearEditText() {
        edtTitle.setText("")
        edtDescription.setText("")
        edtLyrics.setText("")
        edtTitle.requestFocus()
    }

}