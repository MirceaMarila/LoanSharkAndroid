package com.example.loansharkfe;

import org.json.JSONObject;

public class Service {

    public JSONObject makeJSONObject(String[] keys, String[] values){
        JSONObject jsonObject = new JSONObject();
        for(int i=0; i<keys.length; i++){
            try {
                jsonObject.put(keys[i], values[i]);
            } catch (Exception e) {
                //el mucho grande eroras
            }

        }

        return jsonObject;
    }
}
