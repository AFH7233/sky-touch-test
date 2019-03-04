package com.afh.skytouch.managment.queue.listeners;

import com.afh.skytouch.commons.dto.ProductStatus;
import com.afh.skytouch.managment.inboxes.QueueInbox;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StatusListenerTest {
    private ProductStatus message;

    private QueueInbox<String,ProductStatus> inbox;

    private StatusListener listener;

    private String productId = "random";

    @Before
    public void setUp() throws Exception {
        message = new ProductStatus();
        message.setProductId(productId);
        listener = new StatusListener();
        inbox = new QueueInbox<>();
    }

    @Test
    public void readWriteTest() {
        listener.setInbox(inbox);
        listener.onMessage(message);
        assertEquals(message,inbox.readMessage(productId).get());
    }

    @Test
    public  void nullWriteTest(){
        listener.onMessage(null);
        assertTrue(inbox.isEmpty());
    }
}