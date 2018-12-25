package com.example.tolliand.simplecheck

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class Register : AppCompatActivity() {

    val user = JSONObject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val actionBar = supportActionBar
        actionBar!!.title = "Register"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
    }

    fun mainList(view: View?){
        val inputU: EditText = findViewById(R.id.edit_user)
        val inputP: EditText = findViewById(R.id.edit_password)
        val conferP: EditText = findViewById(R.id.edit_confirm)
        val pin: EditText = findViewById(R.id.reg_pin_edit)
        if ((inputP.text.toString() != conferP.text.toString())
                || (inputP.text.toString() == "")
                || (inputU.text.toString() == "")){
            Toast.makeText(applicationContext, "Please, enter correct data.", Toast.LENGTH_SHORT).show()
        } else {
            user.put("login", inputU.text.toString())
            user.put("password", inputP.text.toString())
            user.put("secret", pin.text.toString())

            val queue = Volley.newRequestQueue(this)
            val url = "https://caseidilia.herokuapp.com/api/register"

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
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
