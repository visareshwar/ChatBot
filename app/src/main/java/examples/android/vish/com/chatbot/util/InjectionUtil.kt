package examples.android.vish.com.chatbot.util

import android.content.Context
import android.support.annotation.VisibleForTesting
import examples.android.vish.com.chatbot.db.BotDataBase
import examples.android.vish.com.chatbot.db.repo.ConversationNetworkRepo
import examples.android.vish.com.chatbot.db.repo.ConversationRepo
import examples.android.vish.com.chatbot.ui.viewmodel.ChatViewFactoryModel

object InjectionUtil {
    private var mFactory: ChatViewFactoryModel? = null

    fun getViewModelFactor(context: Context): ChatViewFactoryModel {
        if (mFactory == null) {
            val repo = ConversationRepo.getInstance(BotDataBase.getsInstance(context).conversationDao, ConversationNetworkRepo.getInstance(), AppExecutor.instance)


            mFactory = ChatViewFactoryModel(repo)
        }
        return mFactory!!
    }

    @VisibleForTesting
    fun swap(factory: ChatViewFactoryModel) {
        mFactory = factory
    }
}