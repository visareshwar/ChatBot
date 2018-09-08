package examples.android.vish.com.chatbot.ui

import android.arch.lifecycle.MutableLiveData
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import examples.android.vish.com.chatbot.R
import examples.android.vish.com.chatbot.db.model.Conversation
import examples.android.vish.com.chatbot.db.repo.ConversationRepo
import examples.android.vish.com.chatbot.ui.adapter.ConversationAdapter
import examples.android.vish.com.chatbot.ui.viewmodel.ChatViewModel
import examples.android.vish.com.chatbot.util.DataGenerator
import examples.android.vish.com.chatbot.util.InjectionUtil
import examples.android.vish.com.chatbot.util.ViewModelUtil
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ChatActivityTest {

    private val conversationLiveData = MutableLiveData<List<Conversation>>()


    @Before
    fun setUp() {
        val chatViewModel = mock(ChatViewModel::class.java)
        val catchesInteractorImpl = mock(ConversationRepo::class.java)
        val factory = ViewModelUtil.createFor(chatViewModel, catchesInteractorImpl)
        InjectionUtil.swap(factory)
        `when`(chatViewModel.getBotResponse("")).thenReturn(conversationLiveData)
    }

    @Test
    fun testConversationList() {
        val activity = Robolectric.setupActivity(ChatActivity::class.java)

        val chatRecyclerView = activity.findViewById<RecyclerView>(R.id.conversation_list)


        chatRecyclerView.measure(0, 0)
        chatRecyclerView.layout(0, 0, 100, 100)

        assertNotNull(chatRecyclerView.adapter)
        assertEquals(chatRecyclerView.adapter.itemCount, 0)

        val conversationList = DataGenerator.getConversation()
        conversationLiveData.postValue(conversationList)


        assertNotNull(chatRecyclerView.adapter)
        assertEquals(chatRecyclerView.adapter.itemCount, conversationList.size)

        val holder1 = chatRecyclerView.findViewHolderForLayoutPosition(conversationList.size - 1)
        val itemView1 = holder1.itemView as TextView
        assertEquals(itemView1.text, conversationList.get(conversationList.lastIndex).message)
        assertTrue(holder1 is ConversationAdapter.OutMsgViewHolder)
    }
}