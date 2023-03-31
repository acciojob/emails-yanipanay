package com.driver;

import java.util.*;

public class Gmail extends Email {

    HashMap<String,Mail> MailDB;

    ArrayList<String> trash;

    Deque<String> Inbox;

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
        MailDB = new HashMap<>();
        Inbox= new ArrayDeque<>();
        trash = new ArrayList<>();
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        MailDB.put(message,new Mail(date,sender,message));
        if(Inbox.size()==inboxCapacity){
            trash.add(Inbox.pollLast());
        }
        Inbox.addFirst(message);
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing

        Iterator iterator = Inbox.iterator();
        while(iterator.hasNext()){
            String msg = (String) iterator.next();
            if(message.compareTo(msg)==0){
                Inbox.remove(message);
                trash.add(message);
            }
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        return Inbox.peekFirst();
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        return Inbox.peekLast();
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date

        Iterator iterator = Inbox.iterator();
        int ans = 0;
        while(iterator.hasNext()){
            String msg = (String) iterator.next();
            Mail mail = MailDB.get(msg);
            if(mail.getDate().compareTo(start)>=0 && mail.getDate().compareTo(end)<=0 ) ans++;
            }
        return ans;
        }

    public int getInboxSize(){
        // Return number of mails in inbox
        return Inbox.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        trash.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }
}
