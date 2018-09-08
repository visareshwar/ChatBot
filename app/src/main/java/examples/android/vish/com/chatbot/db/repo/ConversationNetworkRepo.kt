package examples.android.vish.com.chatbot.db.repo

import android.arch.lifecycle.MutableLiveData
import android.text.TextUtils
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import examples.android.vish.com.chatbot.ChatBotApplication
import examples.android.vish.com.chatbot.db.model.Conversation
import org.json.JSONObject

class ConversationNetworkRepo private constructor() {
    val currentPrListLiveData: MutableLiveData<Conversation> = MutableLiveData()

    companion object {
        @Volatile
        private var INSTANCE: ConversationNetworkRepo? = null

        fun getInstance(): ConversationNetworkRepo =
                INSTANCE ?: synchronized(this) {
                    INSTANCE
                            ?: ConversationNetworkRepo().also { ConversationNetworkRepo.INSTANCE = it }
                }

        private val URL = "https://www.personalityforge.com/api/chat/?apiKey=6nt5d1nJHkqbkphe&chatBotID=63906&externalID=chirag1&message="

    }

    fun getBotResponse( message: String) {

        val request = JsonObjectRequest(URL + message, null,
                Response.Listener<JSONObject> { response ->
                    Log.d("ConversationNetworkRepo", response.toString())
                    val conversation = response.optJSONObject("message")
                    if (conversation != null && !TextUtils.isEmpty(conversation.toString())) {
                        val gson = Gson()
                        val conversationEntity = gson.fromJson(conversation.toString(), Conversation::class.java)
                        conversationEntity.isBot = true
                        currentPrListLiveData.postValue(conversationEntity)
                    }

                },


                Response.ErrorListener { error ->
                    error.printStackTrace()
                })

        Volley.newRequestQueue(ChatBotApplication.appInstance).add(request)

    }

}