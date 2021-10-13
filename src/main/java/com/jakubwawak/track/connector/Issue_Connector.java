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
 *Function for managing issues
 * @author kubaw
 */
public class Issue_Connector {
    
    Connector connector;
    
    public Issue_Connector(Connector connector){
        this.connector = connector;
    }
    
    /**
     * Function for adding new issue
     * @param issue_name
     * @param issue_desc
     * @param priority
     * @param project_id
     * @param issue_due
     * @param object
     * @return JsonElement
     * @throws UnirestException 
     */
    public JsonElement issue_set(String issue_name,String issue_desc,int priority,int project_id,String issue_due,JDialog object) throws UnirestException{
        issue_name = issue_name.replaceAll(" ", "%20");
        issue_desc = issue_desc.replaceAll(" ", "%20");
        issue_due = issue_due.replaceAll(" ", "%20");
        return connector.commit("/issue-set/"+connector.oauth.app_token+"/"+connector.oauth.session_token+"/"+project_id+
                "/"+issue_name+"/"+issue_desc+"/"+priority+"/"+issue_due, object);
    }
    
    /**
     * Function for getting issue data
     * @param issue_id
     * @param object
     * @return JsonElement
     * @throws UnirestException 
     */
    public JsonElement issue_get(int issue_id,JDialog object) throws UnirestException{
        return connector.commit("/issue-get/"+connector.oauth.app_token+"/"+connector.oauth.session_token+"/"+issue_id,object);
    }
    
    /**
     * Function for setting new issue_group
     * @param issue_id
     * @param group_id
     * @param object
     * @return JsonElement
     * @throws UnirestException 
     */
    public JsonElement update_group(int issue_id,int group_id,JDialog object) throws UnirestException{
        return connector.commit("/issue-group/"+connector.oauth.app_token+"/"+connector.oauth.session_token+"/"+issue_id+"/"+group_id,object);
    }
    public JsonElement update_group(int issue_id,int group_id,JFrame object) throws UnirestException{
        return connector.commit("/issue-group/"+connector.oauth.app_token+"/"+connector.oauth.session_token+"/"+issue_id+"/"+group_id,object);
    }
    
    /**
     * Function for opening again issue
     * @param issue_id
     * @param object
     * @return
     * @throws UnirestException 
     */
    public JsonElement open_issue(int issue_id,JDialog object) throws UnirestException{
        return connector.commit("/issue-group/"+connector.oauth.app_token+"/"+connector.oauth.session_token+"/"+issue_id+"/"+0,object);
    }
    
    /**
     * Function for loading issues glances
     * @param mode
     * @param object
     * @return JsonElement
     */
    public JsonElement load_issues_glances(int mode,JFrame object) throws UnirestException{
        return connector.commit("/issue-viewer/"+connector.oauth.app_token+"/"+connector.oauth.session_token+"/"+mode, object);
    }
    public JsonElement load_issues_glances(int mode,JDialog object) throws UnirestException{
        return connector.commit("/issue-viewer/"+connector.oauth.app_token+"/"+connector.oauth.session_token+"/"+mode, object);
    }
    
    /**
     * Function for getting issues archive
     * @param object
     * @return JsonElement
     * @throws UnirestException 
     */
    public JsonElement get_archive(JDialog object) throws UnirestException{
        return connector.commit("/issue-archive/"+connector.oauth.app_token+"/"+connector.oauth.session_token,object);
    }
    
    /**
     * Function for loading issue history
     * @param issue_id
     * @param object
     * @return JsonElement
     * @throws UnirestException 
     */
    public JsonElement load_issue_history(int issue_id,JDialog object) throws UnirestException{
        return connector.commit("/issue-history/"+connector.oauth.app_token+"/"+connector.oauth.session_token+"/"+issue_id, object);
    }
    
    /**
     * Function for removing issues
     * @param issue_id
     * @return JsonElement
     */
    public JsonElement issue_remove(int issue_id,JFrame object) throws UnirestException{
        return connector.commit("/issue-remove/"+connector.oauth.app_token+"/"+connector.oauth.session_token+"/"+issue_id, object);
    }
    
    
}
