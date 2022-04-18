/*
Jakub Wawak
kubawawak@gmail.com
all rights reseved
 */
package com.jakubwawak.track;

import com.formdev.flatlaf.FlatDarkLaf;
import com.google.gson.JsonElement;
import com.jakubwawak.track.connector.Connector;
import com.jakubwawak.track.connector.OAuth;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import javax.swing.UnsupportedLookAndFeelException;
import maintenence.Parser;
import maintenence.TrackLogger;
import ui_components.configurationload_window;
import user_interface.login_window;
import user_interface.message_window;

/**
 *Main class of the program
 * @author Jakub Wawak
 */
public class Track {
    
    final static String version = "1.2.5";
    final static String build = "TRA180422REV1CK";
    
    static OAuth oauth;
    static TrackLogger logger;
    static Connector connector;
    /**
     * Main function of the program
     * @param args 
     */
    public static void main(String args[]) throws IOException, UnirestException{
        try{
            FlatDarkLaf.setup();
            show_header();
            System.out.println("Loading configuration...");
            oauth = new OAuth();
            logger = new TrackLogger();
            oauth.load();
            oauth.compose_configuration();
            connector = null;

            // file exist, no error
            if ( oauth.exists && !oauth.error){
                logger.log("Configuration file found", 0);
                System.out.println("Configuration found");
                System.out.println("Running the program");
                logger.log("Configuration loaded",0);
                // program is running
                run();
            }
            // file not exists
            else if ( !oauth.exists ){
                new configurationload_window(null,true,connector,oauth,logger,version,build);
            }
            // error while reading file
            else if ( oauth.error ){
                System.out.println("Configuration file error");
                logger.log("Failed to load/read configuration file",1);
            }
        }catch(Exception e){
            new message_window(connector.oauth.server_ip+" is not answering right now...\n\n"+e.getMessage(),"FATAL ERROR");
        }
        
    }
    
    /**
     * Main loop of the program
     */
    public static void run() throws UnirestException, UnsupportedLookAndFeelException{
        logger.log("Connector initialization", 0);
        connector = new Connector(oauth,logger);
        logger.log("Connector initialized",0);
        JsonElement el = connector.health();
        if ( el == null){
            // connection cannot be established
            new message_window("Failed to connect to the server","ERROR");
            logger.log("Failed to connect to the server",1);
        }
        else{
            Parser parser = new Parser(el);        
            if ( connector.health ){
                connector.version = version;
                connector.bulid = build;
                logger.log("Server health checked. Seems fine",0);
                System.out.println("Connected to: "+oauth.server_ip);
                System.out.println("API "+parser.get_string("version")+ " "+parser.get_string("build_number"));
                logger.log("Connected to: "+oauth.server_ip+
                        " ("+parser.get_string("version")+"/"+parser.get_string("build_number")+")",0);
                connector.api_information = "API - "+oauth.server_ip+"/"+parser.get_string("version")+"/"+parser.get_string("build_number");
                new login_window(connector,0);
            }
            else{
                logger.log("Server health failed to check. Server seems not to respond",0);
                new message_window("Health of the server failed to check","ERROR");
            }
        }
    }
    
    /**
     * Function for showing header
     */
    public static void show_header(){
        String header = " _                  _\n" +
                        "| |_ _ __ __ _  ___| | __\n" +
                        "| __| '__/ _` |/ __| |/ /\n" +
                        "| |_| | | (_| | (__|   <\n" +
                        " \\__|_|  \\__,_|\\___|_|\\_\\";
        
        header = header + version+ " " + build;
        System.out.println(header);
    }
    
}
