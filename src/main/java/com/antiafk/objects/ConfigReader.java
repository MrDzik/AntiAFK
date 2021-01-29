package com.antiafk.objects;

import org.json.simple.JSONObject;
import java.io.FileReader;
import org.json.simple.parser.JSONParser;

public class ConfigReader {
    private final String PATH = "";

    private JSONObject readJSON() throws Exception {
        JSONParser parser = new JSONParser();
        try {
            FileReader fileReader = new FileReader(PATH);
            JSONObject jsonObject = (JSONObject)parser.parse(fileReader);
            return jsonObject;
        }
        catch(Exception e) {
            throw e;
        }
    }
    public Config readOrReturnDefault(){
        try {
            JSONObject jsonObject = readJSON();
            Config config = new Config();
            long timeToKick = (long)jsonObject.get("TimeToKick");
            config.setTimeToKick(timeToKick);
            return config;
        }
        catch (Exception e){
            return Config.getDefault();
        }
    }
}
