package examples.android.vish.com.chatbot.ui.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import examples.android.vish.com.chatbot.db.model.Conversation
import examples.android.vish.com.chatbot.db.repo.ConversationRepo

class ChatViewModel(private val conversationRepo: ConversationRepo) : ViewModel() {

    fun getBotResponse(msg: String): LiveData<List<Conversation>> {
        return conversationRepo.fetchResponse(msg)
    }
}