package com.example.user_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var ed_username: EditText
    private lateinit var ed_city: EditText
    private lateinit var ed_long: EditText
    private lateinit var ed_lati: EditText

    private lateinit var btn_add: Button
    private lateinit var btn_view: Button

    private lateinit var sqLiteHelper: SQLiteHelper

    private fun initView(){
        ed_username = findViewById(R.id.et_username)
        ed_city = findViewById(R.id.et_city)
        ed_long = findViewById(R.id.et_longitude)
        ed_lati = findViewById(R.id.et_latitude)

        btn_add = findViewById(R.id.btn_add)
        btn_view = findViewById(R.id.btn_view)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        sqLiteHelper = SQLiteHelper(this)
        btn_add.setOnClickListener {  }
    }

    private fun addUser(){
        val username = ed_username.text.toString()
        val city = ed_city.text.toString()
        val longitude = ed_long.text.toString()
        val latitude = ed_lati.text.toString()

        if(username.isEmpty() || city.isEmpty() || longitude.isEmpty() || latitude.isEmpty()){
            Toast.makeText(this,"Form is not filled", Toast.LENGTH_SHORT).show()
        }else {
            val userLocation = UserModel(username = username, longitude = longitude, latitude = latitude)
            val status = sqLiteHelper.insertLocation(userLocation)

            if(status > -1){
                Toast.makeText(this,"Location Added....",Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(this,"Insertion failed....",Toast.LENGTH_SHORT).show()
            }
        }
    }
}