package examples.android.vish.com.chatbot.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import examples.android.vish.com.chatbot.R
import examples.android.vish.com.chatbot.db.model.Conversation

class ConversationAdapter(val conversationList: List<Conversation>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val IN_MSG = 0
        const val OUT_MSG = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == OUT_MSG) {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_out_msg, parent, false)
            return OutMsgViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_in_msg, parent, false)
            return InMsgViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return conversationList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is InMsgViewHolder) {
            holder.inMsgTV.text = conversationList.get(position).message
        } else
            (holder.itemView as TextView).text = conversationList.get(position).message
    }

    override fun getItemViewType(position: Int): Int {
        return if (conversationList.get(position).isBot) {
            OUT_MSG
        } else {
            IN_MSG
        }
    }

    class InMsgViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val inMsgTV = view.findViewById<TextView>(R.id.in_msg_tv)
    }

    class OutMsgViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}