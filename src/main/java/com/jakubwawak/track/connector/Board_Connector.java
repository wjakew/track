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
 *Object for maintaining boards on api
 * @author kubaw
 */
public class Board_Connector {
    
    Connector connector;
    
    /**
     * Constuctor
     * @param connector 
     */
    public Board_Connector(Connector connector){
        this.connector = connector;
    }
    
    /**
     * Function for getting bard glances
     * @param object 
     * @return JsonElement
     */
    public JsonElement get_board_glances(JFrame object) throws UnirestException{
        return connector.commit("/board-viewer/"+connector.oauth.app_token+"/"+connector.oauth.session_token, object);
    }
    
    /**
     * Function for adding board to api
     * @param board_name
     * @param board_desc
     * @param object
     * @return JsonElement
     * @throws UnirestException 
     */
    public JsonElement board_set(String board_name,String board_desc,JDialog object) throws UnirestException{
        board_name = board_name.replaceAll(" ", "%20");
        board_desc = board_desc.replaceAll(" ", "%20");
        return connector.commit("/board-set/"+connector.oauth.app_token+"/"+connector.oauth.session_token+"/"+board_name+"/"+board_desc,object);
    }
    
    /**
     * Function for removing board
     * @param board_id
     * @param object
     * @return JsonElement
     * @throws UnirestException 
     */
    public JsonElement board_remove(int board_id,JFrame object) throws UnirestException{
        return connector.commit("/board-remove/"+connector.oauth.app_token+"/"+connector.oauth.session_token+"/"+board_id,object);
    }
    
    /**
     * Object for adding data to board element
     * @param board_id
     * @param object
     * @param object_id
     * @return JsonElement
     * /boardmanager/{app_token}/{session_token}/{action}/{object}/{object_id}/{board_id}
     * object:           action
     * 0 - task          0 - remove
     * 1 - issue         1 - add
     */
    public JsonElement board_addelement_task(int board_id,int object_id,JDialog object) throws UnirestException{
        return connector.commit("/boardmanager/"+connector.oauth.app_token+"/"+connector.oauth.session_token+"/1/0/"+object_id+"/"+board_id,object);
    }
    public JsonElement board_addelement_task(int board_id,int object_id,JFrame object) throws UnirestException{
        return connector.commit("/boardmanager/"+connector.oauth.app_token+"/"+connector.oauth.session_token+"/1/0/"+object_id+"/"+board_id,object);
    }
    /**
     * Object for adding data to board element
     * @param board_id
     * @param object
     * @param object_id
     * @return JsonElement
     * /boardmanager/{app_token}/{session_token}/{action}/{object}/{object_id}/{board_id}
     * object:           action
     * 0 - task          0 - remove
     * 1 - issue         1 - add
     */
    public JsonElement board_addelement_issue(int board_id,int object_id,JDialog object) throws UnirestException{
        return connector.commit("/boardmanager/"+connector.oauth.app_token+"/"+connector.oauth.session_token+"/1/1/"+object_id+"/"+board_id,object);
    }
    public JsonElement board_addelement_issue(int board_id,int object_id,JFrame object) throws UnirestException{
        return connector.commit("/boardmanager/"+connector.oauth.app_token+"/"+connector.oauth.session_token+"/1/1/"+object_id+"/"+board_id,object);
    }
    
    /**
     * Function for removing boardelement issue
     * @return JsonElement
     * /boardmanager-remove/{app_token}/{session_token}/{object}/{object_id}/{board_id}
     */
    public JsonElement board_removeelement_issue(int object_id,int board_id,JFrame object) throws UnirestException{
        return connector.commit("/boardmanager-remove/"+connector.oauth.app_token+"/"+connector.oauth.session_token+"/1/"+object_id+"/"+board_id,object);
    }
    
    /**
     * Function for removing boardelement task
     * @param object_id
     * @param board_id
     * @param object
     * @return JsonElement
     * @throws UnirestException 
     */
    public JsonElement board_removeelement_task(int object_id,int board_id,JFrame object) throws UnirestException{
        return connector.commit("/boardmanager-remove/"+connector.oauth.app_token+"/"+connector.oauth.session_token+"/0/"+object_id+"/"+board_id,object);
    }
    
    /**
     * Function for loading board elements
     * @param board_id
     * @param object
     * @return
     * @throws UnirestException 
     */
    public JsonElement board_element_list(int board_id,JDialog object) throws UnirestException{
        return connector.commit("/board-viewer-element/"+connector.oauth.app_token+"/"+connector.oauth.session_token+"/"+board_id, object);
    }
    public JsonElement board_element_list(int board_id,JFrame object) throws UnirestException{
        return connector.commit("/board-viewer-element/"+connector.oauth.app_token+"/"+connector.oauth.session_token+"/"+board_id, object);
    }
    
    
}
