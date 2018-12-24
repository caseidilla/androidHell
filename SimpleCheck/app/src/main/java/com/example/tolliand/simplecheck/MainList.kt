package com.example.tolliand.simplecheck

import adapters.MAdapter
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main_list.*
import org.json.JSONObject
import com.android.volley.*
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.DiskBasedCache
import android.R.attr.data
import android.support.v4.app.FragmentActivity
import android.util.Log


class MainList : AppCompatActivity() {

    private var recyclerV: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_list)

        recyclerV = findViewById(R.id.recyclerView)
        var adapter = MAdapter(generateData())
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    fun selectedChat(view: View?){
        val intent = Intent(this, Chat::class.java)
        startActivity(intent)
    }

    private fun generateData(): ArrayList<ChatInter> {
        var result = ArrayList<ChatInter>()


            var user: ChatInter = ChatInter("Bett", "Awesome work ;)")
            result.add(user)


        return result
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings -> {
                val intent = Intent(this, Settings::class.java)
                startActivity(intent)
                return true
            }
            R.id.invite -> {
                val queue = Volley.newRequestQueue(this)
                val url = "http://192.168.2.156:8082/api/loupa/invite"
                //val url = "https://caseidilia.herokuapp.com/api/loupa/invite"
                var s: String =""

                val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                        Response.Listener { response ->
                            s = response.getString("secret")
                        },
                        Response.ErrorListener { error ->
                            Toast.makeText(applicationContext, "Oops, smth went wrong", Toast.LENGTH_SHORT).show()
                        }

                )

                queue.add(jsonObjectRequest)

                val builder = AlertDialog.Builder(this)
                builder.setTitle("Code for registration")
                builder.setMessage(s)
                builder.setNeutralButton("OK"){ dialog, which ->

                }
                val dialog: AlertDialog = builder.create()
                dialog.show()
                return true
            }
            R.id.show -> {
                val dialogV: View = LayoutInflater.from(this).inflate(R.layout.activity_dialog_pin,null)
                val builder = AlertDialog.Builder(this)
                builder.setView(dialogV)
                builder.setTitle("Enter PIN")
                builder.setPositiveButton("Yes"){ dialog, which ->
                    Toast.makeText(applicationContext,"Perfect", Toast.LENGTH_SHORT).show()
                }
                builder.setNegativeButton("No"){ dialog, which ->
                    Toast.makeText(applicationContext, "Bad", Toast.LENGTH_SHORT).show()
                }
                val dialog: AlertDialog = builder.create()
                dialog.show()

                item.isVisible = false
                return true
            }
            R.id.hide -> {
                val dialogV: View = LayoutInflater.from(this).inflate(R.layout.activity_dialog_pin,null)
                val builder = AlertDialog.Builder(this)
                builder.setView(dialogV)
                builder.setTitle("Enter PIN")
                builder.setPositiveButton("Yes"){ dialog, which ->
                    Toast.makeText(applicationContext,"Perfect", Toast.LENGTH_SHORT).show()
                }
                builder.setNegativeButton("No"){ dialog, which ->
                    Toast.makeText(applicationContext, "Bad", Toast.LENGTH_SHORT).show()
                }
                val dialog: AlertDialog = builder.create()
                dialog.show()

                item.isVisible = false
                return true
            }
            R.id.findu -> {
                val dialogV: View = LayoutInflater.from(this).inflate(R.layout.activity_dialog_pin,null)
                val builder = AlertDialog.Builder(this)
                builder.setView(dialogV)
                builder.setTitle("Enter user name")
                builder.setPositiveButton("Search"){ dialog, which ->
                    Toast.makeText(applicationContext,"Perfect", Toast.LENGTH_SHORT).show()
                }
                builder.setNegativeButton("Cansel"){ dialog, which ->
                    Toast.makeText(applicationContext, "Bad", Toast.LENGTH_SHORT).show()
                }
                val dialog: AlertDialog = builder.create()
                dialog.show()

                return true
            }
            R.id.findch -> {
                val dialogV: View = LayoutInflater.from(this).inflate(R.layout.activity_dialog_pin,null)
                val builder = AlertDialog.Builder(this)
                builder.setView(dialogV)
                builder.setTitle("Enter chat name")
                builder.setPositiveButton("Search"){ dialog, which ->
                    Toast.makeText(applicationContext,"Perfect", Toast.LENGTH_SHORT).show()
                }
                builder.setNegativeButton("Cancel"){ dialog, which ->
                    Toast.makeText(applicationContext, "Bad", Toast.LENGTH_SHORT).show()
                }
                val dialog: AlertDialog = builder.create()
                dialog.show()

                return true
            }
            R.id.help -> {
                val intent = Intent(this, Help::class.java)
                startActivity(intent)
                return true
            }
            R.id.logout -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                this.finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}

