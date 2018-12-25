package com.example.tolliand.simplecheck

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText

import kotlinx.android.synthetic.main.activity_buffer.*

class Buffer : AppCompatActivity() {

    private var userName = String()
    private var partName = String()
    private var edTex = String()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buffer)
        val arguments = intent.extras
        partName = arguments!!.get("part")!!.toString()
        userName = arguments!!.get("user")!!.toString()
        val actionBar = supportActionBar
        actionBar!!.title = userName
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)


    }

    fun buffOut(){
        edTex = findViewById<EditText>(R.id.buffer_edit).toString()
        val intent = Intent(this, MainList::class.java)
        intent.putExtra("userName", userName)
        intent.putExtra("partName", partName)
        intent.putExtra("edTex", edTex)
        startActivity(intent)
        this.finish()
    }

}
