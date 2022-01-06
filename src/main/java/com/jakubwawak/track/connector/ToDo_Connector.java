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
 *Object for connecting to ToDo
 * @author jakubwawak
 */
public class ToDo_Connector {
    
    Connector connector;
    
    /**
     * Constructor
     * @param connector 
     */
    public ToDo_Connector(Connector connector){
        this.connector = connector;
    }
    
    /**
     * Function for getting todo list
     * @param user_id
     * @param object
     * @param mode
     * @return JsonElement
     */
    public JsonElement get_todo_list(int user_id,JFrame object,int mode) throws UnirestException{
        return connector.commit("/todo-list/"+connector.oauth.app_token+"/"+connector.oauth.session_token+"/"+mode,object);
    }
    
    /**
     * Function for adding todo object
     * @param user_id
     * @param todo_content
     * @param todo_title
     * @param todo_impor
     * @param todo_colour
     * @param object
     * @return JsonElement
     * @throws UnirestException
     */
    public JsonElement add_todo(String todo_title,String todo_content,int todo_impor,
            int todo_colour,JDialog object) throws UnirestException{
        todo_title = todo_title.replaceAll(" ", "%20");
        todo_content = todo_content.replaceAll(" ", "%20");
        return connector.commit("/todo-add/"+connector.oauth.app_token+"/"+connector.oauth.session_token+"/"
        +todo_title+"/"+todo_content+"/"+todo_impor+"/"+todo_colour,object);
    }
    
    /**
     * Function for setting todo state
     * @param todo_id
     * @param todo_state
     * @param object
     * @return JsonElement
     * @throws UnirestException 
     */
    public JsonElement set_todo(int todo_id,int todo_state,JDialog object) throws UnirestException{
        return connector.commit("/todo-state/"+connector.oauth.app_token+"/"+connector.oauth.session_token
                +"/"+todo_id+"/"+todo_state,object);
    }
    
}
