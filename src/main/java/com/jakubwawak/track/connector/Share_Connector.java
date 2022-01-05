/*
Jakub Wawak
kubawawak@gmail.com
all rights reseved
 */
package com.jakubwawak.track.connector;

import com.google.gson.JsonElement;
import com.mashape.unirest.http.exceptions.UnirestException;
import javax.swing.JDialog;

/**
 *
 * @author kubaw
 */
public class Share_Connector {
    Connector connector;
    
    /**
     * Constructor
     * @param connector 
     */
    public Share_Connector(Connector connector){
        this.connector = connector;
    }
    
    /**
     * Function for sharing projects to other users
     * @param project_id
     * @param user_login
     * @param object
     * @return JsonElement
     * @throws UnirestException 
     */
    public JsonElement share_project(int project_id,String user_login,JDialog object) throws UnirestException{
        return connector.commit("/shared-set/"+connector.oauth.app_token+"/"+connector.oauth.session_token+
                "/"+project_id+"/"+user_login, object);
    }
    
    /**
     * Function for showing shared projects
     * @param object
     * @return JsonElement
     * @throws UnirestException 
     */
    public JsonElement show_myshared_projects(JDialog object) throws UnirestException{
        return connector.commit("/shared-viewer-myshares/"+connector.oauth.app_token+"/"+connector.oauth.session_token,object);
    }
    
    /**
     * Function for showing objects shared to uset
     * @param object
     * @return JsonElement
     * @throws UnirestException 
     */
    public JsonElement show_sharedtome_projects(JDialog object) throws UnirestException{
        return connector.commit("/shared-viewer-sharedtome/"+connector.oauth.app_token+"/"+connector.oauth.session_token,object);
    }
    
    /**
     * Function for removing shared project
     * @param project_id
     * @param object
     * @return JsonElement
     * @throws UnirestException 
     */
    public JsonElement remove_share(int project_id,JDialog object) throws UnirestException{
        return connector.commit("/shared-remove/"+connector.oauth.app_token+"/"+connector.oauth.session_token+"/"+project_id,object);
    }
}
