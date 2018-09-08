package examples.android.vish.com.chatbot.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import examples.android.vish.com.chatbot.db.model.Conversation

@Dao
abstract class ConversationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(conversation: Conversation)


    @Query("SELECT * from Conversation")
    abstract fun getConversation();
}