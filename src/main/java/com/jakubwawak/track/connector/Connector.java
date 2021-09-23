/*
Jakub Wawak
kubawawak@gmail.com
all rights reseved
 */
package com.jakubwawak.track.connector;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.Date;
import maintenence.Parser;
import maintenence.TrackLogger;

/**
 *Object for maintaining connection to server
 * @author kubaw
 */
public class Connector {
    
    public OAuth oauth;
    TrackLogger logger;
    public boolean health;
    public Date ldt;
    
    public String version,bulid;
    /**
     * Constructor
     * @param oauth 
     */
    public Connector(OAuth oauth,TrackLogger logger){
        this.oauth = oauth;
        this.logger = logger;
        ldt = new Date();
    }
    
    public String url_builder(String url){
        return "http://"+oauth.server_ip+":8080"+url;
    }
    
    /**
     * Function for creating response
     * @param url
     * @return String
     * @throws UnirestException 
     */
    HttpResponse<JsonNode> response_creator(String url) throws UnirestException{
        try{
            logger.log("Creating response ("+url_builder(url)+")", 0);
            return Unirest.get(url_builder(url)).asJson();
        }catch(UnirestException e){
            logger.log("Failed to create response ("+e.toString()+")",1);
            return null;
        }
    } 
    
    /**
     * Function for parasing response for data
     * @param data
     * @return JsonElement
     */
    JsonElement parse_response(HttpResponse<JsonNode> data){
        logger.log("Parasing response for data", 0);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        return jp.parse(data.getBody().toString());
    }
    /**
     * Function for checking server health
     * @return JsonElement
     */
    public JsonElement health() throws UnirestException{
        try{
            logger.log("Trying to check /health of "+url_builder("/health")+")",0);
            HttpResponse <JsonNode> response = response_creator("/health");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonParser jp = new JsonParser();
            logger.log("/health checked, returning response",0);
            health = true;
            return jp.parse(response.getBody().toString());
        }catch(UnirestException e){
            logger.log("Failed to get /health response ("+e.toString()+")", 1);
            health = false;
            return null;
        }
    }
    
    /**
     * Function for logging user
     * @param user_login
     * @param password
     * @return JsonElement
     */
    public JsonElement user_login(String user_login,String password) throws UnirestException{
        try{
            logger.log("Trying to login "+user_login,0);
            HttpResponse <JsonNode> response = response_creator("/login/"+oauth.app_token+"/"+user_login+"/"+password);
            if ( response != null){
                Parser parser = new Parser(parse_response(response));
                if ( parser.get_int("user_id") > 0 ){
                    oauth.user_id = parser.get_int("user_id");
                    oauth.session_token = parser.get_string("user_session");
                    logger.log("Obtained session token:"+oauth.session_token,0);
                    oauth.apr_login_time = new Date();
                }
                return parse_response(response);
            }
            else{
                return null;
            }
        }catch(UnirestException e){
            logger.log("Failed to login ("+e.toString()+")",1);
            return null;
        }
    }
    
    /**
     * Function for registrating user
     * @param user_name
     * @param user_surname
     * @param user_email
     * @return JsonElement
     */
    public JsonElement user_register(String user_name,String user_surname,String user_email) throws UnirestException{
        try{
            logger.log("Trying to register  new user ("+user_name+" "+user_surname+")",0);
            HttpResponse <JsonNode> response = response_creator("/register/"+oauth.app_token+"/"+user_name+"/"+user_surname+"/"+user_email);
            
            if ( response != null ){
                return parse_response(response);
            }
            else{
                return null;
            }
        }catch(UnirestException e){
            logger.log("Failed to register new user ("+e.toString()+")",1);
            return null;
        }
    }
    
    /**
     * 
     * @param email_address
     * @return 
     */
    public JsonElement user_resetpassword(String email_address) throws UnirestException{
        try{
            logger.log("Trying to reset password for user with email "+email_address,0);
            HttpResponse<JsonNode> response = response_creator("/password-reset/"+oauth.app_token+"/"+email_address);
            
            if ( response != null){
                return parse_response(response);
            }
            else{
                return null;
            }
        }catch(UnirestException e){
            logger.log("Failed to reset password ("+e.toString()+")", 1);
            return null;
        }
        
    }
    
}
