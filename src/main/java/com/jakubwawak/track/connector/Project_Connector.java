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
 *Object for connecting and maintaining Projects
 * @author kubaw
 */
public class Project_Connector {
    Connector connector;
    
    /**
     * Constructor
     * @param connector 
     */
    public Project_Connector(Connector connector){
        this.connector = connector;
    }
    /**
     * Function for loading project to database
     * @param project_name
     * @param project_desc
     * @param project_state
     * @return JsonElement
     */
    public JsonElement load_project(String project_name,String project_desc,String project_state,JDialog object) throws UnirestException{
        project_name = project_name.replaceAll(" ", "%20");
        project_desc = project_desc.replaceAll(" ", "%20");
        project_state = project_state.replaceAll(" ","%20");
        
        return connector.commit("/project-set/"+connector.oauth.app_token+"/"
                +connector.oauth.session_token+"/"+project_name+"/"+project_desc+"/"+project_state,object);
    }
    
    /**
     * Function for loading project glances
     * @return JsonElement
     */
    public JsonElement load_projects_glances(JFrame object) throws UnirestException{
        return connector.commit("/project-viewer/"+connector.oauth.app_token+"/"+connector.oauth.session_token,object);
    }
    
    /**
     * Function for loading project glances
     * @param object
     * @return
     * @throws UnirestException 
     */
    public JsonElement load_projects_glances(JDialog object) throws UnirestException{
        return connector.commit("/project-viewer/"+connector.oauth.app_token+"/"+connector.oauth.session_token,object);
    }
    
    /**
     * Function for loading project glances
     * @param project_id
     * @param object
     * @return
     * @throws UnirestException 
     */
    public JsonElement remove_project(int project_id,JFrame object) throws UnirestException{
        return connector.commit("/project-remove/"+connector.oauth.app_token+"/"+connector.oauth.session_token+"/"+project_id,object);
    }
    /**
     * Function for loading project data from api
     * @param project_id
     * @param object
     * @return JsonElement
     * @throws UnirestException 
     */
    public JsonElement load_project(int project_id,JDialog object) throws UnirestException{
        return connector.commit("/project-get/"+connector.oauth.app_token+"/"+connector.oauth.session_token+"/"+project_id,object);
    }
}
