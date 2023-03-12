package com.zongconsult.tipmeapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var edName: EditText
    private lateinit var edEmail: EditText
    private lateinit var edtLyrics: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnView: Button
    private lateinit var btnUpdate: Button

    private lateinit var sqliteHelper: SQLiteHelper
    private lateinit var recyclerView: RecyclerView
    private var adapter: StudentAdapter? = null
    private var std: StudentModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initRecyclerView()


        sqliteHelper = SQLiteHelper((this))

        btnAdd.setOnClickListener { addStudent() }
        btnView.setOnClickListener { getStudents() }
        btnUpdate.setOnClickListener { updateStudent() }


//When the lyric is clicked in the recyclerview it's corresponding
        //title and description is called into the text fields of the email and description

        adapter?.setOnClickItem {
            Toast.makeText(this, it.title, Toast.LENGTH_SHORT).show()

            //update a record
            edName.setText(it.title)
            /*     valDBTitle.textContent = (it.name)*/
            edEmail.setText(it.desc)
            /*    valDBDescription.textContent = (it.email)*/
            std = it
        }

        adapter?.setOnClickDeleteItem {
            deleteStudent(it.id)

        }


        //this code is going to be invoked when the edit lyric button is clicked
        //on the recyclerview


        adapter?.setOnClickEditLyricItem {

            //calling view lyrics on then recyclerview
            viewStudent(it.id)
            /*   //call an intent to show the next screen
               var i = Intent(this, EditLyricsActivity::class.java)
               startActivity(i)*/


        }
    }


    private fun getStudents() {
        val stdList = sqliteHelper.getAllStudent()
        /* Log.e("App: msg", "${stdList.size}")*/
        adapter?.addItem(stdList)
    }


    private fun addStudent() {
        val title = edName.text.toString()
        val desc = edEmail.text.toString()
        // val lyrics = edtLyrics.text.toString()
        //||lyrics.isEmpty()
        if (title.isEmpty() || desc.isEmpty()) {
            Toast.makeText(this, "Please enter required fields", Toast.LENGTH_SHORT).show()
        } else {
//, lyrics = lyrics
            val std = StudentModel(title = title, desc = desc)
            val status = sqliteHelper.insertStudent(std)
            //checking to see if there are errors
            if (status > -1) {
                Toast.makeText(this, "Song Added...", Toast.LENGTH_SHORT).show()
                clearEditText()
                getStudents()
            } else {
                Toast.makeText(this, "Record not saved...", Toast.LENGTH_SHORT).show()
            }
        }

    }


    private fun updateStudent() {
        val title = edName.text.toString()
        val desc = edEmail.text.toString()

        //check record not changed
        if (title == std?.title && desc == std?.desc) {
            Toast.makeText(this, "Record not changed...", Toast.LENGTH_SHORT).show()
            return
        }

        if (std == null) return

        val std = StudentModel(id = std!!.id, title = title, desc = desc)
        val status = sqliteHelper.updateStudent(std)

        if (status > -1) {
            clearEditText()
            getStudents()
        } else {
            Toast.makeText(this, "updates failed", Toast.LENGTH_SHORT).show()

        }
    }

//building this function to work out view lyrics when the
//view button is clicked on  then recyclerview

    private fun viewStudent(id: Int) {

        if (id == null) return

        sqliteHelper.viewStudentById(id)

        val i = Intent(this, EditLyricsActivity::class.java)
        i.putExtra("ID", "$id")             //putting ID value in the intent for the next screen
        startActivity(i)
    }

    private fun deleteStudent(id: Int) {

        if (id == null) return

        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure you want to delete item?")
        builder.setCancelable(true)
        builder.setPositiveButton("Yes") { dialog, _ ->
            sqliteHelper.deleteStudentById(id)
            getStudents()

            dialog.dismiss()
        }
        builder.setNegativeButton("No") { dialog, _ ->

            dialog.dismiss()
        }
        val alert = builder.create()
        alert.show()
    }

    private fun clearEditText() {
        edName.text.clear()
        edEmail.text.clear()
        edName.requestFocus()
    }


    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = StudentAdapter()
        recyclerView.adapter = adapter

    }

    private fun initView() {
        edName = findViewById(R.id.edName)
        edEmail = findViewById(R.id.edEmail)
        btnAdd = findViewById(R.id.btnAdd)
        btnView = findViewById(R.id.btnView)
        btnUpdate = findViewById(R.id.btnUpdate)
        recyclerView = findViewById(R.id.recyclerView)

    }
}

