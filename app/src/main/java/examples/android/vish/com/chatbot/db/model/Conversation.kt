package examples.android.vish.com.chatbot.db.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
class Conversation(
        @SerializedName("message") val message: String, // Same as before! Doing great
        var isBot: Boolean = false
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}