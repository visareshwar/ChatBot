package examples.android.vish.com.chatbot.db.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Conversation(


        @SerializedName("message") val message: String, // Same as before! Doing great

        val isBot: Boolean = false
){
        @PrimaryKey(autoGenerate = true)
        val id: Int? =null
}