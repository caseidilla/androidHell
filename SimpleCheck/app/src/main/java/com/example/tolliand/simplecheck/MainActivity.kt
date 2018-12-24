package com.example.tolliand.simplecheck

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun reg(view: View?){
        val intent = Intent(this@MainActivity, Register::class.java)
        startActivity(intent)
    }

    fun sign(view: View?){
        val intent = Intent(this@MainActivity, SignIn::class.java)
        startActivity(intent)
    }
}
