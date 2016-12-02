package com.uideh.controles.variables;

import com.uideh.dao.variables.DesarrolloInvestigacionDAO;
import com.uideh.model.Desainvestigacion;
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
public class DesarrolloInvestigacionControl extends Utilidades implements Serializable {

    @EJB
    DesarrolloInvestigacionDAO desarrolloInvestigacionDAO;

    private Desainvestigacion desaInvestigacion;
    private Desainvestigacion desaInvestigacionSelect;
    private List<Desainvestigacion> listaDesaInvestigacion;
    private Boolean accionBoton;
    private String lblAccion;

    public DesarrolloInvestigacionControl() {
    }

    @PostConstruct
    public void inicio() {
        this.desaInvestigacion = new Desainvestigacion();
        this.desaInvestigacionSelect = null;
    }

    public List<Desainvestigacion> getListaDesaInvestigacion() {
        if (this.listaDesaInvestigacion == null) {
            this.listaDesaInvestigacion = desarrolloInvestigacionDAO.allDesainvestigacion();
        }
        return this.listaDesaInvestigacion;
    }

    public String grabar() {
        if (this.getDesaInvestigacion()!= null) {
            try {
                desarrolloInvestigacionDAO.crear(this.getDesaInvestigacion());
                this.desaInvestigacion = new Desainvestigacion();
                this.listaDesaInvestigacion = null;
                addSuccessMessage(RegistroGuardado);
                execute("PF('dlDesInves').hide();");
            } catch (Exception e) {
            }
        }
        return "";
    }

    public String modificar() {
        if (this.getDesaInvestigacion() != null) {
            try {
                desarrolloInvestigacionDAO.modificar(this.getDesaInvestigacion());
                this.desaInvestigacion = new Desainvestigacion();
                this.listaDesaInvestigacion = null;
                this.desaInvestigacion = null;
                addSuccessMessage(RegistroModificado);
                execute("PF('dlDesInves').hide();");
            } catch (Exception e) {
            }
        }
        return "";
    }

    public void eliminar() {
        if (this.getDesainvestigacionSelect()!= null) {
            desarrolloInvestigacionDAO.eliminar(this.getDesainvestigacionSelect());
            this.desaInvestigacion = new Desainvestigacion();
            this.desaInvestigacionSelect = null;
            this.listaDesaInvestigacion = null;
            addSuccessMessage(RegistroEliminado);
        }
    }

    public void seleciondo() {
        this.setDesaInvestigacion(this.getDesainvestigacionSelect());
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

    public Desainvestigacion getDesaInvestigacion() {
        return desaInvestigacion;
    }

    public void setDesaInvestigacion(Desainvestigacion desaInvestigacion) {
        this.desaInvestigacion = desaInvestigacion;    }

    public Desainvestigacion getDesainvestigacionSelect() {
        return desaInvestigacionSelect;
    }

    public void setDesainvestigacionSelect(Desainvestigacion desaInvestigacionSelect) {
        this.desaInvestigacionSelect = desaInvestigacionSelect;
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