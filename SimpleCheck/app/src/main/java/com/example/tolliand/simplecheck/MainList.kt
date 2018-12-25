package com.example.tolliand.simplecheck

import adapters.MAdapter
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.widget.AdapterView.AdapterContextMenuInfo
import kotlinx.android.synthetic.main.activity_main_list.*
import org.json.JSONObject
import com.android.volley.*
import android.R.attr.data
import android.content.Context
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.view.*
import android.view.Gravity
import android.view.MenuInflater
import android.widget.*
import com.android.volley.toolbox.*
import org.json.JSONArray
import android.widget.EditText


class MainList : AppCompatActivity() {

    private var listChat = ArrayList<ChatInter>()
    private var listDialog = JSONArray()
    private var userName = String()
    private var edTex = String()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_list)

        val arguments = intent.extras
        val log = arguments!!.get("log")!!.toString()
        userName = log

        val queue = Volley.newRequestQueue(this)
        val url = "https://caseidilia.herokuapp.com/api/$log/dialogs"

        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, url, null,
                Response.Listener { response ->
                    for (i in 0 until response.length()) {
                        listChat.add(ChatInter(response.getJSONObject(i).getString("name"),
                                response.getJSONObject(i).getString("login")))
                    }
                    if (listChat.size != 0) {
                        var notesAdapter = ChatAdapter(this, listChat)
                        listView.adapter = notesAdapter
                        notesAdapter.notifyDataSetChanged()
                        listView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
                            val intent = Intent(this, Chat::class.java)
                            intent.putExtra("name", listChat[id.toInt()].lastMess)
                            intent.putExtra("user", log)
                            startActivity(intent)
                        }
                        registerForContextMenu(findViewById(R.id.listView))
                    }
                },
                Response.ErrorListener { error ->
                    Toast.makeText(applicationContext, String(error.networkResponse.data), Toast.LENGTH_SHORT).show()
                }
        )

        queue.add(jsonArrayRequest)


    }

    inner class ChatAdapter : BaseAdapter {

        private var notesList = ArrayList<ChatInter>()
        private var context: Context? = null

        constructor(context: Context, notesList: ArrayList<ChatInter>) : super() {
            this.notesList = notesList
            this.context = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

            val view: View?
            val vh: ViewHolder

            if (convertView == null) {
                view = layoutInflater.inflate(R.layout.chat, parent, false)
                vh = ViewHolder(view)
                view.tag = vh
                Log.i("JSA", "set Tag for ViewHolder, position: " + position)
            } else {
                view = convertView
                vh = view.tag as ViewHolder
            }

            vh.tvTitle.text = notesList[position].nameUser
            vh.tvContent.text = notesList[position].lastMess

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
            this.tvTitle = view?.findViewById(R.id.user_nick) as TextView
            this.tvContent = view.findViewById(R.id.user_smth) as TextView
        }

    }

    fun selectedChat(view: View?) {
        val intent = Intent(this, Chat::class.java)
        startActivity(intent)
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View,
                                     menuInfo: ContextMenu.ContextMenuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val inflater = menuInflater
        inflater.inflate(R.menu.chat_context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info: AdapterContextMenuInfo = item.menuInfo as AdapterContextMenuInfo
        when (item.itemId) {
            R.id.hide -> {
                val queue = Volley.newRequestQueue(this)
                val url = "https://caseidilia.herokuapp.com/api/$userName/dialog/hide"
                val nnn = JSONObject()
                nnn.put("participant", listChat[info.id.toInt()].lastMess)
                nnn.put("pin", edTex)

                val jsonObjectRequest = SpecialJsonObjectRequest(Request.Method.POST, url,
                        nnn,
                        Response.Listener { response ->
                        },
                        Response.ErrorListener { error ->
                            if (String(error.networkResponse.data).contains("No pin")) {
                                val builder = AlertDialog.Builder(this)
                                builder.setTitle("Firstly you need to set your PIN")
                                builder.setPositiveButton("Yes") { dialog, which ->
                                    val intent = Intent(this, Settings::class.java)
                                    startActivity(intent)
                                }
                                builder.setNegativeButton("Of course") { dialog, which ->
                                    val intent = Intent(this, Settings::class.java)
                                    startActivity(intent)
                                }
                                val dialog: AlertDialog = builder.create()
                                dialog.show()
                            }
                            Toast.makeText(applicationContext, String(error.networkResponse.data), Toast.LENGTH_SHORT).show()
                        }
                )

                queue.add(jsonObjectRequest)
                Toast.makeText(applicationContext, "Uno", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.show -> {
                val queue = Volley.newRequestQueue(this)
                val url = "https://caseidilia.herokuapp.com/api/$userName/dialog/hide"
                val nnn = JSONObject()
                nnn.put("participant", listChat[info.id.toInt()].lastMess)
                nnn.put("pin", edTex)

                val jsonObjectRequest = SpecialJsonObjectRequest(Request.Method.POST, url,
                        nnn,
                        Response.Listener { response ->
                        },
                        Response.ErrorListener { error ->
                            if (String(error.networkResponse.data).contains("No pin")) {
                                val builder = AlertDialog.Builder(this)
                                builder.setTitle("Firstly you need to set your PIN")
                                builder.setPositiveButton("Yes") { dialog, which ->
                                    val intent = Intent(this, Settings::class.java)
                                    startActivity(intent)
                                }
                                builder.setNegativeButton("Of course") { dialog, which ->
                                    val intent = Intent(this, Settings::class.java)
                                    startActivity(intent)
                                }
                                val dialog: AlertDialog = builder.create()
                                dialog.show()
                            }
                            Toast.makeText(applicationContext, String(error.networkResponse.data), Toast.LENGTH_SHORT).show()
                        }
                )

                queue.add(jsonObjectRequest)
                Toast.makeText(applicationContext, "Uno", Toast.LENGTH_SHORT).show()
                //editItem(info.position) // метод, выполняющий действие при редактировании пункта меню
                return true
            }
            R.id.delete -> {
                val queue = Volley.newRequestQueue(this)
                val url = "https://caseidilia.herokuapp.com/api/$userName/dialog/delete"
                val nnn = JSONObject()
                nnn.put("participant", listChat[info.id.toInt()].lastMess)

                val jsonObjectRequest = SpecialJsonObjectRequest(Request.Method.POST, url,
                        nnn,
                        Response.Listener { response ->
                            listChat.removeAt(info.id.toInt())
                        },
                        Response.ErrorListener { error ->
                            Toast.makeText(applicationContext, String(error.networkResponse.data), Toast.LENGTH_SHORT).show()
                        }
                )

                queue.add(jsonObjectRequest)
                //deleteItem(info.position) //метод, выполняющий действие при удалении пункта меню
                return true
            }
            R.id.rename -> {
                val dialogV: View = LayoutInflater.from(this).inflate(R.layout.activity_dialog_pin, null)
                val builder = AlertDialog.Builder(this)
                builder.setView(dialogV)
                builder.setTitle("Enter new name")
                val valueKey = findViewById<EditText>(R.id.input_pin)

                val queue = Volley.newRequestQueue(this)
                val url = "https://caseidilia.herokuapp.com/api/$userName/dialog/changeName"
                val nnn = JSONObject()
                for (i in 0 until listDialog.length()) {
                    if (listChat[info.id.toInt()].nameUser == listDialog.getJSONObject(i).getString("name")) {
                        nnn.put("participant", listDialog.getJSONObject(i).getString("login"))
                        nnn.put("name", valueKey.text.toString())
                    }
                }

                val jsonObjectRequest = SpecialJsonObjectRequest(Request.Method.POST, url,
                        nnn,
                        Response.Listener { response ->
                        },
                        Response.ErrorListener { error ->
                            Toast.makeText(applicationContext, String(error.networkResponse.data), Toast.LENGTH_SHORT).show()
                        }
                )
                builder.setPositiveButton("Rename") { dialog, which ->
                    for (i in 0 until listDialog.length()) {
                        if (listChat[info.id.toInt()].nameUser == listDialog.getJSONObject(i).getString("name")) {
                            nnn.put("participant", listDialog.getJSONObject(i).getString("login"))
                            nnn.put("name", valueKey.text.toString())
                        }
                    }
                    queue.add(jsonObjectRequest)
                }
                builder.setNegativeButton("Cancel") { dialog, which ->
                }
                val dialog: AlertDialog = builder.create()
                dialog.show()


                return true
            }
        }
        return super.onContextItemSelected(item)
    }

    private fun requRen(str: String, pos: Int) {
        val queue = Volley.newRequestQueue(this)
        val url = "https://caseidilia.herokuapp.com/api/$userName/dialog/changeName"
        val nnn = JSONObject()
        for (i in 0 until listDialog.length()) {
            if (listChat[pos].nameUser == listDialog.getJSONObject(i).getString("name")) {
                nnn.put("participant", listDialog.getJSONObject(i).getString("login"))
                nnn.put("name", str)
            }
        }

        val jsonObjectRequest = SpecialJsonObjectRequest(Request.Method.POST, url,
                nnn,
                Response.Listener { response ->
                },
                Response.ErrorListener { error ->
                    Toast.makeText(applicationContext, String(error.networkResponse.data), Toast.LENGTH_SHORT).show()
                }
        )

        queue.add(jsonObjectRequest)
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
                val url = "https://caseidilia.herokuapp.com/api/loupa/invite"
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Code for registration")
                builder.setNeutralButton("OK") { dialog, which ->

                }
                val dialog: AlertDialog = builder.create()

                val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                        Response.Listener { response ->
                            val s = response.getString("secret")
                            dialog.setMessage(s)
                            dialog.show()
                        },
                        Response.ErrorListener { error ->
                            Toast.makeText(applicationContext, "Oops, smth went wrong", Toast.LENGTH_SHORT).show()
                        }

                )

                queue.add(jsonObjectRequest)

                return true
            }
            R.id.show -> {
                val queue = Volley.newRequestQueue(this)
                val url = "https://caseidilia.herokuapp.com/api/$userName/dialogs/hidden"
                var nnn = JSONArray()
                var ooo = JSONObject()
                ooo.put("pin", "333")
                nnn.put(ooo)

                val jsonArrayRequest = JsonArrayRequest(Request.Method.POST, url, nnn,
                        Response.Listener { response ->
                            listChat.removeAll(listChat)
                            for (i in 0 until response.length()){
                                listChat.add(ChatInter(response.getJSONObject(i).getString("name"),
                                        response.getJSONObject(i).getString("login")))
                            }
                        },
                        Response.ErrorListener { error ->
                            Toast.makeText(applicationContext, String(error.networkResponse.data), Toast.LENGTH_SHORT).show()
                        }
                )

                queue.add(jsonArrayRequest)

                return true
            }
            R.id.hide -> {
                val dialogV: View = LayoutInflater.from(this).inflate(R.layout.activity_dialog_pin, null)
                val builder = AlertDialog.Builder(this)
                builder.setView(dialogV)
                builder.setTitle("Enter PIN")
                builder.setPositiveButton("Yes") { dialog, which ->
                    Toast.makeText(applicationContext, "Perfect", Toast.LENGTH_SHORT).show()
                }
                builder.setNegativeButton("No") { dialog, which ->
                    Toast.makeText(applicationContext, "Bad", Toast.LENGTH_SHORT).show()
                }
                val dialog: AlertDialog = builder.create()
                dialog.show()

                return true
            }
            R.id.findu -> {
                val dialogV: View = LayoutInflater.from(this).inflate(R.layout.activity_dialog_pin, null)
                val builder = AlertDialog.Builder(this)
                builder.setView(dialogV)
                builder.setTitle("Enter user name")
                builder.setPositiveButton("Search") { dialog, which ->
                }
                builder.setNegativeButton("Cansel") { dialog, which ->
                }
                val dialog: AlertDialog = builder.create()
                dialog.show()

                return true
            }
            R.id.findch -> {
                val dialogV: View = LayoutInflater.from(this).inflate(R.layout.activity_dialog_pin, null)
                val builder = AlertDialog.Builder(this)
                builder.setView(dialogV)
                builder.setTitle("Enter chat name")
                builder.setPositiveButton("Search") { dialog, which ->
                }
                builder.setNegativeButton("Cancel") { dialog, which ->
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

