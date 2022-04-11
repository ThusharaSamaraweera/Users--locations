package com.example.user_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    private lateinit var ed_username: EditText
    private lateinit var ed_city: EditText
    private lateinit var ed_long: EditText
    private lateinit var ed_lati: EditText

    private lateinit var btn_add: Button
    private lateinit var btn_view: Button

    private lateinit var sqLiteHelper: SQLiteHelper
    private var adapter: UserLocationAdapter? = null

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
        btn_add.setOnClickListener { addUserlocation() }
        btn_view.setOnClickListener { getAllUserlocations() }
        adapter?.setOnClickItem { Toast.makeText(this, it.city, Toast.LENGTH_SHORT).show() }

        adapter?.setOnClickDeleteUserLoc {
            deleteUserLoc(it.id)
        }

    }

    private fun addUserlocation(){
        val username = ed_username.text.toString()
        val city = ed_city.text.toString()
        val longitude = ed_long.text.toString()
        val latitude = ed_lati.text.toString()

        if(username.isEmpty() || city.isEmpty() || longitude.isEmpty() || latitude.isEmpty()){
            Toast.makeText(this,"Form is not filled", Toast.LENGTH_SHORT).show()
        }else {
            val userLocation = UserLocationModel(username = username, longitude = longitude, latitude = latitude)
            val status = sqLiteHelper.insertLocation(userLocation)

            if(status > -1){
                Toast.makeText(this,"Location Added....",Toast.LENGTH_SHORT).show()
                clear()
            }else {
                Toast.makeText(this,"Insertion failed....",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun clear(){
        ed_username.setText("")
        ed_city.setText("")
        ed_lati.setText("")
        ed_long.setText("")
        ed_username.requestFocus()
    }
    private fun deleteUserLoc(id: Int){
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure to delete?")
        builder.setCancelable(true)
        builder.setPositiveButton("Yes")
        {dialog, _->
            sqLiteHelper.deleteUserLocationById(id)
            getAllUserlocations()
            dialog.dismiss()
        }
        builder.setNegativeButton("No"){
            dialog,_->
            dialog.dismiss()
        }
        val alert = builder.create()
        alert.show()
    }

    private fun getAllUserlocations(){
        val UserLocs = sqLiteHelper.getAllUserLocations()
        adapter?.addUserLoc(UserLocs)
    }
}