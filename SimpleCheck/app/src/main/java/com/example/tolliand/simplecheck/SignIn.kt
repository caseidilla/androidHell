package com.example.tolliand.simplecheck

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent
import android.widget.EditText
import android.widget.Toast
import org.json.JSONObject
import com.android.volley.*
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class SignIn : AppCompatActivity() {


    val user = JSONObject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        val actionBar = supportActionBar
        actionBar!!.title = "Log in"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
    }

    fun mainList(view: View?){

        val inputP = findViewById<EditText>(R.id.edit_password)
        val inputU = findViewById<EditText>(R.id.edit_user)
        user.put("login", inputU.text.toString())
        user.put("password", inputP.text.toString())

        val queue = Volley.newRequestQueue(this)
        val url = "https://caseidilia.herokuapp.com/api/login"
        //val url = "http://10.0.2.2:8082/api/login"

        val jsonObjectRequest = SpecialJsonObjectRequest(Request.Method.POST, url, user,
                Response.Listener { response ->
                    val intent = Intent(this, MainList::class.java)
                    intent.putExtra("log", inputU.text.toString())
                    startActivity(intent)
                },
                Response.ErrorListener { error ->
                    Toast.makeText(applicationContext, String(error.networkResponse.data), Toast.LENGTH_SHORT).show()
                }
        )

        queue.add(jsonObjectRequest)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
