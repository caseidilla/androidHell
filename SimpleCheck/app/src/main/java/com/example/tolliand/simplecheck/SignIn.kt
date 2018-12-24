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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
    }

    fun mainList(view: View?){

        /*val queue = Volley.newRequestQueue(this)
        val url = "http://localhost:8092/api/login"

        val inputP: EditText = findViewById(R.id.edit_password)
        val inputU: EditText = findViewById(R.id.edit_user)
        val user = JSONObject()
        user.put("login", inputU.text.toString())
        user.put("password", inputP.text.toString())

        val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, url, user,
                Response.Listener { response ->
                    val intent = Intent(this, MainList::class.java)
                    startActivity(intent)
                },
                Response.ErrorListener { error ->
                    Toast.makeText(applicationContext,"Incorrect data,\nplease, try again.", Toast.LENGTH_SHORT).show()
                }
        )

        queue.add(jsonObjectRequest)*/

        if (true) {

            val intent = Intent(this, MainList::class.java)
            startActivity(intent)
        }
    }
}
