package com.driver;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;

public class MailRepository {
    HashMap<String,Mail> MailDB;

    ArrayList<String> trash;

    Deque<String> Inbox;

    public MailRepository() {
        MailDB = new HashMap<>();
        Inbox= new ArrayDeque<>();
        trash = new ArrayList<>();
    }

    public int inboxSize(){
        return Inbox.size();
    }

    public void addToTrash(String message){
        trash.add(message);
    }

    public void addToMail(Mail mail){
        String message = mail.getMessage();
        MailDB.put(message,mail);
    }

    public void removeMail(String message){

    }

    public void addToInbox(String message){
        Inbox.addFirst(message);
    }

    public String pollLastInbox(){
        return Inbox.pollLast();
    }

    public String getLastInbox(){
        return  Inbox.getLast();
    }

    public String getFirstInbox(){
        return Inbox.getFirst();
    }


}
