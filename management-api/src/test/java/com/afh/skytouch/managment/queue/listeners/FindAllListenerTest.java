package com.afh.skytouch.managment.queue.listeners;

import com.afh.skytouch.commons.dto.FindAllMessage;
import com.afh.skytouch.managment.inboxes.QueueInbox;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FindAllListenerTest {

    private FindAllMessage message;

    private QueueInbox<String,FindAllMessage> inbox;

    private FindAllListener listener;

    @Before
    public void setUp() throws Exception {
         message = new FindAllMessage();
         listener = new FindAllListener();
         inbox = new QueueInbox<>();
    }

    @Test
    public void readWriteTest() {
        listener.setInbox(inbox);
        listener.onMessage(message);
        assertEquals(message,inbox.readMessage(message.getId()).get());
    }

    @Test
    public  void nullWriteTest(){
        listener.onMessage(null);
        assertTrue(inbox.isEmpty());
    }

}