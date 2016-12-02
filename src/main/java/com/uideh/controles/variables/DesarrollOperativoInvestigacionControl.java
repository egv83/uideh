package com.uideh.controles.variables;

import com.uideh.dao.variables.DesarrollOperativoInvestigacionDAO;
import com.uideh.model.Desaoperativo;
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
public class DesarrollOperativoInvestigacionControl extends Utilidades implements Serializable{

    @EJB
    DesarrollOperativoInvestigacionDAO desarrollOperativoInvestigacionDAO;
    
    private Desaoperativo desaOperativo;
    private Desaoperativo desaOperativoSelect;
    private List<Desaoperativo> listaDesaOperativoInv;
    private Boolean accionBoton;
    private String lblAccion;
    
    public DesarrollOperativoInvestigacionControl() {
    }
    
    @PostConstruct
    public void inicio(){
        this.desaOperativo = new Desaoperativo();
        this.desaOperativoSelect = null;
    }
    
       public List<Desaoperativo> getListaDesaOperativoInv() {
        if (this.listaDesaOperativoInv == null) {
            this.listaDesaOperativoInv = desarrollOperativoInvestigacionDAO.allDesarrollOperativoInv();
        }
        return this.listaDesaOperativoInv;
    }
    
    public String grabar() {
        if (this.getDesaOperativo()!= null) {
            try {
                desarrollOperativoInvestigacionDAO.crear(this.getDesaOperativo());
                this.desaOperativo = new Desaoperativo();
                this.listaDesaOperativoInv = null;
                addSuccessMessage(RegistroGuardado);
                execute("PF('dlOperInv').hide();");
            } catch (Exception e) {
            }
        }
        return "";
    }
    
    public String modificar() {
        if (this.getDesaOperativo() != null) {
            try {
                desarrollOperativoInvestigacionDAO.modificar(this.getDesaOperativo());
                this.desaOperativo = new Desaoperativo();
                this.listaDesaOperativoInv= null;
                this.desaOperativoSelect = null;
                addSuccessMessage(RegistroModificado);
                execute("PF('dlOperInv').hide();");
            } catch (Exception e) {
            }
        }
        return "";
    }
    
     public void eliminar() {
        if (this.getDesaOperativoSelect()!= null) {
            desarrollOperativoInvestigacionDAO.eliminar(this.getDesaOperativoSelect());
            this.desaOperativoSelect = null;
            this.listaDesaOperativoInv = null;
            this.desaOperativo = new Desaoperativo();
            addSuccessMessage(RegistroEliminado);
        }
    }
    
    public void seleciondo() {
        this.setDesaOperativo(this.getDesaOperativoSelect());
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

    public Desaoperativo getDesaOperativo() {
        return desaOperativo;
    }

    public void setDesaOperativo(Desaoperativo desaOperativo) {
        this.desaOperativo = desaOperativo;
    }

    public Desaoperativo getDesaOperativoSelect() {
        return desaOperativoSelect;
    }
    
    public void setDesaOperativoSelect(Desaoperativo desaOperativoSelect) {
        this.desaOperativoSelect = desaOperativoSelect;
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
