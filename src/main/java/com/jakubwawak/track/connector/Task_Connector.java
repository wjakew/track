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
 *Object for maintaining Tasks
 * @author kubaw
 */
public class Task_Connector {
    
    Connector connector;
    
    /**
     * Constructor
     * @param connector 
     */
    public Task_Connector(Connector connector){
        this.connector = connector;
    }
    
    /**
     * Function for loading new task to database
     * @param task_name
     * @param project_id
     * @param task_desc
     * @param priority
     * @param object
     * @return JsonElement
     * @throws UnirestException 
     */
    public JsonElement load_task(String task_name,int project_id,String task_desc,int priority,JDialog object) throws UnirestException{
        return connector.commit("/task-set/"+connector.oauth.app_token+"/"+connector.oauth.session_token+"/"
                +project_id+"/"+task_name+"/"+task_desc+"/"+priority, object);
    }
    
    /**
     * Function for loading task glances for ui components
     * @param mode
     * @param object
     * @return JsonElement
     * @throws UnirestException 
     */
    public JsonElement load_task_glances(int mode,JFrame object) throws UnirestException{
        return connector.commit("/task-viewer/"+connector.oauth.app_token+"/"+connector.oauth.session_token+"/"+mode,object);
    }
    public JsonElement load_task_glances(int mode,JDialog object) throws UnirestException{
        return connector.commit("/task-viewer/"+connector.oauth.app_token+"/"+connector.oauth.session_token+"/"+mode,object);
    }
    
    /**
     * Function for getting task history data in Viewer
     * @param task_id
     * @param object
     * @return JsonElement
     */
    public JsonElement get_history(int task_id, JDialog object) throws UnirestException{
        return connector.commit("/task-history/"+connector.oauth.app_token+"/"+connector.oauth.session_token+"/"+task_id,object);
    }
    
    /**
     * Function for getting archived tasks
     * @param object
     * @return JsonElement
     * @throws UnirestException 
     */
    public JsonElement get_archive(JDialog object) throws UnirestException{
        return connector.commit("/task-archive/"+connector.oauth.app_token+"/"+connector.oauth.session_token,object);
    }
    /**
     * Function for getting task data
     * @param task_id
     * @param object
     * @return JsonElement
     * @throws UnirestException 
     */
    public JsonElement get_task(int task_id,JDialog object) throws UnirestException{
        return connector.commit("/task-get/"+connector.oauth.app_token+"/"+connector.oauth.session_token+"/"+task_id,object);
    } 
    
    /**
     * Function for removing task
     * @param task_id
     * @param object
     * @return JsonElement
     * @throws UnirestException 
     */
    public JsonElement remove_task(int task_id,JFrame object) throws UnirestException{
        return connector.commit("/task-remove/"+connector.oauth.app_token+"/"+connector.oauth.session_token+"/"+task_id, object);
    }
    
    /**
     * Function for setting task done
     * @param task_id
     * @return JsonElement
     */
    public JsonElement set_task_done(int task_id,JDialog object) throws UnirestException{
        return connector.commit("/task-done/"+connector.oauth.app_token+"/"+connector.oauth.session_token+"/"+task_id,object);
    }
    public JsonElement set_task_done(int task_id,JFrame object) throws UnirestException{
        return connector.commit("/task-done/"+connector.oauth.app_token+"/"+connector.oauth.session_token+"/"+task_id,object);
    }
    
    /**
     * Function for setting task open again
     * @param task_id
     * @param object
     * @return JsonElement
     * @throws UnirestException 
     */
    public JsonElement set_task_open(int task_id,JDialog object) throws UnirestException{
        return connector.commit("/task-open/"+connector.oauth.app_token+"/"+connector.oauth.session_token+"/"+task_id,object);
    }
    
}
