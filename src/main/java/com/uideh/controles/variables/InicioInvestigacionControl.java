package com.uideh.controles.variables;

import com.uideh.dao.variables.InicioInvestigacionDAO;
import com.uideh.model.Inicinvetigacion;
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
public class InicioInvestigacionControl extends Utilidades implements Serializable {

    @EJB
    InicioInvestigacionDAO inicioInvestigacionDAO;

    private Inicinvetigacion inicInvetigacion;
    private Inicinvetigacion inicInvetigacionSelect;
    private List<Inicinvetigacion> listaInicInvetigacion;
    private Boolean accionBoton;
    private String lblAccion;

    public InicioInvestigacionControl() {
    }

    @PostConstruct
    public void inicio() {
        this.inicInvetigacion = new Inicinvetigacion();
        this.inicInvetigacionSelect = null;
    }

    public List<Inicinvetigacion> getListaInicinvetigacion() {
        if (this.listaInicInvetigacion == null) {
            this.listaInicInvetigacion = inicioInvestigacionDAO.allResultados();
        }
        return this.listaInicInvetigacion;
    }

    public String grabar() {
        if (this.getInicInvetigacion()!= null) {
            try {
                inicioInvestigacionDAO.crear(this.getInicInvetigacion());
                this.inicInvetigacion= new Inicinvetigacion();
                this.listaInicInvetigacion = null;
                addSuccessMessage(RegistroGuardado);
                execute("PF('dlInicProy').hide();");
            } catch (Exception e) {
            }
        }
        return "";
    }

    public String modificar() {
        if (this.getInicInvetigacion()!= null) {
            try {
                inicioInvestigacionDAO.modificar(this.getInicInvetigacion());
                this.inicInvetigacion = new Inicinvetigacion();
                this.listaInicInvetigacion = null;
                this.inicInvetigacionSelect = null;
                addSuccessMessage(RegistroModificado);
                execute("PF('dlInicProy').hide();");
            } catch (Exception e) {
            }
        }
        return "";
    }

    public void eliminar() {
        if (this.getInicInvetigacionSelect()!= null) {
            inicioInvestigacionDAO.eliminar(this.getInicInvetigacionSelect());
            this.inicInvetigacion = new Inicinvetigacion();
            this.inicInvetigacionSelect= null;
            this.listaInicInvetigacion = null;
            addSuccessMessage(RegistroEliminado);
        }
    }

    public void seleciondo() {
        this.setInicInvetigacion(this.getInicInvetigacionSelect());
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

    public Inicinvetigacion getInicInvetigacion() {
        return inicInvetigacion;
    }

    public void setInicInvetigacion(Inicinvetigacion inicInvetigacion) {
        this.inicInvetigacion = inicInvetigacion;
    }

    public Inicinvetigacion getInicInvetigacionSelect() {
        return inicInvetigacionSelect;
    }

    public void setInicInvetigacionSelect(Inicinvetigacion inicInvetigacionSelect) {
        this.inicInvetigacionSelect = inicInvetigacionSelect;
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