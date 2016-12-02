package com.uideh.controles.variables;

import com.uideh.dao.variables.PresentacionProyectoDAO;
import com.uideh.dao.variables.ResultadosDAO;
import com.uideh.model.Presproyecto;
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
public class PresentacionProyectoControl extends Utilidades implements Serializable {

    @EJB
    PresentacionProyectoDAO presentacionProyectoDAO;

    private Presproyecto presProyecto;
    private Presproyecto presProyectoSelect;
    private List<Presproyecto> listaPresProyecto;
    private Boolean accionBoton;
    private String lblAccion;

    public PresentacionProyectoControl() {
    }

    @PostConstruct
    public void inicio() {
        this.presProyecto = new Presproyecto();
        this.presProyectoSelect = null;
    }

    public List<Presproyecto> getListaPresproyecto() {
        if (this.listaPresProyecto == null) {
            this.listaPresProyecto = presentacionProyectoDAO.allResultados();
        }
        return this.listaPresProyecto;
    }

    public String grabar() {
        if (this.getPresProyecto() != null) {
            try {
                presentacionProyectoDAO.crear(this.getPresProyecto());
                this.presProyecto = new Presproyecto();
                this.listaPresProyecto = null;
                addSuccessMessage(RegistroGuardado);
                execute("PF('dlPresProy').hide();");
            } catch (Exception e) {
            }
        }
        return "";
    }

    public String modificar() {
        if (this.getPresProyecto() != null) {
            try {
                presentacionProyectoDAO.modificar(this.getPresProyecto());
                this.presProyecto = new Presproyecto();
                this.listaPresProyecto = null;
                this.presProyectoSelect = null;
                addSuccessMessage(RegistroModificado);
                execute("PF('dlPresProy').hide();");
            } catch (Exception e) {
            }
        }
        return "";
    }

    public void eliminar() {
        if (this.getPresProyectoSelect() != null) {
            presentacionProyectoDAO.eliminar(this.getPresProyectoSelect());
            this.presProyecto = new Presproyecto();
            this.presProyectoSelect = null;
            this.listaPresProyecto = null;
            addSuccessMessage(RegistroEliminado);
        }
    }

    public void seleciondo() {
        this.setPresProyecto(this.getPresProyectoSelect());
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

    public Presproyecto getPresProyecto() {
        return presProyecto;
    }

    public void setPresProyecto(Presproyecto presProyecto) {
        this.presProyecto = presProyecto;
    }

    public Presproyecto getPresProyectoSelect() {
        return presProyectoSelect;
    }

    public void setPresProyectoSelect(Presproyecto presProyectoSelect) {
        this.presProyectoSelect = presProyectoSelect;
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