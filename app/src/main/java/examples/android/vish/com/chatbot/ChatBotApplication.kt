package examples.android.vish.com.chatbot

import android.app.Application


class ChatBotApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        var appInstance: ChatBotApplication? = null
            private set
    }
}