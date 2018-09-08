package examples.android.vish.com.chatbot.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import examples.android.vish.com.chatbot.db.dao.ConversationDao
import examples.android.vish.com.chatbot.db.model.Conversation

@Database(entities = arrayOf(Conversation::class), version = 1, exportSchema = false)
abstract class BotDataBase : RoomDatabase() {

    // The associated DAOs for the database
    abstract val conversationDao: ConversationDao

    companion object {
        private const val DATABASE_NAME = "conversation"


        // For Singleton instantiation
        private val LOCK = Any()

        private var sInstance: BotDataBase? = null

        fun getsInstance(context: Context): BotDataBase {
            if (sInstance == null) {
                synchronized(LOCK) {
                    sInstance = Room.databaseBuilder(context.applicationContext,
                            BotDataBase::class.java, DATABASE_NAME).build()
                }
            }
            return sInstance!!
        }
    }

}