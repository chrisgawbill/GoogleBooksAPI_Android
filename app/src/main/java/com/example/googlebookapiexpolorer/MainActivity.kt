package com.example.googlebookapiexpolorer

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    lateinit var enterBookText:EditText
    lateinit var findAuthorButton:Button
    lateinit var bookNameResult:TextView
    lateinit var authorResult:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        enterBookText = findViewById(R.id.enterBookNameText)
        bookNameResult = findViewById(R.id.bookNameText)
        authorResult = findViewById(R.id.authorText)
        findAuthorButton = findViewById(R.id.getAuthorButton)
        findAuthorButton.setOnClickListener(View.OnClickListener {
            getBooks()
        })
    }
    fun getBooks(){
        var bookName:String = enterBookText.text.toString()
        SimpleAsyncTask(bookNameResult, authorResult).execute(bookName)
    }

    override fun onPause() {
        super.onPause()
        var shared:SharedPreferences = getSharedPreferences("bookKey", MODE_PRIVATE)
        var editor:SharedPreferences.Editor = shared.edit()
        editor.putString("bookKey", enterBookText.text.toString())
        editor.apply()
    }

    override fun onResume() {
        super.onResume()
        var preferences:SharedPreferences = getSharedPreferences("bookKey", MODE_PRIVATE)
        var prefs:String = preferences.getString("bookKey","").toString()
        enterBookText.setText(prefs)
    }
}