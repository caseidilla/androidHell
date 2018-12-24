package adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.tolliand.simplecheck.ChatInter
import com.example.tolliand.simplecheck.R


class MAdapter(private var items: ArrayList<ChatInter>): RecyclerView.Adapter<MAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.list_main, parent, false)

        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var chatInter = items[position]
        holder?.userName?.text = chatInter.nameUser
        holder?.lastMess?.text = chatInter.lastMess
    }

    class ViewHolder(row: View): RecyclerView.ViewHolder(row) {
        var userName: TextView? = null
        var lastMess: TextView? = null

        init {
            this.userName = row?.findViewById(R.id.user_name)
            this.lastMess = row?.findViewById(R.id.last_message)
        }

    }
}