/*
Jakub Wawak
kubawawak@gmail.com
all rights reseved
 */
package com.jakubwawak.track.connector;

import com.google.gson.JsonElement;
import com.mashape.unirest.http.exceptions.UnirestException;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *Object for connecting and loading Room data
 * @author jakubwawak
 */
public class Room_Connector {
    
    Connector connector;
    /**
     * Constructor
     * @param connector 
     */
    public Room_Connector(Connector connector){
        this.connector = connector;
    }
    
    /**
     * Function for creating room
     * @param room_name
     * @param room_desc
     * @return JsonElement
     */
    public JsonElement create_room(String room_name,String room_desc,JDialog object) throws UnirestException{
        room_name = room_name.replaceAll(" ", "%20");
        room_desc = room_desc.replaceAll(" ", "%20");
        return connector.commit("/room-create/"+connector.oauth.app_token+"/"+connector.oauth.session_token+"/"+room_name+"/"+room_desc,object);
    }
    
    /**
     * Function for removing room from database
     * @param room_id
     * @return JsonElement
     * /room-remove/{app_token}/{session_token}/{room_id}/{room_password}
     */
    public JsonElement remove_room(int room_id,String room_password,JDialog object) throws UnirestException{
        return connector.commit("/room-remove/"+connector.oauth.app_token+"/"+connector.oauth.session_token+"/"+room_id+"/"+room_password,object);
    }
    
    /**
     * Function for adding room member
     * @param roo
     * @return JsonElement
     * /room-addmember/{app_token}/{session_token}/{room_id}/{user_id}/{role}
     */
    public JsonElement add_room_member(int room_id, int user_id, int role,JDialog object) throws UnirestException{
        return connector.commit("/room-addmember/"+connector.oauth.app_token+"/"+connector.oauth.session_token+"/"+room_id+"/"+user_id+"/"+role,object);
    }
    
    /**
     * Function for adding member to room by invite
     * @param user_id
     * @param room_code
     * @param room_password
     * @return JsonElement
     * /room-invite/{app_token}/{session_token}/{mode}/{room_code}/{room_password}
     */
    public JsonElement invite_room(String room_code,String room_password,JDialog object) throws UnirestException{
        return connector.commit("/room-invite/"+connector.oauth.app_token+"/"+connector.oauth.session_token+"/"
        +1+"/"+room_code+"/"+room_password,object);
    }
    
    /**
     * Function for removing room member
     * @param room_id
     * @param user_id
     * @param object
     * @return JsonElement
     * /room-removemember/{app_token}/{session_token}/{room_id}/{user_id}
     */
    public JsonElement remove_room_member(int room_id,int user_id, JFrame object) throws UnirestException{
        return connector.commit("/room-removemember/"+connector.oauth.app_token+"/"+connector.oauth.session_token+"/"+room_id+"/"+user_id,object);
    }
    
    /**
     * Function for listing rooms
     * @return JsonElement
     * /room-viewer/{app_token}/{session_token}
     */
    public JsonElement list_rooms(JFrame object) throws UnirestException{
        return connector.commit("/room-viewer/"+connector.oauth.app_token+"/"+connector.oauth.session_token,object);
    }
    
    /**
     * Function for getting room data
     * @param object
     * @return JsonElement
     * /room-data/{app_token}/{session_token}/{room_id}
     */
    public JsonElement get_room_data(int room_id,JFrame object) throws UnirestException{
        return connector.commit("/room-data/"+connector.oauth.app_token+"/"+connector.oauth.session_token+"/"+room_id,object);
    }
    
}
