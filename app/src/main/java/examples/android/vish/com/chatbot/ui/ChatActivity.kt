package examples.android.vish.com.chatbot.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.util.Log
import android.view.View
import examples.android.vish.com.chatbot.R
import examples.android.vish.com.chatbot.db.model.Conversation
import examples.android.vish.com.chatbot.ui.adapter.ConversationAdapter
import examples.android.vish.com.chatbot.ui.viewmodel.ChatViewModel
import examples.android.vish.com.chatbot.util.InjectionUtil
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity() {

    private lateinit var viewModel: ChatViewModel

    private val mConversationList: ArrayList<Conversation> = ArrayList()

    private lateinit var conversationAdapter: ConversationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        conversationAdapter = ConversationAdapter(mConversationList);

        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.setStackFromEnd(true);
        conversation_list.layoutManager = linearLayoutManager
        conversation_list.adapter = conversationAdapter


        val factory = InjectionUtil.getViewModelFactor(this)

        viewModel = ViewModelProviders.of(this, factory).get(ChatViewModel::class.java)
        viewModel.getBotResponse("").observe(this, msgObserver)
    }

    val msgObserver = Observer<List<Conversation>> { list ->
        if (list != null) {
            Log.d("ChatActivity", "list" + list.size)
            mConversationList.clear()
            mConversationList.addAll(list)
            conversationAdapter.notifyDataSetChanged()

            conversation_list.scrollToPosition(mConversationList.size - 1)
        }
    }

    fun onSendClicked(view: View) {
        val msg = msg_edit_text.text
        if (TextUtils.isEmpty(msg)) {
            return
        }

        viewModel.getBotResponse(msg.toString())
        msg_edit_text.text.clear()
    }

}
