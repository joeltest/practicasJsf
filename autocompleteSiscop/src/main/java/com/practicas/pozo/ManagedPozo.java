/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practicas.pozo;

import com.faces.component.demo.AutocompletePozoAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.siscop.base.BaseDto;
import com.siscop.dto.PozoDto;
import com.siscop.enumerator.EstadoPozoEnum;
import com.siscop.service.CopPozoService;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author ihsa
 */
@javax.faces.bean.ManagedBean
@ViewScoped
public class ManagedPozo implements Serializable {

    private int idPozo;
    private PozoDto pozoDto;
    private Gson gson;
    private JsonArray jsonArrayPozo;
    private List<PozoDto> listaPozo;
    private AutocompletePozoAdapter adapterAutocomplete;
    @EJB
    private CopPozoService pozoService;

    private final List<EstadoPozoEnum> listaEstadosPozo
            = Arrays.asList(
                    EstadoPozoEnum.EN_PERFORACION_EVALUADO,
                    EstadoPozoEnum.EN_OPERACION_ACTIVO,
                    EstadoPozoEnum.EN_OPERACION_EVALUADO,
                    EstadoPozoEnum.EN_OPERACION_AGOTADO,
                    EstadoPozoEnum.EN_OPERACION_CERRADO,
                    EstadoPozoEnum.EN_OPERACION_CERRADO_POR_TRABAJO
            );

    @PostConstruct
    private void init() {

        gson = new Gson();
        
        listaPozo = pozoService.obtenerPozosConPruebaDeTerminacionPorEstados(listaEstadosPozo);
        
        setAdapterAutocomplete(new AutocompletePozoAdapter(listaPozo));

        llenarJson();
    }
    
    

    public void cargarPozos(ActionEvent ev) {
        System.out.println("cargarEventos");

    }

    public String getJsonPozo() {
        return jsonArrayPozo != null ? gson.toJson(jsonArrayPozo) : "";
    }
    
    public void limpiarTodo(){
        this.idPozo = 0;
        this.jsonArrayPozo = null;
        this.listaPozo = Collections.EMPTY_LIST;
    }

    //public void llenarJson(List<PozoDto> lista) {
    public void llenarJson() {
        System.out.println("Lista");
        listaPozo = pozoService.obtenerPozosConPruebaDeTerminacionPorEstados(listaEstadosPozo);

        if (listaPozo != null) {
            
            jsonArrayPozo = new JsonArray();

            for (PozoDto dto : listaPozo) {
                System.out.println("dto add "+dto.getNombre());
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("name", dto.getId());
                jsonObject.addProperty("display", dto.getNombre());

                jsonArrayPozo.add(jsonObject);
            }
        }
        
    }

    public void findPozo(ActionEvent ev) {
        if (idPozo != 0) {
            this.pozoDto = pozoService.findId(idPozo);

            System.out.println("Pozo " + getPozoDto().getNombre());
        } else {
            System.out.println("Id de pozo NULL ");
        }
    }

    public int getIdPozo() {
        return idPozo;
    }

    public void setIdPozo(int idPozo) {
        this.idPozo = idPozo;
    }

    public PozoDto getPozoDto() {
        return pozoDto;
    }

    public void setPozoDto(PozoDto pozoDto) {
        this.pozoDto = pozoDto;
    }

    public AutocompletePozoAdapter getAdapterAutocomplete() {
        return adapterAutocomplete;
    }

    public void setAdapterAutocomplete(AutocompletePozoAdapter adapterAutocomplete) {
        this.adapterAutocomplete = adapterAutocomplete;
    }

}
