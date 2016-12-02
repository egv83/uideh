package com.uideh.controles.variables;

import com.uideh.dao.variables.AutorizacionProyectoDAO;
import com.uideh.model.Autoproyecto;
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
public class AutorizacionProyectoControl extends Utilidades implements Serializable {

    @EJB
    AutorizacionProyectoDAO autorizacionProyectoDAO;

    private Autoproyecto autoProyecto;
    private Autoproyecto autoProyectoSelect;
    private List<Autoproyecto> listaAutoProyecto;
    private Boolean accionBoton;
    private String lblAccion;

    public AutorizacionProyectoControl() {
    }

    @PostConstruct
    public void inicio() {
        this.autoProyecto = new Autoproyecto();
        this.autoProyectoSelect = null;
    }

    public List<Autoproyecto> getListaAutoProyecto() {
        if (this.listaAutoProyecto == null) {
            this.listaAutoProyecto = autorizacionProyectoDAO.allResultados();
        }
        return this.listaAutoProyecto;
    }

    public String grabar() {
        if (this.getAutoProyecto() != null) {
            try {
                autorizacionProyectoDAO.crear(this.getAutoProyecto());
                this.autoProyecto = new Autoproyecto();
                this.listaAutoProyecto = null;
                addSuccessMessage(RegistroGuardado);
                execute("PF('dlAutoProy').hide();");
            } catch (Exception e) {
            }
        }
        return "";
    }

    public String modificar() {
        if (this.getAutoProyecto() != null) {
            try {
                autorizacionProyectoDAO.modificar(this.getAutoProyecto());
                this.autoProyecto = new Autoproyecto();
                this.listaAutoProyecto = null;
                this.autoProyectoSelect = null;
                addSuccessMessage(RegistroModificado);
                execute("PF('dlAutoProy').hide();");
            } catch (Exception e) {
            }
        }
        return "";
    }

    public void eliminar() {
        if (this.getAutoProyectoSelect() != null) {
            autorizacionProyectoDAO.eliminar(this.getAutoProyectoSelect());
            this.autoProyecto = new Autoproyecto();
            this.listaAutoProyecto = null;
            this.autoProyectoSelect = null;
            addSuccessMessage(RegistroEliminado);
        }
    }

    public void seleciondo() {
        this.setAutoProyecto(this.getAutoProyectoSelect());
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

    public Autoproyecto getAutoProyecto() {
        return autoProyecto;
    }

    public void setAutoProyecto(Autoproyecto autoProyecto) {
        this.autoProyecto = autoProyecto;
    }

    public Autoproyecto getAutoProyectoSelect() {
        return autoProyectoSelect;
    }

    public void setAutoProyectoSelect(Autoproyecto autoProyectoSelect) {
        this.autoProyectoSelect = autoProyectoSelect;
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
