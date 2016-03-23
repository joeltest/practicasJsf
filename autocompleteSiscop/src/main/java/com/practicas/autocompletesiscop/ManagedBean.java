/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practicas.autocompletesiscop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author ihsa
 */
@javax.faces.bean.ManagedBean
@RequestScoped
public class ManagedBean {
    private JsonPojo jsonPojo;
    
    private HashMap<String,JsonPojo> hashMap;
    
    
    @PostConstruct
    private void init(){
        hashMap = new HashMap();           
        for(int i=0;i<20;i++){
            String name = ("Pojo "+1);
            hashMap.put(String.valueOf(i),crearjson(1,name, ""));
        }       
        
    }
    
    private JsonPojo crearjson(int id,String nombre,String descripcion){
            return new JsonPojo(id, nombre, descripcion);
    }
            
    public Date getServerTime(){
        return new Date();
    }
    
    public List<JsonPojo> getList(){
        return new ArrayList<>(hashMap.values());
    }    
    
    public JsonPojo getPojoByValue(String value){
        return hashMap.get(value);
    }

    public JsonPojo getJsonPojo() {
        return jsonPojo;
    }

    public void setJsonPojo(JsonPojo jsonPojo) {
        this.jsonPojo = jsonPojo;
    }
}
