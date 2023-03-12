package com.zongconsult.tipmeapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zongconsult.tipmeapp.SQLiteHelper.Companion.ID

class EditLyricsActivity : AppCompatActivity() {
    //declaring variables and assigning them
    private lateinit var edtTitle: EditText
    private lateinit var edtDescription: EditText
    private lateinit var edtLyrics: EditText
    private lateinit var sqliteHelper: SQLiteHelper
    private lateinit var btnSave: Button
    private lateinit var btnViewSaved: Button

    //universal variable for id
    companion object {
        const val id: String = "ID"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_lyrics)
//setting variables as view items
        edtTitle = findViewById(R.id.edtTitle)
        edtDescription = findViewById(R.id.edtDescription)
        edtLyrics = findViewById(R.id.edtLyrics)
        btnViewSaved = findViewById(R.id.btnViewSaved)
        sqliteHelper = SQLiteHelper((this))
        btnSave = findViewById(R.id.btnSave)

//using bundle to collect and save DB values of the a particular lyric at an id
        val bundle = intent.extras
        if (bundle != null) {
            ID = "${bundle.getString("ID")}"
            val stdList = sqliteHelper.getLyric()
            edtTitle.append(stdList[0].title)
            edtDescription.append(stdList[0].desc)
            edtLyrics.append(stdList[0].lyrics)

        }
//when the save button is clicked
        btnSave.setOnClickListener {
            updateLyric()
        }
    }

    //update function for updating the calling lyric from main menu

    private fun updateLyric() {
        val title = edtTitle.text.toString()
        val desc = edtDescription.text.toString()
        val lyrics = edtLyrics.text.toString()

/*        if (std == null) return
       //check record not changed
        if (title == std?.title && desc == std?.desc) {
            Toast.makeText(this, "Record not changed...", Toast.LENGTH_SHORT).show()
            return
        }
        */

        val std = StudentModel(id = ID.toInt(), title = title, desc = desc, lyrics = lyrics)
        //  val std = StudentModel(id = std!!.id, title = title, desc = desc, lyrics = lyrics)
        val status = sqliteHelper.updateLyric(std)

        if (status > -1) {
            clearEditText()
        } else {
            Toast.makeText(this, "updates failed", Toast.LENGTH_SHORT).show()

        }
    }

    private fun clearEditText() {
        edtTitle.setText("")
        edtDescription.setText("")
        edtLyrics.setText("")
        edtLyrics.requestFocus()
    }


}
