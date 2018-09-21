package com.afh.skytouch.managment.inboxes;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class QueueInbox<K,V> {

    private ConcurrentHashMap<K,V> box = new ConcurrentHashMap<>();

    public Optional<V> readMessage(K key){
        V message = box.get(key);
        if(message != null){
            box.remove(key);
            return Optional.of(message);
        }
        return Optional.empty();
    }

    public void writeMessage(K key,V value){
        Objects.requireNonNull(key,"The key of the message should not be null");
        Objects.requireNonNull(value,"The inbox does not allow null messages");
        box.putIfAbsent(key,value);
    }

    public boolean isMessagePresent(K key){
        return box.containsKey(key);
    }

    public boolean isEmpty(){
        return box.isEmpty();
    }
}
