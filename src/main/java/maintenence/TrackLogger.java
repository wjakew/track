/*
Jakub Wawak
kubawawak@gmail.com
all rights reseved
 */
package maintenence;

import java.util.Date;
import java.util.ArrayList;

/**
 *Object for saving app log
 * @author Jakub Wawak
 */
public class TrackLogger {
    
    int debug = 1;
    
    final String version = "1.0.0";
    
    Date actual_time;
    ArrayList<String> raw_data;
    
    /**
     * Constructor
     */
    public TrackLogger(){
        actual_time = new Date();
        raw_data = new ArrayList<>();
        raw_data.add("Log from TRACK app");
        raw_data.add("Started: "+actual_time.toString());
    }
    
    /**
     * Function for saving log data
     * @param data - String data to save
     * @param status - status of the log message
     * status values:
     * 0 - normal information
     * 1 - error
     */
    public void log(String data,int status){
        actual_time = new Date();
        switch(status){
            case 0:
                raw_data.add("INFO ("+actual_time.toString()+") "+data);
                if (debug == 1){
                    System.out.println("INFO ("+actual_time.toString()+") "+data);
                }
                break;
            case 1:
                raw_data.add("ERROR! ("+actual_time.toString()+") "+data);
                if (debug == 1){
                    System.out.println("ERROR! ("+actual_time.toString()+") "+data);
                }
                break;
        }
    }
    
    
}
