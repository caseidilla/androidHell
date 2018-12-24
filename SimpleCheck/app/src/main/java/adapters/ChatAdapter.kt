package adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.tolliand.simplecheck.R
import com.example.tolliand.simplecheck.ChMessage

class ChatAdapter(private var items: ArrayList<ChMessage>): RecyclerView.Adapter<ChatAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.activity_chat_mess, parent, false)

        return ChatAdapter.ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size    }

    override fun onBindViewHolder(holder: ChatAdapter.ViewHolder, position: Int) {
        var message = items[position]
        holder?.userName?.text = message.messageUser
        holder?.messText?.text = message.messageText
        holder?.messTime?.text = message.messageTime.toString()
    }

    class ViewHolder(row: View): RecyclerView.ViewHolder(row) {
        var userName: TextView? = null
        var messText: TextView? = null
        var messTime: TextView? = null

        init {
            this.userName = row?.findViewById(R.id.message_user)
            this.messText = row?.findViewById(R.id.message_text)
            this.messTime = row?.findViewById(R.id.message_time)
        }

    }
}