package examples.android.vish.com.chatbot.db.repo

import examples.android.vish.com.chatbot.db.dao.ConversationDao
import examples.android.vish.com.chatbot.db.model.Conversation
import examples.android.vish.com.chatbot.util.AppExecutor

class ConversationRepo private constructor() {


    private lateinit var conversationDao: ConversationDao


    private lateinit var githubNetworkRepo: ConversationNetworkRepo

    private fun startConversationNetworkRepo(executor: AppExecutor) {
        val data = githubNetworkRepo.currentPrListLiveData
        data.observeForever { conversation ->
            executor.diskIO().execute {
                if (conversation != null)
                    conversationDao.insert(conversation)
            }
        }
    }

    private fun insertUserMsg(userMsg: String) {
        val conversation = Conversation(userMsg)
        conversationDao.insert(conversation)
    }

    fun fetchResponse(userMsg: String) {
        insertUserMsg(userMsg)
        githubNetworkRepo.getBotResponse(userMsg)
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
            instance.startConversationNetworkRepo(executor)
            return instance
        }


    }


}