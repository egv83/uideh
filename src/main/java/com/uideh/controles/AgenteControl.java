package com.uideh.controles;

import com.uideh.dao.AgenciaDAO;
import com.uideh.dao.AgentesDAO;
import com.uideh.dao.GradoDAO;
import com.uideh.model.Agencia;
import com.uideh.model.Agente;
import com.uideh.model.Grado;
import com.uideh.util.Utilidades;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author Estebn Vallejo
 */
@ManagedBean
@SessionScoped
public class AgenteControl extends Utilidades implements Serializable {

    @EJB
    AgentesDAO agenteDAO;
    @EJB
    GradoDAO gradoDAO;
    @EJB
    AgenciaDAO agenciaDAO;

    private Agente agente;
    private Agente agenteSelect;
    private List<Agente> listaAgente;
    private Boolean accionBoton;
    private String lblAccion;

    public AgenteControl() {
    }

    @PostConstruct
    public void inicio() {
        this.agente = new Agente();
//        this.agenteSelect = new Agente();
        this.listaAgente = null;
    }

    public String grabar() {
        if (this.getAgente() != null) {
            try {
                agenteDAO.crear(this.getAgente());
                this.agente = new Agente();
                this.listaAgente = null;
                addSuccessMessage(RegistroGuardado);
                execute("PF('dlAgente').hide();");
            } catch (Exception e) {
            }
        }
        return "";
    }

    public String modificar() {
        if (this.getAgente() != null) {
            try {
                agenteDAO.modificar(this.getAgente());
                this.agente = new Agente();
                this.listaAgente = null;
                this.agenteSelect = null;
                addSuccessMessage(RegistroModificado);
                execute("PF('dlAgente').hide();");
            } catch (Exception e) {
            }
        }
        return "";
    }

    public void eliminar() {
        System.out.println("AGENTE A ELIMINAR: " + this.getAgenteSelect().getIdagente());
        System.out.println("AGENTE NOMBRE: " + this.getAgenteSelect().getNombres());
        if (this.getAgenteSelect() != null) {
            agenteDAO.eliminar(this.getAgenteSelect());
            this.agente = new Agente();
            this.agenteSelect = null;
            this.listaAgente = null;
            addSuccessMessage(RegistroEliminado);
        }
    }

    public List<Agente> getListaAgente() {
        if (this.listaAgente == null) {
            this.listaAgente = agenteDAO.allAgentes();
        }
        return this.listaAgente;
    }

    public List<SelectItem> getListaAgencia() {
        List<SelectItem> lista = new ArrayList<SelectItem>();
        try {
            for (Agencia agencia : agenciaDAO.allAgencias()) {
                lista.add(new SelectItem(agencia.getIdagencia(), agencia.getNombre()));
            }
        } catch (Exception e) {
        }
        return lista;
    }

    public List<SelectItem> getListaGrado() {
        List<SelectItem> lista = new ArrayList<SelectItem>();
        try {
            for (Grado grado : gradoDAO.allGrados()) {
                lista.add(new SelectItem(grado.getIdgrado(), grado.getNombre()));
            }
        } catch (Exception e) {
        }
        return lista;
    }

    public void estadoBoton(String accion) {
        if ("n".equals(accion)) {
            this.setAccionBoton(Boolean.TRUE);
            this.setLblAccion("Nueva Agencia");
        } else {
            this.setAccionBoton(Boolean.FALSE);
            this.setLblAccion("Modificar Agencia");
        }
    }

    public void seleciondo() {
        this.setAgente(this.getAgenteSelect());
    }

    public Agente getAgente() {
        return agente;
    }

    public void setAgente(Agente agente) {
        this.agente = agente;
    }

    public Agente getAgenteSelect() {
        return agenteSelect;
    }

    public void setAgenteSelect(Agente agenteSelect) {
        this.agenteSelect = agenteSelect;
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
