/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chartapp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author kundi
 */

  

public class Message {
    private String payload;
    private boolean isSent;
    private boolean isReceived;
    private boolean isRead;
    private String recipient;
    private String sender;
    private LocalDateTime timestamp;
    
    // Constructor
    public Message(String payload, String sender, String recipient) {
        this.payload = payload;
        this.sender = sender;
        this.recipient = recipient;
        this.isSent = false;
        this.isReceived = false;
        this.isRead = false;
        this.timestamp = LocalDateTime.now();
    }
    
    // Getters and Setters
    public String getPayload() {
        return payload;
    }
    
    public void setPayload(String payload) {
        this.payload = payload;
    }
    
    public boolean isSent() {
        return isSent;
    }
    
    public void setSent(boolean sent) {
        isSent = sent;
    }
    
    public boolean isReceived() {
        return isReceived;
    }
    
    public void setReceived(boolean received) {
        isReceived = received;
    }
    
    public boolean isRead() {
        return isRead;
    }
    
    public void setRead(boolean read) {
        isRead = read;
    }
    
    public String getRecipient() {
        return recipient;
    }
    
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
    
    public String getSender() {
        return sender;
    }
    
    public void setSender(String sender) {
        this.sender = sender;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public String getFormattedTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return timestamp.format(formatter);
    }
    
    public String getStatusString() {
        if (isRead) return "✓✓ Read";
        if (isReceived) return "✓✓ Received";
        if (isSent) return "✓ Sent";
        return "Pending";
    }
    
    @Override
    public String toString() {
        return String.format("[%s] %s: %s [%s]", 
            getFormattedTimestamp(), sender, payload, getStatusString());
    }
}
