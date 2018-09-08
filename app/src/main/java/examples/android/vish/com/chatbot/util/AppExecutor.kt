package examples.android.vish.com.chatbot.util

import java.util.concurrent.Executor
import java.util.concurrent.Executors

class AppExecutor private constructor() {
    private val diskIO: Executor


    init {
        this.diskIO = Executors.newSingleThreadExecutor()
    }

    fun diskIO(): Executor {
        return diskIO
    }

    companion object {

        // For Singleton instantiation
        private val LOCK = Any()
        private var sInstance: AppExecutor? = null

        val instance: AppExecutor
            get() {
                if (sInstance == null) {
                    synchronized(LOCK) {
                        sInstance = AppExecutor()
                    }
                }
                return sInstance!!
            }
    }

}