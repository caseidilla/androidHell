package com.example.tolliand.simplecheck

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Register : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    fun mainList(view: View?){
        val intent = Intent(this, MainList::class.java)
        startActivity(intent)
    }
}
