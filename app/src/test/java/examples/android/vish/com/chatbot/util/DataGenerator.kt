package examples.android.vish.com.chatbot.util

import examples.android.vish.com.chatbot.db.model.Conversation

object DataGenerator {

    fun getConversation(): List<Conversation> {
        val catchList = ArrayList<Conversation>()
        for (i in 1..10) {

            catchList.add(getRandomCatch(i))

        }

        return catchList
    }

    private fun getRandomCatch(index: Int): Conversation {

        val conversation = Conversation("message no $index", index % 2 == 0);
        conversation.id = index
        return conversation

    }
}