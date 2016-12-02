package com.uideh.controles.variables;

import com.uideh.dao.variables.ResultadosDAO;
import com.uideh.model.Resultados;
import com.uideh.util.Utilidades;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Esteban Vallejo
 */
@ManagedBean
@SessionScoped
public class ResultadosControl extends Utilidades implements Serializable {

    @EJB
    ResultadosDAO resultadosDAO;
    
    private Resultados resultados;
    private Resultados resultadosSelect;
    private List<Resultados> listaResultados;
    private Boolean accionBoton;
    private String lblAccion;

    public ResultadosControl() {
    }
    
    @PostConstruct
    public void inicio(){
        this.resultados = new Resultados();
        this.resultadosSelect = null;
    }

    public List<Resultados> getListaResultados(){
        if(this.listaResultados == null){
            this.listaResultados = resultadosDAO.allResultados();
        }
        return this.listaResultados;
    }
    
    public String grabar() {
        if (this.getResultados()!= null) {
            try {
                resultadosDAO.crear(this.getResultados());
                this.resultados = new Resultados();
                this.listaResultados = null;
                addSuccessMessage(RegistroGuardado);
                execute("PF('dlResult').hide();");
            } catch (Exception e) {
            }
        }
        return "";
    }
    
    public String modificar() {
        if (this.getResultados() != null) {
            try {
                resultadosDAO.modificar(this.getResultados());
                this.resultados = new Resultados();
                this.listaResultados = null;
                this.resultadosSelect = null;
                addSuccessMessage(RegistroModificado);
                execute("PF('dlResult').hide();");
            } catch (Exception e) {
            }
        }
        return "";
    }
    
     public void eliminar() {
        if (this.getResultadosSelect()!= null) {
            resultadosDAO.eliminar(this.getResultadosSelect());
            this.resultados = new Resultados();
            this.resultadosSelect = null;
            this.listaResultados = null;
            addSuccessMessage(RegistroEliminado);
        }
    }
    
    public void seleciondo() {
        this.setResultados(this.getResultadosSelect());
    }

    public void estadoBoton(String accion) {
        if ("n".equals(accion)) {
            this.setAccionBoton(Boolean.TRUE);
            this.setLblAccion("Nueva Variable");
        } else {
            this.setAccionBoton(Boolean.FALSE);
            this.setLblAccion("Modificar Variable");
        }
    }
    
    public Resultados getResultados() {
        return resultados;
    }

    public void setResultados(Resultados resultados) {
        this.resultados = resultados;
    }

    public Resultados getResultadosSelect() {
        return resultadosSelect;
    }

    public void setResultadosSelect(Resultados resultadosSelect) {
        this.resultadosSelect = resultadosSelect;
    }

    public Boolean getAccionBoton() {
        return accionBoton;
    }

    public void setAccionBoton(Boolean accionBoton) {
        this.accionBoton = accionBoton;
    }

    public String getLblAccion() {
        return lblAccion;
    }

    public void setLblAccion(String lblAccion) {
        this.lblAccion = lblAccion;
    }
    
}
