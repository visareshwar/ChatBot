package examples.android.vish.com.chatbot.db.repo

import android.arch.lifecycle.LiveData
import android.content.Context
import examples.android.vish.com.chatbot.db.dao.ConversationDao
import examples.android.vish.com.chatbot.db.model.Conversation
import examples.android.vish.com.chatbot.util.AppExecutor

class ConversationRepo private constructor() {


    private lateinit var conversationDao: ConversationDao
    private lateinit var executor: AppExecutor

    private lateinit var githubNetworkRepo: ConversationNetworkRepo

    private fun startConversationNetworkRepo() {
        val data = githubNetworkRepo.currentPrListLiveData
        data.observeForever { conversation ->
            executor.diskIO().execute {
                if (conversation != null)
                    conversationDao.insert(conversation)
            }
        }
    }

    private fun insertUserMsg(userMsg: String) {

        executor.diskIO().execute {
            val conversation = Conversation(userMsg)
            conversationDao.insert(conversation)
        }
    }

    fun fetchResponse(context: Context, userMsg: String): LiveData<List<Conversation>> {
        insertUserMsg(userMsg)
        githubNetworkRepo.getBotResponse(context, userMsg)
        return conversationDao.getConversation()
    }

    companion object {

        @Volatile
        private var INSTANCE: ConversationRepo? = null

        fun getInstance(conversationDao: ConversationDao, conversationNetworkRepo: ConversationNetworkRepo, executor: AppExecutor): ConversationRepo =
                INSTANCE ?: synchronized(this) {
                    INSTANCE
                            ?: buildConversationRepo(conversationDao, conversationNetworkRepo, executor).also { INSTANCE = it }
                }

        private fun buildConversationRepo(conversationDao: ConversationDao, conversationNetworkRepo: ConversationNetworkRepo, executor: AppExecutor): ConversationRepo {
            val instance = ConversationRepo()
            instance.conversationDao = conversationDao
            instance.githubNetworkRepo = conversationNetworkRepo
            instance.executor = executor
            instance.startConversationNetworkRepo()
            return instance
        }


    }


}