/*
Jakub Wawak
kubawawak@gmail.com
all rights reseved
 */
package com.jakubwawak.track;

import com.jakubwawak.track.connector.OAuth;
import java.io.IOException;

/**
 *Main class of the program
 * @author Jakub Wawak
 */
public class Track {
    
    final static String version = "1.0.0";
    final static String build = "TRA100921REV1CK";
    
    static OAuth oauth;
    
    /**
     * Main function of the program
     * @param args 
     */
    public static void main(String args[]) throws IOException{
        show_header();
        System.out.println("Loading configuration...");
        oauth = new OAuth();
        
        oauth.load();
        oauth.compose_configuration();
        
        // file exist, no error
        if ( oauth.exists && !oauth.error){
            System.out.println("Configuration found");
            System.out.println("Running the program");
            // program is running
        }
        // file not exists
        else if ( !oauth.exists ){
            System.out.println("Configuration not found");
            int ret = oauth.user_load();
            if ( ret == 1){
                if (oauth.save_to_file() == 1){
                    System.out.println("Configuration saved");
                    // program is running
                }
                else
                    System.out.println("Failed to save configuration");
                    System.exit(0);
            }
        }
        // error while reading file
        else if ( oauth.error ){
            System.out.println("Configuration file error");
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
