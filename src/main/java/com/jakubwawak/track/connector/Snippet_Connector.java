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
 * Class for maintaining connection to database
 * @author jakubwawak
 */
public class Snippet_Connector {
    
    Connector connector;
    
    /**
     * Constructor
     * @param connector 
     */
    public Snippet_Connector(Connector connector){
        this.connector = connector;
    }
    
    /**
     * Function for loading viewer for snippets
     * @param object
     * @return JsonElement
     * @throws UnirestException 
     */
    public JsonElement load_snippets(JFrame object) throws UnirestException{
        return connector.commit("/snippet-viewer/"+connector.oauth.app_token+"/"+connector.oauth.session_token,object);
    } 
    
    /**
     * Function for adding snippets
     * @param title
     * @param content
     * @param object
     * @return
     * @throws UnirestException 
     */
    public JsonElement add_snippets(String title,String content,JDialog object) throws UnirestException{
        title = title.replace(" ", "%20");
        content = content.replace(" ","%20");
        return connector.commit("/snippet-set/"+connector.oauth.app_token+"/"+connector.oauth.session_token+"/"+title+"/"+content,object);
    }
    
    /**
     * Function for removing snippets
     * @param user_snippet_id
     * @param object
     * @return JsonElement
     * @throws UnirestException 
     */
    public JsonElement remove_snippets(int user_snippet_id,JFrame object) throws UnirestException{
        return connector.commit("/snippet-remove/"+connector.oauth.app_token+"/"+connector.oauth.session_token+"/"+user_snippet_id, object);
    }
    
    /**
     * Function for getting data of a snippet
     * @param user_snippet_id
     * @return JsonElement
     */
    public JsonElement get_snippet(int user_snippet_id,JFrame object) throws UnirestException{
        return connector.commit("/snippet-get/"+connector.oauth.app_token+"/"+connector.oauth.session_token+"/"+user_snippet_id,object);
    }
    
    /**
     * Function for sharing snippet data
     * @param user_snippet_id
     * @param recipient_id
     * @param object
     * @return JsonElement
     */
    public JsonElement share_snippet(int user_snippet_id,int recipient_id,JDialog object) throws UnirestException{
        return connector.commit("/snippet-share/"+connector.oauth.app_token+"/"+connector.oauth.session_token+"/"+user_snippet_id+"/"+recipient_id,object);
    }
}
