/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeanConsultaEventos;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.practicas.pozo.ManagedPozo;
import com.siscop.dto.EventoDto;
import com.siscop.enumerator.TipoTrabajoEnum;
import com.siscop.service.CopEventoService;
import com.siscop.utils.Joda;
import com.siscop.utils.Utils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.FactoryFinder;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author ihsa
 */
@javax.faces.bean.ManagedBean
@ViewScoped
public class ManagedEventos implements Serializable{

    private int idPozo;
    private int idTipoTrabajo;
    private List<EventoDto> listaEventos;
    private Gson gson;
    private JsonArray jsonArray;
    
    @ManagedProperty(value = "#{managedPozo}")
    private ManagedPozo managedPozo;
    
    @EJB
    private CopEventoService servicioEvento;

    @PostConstruct
    private void init() {
        idTipoTrabajo = -1;
        gson = new Gson();
        //idTipoTrabajo = TipoTrabajoEnum.TRABAJO_MAYOR.getId();
    }

    public void cargarEventos(ActionEvent ev) {
        System.out.println("cargarEventos");
        if (idPozo != 0 && idTipoTrabajo!=0 ) {
            this.listaEventos = obtenerLista();
        } else {
            System.out.println("Escribe los valores...............");
        }
    }

    public String getJson() {

        if (getListaEventos() != null) {
            
            jsonArray = new JsonArray();

            for (EventoDto dto : getListaEventos()) {

                final Date fechaHora = Joda.construirFecha(dto.getFecha(), dto.getHora());

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("start", fechaHora.getTime());
                jsonObject.addProperty("content", dto.getNombreEvento());

                jsonArray.add(jsonObject);
            }

            System.out.println("Array completo " + jsonArray.toString());
        }
        
        return getListaEventos()!=null ? gson.toJson(jsonArray):"";

    }

    private List<EventoDto> obtenerLista() {

        List<EventoDto> lista = Collections.EMPTY_LIST;

        if (idTipoTrabajo == -1) {
            System.out.println("Cargar todos los eventos");
            //todos
            lista = new ArrayList<>();
            final List<EventoDto> listaMayor = servicioEvento.obtenerEventosPorTipoTrabajo(idPozo, TipoTrabajoEnum.TRABAJO_MAYOR.getId());
            final List<EventoDto> listaMenor = servicioEvento.obtenerEventosPorTipoTrabajo(idPozo, TipoTrabajoEnum.TRABAJO_MENOR.getId());
            lista.addAll(listaMayor);
            lista.addAll(listaMenor);

        } else {
            System.out.println("solo cargar los de tipo trabajo "+idTipoTrabajo);
            lista = servicioEvento.obtenerEventosPorPozoYTipoEvento(idPozo, idTipoTrabajo);
        }
        return lista;
    }

    public int getIdPozo() {
        return idPozo;
    }

    public void setIdPozo(int idPozo) {
        this.idPozo = idPozo;
    }

    public void setServicioEvento(CopEventoService servicioEvento) {
        this.servicioEvento = servicioEvento;
    }

    public int getIdTipoTrabajo() {
        return idTipoTrabajo;
    }

    public void setIdTipoTrabajo(int idTipoTrabajo) {
        this.idTipoTrabajo = idTipoTrabajo;
    }

    public List<EventoDto> getListaEventos() {
        return listaEventos;
    }

    public ManagedPozo getManagedPozo() {
        return managedPozo;
    }

    public void setManagedPozo(ManagedPozo managedPozo) {
        this.managedPozo = managedPozo;
    }

}
