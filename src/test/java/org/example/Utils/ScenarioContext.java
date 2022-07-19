package org.example.Utils;

import com.google.gson.GsonBuilder;

import java.util.HashMap;

public class ScenarioContext {
    private HashMap<String, Object> scenarioContext = new HashMap();

    public ScenarioContext(){
    }

    public void setScenarioContext(String key, Object value){
        this.scenarioContext.put(key, value);
    }

    public Object getScenarioContext(String key){
        return this.scenarioContext.get(key);
    }

    public boolean isContains(String key){
        return this.scenarioContext.containsKey(key);
    }

    public String toString(){
        return (new GsonBuilder()).setPrettyPrinting().create().toJson(this);
    }
}