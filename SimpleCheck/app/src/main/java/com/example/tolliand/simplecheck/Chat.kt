package com.example.tolliand.simplecheck

import adapters.MAdapter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import adapters.ChatAdapter
import kotlinx.android.synthetic.main.activity_main_list.*

class Chat : AppCompatActivity() {

    var result = ArrayList<ChMessage>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        displayChatMessages()

        var listV: ListView = findViewById(R.id.list_of_messages)
        var adapter = ChatAdapter(generateData())
        adapter.notifyDataSetChanged()
    }

    private fun generateData(): ArrayList<ChMessage> {

        var user: ChMessage = ChMessage("", "Botan")
        result.add(user)

        return result
    }

    fun sendMess(view: View?){
        val input: EditText = findViewById(R.id.input)
        val mess: ChMessage = ChMessage(input.toString(), "Botan")
        input.setText("")

        var listOfMess: ListView = findViewById(R.id.list_of_messages)
        
    }

    private fun displayChatMessages() {

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.chat_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.chat_menu_profile ->{
                val builder = AlertDialog.Builder(this)
                builder.setTitle("User profile")
                builder.setMessage("")
                builder.setNeutralButton("OK"){ dialog, which ->

                }
                val dialog: AlertDialog = builder.create()
                dialog.show()
                return true
            }
            R.id.chat_menu_hide -> {
                val dialogV: View = LayoutInflater.from(this).inflate(R.layout.activity_dialog_pin,null)
                val builder = AlertDialog.Builder(this)
                builder.setView(dialogV)
                builder.setTitle("Enter PIN")
                builder.setPositiveButton("Hide"){ dialog, which ->
                    Toast.makeText(applicationContext,"Perfect", Toast.LENGTH_SHORT).show()
                }
                builder.setNegativeButton("Cancel"){ dialog, which ->
                    Toast.makeText(applicationContext, "Bad", Toast.LENGTH_SHORT).show()
                }
                val dialog: AlertDialog = builder.create()
                dialog.show()
                return true
            }
            R.id.chat_menu_show -> {
                val dialogV: View = LayoutInflater.from(this).inflate(R.layout.activity_dialog_pin,null)
                val builder = AlertDialog.Builder(this)
                builder.setView(dialogV)
                builder.setTitle("Enter PIN")
                builder.setPositiveButton("Show"){ dialog, which ->
                    Toast.makeText(applicationContext,"Perfect", Toast.LENGTH_SHORT).show()
                }
                builder.setNegativeButton("Cancel"){ dialog, which ->
                    Toast.makeText(applicationContext, "Bad", Toast.LENGTH_SHORT).show()
                }
                val dialog: AlertDialog = builder.create()
                dialog.show()
                return true
            }
            R.id.chat_menu_delete -> {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Delete chat?")
                builder.setMessage("Do you want to delete this chatik? \nAll history will be lost!" +
                        "\nWe won't be able to restore it!")

                builder.setPositiveButton("Yes"){ dialog, which ->
                    val dialogV: View = LayoutInflater.from(this).inflate(R.layout.activity_dialog_pin,null)
                    val builderInner = AlertDialog.Builder(this)
                    builderInner.setView(dialogV)
                    builderInner.setTitle("Enter PIN")
                    builderInner.setNeutralButton("Delete chatik!"){ dialog, which ->
                        Toast.makeText(applicationContext,"Deleted", Toast.LENGTH_SHORT).show()
                        this.finish()
                    }
                    val dialogInner: AlertDialog = builderInner.create()
                    dialogInner.show()
                }

                builder.setNegativeButton("No"){ dialog, which ->

                }

                val dialog: AlertDialog = builder.create()
                dialog.show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
