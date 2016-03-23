/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faces.component.demo;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.siscop.dto.PozoDto;
import java.util.List;

/**
 *
 * @author ihsa
 */
//TODO: utilizar el patron adaptador
public class AutocompletePozoAdapter {

    private Gson gson;
    private JsonArray jsonArrayPozo;
    private final List<PozoDto> listaPozo;

    public AutocompletePozoAdapter(List<PozoDto> listaPozo) {
        this.listaPozo = listaPozo;
        gson = new Gson();
        convertToJson();
    }

    public void convertToJson() {
        System.out.println("Convertir");

        if (listaPozo != null) {

            jsonArrayPozo = new JsonArray();

            for (PozoDto dto : listaPozo) {
                System.out.println("Convert to Json " + dto.getNombre());
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("name", dto.getId());
                jsonObject.addProperty("display", dto.getNombre());
                jsonArrayPozo.add(jsonObject);
            }
        }

    }

    public String getJsonAdapter() {
        return jsonArrayPozo != null ? gson.toJson(jsonArrayPozo) : "";
    }

}
