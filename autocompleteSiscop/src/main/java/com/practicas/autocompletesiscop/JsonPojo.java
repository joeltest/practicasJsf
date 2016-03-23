/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practicas.autocompletesiscop;

/**
 *
 * @author ihsa
 */
public class JsonPojo {
    
    private Integer value;
    private String label;
    private String descripcion;

    public JsonPojo(Integer value, String label, String descripcion) {
        this.value = value;
        this.label = label;
        this.descripcion = descripcion;
    }

    
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
