/*
Jakub Wawak
kubawawak@gmail.com
all rights reseved
 */
package com.jakubwawak.track.connector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Object for creating and adding 
 * @author Jakub Wawak
 */
public class OAuth {
    
    String file_src;
    public boolean load, error,exists;
    ArrayList<String> raw_data;
    /**
     * File structure:
     * configfile
     * server_ip%
     * app_token%
     * user_name%
     * 
     * file extension:
     * .track
     */
    String server_ip,app_token,user_name;
    
    /**
     * Constructor
     */
    public OAuth(){
        file_src = "config.track";
        raw_data = new ArrayList<String>();
        
        File file = new File(file_src);
        if ( file.exists() ){
            exists = true;
            error = false;
        }
        else{
            exists = false;
            error = true;
            load = false;
        }
    }
    
    /**
     * Function for loading data
     */
    public void load() throws FileNotFoundException, IOException{
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file_src));
            if( exists ){
                String strCurrentLine;
                while ((strCurrentLine = reader.readLine()) != null) {
                    raw_data.add(strCurrentLine);
               }
                load = true;
            }
            else{
                load = false;
            }   
        }catch(FileNotFoundException e){
            this.error = true;
            this.exists = false;
        }
        
    }
    
    /**
     * Function for data validation
     * @return boolean
     */
    public boolean valid_data(){
        if( raw_data.get(0).contains("configfile")){
            return raw_data.size() >=4;
        }
        else{
            return false;
        }
    }
    
    /**
     * Function for composing configuration data
     */
    public void compose_configuration(){
        raw_data.forEach(line -> {
            if ( line.contains("server_ip%")){
                this.server_ip = line.split("%")[1].substring(0,line.split("%")[1].length()-1);
            }
            else if( line.contains("app_token%")){
                this.app_token = line.split("%")[1].substring(0,line.split("%")[1].length()-1);
            }
            else if( line.contains("user_name%")){
                this.user_name = line.split("%")[1].substring(0,line.split("%")[1].length()-1);
            }
        });
    }
    
    /**
     * Function for loading user configuration
     * @return Integer
     */
    public int user_load(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Configuration loader");
        System.out.println("Ready to configure(y/n)?");
        String ans = sc.nextLine();
        if ( ans.equals("y") ){
            System.out.print("server ip?");
            this.server_ip = sc.nextLine();
            System.out.print("app token?");
            this.app_token = sc.nextLine();
            System.out.print("user name?");
            this.user_name = sc.nextLine();
            return 1;
        }
        return 0;
    }
    
    /**
     * Function for saving data to file
     * @throws IOException 
     * @return Integer
     */
    public int save_to_file() throws IOException{
        try{
            FileWriter fw = new FileWriter(file_src);
            fw.write("confgfile");
            fw.write("server_ip%"+this.server_ip+"\n");
            fw.write("app_token%"+this.app_token+"\n");
            fw.write("user_name%"+this.user_name+"\n");
            fw.close();
            return 1;
        }catch(IOException e){
            return -1;
        }
    }
}
