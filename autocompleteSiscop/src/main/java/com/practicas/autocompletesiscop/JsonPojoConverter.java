/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practicas.autocompletesiscop;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
/**
 *
 * @author ihsa
 */
@FacesConverter(value="jsonPojoConverter")
public class JsonPojoConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String key) {
        
//        FacesContext context = FacesContext.getCurrentInstance();
        
        ManagedBean managedBean = (ManagedBean) context.getELContext().getELResolver().getValue(context.getELContext(), null, "ManagedBean");
 
        return managedBean.getPojoByValue(key);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value.toString();
    }
    
}
