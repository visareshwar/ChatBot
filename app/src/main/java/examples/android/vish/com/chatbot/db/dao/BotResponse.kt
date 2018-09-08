package examples.android.vish.com.chatbot.db.dao

import com.google.gson.annotations.SerializedName


data class BotResponse(
        @SerializedName("success") val success: Int, // 1
        @SerializedName("errorMessage") val errorMessage: String,
        @SerializedName("message") val message: Message
        //@SerializedName("data") val data: List<Any>
) {

    data class Message(
            @SerializedName("chatBotName") val chatBotName: String, // Cyber Ty
            @SerializedName("chatBotID") val chatBotID: Int, // 63906
            @SerializedName("message") val message: String, // Same as before! Doing great
            @SerializedName("emotion") val emotion: String
    )
}