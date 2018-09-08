package examples.android.vish.com.chatbot.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import examples.android.vish.com.chatbot.R
import examples.android.vish.com.chatbot.db.BotDataBase
import examples.android.vish.com.chatbot.db.repo.ConversationNetworkRepo
import examples.android.vish.com.chatbot.db.repo.ConversationRepo
import examples.android.vish.com.chatbot.ui.viewmodel.ChatViewFactoryModel
import examples.android.vish.com.chatbot.ui.viewmodel.ChatViewModel
import examples.android.vish.com.chatbot.util.AppExecutor

class ChatActivity : AppCompatActivity() {

    private lateinit var viewModel: ChatViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val repo = ConversationRepo.getInstance(BotDataBase.getsInstance(this).conversationDao, ConversationNetworkRepo.getInstance(), AppExecutor.instance)
        val factory = ChatViewFactoryModel(repo)

        viewModel = ViewModelProviders.of(this, factory).get(ChatViewModel::class.java)

        viewModel.getBotResponse(this, "hi").observe(this, Observer { list ->
            if (list != null) {
                Log.d("ChatActivity", "list" + list.size)
            }
        })
    }


}
