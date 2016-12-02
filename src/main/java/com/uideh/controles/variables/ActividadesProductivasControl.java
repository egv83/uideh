package com.uideh.controles.variables;

import com.uideh.dao.variables.ActividadesProductivasDAO;
import com.uideh.dao.variables.PresentacionProyectoDAO;
import com.uideh.dao.variables.ResultadosDAO;
import com.uideh.model.Actproductivas;
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
public class ActividadesProductivasControl extends Utilidades implements Serializable {

    @EJB
    ActividadesProductivasDAO actividadesProductivasDAO;

    private Actproductivas actProductivas;
    private Actproductivas actProductivasSelect;
    private List<Actproductivas> listaActProductivas;
    private Boolean accionBoton;
    private String lblAccion;

    public ActividadesProductivasControl() {
    }

    @PostConstruct
    public void inicio() {
        this.actProductivas = new Actproductivas();
        this.actProductivasSelect = null;
    }

    public List<Actproductivas> getListaActproductivas() {
        if (this.listaActProductivas == null) {
            this.listaActProductivas = actividadesProductivasDAO.allResultados();
        }
        return this.listaActProductivas;
    }

    public String grabar() {
        if (this.getActProductivas() != null) {
            try {
                actividadesProductivasDAO.crear(this.getActProductivas());
                this.actProductivas = new Actproductivas();
                this.listaActProductivas = null;
                addSuccessMessage(RegistroGuardado);
                execute("PF('dlActPro').hide();");
            } catch (Exception e) {
            }
        }
        return "";
    }

    public String modificar() {
        if (this.getActProductivas() != null) {
            try {
                actividadesProductivasDAO.modificar(this.getActProductivas());
                this.actProductivas = new Actproductivas();
                this.listaActProductivas = null;
                this.actProductivasSelect = null;
                addSuccessMessage(RegistroModificado);
                execute("PF('dlActPro').hide();");
            } catch (Exception e) {
            }
        }
        return "";
    }

    public void eliminar() {
        if (this.getActProductivas() != null) {
            actividadesProductivasDAO.eliminar(this.getActProductivasSelect());
            this.actProductivas = new Actproductivas();
            this.actProductivasSelect = null;
            this.listaActProductivas = null;
            addSuccessMessage(RegistroEliminado);
        }
    }

    public void seleciondo() {
        this.setActProductivas(this.getActProductivasSelect());
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

    public Actproductivas getActProductivas() {
        return actProductivas;
    }

    public void setActProductivas(Actproductivas actProductivas) {
        this.actProductivas = actProductivas;
    }

    public Actproductivas getActProductivasSelect() {
        return actProductivasSelect;
    }

    public void setActProductivasSelect(Actproductivas actProductivasSelect) {
        this.actProductivasSelect = actProductivasSelect;
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