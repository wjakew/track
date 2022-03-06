/*
Jakub Wawak
kubawawak@gmail.com
all rights reseved
 */
package com.jakubwawak.track.connector;

import com.google.gson.JsonElement;
import com.mashape.unirest.http.exceptions.UnirestException;
import javax.swing.JFrame;

/**
 *Object for connecting to api and maintaining room messages
 * @author jakubwawak
 */
public class Messages_Connector {
    
    Connector connector;
    
    /**
     * Constructor
     * @param connector 
     */
    public Messages_Connector(Connector connector){
        this.connector = connector;
    }
    
    
    /**
     * Function for sending message data
     * @param message
     * @param room
     * @return JsonElement
     * /room-message-send/{app_token}/{session_token}/{room_id}/{message_content}
     */
    public JsonElement send_message(String message,int room_id,JFrame object) throws UnirestException{
        message = message.replaceAll(" ", "%20");
        return connector.commit("/room-message-send/"+connector.oauth.app_token+"/"+connector.oauth.session_token+
                "/"+room_id+"/"+message,object);
    }
    
    /**
     * Function for getting all messages info
     * @param room_id
     * @return JsonElement
     */
    public JsonElement get_messages(int room_id,JFrame object) throws UnirestException{
        return connector.commit("/room-messages/"+connector.oauth.app_token+"/"+connector.oauth.session_token+
                "/"+room_id,object);
    }
}
