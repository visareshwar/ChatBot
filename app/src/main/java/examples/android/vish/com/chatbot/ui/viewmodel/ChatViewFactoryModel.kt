package examples.android.vish.com.chatbot.ui.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import examples.android.vish.com.chatbot.db.repo.ConversationRepo

class ChatViewFactoryModel(private val conversationRepo: ConversationRepo) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ChatViewModel(conversationRepo) as T
    }
}