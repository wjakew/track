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
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import javax.swing.JDialog;
import javax.swing.JFrame;
import maintenence.Parser;
import maintenence.Password_Validator;
import maintenence.TrackLogger;
import user_interface.login_window;
import user_interface.message_window;

/**
 *Object for maintaining connection to server
 * @author kubaw
 */
public class Connector {
    
    public OAuth oauth;
    TrackLogger logger;
    public boolean health;
    public Date ldt;
    public boolean error;
    public String version,bulid;
    public String api_information;
    // for storing configuration data
    public String config1,config2,config3;
    
    /**
     * Constructor
     * @param oauth 
     */
    public Connector(OAuth oauth,TrackLogger logger){
        this.oauth = oauth;
        this.logger = logger;
        api_information = "";
        config1 = "";
        config2 = "";
        config3 = "";
        ldt = new Date();
        error = false;
    }
    
    /**
     * Function for resetting 
     * @param url
     * @return String
     */
    public String url_builder(String url){
        return "http://"+oauth.server_ip+":8080"+url;
    }
    
    /**
     * Function for preparing raw JSON
     * @param url
     * @return JsonElement
     * @throws UnirestException 
     */
    public JsonElement commit(String url,JFrame object) throws UnirestException{
        HttpResponse<JsonNode> response = response_creator(url);
        System.out.println("Trying to commit url:"+url);
        Parser parser = new Parser(parse_response(response));
        try{
            if ( parser == null ){
                new message_window(object,true,"Connection error...","");
                System.exit(0);
            }
            if ( parser.get_int("flag") < 0){
                switch(parser.get_int("flag")){
                    case -11:
                        new message_window(object,true,"Provided app token is wrong","APPTOKEN-ERROR");
                        break;
                    case -99:
                        new message_window(object,true,"Your login session has expired","SESSION-EXPIRED");
                        break;
                    case -88:
                        new message_window(object,true,"Database error when checking session token","");
                        break;
                }
                new login_window(object,true,this,1);
            }
        }catch(Exception e){
            System.out.println("CONNECTOR ERROR: "+e.toString());
        }
        return parse_response(response);
    }
    
    /**
     * Function for preparing raw JSON
     * @param url
     * @return JsonElement
     * @throws UnirestException 
     */
    public JsonElement commit(String url,JDialog object) throws UnirestException{
        HttpResponse<JsonNode> response = response_creator(url);
        System.out.println("Trying to commit url:"+url);
        Parser parser = new Parser(parse_response(response));
        try{
            if ( parser == null ){
                new message_window(object,true,"Connection error...","");
                System.exit(0);
            }
            
            if ( parser.get_int("flag") < 0){
                switch(parser.get_int("flag")){
                    case -11:
                        new message_window(object,true,"Provided app token is wrong","APPTOKEN-ERROR");
                        break;
                    case -99:
                        new message_window(object,true,"Your login session has expired","SESSION-EXPIRED");
                        break;
                    case -88:
                        new message_window(object,true,"Database error when checking session token","");
                        break;
                }
                new login_window(object,true,this,1);
            }
            
        }catch(Exception e){
            System.out.println("CONNECTOR ERROR: "+e.toString());
        }
        return parse_response(response);
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
     * Function for checking user login
     * @param user_login
     * @return JsonElement
     * /user-check/{app_token}/{session_token}/{user_login}
     */
    public JsonElement check_user_login(String user_login,JDialog object) throws UnirestException{
        return this.commit("/user-check/"+oauth.app_token+"/"+oauth.session_token+"/"+user_login, object);
    }
    public JsonElement check_user_login(String user_login,JFrame object) throws UnirestException{
        return this.commit("/user-check/"+oauth.app_token+"/"+oauth.session_token+"/"+user_login, object);
    }
    
    /**
     * Function for setting user email
     * @param user_email
     * @param object
     * @return JsonElement
     */
    public JsonElement set_mail(String user_email,JDialog object) throws UnirestException{
        return this.commit("/user-email-set/"+oauth.app_token+"/"+oauth.session_token+"/"+user_email,object);
    }
    
    /**
     * Function for loading user configuration
     * @param object
     * @throws UnirestException 
     */
    public void get_user_configuration(JDialog object) throws UnirestException{
        try{
            Parser parser = new Parser(this.commit("/user-configuration/"+oauth.app_token+"/"+oauth.session_token, object));
            config1 = parser.get_string("config1");
            config2 = parser.get_string("config2");
            config3 = parser.get_string("config3");
            logger.log("User configuration loaded ("+config1+"/"+config2+"/"+config3+")",0);
        }catch(Exception e){
            logger.log("Failed to get user configuration",1);
        }
    }
    
    /**
     * Function for parsing response for data
     * @param data
     * @return JsonElement
     */
    JsonElement parse_response(HttpResponse<JsonNode> data){
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
    public JsonElement user_login(String user_login,String password) throws UnirestException, NoSuchAlgorithmException{
        try{
            logger.log("Trying to login "+user_login,0);
            Password_Validator pv = new Password_Validator(password);
            HttpResponse <JsonNode> response = response_creator("/login/"+oauth.app_token+"/"+user_login+"/"+pv.hash());
            logger.log("Creating login attempt: "+"/login/"+oauth.app_token+"/"+user_login+"/"+pv.hash(),0);
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
     * Function for getting user id
     * @param user_id
     * @return JsonElement
     */
    public JsonElement get_user(int user_id,JDialog object) throws UnirestException{
        return commit("/user/"+oauth.app_token+"/"+user_id,object);
    }
    
    public JsonElement get_user(int user_id,JFrame object) throws UnirestException{
        return commit("/user/"+oauth.app_token+"/"+user_id,object);
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
    
    /**
     * Function for checking user password
     * @param password
     * @return JsonElement
     * @throws UnirestException 
     */
    public JsonElement user_checkpassword(String password) throws UnirestException{
        try{
            logger.log("Trying to check password for user",0);
            HttpResponse<JsonNode> response = response_creator("/password-check/"+oauth.app_token+"/"+oauth.session_token+"/"+password);
            logger.log("Created query : "+"/password-check/"+oauth.app_token+"/"+oauth.session_token+"/"+password,0);
            if (response != null){
                return parse_response(response);
            }
            return null;
        }catch(UnirestException e){
            logger.log("Failed to check password ("+e.toString()+")",1);
            return null;
        }
    }
    
    public JsonElement user_resetpassword(String old_hash,String new_hash,JDialog object) throws UnirestException{
        try{
            logger.log("User trying to reset password",0);
            return commit("/password-reset/"+oauth.app_token+"/"+oauth.session_token+"/"+old_hash+"/"+new_hash,object);
        }catch(UnirestException e){
            logger.log("Failed to reset user password ("+e.toString()+")", 0);
            return null;
        }
    }
    
}
