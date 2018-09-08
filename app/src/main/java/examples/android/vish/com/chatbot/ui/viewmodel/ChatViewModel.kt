package examples.android.vish.com.chatbot.ui.viewmodel

import android.arch.lifecycle.ViewModel
import examples.android.vish.com.chatbot.db.repo.ConversationRepo

class ChatViewModel(val conversationRepo: ConversationRepo) : ViewModel() {

    fun getBotResponse(msg: String) {
        conversationRepo.fetchResponse(msg)
    }
}