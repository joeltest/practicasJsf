/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practicas.pozo;

import com.practicas.pozo.ManagedPozo;
import com.siscop.dto.PozoDto;
import com.siscop.enumerator.EstadoPozoEnum;
import com.siscop.service.CopPozoService;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author ihsa
 */
@javax.faces.bean.ManagedBean
@ViewScoped
public class BusquedaPozoBean implements Serializable {

    @ManagedProperty(value = "#{managedPozo}")
    private ManagedPozo managedPozo;

    private PozoDto pozoDto;

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
        
//        managedPozo.llenarJson(pozoService.obtenerPozosConPruebaDeTerminacionPorEstados(listaEstadosPozo));
        
    }

    public void cargarDatosPozo(ActionEvent ev) {

        this.pozoDto = managedPozo.getPozoDto();
        
//        final int idPozo = managedPozo.getIdPozo();
//        if (idPozo != 0) {
//            pozoDto = pozoService.findId(idPozo);
//
//            System.out.println("Pozo " + pozoDto.getNombre());
//        } else {
//            System.out.println("Id de pozo NULL ");
//        }
    }

    public ManagedPozo getManagedPozo() {
        return managedPozo;
    }

    public void setManagedPozo(ManagedPozo managedPozo) {
        this.managedPozo = managedPozo;
    }

    public PozoDto getPozoDto() {
        return pozoDto;
    }

    public void setPozoDto(PozoDto pozoDto) {
        this.pozoDto = pozoDto;
    }

}
