/*
Jakub Wawak
kubawawak@gmail.com
all rights reseved
 */
package maintenence;

import com.google.gson.JsonElement;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *Object for parsing json response
 * @author kubaw
 */
public class Parser {
    
    final int debug = 1;
    
    JsonElement raw_object;
    String raw_data;
    JSONObject obj;
    
    public boolean error;
    
    /**
     * Constructor
     * @param raw_object 
     */
    public Parser(JsonElement raw_object){
        this.raw_object = raw_object;
        this.raw_data = "";
        if ( raw_object == null){
            error = true;
        }
        else
            load();
    }
    
    /**
     * Function for getting string values
     * @param key
     * @return String
     */
    public String get_string(String key){
        return obj.getString(key);
    }
    
    /**
     * Function for getting int values
     * @param key
     * @return Integer
     */
    public int get_int(String key){
        return obj.getInt(key);
    }
    
    /**
     * Function for returing connection status flag
     * @return Integer
     */
    public int get_flag(){
        return obj.getInt("flag");
    }
    
    /**
     * Function for getting array
     * @param key
     * @return JSONArray
     */
    public JSONArray get_array(String key){
        return obj.getJSONArray(key);
    }
    
    /**
     * Function for getting object list
     * @param key
     * @return ArrayList
     */
    public ArrayList get_room_lists(String key){
        ArrayList<ArrayList> list = new ArrayList<>();
        JSONArray objects = obj.getJSONArray(key);
        for( int i = 0; i < objects.length(); i++  ){
            /**
             * {"view":["admin
                "]
                "flag":1
                "sv":null
                "view2":[{"room_id":1
                "room_name":"Testowy pokój"
                "room_password":"kelhujpmcl"
                "flag":1
                "room_code":"nhsuqjkmcm"
                "room_desc":"Test pokoju"}]}
             */
            ArrayList<String> object = new ArrayList<>();
            object.add(Integer.toString(objects.getJSONObject(i).getInt("room_id")));
            object.add(objects.getJSONObject(i).getString("room_name"));
            object.add(objects.getJSONObject(i).getString("room_password"));
            object.add(objects.getJSONObject(i).getString("room_code"));
            object.add(objects.getJSONObject(i).getString("room_desc"));
            object.add(objects.getJSONObject(i).getString("owner_login"));
            list.add(object);
        }
        return list;
    }
    
    /**
     * Function for getting messages list from api
     * @param key
     * @return 
     */
    public ArrayList get_messages_list(String key){
        ArrayList<ArrayList> list = new ArrayList<>();
        JSONArray objects = obj.getJSONArray(key);
        for( int i = 0; i < objects.length(); i++  ){
            /**
             * {"room_id":1
                    "room_time":"2022-03-04T09:37:59"
                    "flag":1
                    "room_message_content":"Test"
                    "user_id":1000
                    "content_id":-1
                    "user_login":"admin"
                    "room_message_id":2
                    "ping_id":-1}
             */
            ArrayList<String> object = new ArrayList<>();
            object.add(objects.getJSONObject(i).getString("room_time"));
            object.add(objects.getJSONObject(i).getString("room_message_content"));
            object.add(objects.getJSONObject(i).getString("user_login"));
            list.add(object);
        }
        return list;
    }
    
    /**
     * Function for ArrayList
     * @param key
     * @return ArrayList
     */
    public ArrayList<String> get_arraylist(String key){
        ArrayList<String> data = new ArrayList<>();
        JSONArray array = obj.getJSONArray(key);
        if ( array != null){
            for(int i = 0; i<array.length(); i++){
                data.add(array.get(i).toString());
            }
        }
        return data;
    }
    
    /**
     * Function for List
     * @param key
     * @return List
     */
    public List<String> get_listlist(String key){
        List<String> data = new ArrayList<>();
        JSONArray array = obj.getJSONArray(key);
        if ( array != null){
            for(int i = 0; i<array.length(); i++){
                data.add(array.get(i).toString());
            }
        }
        return data;
    }
    
    /**
     * Function for loading data from Json object
     */
    void load(){
        raw_data = raw_object.toString();
        obj = new JSONObject(raw_data);
    }
    
}
