package examples.android.vish.com.chatbot.util

import android.arch.lifecycle.ViewModel
import examples.android.vish.com.chatbot.db.repo.ConversationRepo
import examples.android.vish.com.chatbot.ui.viewmodel.ChatViewFactoryModel


object ViewModelUtil {

    fun <T : ViewModel> createFor(model: T, repo: ConversationRepo): ChatViewFactoryModel {

        return object : ChatViewFactoryModel(repo) {

            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(model.javaClass)) {

                    return model as T
                }
                throw IllegalArgumentException("unexpected model class $modelClass")
            }
        }
    }

}