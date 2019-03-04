package com.afh.skytouch.managment.inboxes;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class QueueInboxTest {

    private QueueInbox<String,String> simpleInbox;

    private String test = "Hola mundo";

    private String key = "Saludo";

    @Before
    public void setUp() throws Exception {
        simpleInbox = new QueueInbox<>();
    }

    @Test
    public void readMessage() {
        simpleInbox.writeMessage(key,test);
        assertEquals(test,simpleInbox.readMessage(key).get());
    }

    @Test
    public void readAndEraseMessage() {
        simpleInbox.writeMessage(key,test);
        simpleInbox.readMessage(key);
        assertTrue(simpleInbox.isEmpty());
    }

    @Test
    public void readNonExistingMessage() {
        Optional<String> message = simpleInbox.readMessage(key);
        assertFalse(message.isPresent());
    }

    @Test
    public void writeMessage() {
        simpleInbox.writeMessage(key,test);
        boolean isWellWritten = simpleInbox.isMessagePresent(key) &&
                                simpleInbox.readMessage(key).get().equals(test);
        assertTrue(isWellWritten);
    }

    @Test(expected = NullPointerException.class)
    public void writeNullMessage(){
        simpleInbox.writeMessage("test",null);
        fail("Expected null pointer exception");
    }

    @Test(expected = NullPointerException.class)
    public void writeWithNullKey(){
        simpleInbox.writeMessage(null,"message");
        fail("Expected null pointer exception");
    }

    @Test
    public void isMessagePresent() {
        simpleInbox.writeMessage(key,test);
        assertTrue(simpleInbox.isMessagePresent(key));
    }

    @Test
    public void isMessageNotPresent() {
        simpleInbox.writeMessage(key,test);
        assertFalse(simpleInbox.isMessagePresent("anotherKey"));
    }

    @Test
    public void isEmpty() {
        assertTrue(simpleInbox.isEmpty());
    }
}