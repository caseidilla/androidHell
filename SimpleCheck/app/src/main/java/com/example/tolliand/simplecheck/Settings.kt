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

class Settings : AppCompatActivity() {

    val pin = JSONObject()
    var edTex = String()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        val actionBar = supportActionBar
        actionBar!!.title = "Settings"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
    }

    fun changePin(view: View?){
        val inputO: EditText = findViewById(R.id.old_pin)
        val inputN: EditText = findViewById(R.id.new_pin)
        val confirmN: EditText = findViewById(R.id.confirm_pin)
        if ((inputN.text.toString() != confirmN.text.toString())
                || (inputN.text.toString() == "")){
            Toast.makeText(applicationContext, "Please, confirm your new pin", Toast.LENGTH_SHORT).show()
        } else {
            pin.put("pin", inputN.text.toString())
            pin.put("oldPin", inputO.text.toString())

            val queue = Volley.newRequestQueue(this)
            val url = "https://caseidilia.herokuapp.com/api/svet/setPin"

            val jsonObjectRequest = SpecialJsonObjectRequest(Request.Method.POST, url, pin,
                    Response.Listener { response ->
                        Toast.makeText(applicationContext, "Success", Toast.LENGTH_SHORT).show()
                    },
                    Response.ErrorListener { error ->
                        if(String(error.networkResponse.data) == "No pin") {
                            Toast.makeText(applicationContext, "Wanna change pin, nigga?", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(applicationContext, String(error.networkResponse.data), Toast.LENGTH_SHORT).show()
                        }
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
