package com.uideh.controles;

import com.uideh.dao.AgenciaDAO;
import com.uideh.model.Agencia;
import com.uideh.util.Utilidades;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Esteban Vallejo
 */
@ManagedBean
@SessionScoped
public class AgenciaControl extends Utilidades implements Serializable {

    @EJB
    AgenciaDAO agenciaDAO;

    private Agencia agencia;
    private Agencia agenciaSelect;
    private List<Agencia> listaAgencia;

    private Boolean accionBoton;
    private String lblAccion;

    public AgenciaControl() {

    }

    @PostConstruct
    public void inicio() {
        this.agencia = new Agencia();
        this.listaAgencia = null;
    }

    public List<Agencia> getListaAgencia() {
        if (this.listaAgencia == null) {
            this.listaAgencia = agenciaDAO.allAgencias();
        }
        return this.listaAgencia;
    }

    public String grabar() {
        if (this.getAgencia() != null) {
            try {
                agenciaDAO.crear(this.getAgencia());
                this.listaAgencia = null;
                this.agencia = new Agencia();
                addSuccessMessage(RegistroGuardado);
                execute("PF('dlAgencia').hide();");
            } catch (Exception e) {
            }
        }
        return "";
    }

    public String modificar() {
        if (this.getAgencia() != null) {
            try {
                agenciaDAO.modificar(this.getAgencia());
                this.agencia = new Agencia();
                this.listaAgencia = null;
                this.agenciaSelect = null;
                addSuccessMessage(RegistroModificado);
                execute("PF('dlAgencia').hide();");
            } catch (Exception e) {
            }
        }
        return "";
    }

    public void eliminar() {
        if (this.getAgenciaSelect() != null) {
            agenciaDAO.eliminar(this.getAgenciaSelect());
            this.agenciaSelect = null;
            this.listaAgencia = null;
            this.agencia = new Agencia();
            addSuccessMessage(RegistroEliminado);
        }
    }

    public void seleciondo() {
        this.setAgencia(this.getAgenciaSelect());
    }

    public void estadoAgencia(String accion) {
        if ("n".equals(accion)) {
            this.setAccionBoton(Boolean.TRUE);
            this.setLblAccion("Nueva Agencia");
        } else {
            this.setAccionBoton(Boolean.FALSE);
            this.setLblAccion("Modificar Agencia");
        }
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    public Agencia getAgenciaSelect() {
        return agenciaSelect;
    }

    public void setAgenciaSelect(Agencia agenciaSelect) {
        this.agenciaSelect = agenciaSelect;
    }

    public void setListaAgencia(List<Agencia> listaAgencia) {
        this.listaAgencia = listaAgencia;
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
