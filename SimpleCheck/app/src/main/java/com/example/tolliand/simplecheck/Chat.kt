package com.example.tolliand.simplecheck

import adapters.MAdapter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import adapters.ChatAdapter
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.*
import android.widget.*
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.activity_main_list.*
import org.json.JSONObject

class Chat : AppCompatActivity() {

    private var listMess = ArrayList<ChMessage>()
    private var userName = String()
    private var partName = String()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        val arguments = intent.extras
        val name = arguments!!.get("name")!!.toString()
        partName = name
        val user = arguments!!.get("user")!!.toString()
        userName = user
        val actionBar = supportActionBar
        actionBar!!.title = name
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)

        val queue = Volley.newRequestQueue(this)
        val url = "https://caseidilia.herokuapp.com/api/$user/dialog?participant=$name"

        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, url, null,
                Response.Listener { response ->
                    for (i in 0 until response.length()){
                        listMess.add(ChMessage(response.getJSONObject(i).getString("type"),
                                response.getJSONObject(i).getString("body")))

                    }
                    if (listMess.size != 0) {
                        var notesAdapter = MessAdapter(this, listMess)
                        list_of_messages.adapter = notesAdapter
                        notesAdapter.notifyDataSetChanged()
                    }
                },
                Response.ErrorListener { error ->
                    Toast.makeText(applicationContext, String(error.networkResponse.data), Toast.LENGTH_SHORT).show()
                }
        )

        queue.add(jsonArrayRequest)
    }

    inner class MessAdapter : BaseAdapter {

        private var notesList = ArrayList<ChMessage>()
        private var context: Context? = null

        constructor(context: Context, notesList: ArrayList<ChMessage>) : super() {
            this.notesList = notesList
            this.context = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

            val view: View?
            val vh: ViewHolder

            if (convertView == null) {
                view = layoutInflater.inflate(R.layout.mess, parent, false)
                vh = ViewHolder(view)
                view.tag = vh
                Log.i("JSA", "set Tag for ViewHolder, position: " + position)
            } else {
                view = convertView
                vh = view.tag as ViewHolder
            }

            vh.tvTitle.text = notesList[position].messageUser
            vh.tvContent.text = notesList[position].messageText

            return view
        }

        override fun getItem(position: Int): Any {
            return notesList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return notesList.size
        }
    }

    private class ViewHolder(view: View?) {
        val tvTitle: TextView
        val tvContent: TextView

        init {
            this.tvTitle = view?.findViewById(R.id.mess_user) as TextView
            this.tvContent = view.findViewById(R.id.mess_text) as TextView
        }

    }

    fun sendMess(view: View?){
        val inputt: EditText = findViewById(R.id.input)
        var messT = JSONObject()
        messT.put("participant", partName)
        messT.put("body", inputt.text.toString())

        val queue = Volley.newRequestQueue(this)
        val url = "https://caseidilia.herokuapp.com/api/$userName/dialog/send"

        val jsonObjectRequest = SpecialJsonObjectRequest(Request.Method.POST, url, messT,
                Response.Listener { response ->
                    listMess.add(ChMessage(userName, inputt.text.toString()))
                    inputt.setText("")
                },
                Response.ErrorListener { error ->
                    Toast.makeText(applicationContext, String(error.networkResponse.data), Toast.LENGTH_SHORT).show()
                }
        )

        queue.add(jsonObjectRequest)

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
                builder.setMessage(partName)
                builder.setNeutralButton("OK"){ dialog, which ->

                }
                val dialog: AlertDialog = builder.create()
                dialog.show()
                return true
            }
            R.id.chat_menu_hide -> {
                val dialogV: View = LayoutInflater.from(this).inflate(R.layout.activity_dialog_pin, null)
                val etSearch = dialogV.findViewById(R.id.input_pin) as EditText
                val builder = AlertDialog.Builder(this)
                builder.setView(dialogV)
                builder.setTitle("Enter pin")

                builder.setPositiveButton("Hide") { dialog, which ->
                    val queue = Volley.newRequestQueue(this)
                    val url = "https://caseidilia.herokuapp.com/api/$userName/dialog/hide"
                    val valueKey = findViewById<EditText>(R.id.input_pin)
                    val nnn = JSONObject()
                    nnn.put("pin", etSearch.text.toString())
                    nnn.put("name", partName)
// for (i in 0 until listDialog.length()) {
// if (listChat[info.id.toInt()].nameUser == listDialog.getJSONObject(i).getString("name")) {
// nnn.put("participant", listDialog.getJSONObject(i).getString("login"))
// nnn.put("name", valueKey.text.toString())
// }
// }
                    val jsonObjectRequest = SpecialJsonObjectRequest(Request.Method.POST, url,
                            nnn,
                            Response.Listener { response ->
                            },
                            Response.ErrorListener { error ->
                                Toast.makeText(applicationContext, "No pin", Toast.LENGTH_SHORT).show()
                            }
                    )
                    queue.add(jsonObjectRequest)
                }
                builder.setNegativeButton("Cancel") { dialog, which ->
                }

                val dialog: AlertDialog = builder.create()
                dialog.show()

                return true
            }
            R.id.chat_menu_show -> {
                val dialogV: View = LayoutInflater.from(this).inflate(R.layout.activity_dialog_pin, null)
                val etSearch = dialogV.findViewById(R.id.input_pin) as EditText
                val builder = AlertDialog.Builder(this)
                builder.setView(dialogV)
                builder.setTitle("Enter pin")

                builder.setPositiveButton("Show") { dialog, which ->
                    val queue = Volley.newRequestQueue(this)
                    val url = "https://caseidilia.herokuapp.com/api/$userName/dialog/hide"
                    val valueKey = findViewById<EditText>(R.id.input_pin)
                    val nnn = JSONObject()
                    nnn.put("pin", etSearch.text.toString())
                    nnn.put("name", partName)
// for (i in 0 until listDialog.length()) {
// if (listChat[info.id.toInt()].nameUser == listDialog.getJSONObject(i).getString("name")) {
// nnn.put("participant", listDialog.getJSONObject(i).getString("login"))
// nnn.put("name", valueKey.text.toString())
// }
// }
                    val jsonObjectRequest = SpecialJsonObjectRequest(Request.Method.POST, url,
                            nnn,
                            Response.Listener { response ->
                            },
                            Response.ErrorListener { error ->
                                Toast.makeText(applicationContext, "No pin", Toast.LENGTH_SHORT).show()
                            }
                    )
                    queue.add(jsonObjectRequest)
                }
                builder.setNegativeButton("Cancel") { dialog, which ->
                }

                val dialog: AlertDialog = builder.create()
                dialog.show()

                return true
            }
            R.id.chat_menu_delete -> {
                /* val builder = AlertDialog.Builder(this)
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
                 dialog.show()*/
                val queue = Volley.newRequestQueue(this)
                val url = "https://caseidilia.herokuapp.com/api/$userName/dialog/delete"
                val nnn = JSONObject()
                nnn.put("participant", partName)

                val jsonObjectRequest = SpecialJsonObjectRequest(Request.Method.POST, url,
                        nnn,
                        Response.Listener { response ->
                        },
                        Response.ErrorListener { error ->
                            Toast.makeText(applicationContext, String(error.networkResponse.data), Toast.LENGTH_SHORT).show()
                        }
                )

                queue.add(jsonObjectRequest)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
