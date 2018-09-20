package com.afh.skytouch.managment.inboxes;

import java.util.concurrent.ConcurrentHashMap;

public class QueueInbox<K,V> {

    private ConcurrentHashMap<K,V> box = new ConcurrentHashMap<>();

    public V readMessage(K key){
        V message = box.get(key);
        if(message != null){
            box.remove(key);
        }
        return message;
    }

    public void writeMessage(K key,V value){
        box.putIfAbsent(key,value);
    }

    public boolean isMessagePresent(K key){
        return box.containsKey(key);
    }
}
